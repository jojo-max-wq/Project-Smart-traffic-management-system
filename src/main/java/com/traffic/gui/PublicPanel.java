package com.traffic.gui;

import javax.swing.*;

public class PublicPanel extends JPanel {
    public PublicPanel(JPanel parent, CardLayout cardLayout) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(new JLabel("Public Dashboard"));
    }
}