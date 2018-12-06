package com.crappyengineering.luker;

import java.time.Instant;
import java.util.stream.Stream;

import com.crappyengineering.Solution;
import com.crappyengineering.helper.Answer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Luke6 implements Solution {

    private static final Logger logger = LoggerFactory.getLogger(Luke6.class);

    private final long start;
    private final long end;

    public Luke6(Long start, Long end) {
        this.start = start;
        this.end = end;
    }


    @Override
    public void solve() {

        logger.info("{}", new Answer(Stream.iterate(start, along -> ++along).limit(end).parallel()
                .filter(aLong -> aLong.toString().contains("0"))
                .filter(aLong -> (aLong.toString().chars().filter(value -> value == '0').count()<<1) - aLong.toString().length() > 0)
                .mapToLong(value -> value)
                .sum()
                ));
    }
}
