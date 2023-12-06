// Specify the package for the current Java file
package com.pirai.busreservation;

// Class representing messages and menus for the Bus Reservation System
public class Messages {

    // Method to display the initial menu for choosing between ADMIN, USER, or EXIT
    public static void firstMsg() {
        System.out.println("  Bus Reservation System App");
        System.out.println(" ____________________________");
        System.out.println("|           WELCOME          |");
        System.out.println("|------------HOME------------|");
        System.out.println("|          1.ADMIN           |");
        System.out.println("|          2.USER            |");
        System.out.println("|          3.EXIT            |");
        System.out.println("|____________________________|");
        System.out.println();
        System.out.println(" Enter your choice: ");
    }

    // Method to display the menu for ADMIN operations
    public static void adminMsg() {
        System.out.println(" ____________________________");
        System.out.println("|          WELCOME           |");
        System.out.println("|---------ADMIN PAGE---------|");
        System.out.println("|1.   Create Admin Account   |");
        System.out.println("|2.   Admin Login            |");
        System.out.println("|3.   Add New Bus            |");
        System.out.println("|4.   View Available Buses   |");
        System.out.println("|5.   View Bookings     -->  |");
        System.out.println("|6.   View user details -->  |");
        System.out.println("|7.   Remove Bus             |");
        System.out.println("|8.   Admin Logout           |");
        System.out.println("|9.   Delete Admin Account   |");
        System.out.println("|        ____________        |");
        System.out.println("|10.----|GO HOME PAGE|-------|");
        System.out.println("|____________________________|");
        System.out.println();
        System.out.println(" Enter your choice: ");
    }

    // Method to display the menu for viewing individual or overall bus information in ADMIN mode
    public static void adminViewMsg() {
        System.out.println(" ______________________________________");
        System.out.println("|                WELCOME               |");
        System.out.println("|---------BOOKINGS DETAILS PAGE--------|");
        System.out.println("|1. Individual Bus Booking information |");
        System.out.println("|2. Overall Bus Booking information    |");
        System.out.println("|             _____________            |");
        System.out.println("|3.----------|GO ADMIN PAGE|-----------|");
        System.out.println("|______________________________________|");
        System.out.println();
        System.out.println(" Enter your choice: ");
    }

    // Method to display the menu for USER operations
    public static void userMsg() {
        System.out.println(" ____________________________");
        System.out.println("|          WELCOME           |");
        System.out.println("|---------USER PAGE----------|");
        System.out.println("|1.   Create Account         |");
        System.out.println("|2.   User Login             |");
        System.out.println("|3.   Reserve Booking        |");
        System.out.println("|4.   View Available Buses   |");
        System.out.println("|5.   View Bookings          |");
        System.out.println("|6.   Cancel Booking         |");
        System.out.println("|7.   User Logout            |");
        System.out.println("|8.   Delete Account         |");
        System.out.println("|        ____________        |");
        System.out.println("|9.-----|GO HOME PAGE|-------|");
        System.out.println("|____________________________|");
        System.out.println();
        System.out.println(" Enter your choice: ");
    }
    public static void userInfo() {
    	System.out.println(" ____________________________");
    	System.out.println("|          WELCOME           |");
        System.out.println("|-----USER DETAILS PAGE------|");
    	System.out.println("|1.   view Registered user   |");
    	System.out.println("|2.   view Logined user      |");
    	System.out.println("|         _____________      |");
        System.out.println("|3.-----|GO ADMIN PAGE|------|");
        System.out.println("|____________________________|");
        System.out.println();
        System.out.println(" Enter your choice: ");
    }
}
