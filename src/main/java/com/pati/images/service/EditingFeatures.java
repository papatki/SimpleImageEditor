package com.pati.images.service;

import com.pati.images.view.UserInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;

/**
 * Created by patrioshka on 12/4/17.
 */
public class EditingFeatures {


    public static void printAnImage(ImageIcon icon ) throws PrinterException {
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
                printerJob.print();
        }
    }

    public static void exitProgramme() {
            System.exit(0);
    }

    public static void undo(UserInterface userInterface, SizedStack<BufferedImage> undoStack) {
        if (undoStack.size() > 0) {
            userInterface.repaint(undoStack.pop());
        }
    }
    public static void redo(UserInterface userInterface, SizedStack<BufferedImage> redoStack) {
        if (redoStack.size() > 0) {
            userInterface.repaint(redoStack.pop());
        }
    }


    public static void saveToUndoStack(SizedStack<BufferedImage> stack, BufferedImage image) {
        stack.push(image);
    }

    public static void saveToRedoStack(SizedStack <BufferedImage> stack, BufferedImage image) {
        stack.push(image);

    }

}
