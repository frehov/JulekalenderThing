package com.crappyengineering.luker;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import com.crappyengineering.helper.Answer;
import com.crappyengineering.Solution;
import com.crappyengineering.helper.luke2.Line;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Luke2 implements Solution {

    private static final Logger logger = LoggerFactory.getLogger(Luke2.class);

    final String location;
    final Map<Double, Integer> slopeMap;
    private int sum;

    public Luke2(String location) {
        this.location = location;
        slopeMap = new HashMap<>();
    }

    @Override
    public void solve() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new URL(location).openStream()))) {
            String input;

            while ((input = reader.readLine()) != null) {
                slopeMap.compute(Line.of(input.split(";")).getSlope(), (key, value) -> value == null ? 1 : value+1);
            }

            sum = slopeMap.values().stream().reduce(0, (integer, integer2) -> integer > integer2 ? integer : integer2);

            logger.info("{}", new Answer(sum));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
