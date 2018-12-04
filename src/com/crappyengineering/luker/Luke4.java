package com.crappyengineering.luker;

import static java.lang.String.format;

import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.stream.Stream;

import javax.imageio.ImageIO;

import com.crappyengineering.Solution;
import com.crappyengineering.helper.Answer;
import com.crappyengineering.helper.luke4.ImageIterationHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

public class Luke4 implements Solution {

    private final static Logger logger = LoggerFactory.getLogger(Luke4.class);

    final String location;
    final int iterations;

    public Luke4(String location, Integer iterations) {
        this.location = location;
        this.iterations = iterations;
    }

    @Override
    public void solve() {
        try {
            BufferedImage image = ImageIO.read(new URL(location).openStream());

            Stream.iterate(0, iteration -> ++iteration).limit(iterations).parallel()
                    .map(iteration -> new ImageIterationHolder(iteration, new BufferedImage(image.getWidth(), image.getHeight(), image.getType())))
                    .peek(imageHolder -> Stream
                            .iterate(0, integerX -> ++integerX).limit(image.getWidth()).parallel()
                            .peek(integerX -> Stream
                                    .iterate(0, integerY -> ++integerY).limit(image.getHeight()).parallel()
                                    .peek(integerY -> imageHolder.getImage().setRGB(integerX, integerY, image.getRGB(integerX, integerY) << imageHolder.getIteration()))
                                    .toArray())
                            .toArray())
                    .map(imageHolder -> writeImageOutput(imageHolder.getImage(), imageHolder.getIteration()))
                    .toArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean writeImageOutput(RenderedImage image, int iteration) {
        final String className = this.getClass().getSimpleName();
        final String imageLocation = format("logs/%s_iter_%d.png", className, iteration);

        try {
            // Override the pesky stream thread names and reattach the taskId
            MDC.put("taskId", className);
            Thread.currentThread().setName(className);

            logger.info("{}", new Answer(imageLocation));
            return ImageIO.write(image, "png", new File(imageLocation));
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
