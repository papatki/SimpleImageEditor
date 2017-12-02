package com.pati.images.view;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.IOException;

/**
 * Created by patrioshka on 10/25/17.
 */

public class FileItems extends JPanel {
    private BufferedImage image;
    private File file;
    private JPanel jPanel = new JPanel();
    private JLabel jLabel = new JLabel();
    private ImageIcon icon;
    private final SizedStack<BufferedImage> undoStack = new SizedStack<>(20);
    private final SizedStack<BufferedImage> redoStack = new SizedStack<>(20);

    public void openingImage() {

        FileNameExtensionFilter filter = new FileNameExtensionFilter("Images Files", "jpg", "png", "jpeg");
        JFileChooser fc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        fc.setFileFilter(filter);

        int result = fc.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            file = fc.getSelectedFile();
            if (image == null) {
                try {
                    jPanel.setSize(500, 500);
                    add(jPanel);
                    image = ImageIO.read(file);
                    repaintAnImage(image);
                    saveToUndoStack(image);
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            } else {
                try {
                    image = ImageIO.read(file);
                    repaintAnImage(image);
                    saveToUndoStack(image);
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    private void repaintAnImage(BufferedImage img) {
        icon = new ImageIcon(img);
        jLabel.setIcon(icon);
        jPanel.add(jLabel, BorderLayout.CENTER);
        jPanel.revalidate();
        jPanel.repaint();
    }

    public void undo() {
        if (undoStack.size() > 0) {
            repaintAnImage(undoStack.pop());
        }
    }

    public void redo() {
        if (redoStack.size() > 0) {
            repaintAnImage(redoStack.pop());
        }
    }

    private void saveToUndoStack(BufferedImage img) {
        BufferedImage image = (BufferedImage) icon.getImage();
        Graphics gd = image.createGraphics();
        gd.drawImage(img, 0, 0, img.getWidth(), img.getHeight(), null);
        undoStack.push(image);
        saveToRedoStack(image);
    }

    private void saveToRedoStack(BufferedImage img) {
        BufferedImage image = (BufferedImage) icon.getImage();
        Graphics gd = image.createGraphics();
        gd.drawImage(img, 0, 0, img.getWidth(), img.getHeight(), null);
        redoStack.push(image);
    }

    public void savingImage() {
        FileDialog fileDialog = new FileDialog(new JFrame(), "Save", FileDialog.SAVE);
        fileDialog.setVisible(true);
        String path = fileDialog.getDirectory() + fileDialog.getFile();
        File output = new File(path);

        try {
            BufferedImage img = (BufferedImage) icon.getImage();
            ImageIO.write(img, "jpg", output);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void printingImage() {
        PrinterJob printerJob = PrinterJob.getPrinterJob();
        printerJob.setPrintable(new Printable() {

            @Override
            public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
                BufferedImage img = (BufferedImage) icon.getImage();

                graphics.drawImage(img, 0, 0, img.getWidth(), img.getHeight(), null);
                if (pageIndex != 0) {
                    return NO_SUCH_PAGE;
                }
                return PAGE_EXISTS;
            }
        });
        if (printerJob.printDialog()) {
            try {
                printerJob.print();
            } catch (PrinterException e) {
                e.printStackTrace();
            }
        }
    }

    public void exitingProgramme() {
        System.exit(0);
    }

    public void makeGray() { // todo this method should receive an image as a argument and operate on it instead of using global object
        if (image == null) {
            JOptionPane.showMessageDialog(new JFrame(), "Upload an image.",
                    "", JOptionPane.INFORMATION_MESSAGE);
        } else {

            try {
                BufferedImage img = ImageIO.read(file);
                int height = img.getHeight();
                int width = img.getWidth();

                for (int i = 0; i < width; i++) {
                    for (int j = 0; j < height; j++) {
                        Color color = new Color(img.getRGB(i, j));
                        int red = color.getRed();
                        int green = color.getGreen();
                        int blue = color.getBlue();
                        int avr = (red + green + blue) / 3;

                        Color newColor = new Color(avr, avr, avr);

                        img.setRGB(i, j, newColor.getRGB());
                    }
                }

                repaintAnImage(img);
                saveToUndoStack(img);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public void makeNegative() throws IOException {
        if (image == null) {
            JOptionPane.showMessageDialog(new JFrame(), "Upload an image.",
                    "", JOptionPane.INFORMATION_MESSAGE);
        } else {
                BufferedImage img = ImageIO.read(file);
                int height = img.getHeight();
                int width = img.getWidth();

                for (int i = 0; i < width; i++) {
                    for (int j = 0; j < height; j++) {
                        int p = img.getRGB(i, j);

                        int a = (p >> 24) & 0xff;
                        int red = (p >> 16) & 0xff;
                        int green = (p >> 8) & 0xff;
                        int blue = p & 0xff;

                        red = 255 - red;
                        green = 255 - green;
                        blue = 255 - blue;

                        p = (a << 24) | (red << 16) | (green << 8) | blue;
                        img.setRGB(i, j, p);
                    }
                }
                repaintAnImage(img);
                saveToUndoStack(img);
        }
    }

    public void makeSepia() {
        if (image == null) {
            JOptionPane.showMessageDialog(new JFrame(), "Upload an image.",
                    "", JOptionPane.INFORMATION_MESSAGE);
        } else {
            try {
                BufferedImage img = ImageIO.read(file);
                int height = img.getHeight();
                int width = img.getWidth();

                for (int i = 0; i < width; i++) {
                    for (int j = 0; j < height; j++) {
                        int p = img.getRGB(i, j);

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

                        img.setRGB(i, j, p);
                    }
                }
                repaintAnImage(img);
                saveToUndoStack(img);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void makeBlurry() {
        if (image == null) {
            JOptionPane.showMessageDialog(new JFrame(), "Upload an image.",
                    "", JOptionPane.INFORMATION_MESSAGE);
        } else {

            try {
                BufferedImage img = ImageIO.read(file);
                BufferedImage biSrc = new BufferedImage(img.getWidth(this),
                        img.getHeight(this), BufferedImage.TYPE_INT_RGB);
                Graphics2D graphics2D;
                graphics2D = biSrc.createGraphics();
                graphics2D.drawImage(img, 0, 0, this);

                BufferedImage biDest = new BufferedImage(img.getWidth(this),
                        img.getHeight(this), BufferedImage.TYPE_INT_RGB);

                float[] data = {0.0625f, 0.125f, 0.0625f,
                        0.125f, 0.25f, 0.125f,
                        0.0625f, 0.125f, 0.0625f};
                Kernel kernel = new Kernel(3, 3, data);
                ConvolveOp convolveOp = new ConvolveOp(kernel, ConvolveOp.EDGE_NO_OP, null);
                convolveOp.filter(biSrc, biDest);
                img = biDest;
                repaintAnImage(img);
                saveToUndoStack(img);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void makeSharpen() {
        if (image == null) {
            JOptionPane.showMessageDialog(new JFrame(), "Upload an image.",
                    "", JOptionPane.INFORMATION_MESSAGE);
        } else {
            try {
                BufferedImage img = ImageIO.read(file);
                BufferedImage biSrc = new BufferedImage(img.getWidth(this),
                        img.getHeight(this), BufferedImage.TYPE_INT_RGB);
                Graphics2D graphics2D;
                graphics2D = biSrc.createGraphics();
                graphics2D.drawImage(img, 0, 0, this);

                BufferedImage biDest = new BufferedImage(img.getWidth(this),
                        img.getHeight(this), BufferedImage.TYPE_INT_RGB);
                float data[] = {-1.0f, -1.0f, -1.0f,
                        -1.0f, 9.0f, -1.0f,
                        -1.0f, -1.0f, -1.0f,};
                Kernel kernel = new Kernel(3, 3, data);
                ConvolveOp convolveOp = new ConvolveOp(kernel, ConvolveOp.EDGE_NO_OP, null);
                convolveOp.filter(biSrc, biDest);
                img = biDest;
                repaintAnImage(img);
                saveToUndoStack(img);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public void detectEdges() {
        if (image == null) {
            JOptionPane.showMessageDialog(new JFrame(), "Upload an image.",
                    "", JOptionPane.INFORMATION_MESSAGE);
        } else {
            try {
                BufferedImage img = ImageIO.read(file);
                BufferedImage biSrc = new BufferedImage(img.getWidth(this),
                        img.getHeight(this), BufferedImage.TYPE_INT_RGB);
                Graphics2D graphics2D;
                graphics2D = biSrc.createGraphics();
                graphics2D.drawImage(img, 0, 0, this);

                BufferedImage biDest = new BufferedImage(img.getWidth(this),
                        img.getHeight(this), BufferedImage.TYPE_INT_RGB);
                float data[] = {1.0f, 0.0f, -1.0f,
                        1.0f, 0.0f, -1.0f,
                        1.0f, 0.0f, -1.0f,};
                Kernel kernel = new Kernel(3, 3, data);
                ConvolveOp convolveOp = new ConvolveOp(kernel, ConvolveOp.EDGE_NO_OP, null);
                convolveOp.filter(biSrc, biDest);
                img = biDest;
                repaintAnImage(img);
                saveToUndoStack(img);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
