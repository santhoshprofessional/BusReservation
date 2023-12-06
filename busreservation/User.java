// Specify the package for the current Java file
package com.pirai.busreservation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import testing.DBConnection;

// Class representing a User in the bus reservation system
public class User {
    // User attributes
    public String username;
    public String password;
    public String userPhNumber;
    public String userEmail;

    // Constructor to initialize a User object with the given username and password
    public User(String username, String password, String userPhNumber, String userEmail) {
        this.username = username;
        this.password = password;
        this.userPhNumber = userPhNumber;
        this.userEmail = userEmail;
    }

    // Method to register a new user
    public void registerUser() throws SQLException {
        // SQL query to insert user details into the 'user' table
        String query = "insert into user values(?,?,?,?)";
        try {
            // Establish a database connection
            Connection con = DBConnection.getConnection();
            // Prepare the SQL statement
            PreparedStatement pst = con.prepareStatement(query);
            // Set values for the parameters in the SQL statement
            pst.setString(1, username);
            pst.setString(2, password);
            pst.setString(3, userPhNumber);
            pst.setString(4, userEmail);
            // Execute the SQL statement to insert the user details
            pst.executeUpdate();

            // Display a success message
            System.out.println("User registered successfully!");
        } catch (SQLException e) {
            // Handle SQL exceptions
            if (e instanceof java.sql.SQLIntegrityConstraintViolationException) {
                System.out.println("User already exists. Try Another name.");
            } else {
                e.printStackTrace(); // Print the stack trace for other types of SQLExceptions
            }
        }
    }

    // Method to delete a user account
    public static void delAcc(String delName) {
        // SQL query to delete a user from the 'user' table based on the username
        String query = "delete from user where username = ?";
        Connection con = null;
        PreparedStatement pst = null;

        try {
            // Establish a database connection
            con = DBConnection.getConnection();
            // Prepare the SQL statement
            pst = con.prepareStatement(query);
            // Set the username parameter in the SQL statement
            pst.setString(1, delName);
            // Execute the SQL statement to delete the user account
            pst.executeUpdate();

            // Display a success message
            System.out.println("User Account Deleted successfully!");
        } catch (SQLException e) {
            // Handle SQL exceptions
            e.printStackTrace(); // Print the stack trace for other types of SQLExceptions
        } 
    }
    public static void displayUserInfo() throws SQLException {
    	 // SQL query to select all columns from the 'bus' table
        String query = "Select * from user";
        
        // Establish a database connection
        Connection con = DBConnection.getConnection();
        Statement st = con.createStatement();
        
        // Execute the SQL query
        ResultSet rs = st.executeQuery(query);
        System.out.println("Registered users are:");
        
        // Display bus information for each bus in the result set
        while (rs.next()) {
        	System.out.println("User name:" + rs.getString(1));
            System.out.println("Mobile Number:" + rs.getString(2));
            System.out.println("Email-Id:" + rs.getString(3));
        }
    }
}
