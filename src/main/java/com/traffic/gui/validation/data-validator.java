package com.traffic.validation;

import java.util.regex.Pattern;
import java.util.Set;
import java.util.HashSet;
import java.time.LocalDateTime;

public class DataValidator {
    // Regular expression patterns for validation
    private static final Pattern USERNAME_PATTERN = Pattern.compile("^[a-zA-Z0-9_]{3,20}$");
    private static final Pattern PASSWORD_PATTERN = Pattern.compile("^[a-zA-Z0-9@#$%^&+=]{6,20}$");
    private static final Pattern SEARCH_PATTERN = Pattern.compile("^[a-zA-Z0-9\\s-]{1,50}$");
    private static final Pattern INTERSECTION_PATTERN = Pattern.compile("^[a-zA-Z0-9\\s-]{3,100}$");
    private static final Pattern DESCRIPTION_PATTERN = Pattern.compile("^[a-zA-Z0-9\\s.,!?-]{0,500}$");

    // Valid values for enumerated fields
    private static final Set<String> VALID_STATUSES = new HashSet<>(Set.of("RED", "YELLOW", "GREEN"));
    private static final Set<String> VALID_MODES = new HashSet<>(Set.of("AUTOMATIC", "MANUAL", "EMERGENCY"));
    private static final Set<String> VALID_INCIDENT_TYPES = new HashSet<>(Set.of(
        "ACCIDENT", "CONSTRUCTION", "WEATHER", "MAINTENANCE", "OTHER"
    ));
    private static final Set<String> VALID_INCIDENT_STATUSES = new HashSet<>(Set.of(
        "REPORTED", "IN_PROGRESS", "RESOLVED", "CLOSED"
    ));

    // Username validation
    public boolean isValidUsername(String username) {
        return username != null && USERNAME_PATTERN.matcher(username).matches();
    }

    // Password validation
    public boolean isValidPassword(String password) {
        return password != null && PASSWORD_PATTERN.matcher(password).matches();
    }

    // Search query validation
    public boolean isValidSearchQuery(String query) {
        if (query == null || query.trim().isEmpty()) {
            return true; // Empty search is valid
        }
        return SEARCH_PATTERN.matcher(query).matches();
    }

    // Signal status validation
    public boolean isValidSignalStatus(String status) {
        return status != null && VALID_STATUSES.contains(status.toUpperCase());
    }

    // Signal mode validation
    public boolean isValidSignalMode(String mode) {
        return mode != null && VALID_MODES.contains(mode.toUpperCase());
    }

    // Intersection name validation
    public boolean isValidIntersection(String intersection) {
        return intersection != null && INTERSECTION_PATTERN.matcher(intersection).matches();
    }

    // Traffic incident validation methods
    public boolean isValidIncidentType(String type) {
        return type != null && VALID_INCIDENT_TYPES.contains(type.toUpperCase());
    }

    public boolean isValidIncidentStatus(String status) {
        return status != null && VALID_INCIDENT_STATUSES.contains(status.toUpperCase());
    }

    public boolean isValidIncidentDescription(String description) {
        return description == null || DESCRIPTION_PATTERN.matcher(description).matches();
    }

    // Date and time validation
    public boolean isValidDateTime(LocalDateTime dateTime) {
        return dateTime != null && !dateTime.isAfter(LocalDateTime.now());
    }

    // ID validation
    public boolean isValidId(Long id) {
        return id != null && id > 0;
    }

    // Numeric range validation
    public boolean isInRange(int value, int min, int max) {
        return value >= min && value <= max;
    }

    public boolean isInRange(double value, double min, double max) {
        return value >= min && value <= max;
    }

    // Coordinates validation (for map locations)
    public boolean isValidLatitude(double latitude) {
        return isInRange(latitude, -90.0, 90.0);
    }

    public boolean isValidLongitude(double longitude) {
        return isInRange(longitude, -180.0, 180.0);
    }

    // Combined validation methods for common use cases
    public boolean isValidSignalUpdate(String status, String mode, String intersection) {
        return isValidSignalStatus(status) && 
               isValidSignalMode(mode) && 
               isValidIntersection(intersection);
    }

    public boolean isValidIncident(String type, String status, String description, LocalDateTime reportTime) {
        return isValidIncidentType(type) && 
               isValidIncidentStatus(status) && 
               isValidIncidentDescription(description) && 
               isValidDateTime(reportTime);
    }

    // Helper method to get validation error message
    public String getValidationMessage(String fieldName, String value) {
        return switch (fieldName.toLowerCase()) {
            case "username" -> "Username must be 3-20 characters long and contain only letters, numbers, and underscores";
            case "password" -> "Password must be 6-20 characters long and may contain letters, numbers, and special characters";
            case "status" -> "Status must be one of: " + String.join(", ", VALID_STATUSES);
            case "mode" -> "Mode must be one of: " + String.join(", ", VALID_MODES);
            case "intersection" -> "Intersection name must be 3-100 characters long and contain only letters, numbers, spaces, and hyphens";
            case "incident_type" -> "Incident type must be one of: " + String.join(", ", VALID_INCIDENT_TYPES);
            case "incident_status" -> "Incident status must be one of: " + String.join(", ", VALID_INCIDENT_STATUSES);
            case "description" -> "Description must not exceed 500 characters and contain only letters, numbers, spaces, and basic punctuation";
            default -> "Invalid input for " + fieldName;
        };
    }
}
