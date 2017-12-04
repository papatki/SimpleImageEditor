package com.pati.images.controller;

import com.pati.images.model.AppModel;
import com.pati.images.service.EditingFeatures;
import com.pati.images.service.FileSupport;
import com.pati.images.service.FilterImage;
import com.pati.images.view.MainMenuBar;
import com.pati.images.view.UserInterface;

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.awt.print.PrinterException;
import java.io.IOException;

public class AppController extends JFrame {

    private UserInterface userInterface;
    private MainMenuBar mainMenuBar;
    private AppModel appModel;
    private FileSupport fileSupport;
    private EditingFeatures editingFeatures;
    private FilterImage filterImage;
    private BufferedImage image;
    private ImageIcon icon;

    public void controlAnApp () throws IOException, PrinterException {
        appModel = new AppModel(image);
        userInterface = new UserInterface(mainMenuBar);
        }
}
