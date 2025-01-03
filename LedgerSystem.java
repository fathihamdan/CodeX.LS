import java.util.Scanner;
import org.mindrot.jbcrypt.BCrypt;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
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
        boolean exitRegistration = false;
        

        boolean LedgerSystemRunning = true;
        
        while(LedgerSystemRunning){
            while(!exitRegistration){
            
            ImageIcon icon = new ImageIcon("icon1.png");
            int LogReg=JOptionPane.showOptionDialog(null,"Welcome to Ledger System!","Ledger System",JOptionPane.YES_NO_OPTION,JOptionPane.INFORMATION_MESSAGE,icon,new String[]{"Login","Register"},"Login");


            //kalau user tekan Login button LogReg=0, kalau user tekan Register button LogReg=1
            
            //register
                if (LogReg==1){

                    regName=JOptionPane.showInputDialog("Name: ");

                    if(regName==null){
                        System.exit(0);
                    }

                    while(true){
                        
                        regEmail=JOptionPane.showInputDialog("Email: ");

                        if(regEmail==null){
                            System.exit(0);
                        }
                    
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

                        if(regPass == null){
                            System.exit(0);
                        }
                        
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
                            String hashedPass = hashPassword(regPassValid);

                            try (FileWriter writer = new FileWriter("user.csv", true)) {
                                writer.append(regName+","+regEmailValid+","+hashedPass+"\n");
                            } catch (IOException e) {
                                e.printStackTrace();
                            }


                            JOptionPane.showMessageDialog(null,"\nRegister succesful!!!\n","Ledger System",JOptionPane.INFORMATION_MESSAGE);
                            
                            break;
                        }
                        
                    }
                    
                }
                

                //login
                else if (LogReg==0){


                    boolean exitLoop=false;
                    while(!exitLoop){
                        String email=JOptionPane.showInputDialog("Email: ");
                        if(email==null){
                            System.exit(0);
                        }
                        String pass=JOptionPane.showInputDialog("Password: ");
                        if(pass==null){
                            System.exit(0);
                        }
                        

                        try (BufferedReader reader = new BufferedReader(new FileReader("user.csv"))) {
                            String line;
                            boolean found = false;
                
                            while ((line = reader.readLine()) != null) {
                                String[] parts = line.split(",");
                                if (parts[1].equalsIgnoreCase(email)&&verifyPassword(pass, parts[2])) {
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
                                    exitRegistration = true;
                                    
                                    break;
                                }
                            }
                
                            if (!found) {
                                JOptionPane.showMessageDialog(null,"\nYour email or password is wrong. Please try again","Ledger System",JOptionPane.WARNING_MESSAGE);
                            }
                        } catch (FileNotFoundException e) {
                            System.out.println("The file does not exist. Please add data first.");
                        } catch (IOException e) {
                            System.out.println("An error occurred while reading the file: " + e.getMessage());
                        }

                    }
                    
                }
                else if(LogReg==-1){
                    LedgerSystemRunning = false;
                    break;
                }
                else{
                    System.out.println("\nPlease choose between 1 or 2 only.");
                    System.out.println(LogReg);
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

            


            
            
                

                
                JOptionPane.showMessageDialog(null,"Welcome, "+regName+"! \nBalance: "+balance+"\nSavings: "+savings+"\nLoan: "+loan,"Ledger System",JOptionPane.INFORMATION_MESSAGE);

                boolean logout = false;

                while(!logout){


                    int []option ={100};
                    JFrame optionMenu = new JFrame("Ledger System");
                    optionMenu.setTitle("Ledger System");
                    optionMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    optionMenu.setSize(400, 500);
                    optionMenu.setResizable(false);
                    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                    int x = ((screenSize.width - optionMenu.getWidth()) / 2);
                    optionMenu.setLocation(x,200);
                    optionMenu.setLayout(null);

                    JLabel label = new JLabel();
                    label.setText("MENU");
                    label.setBounds(0, 10, 400, 40);
                    label.setFont(new Font("Arial", Font.BOLD, 20)); 
                    label.setHorizontalAlignment(JLabel.CENTER);
                    label.setVerticalAlignment(JLabel.TOP);

                    JButton debitButton = new JButton("Debit");
                    debitButton.setBounds(50, 60, 300, 40); 
                    debitButton.setBorder(BorderFactory.createEtchedBorder());
                    debitButton.setFocusable(false);
                    debitButton.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            option[0] = 0;
                            optionMenu.dispose();
                        }
                    });

                    JButton creditButton = new JButton("Credit");
                    creditButton.setBounds(50, 110, 300, 40); 
                    creditButton.setBorder(BorderFactory.createEtchedBorder());
                    creditButton.setFocusable(false);
                    creditButton.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            option[0] = 1;
                            optionMenu.dispose();
                        }
                    });

                    JButton historyButton = new JButton("History");
                    historyButton.setBounds(50, 160, 300, 40); 
                    historyButton.setBorder(BorderFactory.createEtchedBorder());
                    historyButton.setFocusable(false);
                    historyButton.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            option[0] = 2;
                            optionMenu.dispose();
                        }
                    });

                    JButton savingsButton = new JButton("Savings");
                    savingsButton.setBounds(50, 210, 300, 40); 
                    savingsButton.setBorder(BorderFactory.createEtchedBorder());
                    savingsButton.setFocusable(false);
                    savingsButton.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            option[0] = 3;
                            optionMenu.dispose();
                        }
                    });

                    JButton creditLoanButton = new JButton("Credit Loan");
                    creditLoanButton.setBounds(50, 260, 300, 40); 
                    creditLoanButton.setBorder(BorderFactory.createEtchedBorder());
                    creditLoanButton.setFocusable(false);
                    creditLoanButton.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            option[0] = 4;
                            optionMenu.dispose();
                        }
                    });

                    JButton DIPButton = new JButton("Deposit Interest Predictor");
                    DIPButton.setBounds(50, 310, 300, 40); 
                    DIPButton.setBorder(BorderFactory.createEtchedBorder());
                    DIPButton.setFocusable(false);
                    DIPButton.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            option[0] = 5;
                            optionMenu.dispose();
                        }
                    });

                    JButton logoutButton = new JButton("Logout");
                    logoutButton.setBounds(50, 360, 300, 40); 
                    logoutButton.setBorder(BorderFactory.createEtchedBorder());
                    logoutButton.setFocusable(false);
                    logoutButton.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            option[0] = 6;
                            optionMenu.dispose();
                        }
                    });

                    optionMenu.add(debitButton);
                    optionMenu.add(creditButton);
                    optionMenu.add(historyButton);
                    optionMenu.add(savingsButton);
                    optionMenu.add(creditLoanButton);
                    optionMenu.add(DIPButton);
                    optionMenu.add(logoutButton);

                    optionMenu.add(label);

                    ImageIcon image = new ImageIcon("Slide1.png");
                    optionMenu.setIconImage(image.getImage());

                    optionMenu.setVisible(true);

                    while (option[0] == 100) {
                        try {
                            Thread.sleep(100); 
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    System.out.println(option[0]);
                    
                    
                    
                            //        0. Debit
                            //        1. Credit
                            //        2. History
                            //        3. Savings
                            //        4. Credit loan
                            //        5. Deposit Interest Predictor
                            //        6. Logout (go back to registration page)
                            //       -1. Stop running ledger System
                
                    switch (option[0]){
                        case 0:
                            if(!HasPaidThisMonth(loanStartDate,repaymentPeriod, monthsPaid)){ //loan overdue restriction
                                    JOptionPane.showMessageDialog(null,"Cannot perform debit. Please pay your monthly repayment first.","Ledger System",JOptionPane.WARNING_MESSAGE);
                                    break;
                                }
                            
                            while (true) {
                                try {
                                    String input = JOptionPane.showInputDialog("Enter debit amount: ");
                                    if (input == null) { // Handle cancellation
                                        JOptionPane.showMessageDialog(null, "Input canceled.", "Ledger System", JOptionPane.INFORMATION_MESSAGE);
                                        break;
                                    }
                            
                                    DebitCredit[count] = Double.parseDouble(input); // Attempt to parse the input
                            
                                    if (DebitCredit[count] > 1000000000) {
                                        JOptionPane.showMessageDialog(null, "The amount exceeded 10 digits. Please try again.", "Ledger System", JOptionPane.WARNING_MESSAGE);
                                    } else if (DebitCredit[count] < 0) {
                                        JOptionPane.showMessageDialog(null, "Please insert positive values only.", "Ledger System", JOptionPane.WARNING_MESSAGE);
                                    } else {
                                        
                                        if (SavingActivated) {
                                            double savedMoney = (SavingPercent / 100) * DebitCredit[count];
                                            DebitCredit[count] = DebitCredit[count] - savedMoney;
                                            savings += savedMoney;
                                        }
                            
                                        CurrentBalance[count] = balance + DebitCredit[count];
                                        balance += DebitCredit[count];
                                        break; 
                                    }
                                } catch (NumberFormatException e) {
                                    
                                    JOptionPane.showMessageDialog(null, "Invalid input. Please enter a numeric value.", "Ledger System", JOptionPane.ERROR_MESSAGE);
                                }
                            }
                                

                            while(true){
                                
                                descDebitCredit[0][count]=JOptionPane.showInputDialog(null,"Enter description: ","Ledger System",JOptionPane.INFORMATION_MESSAGE);
                                descDebitCredit[1][count]="Debit";

                                transactionDates[count] = LocalDate.now();
                                
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
                            
                            while (true) {
                                try {
                                    String input = JOptionPane.showInputDialog(null, "Enter credit amount: ", "Ledger System", JOptionPane.INFORMATION_MESSAGE);
                            
                                    if (input == null) { 
                                        JOptionPane.showMessageDialog(null, "Input canceled.", "Ledger System", JOptionPane.INFORMATION_MESSAGE);
                                        break;
                                    }
                            
                                    DebitCredit[count] = Double.parseDouble(input); 
                            
                                    if (DebitCredit[count] > 1000000000) {
                                        JOptionPane.showMessageDialog(null, "The amount exceeded 10 digits. Please try again.", "Ledger System", JOptionPane.WARNING_MESSAGE);
                                    } else if (DebitCredit[count] < 0) {
                                        JOptionPane.showMessageDialog(null, "Please insert positive value only.", "Ledger System", JOptionPane.WARNING_MESSAGE);
                                    } else {
                                        CurrentBalance[count] = balance - DebitCredit[count];
                                        balance -= DebitCredit[count];
                                        break; 
                                    }
                                } catch (NumberFormatException e) {
                                    
                                    JOptionPane.showMessageDialog(null, "Invalid input. Please enter a numeric value.", "Ledger System", JOptionPane.ERROR_MESSAGE);
                                }
                            }
                                

                            while(true){

                                descDebitCredit[0][count]=JOptionPane.showInputDialog(null,"Enter description: ","Ledger System",JOptionPane.INFORMATION_MESSAGE);
                                descDebitCredit[1][count]="Credit";
                                transactionDates[count] = LocalDate.now();

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
                            if(SavingActivated == false){
                                int YN = JOptionPane.showOptionDialog(null,"Are you sure you want to activate it? (Y/N) : ","Ledger System",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.INFORMATION_MESSAGE,null,new String[]{"Yes","No"},"Yes");
                                if(YN == 0){
                                    
                                    while (true) {
                                        try {
                                            String input = JOptionPane.showInputDialog("Please enter the percentage you wish to deduct from your next debit:");
                                    
                                            if (input == null) { // Handle cancellation
                                                JOptionPane.showMessageDialog(null, "Input canceled.", "Ledger System", JOptionPane.INFORMATION_MESSAGE);
                                                break;
                                            }
                                    
                                            SavingPercent = Double.parseDouble(input); // Attempt to parse the input
                                    
                                            if (SavingPercent < 0 || SavingPercent > 100) {
                                                JOptionPane.showMessageDialog(null, "Please enter a valid percentage between 0 and 100.", "Ledger System", JOptionPane.WARNING_MESSAGE);
                                            } else {
                                                JOptionPane.showMessageDialog(null, "Savings settings added successfully!!!\n" + SavingPercent + "% will be auto-deducted from your next debit.");
                                                SavingActivated = true;
                                                break; // Exit loop after successful input and processing
                                            }
                                        } catch (NumberFormatException e) {
                                            JOptionPane.showMessageDialog(null, "Invalid input. Please enter a numeric value.", "Ledger System", JOptionPane.ERROR_MESSAGE);
                                        }
                                    }
                                    
                                    break;
                                } 
                                else if(YN == 1){
                                    SavingActivated = false;
                                    break;
                                }
                                else if(YN==-1){
                                    
                                    break;
                                }
                            }
                            else if(SavingActivated == true){
                                int YN = JOptionPane.showOptionDialog(null,"You have already activated Savings.\nCurrent saving percentage: "+SavingPercent+"\nWould you like to deactivate it?","Ledger System",JOptionPane.YES_NO_OPTION,JOptionPane.INFORMATION_MESSAGE,null,new String[]{"Yes","No"},"Yes");
                                if(YN==0){
                                    SavingActivated = false;
                                    SavingPercent = 0.0;
                                    System.out.println("Saving deactivated successfully");
                                    break;
                                }
                                else if(YN==1){
                                    break;
                                }
                                else if(YN==-1){
                                    break;
                                }
                            }
                            break;
                        
                        case 4:

                            int choice = JOptionPane.showOptionDialog(null,"Credit Loan","Ledger System",JOptionPane.YES_NO_OPTION,JOptionPane.INFORMATION_MESSAGE,null,new String[]{"Apply","Repay"},"Apply");
                    
                            
                            if(choice == 0){ 
                                double P = 0;

                                while (true) {
                                    try {
                                        String input = JOptionPane.showInputDialog("Enter the total amount of money you want to take a loan:");

                                        if (input == null) { 
                                            JOptionPane.showMessageDialog(null, "Input canceled.", "Ledger System", JOptionPane.INFORMATION_MESSAGE);
                                            break;
                                        }

                                        P = Double.parseDouble(input); 

                                        if (P <= 0) {
                                            JOptionPane.showMessageDialog(null, "Please enter a positive loan amount.", "Ledger System", JOptionPane.WARNING_MESSAGE);
                                        } else {
                                            JOptionPane.showMessageDialog(null, String.format("Loan amount entered: %.2f", P), "Ledger System", JOptionPane.INFORMATION_MESSAGE);
                                            break; 
                                        }
                                    } catch (NumberFormatException e) {
                                        JOptionPane.showMessageDialog(null, "Invalid input. Please enter a numeric value.", "Ledger System", JOptionPane.ERROR_MESSAGE);
                                    }
                                }


                                double InterestRate = 0;

                                while (true) {
                                    try {
                                        String input = JOptionPane.showInputDialog("Enter the interest rate (%): ");

                                        if (input == null) { 
                                            JOptionPane.showMessageDialog(null, "Input canceled.", "Ledger System", JOptionPane.INFORMATION_MESSAGE);
                                            break;
                                        }

                                        InterestRate = Double.parseDouble(input); 

                                        if (InterestRate < 0) { 
                                            JOptionPane.showMessageDialog(null, "Please enter a positive interest rate.", "Ledger System", JOptionPane.WARNING_MESSAGE);
                                        } else {
                                            JOptionPane.showMessageDialog(null, String.format("Interest rate entered: %.2f%%", InterestRate), "Ledger System", JOptionPane.INFORMATION_MESSAGE);
                                            break; 
                                        }
                                    } catch (NumberFormatException e) {
                                        JOptionPane.showMessageDialog(null, "Invalid input. Please enter a numeric value.", "Ledger System", JOptionPane.ERROR_MESSAGE);
                                    }
                                }

                                
                                

                                while (true) {
                                    try {
                                        String input = JOptionPane.showInputDialog("Enter the repayment period (in months):");

                                        if (input == null) { 
                                            JOptionPane.showMessageDialog(null, "Input canceled.", "Ledger System", JOptionPane.INFORMATION_MESSAGE);
                                            break;
                                        }

                                        repaymentPeriod = Integer.parseInt(input); 

                                        if (repaymentPeriod <= 0) { 
                                            JOptionPane.showMessageDialog(null, "Please enter a positive number of months.", "Ledger System", JOptionPane.WARNING_MESSAGE);
                                        } else {
                                            JOptionPane.showMessageDialog(null, String.format("Repayment period set to %d months.", repaymentPeriod), "Ledger System", JOptionPane.INFORMATION_MESSAGE);
                                            break; 
                                        }
                                    } catch (NumberFormatException e) {
                                        JOptionPane.showMessageDialog(null, "Invalid input. Please enter a numeric value.", "Ledger System", JOptionPane.ERROR_MESSAGE);
                                    }
                                }

                                
                                loanStartDate = CurrentDate;
                                
                                double r= (InterestRate/(100*12));
                                double totalrepayment= (P*(r*Math.pow((1+r),repaymentPeriod)/(Math.pow((1+r),repaymentPeriod)-1)))*repaymentPeriod;
                                loan = Math.round(totalrepayment * 100.0) / 100.0;// assign total repayment to loan
                                monthlyRepayment = Math.round((totalrepayment/repaymentPeriod)*100.0) / 100.0;
                                monthsPaid = 0;
                                
                                
                                JOptionPane.showMessageDialog(null,"Your loan has been authorized!"+String.format("\nTotal repayment amount: %.2f",totalrepayment)+String.format("\nMonthly repayment : %.2f\n", monthlyRepayment ));
                                
                            }else if(choice ==1){ //repay loan
                                if(loan >0){

                                    double repayment = 0;

                                    while (true) {
                                        try {
                                            String input = JOptionPane.showInputDialog(String.format("Monthly repayment : %.2f\nEnter the amount you want to repay", monthlyRepayment));

                                            if (input == null) { 
                                                JOptionPane.showMessageDialog(null, "Input canceled.", "Ledger System", JOptionPane.INFORMATION_MESSAGE);
                                                break;
                                            }

                                            repayment = Double.parseDouble(input); 

                                            if (repayment <= 0) { 
                                                JOptionPane.showMessageDialog(null, "Please enter a positive repayment amount.", "Ledger System", JOptionPane.WARNING_MESSAGE);
                                            } else {
                                                JOptionPane.showMessageDialog(null, String.format("Repayment amount: %.2f", repayment), "Ledger System", JOptionPane.INFORMATION_MESSAGE);
                                                break; 
                                            }
                                        } catch (NumberFormatException e) {
                                            JOptionPane.showMessageDialog(null, "Invalid input. Please enter a numeric value.", "Ledger System", JOptionPane.ERROR_MESSAGE);
                                        }
                                    }

                                    
                                    if(repayment == monthlyRepayment){
                                        loan -= repayment;
                                        loan = Math.max(loan,0.0); //to make sure loan doesn't go negative
                                        monthsPaid++;
                                        
                                        JOptionPane.showMessageDialog(null,String.format("Repayment successful!! Remaining loan balance : %.2f\n", loan));
                                        
                                        if(loan==0){
                                            JOptionPane.showMessageDialog(null,"Congratulations! Your loan has been fully repaid");
                                        }
                                        
                                    }else if(repayment < monthlyRepayment){
                                        JOptionPane.showMessageDialog(null,"Insufficient repayment. Please pay the exact monthly repayment amount.");                                        
                                    }else{
                                        JOptionPane.showMessageDialog(null,"Overpayment is not allowed. Please pay the exact monthly repayment amount.");           
                                        }
                                    
                                }else{
                                    JOptionPane.showMessageDialog(null,"No active loan to repay");
                                }
                            }
                            break;
                        
                        case 5:
                        while (true) {
                            try {

                                String[] banks = {"RHB", "Maybank", "Hong Leong", "Alliance", "AmBank", "Standard Chartered"};
                                double[] INTEREST = {2.60, 2.50, 2.30, 2.85, 2.55, 2.65};
                        
                                System.out.printf("%-30s %s%n", "Bank", "Interest Rate (%)");
                                System.out.println("------------------------------------------------");
                       
                                for (int i = 0; i < banks.length; i++) {
                                System.out.printf("%-30s %6.2f%n", banks[i], INTEREST[i]);}
                                           
                                System.out.println("------------------------------------------------");
                                
                                String input = JOptionPane.showInputDialog("Enter bank interest rate (%): ");
                    
                                if (input == null) { 
                                    JOptionPane.showMessageDialog(null, "Input canceled.", "Ledger System", JOptionPane.INFORMATION_MESSAGE);
                                    break;
                                }
                    
                                double rate = Double.parseDouble(input); 
                    
                                if (rate < 0) {
                                    JOptionPane.showMessageDialog(null, "Please enter a positive interest rate.", "Ledger System", JOptionPane.WARNING_MESSAGE);
                                } else {
                                    double interest = (balance * (rate / 100)) / 12;
                                    JOptionPane.showMessageDialog(null, String.format("Predicted interest monthly: %.2f", interest));
                                    break; 
                                }
                            } catch (NumberFormatException e) {
                                
                                JOptionPane.showMessageDialog(null, "Invalid input. Please enter a numeric value.", "Ledger System", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                        break;
                        

                        //logout
                        case 6:
                            System.out.println("Thank you for using Ledger System!");
                            
                            logout = true;
                            break;


                        case -1:
                            
                            logout = true;
                            LedgerSystemRunning = false;
                            
                            break;

                        default:
                            System.out.println("Invalid option. Please try again.");
                            break;
                    }
                    
                }   
        
        }
        System.out.println("Ledger System has stop running");   
    }
    
    //Generate hashed version of the password
    public static String hashPassword(String plainPass) {
        return BCrypt.hashpw(plainPass, BCrypt.gensalt(12));
        //gensalt() make it more secure
    }

    // Verify a password
    public static boolean verifyPassword(String plainPass, String hashedPass) {
        return BCrypt.checkpw(plainPass, hashedPass);
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

                String[][] data = new String[5][100];
                for (int i = 0; i < count; i++) {
                    Transaction t = transactions.get(i);
                    data[i][0]=String.format("%d",i+1);
                    data[i][1]=String.format("%s",t.date);
                    data[i][2]=t.description;
                    data[i][3]=String.format("%.2f",t.amount);
                    data[i][4]=t.type;
                }
                
                JFrame history = new JFrame("Ledger System");
                history.setTitle("Ledger System");
                history.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                history.setSize(800, 500);
                history.setResizable(false);
                Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                int x = ((screenSize.width - history.getWidth()) / 2);
                history.setLocation(x,200);
                history.setLayout(null);

                JLabel label = new JLabel();
                label.setText("HISTORY");
                label.setBounds(2, 0, 800, 40);
                label.setFont(new Font("Arial", Font.BOLD, 15)); 
                label.setHorizontalAlignment(JLabel.CENTER);


                String[] column = {"No.", "Date", "Description", "Amount", "Type"};

                JTable table = new JTable(data, column);

                JScrollPane scp = new JScrollPane(table);
                scp.setBounds(45,50,700,350);


                int []option ={100};
                JButton OK = new JButton("OK");
                OK.setBounds(350, 420, 100, 30); 
                OK.setBorder(BorderFactory.createEtchedBorder());

                OK.setFocusable(false);
                OK.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        option[0] = 6;
                        history.dispose();
                    }
                });

                ImageIcon image = new ImageIcon("icon1.png");
                history.setIconImage(image.getImage());

                history.add(scp);
                history.add(label);
                history.add(OK);

                history.setVisible(true);

                while (option[0] == 100) {
                    try {
                        Thread.sleep(100); 
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                break;
                
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

