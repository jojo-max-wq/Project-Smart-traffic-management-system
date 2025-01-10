package com.traffic.gui;

import javax.swing.*;
import java.awt.*;
import com.traffic.validation.DataValidator;
import com.traffic.service.UserService;
import com.traffic.util.GUIHelper;

public class LoginDialog extends JDialog {
    private final UserService userService;
    private final DataValidator validator;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private boolean authenticated = false;

    public LoginDialog(Frame parent) {
        super(parent, "Traffic Management System Login", true);
        this.userService = new UserService();
        this.validator = new DataValidator();
        initializeUI();
        pack();
        setLocationRelativeTo(parent);
    }

    private void initializeUI() {
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Create a custom color scheme
        Color primaryColor = new Color(41, 128, 185);
        Color backgroundColor = new Color(236, 240, 241);
        
        // Set panel background
        mainPanel.setBackground(backgroundColor);
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        // Add logo or title
        JLabel titleLabel = new JLabel("Traffic Management System");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setForeground(primaryColor);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        mainPanel.add(titleLabel, gbc);

        // Username field
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        JLabel usernameLabel = new JLabel("Username:");
        mainPanel.add(usernameLabel, gbc);

        gbc.gridx = 1;
        usernameField = new JTextField(15);
        mainPanel.add(usernameField, gbc);

        // Password field
        gbc.gridx = 0;
        gbc.gridy = 2;
        JLabel passwordLabel = new JLabel("Password:");
        mainPanel.add(passwordLabel, gbc);

        gbc.gridx = 1;
        passwordField = new JPasswordField(15);
        mainPanel.add(passwordField, gbc);

        // Login button
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        JButton loginButton = new JButton("Login");
        loginButton.setBackground(primaryColor);
        loginButton.setForeground(Color.WHITE);
        loginButton.setFocusPainted(false);
        loginButton.addActionListener(e -> attemptLogin());
        mainPanel.add(loginButton, gbc);

        add(mainPanel);
        
        // Add key listener for Enter key
        getRootPane().setDefaultButton(loginButton);
    }

    private void attemptLogin() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        if (!validator.isValidUsername(username) || !validator.isValidPassword(password)) {
            GUIHelper.showError(this, "Invalid username or password format");
            return;
        }

        try {
            if (userService.authenticate(username, password) != null) {
                authenticated = true;
                dispose();
            } else {
                GUIHelper.showError(this, "Invalid credentials");
                passwordField.setText("");
            }
        } catch (Exception e) {
            GUIHelper.showError(this, "Login failed: " + e.getMessage());
        }
    }

    public boolean isAuthenticated() {
        return authenticated;
    }
}
