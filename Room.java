import java.util.*;

public class Room {
   //Attributes
   private String label;
   private int doorNumber;
   private int floorNumber;
   private int totalBeds;
   private String roomID;
   private boolean hasWifi;
   private double price;
   private ArrayList<Booking> listOfBookings;
   private static int totalRooms = 0;
   
   //Constructors
   public Room() {
      setListOfBookings(new ArrayList());
      totalRooms++;
   }
   
   public Room(String label, int floorNumber, int doorNumber, int totalBeds, boolean hasWifi, double price) {
      setLabel(label);
      setDoorNumber(doorNumber);
      setFloorNumber(floorNumber);
      setTotalBeds(totalBeds);
      setHasWifi(hasWifi);
      setPrice(price);
      setListOfBookings(new ArrayList());
      totalRooms++;
   }
   
   //Getters & Setters
   public String getLabel() {
      return this.label;
   }
   
   public void setLabel(String label) {
      this.label = label;
   }
   
   public int getDoorNumber() {
      return this.doorNumber;
   }
   
   public void setDoorNumber(int doorNumber) {
      this.doorNumber = doorNumber;
      generateRoomID();
   }
   
   public int getFloorNumber() {
      return this.floorNumber;
   }
   
   public void setFloorNumber(int floorNumber) {
      this.floorNumber = floorNumber;
      generateRoomID();
   }
   
   public int getTotalBeds() {
      return this.totalBeds;
   }
   
   public void setTotalBeds(int totalBeds) {
      this.totalBeds = totalBeds;
      generateRoomID();
   }
   
   public boolean getHasWifi() {
      return this.hasWifi;
   }
   
   public void setHasWifi(boolean hasWifi) {
      this.hasWifi = hasWifi;
   }
   
   public double getPrice() {
      return this.price;
   }
   
   public void setPrice(double price) {
      this.price = price;
   }
   
   public ArrayList<Booking> getListOfBookings() {
      return this.listOfBookings;
   }
   
   public void setListOfBookings(ArrayList<Booking> listOfBookings) {
      this.listOfBookings = listOfBookings;
   }
   
   public String getRoomID(){
      return this.roomID;
   }
   
   public int getTotalRooms() {
      return this.totalRooms;
   }
   
   
   //.toString() method
   public String toString() {
      return getLabel() + " " + getDoorNumber() + " " + getFloorNumber() + " " + getTotalBeds() + " " +
         getHasWifi() + " " + getPrice() + " " + bookingToFile() + '\n';
   }
   
   public void print() {
      String wifi = "";
      if (getHasWifi()) {
         wifi = "Yes";
      }
      else {
         wifi = "No";
      }
      
      System.out.printf("%-5s%-10s%-10s%-7s%10s\n", "", getLabel(), getRoomID(), wifi, (getPrice() + ""));
   }
   
   public String bookingToFile() {
      String bookingString = "";
      
      for (int i = 0; i < getListOfBookings().size(); ++i) {
         bookingString += getListOfBookings().get(i);
      }
      return bookingString;
   }
   
   public void generateRoomID() {
      this.roomID = "" + getFloorNumber() + getDoorNumber() + getTotalBeds();
   }
   
}