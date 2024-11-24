
import java.util.Scanner;

public class LedgerSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Double []debit=new Double[100];
        String []descDebit=new String[100];
        int count=0;
        boolean running = true;

        while(running){
            
            System.out.println("== Transaction ==");
            System.out.println("1. Debit\n"
                           + "2. Credit\n"
                           + "3. History\n"
                           + "4. Savings\n"
                           + "5. Credit loan\n"
                           + "6. Deposit Interest Predictor\n"
                           + "7. Logout");
            int option = sc.nextInt();
            
            switch (option){
                case 1:
                    System.out.println("== Debit ==");
                    System.out.print("Enter amount:");
                    debit[count]=sc.nextDouble();
                    sc.nextLine();
                    System.out.print("Enter description: ");
                    descDebit[count]=sc.nextLine();
                    System.out.println(descDebit[count]);
                    count++;

                    System.out.println("Debit Successfully Recorded!!!");
                    break;
                    
                
                case 2:
                    
                case 3:
                    
                case 4:
                    
                case 5:
                    
                case 6:

                case 7:
                    System.out.println("Thank you for using Ledger System!");
                    running = false;
                    break;

                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
            
            
        }
        
    }
}
