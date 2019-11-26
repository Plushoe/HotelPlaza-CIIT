public class Booking{
   private String startDate;
   private String endDate;
   private int numberOfDays;
   private int guestID;
   private String bookedDays="";
   
   public Booking(){}
   
   public Booking(String startDate, String endDate, int guestID){
      this.startDate = startDate;
      this.endDate = endDate;
      this.guestID = guestID;
      this.numberOfDays = Calendar.daysInBetween(startDate,endDate);
      generateBookedDays();
   }
   //getters, Setters
   public String getStartDate(){
      return this.startDate;
   }
   public void setStartDate(String startDate){
      this.startDate=startDate;
   }
   
   public String getEndDate(){
      return this.endDate;
   }
   public void setEndDate(String endDate){
      this.endDate=endDate;
   } 
   
   public int getNumberOfDays(){
      return this.numberOfDays;
   }
   
   public int getGuestID(){
      return this.guestID;
   }
   
   public void setGuestID(int guestID){
      this.guestID=guestID;
   }
   
   public void setBookedDays(String bookedDays) {
      this.bookedDays = bookedDays;
   }
   
   public String getBookedDays(){
      return this.bookedDays;
   }
   //toString method
   public String toString(){
      return startDate+" "+endDate+" "+guestID+" ";
   }
   
   public void generateBookedDays() {
      String bookedDays = "";
      String tempDate = getStartDate();
      for (int i = 0; i <= this.numberOfDays; i++) {
         bookedDays += tempDate + " ";
         tempDate = Calendar.nextDay(tempDate);
      }
      
      setBookedDays(bookedDays);
   }
 
}