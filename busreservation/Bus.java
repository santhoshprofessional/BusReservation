// Import necessary packages for working with SQL, database connections, and Date
package com.pirai.busreservation;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

// Import the DBConnection class from the 'testing' package
import testing.DBConnection;

// Class definition for handling Bus-related operations
class Bus {
    // Private member variables to store bus details
    private int busNumber;
    private String route;
    private int capacity;
    private Date date;

    // Constructor to initialize the Bus object with given details
    public Bus(int busNumber, String route, int capacity, Date date) {
        this.busNumber = busNumber;
        this.route = route;
        this.capacity = capacity;
        this.date = date;
    }

    // Method to add a new bus to the database
    public void addBus() throws SQLException {
        // SQL query to insert a new bus into the 'bus' table
        String query = "insert into bus values(?,?,?,?)";
        
        // Establish a database connection
        Connection con = DBConnection.getConnection();
        PreparedStatement pst = con.prepareStatement(query);

        // Set values of parameters in the prepared statement
        pst.setInt(1, busNumber);
        pst.setString(2, route);
        pst.setInt(3, capacity);
        java.sql.Date sqldate = new java.sql.Date(date.getTime());
        pst.setDate(4, sqldate);

        // Execute the SQL query to add the bus
        pst.executeUpdate();
        System.out.println("Bus added successfully!");
    }

    // Static method to delete a bus from the database
    public static void deleteBus(int deleteBus) throws SQLException {
        int dBus = deleteBus;
        // SQL query to delete a bus from the 'bus' table
        String query = "delete from bus where busNumber = ?";
        
        // Establish a database connection
        Connection con = DBConnection.getConnection();
        PreparedStatement pst = con.prepareStatement(query);
        
        // Set the value of the parameter in the prepared statement
        pst.setInt(1, dBus);

        // Execute the SQL query to delete the bus
        pst.executeUpdate();
        System.out.println("Bus deleted successfully!");
    }

    // Static method to display information about all buses in the database
    public static void displayBusInfo() throws SQLException {
        // SQL query to select all columns from the 'bus' table
        String query = "SELECT * FROM bus order by busNumber;";
        
        // Establish a database connection
        Connection con = DBConnection.getConnection();
        Statement st = con.createStatement();
        
        // Execute the SQL query
        ResultSet rs = st.executeQuery(query);
        System.out.println("=====Available Buses are:=====");
        
        // Display bus information for each bus in the result set
        while (rs.next()) {
            System.out.println("Bus No     : " + rs.getInt(1));
            System.out.println("Destination:" + rs.getString(2));
            System.out.println("Capacity   : " + rs.getInt(3));
            System.out.println("Date       : " + rs.getDate(4));
            System.out.println("------------------------------");
        }
    }

    // Static method to check if a bus with a specific number exists in the database
    public static boolean deleteCheck(int busno) throws SQLException {
        int delCheckBusNo = busno;
        // SQL query to select all columns from the 'bus' table where busNumber matches the given number
        String query = "SELECT * FROM bus WHERE busNumber = ?";
        
        // Establish a database connection
        Connection con = DBConnection.getConnection();
        PreparedStatement pst = con.prepareStatement(query);
        
        // Set the value of the parameter in the prepared statement
        pst.setInt(1, delCheckBusNo);
        
        // Execute the SQL query
        ResultSet rs = pst.executeQuery();

        // Check if a matching bus is found in the result set
        if (rs.next()) {
            int returnedNum = rs.getInt("busNumber");
            return delCheckBusNo == returnedNum;
        } else {
            return false; // No matching bus found
        }
    }
}
