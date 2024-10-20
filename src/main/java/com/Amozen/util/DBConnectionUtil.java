package com.Amozen.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectionUtil {

    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/amozen-clone"; // Updated database name
    private static final String DATABASE_USER = "root";
    private static final String DATABASE_PASSWORD = "root";
    private static Connection connection; // Declare connection here

    // Private constructor to prevent external instantiation
    private DBConnectionUtil() 
    {
    	
    }

    // Public method to get the instance (connection)
    public static Connection getConnection() {
        try {
            // Check if connection is null or closed
            if (connection == null || connection.isClosed()) {
                initializeConnection();
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle error or log it
        }
        return connection;
    }

    // Private method to initialize the connection
    private static void initializeConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace(); // Handle error or log it
        }
    }

    // Static method to close the connection
    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace(); // Handle error or log it
            }
        }
    }
}
