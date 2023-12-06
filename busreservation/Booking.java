// Import necessary packages for working with SQL and database connections
package com.pirai.busreservation;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

// Class definition for handling booking-related operations
public class Booking {

    // Method to get the count of booked seats for a specific bus
    public static int getBookedCount(int busNo) throws SQLException {
        // SQL query to count the booked seats for a specific bus
        String query = "SELECT count(passengerName) FROM booking WHERE busNumber=?";
        
        // Establish a database connection
        Connection con = DBConnection.getConnection();
        PreparedStatement pst = con.prepareStatement(query);
        
        // Set the value of the parameter in the prepared statement
        pst.setInt(1, busNo);
        
        // Execute the SQL query
        ResultSet rs = pst.executeQuery();
        
        // Move to the first row in the result set and retrieve the count
        rs.next();
        return rs.getInt(1);
    }

    // Method to get the capacity of a specific bus
    public static int getCapacity(int busNumber) throws SQLException {
        // SQL query to select the capacity of a specific bus from the 'bus' table
        String query = "SELECT capacity FROM bus WHERE busNumber=" + busNumber;
        
        // Establish a database connection
        Connection con = DBConnection.getConnection();
        Statement st = con.createStatement();
        
        // Execute the SQL query
        ResultSet rs = st.executeQuery(query);
        
        // Move to the first row in the result set and retrieve the capacity
        rs.next();
        return rs.getInt(1);
    }

    // Method to add a booking for a specific seat on a bus
    public static void addBooking(int seatno, String pName, String pNumber, int busNo) throws SQLException {
        // Extract parameters for the booking
        int seatNumber = seatno;
        String passengerName = pName;
        String phoneNumber = pNumber;
        int busNumber = busNo;
        
        // SQL query to insert a new booking into the 'booking' table
        String query = "INSERT INTO booking VALUES(?,?,?,?)";
        
        // Establish a database connection
        Connection con = DBConnection.getConnection();
        PreparedStatement pst = con.prepareStatement(query);
        
        // Set values of parameters in the prepared statement
        pst.setInt(1, seatNumber);
        pst.setString(2, passengerName);
        pst.setString(3, phoneNumber);
        pst.setInt(4, busNumber);
        
        // Execute the SQL query to add the booking
        pst.executeUpdate();
    }

    // Method to display booking information for a specific bus
    public static void displayBookingInfo(int dispBusNumber) throws SQLException {
        // Extract the bus number for which booking information is displayed
        int dNumber = dispBusNumber;
        
        // Get the count of booked seats and capacity for the bus
        int bookedCount = getBookedCount(dNumber);
        int capacity = getCapacity(dNumber);
        
        // Display total, booked, and available seats information
        System.out.println("----------------------------------");
        System.out.println("Bus number     :"+dNumber);
        System.out.println("Total seats    :" + capacity);
        System.out.println("Booked Seats   :" + bookedCount);
        System.out.println("Available Seats:" + (capacity - bookedCount));
        System.out.println("=====Booked seats information:=====");
        
        // SQL query to select seatNumber and passengerName for the given bus
        String query = "SELECT seatNumber,passengerName FROM booking WHERE busNumber=? order by seatNumber";
        
        // Establish a database connection
        Connection con = DBConnection.getConnection();
        PreparedStatement pst = con.prepareStatement(query);
        
        // Set the value of the parameter in the prepared statement
        pst.setInt(1, dNumber);
        
        // Execute the SQL query
        ResultSet rs = pst.executeQuery();
        
        // Display seatNumber and passengerName for each booking
        while (rs.next()) {
            System.out.println("Seat Number    :" + rs.getInt(1));
            System.out.println("Passenger Name :" + rs.getString(2));
            System.out.println("---------------------------------");
        }
    }

    // Method to display booking information for all buses (admin view)
    public static void displayBookingInfoAdmin() throws SQLException {
        // SQL query to select all columns from the 'booking' table
        String query = "SELECT * FROM booking";
        
        // Establish a database connection
        Connection con = DBConnection.getConnection();
        Statement st = con.createStatement();
        
        // Execute the SQL query
        ResultSet rs = st.executeQuery(query);
        System.out.println("---------------------------------");
        // Display seatNumber, passengerName, passengerPhoneNumber, and busNumber for each booking
        while (rs.next()) {
            System.out.println("Seat Number           : " + rs.getInt(1));
            System.out.println("Passenger Name        :" + rs.getString(2));
            System.out.println("Passenger Phone Number:" + rs.getString(3));
            System.out.println("busNumber is          : " + rs.getInt(4));
            System.out.println("---------------------------------");
        }
    }

    // Method to get the count of a specific seat on a specific bus
    public static int getSeatNumber(int bu, int se) throws SQLException {
        // Extract parameters for seat and bus
        int busNumber = bu;
        int seatNumber = se;
        
        // SQL query to count the occurrences of a specific seat on a specific bus
        String query = "SELECT count(seatNumber) FROM booking WHERE busNumber=? AND seatNumber=?";
        
        // Establish a database connection
        Connection con = DBConnection.getConnection();
        PreparedStatement pst = con.prepareStatement(query);
        
        // Set values of parameters in the prepared statement
        pst.setInt(1, busNumber);
        pst.setInt(2, seatNumber);
        
        // Execute the SQL query
        ResultSet rs = pst.executeQuery();
        
        // Move to the first row in the result set and retrieve the count
        rs.next();
        return rs.getInt(1);
    }

    // Method to cancel a booking for a specific seat on a specific bus
    public static void cancelBooking(int seatno, int busNo) throws SQLException {
        // Extract parameters for seat and bus
        int seatNumber = seatno;
        int busNumber = busNo;
        
        // SQL query to delete a booking for a specific seat on a specific bus
        String query = "DELETE FROM booking WHERE seatNumber=? AND busNumber=?";
        
        // Establish a database connection
        Connection con = DBConnection.getConnection();
        PreparedStatement pst = con.prepareStatement(query);
        
        // Set values of parameters in the prepared statement
        pst.setInt(1, seatNumber);
        pst.setInt(2, busNumber);
        
        // Execute the SQL query to cancel the booking
        pst.executeUpdate();
    }
}
