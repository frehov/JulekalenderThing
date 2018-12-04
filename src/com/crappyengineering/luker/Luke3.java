package com.crappyengineering.luker;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

import com.crappyengineering.Solution;
import com.crappyengineering.helper.Answer;
import com.crappyengineering.helper.luke3.Primes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Luke3 implements Solution {

    private final static Logger logger = LoggerFactory.getLogger(Luke3.class);

    long limit;
    int factors;
    long primeLimit;

    public Luke3(Long limit, Integer factors, Long primeLimit) {
        this.limit = limit;
        this.factors = factors;
        this.primeLimit = primeLimit;
    }

    @Override
    public void solve() {
        List<Long> primeList = Primes.generateAllPrimesBelow(primeLimit);


        List<List<Long>> factorListList = LongStream.rangeClosed((long) Math.pow(2, 24), limit).parallel().boxed()
                .map(integer -> factorize(primeList, integer))
                .filter(integers -> integers.size() == 24)
                .collect(Collectors.toList());

        logger.info("{}", new Answer(factorListList.size()));

    }

    private List<Long> factorize(List<Long> factors, Long original) {
        List<Long> factorList = new ArrayList<>();
        long number = original;
        for (Long prime : factors ) {
            while(number % prime == 0) {
                number = number/prime;
                factorList.add(prime);
            }
        }
        if(logger.isDebugEnabled() && factorList.size() == 24) {
            logger.debug("{} -> {}", original, factorList);
        }
        return factorList;
    }
}
