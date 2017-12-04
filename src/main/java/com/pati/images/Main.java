package com.pati.images;

import com.pati.images.controller.AppController;

import java.awt.*;

/**
 * Created by patrioshka on 10/25/17.
 */
public class Main {
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            AppController appController = new AppController();
            appController.setVisible(true);
        });
    }
}
