package com.traffic.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.table.DefaultTableModel;
import java.util.Vector;

public class TrafficManagementSystem extends JFrame {
    private JPanel cards;
    private CardLayout cardLayout;
    private String currentUser;
    private String userRole;

    public TrafficManagementSystem() {
        setTitle("Traffic Management System");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        cardLayout = new CardLayout();
        cards = new JPanel(cardLayout);
        
        // Add screens
        cards.add(createLoginPanel(), "login");
        cards.add(createAdminPanel(), "admin");
        cards.add(createOperatorPanel(), "operator");
        cards.add(createPublicPanel(), "public");
        
        add(cards);
        setLocationRelativeTo(null);
    }

    private JPanel createLoginPanel() {
        JPanel loginPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        
        JTextField usernameField = new JTextField(20);
        JPasswordField passwordField = new JPasswordField(20);
        JButton loginButton = new JButton("Login");
        
        loginButton.addActionListener(e -> {
            String username = usernameField.getText();
            if (username.startsWith("admin")) {
                userRole = "admin";
                cardLayout.show(cards, "admin");
            } else if (username.startsWith("operator")) {
                userRole = "operator";
                cardLayout.show(cards, "operator");
            } else {
                userRole = "public";
                cardLayout.show(cards, "public");
            }
            currentUser = username;
        });
        
        // Layout components
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

    private JPanel createAdminPanel() {
        JPanel adminPanel = new JPanel(new BorderLayout());
        
        // Create menu bar
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Sensor Config", createSensorConfigPanel());
        tabbedPane.addTab("Traffic Rules", createTrafficRulesPanel());
        tabbedPane.addTab("System Analytics", createSystemAnalyticsPanel());
        tabbedPane.addTab("User Management", createUserManagementPanel());
        
        JButton logoutButton = new JButton("Logout");
        logoutButton.addActionListener(e -> cardLayout.show(cards, "login"));
        
        adminPanel.add(createHeader("Admin Dashboard", logoutButton), BorderLayout.NORTH);
        adminPanel.add(tabbedPane, BorderLayout.CENTER);
        
        return adminPanel;
    }

    private JPanel createOperatorPanel() {
        JPanel operatorPanel = new JPanel(new BorderLayout());
        
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Live Monitoring", createLiveMonitoringPanel());
        tabbedPane.addTab("Signal Control", createSignalControlPanel());
        tabbedPane.addTab("Reports", createReportsPanel());
        
        JButton logoutButton = new JButton("Logout");
        logoutButton.addActionListener(e -> cardLayout.show(cards, "login"));
        
        operatorPanel.add(createHeader("Operator Dashboard", logoutButton), BorderLayout.NORTH);
        operatorPanel.add(tabbedPane, BorderLayout.CENTER);
        
        return operatorPanel;
    }

    private JPanel createPublicPanel() {
        JPanel publicPanel = new JPanel(new BorderLayout());
        
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Traffic Updates", createTrafficUpdatesPanel());
        tabbedPane.addTab("Report Issue", createReportIssuePanel());
        
        JButton logoutButton = new JButton("Logout");
        logoutButton.addActionListener(e -> cardLayout.show(cards, "login"));
        
        publicPanel.add(createHeader("Public Dashboard", logoutButton), BorderLayout.NORTH);
        publicPanel.add(tabbedPane, BorderLayout.CENTER);
        
        return publicPanel;
    }

    private JPanel createHeader(String title, JButton logoutButton) {
        JPanel header = new JPanel(new BorderLayout());
        header.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        header.add(new JLabel(title), BorderLayout.WEST);
        header.add(logoutButton, BorderLayout.EAST);
        return header;
    }

    private JPanel createSensorConfigPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        String[] columns = {"Sensor ID", "Location", "Status", "Last Updated"};
        Object[][] data = {
            {"S001", "Main St & Broadway", "Active", "2024-01-07 10:00"},
            {"S002", "5th Ave & Park Rd", "Inactive", "2024-01-07 09:45"}
        };
        JTable table = new JTable(data, columns);
        panel.add(new JScrollPane(table), BorderLayout.CENTER);
        return panel;
    }

    private JPanel createTrafficRulesPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        // Add traffic rules components
        return panel;
    }

    private JPanel createSystemAnalyticsPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        // Add analytics components
        return panel;
    }

    private JPanel createUserManagementPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        String[] columns = {"Username", "Role", "Status"};
        Object[][] data = {
            {"admin1", "Admin", "Active"},
            {"operator1", "Operator", "Active"},
            {"public1", "Public", "Active"}
        };
        JTable table = new JTable(data, columns);
        panel.add(new JScrollPane(table), BorderLayout.CENTER);
        return panel;
    }

    private JPanel createLiveMonitoringPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        // Add monitoring components
        return panel;
    }

    private JPanel createSignalControlPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        
        JComboBox<String> intersectionCombo = new JComboBox<>(new String[]{
            "Main St & Broadway", "5th Ave & Park Rd", "West Blvd & East Ave"
        });
        
        JComboBox<String> modeCombo = new JComboBox<>(new String[]{
            "Automatic", "Manual Control", "Emergency Mode"
        });
        
        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(new JLabel("Intersection:"), gbc);
        gbc.gridx = 1;
        panel.add(intersectionCombo, gbc);
        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(new JLabel("Mode:"), gbc);
        gbc.gridx = 1;
        panel.add(modeCombo, gbc);
        
        return panel;
    }

    private JPanel createReportsPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        String[] columns = {"Time", "Location", "Type", "Status"};
        Object[][] data = {
            {"09:00", "Main St & Broadway", "Heavy Traffic", "Resolved"},
            {"10:30", "5th Ave & Park Rd", "Signal Malfunction", "In Progress"}
        };
        JTable table = new JTable(data, columns);
        panel.add(new JScrollPane(table), BorderLayout.CENTER);
        return panel;
    }

    private JPanel createTrafficUpdatesPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        DefaultListModel<String> listModel = new DefaultListModel<>();
        listModel.addElement("Heavy traffic on Main Street - Delay: 15 mins");
        listModel.addElement("Construction work on 5th Avenue - Use alternate route");
        listModel.addElement("Accident cleared on Broadway - Traffic normal");
        
        JList<String> updatesList = new JList<>(listModel);
        panel.add(new JScrollPane(updatesList), BorderLayout.CENTER);
        return panel;
    }

    private JPanel createReportIssuePanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        
        JTextField locationField = new JTextField(20);
        JComboBox<String> issueTypeCombo = new JComboBox<>(new String[]{
            "Accident", "Construction", "Heavy Traffic", "Other"
        });
        JTextArea descriptionArea = new JTextArea(4, 20);
        JButton submitButton = new JButton("Submit Report");
        
        submitButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Issue reported successfully!");
            locationField.setText("");
            descriptionArea.setText("");
        });
        
        // Layout components
        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(new JLabel("Location:"), gbc);
        gbc.gridx = 1;
        panel.add(locationField, gbc);
        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(new JLabel("Issue Type:"), gbc);
        gbc.gridx = 1;
        panel.add(issueTypeCombo, gbc);
        gbc.gridx = 0; gbc.gridy = 2;
        panel.add(new JLabel("Description:"), gbc);
        gbc.gridx = 1;
        panel.add(new JScrollPane(descriptionArea), gbc);
        gbc.gridx = 1; gbc.gridy = 3;
        panel.add(submitButton, gbc);
        
        return panel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }
            new TrafficManagementSystem().setVisible(true);
        });
    }
}