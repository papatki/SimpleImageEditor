package com.pati.images;

import com.pati.images.controller.AppController;

import java.awt.*;
import java.awt.print.PrinterException;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            AppController appController = new AppController();
            try {
                appController.controlAnApp();
            } catch (IOException | PrinterException e) {
                e.printStackTrace();
            }
        });
    }
}
