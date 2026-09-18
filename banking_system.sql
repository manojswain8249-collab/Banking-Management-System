-- ============================================================
--  Banking System Database
--  Author: Naved Sheikh
--  Created: 2025-08-10
--  Description: SQL schema and sample data for the 
--               Banking Management System (JDBC-based).
-- ============================================================

-- Drop existing database if it exists
DROP DATABASE IF EXISTS banking_system;

-- Create new database
CREATE DATABASE banking_system;
USE banking_system;

-- ============================================================
-- 1. Table: users
--    Stores personal details of each registered user.
-- ============================================================
CREATE TABLE users (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    phone VARCHAR(15) NOT NULL,
    address VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- ============================================================
-- 2. Table: accounts
--    Stores account-specific details linked to a user.
-- ============================================================
CREATE TABLE accounts (
    account_number BIGINT PRIMARY KEY,
    user_id INT NOT NULL,
    balance DECIMAL(15,2) DEFAULT 0.00,
    security_pin CHAR(4) NOT NULL,
    account_type ENUM('SAVINGS', 'CURRENT') DEFAULT 'SAVINGS',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE
);

-- ============================================================
-- Sample Data
-- ============================================================

-- Insert sample users
INSERT INTO users (name, email, phone, address) VALUES
('John Doe', 'john.doe@example.com', '9876543210', 'Mumbai, India'),
('Priya Sharma', 'priya.sharma@example.com', '9123456789', 'Delhi, India');

-- Insert sample accounts
INSERT INTO accounts (account_number, user_id, balance, security_pin, account_type) VALUES
(1001001001, 1, 50000.00, '1234', 'SAVINGS'),
(1001001002, 2, 75000.00, '5678', 'CURRENT');
