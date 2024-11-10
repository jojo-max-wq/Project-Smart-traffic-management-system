import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TrafficManagementSystem {
    private JFrame frame;
    private CardLayout cardLayout;
    private JPanel mainPanel;

    public TrafficManagementSystem() {
        // Create main frame
        frame = new JFrame("Traffic Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 600);
        frame.setLocationRelativeTo(null);

        // Create card layout for switching between panels
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        // Add panels
        mainPanel.add(createLoginPanel(), "login");
        mainPanel.add(createAdminPanel(), "admin");
        mainPanel.add(createOperatorPanel(), "operator");
        mainPanel.add(createPublicPanel(), "public");

        frame.add(mainPanel);
    }

    private JPanel createLoginPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        panel.setBackground(new Color(245, 245, 245));

        // Create components
        JLabel titleLabel = new JLabel("Traffic Management System");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        JTextField usernameField = new JTextField(20);
        JPasswordField passwordField = new JPasswordField(20);
        String[] roles = {"Admin", "Operator", "Public User"};
        JComboBox<String> roleCombo = new JComboBox<>(roles);
        JButton loginButton = new JButton("Login");

        // Style components
        loginButton.setBackground(new Color(33, 150, 243));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFocusPainted(false);

        // Add components
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(10, 10, 30, 10);
        panel.add(titleLabel, gbc);

        gbc.gridy = 1;
        gbc.gridwidth = 1;
        panel.add(new JLabel("Username:"), gbc);

        gbc.gridx = 1;
        panel.add(usernameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(new JLabel("Password:"), gbc);

        gbc.gridx = 1;
        panel.add(passwordField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(new JLabel("Role:"), gbc);

        gbc.gridx = 1;
        panel.add(roleCombo, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        panel.add(loginButton, gbc);

        // Add login button action
        loginButton.addActionListener(e -> {
            String role = roleCombo.getSelectedItem().toString().toLowerCase();
            cardLayout.show(mainPanel, role);
        });

        return panel;
    }

    private JPanel createAdminPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        
        // Create menu panel
        JPanel menuPanel = new JPanel(new GridLayout(0, 1, 5, 5));
        menuPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        JButton sensorConfigButton = new JButton("Sensor Configuration");
        JButton trafficRulesButton = new JButton("Traffic Rules");
        JButton systemControlButton = new JButton("System Control");
        JButton logoutButton = new JButton("Logout");
        
        menuPanel.add(sensorConfigButton);
        menuPanel.add(trafficRulesButton);
        menuPanel.add(systemControlButton);
        menuPanel.add(logoutButton);
        
        // Create content panel
        JPanel contentPanel = new JPanel();
        contentPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Add components to main panel
        panel.add(menuPanel, BorderLayout.WEST);
        panel.add(contentPanel, BorderLayout.CENTER);
        
        // Add button actions
        logoutButton.addActionListener(e -> cardLayout.show(mainPanel, "login"));
        
        return panel;
    }

    private JPanel createOperatorPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        
        // Create menu panel
        JPanel menuPanel = new JPanel(new GridLayout(0, 1, 5, 5));
        menuPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        JButton monitoringButton = new JButton("Traffic Monitoring");
        JButton configButton = new JButton("Signal Configuration");
        JButton reportsButton = new JButton("Reports");
        JButton logoutButton = new JButton("Logout");
        
        menuPanel.add(monitoringButton);
        menuPanel.add(configButton);
        menuPanel.add(reportsButton);
        menuPanel.add(logoutButton);
        
        // Create content panel
        JPanel contentPanel = new JPanel();
        contentPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Add components to main panel
        panel.add(menuPanel, BorderLayout.WEST);
        panel.add(contentPanel, BorderLayout.CENTER);
        
        // Add button actions
        logoutButton.addActionListener(e -> cardLayout.show(mainPanel, "login"));
        
        return panel;
    }

    private JPanel createPublicPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        
        // Create menu panel
        JPanel menuPanel = new JPanel(new GridLayout(0, 1, 5, 5));
        menuPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        JButton updatesButton = new JButton("Traffic Updates");
        JButton reportButton = new JButton("Report Issue");
        JButton logoutButton = new JButton("Logout");
        
        menuPanel.add(updatesButton);
        menuPanel.add(reportButton);
        menuPanel.add(logoutButton);
        
        // Create content panel
        JPanel contentPanel = new JPanel();
        contentPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Add components to main panel
        panel.add(menuPanel, BorderLayout.WEST);
        panel.add(contentPanel, BorderLayout.CENTER);
        
        // Add button actions
        logoutButton.addActionListener(e -> cardLayout.show(mainPanel, "login"));
        
        return panel;
    }

    public void show() {
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TrafficManagementSystem system = new TrafficManagementSystem();
            system.show();
        });
    }
}