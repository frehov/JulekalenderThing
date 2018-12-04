package com.crappyengineering.helper.luke4;

import java.awt.image.BufferedImage;

public class ImageIterationHolder {

    private final int iteration;
    private final BufferedImage image;

    public ImageIterationHolder(int iteration, BufferedImage image) {
        this.iteration = iteration;
        this.image = image;
    }

    public int getIteration() {
        return iteration;
    }

    public BufferedImage getImage() {
        return image;
    }
}
