import java.util.*;
import java.io.*;

class HotelPlazaMain {
   static ArrayList<Employee>employeeArray = new ArrayList<Employee>(); //hold list of employees 
   static ArrayList<Guest>guestArray = new ArrayList<Guest>(); //hold list of guests
   static ArrayList<Room>roomArray = new ArrayList<Room>(); //hold list of rooms
   static ArrayList<Booking>bookingArray = new ArrayList<Booking>(); //hold list of booking
   static ArrayList<String> daysForBooking=new ArrayList<String>(); //hold dates (1 1 2020, 2 1 2020...)
   static ArrayList<String> availableRoomss=new ArrayList<String>(); // hold roomID related to the index of daysForBooking
   static int []months={31,28,31,30,31,30,31,31,30,31,30,31};
   
   public static void main(String[] args) throws FileNotFoundException {
      //Filling up arrays
      FilesRelated.fileToArray(guestArray, 1);
      FilesRelated.fileToArray(employeeArray, 2);
      FilesRelated.fileToArray(roomArray, 3);

      //Password check
      Calendar.dates(1000);
      int accessLevel = Password.passwordCheck();
      if (accessLevel != 0) {
         System.out.println("Welcome to the Hotel Plaza System!");
         System.out.println();
         Menu.mainMenu(accessLevel);
      }
      else {
         System.out.println("Unauthorized access! System shutting down...");
      }
      
      FilesRelated.saveToFile(guestArray, 1);
      FilesRelated.saveToFile(employeeArray, 2);
      FilesRelated.saveToFile(roomArray, 3);
   }
}