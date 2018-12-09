package com.crappyengineering.luker;

import static java.lang.String.format;
import static java.nio.charset.StandardCharsets.UTF_8;
import static java.security.MessageDigest.getInstance;
import static java.time.Duration.between;
import static java.time.Instant.now;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

import com.crappyengineering.Solution;
import com.crappyengineering.helper.Answer;
import com.crappyengineering.helper.luke9.HashNode;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Luke9 implements Solution {

    private static final Logger logger = LoggerFactory.getLogger(Luke9.class);

    private String location;
    private MessageDigest digester;
    private String initialDigest;

    public Luke9(String location, String algorithm, String initialDigest) throws NoSuchAlgorithmException {
        this.location = location;
        this.digester = getInstance(algorithm);
        this.initialDigest = initialDigest;
    }

    @Override
    public void solve() {

        try( BufferedReader reader = new BufferedReader( new InputStreamReader(new URL(location).openStream()))) {

            List<HashNode> nodes = new Gson().fromJson(reader, new TypeToken<List<HashNode>>() {}.getType());
            StringBuilder sb = new StringBuilder();

            Instant start = now();
            HashNode current = findNextNode(new HashNode(createNewDigest(initialDigest)), nodes).orElseThrow(IllegalStateException::new);
            while(nodes.size() > 0) {
                if(current != null) {
                    sb.append(current.getLetter());
                    nodes.remove(current);
                } else {
                    logger.info("Chain broken");
                    break;
                }
                current = findNextNode(current, nodes).orElse(null);
            }

            logger.info("Found answer in {} ms", between(start, now()).toMillis());
            logger.info("{}", new Answer(sb.toString()));

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private Optional<HashNode> findNextNode(HashNode current, List<HashNode> nodes) {
        return nodes.stream()
                .filter(hashNode -> hashNode.getMd5Hash().equals(createNewDigest(current.getMd5Hash(), hashNode.getLetter())))
                .findFirst();

    }

    private String createNewDigest(String hash, String letter) {
        return format("%032x", new BigInteger(1, digester.digest((hash+letter).getBytes(UTF_8))));
    }

    private String createNewDigest(String inital) {
        return createNewDigest(inital, "");
    }
}
