-- Database setup script for Student Management API
-- Run this script in your MySQL client

-- Create database (if not exists)
CREATE DATABASE IF NOT EXISTS studentdb;

-- Use the database
USE studentdb;

-- The students table will be automatically created by Hibernate
-- But you can also create it manually if needed:

CREATE TABLE IF NOT EXISTS students (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    phone_number VARCHAR(20),
    date_of_birth DATE,
    grade VARCHAR(10),
    gpa DOUBLE
);

-- Insert sample data (optional)
INSERT INTO students (first_name, last_name, email, phone_number, date_of_birth, grade, gpa) VALUES
('John', 'Doe', 'john.doe@example.com', '123-456-7890', '2000-05-15', 'A', 3.8),
('Jane', 'Smith', 'jane.smith@example.com', '123-456-7891', '1999-08-22', 'A+', 3.9),
('Mike', 'Johnson', 'mike.johnson@example.com', '123-456-7892', '2001-03-10', 'B+', 3.5),
('Sarah', 'Williams', 'sarah.williams@example.com', '123-456-7893', '2000-11-18', 'A-', 3.7),
('David', 'Brown', 'david.brown@example.com', '123-456-7894', '1999-12-05', 'B', 3.2);

-- Verify the data
SELECT * FROM students;
