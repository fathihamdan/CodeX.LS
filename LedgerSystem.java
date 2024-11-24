
import java.util.Scanner;

public class LedgerSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        while(true){
            System.out.println("== Transaction ==");
            System.out.println("1. Debit\n"
                           + "2. Credit\n"
                           + "3. History\n"
                           + "4. Savings\n"
                           + "5. Credit loan");
            int option = sc.nextInt();
            
            switch (option){
                case 1:
                    System.out.println("== Debit ==");
                    System.out.println("Enter amount:");
                    sc.nextDouble();
                    break;
                    
                
                case 2:
                    
                case 3:
                    
                case 4:
                    
                case 5:
                    
            }
            
        }
        
    }
}