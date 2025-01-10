package com.traffic.gui.components.operator;

import javax.swing.*;
import java.awt.*;

public class LiveMonitoringPanel extends JPanel {
    public LiveMonitoringPanel() {
        setLayout(new BorderLayout());
        
        // Map view
        JPanel mapPanel = new JPanel();
        mapPanel.setPreferredSize(new Dimension(800, 500));
        mapPanel.setBorder(BorderFactory.createTitledBorder("Traffic Map"));
        add(mapPanel, BorderLayout.CENTER);
        
        // Status panel
        JPanel statusPanel = new JPanel(new GridLayout(1, 3));
        statusPanel.add(createStatusBox("Active Sensors", "24/24"));
        statusPanel.add(createStatusBox("Current Flow Rate", "Normal"));
        statusPanel.add(createStatusBox("Alerts", "No active alerts"));
        add(statusPanel, BorderLayout.SOUTH);
    }

    private JPanel createStatusBox(String title, String value) {
        JPanel box = new JPanel(new BorderLayout());
        box.setBorder(BorderFactory.createTitledBorder(title));
        box.add(new JLabel(value, SwingConstants.CENTER));
        return box;
    }
}