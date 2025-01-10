package com.traffic.gui;

import javax.swing.*;

public class OperatorPanel extends JPanel {
    public OperatorPanel(JPanel parent, CardLayout cardLayout) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(new JLabel("Operator Dashboard"));
    }
}