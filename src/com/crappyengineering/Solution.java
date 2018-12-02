package com.crappyengineering;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public interface Solution extends Runnable {

    Logger logger = LoggerFactory.getLogger(Solution.class);

    default void solve() {
        logger.error("Not Implemented!");
    }
}
