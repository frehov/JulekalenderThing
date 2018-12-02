package com.crappyengineering.luker;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import com.crappyengineering.Answer;
import com.crappyengineering.Solution;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Luke1 implements Solution {

    private static final Logger logger = LoggerFactory.getLogger(Luke1.class);

    long sum;
    final String location;

    public Luke1(String location) {
        sum = 0;
        this.location = location;
    }

    @Override
    public void solve() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new URL(location).openStream()))) {
            String line;
            long currentNumber = 0;

            while ((line = reader.readLine()) != null) {
                long number = Long.parseLong(line);

                if (number >= currentNumber) {
                    currentNumber = number;
                    sum += number;
                }
            }
            logger.info("{}", new Answer(sum));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        logger.info("Started running!");
        solve();
        logger.info("Finished running!");
    }
}
