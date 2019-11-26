import java.util.*;

public class Password {
   
      private static final String ePassword = "1234";
      private static final String aPassword = "9999";
      
      public static int passwordCheck() {
         String passwordTrial = ""; 
         int tries = 3;
         System.out.println("Please, enter your four-digit password: ");
         passwordTrial = "" + ScannerReaders.readerInt();
         while (!passwordTrial.equals(ePassword) && !passwordTrial.equals(aPassword)) {
            tries--;
            if (tries == 0) {
               System.out.println("You tried too many times. Please try again later.");
               return 0;
               
            }
            System.out.println("Wrong password. Please try again: ");
            passwordTrial = "" + ScannerReaders.readerInt();
         }
         
         if (passwordTrial.equals(aPassword)) {
            System.out.println("Correct password! Welcome Director!");
            System.out.println();
            return 2;
         }
         else {
            System.out.println("Correct password! Enjoy your shift!");
            System.out.println();
            return 1;
         }
      }
}                  