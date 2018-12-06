package com.crappyengineering;

import static java.lang.Class.forName;
import static java.lang.String.format;
import static java.util.Arrays.asList;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JulekalenderLoader {

    private static final String LUKE_PACKAGE = "com.crappyengineering.luker";

    private static final Logger logger = LoggerFactory.getLogger(JulekalenderLoader.class);

    private final static Class[][] constructorParameters = {
            {String.class},
            {String.class},
            {Long.class, Integer.class, Long.class},
            {String.class, Integer.class},
            {List.class},
            {Long.class, Long.class}

    };

    private final static Object[][] constructorInputs = {
            {"https://s3-eu-west-1.amazonaws.com/knowit-julekalender-2018/input-vekksort.txt"},
            {"https://s3-eu-west-1.amazonaws.com/knowit-julekalender-2018/input-rain.txt"},
            {4294967296L, 24, 1000L},
            {"https://s3-eu-west-1.amazonaws.com/knowit-julekalender-2018/input-pokemon-jakt.png", 8},
            {asList(1, 2, 3, 4, 5, 6, 7, 8, 7, 6, 5, 4, 3, 2, 1)},
            {1L, 18163106L}
    };


    public static void main(String[] args) {
        for (int i = 1; i <= 24; i++) {
            try {
                new Thread(
                        ((Solution) forName(format("%s.Luke%s", LUKE_PACKAGE, i))
                                .getConstructor(constructorParameters[i - 1])
                                .newInstance(constructorInputs[i - 1]))
                ).start();
            } catch (ClassNotFoundException e) {
                logger.warn(format("%6s not implemented!",
                        e.getMessage().replace(LUKE_PACKAGE+".", "")));
            } catch (NoSuchMethodException e) {
                logger.warn(format("%6s does not implement a constructor with parameters %s!",
                        e.getMessage().replace(LUKE_PACKAGE+".", "").replaceAll("\\.<init>.*", ""),
                        e.getMessage().replaceAll(LUKE_PACKAGE, "").replaceAll("\\.Luke\\d+\\.<init>", "")));

            } catch (IllegalArgumentException e) {
                logger.warn(format("Failed to load class #%d due to \"%s\"!",
                        i,
                        e.getMessage()));
            } catch (InvocationTargetException e) {
                logger.warn(format("Constructor of class #%d threw an \"%s\" with the message \"%s\"!",
                        i,
                        e.getCause().getClass().getSimpleName(),
                        e.getCause().getMessage()));
            } catch (IllegalAccessException | InstantiationException e) {
                e.printStackTrace();
            }
        }
    }
}
