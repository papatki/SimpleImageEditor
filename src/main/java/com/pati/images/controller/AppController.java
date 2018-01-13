package com.pati.images.controller;

import com.pati.images.model.AppModel;
import com.pati.images.service.EditingFeatures;
import com.pati.images.service.FileSupport;
import com.pati.images.service.FilterImage;
import com.pati.images.view.MainMenuBar;
import com.pati.images.view.UserInterface;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.io.IOException;

public class AppController {

    private UserInterface userInterface;
    private MainMenuBar mainMenuBar;
    private AppModel appModel;

    public void controlAnApp () throws IOException, PrinterException {
        appModel = new AppModel(null);

        ActionListener onOpenImage = (event) -> {
            try {
                FileSupport.openAnImage(appModel);
                userInterface.repaint(appModel.getImage());
            } catch (IOException e) {
                e.printStackTrace();
            }
        };

        ActionListener onSaveImage = (event) -> {
            try {
                FileSupport.saveAnImage(new ImageIcon(appModel.getImage()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        };

        ActionListener onPrintImage = (event) -> {
            try {
                EditingFeatures.printAnImage(new ImageIcon(appModel.getImage()));
            } catch (PrinterException e) {
                e.printStackTrace();
            }
        };

        ActionListener onExit = (event) -> EditingFeatures.exitProgramme();

        ActionListener onUndo = (event) -> EditingFeatures.undo();

        ActionListener onRedo = (event) -> EditingFeatures.redo();

        ActionListener onMakeGray = (event) -> {
            FilterImage.makeGray(appModel.getImage());
            userInterface.repaint(appModel.getImage());
        };

        ActionListener onMakeNegative = (event) -> {
            FilterImage.makeNegative(appModel.getImage());
            userInterface.repaint(appModel.getImage());
        };

        ActionListener onMakeSepia = (event) -> {
            FilterImage.makeSepia(appModel.getImage());
            userInterface.repaint(appModel.getImage());
        };

        ActionListener onMakeBlur = (event) -> {
            FilterImage.makeBlurry(appModel, userInterface);
            userInterface.repaint(appModel.getImage());
        };

        ActionListener onMakeSharpen = (event) -> {
            FilterImage.makeSharpen(appModel, userInterface);
            userInterface.repaint(appModel.getImage());
        };

        ActionListener onMakeEdges = (event) -> {
            FilterImage.detectEdges(appModel, userInterface);
            userInterface.repaint(appModel.getImage());
        };

        mainMenuBar = new MainMenuBar(onOpenImage, onSaveImage, onPrintImage, onExit, onUndo, onRedo,
                onMakeGray, onMakeNegative, onMakeSepia, onMakeBlur, onMakeSharpen, onMakeEdges);
        userInterface = new UserInterface(mainMenuBar);
        userInterface.setVisible(true);

    }
}
