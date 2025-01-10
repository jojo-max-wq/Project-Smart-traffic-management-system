package com.traffic.util;

import javax.swing.*;
import java.awt.*;

public class GUIHelper {
    // Show error message
    public static void showError(Component parent, String message) {
        JOptionPane.showMessageDialog(
            parent,
            message,
            "Error",
            JOptionPane.ERROR_MESSAGE
        );
    }

    // Show success message
    public static void showSuccess(Component parent, String message) {
        JOptionPane.showMessageDialog(
            parent,
            message,
            "Success",
            JOptionPane.INFORMATION_MESSAGE
        );
    }

    // Show validation warning
    public static void showValidationWarning(Component parent, String message) {
        JOptionPane.showMessageDialog(
            parent,
            message,
            "Validation Warning",
            JOptionPane.WARNING_MESSAGE
        );
    }

    // Show confirmation dialog
    public static boolean showConfirmation(Component parent, String message) {
        return JOptionPane.showConfirmDialog(
            parent,
            message,
            "Confirm",
            JOptionPane.YES_NO_OPTION
        ) == JOptionPane.YES_OPTION;
    }

    // Create standardized button
    public static JButton createButton(String text, ActionListener action) {
        JButton button = new JButton(text);
        button.addActionListener(action);
        return button;
    }

    // Create standardized labeled field
    public static JPanel createLabeledField(String labelText, JComponent field) {
        JPanel panel = new JPanel(new BorderLayout(5, 0));
        panel.add(new JLabel(labelText), BorderLayout.WEST);
        panel.add(field, BorderLayout.CENTER);
        return panel;
    }

    // Add keyboard shortcut
    public static void addKeyboardShortcut(JComponent component, KeyStroke keyStroke, String actionKey, Action action) {
        component.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(keyStroke, actionKey);
        component.getActionMap().put(actionKey, action);
    }
}
