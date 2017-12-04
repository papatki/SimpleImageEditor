package com.pati.images.service;

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
//    todo redo and undo methods


}
