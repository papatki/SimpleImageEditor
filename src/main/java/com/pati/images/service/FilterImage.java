package com.pati.images.service;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.ImageObserver;
import java.awt.image.Kernel;

public class FilterImage {

    public static void makeGray(BufferedImage image) {
        int height = image.getHeight();
        int width = image.getWidth();

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Color color = new Color(image.getRGB(i, j));
                int red = color.getRed();
                int green = color.getGreen();
                int blue = color.getBlue();
                int avr = (red + green + blue) / 3;

                Color newColor = new Color(avr, avr, avr);

                image.setRGB(i, j, newColor.getRGB());
            }
        }
    }

    public static  void makeNegative(BufferedImage image) {
        int height = image.getHeight();
        int width = image.getWidth();

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                int p = image.getRGB(i, j);

                int a = (p >> 24) & 0xff;
                int red = (p >> 16) & 0xff;
                int green = (p >> 8) & 0xff;
                int blue = p & 0xff;

                red = 255 - red;
                green = 255 - green;
                blue = 255 - blue;

                p = (a << 24) | (red << 16) | (green << 8) | blue;
                image.setRGB(i, j, p);
            }
        }
    }

    public static  void makeSepia(BufferedImage image) {
        int height = image.getHeight();
        int width = image.getWidth();

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                int p = image.getRGB(i, j);

                int a = (p >> 24) & 0xff;
                int red = (p >> 16) & 0xff;
                int green = (p >> 8) & 0xff;
                int blue = p & 0xff;

                int newRed = (int) (red * .393 + green * .769 + blue * .189);
                int newGreen = (int) (red * .349 + green * .689 + blue * .168);
                int newBlue = (int) (red * .272 + green * .534 + blue * .131);

                if (newRed > 255) {
                    red = 255;
                } else {
                    red = newRed;
                }

                if (newGreen > 255) {
                    green = 255;
                } else {
                    green = newGreen;
                }

                if (newBlue > 255) {
                    blue = 255;
                } else {
                    blue = newBlue;
                }

                p = (a << 24) | (red << 16) | (green << 8) | blue;

                image.setRGB(i, j, p);
            }
        }
    }

    public void makeBlurry(BufferedImage image) { // todo rewrite methods without Graphics2D ???

        int height = image.getHeight();
        int width = image.getWidth();

        BufferedImage biSrc = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics2D;
        graphics2D = biSrc.createGraphics();

        graphics2D.drawImage(image, 0, 0, (ImageObserver) this);

        BufferedImage biDest = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        float[] data = {0.0625f, 0.125f, 0.0625f,
                0.125f, 0.25f, 0.125f,
                0.0625f, 0.125f, 0.0625f};
        Kernel kernel = new Kernel(3, 3, data);
        ConvolveOp convolveOp = new ConvolveOp(kernel, ConvolveOp.EDGE_NO_OP, null);
        convolveOp.filter(biSrc, biDest);
        image = biDest;
        image.getGraphics();
    }

    public  void makeSharpen(BufferedImage image) {

        int height = image.getHeight();
        int width = image.getWidth();

        BufferedImage biSrc = new BufferedImage(width,
                height, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics2D;
        graphics2D = biSrc.createGraphics();
        graphics2D.drawImage(image, 0, 0,(ImageObserver) this);

        BufferedImage biDest = new BufferedImage(width,
                height, BufferedImage.TYPE_INT_RGB);
        float data[] = {-1.0f, -1.0f, -1.0f,
                -1.0f, 9.0f, -1.0f,
                -1.0f, -1.0f, -1.0f,};
        Kernel kernel = new Kernel(3, 3, data);
        ConvolveOp convolveOp = new ConvolveOp(kernel, ConvolveOp.EDGE_NO_OP, null);
        convolveOp.filter(biSrc, biDest);
        image = biDest;
        image.getGraphics();
    }

    public void detectEdges(BufferedImage image) {
        int height = image.getHeight();
        int width = image.getWidth();
        BufferedImage biSrc = new BufferedImage(width,
                height, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics2D;
        graphics2D = biSrc.createGraphics();
        graphics2D.drawImage(image, 0, 0, (ImageObserver) this);

        BufferedImage biDest = new BufferedImage(width,
                height, BufferedImage.TYPE_INT_RGB);
        float data[] = {1.0f, 0.0f, -1.0f,
                1.0f, 0.0f, -1.0f,
                1.0f, 0.0f, -1.0f,};
        Kernel kernel = new Kernel(3, 3, data);
        ConvolveOp convolveOp = new ConvolveOp(kernel, ConvolveOp.EDGE_NO_OP, null);
        convolveOp.filter(biSrc, biDest);
        image = biDest;
        image.getGraphics();
    }

}
