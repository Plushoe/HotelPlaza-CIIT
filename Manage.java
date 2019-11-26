import java.util.*;
import java.io.*;

class Manage {
   
   //CREATE
   public static void createEmployee(){ //creating a new employee 
      System.out.println("Please type the title of the new employee:");
      String title=ScannerReaders.scannerWords();
      System.out.println("Please type the first name of the employee:");
      String name=ScannerReaders.scannerWords();
      System.out.println("Please type the surname of the employee:");
      String surname=ScannerReaders.scannerWords();
      System.out.println("Please type how many hours the employee works in a month:");
      int hoursWorked=ScannerReaders.readerInt();
      System.out.println("Please type the employee's phone number:");
      String phone=Integer.toString(ScannerReaders.readerInt());  
      HotelPlazaMain.employeeArray.add(new Employee(title, name, surname, hoursWorked, phone));
   }
   
   public static void createGuest(){ //creating a new guest
      System.out.println("Please type the first name of the guest:");
      String name=ScannerReaders.scannerWords();
      System.out.println("Please type the surname of the guest:");
      String surname=ScannerReaders.scannerWords();
      System.out.println("Please type the zip code of the area where the guest lives:");
      int zipCode=ScannerReaders.readerInt();
      System.out.println("Please type the guest's phone number:");
      String phone=Integer.toString(ScannerReaders.readerInt());  
      HotelPlazaMain.guestArray.add(new Guest(name, surname, zipCode, phone));
   }
    
   public static void createRoom(){ //creating a new room
      System.out.println("Please type the label of the room:");
      String roomLabel= ScannerReaders.scannerWords();
      System.out.println("Please type the door number:");
      int roomDoorNumber=ScannerReaders.readerInt() ;
      System.out.println("Please type the room's floor:");
      int roomFloorNumber=ScannerReaders.readerInt() ;
      System.out.println("Please type the amount of beds:");
      int roomTotalBeds= ScannerReaders.readerInt();
      System.out.println("Does the room has Wi-Fi?\nType 1 for YES / Type 2 for NO:");
      boolean roomHasWifi=ScannerReaders.yesOrNo(ScannerReaders.readerInt());
      System.out.println("Please set the price for the room:");
      int roomPrice= ScannerReaders.readerInt();
      HotelPlazaMain.roomArray.add(new Room(roomLabel, roomDoorNumber, roomFloorNumber, roomTotalBeds, roomHasWifi, roomPrice));
   }
   
   //Methods for creating a booking
   //Formerly checkRoomsByDate
   public static void createBooking() {
      ArrayList<Integer> unavailableRooms = new ArrayList();
      ArrayList<Integer> availableRooms = new ArrayList();
      ArrayList<String> datesRequired = Calendar.requestForDates();
      String currentDate;
      int indexOfRoomArray = 0;
      
      for (int i = 0; i < datesRequired.size(); i++) { //creating indexes array of the unavailble rooms
         currentDate = datesRequired.get(i);
         for (int j = 0; j < HotelPlazaMain.roomArray.size(); j++) {
            ArrayList<Booking> currentBookingList = HotelPlazaMain.roomArray.get(j).getListOfBookings();
            if (currentBookingList.isEmpty()) {
               availableRooms.add(j);
            }
            else {
               for (int k = 0; k < currentBookingList.size(); k++){
                  if (currentBookingList.get(k).getBookedDays().contains(currentDate)) {//mark room as unavailble for booking
                     unavailableRooms.add(j);
                  }
                  else {
                     availableRooms.add(j);
                  }
               }
            }
         }
      }
      
      ArrayList<Integer> newUnavailableRooms = shrinkArrayList(unavailableRooms);//creating new arraylist of unavailble rooms withhout duplicates
      ArrayList<Integer> newAvailableRooms = shrinkArrayList(availableRooms);
      
      for (int i = 0; i < newUnavailableRooms.size(); i++) {
         newAvailableRooms.remove(newUnavailableRooms.get(i));
      }
      
      newAvailableRooms = setBedsPlusWifi(newAvailableRooms); 
      int userChoice = 0;  
      indexOfRoomArray = chooseRoomFromAvailableRoom(newAvailableRooms);
      //Save Booking
      int guestID = guestHasBooking();
      String sDate = datesRequired.get(0);
      String eDate = datesRequired.get(datesRequired.size()-1);
      Booking newBooking = new Booking();
      newBooking.setStartDate(sDate);
      newBooking.setEndDate(eDate);
      newBooking.setGuestID(guestID);
      HotelPlazaMain.roomArray.get(indexOfRoomArray).getListOfBookings().add(newBooking);
      System.out.println("Booking has been created");
      
   }
   
   public static ArrayList shrinkArrayList(ArrayList original){ //reduce duplicated attributes in arrayList
      ArrayList returnArrayList = new ArrayList();
      for (int i=0; i<original.size();i++){
         if (!returnArrayList.contains(original.get(i))){
            returnArrayList.add(original.get(i));
         }
      }
      return returnArrayList;   
   }
   
   public static int guestHasBooking(){
      int guestID=-1;
      System.out.println("Are you an existing guest in our hotel?");
      if(ScannerReaders.yesOrNo(ScannerReaders.readerInt())){ 
         ArrayList<Integer> findGuest = Search.sGuestMenu(HotelPlazaMain.guestArray);
         
         for (int i = 0; i < findGuest.size(); i++) {
            System.out.println((i + 1) + ". " + HotelPlazaMain.guestArray.get(findGuest.get(i)));
         }
         System.out.print("Which guest are you? type the number");
         int number = ScannerReaders.readerInt();
         guestID = HotelPlazaMain.guestArray.get(findGuest.get(number - 1)).getGuestID();
         System.out.println(guestID);
      }
      else {
         createGuest();
         guestID = HotelPlazaMain.guestArray.get(HotelPlazaMain.guestArray.size() - 1).getGuestID();
         //creating new guest and find the guest id
      }
      return guestID;
   }

   public static ArrayList<Integer> setBedsPlusWifi(ArrayList<Integer> a) {
      ArrayList<Integer> freeRoomIndexes = a;
      System.out.println("How many beds does the guest want in the room?");
      int totalBeds = ScannerReaders.readerInt();
      System.out.println("Does the room need Wi-Fi?");
      boolean hasWifi = ScannerReaders.yesOrNo(ScannerReaders.readerInt());
      
      ArrayList<Integer> newFreeRoomIndexes = new ArrayList();
      
      for (int i = 0; i < freeRoomIndexes.size(); ++i) {
         int currentRoomIndex = freeRoomIndexes.get(i);
         if (HotelPlazaMain.roomArray.get(currentRoomIndex).getHasWifi() == hasWifi && HotelPlazaMain.roomArray.get(currentRoomIndex).getTotalBeds() >= totalBeds) {
            newFreeRoomIndexes.add(currentRoomIndex);
         }
         
      }
      return newFreeRoomIndexes;
   }

   
   public static int chooseRoomFromAvailableRoom(ArrayList <Integer> availableRoom){
      int userChoice =-1;
      if (availableRoom.size() > 1) {
         printRoom(availableRoom);
         System.out.print("Select a room: ");
         userChoice = (ScannerReaders.readerInt() - 1);
         while(userChoice > availableRoom.size() || userChoice < 0){
            System.out.println("please choose one of the roooms that are available");
            userChoice = (ScannerReaders.readerInt() - 1);
         }
      }
      else if (availableRoom.size() == 1) {
         System.out.print(availableRoom +"\n would you like to book this room?");
         if(ScannerReaders.yesOrNo(ScannerReaders.readerInt())){
            userChoice = 0;
         }
      }
      else {
         System.out.println("there is no room available");
      }      
      return availableRoom.get(userChoice);
   }
   
   //READ
   public static ArrayList <Integer> toShowWholeArray(ArrayList toShow){
      ArrayList<Integer> x = new ArrayList<Integer>();
      for (int i=0;i<toShow.size();i++){
         x.add(i);
      }
      return x;
   }
   
   public static void printRoom(ArrayList <Integer> subRoomArray){
      System.out.printf("%-5s%-10s%-10s%-10s%-10s%n"," ","Label", "Room-ID", "Wifi", "Price");
      System.out.println("__________________________________________________________________");
      for (int i = 0; i < subRoomArray.size(); ++i) {
         String x = "[" +(i+1)+"]";
         System.out.printf("%-5s%-10s%-10s%-10s%-10s%n",x,HotelPlazaMain.roomArray.get(subRoomArray.get(i)).getLabel(),HotelPlazaMain.roomArray.get(subRoomArray.get(i)).getRoomID(),"" + HotelPlazaMain.roomArray.get(subRoomArray.get(i)).getHasWifi(),"" + HotelPlazaMain.roomArray.get(subRoomArray.get(i)).getPrice());
      }
   }
   
   public static void printGuest(ArrayList <Integer> subGuestArray){
      System.out.printf("%-5s%-10s%-10s%-10s%-10s%n"," ","Name","Surname","Phone","Zip-Code");
      System.out.println("__________________________________________________________________");
      for (int i=0;i<subGuestArray.size();i++){
         String x = "[" +(i+1)+"]";
         System.out.printf("%-5s%-10s%-10s%-10s%-10s%n",x,HotelPlazaMain.guestArray.get(subGuestArray.get(i)).getName(),HotelPlazaMain.guestArray.get(subGuestArray.get(i)).getSurname(),HotelPlazaMain.guestArray.get(subGuestArray.get(i)).getPhone(), "" + HotelPlazaMain.guestArray.get(subGuestArray.get(i)).getZipCode());
      }
   }
   
   public static void printEmployee(ArrayList<Integer> subEmployeeArray){
      System.out.printf("%-5s%-15s%-10s%-10s%-10s\n"," ","Name", "Surname", "Phone", "Title");
      System.out.println("_______________________________________________________________");
      for (int i=0;i<subEmployeeArray.size();i++){
         String x = "[" +(i+1)+"]";
         System.out.printf("%-5s%-15s%-10s%-10s%-10s\n",x,HotelPlazaMain.employeeArray.get(subEmployeeArray.get(i)).getName(),HotelPlazaMain.employeeArray.get(subEmployeeArray.get(i)).getSurname(),HotelPlazaMain.employeeArray.get(subEmployeeArray.get(i)).getPhone(),HotelPlazaMain.employeeArray.get(subEmployeeArray.get(i)).getTitle()); 
      }
   }
   
   public static void printBooking(ArrayList<int[]> subBookingArray){
      System.out.printf("%-5s%-10s%-20s%-15s%-15s%-10s%n"," ", "Room-ID","Guest name", "Check-In","Check-Out","Price");
      System.out.println("______________________________________________________________________________");
      for (int i=0;i<subBookingArray.size();i++){
         
         int []currentBooking = subBookingArray.get(i);
         String x = "[" +(i+1)+"]";
         String roomId = ""+HotelPlazaMain.roomArray.get(currentBooking[0]).getRoomID();
         int guestID = HotelPlazaMain.roomArray.get(currentBooking[0]).getListOfBookings().get(currentBooking[1]).getGuestID();
         int indexGuest = Search.searchGuest(HotelPlazaMain.guestArray, "" + guestID, "l").get(0);
         
         String guestName = "Mr. " + HotelPlazaMain.guestArray.get(indexGuest).getSurname() + " " + HotelPlazaMain.guestArray.get(indexGuest).getName();
         String startDate = HotelPlazaMain.roomArray.get(currentBooking[0]).getListOfBookings().get(currentBooking[1]).getStartDate();
         String endDate = HotelPlazaMain.roomArray.get(currentBooking[0]).getListOfBookings().get(currentBooking[1]).getEndDate();
         String price = ""+(HotelPlazaMain.roomArray.get(currentBooking[0]).getListOfBookings().get(currentBooking[1]).getNumberOfDays() * HotelPlazaMain.roomArray.get(currentBooking[0]).getPrice());
         System.out.printf("%-5s%-10s%-20s%-15s%-15s%-10s%n",x,roomId,guestName,startDate,endDate,price);
      }
   }
   //UPDATE
   public static void updateGuest(){
      ArrayList<Integer> matchingIndexes = Search.sGuestMenu(HotelPlazaMain.guestArray);
      if (!matchingIndexes.isEmpty()) {
         printGuest(matchingIndexes);
      
         int userChoice = -1;
         do { 
            System.out.println("Which guest do you want to update?");
            userChoice = ScannerReaders.readerInt() - 1;
         }while (userChoice < 0 || userChoice >= matchingIndexes.size());
         int guestForUpdate = matchingIndexes.get(userChoice);
         System.out.println("[1] Change name");
         System.out.println("[2] Change surname");
         System.out.println("[3] Change zip-code");
         System.out.println("[4] Change phone-number");
         System.out.println("[0] Back to menu");
         do{   
            System.out.print("Which field do you wish to change?: ");
            userChoice =ScannerReaders.readerInt();
         }
         while (userChoice<0 || userChoice>4);
         switch(userChoice){
            case 1:
               String newName = ScannerReaders.scannerWords();
               HotelPlazaMain.guestArray.get(guestForUpdate).setName(newName);
               break;
            case 2:
               String newSurname = ScannerReaders.scannerWords();
               HotelPlazaMain.guestArray.get(guestForUpdate).setSurname(newSurname);
               break;
            case 3:
               int newZipcode =ScannerReaders.readerInt();
               HotelPlazaMain.guestArray.get(guestForUpdate).setZipCode(newZipcode);
               break;
            case 4:
               String newPhone = "" +ScannerReaders.readerInt();
               HotelPlazaMain.guestArray.get(guestForUpdate).setPhone(newPhone);
               break;
            case 0:
               System.out.println("Nothing has been changedd");
               break;
            default:
               break; 
         }
      }
      else{
         System.out.println("No match found!");
      }
   }
   
   public static void updateEmployee(){
      ArrayList<Integer> matchingIndexes = Search.sEmployeeMenu(HotelPlazaMain.employeeArray);
      
      if (!matchingIndexes.isEmpty()) {
         printEmployee(matchingIndexes);
      
         int userChoice = -1;
         do { 
            System.out.println("Which employee do you want to change?");
            userChoice = ScannerReaders.readerInt() -1;
         }while (userChoice < 0 || userChoice >= matchingIndexes.size());
         int EmployeeToUpdate=matchingIndexes.get(userChoice);
         System.out.println("[1] Change name");
         System.out.println("[2] Change surname");
         System.out.println("[3] Change title");
         System.out.println("[4] Change phone-number");
         System.out.println("[5] Change salary");
         System.out.println("[0] Back to menu");
         do{   
            System.out.print("Which field do you wish to change?: ");
            userChoice =ScannerReaders.readerInt();
         }
         while (userChoice<0 || userChoice>5);
         System.out.println("Type in your change: ");
         switch(userChoice){
            case 1:
               String newName = ScannerReaders.scannerWords();
               HotelPlazaMain.employeeArray.get(EmployeeToUpdate).setName(newName);
               break;
            case 2:
               String newSurname = ScannerReaders.scannerWords();
               HotelPlazaMain.employeeArray.get(EmployeeToUpdate).setSurname(newSurname);
               break;
            case 3:
               String newTitle =ScannerReaders.scannerWords();
               HotelPlazaMain.employeeArray.get(EmployeeToUpdate).setTitle(newTitle);
               break;
            case 4:
               String newPhone = "" +ScannerReaders.readerInt();
               HotelPlazaMain.employeeArray.get(EmployeeToUpdate).setPhone(newPhone);
               break;
            case 5:
               double newSalary = ScannerReaders.readerDouble();
               HotelPlazaMain.employeeArray.get(EmployeeToUpdate).setSalary(newSalary);
            case 0:
               System.out.println("Nothing has been changed");
               break;
            default:
               break; 
         }
      }
      else{
         System.out.println("No match found!");
      }
   }
   
   public static void updateRoom(){
      ArrayList<Integer> matchingIndexes = Search.sRoomMenu(HotelPlazaMain.roomArray);
      if (!matchingIndexes.isEmpty()) {
         printRoom(matchingIndexes);
         
         int userChoice = -1;
         do { 
            System.out.println("Which room do you want to update?");
            userChoice = ScannerReaders.readerInt() - 1;
         } while (userChoice < 0 || userChoice >= matchingIndexes.size());
         int roomToUpdate = matchingIndexes.get(userChoice);
         System.out.println("[1] Change label");
         System.out.println("[2] Change floor number");
         System.out.println("[3] Change door number");
         System.out.println("[4] Change total beds");
         System.out.println("[5] Change wifi ");
         System.out.println("[6] change price");
         System.out.println("[0] Back to menu");
         do{   
            System.out.print("Which field do you wish to change?: ");
            userChoice =ScannerReaders.readerInt();
         }
         while (userChoice<0 || userChoice>6);
         switch (userChoice){
            case 1:
               String newLabel = ScannerReaders.scannerWords();
               HotelPlazaMain.roomArray.get(roomToUpdate).setLabel(newLabel);
               break;
            case 2:
               int newFloorNumber = ScannerReaders.readerInt();
               HotelPlazaMain.roomArray.get(roomToUpdate).setFloorNumber(newFloorNumber); 
               break;
            case 3:
               int newDoorNumber = ScannerReaders.readerInt();
               HotelPlazaMain.roomArray.get(roomToUpdate).setDoorNumber(newDoorNumber);
               break;
            case 4:
               int newTotalBeds = ScannerReaders.readerInt();
               HotelPlazaMain.roomArray.get(roomToUpdate).setTotalBeds(newTotalBeds);
               break;
            case 5:
               boolean wifi = ScannerReaders.yesOrNo(ScannerReaders.readerInt());
               HotelPlazaMain.roomArray.get(roomToUpdate).setHasWifi(wifi);
               break;
            case 6:
               double price = ScannerReaders.readerDouble();
               HotelPlazaMain.roomArray.get(roomToUpdate).setPrice(price);
               break;
            case 0:
               break;
            default:
               break; 
         }
      
      }
      else {
         System.out.println("No match found!");
      }
   }
   
   public static void updateBooking(){
      ArrayList<int[]> matchingIndexes = Search.sBookingMenu(HotelPlazaMain.roomArray);
      
      if (!matchingIndexes.isEmpty()) {
         printBooking(matchingIndexes);
      
         int userChoice = -1;
         do { 
            System.out.println("Which Booking do you want to update?");
            userChoice = ScannerReaders.readerInt() - 1;
         }while (userChoice < 0 || userChoice >= matchingIndexes.size());
         int[] bookingUpdate = matchingIndexes.get(userChoice);
         System.out.println("[1] Change check-in date");
         System.out.println("[2] Change check-out date");
         System.out.println("[3] Change guest");
         System.out.println("[0] Back to menu");
         do{   
            System.out.print("Which field do you wish to change?: ");
            userChoice =ScannerReaders.readerInt();
         }
         while (userChoice<0 || userChoice>3);
         switch (userChoice){
            case 1:
               String newCheckInDate = Calendar.enterDate("check-in");
               HotelPlazaMain.roomArray.get(bookingUpdate[0]).getListOfBookings().get(bookingUpdate[1]).setStartDate(newCheckInDate);
               break;
            case 2:
               String newEndDate = Calendar.enterDate("check-out");
               HotelPlazaMain.roomArray.get(bookingUpdate[0]).getListOfBookings().get(bookingUpdate[1]).setEndDate(newEndDate); 
               break;
            case 3:
               int newGuestID = guestHasBooking();
               HotelPlazaMain.roomArray.get(bookingUpdate[0]).getListOfBookings().get(bookingUpdate[1]).setGuestID(newGuestID);
               break;
            case 0:
               break;
            default:
               System.out.println("something went wrong");
               break;
         }
      }
   }
   
   //DELETE
   public static void deleteBooking(){
      ArrayList<int[]> matchingIndexes = Search.sBookingMenu(HotelPlazaMain.roomArray);
      
      if (!matchingIndexes.isEmpty()) {
         printBooking(matchingIndexes);
      
         int userChoice = -1;
         do { 
            System.out.println("Which Booking do you want to delete?");
            userChoice = ScannerReaders.readerInt() - 1;
         }while (userChoice < 0 || userChoice >= matchingIndexes.size());
         int[] needToDelete = matchingIndexes.get(userChoice);
         HotelPlazaMain.roomArray.get(needToDelete[0]).getListOfBookings().remove(needToDelete[1]);
         System.out.println("Item has been deleted!");
      }
      else {
         System.out.println("No match found!");
      }
   }
   public static void deleteGuest(){
      ArrayList<Integer> matchingIndexes = Search.sGuestMenu(HotelPlazaMain.guestArray);
      
      if (!matchingIndexes.isEmpty()) {
         printGuest(matchingIndexes);
      
         int userChoice = -1;
         do { 
            System.out.println("Which guest do you want to delete?");
            userChoice = ScannerReaders.readerInt() - 1;
         }while (userChoice < 0 || userChoice >= matchingIndexes.size());
         int needToDelete = matchingIndexes.get(userChoice);
         HotelPlazaMain.guestArray.remove(needToDelete);
         System.out.println("Item has been deleted!");
      }
      else {
         System.out.println("No match found!");
      }
   }
   
   public static void deleteEmployee(){
      ArrayList<Integer> matchingIndexes = Search.sEmployeeMenu(HotelPlazaMain.employeeArray);
      
      if (!matchingIndexes.isEmpty()) {
         printEmployee(matchingIndexes);
      
         int userChoice = -1;
         do { 
            System.out.println("Which employee do you want to delete?");
            userChoice = ScannerReaders.readerInt() -1;
         }while (userChoice < 0 || userChoice >= matchingIndexes.size());
         int needToDelete=matchingIndexes.get(userChoice);
         HotelPlazaMain.employeeArray.remove(needToDelete);
         System.out.println("Item has been deleted!");
      }
      else {
         System.out.println("No match found!");
      }
   }
 
 
   public static void deleteRoom(){
      ArrayList<Integer> matchingIndexes = Search.sRoomMenu(HotelPlazaMain.roomArray);
      
      if (!matchingIndexes.isEmpty()) {
         printRoom(matchingIndexes);
      
         int userChoice = -1;
         do { 
            System.out.println("Which room do you want to delete?");
            userChoice = ScannerReaders.readerInt() - 1;
         } while (userChoice < 0 || userChoice >= matchingIndexes.size());
         
         int needToDelete = matchingIndexes.get(userChoice);
         HotelPlazaMain.roomArray.remove(needToDelete);
         System.out.println("Item has been deleted!");
      }
      else {
         System.out.println("No match found!");
      }
   }
   
}