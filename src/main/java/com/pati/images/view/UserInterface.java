package com.pati.images.view;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class UserInterface extends JFrame {
    private JPanel usingImagePanel;
    private BufferedImage image;
    private JLabel jLabel;

    public UserInterface(MainMenuBar mainMenuBar) {
        this.usingImagePanel = new JPanel();
        this.jLabel = new JLabel();
        mainMenuBar.createMenuBar();
        setJMenuBar(mainMenuBar.getMenuBar());
        mainMenuBar.createMenuBar();
        setContentPane(usingImagePanel);

        setTitle("Pimp My Picture");
        setSize(500, 500);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(true); // windows resizing - disabled
        setLocationRelativeTo(null); //center frame
    }

    public void setImage(BufferedImage image) {
        this.image = image;
        repaintAnImage();
    }

    private void repaintAnImage() {
        jLabel.setIcon(new ImageIcon(image));
        usingImagePanel.add(jLabel, BorderLayout.CENTER);
        usingImagePanel.revalidate();
        usingImagePanel.repaint();
    }

}
