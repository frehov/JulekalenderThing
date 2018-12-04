package com.crappyengineering.helper.luke3;

import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.stream.LongStream;

public class Primes {

    public static List<Long> generateAllPrimesBelow(long primeLimit) {
        return LongStream.rangeClosed(2, primeLimit)
                .filter(number -> number >= 2 && LongStream.rangeClosed(2, (int) Math.sqrt(number))
                        .noneMatch(n -> (number % n == 0)))
                .boxed()
                .collect(toList());
    }
}
