package com.pati.images;

import com.pati.images.controller.AppController;

import java.awt.*;
import java.awt.print.PrinterException;
import java.io.IOException;

/**
 * Created by patrioshka on 10/25/17.
 */
public class Main {
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            AppController appController = new AppController();
            try {
                appController.controlAnApp();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (PrinterException e) {
                e.printStackTrace();
            }
            appController.setVisible(true);
        });
    }
}
