import java.io.*;

public class Menu {
   public static void mainMenu(int accessLevel) throws FileNotFoundException {    
      
      boolean quit = false;
      
      do {
         System.out.println("Please select an option:\n");
         System.out.println("[1] To Manage Bookings");
         System.out.println("[2] To Manage Guests");
         System.out.println("[3] To Manage Staff");
         System.out.println("[4] To Manage Rooms");
         System.out.println("[0] To Save and Quit");
         
         System.out.print("\nYour choice: ");
         int selection = ScannerReaders.readerInt();
         System.out.println();

         switch (selection) {
            case 1:
               System.out.println("You have chosen \"Manage Bookings\"");
               System.out.println("[1] To Show Bill For Booking");
               System.out.println("[2] To Create Booking");
               System.out.println("[3] To Update Booking");
               System.out.println("[4] To Delete Booking");
                  int selection1 = ScannerReaders.readerInt();
                  switch (selection1) {
                     case 1:
                        Manage.printBooking(Search.sBookingMenu(HotelPlazaMain.roomArray));
                        System.out.println();
                        break;
                     case 2:
                        Manage.createBooking();
                        break;
                     case 3:
                        Manage.updateBooking();
                        break;
                     case 4 :
                        Manage.deleteBooking();
                        break;
                     default:
                     System.out.println("The selection is invalid");
                     break;
                  }
                  FilesRelated.saveToFile(HotelPlazaMain.roomArray, 3);
            break;
               
            case 2:
               System.out.println("You have chosen \"Manage Guests\"");
               System.out.println("[1] To Show All Guests");
               System.out.println("[2] To Create Guest");
               System.out.println("[3] To Update Guest");
               System.out.println("[4] To Delete Guest");
               
               System.out.print("\nYour choice: ");
               int selection2 = ScannerReaders.readerInt();
               System.out.println();
                  
                  switch (selection2) {
                     case 1:
                        Manage.printGuest(Manage.toShowWholeArray(HotelPlazaMain.guestArray));
                        System.out.println();
                        break;
                     case 2:
                        Manage.createGuest();
                        break;
                     case 3:
                        Manage.updateGuest();
                        break;
                     case 4 :
                        Manage.deleteGuest();
                        break;
                     default:
                     System.out.println("The selection is invalid");               
                     break;
                  }
                  FilesRelated.saveToFile(HotelPlazaMain.guestArray, 1);
            break;
            
            case 3:
               if (accessLevel != 2) {
                  System.out.println("You do not have access to this command.\nChoose something else.");
                  break;
               }
               System.out.println("You have chosen \"Manage Staff\"");
               System.out.println("[1] To Show All Staff");
               System.out.println("[2] To Create Staff");
               System.out.println("[3] To Update Staff");
               System.out.println("[4] To Delete Staff"); 
               
               System.out.print("\nYour choice: ");   
               int selection3 = ScannerReaders.readerInt();
               System.out.println();
               
                  switch (selection3) {
                     case 1:
                        Manage.printEmployee(Manage.toShowWholeArray(HotelPlazaMain.employeeArray));
                        System.out.println();
                        break;
                     case 2:
                        Manage.createEmployee();
                        break;
                     case 3:
                        Manage.updateEmployee();
                        break;
                     case 4 :
                        Manage.deleteEmployee();
                        break;
                     default:
                     System.out.println("The selection is invalid");
                     break;
                  }
                  FilesRelated.saveToFile(HotelPlazaMain.employeeArray, 2);
            break;
            
            case 4:
               if (accessLevel != 2) {
                  System.out.println("You do not have access to this command.\nChoose something else.");
                  break;
               }
               System.out.println("You have chosen \"Manage Rooms\"");
               System.out.println("[1] To Show All Rooms");
               System.out.println("[2] To Create Room");
               System.out.println("[3] To Update Room");
               System.out.println("[4] To Delete Room");
               
               System.out.print("\nYour choice: ");
               int selection4 = ScannerReaders.readerInt();
               System.out.println();
               
               switch (selection4) {
                     case 1:
                        System.out.println();
                        Manage.printRoom(Manage.toShowWholeArray(HotelPlazaMain.roomArray));
                        break;
                     case 2:
                        Manage.createRoom();
                        break;
                     case 3:
                        Manage.updateRoom();
                        break;
                     case 4 :
                        Manage.deleteRoom();
                        break;
                     default:
                     System.out.println("The selection is invalid");
                     break;
                  }
                  FilesRelated.saveToFile(HotelPlazaMain.roomArray, 3);
            break;
            
            case 0:
               System.out.println("Goodbye! Have a nice day!");
               quit = true;
               break;
            default:
               System.out.println("The selection is invalid");
               break;
         }
               
      } while (!quit);
   }
}