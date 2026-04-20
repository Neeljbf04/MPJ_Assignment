-- Create Database (Note: In Postgres, you usually create the DB first via psql or pgAdmin)
-- CREATE DATABASE complaint_db;

-- Drop table and types if they exist
DROP TABLE IF EXISTS complaints;
DROP TYPE IF EXISTS issue_category;
DROP TYPE IF EXISTS complaint_status;

-- Create Custom ENUM Types for Postgres
CREATE TYPE issue_category AS ENUM ('Technical', 'Billing', 'Feedback', 'Other');
CREATE TYPE complaint_status AS ENUM ('Pending', 'Resolved');

-- Create Complaints Table
CREATE TABLE complaints (
    complaint_id SERIAL PRIMARY KEY,
    user_name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    issue_type issue_category NOT NULL,
    description TEXT NOT NULL,
    status complaint_status DEFAULT 'Pending',
    date_submitted TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Set starting value for ID (Postgres uses sequences for SERIAL)
ALTER SEQUENCE complaints_complaint_id_seq RESTART WITH 1001;

-- Indexing
CREATE INDEX idx_email ON complaints(email);
CREATE INDEX idx_status ON complaints(status);

-- Sample Data
INSERT INTO complaints (user_name, email, issue_type, description, status) 
VALUES 
('John Doe', 'john@example.com', 'Technical', 'Internet connection is unstable since morning.', 'Pending'),
('Jane Smith', 'jane@test.org', 'Billing', 'Overcharged for last month subscription.', 'Resolved');
