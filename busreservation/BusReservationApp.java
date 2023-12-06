// Import necessary packages
package com.pirai.busreservation;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
//The MainClass contains the main method and serves as the entry point for the Bus Reservation System application.

/*@author  santhosh
* @version 06-12-2023
* @code    Java Program for Bus Reservation System App
* Admin Features:
Admin Registration:->
-Admins can register by providing a username, password, phone number, and email. Password, phone number, and email formats are validated.
Admin Login:->
-Admins can log in using their credentials. Successful login is verified through the database.
Add Bus:->
-Admins can add buses with details such as bus number, destination, total seats, and date. Validation checks ensure uniqueness and format.
Display Bus Information:->
-Admins can view information about buses, such as bus number, destination, total seats, and date.
View Bookings:->
-Admins can view bookings, including seat number, passenger name, and phone number.
View User Details:->
-Admins can view details of registered users and currently logged-in users.
Remove Bus:->
-Admins can remove buses after validating the availability of the bus and checking if it exists.
Admin Logout:->
-Admins can log out of the system.
Delete Admin Account:->
-Admins can delete their accounts after validating their credentials.

User Features:
User Registration:->
-Users can register by providing a username, password, phone number, and email. Password, phone number, and email formats are validated.
User Login:->
-Users can log in using their credentials. Successful login is verified through the database.
Book Seat:->
-Users can book seats by providing bus number, seat number, passenger name, and phone number. Validation checks ensure seat availability and proper phone number format.
Display Bus Information:->
-Users can view information about buses, such as bus number, destination, total seats, and date.
View Booking Information:->
-Users can view booking information for a specific bus, including seat number, passenger name, and phone number.
Cancel Booking:->
-Users can cancel bookings by providing the bus number and seat number. Validation checks ensure the seat is booked.
User Logout:->
-Users can log out of the system.
Delete User Account:->
-Users can delete their accounts after validating their credentials.

General Features:
Exception Handling:->
-The code incorporates exception handling for SQL-related operations and parsing exceptions, ensuring graceful error handling.
User Interface Messages:->
-Messages are displayed at various stages to guide users through the application and provide feedback.
Date Parsing:->
-The code uses SimpleDateFormat to parse date inputs, ensuring correct processing of dates provided by users.
Database Operations:->
-The application interacts with a database to perform various operations, including registering admins and users, adding buses, handling bookings, and retrieving information for display.
*/
public class BusReservationApp {
	// Main class for the Bus Reservation application
	public static void main(String[] args) {
		// Scanner for user input
		Scanner scanner = new Scanner(System.in);
		// Variables to store admin and user login information
        String loginAdimnname = null;
        String regAdminname = null;
        String loginUsername = null;
        String regUsername = null;
        int busNumber = 0;
        int busnumber = 0;
        // Variables for menu choices
        int choice,innerChoice1,innerChoice2;
     // Main loop for the application
        do {
        	// Display the first menu
        	Messages.firstMsg();
            choice = scanner.nextInt();

            // Switch statement to handle user's main menu choice
            switch (choice) {
                case 1:
                	do {
                		// Display admin menu
                		Messages.adminMsg();
                        innerChoice1 = scanner.nextInt();

                        // Switch statement to handle user menu choices
                        // Admin menu
                        switch (innerChoice1) {                  
                            case 1:
                                // Register Admin
                                System.out.print("Enter a adminname: ");
                                regAdminname = scanner.next();
                                System.out.print("Enter a password: ");
                                String regAdPassword = scanner.next();
                                String passwordRegex = "^[A-Z][a-z\\d]{5,8}$";
                                /*  ^: Asserts the start of the string.
								[A-Z]: Matches an uppercase letter at the beginning.
						 [a-z\d]{5,7}: Matches a combination of lowercase letters and digits, with a minimum length of 5 and a maximum length of 7.
									$: Asserts the end of the string.*/

                                // Validate the password
                                if (!regAdPassword.matches(passwordRegex)) {
                                    System.out.println("Invalid password format. Please follow the password requirements.");
                                    System.out.println("It should be 6 to 8 charactors.");
                                    System.out.println("Begin with uppercase letters.");
                                    System.out.println("Rest of charactors combination of lowercase letters, numbers.");
                                    continue;
                                }

                                System.out.print("Enter a admin phone number: ");
                                String regAdminPhNumber = scanner.next();
                                String phoneRegex = "^[6-9]\\d{9}$";
                                /*^: Asserts the start of the string.
							  [6-9]: Matches a single digit that is either 6, 7, 8, or 9 at the beginning.
							 \\d{9}: Matches exactly 9 digits (0-9).
								  $: Asserts the end of the string*/

                                // Validate the phone number
                                if (!regAdminPhNumber.matches(phoneRegex)) {
                                    System.out.println("Invalid phone number format. Please enter a valid 10-digit number starting with 6, 7, 8, or 9.");
                                    continue;
                                }

                                System.out.print("Enter a admin email-address: ");
                                String regAdminEmail = scanner.next();
                                String emailRegex = "^[a-zA-Z0-9._&+-]+@[a-zA-Z.-]+\\.[a-zA-Z]{2,3}$";
                                /*^: Asserts the start of the string.
  							  [a-zA-Z0-9._&+-]+: Matches one or more of the allowed characters before the @ symbol.
  											  @: Matches the @ symbol.
  									[a-zA-Z.-]+: Matches one or more of the allowed characters after the @ symbol in the domain name.
  											\\.: Escapes the dot (.) because it has a special meaning in regex, and matches the dot in the domain.
  								  [a-zA-Z]{2,3}: Matches the top-level domain (TLD) with 2 to 3 characters.
  											  $: Asserts the end of the string.*/
                                
                                // Validate the email
                                if (!regAdminEmail.matches(emailRegex)) {
                                    System.out.println("Invalid email format. Please enter a valid email address.");
                                    continue;
                                }

                                try {
                                    Admin newAdmin = new Admin(regAdminname, regAdPassword, regAdminPhNumber, regAdminEmail);
                                    newAdmin.registerAdmin();
                                } catch (SQLException e1) {
                                    System.out.println("Invalid information");
                                }
                                break;
                            case 2:
                                // Admin Login
                                
                                try {
                                	
                                		System.out.print("Enter your adminname: ");
                                        loginAdimnname = scanner.next();
                                        System.out.print("Enter your password: ");
                                        String aloginPassword = scanner.next();
                                        if (AReservation.AloginCheck(loginAdimnname, aloginPassword)) {
                                            ALoginDB alogin = new ALoginDB(loginAdimnname, aloginPassword);
                                            if (alogin.AloginIsExist()) {
                                                alogin.LoginAdmin();
                                            } else {
                                                System.out.println("Admin already logged in!");
                                            }
                                        } else {
                                            System.out.println("Invalid Admin");
                                        }
                            		
                                	
                                } catch (SQLException e) {
                                	System.out.println(e.getMessage());
                                }
                                break;
                            case 3:
                            	// Add Bus
                            	int Alogined2 = 0;
                                try {
                                    Alogined2 = AReservation.getALoginCount();
                                } catch (SQLException e1) {
                                	System.out.println(e1.getMessage());
                                }
                                if (Alogined2 == 0) {
                                    System.out.println("Login and then add bus");
                                } else {
                            	System.out.print("Enter bus number: ");
                                 busNumber = scanner.nextInt();
                                System.out.print("Enter Destination: ");
                                String route = scanner.next();
                                System.out.print("Enter total seats: ");
                                int totalSeats = scanner.nextInt();
                                System.out.println("Enter Date:dd-MM-yyyy");
                                String dateInput = scanner.next();
                                SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                                Date date = null;
                                try {
                                    date = dateFormat.parse(dateInput);
                                } catch (ParseException e) {
                                    System.out.println("ParseException is Handled : invalid date formate");
                                }
                                
                                Bus newBus = new Bus(busNumber, route, totalSeats, date);
                                try {
                                	newBus.addBus();
                                } catch (SQLException e1) {
                                	System.out.println("The bus with this number already runnig!");
                                }
                                }
                            break;
                            case 4:// Display Bus Information
                            	try {                            		
                            			Bus.displayBusInfo();
        						} catch (SQLException e) {
        							System.out.println(e.getMessage());
        						}
								break;
                            case 5:// View Bookings
                            	do {                                   
                            		Messages.adminViewMsg();
                                    choice = scanner.nextInt();

                                    // Switch statement to handle user's main menu choice
                                    switch (choice) {
                                        case 1:try {
        										System.out.println("Enter Bus Number to display bus information");
            									int dispBusNumber = scanner.nextInt();
            									Booking.displayBookingInfo(dispBusNumber);        									
        								} catch (SQLException e) {
        									System.out.println("Invalid bus number");
        								}
        								break;
                                        case 2:
                                        	try {           										
            										Booking.displayBookingInfoAdmin();           									
            								} catch (SQLException e) {
            									System.out.println("Invalid bus number");
            								}
            								break;
                                        case 3:
                                            // Exit the application
                                            System.out.println("Thank you! to view");
                                            System.out.println();
                                            break;
                                        default:
                                            System.out.println("Invalid choice. Please enter a valid option.");
                                        	}
                                    } while (choice != 3);
                            break;
                            case 6://view user details
                            	do {                                   
                            		Messages.userInfo();
                                    choice = scanner.nextInt();

                                    // Switch statement to handle user's main menu choice
                                    switch (choice) {
                                        case 1:try {//view registered users details                                        	
                            					User.displayUserInfo();                            				
        								} catch (SQLException e) {
        									System.out.println("Invalid user");
        								}
        								break;
                                        case 2://view currently logined user
                                        	try {                                        		
                                					LoginDB.displayLoginUserInfo();                               				
            								} catch (SQLException e) {
            									System.out.println("Invalid user");
            								}
            								break;
                                        case 3:
                                            // Exit the application
                                            System.out.println("Thank you! to view");
                                            System.out.println();
                                            break;
                                        default:
                                            System.out.println("Invalid choice. Please enter a valid option.");
                                        	}
                                    } while (choice != 3);
                            	break;
                            case 7:// Remove Bus
                            	int Alogined3 = 0;
                                try {
                                    Alogined3 = AReservation.getALoginCount();
                                } catch (SQLException e1) {
                                	System.out.println("Exception handled");
                                }
                                if (Alogined3 == 0) {
                                    System.out.println("Login and then remove bus");
                                } else {
                            	 System.out.print("Enter bus number: ");
                                 int delBusNumber = scanner.nextInt();
                                 try {
        						if(Bus.deleteCheck(delBusNumber)) {
        							Bus.deleteBus(delBusNumber);
        						}else {
        							System.out.println("invalid bus number or no Bus are available to Remove Bus!");
        						}
        						
                                 } catch (SQLException e) {
                                	 System.out.println("invalid bus information");
                                 }
                                }
                            break;
                            case 8:// Admin Logout
                                int Alogined = 0;
                                try {
                                    Alogined = AReservation.getALoginCount();
                                } catch (SQLException e1) {
                                	System.out.println("Exception handled");
                                }
                                if (Alogined == 0) {
                                    System.out.println("No login Exist");
                                } else {
                                    ALoginDB.AlogOut();
                                }
                                break;
                            case 9:// Delete Admin Account
                                int Alogined1 = 0;
                                try {
                                    Alogined1 = AReservation.getALoginCount();
                                } catch (SQLException e1) {
                                	System.out.println("Exception handled");
                                }
                                if (Alogined1 == 1) {
                                    System.out.println("Logout and then delete Account");
                                } else {                                  
                                    try {
                                    		System.out.print("Enter your adminname: ");
                                            String adeletename = scanner.next();
                                            System.out.print("Enter your password: ");
                                            String adeletePassword = scanner.next();
                                            if (AReservation.adeleteCheck(adeletename, adeletePassword)) {
                                                Admin.delAdminAcc(adeletename);
                                            } else {
                                                System.out.println("Invalid Admin");
                                            }
                                    } catch (SQLException e) {
                                       System.out.println("Give a correct information");
                                    }
                                }
                                break;
                            case 10:// Exit admin menu
                                System.out.println("Thank you Admin! please visit again");
                                System.out.println();
                                break;
                            default:
                                System.out.println("Invalid choice. Please enter a valid option.");
                        	}
                    		} while (innerChoice1 != 10);
                			break;
                			case 2:
                				// User menu
                					do {
                						 // Display user menu
                						Messages.userMsg();
                						innerChoice2 = scanner.nextInt();

                						// Switch statement to handle user menu choices
                						switch (innerChoice2) {
                						case 1:
                							// Register User
                							System.out.print("Enter a username: ");
                							regUsername = scanner.next();
                							System.out.print("Enter a password: ");
                							String regPassword = scanner.next();
                							String passwordRegex = "^[A-Z][a-z\\d]{5,8}$";
                						/*      ^: Asserts the start of the string.
											[A-Z]: Matches an uppercase letter at the beginning.
									 [a-z\d]{5,7}: Matches a combination of lowercase letters and digits, with a minimum length of 5 and a maximum length of 7.
												$: Asserts the end of the string.*/

                							// Validate the password
                							if (!regPassword.matches(passwordRegex)) {
                								 System.out.println("Invalid password format. Please follow the password requirements.");
                                                 System.out.println("It should be 6 to 8 charactors.");
                                                 System.out.println("Begin with uppercase letters.");
                                                 System.out.println("Rest of charactors combination of lowercase letters, numbers.");
                								continue;
                							}

                							System.out.print("Enter a user phone number: ");
                							String regUserPhNumber = scanner.next();
                							String phoneRegex = "^[6-9]\\d{9}$";
                							/*^: Asserts the start of the string.
              							  [6-9]: Matches a single digit that is either 6, 7, 8, or 9 at the beginning.
              							 \\d{9}: Matches exactly 9 digits (0-9).
              								  $: Asserts the end of the string*/

                							// Validate the phone number
                							if (!regUserPhNumber.matches(phoneRegex)) {
                								System.out.println("Invalid phone number format. Please enter a valid 10-digit number starting with 6, 7, 8, or 9.");
                								continue;
                							}

                							System.out.print("Enter a user email-address: ");
                							String regUserEmail = scanner.next();
                							String emailRegex = "^[a-zA-Z0-9._&+-]+@[a-zA-Z.-]+\\.[a-zA-Z]{2,3}$";
                							/*^: Asserts the start of the string.
							  [a-zA-Z0-9._&+-]+: Matches one or more of the allowed characters before the @ symbol.
											  @: Matches the @ symbol.
									[a-zA-Z.-]+: Matches one or more of the allowed characters after the @ symbol in the domain name.
											\\.: Escapes the dot (.) because it has a special meaning in regex, and matches the dot in the domain.
								  [a-zA-Z]{2,3}: Matches the top-level domain (TLD) with 2 to 3 characters.
											  $: Asserts the end of the string.*/
                							
                							// Validate the email
                							if (!regUserEmail.matches(emailRegex)) {
                								System.out.println("Invalid email format. Please enter a valid email address.");
                								continue;
                							}

                							try {
                								User newUser = new User(regUsername, regPassword, regUserPhNumber, regUserEmail);
                								newUser.registerUser();
                							} catch (SQLException e1) {
                								System.out.println("give correct information");
                							}
                							break;
                						case 2:
                							// User Login
                							
                							try {               								
                									System.out.print("Enter your username: ");
                        							loginUsername = scanner.next();
                        							System.out.print("Enter your password: ");
                        							String loginPassword = scanner.next();
                    								if (Reservation.loginCheck(loginUsername, loginPassword)) {
                    									LoginDB login = new LoginDB(loginUsername, loginPassword);
                    									if (login.loginIsExist()) {
                    										login.LoginUser();
                    									} else {
                    										System.out.println("User already logged in!");
                    									}
                    								} else {
                    									System.out.println("Invalid user");
                    								}                                       		
                							} catch (SQLException e) {
                								System.out.println("invalid user");
                							}
                							break;
                							case 3:
                								 // Book Seat
                								int loginedA = 0;
                								try {
                									loginedA = Reservation.getLoginCount();
                								} catch (SQLException e1) {
                									System.out.println("Exception handled");
                								}
                								if (loginedA == 0) {
                									System.out.println("No login Exist!");
                								} else {
                									System.out.print("Enter bus number: ");
                									busnumber = scanner.nextInt();
                									System.out.print("Enter Seat number: ");
                									int seatNumber = scanner.nextInt();                         
                									System.out.print("Enter Passenger Name: ");
                									String passengerName = scanner.next();
                									System.out.print("Enter phone number: ");
                									String phoneNumber = scanner.next();
                									String phoneRegexx = "^[6-9]\\d{9}$";
                									// Validate the phone number
                									if (!phoneNumber.matches(phoneRegexx)) {
                										System.out.println("Invalid phone number format. Please enter a valid 10-digit number starting with 6, 7, 8, or 9.");
                										continue;
                									}
                									try {
                										if(BusReservation.seatNumberCheck(busnumber,seatNumber)) {
                											if(BusReservation.isAvailable(busnumber)) {
                												Booking.addBooking(seatNumber,passengerName,phoneNumber,busnumber);
                												System.out.println("Your booking is confirmed");
                											}else {
                												System.out.println("Not seats are available in this bus try another bus!");
                											}
                										}else
                										{
                											System.out.println("This seat is already booked try another seat number");
                										}   						
                									} catch (SQLException e) {
   														System.out.println("not confirmed");
   														System.out.println("invalid bus number provide correct bus number");
                									}
                								}
                								break;
                							case 4: // Display Bus Information
                								try {                                           		
                                            			Bus.displayBusInfo();                                            		
                        							
                        						} catch (SQLException e) {
                        							System.out.println(e.getMessage());
                        						}
                								break;
                							case 5:// View Booking Information
                								
                								try {
                										System.out.println("Enter Bus Number to display bus information");
                    									int dispBusNumber = scanner.nextInt();
                    									Booking.displayBookingInfo(dispBusNumber);
                								} catch (SQLException e) {
                									System.out.println("Invalid bus number");
                								}
                								break;
                							case 6:  // Cancel Booking
                								int loginedC = 0;
                								try {
                									loginedC = Reservation.getLoginCount();
                								} catch (SQLException e1) {
                									System.out.println("Exception handled");
                								}
                								if (loginedC == 0) {
                									System.out.println("No login Exist!");
                								} else {
                									System.out.print("Enter bus number: ");
                									int cancelbusNumber = scanner.nextInt();
                									System.out.print("Enter Seat number: ");
                									int cancelseatNumber = scanner.nextInt();
                									try {
                										if(BusReservation.seatNumberCheck(cancelbusNumber,cancelseatNumber)) {
                											System.out.println("No Seats are availanle to cancel");
                										}
                										else {
                											Booking.cancelBooking(cancelseatNumber,cancelbusNumber );
                											System.out.println("Cancel seat:"+cancelseatNumber);
                										}
                									} catch (SQLException e) {
                										System.out.println("invalid bus number");
                									}
                								}                                      	
                								break;
                							case 7:
                								 // User Logout
                								int logined = 0;
                								try {
                									logined = Reservation.getLoginCount();
                								} catch (SQLException e1) {
                									System.out.println("Exception handled");
                								}
                								if (logined == 0) {
                									System.out.println("No login Exist");
                								} else {
                									LoginDB.logOut();
                								}
                								break;
                							case 8:// Delete User Account
                								int logined1 = 0;
                								try {
                									logined1 = Reservation.getLoginCount();
                								} catch (SQLException e1) {
                									System.out.println("Exception handled");
                								}
                								if (logined1 == 1) {
                									System.out.println("Logout and then delete Account");
                								} else {              									
                									try {
                											System.out.print("Enter your username: ");
                        									String deletename = scanner.next();
                        									System.out.print("Enter your password: ");
                        									String deletePassword = scanner.next();
                    										if (Reservation.deleteCheck(deletename, deletePassword)) {
                    											User.delAcc(deletename);
                    										} else {
                    											System.out.println("Invalid user");
                    										}         										
                									} catch (SQLException e) {
                										System.out.println("invalid");
                									}
                								}
                								break;
                							case 9:
                								// Exit the user menu
                								System.out.println("Thank you User! please visit again");
                								System.out.println();
                								break;
                							default:
                								System.out.println("Invalid choice. Please enter a valid option.");
                						}
                					} while (innerChoice2 != 9);
                					break;
                			case 3:
                				// Exit the application
                				System.out.println("Thank you! please visit again");
                				break;
        		}
        	} while (choice != 3);
        scanner.close();
		}
}
