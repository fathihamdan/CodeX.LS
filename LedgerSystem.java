import java.util.Scanner;
public class LedgerSystem {

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        
        //User Registration Validation
        String regName=" ";
        String regEmail=" ";
        String regPass=" ";
        String regEmailValid=" ";
        String regPassValid=" ";
        
        while(true){
            System.out.println("\n== Ledger System ==");
            System.out.println("Login or Register");
            System.out.println("1.Login");
            System.out.println("2.Register");
            System.out.print("\n>");
            int LogReg=sc.nextInt();
            
            sc.nextLine();
        
            if (LogReg==2){
                System.out.println("\n== Please fill in the form ==");
                System.out.print("Name: ");
                regName=sc.nextLine();
                
                while(true){
                    System.out.print("Email: ");
                    regEmail=sc.nextLine();
                
                    if(regEmail.endsWith("@gmail.com")){
                        regEmailValid=regEmail;
                        break;
                    }
                
                    else{
                        System.out.println("\nEmail must be in correct format(name@gmail.com).\n");
                    }
                } 
                
                while(true){
                    System.out.print("Password: ");
                    regPass=sc.nextLine();
                    
                    if (regPass.length()<8){
                        System.out.println("\nPassword must be at least 8 characters.\n");
                    }
                    
                    boolean letter=false;
                    boolean digit=false;
                    boolean spec=false;
                    
                    for(int i=0; i<regPass.length(); i++){
                        char charac=regPass.charAt(i);
                        
                        if (Character.isLetter(charac))
                            letter=true;
                        else if (Character.isDigit(charac))
                            digit=true;
                        else if (!Character.isLetterOrDigit(charac))
                            spec=true;
                    }
                    
                    if(letter&&digit&&spec){
                        regPassValid=regPass;
                        System.out.println("\nRegister succesful!!!\n");
                        break;
                    }
                    
                    else{
                        System.out.println("\nPassword must contain at least one letter, digit and special character.\n");
                    }
                }
            }
            
            else if (LogReg==1){
                System.out.println("\n== Please enter your email and password ==");
                
                while(true){
                    System.out.print("Email: ");
                    String email=sc.nextLine();
                    System.out.print("Password: ");
                    String pass=sc.nextLine();
                
                    if(email.equals(regEmailValid)&&pass.equals(regPassValid)){
                        System.out.println("\nLogin Successful!!!\n");
                        break;
                    }
                
                    else{
                        System.out.println("\nYour email or password is wrong.");
                    }
                }
                break;
            }
            else{
                System.out.println("\nPlease choose between 1 or 2 only.");
            }
        }
        
        Double []DebitCredit=new Double[100];
        String []descDebitCredit=new String[100];

        int count=0;
        Double balance =0.0;
        Double savings =0.0;
        Double loan =0.0;
        boolean running = true;
        
        while(true){
            System.out.println("\n== Welcome, "+regName+" ==");
            System.out.println("Balance: "+balance);
            System.out.println("Savings: "+savings);
            System.out.println("Loan: "+loan);

            while(running){
            
                System.out.println("\n== Transaction ==");
                System.out.println("1. Debit\n"
                               + "2. Credit\n"
                               + "3. History\n"
                               + "4. Savings\n"
                               + "5. Credit loan\n"
                               + "6. Deposit Interest Predictor\n"
                               + "7. Logout");
                System.out.print("\n>");
                int option = sc.nextInt();
            
                switch (option){
                    case 1:
                        System.out.println("\n== Debit ==");
                        System.out.print("Enter  amount: ");
                        DebitCredit[count]=sc.nextDouble();
                        sc.nextLine();
                        balance+=DebitCredit[count];
                        System.out.print("Enter description: ");
                        descDebitCredit[count]=sc.nextLine();
                        count++;
                        
                        System.out.println("\nDebit Successfully Recorded!!!\n");
                        break;
                    
                
                    case 2:
                        System.out.println("\n== Credit ==");
                        System.out.println("Enter credit amount: ");
                        DebitCredit[count]=sc.nextDouble();
                        sc.nextLine();
                        balance-=DebitCredit[count];
                        System.out.println("Enter description");
                        descDebitCredit[count]=sc.nextLine();
                        count++;

                        System.out.println("\nCredit Successfully Recorded!!!\n");
                    
                    case 3:
                        System.out.println("\n== History ==");
                        for(int i=0; i<count;i++){
                            
                        }
                    
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
            break;
            }   
        }
    }
}
