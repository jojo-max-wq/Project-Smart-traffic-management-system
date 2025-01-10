package com.traffic.gui.components.admin;

import javax.swing.*;
import java.awt.*;

public class SensorConfigPanel extends JPanel {
    public SensorConfigPanel() {
        setLayout(new BorderLayout());
        
        // Sensor list
        DefaultListModel<String> listModel = new DefaultListModel<>();
        listModel.addElement("Sensor 1 - Main Street");
        listModel.addElement("Sensor 2 - Broadway");
        listModel.addElement("Sensor 3 - 5th Avenue");
        
        JList<String> sensorList = new JList<>(listModel);
        add(new JScrollPane(sensorList), BorderLayout.WEST);

        // Configuration form
        JPanel configPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        
        configPanel.add(new JLabel("Sensor ID:"), gbc);
        gbc.gridx = 1;
        configPanel.add(new JTextField(20), gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        configPanel.add(new JLabel("Location:"), gbc);
        gbc.gridx = 1;
        configPanel.add(new JTextField(20), gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        configPanel.add(new JLabel("Status:"), gbc);
        gbc.gridx = 1;
        configPanel.add(new JComboBox<>(new String[]{"Active", "Inactive", "Maintenance"}), gbc);

        add(configPanel, BorderLayout.CENTER);
    }
}