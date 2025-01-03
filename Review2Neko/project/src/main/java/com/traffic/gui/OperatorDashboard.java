package com.traffic.gui;

import javax.swing.*;
import java.awt.*;

public class OperatorDashboard extends JFrame {
    public OperatorDashboard() {
        setTitle("Operator Dashboard - Traffic Management System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        // Create main split pane
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        
        // Left panel - Traffic monitoring
        JPanel monitoringPanel = createMonitoringPanel();
        splitPane.setLeftComponent(monitoringPanel);
        
        // Right panel - Control panel
        JPanel controlPanel = createControlPanel();
        splitPane.setRightComponent(controlPanel);
        
        splitPane.setDividerLocation(400);
        add(splitPane);
    }

    private JPanel createMonitoringPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Traffic status table
        String[] columnNames = {"Intersection", "Status", "Wait Time"};
        Object[][] data = {
            {"Main St & 5th Ave", "Green", "30s"},
            {"Broadway & 7th", "Red", "45s"},
            {"Park Road & Lake St", "Yellow", "5s"}
        };
        JTable statusTable = new JTable(data, columnNames);
        
        // Alert panel
        JPanel alertPanel = new JPanel();
        alertPanel.setBorder(BorderFactory.createTitledBorder("Active Alerts"));
        alertPanel.add(new JLabel("No active alerts"));

        panel.add(new JScrollPane(statusTable), BorderLayout.CENTER);
        panel.add(alertPanel, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel createControlPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Signal control buttons
        JPanel buttonPanel = new JPanel(new GridLayout(3, 1, 5, 5));
        buttonPanel.add(new JButton("Override Signal"));
        buttonPanel.add(new JButton("Reset Timing"));
        buttonPanel.add(new JButton("Emergency Mode"));

        // Configuration panel
        JPanel configPanel = new JPanel(new GridLayout(4, 2, 5, 5));
        configPanel.setBorder(BorderFactory.createTitledBorder("Quick Settings"));
        
        configPanel.add(new JLabel("Green Time:"));
        configPanel.add(new JSpinner(new SpinnerNumberModel(30, 10, 120, 5)));
        
        configPanel.add(new JLabel("Yellow Time:"));
        configPanel.add(new JSpinner(new SpinnerNumberModel(5, 3, 10, 1)));
        
        configPanel.add(new JLabel("Red Time:"));
        configPanel.add(new JSpinner(new SpinnerNumberModel(45, 20, 180, 5)));

        panel.add(buttonPanel, BorderLayout.NORTH);
        panel.add(configPanel, BorderLayout.CENTER);

        return panel;
    }
}