// Specify the package for the current Java file
package com.pirai.busreservation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

// Class representing reservation-related operations
public class Reservation {

    // Method to check login credentials for a user
    public static boolean loginCheck(String checkName, String checkPassword) throws SQLException {
        // Extracted input credentials
        String givenName = checkName;
        String givenPassword = checkPassword;

        // SQL query to check if the user exists in the 'user' table
        String query = "SELECT * FROM user WHERE username = ? AND passwordd = ?";
        Connection con = DBConnection.getConnection();
        PreparedStatement pst = con.prepareStatement(query);
        pst.setString(1, checkName);
        pst.setString(2, checkPassword);
        ResultSet rs = pst.executeQuery();

        // Check if the user exists and provided credentials match
        if (rs.next()) {
            String returnedName = rs.getString("username");
            String returnedPassword = rs.getString("passwordd");

            return givenName.equalsIgnoreCase(returnedName) && givenPassword.equalsIgnoreCase(returnedPassword);
        } else {
            return false; // No matching user found
        }
    }

    // Method to get the count of login entries in the 'logindp' table
    public static int getLoginCount() throws SQLException {
        Connection con = null;
        String query = "select count(*) from logindp";
        con = DBConnection.getConnection();
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        rs.next();
        return rs.getInt(1);
    }

    // Method to check credentials for user deletion
    public static boolean deleteCheck(String dcheckName, String dcheckPassword) throws SQLException {
        // Extracted input credentials
        String givenName = dcheckName;
        String givenPassword = dcheckPassword;

        // SQL query to check if the user exists in the 'user' table
        String query = "SELECT * FROM user WHERE username = ? AND passwordd = ?";
        Connection con = DBConnection.getConnection();
        PreparedStatement pst = con.prepareStatement(query);
        pst.setString(1, dcheckName);
        pst.setString(2, dcheckPassword);
        ResultSet rs = pst.executeQuery();

        // Check if the user exists and provided credentials match
        if (rs.next()) {
            String returnedName = rs.getString("username");
            String returnedPassword = rs.getString("passwordd");

            return givenName.equalsIgnoreCase(returnedName) && givenPassword.equalsIgnoreCase(returnedPassword);
        } else {
            return false; // No matching user found
        }
    }
}
