package com.crappyengineering;

import static java.lang.Thread.currentThread;
import static org.slf4j.LoggerFactory.getLogger;

import org.slf4j.Logger;
import org.slf4j.MDC;

public interface Solution extends Runnable {

    void solve();

    @Override
    default void run() {
        Logger logger = getLogger(this.getClass().getName());

        currentThread().setName(this.getClass().getSimpleName());
        MDC.put("taskId", this.getClass().getSimpleName());
        logger.info("Started running!");
        solve();
        logger.info("Finished running!");
        MDC.clear();
    }
}
