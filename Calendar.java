import java.util.*;

class Calendar {
   
   public static void dates(int DaysToAdd){ //generate amount of days for the arrayList of available days for booking
      if(HotelPlazaMain.daysForBooking.isEmpty()){
         HotelPlazaMain.daysForBooking.add("1 1 2020");
      }
      for (int i=0;i<DaysToAdd;i++){
         HotelPlazaMain.daysForBooking.add(nextDay(HotelPlazaMain.daysForBooking.get(HotelPlazaMain.daysForBooking.size()-1)));
      }
   }  
   
   public static String checkOutDate(String checkIn, int lengthOfVisit){ //takes a check-in date and a time period and returns the last day of the visit
      for (int i=0;i<lengthOfVisit;i++){
         checkIn=nextDay(checkIn);
      }
      return checkIn;
   }
   
   public static String enterDate(String DateForWhat){ // creating a date by asking for day, month, year
      int day,month,year;
      do{
         System.out.println("Which day of the month would you like to "+DateForWhat+ "?");
         day = ScannerReaders.readerInt();
         System.out.println("Which month would you like to "+DateForWhat+ "?");
         month = ScannerReaders.readerInt();
         System.out.println("Which year would you like to "+DateForWhat+ "?");
         year = ScannerReaders.readerInt();
      }while(day>HotelPlazaMain.months[month-1]);
      return (day+" "+month+" "+year);
   }
   
   public static ArrayList <String> requestForDates(){
      ArrayList<String> datesRequired = new ArrayList<String>();
      String checkInDate = enterDate("check-in");
      String tempDate = checkInDate;
      System.out.println("For how many days would you like to stay?");
      int lengthOfVisit = ScannerReaders.readerInt();
      int indexOfRoomArray = 0;
      int guestID = -1;
      
      for (int i = 0; i < lengthOfVisit; i++) {
         datesRequired.add(tempDate);
         tempDate = nextDay(tempDate) + " ";
      }
      return datesRequired;
   }
   
   
   public static String nextDay(String startDate){ //return the next day
      Scanner scanner= new Scanner(startDate);
      int startDay=scanner.nextInt();
      int startMonth=scanner.nextInt();
      int startYear=scanner.nextInt();;
      if (isLeapYear(startYear)){
         HotelPlazaMain.months[1]=29;
      }
      else{
         HotelPlazaMain.months[1]=28;
      }
      if (startDay<HotelPlazaMain.months[startMonth-1]){
         startDay++;
      }
      else if(startMonth<12){
         startDay=1;
         startMonth++;
      }
      else{
         startYear++;
         startMonth=1;
         startDay=1;
      }
      return(startDay +" "+startMonth+" "+startYear);
   }
   
   public static int daysInBetween(String startDate, String endDate){ //day counter to know how many days we have booked to.
      int count=1;
      while (!startDate.equals(endDate)){
         count++;
         startDate=nextDay(startDate);
      }
      return count;
   }
   
   public static boolean isLeapYear(int year){ //check if it is a leap year
      if(year%4!=0){
         return false;
      }
      else if(year%100!=0){
         return true;
      }
      else if(year%400!=0){
         return false;
      }
      else{
         return true;
      }
   }
}