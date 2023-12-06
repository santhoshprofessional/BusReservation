// Import necessary packages for working with SQL and database connections
package com.pirai.busreservation;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

// Class definition for the Admin entity
public class Admin {
    // Member variables to store admin information
    public String adminname;
    public String password;
    public String adminPhNumber;
    public String adminEmail;

    // Constructor to initialize an Admin object with the given details
    public Admin(String adminname, String password, String adminPhNumber, String adminEmail) {
        this.adminname = adminname;
        this.password = password;
        this.adminPhNumber = adminPhNumber;
        this.adminEmail = adminEmail;
    }

    // Method to register a new admin in the database
    public void registerAdmin() throws SQLException {
        // SQL query to insert admin details into the 'admin' table
        String query = "insert into admin values(?,?,?,?)";

        try {
            // Establish a database connection
            Connection con = DBConnection.getConnection();
            
            // Create a prepared statement with the SQL query
            PreparedStatement pst = con.prepareStatement(query);
            
            // Set the values of parameters in the prepared statement
            pst.setString(1, adminname);
            pst.setString(2, password);
            pst.setString(3, adminPhNumber);
            pst.setString(4, adminEmail);
            
            // Execute the SQL update
            pst.executeUpdate();
            
            // Display a success message
            System.out.println("Admin registered successfully!");
        } catch (SQLException e) {
            // Handle SQL exceptions
            if (e instanceof java.sql.SQLIntegrityConstraintViolationException) {
                // Specific handling for a constraint violation (e.g., unique key violation)
                System.out.println("Admin already exists. Try Another name.");
            } else {
                // Print the stack trace for other types of SQLExceptions
                e.printStackTrace();
            }
        }
    }

    // Method to delete an admin account from the database
    public static void delAdminAcc(String delName) {
        // SQL query to delete an admin account based on the admin name
        String query = "delete from admin where adminName = ?";
        
        // Initialize database connection and prepared statement
        Connection con = null;
        PreparedStatement pst = null;

        try {
            // Establish a database connection
            con = DBConnection.getConnection();
            
            // Create a prepared statement with the SQL query
            pst = con.prepareStatement(query);
            
            // Set the value of the parameter in the prepared statement
            pst.setString(1, delName);
            
            // Execute the SQL update
            pst.executeUpdate();

            // Display a success message
            System.out.println("Admin Account Deleted successfully!");
        } catch (SQLException e) {
            // Handle SQL exceptions
            e.printStackTrace(); // Print the stack trace for other types of SQLExceptions
        } 
    }
}
