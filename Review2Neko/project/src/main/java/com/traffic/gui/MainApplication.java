package com.traffic.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainApplication {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LoginFrame loginFrame = new LoginFrame();
            loginFrame.setVisible(true);
        });
    }
}

class LoginFrame extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JComboBox<String> userTypeCombo;

    public LoginFrame() {
        setTitle("Traffic Management System - Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);
        
        // Main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Title
        JLabel titleLabel = new JLabel("Traffic Management System");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Login components
        JPanel loginPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        
        usernameField = new JTextField(15);
        passwordField = new JPasswordField(15);
        String[] userTypes = {"Admin", "Operator", "Public User"};
        userTypeCombo = new JComboBox<>(userTypes);

        loginPanel.add(new JLabel("Username:"));
        loginPanel.add(usernameField);
        loginPanel.add(new JLabel("Password:"));
        loginPanel.add(passwordField);
        loginPanel.add(new JLabel("User Type:"));
        loginPanel.add(userTypeCombo);

        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(e -> handleLogin());

        // Add components
        mainPanel.add(titleLabel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        mainPanel.add(loginPanel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        mainPanel.add(loginButton);

        add(mainPanel);
    }

    private void handleLogin() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());
        String userType = (String) userTypeCombo.getSelectedItem();

        // Simple validation
        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "Please enter both username and password", 
                "Login Error", 
                JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Open appropriate dashboard based on user type
        SwingUtilities.invokeLater(() -> {
            this.dispose();
            switch (userType) {
                case "Admin":
                    new AdminDashboard().setVisible(true);
                    break;
                case "Operator":
                    new OperatorDashboard().setVisible(true);
                    break;
                case "Public User":
                    new PublicDashboard().setVisible(true);
                    break;
            }
        });
    }
}