// Import necessary packages for working with SQL and database connections
package com.pirai.busreservation;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

// Class definition for handling admin reservations and logins
public class AReservation {

    // Method to check if admin login credentials are valid
    public static boolean AloginCheck(String checkName, String checkPassword) throws SQLException {
        // Extract given admin name and password
        String givenName = checkName;
        String givenPassword = checkPassword;

        // SQL query to check if admin credentials exist in the 'admin' table
        String query = "SELECT * FROM admin WHERE adminName = ? AND passwordd = ?";
        
        // Establish a database connection
        Connection con = DBConnection.getConnection();
        PreparedStatement pst = con.prepareStatement(query);
        
        // Set the values of parameters in the prepared statement
        pst.setString(1, checkName);
        pst.setString(2, checkPassword);
        
        // Execute the SQL query
        ResultSet rs = pst.executeQuery();

        // Check if there is a matching admin in the result set
        if (rs.next()) {
            // Retrieve admin name and password from the result set
            String returnedName = rs.getString("adminName");
            String returnedPassword = rs.getString("passwordd");

            // Compare given credentials with returned credentials
            return givenName.equalsIgnoreCase(returnedName) && givenPassword.equalsIgnoreCase(returnedPassword);
        } else {
            return false; // No matching admin found
        }
    }

    // Method to get the count of admin logins from the 'alogindp' table
    public static int getALoginCount() throws SQLException {
        // Establish a database connection
        Connection con = null;
        String query = "SELECT COUNT(*) FROM alogindp";
        con = DBConnection.getConnection();
        Statement stmt = con.createStatement();
        
        // Execute the SQL query
        ResultSet rs = stmt.executeQuery(query);
        
        // Move to the first row in the result set and retrieve the count
        rs.next();
        return rs.getInt(1);
    }

    // Method to check if admin credentials are valid for deletion
    public static boolean adeleteCheck(String dcheckName, String dcheckPassword) throws SQLException {
        // Extract given admin name and password
        String givenName = dcheckName;
        String givenPassword = dcheckPassword;

        // SQL query to check if admin credentials exist in the 'admin' table
        String query = "SELECT * FROM admin WHERE adminName = ? AND passwordd = ?";
        
        // Establish a database connection
        Connection con = DBConnection.getConnection();
        PreparedStatement pst = con.prepareStatement(query);
        
        // Set the values of parameters in the prepared statement
        pst.setString(1, dcheckName);
        pst.setString(2, dcheckPassword);
        
        // Execute the SQL query
        ResultSet rs = pst.executeQuery();

        // Check if there is a matching admin in the result set
        if (rs.next()) {
            // Retrieve admin name and password from the result set
            String returnedName = rs.getString("adminName");
            String returnedPassword = rs.getString("passwordd");

            // Compare given credentials with returned credentials
            return givenName.equalsIgnoreCase(returnedName) && givenPassword.equalsIgnoreCase(returnedPassword);
        } else {
            return false; // No matching admin found
        }
    }
}
