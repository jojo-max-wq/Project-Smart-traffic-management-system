package com.traffic.gui.components.public;

import javax.swing.*;
import java.awt.*;

public class TrafficUpdatesPanel extends JPanel {
    public TrafficUpdatesPanel() {
        setLayout(new BorderLayout());
        
        // Updates list
        DefaultListModel<String> listModel = new DefaultListModel<>();
        listModel.addElement("Heavy traffic on Main Street - Delay: 15 mins");
        listModel.addElement("Construction work on 5th Avenue - Use alternate route");
        listModel.addElement("Accident cleared on Broadway - Traffic normal");
        
        JList<String> updatesList = new JList<>(listModel);
        add(new JScrollPane(updatesList), BorderLayout.CENTER);
        
        // Control panel
        JPanel controlPanel = new JPanel();
        controlPanel.add(new JButton("Refresh"));
        controlPanel.add(new JButton("Set Alerts"));
        add(controlPanel, BorderLayout.SOUTH);
    }
}