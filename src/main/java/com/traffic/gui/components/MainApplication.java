package com.traffic.gui;

import javax.swing.*;
import java.awt.*;
import com.traffic.gui.components.*;
import com.traffic.gui.components.admin.*;
import com.traffic.gui.components.operator.*;
import com.traffic.gui.components.public.*;

public class MainApplication {
    private JFrame mainFrame;
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private String currentUser = null;
    private String userRole = null;

    public MainApplication() {
        initializeUI();
    }

    private void initializeUI() {
        mainFrame = new JFrame("Traffic Management System");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(1200, 800);
        mainFrame.setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        // Add panels
        mainPanel.add(new LoginPanel(this::handleLogin), "login");
        mainPanel.add(createAdminPanel(), "admin");
        mainPanel.add(createOperatorPanel(), "operator");
        mainPanel.add(createPublicUserPanel(), "public");

        mainFrame.add(mainPanel);
        cardLayout.show(mainPanel, "login");
    }

    private JPanel createAdminPanel() {
        JPanel adminPanel = new JPanel(new BorderLayout());
        
        // Toolbar
        JToolBar toolbar = new JToolBar();
        toolbar.add(createLogoutButton());
        toolbar.addSeparator();
        toolbar.add(new JLabel("Admin Dashboard"));
        adminPanel.add(toolbar, BorderLayout.NORTH);

        // Tabbed pane
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Sensor Configuration", new SensorConfigPanel());
        tabbedPane.addTab("Traffic Rules", new TrafficRulesPanel());
        tabbedPane.addTab("System Analytics", new SystemAnalyticsPanel());
        tabbedPane.addTab("User Management", new UserManagementPanel());
        
        adminPanel.add(tabbedPane, BorderLayout.CENTER);
        return adminPanel;
    }

    private JPanel createOperatorPanel() {
        JPanel operatorPanel = new JPanel(new BorderLayout());
        
        JToolBar toolbar = new JToolBar();
        toolbar.add(createLogoutButton());
        toolbar.addSeparator();
        toolbar.add(new JLabel("Operator Dashboard"));
        operatorPanel.add(toolbar, BorderLayout.NORTH);

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Live Monitoring", new LiveMonitoringPanel());
        tabbedPane.addTab("Signal Control", new SignalControlPanel());
        tabbedPane.addTab("Reports", new ReportsPanel());
        
        operatorPanel.add(tabbedPane, BorderLayout.CENTER);
        return operatorPanel;
    }

    private JPanel createPublicUserPanel() {
        JPanel publicPanel = new JPanel(new BorderLayout());
        
        JToolBar toolbar = new JToolBar();
        toolbar.add(createLogoutButton());
        toolbar.addSeparator();
        toolbar.add(new JLabel("Traffic Updates"));
        publicPanel.add(toolbar, BorderLayout.NORTH);

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Traffic Updates", new TrafficUpdatesPanel());
        tabbedPane.addTab("Report Issue", new IssueReportPanel());
        
        publicPanel.add(tabbedPane, BorderLayout.CENTER);
        return publicPanel;
    }

    private JButton createLogoutButton() {
        JButton logoutButton = new JButton("Logout");
        logoutButton.addActionListener(e -> {
            currentUser = null;
            userRole = null;
            cardLayout.show(mainPanel, "login");
        });
        return logoutButton;
    }

    private void handleLogin(String username, String password) {
        if (username.startsWith("admin")) {
            currentUser = username;
            userRole = "admin";
            cardLayout.show(mainPanel, "admin");
        } else if (username.startsWith("operator")) {
            currentUser = username;
            userRole = "operator";
            cardLayout.show(mainPanel, "operator");
        } else {
            currentUser = username;
            userRole = "public";
            cardLayout.show(mainPanel, "public");
        }
    }

    public void show() {
        mainFrame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }
            new MainApplication().show();
        });
    }
}