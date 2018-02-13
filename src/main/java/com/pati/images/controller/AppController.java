package com.pati.images.controller;

import com.pati.images.model.AppModel;
import com.pati.images.service.EditingFeatures;
import com.pati.images.service.FileSupport;
import com.pati.images.service.FilterImage;
import com.pati.images.service.SizedStack;
import com.pati.images.view.MainMenuBar;
import com.pati.images.view.UserInterface;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.print.PrinterException;
import java.io.IOException;

public class AppController {

    private UserInterface userInterface;
    private MainMenuBar mainMenuBar;
    private AppModel appModel;
    private final SizedStack<BufferedImage> undoStack = new SizedStack<>(30);
    private final SizedStack<BufferedImage> redoStack = new SizedStack<>(30);

    public void controlAnApp() throws IOException, PrinterException {
        appModel = new AppModel(null);

        ActionListener onOpenImage = (event) -> {
            try {
                FileSupport.openAnImage(appModel);
                userInterface.repaint(appModel.getImage());
                EditingFeatures.saveToUndoStack(undoStack, appModel.getImage());
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

        ActionListener onUndo = (event) -> {
            EditingFeatures.saveToRedoStack(redoStack, appModel.getImage());
            EditingFeatures.undo(userInterface, undoStack);

        };

        ActionListener onRedo = (event) -> {
            EditingFeatures.saveToUndoStack(undoStack, appModel.getImage());
            EditingFeatures.redo(userInterface, redoStack);

        };

        ActionListener onMakeGray = (event) -> {
            EditingFeatures.saveToUndoStack(undoStack, appModel.getImage());
            FilterImage.makeGray(appModel.getImage());
            userInterface.repaint(appModel.getImage());
            EditingFeatures.saveToUndoStack(undoStack, appModel.getImage());

        };

        ActionListener onMakeNegative = (event) -> {
            EditingFeatures.saveToUndoStack(undoStack, appModel.getImage());
            FilterImage.makeNegative(appModel.getImage());
            userInterface.repaint(appModel.getImage());
            EditingFeatures.saveToUndoStack(undoStack, appModel.getImage());

        };

        ActionListener onMakeSepia = (event) -> {
            EditingFeatures.saveToUndoStack(undoStack, appModel.getImage());
            FilterImage.makeSepia(appModel.getImage());
            EditingFeatures.saveToUndoStack(undoStack, appModel.getImage());
            userInterface.repaint(appModel.getImage());
        };

        ActionListener onMakeBlur = (event) -> {
            EditingFeatures.saveToUndoStack(undoStack, appModel.getImage());
            FilterImage.makeBlurry(appModel, userInterface);
            userInterface.repaint(appModel.getImage());
            EditingFeatures.saveToUndoStack(undoStack, appModel.getImage());
        };

        ActionListener onMakeSharpen = (event) -> {
            EditingFeatures.saveToUndoStack(undoStack, appModel.getImage());
            FilterImage.makeSharpen(appModel, userInterface);
            userInterface.repaint(appModel.getImage());
            EditingFeatures.saveToUndoStack(undoStack, appModel.getImage());

        };

        ActionListener onMakeEdges = (event) -> {
            EditingFeatures.saveToUndoStack(undoStack, appModel.getImage());
            FilterImage.detectEdges(appModel, userInterface);
            userInterface.repaint(appModel.getImage());
            EditingFeatures.saveToUndoStack(undoStack, appModel.getImage());

        };

        mainMenuBar = new MainMenuBar(onOpenImage, onSaveImage, onPrintImage, onExit, onUndo, onRedo,
                onMakeGray, onMakeNegative, onMakeSepia, onMakeBlur, onMakeSharpen, onMakeEdges);
        userInterface = new UserInterface(mainMenuBar);
        userInterface.setVisible(true);


    }
}
