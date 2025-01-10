package com.traffic.gui;

import javax.swing.*;
import java.awt.*;

public class TrafficManagementSystem extends JFrame {

    private CardLayout cardLayout;
    private JPanel cards;
    private StatusBar statusBar;

    public TrafficManagementSystem() {
        super("Traffic Management System");
        setSize(1024, 768);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initializeUI();
    }

    private void initializeUI() {
        cardLayout = new CardLayout();
        cards = new JPanel(cardLayout);

        cards.add(new LoginPanel(cards, cardLayout), "login");
        cards.add(new AdminPanel(cards, cardLayout), "admin");
        cards.add(new OperatorPanel(cards, cardLayout), "operator");
        cards.add(new PublicPanel(cards, cardLayout), "public");

        add(cards, BorderLayout.CENTER);
        statusBar = new StatusBar();
        add(statusBar, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new TrafficManagementSystem().setVisible(true);
        });
    }
}