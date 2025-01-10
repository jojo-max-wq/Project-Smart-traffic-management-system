package com.traffic.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.table.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.jfree.chart.*;
import org.jfree.data.xy.*;
import org.jfree.data.time.*;

public class TrafficManagementSystem extends JFrame {
    // Constants for better maintainability
    private static final int WINDOW_WIDTH = 1024;
    private static final int WINDOW_HEIGHT = 768;
    private static final String APP_TITLE = "Traffic Management System";
    
    // Core components
    private final JPanel cards;
    private final CardLayout cardLayout;
    private final TrafficController controller;
    private String currentUser;
    private String userRole;
    
    // UI Components that need class-level access
    private JLabel statusLabel;
    private Timer updateTimer;
    private JTable sensorTable;
    private DefaultTableModel sensorTableModel;
    private JFreeChart trafficChart;
    private ChartPanel chartPanel;

    public TrafficManagementSystem() {
        super(APP_TITLE);
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Initialize controller for backend communication
        controller = new TrafficController();
        
        // Initialize main components
        cardLayout = new CardLayout();
        cards = new JPanel(cardLayout);
        statusLabel = new JLabel("Ready");
        
        // Set up the UI
        initializeUI();
        setupStatusBar();
        setupUpdateTimer();
        
        // Center on screen
        setLocationRelativeTo(null);
        
        // Add window listener for cleanup
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                cleanup();
            }
        });
    }

    private void initializeUI() {
        try {
            // Add all main screens
            cards.add(createLoginPanel(), "login");
            cards.add(createAdminPanel(), "admin");
            cards.add(createOperatorPanel(), "operator");
            cards.add(createPublicPanel(), "public");
            
            // Set up main container
            Container contentPane = getContentPane();
            contentPane.setLayout(new BorderLayout());
            contentPane.add(cards, BorderLayout.CENTER);
            
            // Error handling for UI initialization
        } catch (Exception e) {
            handleError("Error initializing UI", e);
        }
    }

    private void setupUpdateTimer() {
        // Create timer for periodic updates (every 5 seconds)
        updateTimer = new Timer(5000, e -> {
            try {
                if (userRole != null) {
                    updateDashboard();
                }
            } catch (Exception ex) {
                handleError("Error updating dashboard", ex);
            }
        });
        updateTimer.start();
    }

    private void setupStatusBar() {
        JPanel statusBar = new JPanel(new BorderLayout());
        statusBar.setBorder(BorderFactory.createEtchedBorder());
        statusBar.add(statusLabel, BorderLayout.WEST);
        add(statusBar, BorderLayout.SOUTH);
    }

    private JPanel createLoginPanel() {
        JPanel loginPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        
        // Create styled components
        JTextField usernameField = new JTextField(20);
        JPasswordField passwordField = new JPasswordField(20);
        JButton loginButton = new JButton("Login");
        
        // Add input validation
        usernameField.setInputVerifier(new InputVerifier() {
            @Override
            public boolean verify(JComponent input) {
                String text = ((JTextField) input).getText();
                return text != null && text.length() >= 3;
            }
        });

        // Enhanced login handling
        loginButton.addActionListener(e -> {
            try {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                
                if (controller.validateLogin(username, password)) {
                    handleSuccessfulLogin(username);
                } else {
                    throw new SecurityException("Invalid credentials");
                }
            } catch (Exception ex) {
                handleError("Login failed", ex);
            }
        });

        // Layout components with proper spacing
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridx = 0; gbc.gridy = 0;
        loginPanel.add(new JLabel("Username:"), gbc);
        gbc.gridx = 1;
        loginPanel.add(usernameField, gbc);
        gbc.gridx = 0; gbc.gridy = 1;
        loginPanel.add(new JLabel("Password:"), gbc);
        gbc.gridx = 1;
        loginPanel.add(passwordField, gbc);
        gbc.gridx = 1; gbc.gridy = 2;
        loginPanel.add(loginButton, gbc);
        
        return loginPanel;
    }

    // Error handling method
    private void handleError(String message, Exception e) {
        SwingUtilities.invokeLater(() -> {
            statusLabel.setText("Error: " + message);
            JOptionPane.showMessageDialog(this,
                "Error: " + message + "\n" + e.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE);
            // Log error
            System.err.println(message);
            e.printStackTrace();
        });
    }

    // Cleanup method
    private void cleanup() {
        try {
            if (updateTimer != null) {
                updateTimer.stop();
            }
            controller.disconnect();
        } catch (Exception e) {
            handleError("Error during cleanup", e);
        }
    }

    // Dashboard update method
    private void updateDashboard() {
        try {
            switch (userRole) {
                case "admin":
                    updateAdminDashboard();
                    break;
                case "operator":
                    updateOperatorDashboard();
                    break;
                case "public":
                    updatePublicDashboard();
                    break;
            }
        } catch (Exception e) {
            handleError("Error updating dashboard", e);
        }
    }

    // Main method with error handling
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                new TrafficManagementSystem().setVisible(true);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null,
                    "Error starting application: " + e.getMessage(),
                    "Startup Error",
                    JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
        });
    }

    // Additional methods would follow...
}
