package com.traffic.gui;

import javax.swing.*;
import java.awt.*;

public class PublicDashboard extends JFrame {
    public PublicDashboard() {
        setTitle("Public Dashboard - Traffic Management System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 500);
        setLocationRelativeTo(null);

        // Create tabbed pane
        JTabbedPane tabbedPane = new JTabbedPane();
        
        // Traffic Updates Panel
        JPanel updatesPanel = createUpdatesPanel();
        tabbedPane.addTab("Traffic Updates", updatesPanel);
        
        // Report Issue Panel
        JPanel reportPanel = createReportPanel();
        tabbedPane.addTab("Report Issue", reportPanel);

        add(tabbedPane);
    }

    private JPanel createUpdatesPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Traffic updates list
        DefaultListModel<String> listModel = new DefaultListModel<>();
        listModel.addElement("Main Street: Heavy traffic due to construction");
        listModel.addElement("5th Avenue: Normal flow");
        listModel.addElement("Broadway: Slow moving due to event");
        
        JList<String> updatesList = new JList<>(listModel);
        updatesList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        // Refresh button
        JButton refreshButton = new JButton("Refresh Updates");
        
        panel.add(new JScrollPane(updatesList), BorderLayout.CENTER);
        panel.add(refreshButton, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel createReportPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Form panel
        JPanel formPanel = new JPanel(new GridLayout(4, 2, 5, 5));
        
        formPanel.add(new JLabel("Location:"));
        formPanel.add(new JTextField());
        
        formPanel.add(new JLabel("Issue Type:"));
        String[] issueTypes = {"Traffic Signal Malfunction", "Heavy Traffic", "Accident", "Road Work", "Other"};
        formPanel.add(new JComboBox<>(issueTypes));
        
        formPanel.add(new JLabel("Description:"));
        JTextArea descArea = new JTextArea(3, 20);
        JScrollPane scrollPane = new JScrollPane(descArea);
        formPanel.add(scrollPane);
        
        // Submit button
        JButton submitButton = new JButton("Submit Report");
        
        panel.add(formPanel, BorderLayout.CENTER);
        panel.add(submitButton, BorderLayout.SOUTH);

        return panel;
    }
}