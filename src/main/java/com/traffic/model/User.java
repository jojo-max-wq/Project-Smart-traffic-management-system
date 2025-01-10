package com.traffic.models;

import java.sql.Timestamp;

// User model for authentication and authorization
public class User {
    private int id;
    private String username;
    private String passwordHash;
    private String role;
    private Timestamp lastLogin;
    private boolean isActive;

    // Getters and setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getPasswordHash() { return passwordHash; }
    public void setPasswordHash(String passwordHash) { this.passwordHash = passwordHash; }
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
    public Timestamp getLastLogin() { return lastLogin; }
    public void setLastLogin(Timestamp lastLogin) { this.lastLogin = lastLogin; }
    public boolean isActive() { return isActive; }
    public void setActive(boolean active) { isActive = active; }
}

// TrafficSensor model for monitoring traffic flow
class TrafficSensor {
    private int id;
    private String location;
    private double trafficFlow;
    private String status;
    private Timestamp lastUpdate;
    private boolean isOperational;

    // Getters and setters implemented similarly
}

// TrafficIncident model for reporting traffic issues
class TrafficIncident {
    private int id;
    private String location;
    private String type;
    private String description;
    private String status;
    private Timestamp reportTime;
    private Integer reportedBy;

    // Getters and setters implemented similarly
}

// TrafficSignal model for controlling traffic signals
class TrafficSignal {
    private int id;
    private String intersection;
    private String currentState;
    private String mode;
    private Timestamp lastStateChange;
    private boolean isOperational;

    // Getters and setters implemented similarly
}