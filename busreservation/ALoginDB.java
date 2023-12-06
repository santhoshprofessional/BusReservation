// Import necessary packages for working with SQL and database connections
package com.pirai.busreservation;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

// Class definition for handling Admin logins and logouts
public class ALoginDB {

    // Member variables to store admin login credentials
    public String AloginUsername;
    public String AloginPassword;

    // Constructor to initialize an ALoginDB object with the given login credentials
    public ALoginDB(String AloginUsername, String AloginPassword) {
        this.AloginUsername = AloginUsername;
        this.AloginPassword = AloginPassword;
    }

    // Method to record an admin login in the database
    public void LoginAdmin() {
        // SQL query to insert admin login details into the 'alogindp' table
        String query = "insert into alogindp values(?,?)";
        
        // Initialize database connection and prepared statement
        Connection con = null;
        PreparedStatement pst = null;

        try {
            // Establish a database connection
            con = DBConnection.getConnection();
            
            // Create a prepared statement with the SQL query
            pst = con.prepareStatement(query);
            
            // Set the values of parameters in the prepared statement
            pst.setString(1, AloginUsername);
            pst.setString(2, AloginPassword);
            
            // Execute the SQL update
            pst.executeUpdate();

            // Display a success message
            System.out.println("Admin Login successfully!");
        } catch (SQLException e) {
            // Handle SQL exceptions
            e.printStackTrace(); // Print the stack trace for other types of SQLExceptions
        }
    }

    // Method to check if an admin login already exists
    public boolean AloginIsExist() throws SQLException {
        // Get the count of admin logins from the 'AReservation' class
        int logined = AReservation.getALoginCount();
        
        // Check if there are no existing admin logins
        if (logined == 0) {
            return true;
        } else {
            return false;
        }
    }

    // Method to perform admin logout by truncating the 'alogindp' table
    public static void AlogOut() {
        // SQL query to truncate the 'alogindp' table
        String query = "truncate table alogindp";
        
        // Initialize database connection and prepared statement
        Connection con = null;
        PreparedStatement pst = null;

        try {
            // Establish a database connection
            con = DBConnection.getConnection();
            
            // Create a prepared statement with the SQL query
            pst = con.prepareStatement(query);
            
            // Execute the SQL update
            pst.executeUpdate();

            // Display a success message
            System.out.println("Admin LogOut successfully!");
        } catch (SQLException e) {
            // Handle SQL exceptions
            e.printStackTrace(); // Print the stack trace for other types of SQLExceptions
        } 
    }
}
