package com.traffic.model;

public class SensorData {
    private String location;
    private int trafficDensity;

    public SensorData(String location, int trafficDensity) {
        this.location = location;
        this.trafficDensity = trafficDensity;
    }

    public String getLocation() {
        return location;
    }

    public int getTrafficDensity() {
        return trafficDensity;
    }
}