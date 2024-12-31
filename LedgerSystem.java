import java.util.Scanner;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import javax.swing.*;
import java.util.ArrayList;

import java.io.*;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.InputMismatchException;

public class LedgerSystem {


    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        LocalDate CurrentDate = LocalDate.now();
        
        //User Registration Validation


        String regName=" ";
        String regEmail=" ";
        String regPass=" ";
        String regEmailValid=" ";
        String regPassValid=" ";
        LocalDate loanStartDate = null;
        int repaymentPeriod =0;
        Double loan = 0.0;
        int monthsPaid =0;
        
        while(true){
            
            int LogReg=JOptionPane.showOptionDialog(null,"Welcome to Ledger System!","Ledger System",JOptionPane.YES_NO_OPTION,JOptionPane.INFORMATION_MESSAGE,null,new String[]{"Login","Register"},"Login");


            //kalau user tekan Login button LogReg=0, kalau user tekan Register button LogReg=1
            if (LogReg==1){

                regName=JOptionPane.showInputDialog("Name: ");


                while(true){
                    
                    regEmail=JOptionPane.showInputDialog("Email: ");
                
                    if(regEmail.endsWith("@gmail.com")){
                        regEmailValid=regEmail;
                        break;
                    }
                    else if(regEmail.endsWith("@hotmail.com")){
                        regEmailValid=regEmail;
                        break;
                    }
                    else if(regEmail.endsWith("@yahoo.com")){
                        regEmailValid=regEmail;
                        break;
                    }
                    else if(regEmail.endsWith("@outlook.com")){
                        regEmailValid=regEmail;
                        break;
                    }
                    
                    else{

                        JOptionPane.showMessageDialog(null, "Email must be in correct format(name@gmail.com)","Ledger System",JOptionPane.WARNING_MESSAGE);
                    }
                } 
                
                while(true){
                    
                    regPass=JOptionPane.showInputDialog("Password: ");
                    
                    if (regPass.length()<8){
                        JOptionPane.showMessageDialog(null,"\nPassword must be at least 8 characters.\n","Ledger System",JOptionPane.WARNING_MESSAGE);
                    }
                    
                    boolean letter=false;
                    boolean digit=false;
                    boolean spec=false;
                    boolean moreThan8Characters=false;
                    
                    for(int i=0; i<regPass.length(); i++){
                        char charac=regPass.charAt(i);
                        
                        if (Character.isLetter(charac))
                            letter=true;
                        else if (Character.isDigit(charac))
                            digit=true;
                        else if (!Character.isLetterOrDigit(charac))
                            spec=true;
                    }
                    
                    if(regPass.length()>=8){
                        moreThan8Characters=true;
                    }
                    
                    
                    if(!letter){
                        JOptionPane.showMessageDialog(null,"\nPassword must contain at least one letter.\n","Ledger System",JOptionPane.WARNING_MESSAGE);
                    }
                    else if(!digit){
                        JOptionPane.showMessageDialog(null,"\nPassword must contain at least one digit.\n","Ledger System",JOptionPane.WARNING_MESSAGE);
                    }
                    else if(!spec){
                        JOptionPane.showMessageDialog(null,"\nPassword must contain at least one special character.\n","Ledger System",JOptionPane.WARNING_MESSAGE);
                    }
                    else if(!moreThan8Characters){
                        JOptionPane.showMessageDialog(null,"\nPassword must be at least 8 characters.\n","Ledger System",JOptionPane.WARNING_MESSAGE);
                    
                    }
                    else {
                        regPassValid=regPass;

                        try (FileWriter writer = new FileWriter("user.csv", true)) {
                            writer.append(regName+","+regEmailValid+","+regPassValid+"\n");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }


                        JOptionPane.showMessageDialog(null,"\nRegister succesful!!!\n","Ledger System",JOptionPane.INFORMATION_MESSAGE);
                        break;
                    }
                    
                }
            }
            
            else if (LogReg==0){


                boolean exitLoop=false;
                while(!exitLoop){
                    String email=JOptionPane.showInputDialog("Email: ");
                    String pass=JOptionPane.showInputDialog("Password: ");
                    

                    try (BufferedReader reader = new BufferedReader(new FileReader("user.csv"))) {
                        String line;
                        boolean found = false;
            
                        while ((line = reader.readLine()) != null) {
                            String[] parts = line.split(",");
                            if (parts[1].equalsIgnoreCase(email)&&parts[2].equals(pass)) {
                                JOptionPane.showMessageDialog(null,"\nLogin Successful!!!\n","Ledger System",JOptionPane.INFORMATION_MESSAGE);
                                regName=parts[0];
                                regEmail=parts[1];
                                found = true;

                                //reminder for loan repayment
                                if(loan >0 && loanStartDate != null && !HasPaidThisMonth(loanStartDate, repaymentPeriod, monthsPaid)){
                                    LocalDate dueDate = loanStartDate.plusMonths(repaymentPeriod);
                                    long DaysUntilDue = ChronoUnit.DAYS.between(LocalDate.now(), dueDate);
                                    
                                    System.out.printf("REMINDER!! Your loan repayment is due in %d days (Due Date: %s).\nPlease pay your monthly repayment.", DaysUntilDue,dueDate);
                                }

                                exitLoop=true;
                                break;
                            }
                        }
            
                        if (!found) {
                            JOptionPane.showMessageDialog(null,"\nYour email or password is wrong.","Ledger System",JOptionPane.WARNING_MESSAGE);
                        }
                    } catch (FileNotFoundException e) {
                        System.out.println("The file does not exist. Please add data first.");
                    } catch (IOException e) {
                        System.out.println("An error occurred while reading the file: " + e.getMessage());
                    }

                }
                break;
            }
            else{
                System.out.println("\nPlease choose between 1 or 2 only.");
            }
        }

        //Aqil&&Fathi
        
        Double []DebitCredit=new Double[100];    //Combine both Debit adn Credit in one array to ease the order of transaction(but it is limited to 100 transactions only)
        String [][]descDebitCredit=new String[2][100]; /*Two collums of array of Transaction description. 
                                                        first collum is to label whether it is a Debit or Credit. 
                                                        the other is to store the description
                                                        */
        LocalDate[] transactionDates = new LocalDate[100];
        int count=0;
        boolean running = true;
        Double balance =0.0;
        Double [] CurrentBalance=new Double[100];
        Double savings =0.0;
        Double SavingPercent = 0.0;
        boolean SavingActivated = false;
        double monthlyRepayment = 0.0;

        
        while(true){
            

            
            JOptionPane.showMessageDialog(null,"Welcome, "+regName+"! \nBalance: "+balance+"\nSavings: "+savings+"\nLoan: "+loan,"Ledger System",JOptionPane.INFORMATION_MESSAGE);

            while(running){
                
                int option=JOptionPane.showOptionDialog(null,"Welcome to Ledger System!","Menu",JOptionPane.CLOSED_OPTION,JOptionPane.INFORMATION_MESSAGE,null,new String[]{"Debit","Credit","History","Savings","Credit Loan","Deposit Interest Predictor","Logout"},"Debit");
                
                        //        0. Debit
                        //        1. Credit
                        //        2. History
                        //        3. Savings
                        //        4. Credit loan
                        //        5. Deposit Interest Predictor
                        //        6. Logout
            
                switch (option){
                    case 0:
                        if(!HasPaidThisMonth(loanStartDate,repaymentPeriod, monthsPaid)){ //loan overdue restriction
                                JOptionPane.showMessageDialog(null,"Cannot perform debit. Please pay your monthly repayment first.","Ledger System",JOptionPane.WARNING_MESSAGE);
                                break;
                            }
                        
                        while(true){



                            DebitCredit[count]=Double.parseDouble(JOptionPane.showInputDialog("Enter debit amount: "));

                            if(DebitCredit[count]>1000000000){
                                JOptionPane.showMessageDialog(null,"The amount exceeded 10 digits. Please try again.","Ledger System",JOptionPane.WARNING_MESSAGE);
                            }
                            else if(DebitCredit[count]<0){
                                JOptionPane.showInputDialog(null,"Please insert positive value only.","Ledger System",JOptionPane.WARNING_MESSAGE);    
                            }
                            else{

                                //if savings was activated on option 4, these lines of code will run
                                if(SavingActivated){
                                    double savedMoney = (SavingPercent/100)*DebitCredit[count];
                                    DebitCredit[count]= DebitCredit[count]-savedMoney;
                                    savings+=savedMoney;
                                    
                                }

                                
                                CurrentBalance[count]=balance+DebitCredit[count];
                                balance+=DebitCredit[count];
                                break;
                            }
                        }

                        while(true){
                            
                            descDebitCredit[0][count]=JOptionPane.showInputDialog(null,"Enter description: ","Ledger System",JOptionPane.INFORMATION_MESSAGE);
                            descDebitCredit[1][count]="Debit";
                            
                            if(descDebitCredit[0][count].length()>20){
                                JOptionPane.showMessageDialog(null,"Transaction description exceeded 20 characters. Please try again.","Ledger System",JOptionPane.WARNING_MESSAGE);
                                
                            }
                            else{
                                JOptionPane.showMessageDialog(null,"\nDebit Successfully Recorded!!!\n","Ledger System",JOptionPane.INFORMATION_MESSAGE);
                                count++;
                                break;
                            }
                        }
                        break;
                        
                
                    case 1:
                        if(!HasPaidThisMonth(loanStartDate,repaymentPeriod, monthsPaid)){ //loan overdue restriction
                                JOptionPane.showMessageDialog(null,"Cannot perform credit. Please pay your monthly repayment first.","Ledger System",JOptionPane.WARNING_MESSAGE);
                                break;
                            }
                        
                        while(true){

                            DebitCredit[count]=Double.parseDouble(JOptionPane.showInputDialog(null,"Enter credit amount: ","Ledger System",JOptionPane.INFORMATION_MESSAGE));

                            if(DebitCredit[count]>1000000000){
                                JOptionPane.showMessageDialog(null, "The amount exceeded 10 digits. Please try again.","Ledger System",JOptionPane.WARNING_MESSAGE);
                            }
                            else if(DebitCredit[count]<0){
                                JOptionPane.showMessageDialog(null,"Please insert positive value only.","Ledger System",JOptionPane.WARNING_MESSAGE);
                            }
                            else{
                                CurrentBalance[count]=balance-DebitCredit[count];
                                balance-=DebitCredit[count];
                                break;
                            }
                        }

                        while(true){

                            descDebitCredit[0][count]=JOptionPane.showInputDialog(null,"Enter description: ","Ledger System",JOptionPane.INFORMATION_MESSAGE);
                            descDebitCredit[1][count]="Credit";

                            if(descDebitCredit[0][count].length()>20){
                                JOptionPane.showMessageDialog(null,"Transaction description exceeded 20 characters. Please try again.","Ledger System",JOptionPane.WARNING_MESSAGE);
                            }
                            else{
                                JOptionPane.showMessageDialog(null,"\nCredit Successfully Recorded!!!\n","Ledger System",JOptionPane.INFORMATION_MESSAGE);
                                count++;
                                break;
                            }
                        }
                        break;
                    
                
                    case 2:

                        filterAndSortHistory(sc, DebitCredit, descDebitCredit, transactionDates, count);
                        break;
                    
                    case 3:
                        System.out.println("== Savings ==");
                        if(SavingActivated == false){
                            System.out.print("Are you sure you want to activate it? (Y/N) : ");
                            String YesNo = sc.next();
                            sc.nextLine();
                            if(YesNo.equalsIgnoreCase("Y")){
                                SavingActivated = true;
                                System.out.print("Please enter the percentage you wish to deduct from your next debit: ");
                                SavingPercent = sc.nextDouble();
                                sc.nextLine();
                                System.out.println(SavingPercent+"% will be auto deduct from your next debit.");
                                System.out.println("Savings settings added successfully!!!");
                                SavingActivated = true;
                                break;
                            } 
                            else if(YesNo.equalsIgnoreCase("N")){
                                SavingActivated = false;
                                break;
                            }
                            else{
                                System.out.println("Wrong input sucker!");
                                break;
                            }
                        }
                        else if(SavingActivated == true){
                            System.out.println("You have already activated Savings.");
                            System.out.println("Current saving percentage: "+SavingPercent);
                            System.out.println("Would you like to deactivate it?");
                            String YN = sc.nextLine();
                            sc.nextLine();
                            if(YN.equalsIgnoreCase("Y")){
                                SavingActivated = false;
                                SavingPercent = 0.0;
                                System.out.println("Saving deactivated successfully");
                                break;
                            }
                            else if(YN.equalsIgnoreCase("N")){
                                break;
                            }
                            else{
                                System.out.println("Wrong input sucker!");
                            }
                        }
                        break;
                    
                    case 4:
                        System.out.println("\n== Credit Loan ==");
                        System.out.println("1. Apply");
                        System.out.println("2. Repay");
                        System.out.print("\n>");
                        int choice = sc.nextInt();
                        sc.nextLine();                        
                        
                        if(choice == 1){ //apply loan
                            System.out.print("Enter the total amount of money you want to take a loan: ");
                            double P = validatepositiveinput(sc); // principal
                            System.out.print("Enter the interest rate(%): ");
                            double InterestRate = validatepositiveinput(sc);
                            System.out.print("Enter the repayment period (in months): ");
                            repaymentPeriod = (int) validatepositiveinput(sc);
                            
                            loanStartDate = CurrentDate;
                            
                            double r= (InterestRate/(100*12));
                            double totalrepayment= (P*(r*Math.pow((1+r),repaymentPeriod)/(Math.pow((1+r),repaymentPeriod)-1)))*repaymentPeriod;
                            loan = Math.round(totalrepayment * 100.0) / 100.0;// assign total repayment to loan
                            monthlyRepayment = Math.round((totalrepayment/repaymentPeriod)*100.0) / 100.0;
                            monthsPaid = 0;
                            
                            System.out.println("\nYour loan has been authorized!");
                            System.out.printf("Total repayment amount: %.2f\n",totalrepayment);
                            System.out.printf("Monthly repayment : %.2f\n", monthlyRepayment );                                                                                                                
                            
                        }else if(choice ==2){ //repay loan
                            if(loan >0){
                                System.out.printf("Monthly repayment: %.2f\n",monthlyRepayment);
                                System.out.print("Enter the amount you want to repay :");
                                double repayment = validatepositiveinput(sc);
                                
                                if(repayment == monthlyRepayment){
                                    loan -= repayment;
                                    loan = Math.max(loan,0.0); //to make sure loan doesn't go negative
                                    monthsPaid++;
                                    
                                    System.out.printf("Repayment successful!! Remaining loan balance : %.2f\n", loan);
                                    
                                    if(loan==0){
                                        System.out.println("Congratulations! Your loan has been fully repaid");
                                    }
                                    
                                }else if(repayment < monthlyRepayment){
                                    System.out.println("Insufficient repayment. Please pay the exact monthly repayment amount.");                                        
                                }else{
                                    System.out.println("Overpayment is not allowed. Please pay the exact monthly repayment amount.");           
                                    }
                                
                            }else{
                                System.out.println("No active loan to repay");
                            }
                        }
                        System.out.println();
                        break;
                    
                    case 5:
                        System.out.println("\n== Deposit Interest Predictor ==");                       
                        System.out.print("Enter bank interest rate(%): ");
                        double rate = validatepositiveinput(sc);                       
                        
                        double interest = (balance*(rate/100))/12;
                        System.out.printf("Predicted interest monthly: %.2f\n", interest);
                        break;

                    case 6:
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
    public static boolean HasPaidThisMonth(LocalDate loanStartDate, int repaymentPeriod, int monthsPaid){
        if(loanStartDate == null || monthsPaid >= repaymentPeriod){
            return true; // no loan or fully paid
        }
        LocalDate dueDate = loanStartDate.plusMonths(monthsPaid);
        return !LocalDate.now().isAfter(dueDate);
    }

    public static double validatepositiveinput(Scanner sc){
        double value;
        while(true){
            value = sc.nextDouble();
            if(value>0){
                break;
            }else{
                System.out.print("Invalid input.Please enter a positive value: ");
            }            
        }
        return value;
    }

    public static void filterAndSortHistory(Scanner sc, Double[] DebitCredit, String[][] descDebitCredit, LocalDate[] transactionDates, int count) {
        List<Transaction> transactions = new ArrayList<>();
    
        // Collect transactions
        for (int i = 0; i < count; i++) {
            transactions.add(new Transaction(transactionDates[i], descDebitCredit[0][i], DebitCredit[i], descDebitCredit[1][i]));
        }
    
        while (true) {
            try {
                // History options

                int historyOption = JOptionPane.showOptionDialog(null,"History","Ledger System",JOptionPane.CLOSED_OPTION,JOptionPane.INFORMATION_MESSAGE,null,new String[]{"Filter","Sort","View All","Back"},"Filter");

    
                switch (historyOption) {
                    // Filter option
                    case 0:

                        int filterOption = JOptionPane.showOptionDialog(null,"Enter filter option: ","Ledger System",JOptionPane.CLOSED_OPTION,JOptionPane.INFORMATION_MESSAGE,null,new String[]{"By Date","By Amount","By Transaction","Back"},"By Date");

                        
                        switch (filterOption) {

                            //by date
                            case 0:
                                
                                LocalDate startDate = LocalDate.parse(JOptionPane.showInputDialog(null,"Enter start date (YYYY-MM-DD): ","History",JOptionPane.INFORMATION_MESSAGE));
                                
                                LocalDate endDate = LocalDate.parse(JOptionPane.showInputDialog(null,"Enter end date (YYYY-MM-DD): ","History",JOptionPane.INFORMATION_MESSAGE));
                                transactions.removeIf(t -> t.date.isBefore(startDate) || t.date.isAfter(endDate.plusDays(1)));
                                break;
    
                            //by amount
                            case 1:
                                
                                double minAmount = Double.parseDouble(JOptionPane.showInputDialog(null,"Enter minimum amount: ","History",JOptionPane.INFORMATION_MESSAGE));
                                
                                double maxAmount = Double.parseDouble(JOptionPane.showInputDialog(null,"Enter maximum amount: ","History",JOptionPane.INFORMATION_MESSAGE));
                                transactions.removeIf(t -> t.amount < minAmount || t.amount > maxAmount);
                                break;
    
                            //by transaction
                            case 2:
                                
                                int input = JOptionPane.showOptionDialog(null,"Enter transaction type (Debit/Credit): ","History",JOptionPane.CLOSED_OPTION,JOptionPane.INFORMATION_MESSAGE,null,new String[]{"Debit","Credit"},"Debit");
                                String type = "";
                                if(input == 0){
                                    type = "Debit";
                                    transactions.removeIf(t -> !t.type.equalsIgnoreCase("Debit"));
                                }else if(input == 1){
                                    type = "Credit";
                                    transactions.removeIf(t -> !t.type.equalsIgnoreCase("Credit"));
                                }
                                break;
                                
                            //back
                            case 3:
                                return;
    
                            default:
                                JOptionPane.showMessageDialog(null,"Invalid option.","Ledger System",JOptionPane.WARNING_MESSAGE);
                                return;
                        }
                        break;
    
                    // Sorting option    
                    case 1:

                        int input = JOptionPane.showOptionDialog(null,"Enter sort option: ","Sorting",JOptionPane.CLOSED_OPTION,JOptionPane.INFORMATION_MESSAGE,null,new String[]{"By Date","By Amount","Back"},"By Date (Newest to Oldest)");
                        
                        int sortOption;
                        
                        //by date
                        if(input==0){
                            sortOption = JOptionPane.showOptionDialog(null,"Sort by date","Sorting",JOptionPane.CLOSED_OPTION,JOptionPane.INFORMATION_MESSAGE,null,new String[]{"Newest to Oldest","Oldest to Newest"},"Newest to Oldest");
                        
                            switch (sortOption) {

                                //newest to oldest
                                case 0:
                                    transactions.sort(Comparator.comparing(Transaction::getDate).reversed());
                                    break;
        
                                //oldest to newest
                                case 1:
                                    transactions.sort(Comparator.comparing(Transaction::getDate));
                                    break;
        
                                default:
                                    System.out.println("Invalid option.");
                                    return;
                            }
                            break;
                        }

                        //by amount
                        else if(input==1){
                            sortOption = JOptionPane.showOptionDialog(null,"Sort by date","Sorting",JOptionPane.CLOSED_OPTION,JOptionPane.INFORMATION_MESSAGE,null,new String[]{"Highest to Lowest","Lowest to Highest"},"Highest to Lowest");
                        
                            switch (sortOption) {

                                //highest to lowest
                                case 0:
                                    transactions.sort(Comparator.comparing(Transaction::getAmount).reversed());
                                    break;
        

                                //lowest to highest
                                case 1:
                                    transactions.sort(Comparator.comparing(Transaction::getAmount));
                                    break;

                                default:
                                    System.out.println("Invalid option.");
                                    return;
                            }
                            break;
                        }else if(input==2){
                            return;
                        }

    
    
                    case 2:
                        // Display all transactions
                        break;
    
                    case 3:
                        // No filter applied
                        return;
    
                    default:
                        System.out.println("Invalid option.");
                        return;
                }
    
                // Display filtered or sorted transactions
                System.out.printf("%-10s%-20s%-20s%-15s%-15s\n", "No.", "Date", "Description", "Amount", "Type");
                for (int i = 0; i < transactions.size(); i++) {
                    Transaction t = transactions.get(i);
                    System.out.printf("%-10d%-20s%-20s%-15.2f%-15s\n", (i + 1), t.date, t.description, t.amount, t.type);
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                sc.nextLine(); // Clear the invalid input
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
            }
        }
    }
    
    static class Transaction {
    
        LocalDate date;
        String description;
        double amount;
        String type;
    
        Transaction(LocalDate date, String description, double amount, String type) {
            this.date = date;
            this.description = description;
            this.amount = amount;
            this.type = type;
        }
    
        public LocalDate getDate() {
            return date;
        }
    
        public double getAmount() {
            return amount;
        }
    }
}

