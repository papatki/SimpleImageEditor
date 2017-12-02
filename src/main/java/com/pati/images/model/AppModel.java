package com.pati.images.model;

import java.awt.image.BufferedImage;

public class AppModel {
    private BufferedImage image;

    public AppModel(BufferedImage image) {
        this.image = image;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }
}
