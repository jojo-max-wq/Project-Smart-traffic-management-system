package com.traffic.utils;

import javax.swing.*;

public class ErrorHandler {
    public static void handleError(String message, Exception e) {
        SwingUtilities.invokeLater(() -> {
            JOptionPane.showMessageDialog(null,
                "Error: " + message + "\n" + e.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE);
        });
    }
}