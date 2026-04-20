package com.complaint.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Utility class for PostgreSQL Connection Management
 */
public class DBConnection {
    private static final String DRIVER = "org.postgresql.Driver";
    // Port 5432 is default for PostgreSQL
    private static final String URL = "jdbc:postgresql://localhost:5432/complaint_db";
    private static final String USER = "postgres"; // Default Postgres user
    private static final String PASSWORD = "1092"; // Update this with your actual password

    static {
        try {
            // Load PostgreSQL Driver
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("PostgreSQL Driver not found!");
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
