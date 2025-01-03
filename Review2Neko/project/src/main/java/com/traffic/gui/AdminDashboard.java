package com.traffic.gui;

import javax.swing.*;
import java.awt.*;

public class AdminDashboard extends JFrame {
    public AdminDashboard() {
        setTitle("Admin Dashboard - Traffic Management System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        // Create main panel with tabs
        JTabbedPane tabbedPane = new JTabbedPane();
        
        // Sensor Configuration Panel
        JPanel sensorPanel = createSensorPanel();
        tabbedPane.addTab("Sensor Configuration", sensorPanel);
        
        // Traffic Rules Panel
        JPanel rulesPanel = createTrafficRulesPanel();
        tabbedPane.addTab("Traffic Rules", rulesPanel);
        
        // System Control Panel
        JPanel controlPanel = createSystemControlPanel();
        tabbedPane.addTab("System Control", controlPanel);
        
        // Analytics Panel
        JPanel analyticsPanel = createAnalyticsPanel();
        tabbedPane.addTab("Analytics", analyticsPanel);

        add(tabbedPane);
    }

    private JPanel createSensorPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Sensor list
        JList<String> sensorList = new JList<>(new String[]{
            "Sensor 1 - Main Street",
            "Sensor 2 - Broadway",
            "Sensor 3 - 5th Avenue"
        });
        
        // Control buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(new JButton("Add Sensor"));
        buttonPanel.add(new JButton("Edit Sensor"));
        buttonPanel.add(new JButton("Remove Sensor"));

        panel.add(new JScrollPane(sensorList), BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel createTrafficRulesPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Rules table
        String[] columnNames = {"Rule ID", "Description", "Status"};
        Object[][] data = {
            {"R001", "Peak Hour Signal Timing", "Active"},
            {"R002", "Emergency Vehicle Priority", "Active"},
            {"R003", "Night Mode Timing", "Inactive"}
        };
        JTable rulesTable = new JTable(data, columnNames);
        
        // Control buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(new JButton("Add Rule"));
        buttonPanel.add(new JButton("Edit Rule"));
        buttonPanel.add(new JButton("Delete Rule"));

        panel.add(new JScrollPane(rulesTable), BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel createSystemControlPanel() {
        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        panel.add(new JLabel("System Status:"));
        panel.add(new JLabel("Running"));
        
        panel.add(new JLabel("Active Sensors:"));
        panel.add(new JLabel("15"));
        
        panel.add(new JLabel("Active Rules:"));
        panel.add(new JLabel("8"));
        
        JButton restartButton = new JButton("Restart System");
        JButton backupButton = new JButton("Backup Data");
        
        panel.add(restartButton);
        panel.add(backupButton);

        return panel;
    }

    private JPanel createAnalyticsPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Placeholder for traffic data chart
        JPanel chartPanel = new JPanel();
        chartPanel.setBackground(Color.WHITE);
        chartPanel.setBorder(BorderFactory.createTitledBorder("Traffic Flow Chart"));
        
        // Control panel
        JPanel controlPanel = new JPanel();
        controlPanel.add(new JButton("Generate Report"));
        controlPanel.add(new JButton("Export Data"));
        controlPanel.add(new JButton("Print"));

        panel.add(chartPanel, BorderLayout.CENTER);
        panel.add(controlPanel, BorderLayout.SOUTH);

        return panel;
    }
}