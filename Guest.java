public class Guest extends Person {
   //Attributes
   private int zipCode;
   private int guestID;
   //Class-wide variable
   private static int totalGuests = 0;
   
   //Constructors
   public Guest() {
      guestID = ++totalGuests;
   }
   
   public Guest(String name, String surname, int zipCode, String phone) {
      setName(name);
      setSurname(surname);
      setZipCode(zipCode);
      setPhone(phone);
      guestID = ++totalGuests;
   }   
   
   
   public int getZipCode() {
      return this.zipCode;
   }
   
   public void setZipCode(int zipCode) {
      this.zipCode = zipCode;
   }
   
   //No setter, ID is dependant on totalGuests   
   public int getGuestID() {
      return this.guestID;
   }
   
   //.toString() method, incorporates .toString() from Person
   public String toString() {
      return super.toString() + getZipCode() + " " + getGuestID() + '\n';
   }
}