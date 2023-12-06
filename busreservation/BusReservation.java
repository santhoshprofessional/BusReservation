// Specify the package for the current Java file
package com.pirai.busreservation;

import java.sql.SQLException;

// Class responsible for handling bus reservations
public class BusReservation {
    
    // Check if seats are available on a bus
    public static boolean isAvailable(int busNumber) throws SQLException {
        int busNo = busNumber;
        // Get the capacity and booked count for the specified bus
        int capacity = Booking.getCapacity(busNo);
        int booked = Booking.getBookedCount(busNo);

        // Return true if there are available seats (booked count is less than capacity)
        return booked < capacity;
    }

    // Check if a specific seat number is available on a bus
    public static boolean seatNumberCheck(int bus, int seat) throws SQLException {
        int busno = bus;
        int seatno = seat;
        
        // Get the count of seats with the specified seat number on the specified bus
        int seatnumber = Booking.getSeatNumber(busno, seatno);
        
        // Return true if the seat is available (count is 0)
        if (seatnumber == 0) {
            return true;
        } else {
            return false;
        }
    }
}
