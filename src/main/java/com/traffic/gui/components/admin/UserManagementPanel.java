package com.traffic.gui.components.admin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.table.DefaultTableModel;

public class UserManagementPanel extends JPanel {
    private JTextField usernameField;
    private JTextField emailField;
    private JComboBox<String> roleComboBox;
    private JTable userTable;
    private DefaultTableModel tableModel;

    public UserManagementPanel() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Create form panel
        JPanel formPanel = createFormPanel();
        add(formPanel, BorderLayout.NORTH);

        // Create table panel
        JPanel tablePanel = createTablePanel();
        add(tablePanel, BorderLayout.CENTER);
    }

    private JPanel createFormPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Username field
        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(new JLabel("Username:"), gbc);
        gbc.gridx = 1;
        usernameField = new JTextField(20);
        panel.add(usernameField, gbc);

        // Email field
        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(new JLabel("Email:"), gbc);
        gbc.gridx = 1;
        emailField = new JTextField(20);
        panel.add(emailField, gbc);

        // Role combo box
        gbc.gridx = 0; gbc.gridy = 2;
        panel.add(new JLabel("Role:"), gbc);
        gbc.gridx = 1;
        roleComboBox = new JComboBox<>(new String[]{"Admin", "Operator", "Public"});
        panel.add(roleComboBox, gbc);

        // Add button
        gbc.gridx = 1; gbc.gridy = 3;
        JButton addButton = new JButton("Add User");
        addButton.addActionListener(e -> addUser());
        panel.add(addButton, gbc);

        return panel;
    }

    private JPanel createTablePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder("User List"));

        // Create table model
        String[] columns = {"Username", "Email", "Role", "Actions"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 3; // Only actions column is editable
            }
        };

        // Create table
        userTable = new JTable(tableModel);
        userTable.getColumnModel().getColumn(3).setCellRenderer(new ButtonRenderer());
        userTable.getColumnModel().getColumn(3).setCellEditor(new ButtonEditor(new JCheckBox()));

        // Add table to scroll pane
        JScrollPane scrollPane = new JScrollPane(userTable);
        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }

    private void addUser() {
        String username = usernameField.getText();
        String email = emailField.getText();
        String role = (String) roleComboBox.getSelectedItem();

        if (validateInput(username, email)) {
            Object[] rowData = {username, email, role, "Delete"};
            tableModel.addRow(rowData);
            clearForm();
        }
    }

    private boolean validateInput(String username, String email) {
        if (username.length() < 3) {
            JOptionPane.showMessageDialog(this, "Username must be at least 3 characters long");
            return false;
        }
        if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            JOptionPane.showMessageDialog(this, "Please enter a valid email address");
            return false;
        }
        return true;
    }

    private void clearForm() {
        usernameField.setText("");
        emailField.setText("");
        roleComboBox.setSelectedIndex(0);
    }

    // Custom button renderer
    class ButtonRenderer extends JButton implements TableCellRenderer {
        public ButtonRenderer() {
            setOpaque(true);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                boolean isSelected, boolean hasFocus, int row, int column) {
            setText((value == null) ? "" : value.toString());
            return this;
        }
    }

    // Custom button editor
    class ButtonEditor extends DefaultCellEditor {
        private JButton button;
        private String label;
        private boolean isPushed;

        public ButtonEditor(JCheckBox checkBox) {
            super(checkBox);
            button = new JButton();
            button.setOpaque(true);
            button.addActionListener(e -> fireEditingStopped());
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value,
                boolean isSelected, int row, int column) {
            label = (value == null) ? "" : value.toString();
            button.setText(label);
            isPushed = true;
            return button;
        }

        @Override
        public Object getCellEditorValue() {
            if (isPushed) {
                // Handle delete action
                int row = userTable.getSelectedRow();
                if (row != -1) {
                    tableModel.removeRow(row);
                }
            }
            isPushed = false;
            return label;
        }
    }
}