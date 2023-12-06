// Specify the package for the current Java file
package com.pirai.busreservation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// Class representing a singleton database connection for the Bus Reservation System
public class DBConnection {
    // JDBC URL for connecting to the MySQL database
    private static final String url = "jdbc:mysql://localhost:3306/busreservation";
    // MySQL database username
    private static final String username = "root";
    // MySQL database password
    private static final String userpass = "root";

    // Create a single instance of the connection
    private static Connection connection;

    // Private constructor to prevent object creation from outside
    private DBConnection() {
        // Initialize the connection
        try {
            // Establish a connection to the MySQL database
            connection = DriverManager.getConnection(url, username, userpass);
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exception appropriately
        }
    }

    // Public method to get the single instance of the connection
    public static Connection getConnection() {
        // If the connection is null or closed, create a new connection
        if (connection == null) {
            new DBConnection();
        }
        return connection;
    }
}
