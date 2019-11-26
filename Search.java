import java.util.*;
import java.io.*;
class Search {
   
   public static boolean quit(String keystroke) {
      return keystroke.equals("0");
   }

   public static ArrayList<Integer> sGuestMenu(ArrayList<Guest> a) {
      System.out.println("What do you want to search by?\n");
      System.out.println("[1]. Name");
      System.out.println("[2]. Surname");
      System.out.println("[3]. Phone");
      System.out.println("[4]. Zipcode");
      System.out.println("[0]. Cancel");
      System.out.println("_______________________________________________");
      
      Scanner sc = new Scanner(System.in);
      
      String keystroke = sc.next();
      if (quit(keystroke)) {
         return new ArrayList();
      }
      
      System.out.print("Enter search term: ");
      String keyword = sc.next();
      System.out.println();
      
      return searchGuest(a, keyword, keystroke);
   }
   
   public static ArrayList<Integer> sEmployeeMenu(ArrayList<Employee> a) {
      System.out.println("What do you want to search by?\n");
      System.out.println("[1]. Name");
      System.out.println("[2]. Surname");
      System.out.println("[3]. Phone");
      System.out.println("[4]. Job Title");
      System.out.println("[0]. Cancel");
      System.out.println("_______________________________________________");
   
      Scanner sc = new Scanner(System.in);
   
      String keystroke = sc.next();
      if (quit(keystroke)) {
         return new ArrayList();
      }
      
      System.out.print("Enter search term: ");
      String keyword = sc.next();
      System.out.println();
      return searchEmployee(a, keyword, keystroke);
   }
   
   public static ArrayList sBookingMenu(ArrayList<Room> a){
      System.out.println("What do you want to search by?\n");
      System.out.println("[1]. Check-In Date");
      System.out.println("[2]. Check-Out Date");
      System.out.println("[3]. Guest-ID");
      System.out.println("[0]. Cancel");
      System.out.println("_______________________________________________");
      Scanner sc = new Scanner(System.in);
      String keystroke = sc.next();
      if (quit(keystroke)) {
         return new ArrayList();
      }
      
      String keyword;
      if(keystroke.equals("3")){
         System.out.print("Enter search term: ");
         keyword = sc.next();
         System.out.println();
      }
      else{
         keyword = Calendar.enterDate("search by");
      }
      return searchBooking(a, keyword, keystroke);
   }
   
   public static ArrayList<Integer> sRoomMenu(ArrayList<Room> a) {
      System.out.println("What do you want to search by?\n");
      System.out.println("[1]. Room Type");
      System.out.println("[2]. Door Number");
      System.out.println("[3]. Floor Number");
      System.out.println("[4]. Number of Beds");
      System.out.println("[5]. Wifi-capabilities");
      System.out.println("[6]. Price/Night");
      System.out.println("[0]. Cancel");
      System.out.println("_______________________________________________");
      
      Scanner sc = new Scanner(System.in);
      
      String keystroke = sc.next();
      if (quit(keystroke)) {
         return new ArrayList();
      }
      
      System.out.print("Enter search term: ");
      String keyword = sc.next();
      System.out.println();
      return searchRoom(a, keyword, keystroke);
   }

   public static void searchMainMenu() {      
      System.out.println("Choose what you want to search for:");
      System.out.println("[1]. Guests");
      System.out.println("[2]. Employees");
      System.out.println("[3]. Rooms");
      System.out.println("[4]. Bookings");
      System.out.println("[0] Quit to main menu");
      System.out.println("_______________________________________________");
      
      String choice = ScannerReaders.readerInt() + "";
      
      switch (choice) {
         case "1":
            sGuestMenu(HotelPlazaMain.guestArray);      
            break;
         
         case "2":
            sEmployeeMenu(HotelPlazaMain.employeeArray);
            break;
         
         case "3":
            sRoomMenu(HotelPlazaMain.roomArray);
            break;
         
         case "0":
            break;
      }
   }
   
   public static ArrayList<Integer> searchGuest(ArrayList<Guest> a, String keyword, String keystroke) {
      ArrayList<Integer> matchingIndexes = new ArrayList();
      String target = "";
      
      for (int i = 0; i < a.size(); ++i) {
         Guest currentObject = a.get(i);
         
         switch (keystroke) {
            case "1":
               target = currentObject.getName();
               break;
           
            case "2":
               target = currentObject.getSurname();
               break;
            
            case "3":
               target = currentObject.getPhone();
               break;
               
            case "4":
               target = currentObject.getZipCode() + "";
               break;
               
            case "l":
               target = "" + currentObject.getGuestID();
               break;
               
            default:
               System.out.println("Choose one of the specified numbers!");
               sGuestMenu(HotelPlazaMain.guestArray);
               break;   
               
         }
         boolean isMatch = keyword.equalsIgnoreCase(target);   
         if (isMatch) {
            matchingIndexes.add(i);
         }
      }
      return matchingIndexes;
   }
   
   
   public static ArrayList<Integer> searchRoom(ArrayList<Room> a, String keyword, String keystroke) {
      ArrayList<Integer> matchingIndexes = new ArrayList();
      String target = "";
      
      for (int i = 0; i < a.size(); ++i) {
         Room currentObject = a.get(i);
         switch (keystroke) {
            case "1":
               target = currentObject.getLabel();
               break;
               
            case "2":
               target = currentObject.getDoorNumber() + "";
               break;
               
            case "3":
               target = currentObject.getFloorNumber() + "";
               break;
               
            case "4":
               target = currentObject.getTotalBeds() + "";
               break;
               
            case "5":
               target = currentObject.getHasWifi() + "";
               break;
               
            case "6":
               target = currentObject.getPrice() + "";
               break;
               
            default:
               System.out.println("Choose one of the specified numbers!");
               sRoomMenu(HotelPlazaMain.roomArray);
               break;
         }
         boolean isMatch = keyword.equalsIgnoreCase(target);
         if (isMatch) {
            matchingIndexes.add(i);
         }
      }
      
      return matchingIndexes;
   }
   
   
   public static ArrayList <int []> searchBooking(ArrayList<Room> a, String keyword, String keystroke) {
      ArrayList<int []> matchingIndexesArray = new ArrayList();
      ArrayList<Integer> matchingIndexes = new ArrayList();
      String target = "";
      for (int i = 0; i < a.size(); ++i) {
         Room currentRoom = a.get(i);
         if (!currentRoom.getListOfBookings().isEmpty()) {
            for (int j=0; j < currentRoom.getListOfBookings().size(); j ++){ 
               Booking currentBooking = currentRoom.getListOfBookings().get(j);
               switch (keystroke) {
                  case "1":
                     target = currentBooking.getStartDate();
                     break;
                  case "2":
                     target = currentBooking.getEndDate();
                     break;
                  case "3":
                     target = currentBooking.getGuestID() + "";
                     break;  
                  default:
                     System.out.println("Choose one of the specified numbers!");
                     sBookingMenu(HotelPlazaMain.roomArray);
                     break; 
               }
               boolean isMatch = keyword.equalsIgnoreCase(target);   
               if (isMatch) {
                   int [] matches = {i,j};
                   matchingIndexes.add(i);
                   matchingIndexes.add(j);
                   matchingIndexesArray.add(matches);
               }
            }
         }
      }
      return matchingIndexesArray;
   }
   
   
   public static ArrayList<Integer> searchEmployee(ArrayList<Employee> a, String keyword, String keystroke) {
      ArrayList<Integer> matchingIndexes = new ArrayList();
      String target = "";
      
      for (int i = 0; i < a.size(); ++i) {
         Employee currentObject = a.get(i);
         
         switch (keystroke) {
            case "1":
               target = currentObject.getName();
               break;
           
            case "2":
               target = currentObject.getSurname();
               break;
            
            case "3":
               target = currentObject.getPhone();
               break;
               
            case "4":
               target = currentObject.getTitle() + "";
               break;
               
            default:
               System.out.println("Choose one of the specified numbers!");
               sEmployeeMenu(HotelPlazaMain.employeeArray);
               break;   
               
         }
         boolean isMatch = keyword.equalsIgnoreCase(target);   
         if (isMatch) {
            matchingIndexes.add(i);
         }
      }
      return matchingIndexes;
   
   }

}