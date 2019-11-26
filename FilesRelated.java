import java.util.*;
import java.io.*;

public class FilesRelated {

   //SAVE TO FILE
   public static void saveToFile(ArrayList toSave, int fileType)throws FileNotFoundException{ //takes all the arraylist and save it to file
      PrintStream output;
      String fileName = "";
      switch (fileType) {
         case 1:
            fileName = "HotelPlazaGuest.txt";
            break;
         
         case 2:
            fileName = "HotelPlazaEmployee.txt";
            break;
         
         case 3:
            fileName = "HotelPlazaRoom.txt";
            break;
            
         default:
            fileName = "break.txt";
            System.out.println("ERROR: Not correct FileType given.");
            break;
      }
      
      output = new PrintStream(fileName);
      for (int i=0;i<toSave.size();i++){
         output.print(toSave.get(i));
      }
   }
      
      
      
   //FILE TO ARRAY
   public static void fileToArray(ArrayList a, int arrayType) throws FileNotFoundException {
      Scanner readFile;
      String currentObject = "";
      
      switch (arrayType) {
         //Guest
         case 1:
            readFile = new Scanner(new File("HotelPlazaGuest.txt"));
            while (readFile.hasNext()) {
               currentObject = readFile.nextLine();
               a.add(guestProcessLine(currentObject));
            }
            break;
         
         //Employee
         case 2:
            readFile = new Scanner(new File("HotelPlazaEmployee.txt"));
            while (readFile.hasNext()) {
               currentObject = readFile.nextLine();
               a.add(employeeProcessLine(currentObject));
            }
            break;
            
         //Room
         case 3:
            readFile = new Scanner(new File("HotelPlazaRoom.txt"));
            while (readFile.hasNext()) {
               currentObject = readFile.nextLine();
               a.add(roomProcessLine(currentObject));
            }
            break;
         
         default:
            System.out.println("ERROR: No database corresponding to input");
            break;
      }
   } 
   
   
   
   //PROCESSING EMPLOYEE
   public static Employee employeeProcessLine(String currentLine) {
      //Put the employee object from the file (stored as a string)
      //into the scanner and instantiate a placeholder object of Employee type
      Scanner sc = new Scanner(currentLine);
      Employee currentEmployee = new Employee();
      //Assign correct values from the string to the object
      currentEmployee.setName(sc.next());
      currentEmployee.setSurname(sc.next());
      currentEmployee.setPhone(sc.next());
      currentEmployee.setTitle(sc.next());
      currentEmployee.setHoursWorked(sc.nextInt());
      
      //remove garbage input
      sc.nextLine();
      
      return currentEmployee;
   }
   
   
   
   //PROCESSING GUEST
   public static Guest guestProcessLine(String currentLine) {
      //Put the guest object from the file (stored as a string)
      //into the scanner and instantiate a placeholder object of Guest type
      Scanner sc = new Scanner(currentLine);
      Guest currentGuest = new Guest();
      
      //Assign correct values from the string to the object
      currentGuest.setName(sc.next());
      currentGuest.setSurname(sc.next());
      currentGuest.setPhone(sc.next());
      currentGuest.setZipCode(sc.nextInt());
      
      //Discard guestID, no setter
      sc.next();
      
      return currentGuest;
   }
   
   
   
   //PROCESSING ROOM
   public static Room roomProcessLine(String currentLine) {
      //Put the room object from the file (stored as a string)
      //into the scanner and instantiate a placeholder object of Room type
      Scanner sc = new Scanner(currentLine);
      Room currentRoom = new Room();
   
      currentRoom.setLabel(sc.next());
      currentRoom.setDoorNumber(sc.nextInt());
      currentRoom.setFloorNumber(sc.nextInt());
      currentRoom.setTotalBeds(sc.nextInt());
      currentRoom.setHasWifi(sc.nextBoolean());
      currentRoom.setPrice(Double.parseDouble(sc.next()));
      
      while (sc.hasNext()) {
         String startDate = sc.next() + " " + sc.next() + " " + sc.next();
         String endDate = sc.next() + " " + sc.next() + " " + sc.next();
         int guestID = sc.nextInt();
         currentRoom.getListOfBookings().add(new Booking(startDate, endDate, guestID));
         
      }
      return currentRoom;
   }


}