-- Create database
CREATE DATABASE IF NOT EXISTS traffic_management;
USE traffic_management;

-- Users table
CREATE TABLE users (
    id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) UNIQUE NOT NULL,
    password_hash VARCHAR(255) NOT NULL,
    role ENUM('admin', 'operator', 'public') NOT NULL,
    last_login TIMESTAMP,
    is_active BOOLEAN DEFAULT true,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Traffic sensors table
CREATE TABLE traffic_sensors (
    id INT PRIMARY KEY AUTO_INCREMENT,
    location VARCHAR(100) NOT NULL,
    traffic_flow DOUBLE,
    status VARCHAR(50),
    last_update TIMESTAMP,
    is_operational BOOLEAN DEFAULT true
);

-- Traffic incidents table
CREATE TABLE traffic_incidents (
    id INT PRIMARY KEY AUTO_INCREMENT,
    location VARCHAR(100) NOT NULL,
    type VARCHAR(50) NOT NULL,
    description TEXT,
    status VARCHAR(20) DEFAULT 'PENDING',
    report_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    reported_by INT,
    FOREIGN KEY (reported_by) REFERENCES users(id)
);

-- Traffic signals table
CREATE TABLE traffic_signals (
    id INT PRIMARY KEY AUTO_INCREMENT,
    intersection VARCHAR(100) NOT NULL,
    current_state VARCHAR(20),
    mode VARCHAR(20) DEFAULT 'AUTOMATIC',
    last_state_change TIMESTAMP,
    is_operational BOOLEAN DEFAULT true
);

CREATE TABLE traffic_rules (
    id INT PRIMARY KEY AUTO_INCREMENT,
    intersection VARCHAR(100) NOT NULL,
    rule_type VARCHAR(50),
    priority INT,
    active_times VARCHAR(100),
    created_by INT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (created_by) REFERENCES users(id)
);

-- System logs table
CREATE TABLE system_logs (
    id INT PRIMARY KEY AUTO_INCREMENT,
    event_type VARCHAR(50) NOT NULL,
    description TEXT,
    user_id INT,
    timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id)
);

-- Insert default admin user
INSERT INTO users (username, password_hash, role) 
VALUES ('admin', '$2a$12$LQv3c1yqBWVHxkd0LHAkCOYz6TtxMQJqhN8/LewqyPende3/eLS9K', 'admin');