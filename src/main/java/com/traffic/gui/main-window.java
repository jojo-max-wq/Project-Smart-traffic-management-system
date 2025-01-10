package com.traffic.gui;

import javax.swing.*;
import java.awt.*;
import com.traffic.validation.DataValidator;
import com.traffic.service.TrafficManagementService;
import com.traffic.model.TrafficSignal;
import com.traffic.util.GUIHelper;
import java.util.List;

public class MainWindow extends JFrame {
    private final TrafficManagementService service;
    private final DataValidator validator;
    private JPanel signalsPanel;
    private JTextField searchField;
    private JLabel statusLabel;

    public MainWindow() {
        this.service = new TrafficManagementService();
        this.validator = new DataValidator();
        
        // Show login dialog first
        LoginDialog loginDialog = new LoginDialog(this);
        loginDialog.setVisible(true);
        
        if (!loginDialog.isAuthenticated()) {
            System.exit(0);
        }
        
        initializeUI();
        loadSignals();
    }

    private void initializeUI() {
        setTitle("Traffic Management System");
        setSize(1024, 768);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Set custom colors
        Color primaryColor = new Color(41, 128, 185);
        Color backgroundColor = new Color(236, 240, 241);
        
        // Create main panel with border layout
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(backgroundColor);
        
        // Create and add toolbar
        createToolbar(mainPanel, primaryColor);
        
        // Create content panel
        createContentPanel(mainPanel, backgroundColor);
        
        // Create status bar
        createStatusBar(mainPanel, primaryColor);
        
        add(mainPanel);
        
        // Center the window
        setLocationRelativeTo(null);
    }

    private void createToolbar(JPanel mainPanel, Color primaryColor) {
        JToolBar toolbar = new JToolBar();
        toolbar.setFloatable(false);
        toolbar.setBackground(primaryColor);
        
        // Search panel
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        searchPanel.setBackground(primaryColor);
        
        searchField = new JTextField(20);
        JButton searchButton = new JButton("Search");
        searchButton.setBackground(Color.WHITE);
        
        searchPanel.add(new JLabel("Search: "));
        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        
        toolbar.add(searchPanel);
        
        // Add refresh button
        JButton refreshButton = new JButton("Refresh");
        refreshButton.setBackground(Color.WHITE);
        refreshButton.addActionListener(e -> loadSignals());
        toolbar.add(Box.createHorizontalGlue());
        toolbar.add(refreshButton);
        
        mainPanel.add(toolbar, BorderLayout.NORTH);
    }

    private void createContentPanel(JPanel mainPanel, Color backgroundColor) {
        // Create split pane
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitPane.setBackground(backgroundColor);
        
        // Create signals list panel
        signalsPanel = new JPanel(new GridLayout(0, 1, 5, 5));
        signalsPanel.setBackground(backgroundColor);
        JScrollPane scrollPane = new JScrollPane(signalsPanel);
        
        // Create detail panel
        JPanel detailPanel = new JPanel(new BorderLayout());
        detailPanel.setBackground(backgroundColor);
        detailPanel.setBorder(BorderFactory.createTitledBorder("Signal Details"));
        
        splitPane.setLeftComponent(scrollPane);
        splitPane.setRightComponent(detailPanel);
        splitPane.setDividerLocation(300);
        
        mainPanel.add(splitPane, BorderLayout.CENTER);
    }

    private void createStatusBar(JPanel mainPanel, Color primaryColor) {
        JPanel statusBar = new JPanel(new FlowLayout(FlowLayout.LEFT));
        statusBar.setBackground(primaryColor);
        
        statusLabel = new JLabel("Ready");
        statusLabel.setForeground(Color.WHITE);
        statusBar.add(statusLabel);
        
        mainPanel.add(statusBar, BorderLayout.SOUTH);
    }

    private void loadSignals() {
        try {
            List<TrafficSignal> signals = service.getAllSignals();
            signalsPanel.removeAll();
            
            for (TrafficSignal signal : signals) {
                SignalPanel panel = new SignalPanel(signal);
                signalsPanel.add(panel);
            }
            
            signalsPanel.revalidate();
            signalsPanel.repaint();
            statusLabel.setText("Signals loaded successfully");
        } catch (Exception e) {
            GUIHelper.showError(this, "Error loading signals: " + e.getMessage());
            statusLabel.setText("Error loading signals");
        }
    }

    public static void main(String[] args) {
        // Set look and feel to system default
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        SwingUtilities.invokeLater(() -> {
            MainWindow window = new MainWindow();
            window.setVisible(true);
        });
    }
}
