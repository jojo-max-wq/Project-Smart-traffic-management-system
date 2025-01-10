package com.traffic.gui;

import javax.swing.*;
import java.awt.*;
import com.traffic.model.TrafficSignal;
import com.traffic.validation.DataValidator;
import com.traffic.util.GUIHelper;

public class SignalPanel extends JPanel {
    private final TrafficSignal signal;
    private final DataValidator validator;
    private JComboBox<String> statusCombo;
    private JComboBox<String> modeCombo;
    private JLabel statusIndicator;

    public SignalPanel(TrafficSignal signal) {
        this.signal = signal;
        this.validator = new DataValidator();
        
        initializePanel();
    }

    private void initializePanel() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createEmptyBorder(5, 5, 5, 5),
            BorderFactory.createLineBorder(Color.GRAY)
        ));

        // Create main content panel
        JPanel contentPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Signal name and status indicator
        JPanel headerPanel = new JPanel(new BorderLayout());
        JLabel nameLabel = new JLabel(signal.getIntersection());
        nameLabel.setFont(new Font("Arial", Font.BOLD, 14));
        
        statusIndicator = new JLabel("●");
        statusIndicator.setFont(new Font("Arial", Font.BOLD, 20));
        updateStatusIndicator(signal.getStatus());
        
        headerPanel.add(nameLabel, BorderLayout.WEST);
        headerPanel.add(statusIndicator, BorderLayout.EAST);
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        contentPanel.add(headerPanel, gbc);

        // Status selection
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        contentPanel.add(new JLabel("Status:"), gbc);

        gbc.gridx = 1;
        String[] statuses = {"RED", "YELLOW", "GREEN"};
        statusCombo = new JComboBox<>(statuses);
        statusCombo.setSelectedItem(signal.getStatus());
        statusCombo.addActionListener(e -> validateAndUpdateStatus());
        contentPanel.add(statusCombo, gbc);

        // Mode selection
        gbc.gridx = 0;
        gbc.gridy = 2;
        contentPanel.add(new JLabel("Mode:"), gbc);

        gbc.gridx = 1;
        String[] modes = {"AUTOMATIC", "MANUAL", "EMERGENCY"};
        modeCombo = new JComboBox<>(modes);
        modeCombo.setSelectedItem(signal.getMode());
        modeCombo.addActionListener(e -> validateMode());
        contentPanel.add(modeCombo, gbc);

        // Add update button
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        JButton updateButton = new JButton("Update Signal");
        updateButton.addActionListener(e -> updateSignal());
        contentPanel.add(updateButton, gbc);

        add(contentPanel, BorderLayout.CENTER);
    }

    private void validateAndUpdateStatus() {
        String status = (String) statusCombo.getSelectedItem();
        if (validator.isValidSignalStatus(status)) {
            updateStatusIndicator(status);
            statusCombo.setBorder(null);
        } else {
            statusCombo.setBorder(BorderFactory.createLineBorder(Color.RED));
            GUIHelper.showError(this, "Invalid signal status");
        }
    }

    private void updateStatusIndicator(String status) {
        Color indicatorColor = switch (status) {
            case "RED" -> Color.RED;
            case "YELLOW" -> Color.YELLOW;
            case "GREEN" -> Color.GREEN;
            default -> Color.GRAY;
        };
        statusIndicator.setForeground(indicatorColor);
    }

    private void validateMode() {
        String mode = (String) modeCombo.getSelectedItem();
        if (!validator.isValidSignalMode(mode)) {
            modeCombo.setBorder(BorderFactory.createLineBorder(Color.RED));
            GUIHelper.showError(this, "Invalid signal mode");
        } else {
            modeCombo.setBorder(null);
        }
    }

    private void updateSignal() {
        String status = (String) statusCombo.getSelectedItem();
        String mode = (String) modeCombo.getSelectedItem();
        
        if (validator.isValidSignalStatus(status) && validator.isValidSignalMode(mode)) {
            signal.setStatus(status);
            signal.setMode(mode);
            GUIHelper.showSuccess(this, "Signal updated successfully");
        } else {
            GUIHelper.showError(this, "Invalid signal parameters");
        }
    }
}
