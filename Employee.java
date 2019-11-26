public class Employee extends Person {
   //Attributes
   private String title;
   private double salary;
   private int hoursWorked;
   private static int totalEmployees = 0;
   
   //Constructors, maybe one without hoursworked for Director??
   public Employee() {
      ++totalEmployees;
   }
   
   public Employee(String title, String name, String surname, String phone) {
      setTitle(title);
      setName(name);
      setSurname(surname);
      setHoursWorked(160);
      setPhone(phone);
      ++totalEmployees;
   }
   
   public Employee(String title, String name, String surname, int hoursWorked, String phone) {
      setTitle(title);
      setName(name);
      setSurname(surname);
      setHoursWorked(hoursWorked);
      setPhone(phone);
      ++totalEmployees;
   }
   
   //Getters & Setters
   public String getTitle() {
      return this.title;
   }
   
   public void setTitle(String title) {
      //Convert all titles into one of these three presets
      //If garbage input is entered, set user as Service,
      //the lowest possible rank
      if (title.equalsIgnoreCase("director")) {
         title = "DIRECTOR";
      }
      else if (title.equalsIgnoreCase("receptionist")) {
         title = "RECEPTIONIST";
      }
      else if (title.equalsIgnoreCase("accountant")) {
         title = "ACCOUNTANT";
      }
      else {
         title = "CLEANING";
      }
      
      this.title = title;
   }
   
   //No setter, it's determined somewhere else
   public double getSalary() {
      return this.salary;
   }
   
   public void setSalary(double salary){
     this.salary=salary;
   }
   
   public int getHoursWorked() {
      return this.hoursWorked;
   }
   
   public void setHoursWorked(int hoursWorked) {
      this.hoursWorked = hoursWorked;
      calcSalary();
   }
   
   //No setter, totalEmployees shouldn't be manipulated
   public static int getTotalEmployees() {
      return totalEmployees;
   }
   
   //.toString() method, incorporates .toString() from Person
   public String toString() {
      return super.toString() + getTitle() + " " + getHoursWorked() + " " + getSalary() + '\n';
   }
   
   //Method to calculate the salary for an employee based on hoursWorked
   //Director has a set salary
   public void calcSalary() {
      if (this.title.equals("DIRECTOR")) {
         this.salary = 50000;
      }
      else if (this.title.equals("ACCOUNTANT")) {
         this.salary = this.hoursWorked * 320;
      }
      else if (this.title.equals("RECEPTIONIST")) {
         this.salary = this.hoursWorked * 230;
      }
      else {
         this.salary = this.hoursWorked * 160;
      }
   }
}