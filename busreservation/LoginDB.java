// Specify the package for the current Java file
package com.pirai.busreservation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import testing.DBConnection;

// Class representing user login functionality and database operations
public class LoginDB {

    // User login credentials
    public String loginUsername;
    public String loginPassword;

    // Constructor to initialize a User object with the given username and password
    public LoginDB(String loginUsername, String loginPassword) {
        this.loginUsername = loginUsername;
        this.loginPassword = loginPassword;
    }

    // Method to insert user login details into the database
    public void LoginUser() {
        // SQL query to insert user login details into the 'logindp' table
        String query = "insert into logindp values(?,?)";
        Connection con = null;
        PreparedStatement pst = null;

        try {
            // Establish a database connection
            con = DBConnection.getConnection();
            // Prepare the SQL statement
            pst = con.prepareStatement(query);
            // Set values for the parameters in the SQL statement
            pst.setString(1, loginUsername);
            pst.setString(2, loginPassword);
            // Execute the SQL statement to insert the user login details
            pst.executeUpdate();

            // Display a success message
            System.out.println("User Login successfully!");
        } catch (SQLException e) {
            // Handle SQL exceptions
            e.printStackTrace(); // Print the stack trace for other types of SQLExceptions
        } 
    }

    // Method to check if any user is currently logged in
    public boolean loginIsExist() throws SQLException {
        // Get the count of logged-in users from the 'logindp' table
        int logined = Reservation.getLoginCount();
        // Check if any user is logged in
        if (logined == 0) {
            return true;
        } else {
            return false;
        }
    }

    // Method to log out the current user by truncating the 'logindp' table
    public static void logOut() {
        // SQL query to truncate the 'logindp' table
        String query = "truncate table logindp";
        Connection con = null;
        PreparedStatement pst = null;

        try {
            // Establish a database connection
            con = DBConnection.getConnection();
            // Prepare the SQL statement
            pst = con.prepareStatement(query);
            // Execute the SQL statement to truncate the 'logindp' table
            pst.executeUpdate();

            // Display a success message
            System.out.println("User LogOut successfully!");
        } catch (SQLException e) {
            // Handle SQL exceptions
            e.printStackTrace(); // Print the stack trace for other types of SQLExceptions
        } 
    }
    public static void displayLoginUserInfo() throws SQLException {
   	 // SQL query to select all columns from the 'bus' table
       String query = "Select * from logindp";
       
       // Establish a database connection
       Connection con = DBConnection.getConnection();
       Statement st = con.createStatement();
       
       // Execute the SQL query
       ResultSet rs = st.executeQuery(query);
       System.out.println("Registered users are:");
       
       // Display bus information for each bus in the result set
       while (rs.next()) {
       	System.out.println("Currently logined user name:" + rs.getString(1));
       }
   }
}
