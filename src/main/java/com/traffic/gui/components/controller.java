package com.traffic.controller;

import com.traffic.dao.*;
import com.traffic.models.*;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.*;
import org.mindrot.jbcrypt.BCrypt;

public class TrafficController {
    private final UserDAO userDAO;
    private final TrafficSensorDAO sensorDAO;
    private final TrafficIncidentDAO incidentDAO;
    private final ExecutorService executorService;
    private final ScheduledExecutorService scheduler;
    private final Map<Integer, TrafficSensor> sensorCache;
    
    public TrafficController() {
        this.userDAO = new UserDAO();
        this.sensorDAO = new TrafficSensorDAO();
        this.incidentDAO = new TrafficIncidentDAO();
        this.executorService = Executors.newFixedThreadPool(4);
        this.scheduler = Executors.newScheduledThreadPool(2);
        this.sensorCache = new ConcurrentHashMap<>();
        
        initializeScheduledTasks();
    }
    
    private void initializeScheduledTasks() {
        // Update sensor data every 5 seconds
        scheduler.scheduleAtFixedRate(() -> {
            try {
                updateSensorData();
            } catch (Exception e) {
                logError("Error updating sensor data", e);
            }
        }, 0, 5, TimeUnit.SECONDS);
        
        // Clean up cache every 30 minutes
        scheduler.scheduleAtFixedRate(() -> {
            try {
                cleanupCache();
            } catch (Exception e) {
                logError("Error cleaning cache", e);
            }
        }, 30, 30, TimeUnit.MINUTES);
    }
    
    public CompletableFuture<User> loginAsync(String username, String password) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                return userDAO.authenticateUser(username, password);
            } catch (SQLException e) {
                throw new CompletionException(e);
            }
        }, executorService);
    }
    
    public CompletableFuture<List<TrafficIncident>> getActiveIncidentsAsync() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                return incidentDAO.getActiveIncidents();
            } catch (SQLException e) {
                throw new CompletionException(e);
            }
        }, executorService);
    }
    
    public void reportIncident(TrafficIncident incident) throws SQLException {
        incidentDAO.reportIncident(incident);
    }
    
    private void updateSensorData() throws SQLException {
        List<TrafficSensor> sensors = sensorDAO.getActiveSensors();
        for (TrafficSensor sensor : sensors) {
            sensorCache.put(sensor.getId(), sensor);
        }
    }
    
    private void cleanupCache() {
        // Remove sensors that haven't been updated in the last minute
        long cutoff = System.currentTimeMillis() - TimeUnit.MINUTES.toMillis(1);
        sensorCache.values().removeIf(sensor -> 
            sensor.getLastUpdate().getTime() < cutoff);
    }
    
    private void logError(String message, Exception e) {
        // TODO: Implement proper logging
        System.err.println(message + ": " + e.getMessage());
        e.printStackTrace();
    }
    
    public void shutdown() {
        executorService.shutdown();
        scheduler.shutdown();
        try {
            if (!executorService.awaitTermination(5, TimeUnit.SECONDS)) {
                executorService.shutdownNow();
            }
            if (!scheduler.awaitTermination(5, TimeUnit.SECONDS)) {
                scheduler.shutdownNow();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}