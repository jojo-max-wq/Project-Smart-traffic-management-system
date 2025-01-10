package com.traffic.database;

public class DatabaseConfig {
    public static final String URL = "jdbc:mysql://localhost:3306/traffic_management";
    public static final String USER = "root";
    public static final String PASSWORD = "root";
    
    private DatabaseConfig() {
        // Private constructor to prevent instantiation
    }
}