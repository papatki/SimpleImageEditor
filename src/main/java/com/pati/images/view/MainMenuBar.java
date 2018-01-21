package com.pati.images.view;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;


public class MainMenuBar {

    private JMenuBar jMenuBar;
    private JMenu jFile, jEdit, jFilters;
    private JMenuItem open, save, print, exit, undo, redo, grayScale, negative, sepia, sharpen, blur, edgeDetect;

    private ActionListener onOpeningImage;
    private ActionListener onSavingFile;
    private ActionListener onPrintingImage;
    private ActionListener onExitingProgramme;
    private ActionListener onUndo;
    private ActionListener onRedo;
    private ActionListener onMakeGray;
    private ActionListener onMakeNegative;
    private ActionListener onMakeSepia;
    private ActionListener onMakeBlur;
    private ActionListener onMakeSharpen;
    private ActionListener onDetectEdges;

    public MainMenuBar(ActionListener onOpeningImage,
                       ActionListener onSavingFile,
                       ActionListener onPrintingImage,
                       ActionListener onExitingProgramme,
                       ActionListener onUndo,
                       ActionListener onRedo,
                       ActionListener onMakeGray,
                       ActionListener onMakeNegative,
                       ActionListener onMakeSepia,
                       ActionListener onMakeBlur,
                       ActionListener onMakeSharpen,
                       ActionListener onDetectEdges) {
        this.onOpeningImage = onOpeningImage;
        this.onSavingFile = onSavingFile;
        this.onPrintingImage = onPrintingImage;
        this.onExitingProgramme = onExitingProgramme;
        this.onUndo = onUndo;
        this.onRedo = onRedo;
        this.onMakeGray = onMakeGray;
        this.onMakeNegative = onMakeNegative;
        this.onMakeSepia = onMakeSepia;
        this.onMakeBlur = onMakeBlur;
        this.onMakeSharpen = onMakeSharpen;
        this.onDetectEdges = onDetectEdges;
    }

    public JMenuBar getMenuBar() {
        return jMenuBar;
    }

    public void createMenuBar() {
        jMenuBar = new JMenuBar();

        jFile = new JMenu("File");
        jMenuBar.add(jFile);

        open = new JMenuItem("Open");
        open.setMnemonic(KeyEvent.VK_F);
        open.setToolTipText("Open an image");
        open.addActionListener(onOpeningImage);
        jFile.add(open);
        jFile.addSeparator();

        save = new JMenuItem("Save");
        save.setMnemonic(KeyEvent.VK_F);
        save.setToolTipText("Save the image");
        save.addActionListener(onSavingFile);
        jFile.add(save);
        jFile.addSeparator();

        print = new JMenuItem("Print");
        print.setMnemonic(KeyEvent.VK_F);
        print.setToolTipText("Print the image");
        print.addActionListener(onPrintingImage);
        jFile.add(print);
        jFile.addSeparator();

        exit = new JMenuItem("Exit");
        exit.setMnemonic(KeyEvent.VK_F);
        exit.setToolTipText("Exit application");
        exit.addActionListener(onExitingProgramme);
        jFile.add(exit);


// edit
        jEdit = new JMenu("Edit");
        jMenuBar.add(jEdit);

        undo = new JMenuItem("Undo");
        undo.setMnemonic(KeyEvent.VK_F);
        undo.addActionListener(onUndo);
        jEdit.add(undo);
        jEdit.addSeparator();

        redo = new JMenuItem("Redo");
        redo.setMnemonic(KeyEvent.VK_F);
        redo.addActionListener(onRedo);
        jEdit.add(redo);
        jEdit.add(redo);

// filters
        jFilters = new JMenu("Add filter");
        jMenuBar.add(jFilters);

        grayScale = new JMenuItem("Grayscale");
        grayScale.setMnemonic(KeyEvent.VK_F);
        grayScale.setToolTipText("Make it gray");
        grayScale.addActionListener(onMakeGray);
        jFilters.add(grayScale);
        jFilters.addSeparator();

        negative = new JMenuItem("Negative");
        negative.setMnemonic(KeyEvent.VK_F);
        negative.setToolTipText("Make it negative");
        negative.addActionListener(onMakeNegative);
        jFilters.add(negative);
        jFilters.addSeparator();

        sepia = new JMenuItem("Sepia");
        sepia.setMnemonic(KeyEvent.VK_F);
        sepia.setToolTipText("Make it sepia");
        sepia.addActionListener(onMakeSepia);
        jFilters.add(sepia);
        jFilters.addSeparator();

        sharpen = new JMenuItem("Sharpen");
        sharpen.setMnemonic(KeyEvent.VK_F);
        sharpen.setToolTipText("Make it sharpen");
        sharpen.addActionListener(onMakeSharpen);
        jFilters.add(sharpen);
        jFilters.addSeparator();

        blur = new JMenuItem("Blur");
        blur.setMnemonic(KeyEvent.VK_F);
        blur.setToolTipText("Make it blurry");
        blur.addActionListener(onMakeBlur);
        jFilters.add(blur);
        jFilters.addSeparator();

        edgeDetect = new JMenuItem("Edge Detection");
        edgeDetect.setMnemonic(KeyEvent.VK_F);
        edgeDetect.setToolTipText("....");
        edgeDetect.addActionListener(onDetectEdges);
        jFilters.add(edgeDetect);
    }

}
