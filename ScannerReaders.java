import java.util.*;

public class ScannerReaders {
   
   public static String scannerWords() { //Used when needing String input 
      Scanner console = new Scanner(System.in);
      String keystroke = console.next();
      
      while (!keystroke.matches("^[a-zA-Z]*$")) {
         System.out.println("This doesn't look right, try again only using letters :)");
         keystroke=console.next();
      }
      
      return keystroke;
   }
   
   public static double readerDouble(){
      Scanner console = new Scanner(System.in);
      double num = 0.0;
      boolean valid = true;
      
      do {
         try {
            num = console.nextDouble();
            valid = false;
         }
         catch(Exception e) {
            console.next();
            console.nextLine();
            System.out.println("You did not enter a number");
         }
      } while(valid);
      
      return num;
   }  

   public static int readerInt() { //Used when needing int input
      Scanner console = new Scanner(System.in);
      int num = 0;
      boolean valid = true;
      
      do {
         try {
            num = console.nextInt();
            valid = false;
         }
         catch(Exception e) {
            console.next();
            console.nextLine();
            System.out.println("You did not enter a number");
         }
      } while(valid);
      
      return num;
   }  
   
   
   
   
   public static boolean yesOrNo(int number){ //Used to make number into boolean
      while (number>2 || number<1){
         System.out.println("Please type 1 for YES / Type 2 for NO ");
         number=readerInt();
      }
      if (number==1){
         return true;
      }
      else{
         return false;
      }
   }
}    