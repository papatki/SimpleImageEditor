package com.pati.images.service;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by patrioshka on 12/4/17.
 */
public class FileSupport {

    public static void openAnImage(BufferedImage image) throws IOException {
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Images Files", "jpg", "png", "jpeg");
        JFileChooser fc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        fc.setFileFilter(filter);
        File file;

        int result = fc.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            file = fc.getSelectedFile();
            if (image == null) {
                ImageIO.read(file);

            } else {
                ImageIO.read(file);
            }
        }
    }

    public static void saveAnImage(ImageIcon icon) throws IOException {
        FileDialog fileDialog = new FileDialog(new JFrame(), "Save", FileDialog.SAVE);
        fileDialog.setVisible(true);
        String path = fileDialog.getDirectory() + fileDialog.getFile();
        File output = new File(path);

        BufferedImage image = (BufferedImage) icon.getImage();
        ImageIO.write(image, "jpg", output);
    }
}
