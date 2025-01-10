package com.traffic.dao;

import com.traffic.database.DatabaseConnection;
import com.traffic.models.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import org.mindrot.jbcrypt.BCrypt;

public class UserDAO {
    public User authenticateUser(String username, String password) throws SQLException {
        String sql = "SELECT * FROM users WHERE username = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next() && BCrypt.checkpw(password, rs.getString("password_hash"))) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setRole(rs.getString("role"));
                user.setActive(rs.getBoolean("is_active"));
                
                // Update last login time
                updateLastLogin(user.getId());
                
                return user;
            }
        }
        return null;
    }

    private void updateLastLogin(int userId) throws SQLException {
        String sql = "UPDATE users SET last_login = CURRENT_TIMESTAMP WHERE id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, userId);
            stmt.executeUpdate();
        }
    }
}

public class TrafficSensorDAO {
    public List<TrafficSensor> getActiveSensors() throws SQLException {
        List<TrafficSensor> sensors = new ArrayList<>();
        String sql = "SELECT * FROM traffic_sensors WHERE is_operational = true";
        
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                TrafficSensor sensor = new TrafficSensor();
                // Populate sensor object from ResultSet
                sensors.add(sensor);
            }
        }
        return sensors;
    }

    public void updateSensorStatus(int sensorId, boolean isOperational) throws SQLException {
        String sql = "UPDATE traffic_sensors SET is_operational = ?, last_update = CURRENT_TIMESTAMP WHERE id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setBoolean(1, isOperational);
            stmt.setInt(2, sensorId);
            stmt.executeUpdate();
        }
    }
}

public class TrafficIncidentDAO {
    public void reportIncident(TrafficIncident incident) throws SQLException {
        String sql = "INSERT INTO traffic_incidents (location, type, description, status, reported_by) VALUES (?, ?, ?, ?, ?)";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, incident.getLocation());
            stmt.setString(2, incident.getType());
            stmt.setString(3, incident.getDescription());
            stmt.setString(4, "PENDING");
            stmt.setInt(5, incident.getReportedBy());
            
            stmt.executeUpdate();
        }
    }

    public List<TrafficIncident> getActiveIncidents() throws SQLException {
        List<TrafficIncident> incidents = new ArrayList<>();
        String sql = "SELECT * FROM traffic_incidents WHERE status != 'RESOLVED' ORDER BY report_time DESC";
        
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                TrafficIncident incident = new TrafficIncident();
                // Populate incident object from ResultSet
                incidents.add(incident);
            }
        }
        return incidents;
    }
}