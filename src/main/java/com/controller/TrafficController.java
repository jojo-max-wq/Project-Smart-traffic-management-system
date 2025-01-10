package com.traffic.controller;

public class TrafficController {
    public boolean validateLogin(String username, String password) {
        // Placeholder logic
        return "admin".equals(username) && "admin".equals(password);
    }

    public void disconnect() {
        // Cleanup logic here
    }
}