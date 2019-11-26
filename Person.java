public class Person{
   //Attributes
   private String name;
   private String surname;
   private String phone;
   
   //Constructors
   public Person() {}
   
   public Person(String name, String surname, String phone){
      setName(name);
      setSurname(surname);
      setPhone(phone);
   }
   
   //Getters & Setters
   public String getName(){
      return this.name;
   }
   
   public void setName(String name){
      this.name = name;
   }
   
   public String getSurname(){
      return this.surname;
   }   
   
   public void setSurname(String surname){
      this.surname = surname;
   }
   
   public String getPhone() {
      return this.phone;
   }
   
   public void setPhone(String phone) {
      this.phone = phone;
   }
   
   //.toString() method
   public String toString(){
      return getName() + " " + getSurname() + " " + getPhone() + " ";
   }
}

/*
int x = sc.nextInt();
switch (x) {

case "1":
blabla
break;


case 2:

case 3:
break;

default:
blabla
break;
}
*/