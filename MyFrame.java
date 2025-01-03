import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class MyFrame {
    public static void main(String[] args) {
int []option ={100};

String[] banks = {"RHB", "Maybank", "Hong Leong", "Alliance", "AmBank", "Standard Chartered"};
double[] INTEREST = {2.60, 2.50, 2.30, 2.85, 2.55, 2.65};

// JFrame loanFrame = new JFrame("Ledger System");
// loanFrame.setTitle("Ledger System");
// loanFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
// loanFrame.setSize(400, 500);
// loanFrame.setResizable(false);
// Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
// int x = ((screenSize.width - loanFrame.getWidth()) / 2);
// loanFrame.setLocation(x,200);
// loanFrame.setLayout(null);


// JLabel ask = new JLabel();
// ask.setText("Please select your bank name");
// ask.setBounds(20, 20, 300, 40);



// loanFrame.add(ask); 
// loanFrame.setVisible(true);




                    JFrame optionMenu = new JFrame("Ledger System");
                    optionMenu.setTitle("Ledger System");
                    optionMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    optionMenu.setSize(400, 500);
                    optionMenu.setResizable(false);
                    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                    int x = ((screenSize.width - optionMenu.getWidth()) / 2);
                    optionMenu.setLocation(x,200);

                    CardLayout cardLayout = new CardLayout();
                    JPanel mainPanel = new JPanel(cardLayout);

                    JPanel menuPanel = new JPanel();



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
                            // optionMenu.dispose();
                        }
                    });

                    JButton creditButton = new JButton("Credit");
                    creditButton.setBounds(50, 110, 300, 40); 
                    creditButton.setBorder(BorderFactory.createEtchedBorder());
                    creditButton.setFocusable(false);
                    creditButton.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            option[0] = 1;
                            
                        }
                    });

                    JButton historyButton = new JButton("History");
                    historyButton.setBounds(50, 160, 300, 40); 
                    historyButton.setBorder(BorderFactory.createEtchedBorder());
                    historyButton.setFocusable(false);
                    historyButton.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            option[0] = 2;
                            
                        }
                    });

                    JButton savingsButton = new JButton("Savings");
                    savingsButton.setBounds(50, 210, 300, 40); 
                    savingsButton.setBorder(BorderFactory.createEtchedBorder());
                    savingsButton.setFocusable(false);
                    savingsButton.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            option[0] = 3;
                            
                        }
                    });

                    JButton creditLoanButton = new JButton("Credit Loan");
                    creditLoanButton.setBounds(50, 260, 300, 40); 
                    creditLoanButton.setBorder(BorderFactory.createEtchedBorder());
                    creditLoanButton.setFocusable(false);
                    creditLoanButton.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            option[0] = 4;
                            
                        }
                    });

                    JButton DIPButton = new JButton("Deposit Interest Predictor");
                    DIPButton.setBounds(50, 310, 300, 40); 
                    DIPButton.setBorder(BorderFactory.createEtchedBorder());
                    DIPButton.setFocusable(false);
                    DIPButton.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            option[0] = 5;
                            cardLayout.show(mainPanel,"DIP");
                            
                        }
                    });

                    JButton logoutButton = new JButton("Logout");
                    logoutButton.setBounds(50, 360, 300, 40); 
                    logoutButton.setBorder(BorderFactory.createEtchedBorder());
                    logoutButton.setFocusable(false);
                    logoutButton.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            option[0] = 6;
                            
                        }
                    });



                    menuPanel.add(debitButton);
                    menuPanel.add(creditButton);
                    menuPanel.add(historyButton);
                    menuPanel.add(savingsButton);
                    menuPanel.add(creditLoanButton);
                    menuPanel.add(DIPButton);
                    menuPanel.add(logoutButton);

                    menuPanel.add(label);

                    JPanel DIPPanel = new JPanel();
                    JLabel DIPLabel = new JLabel("Deposit Interest Predictor");
                    DIPLabel.setBounds(0, 10, 400, 40);
                    DIPLabel.setFont(new Font("Arial", Font.BOLD, 20));
                    DIPLabel.setHorizontalAlignment(JLabel.CENTER);

                    JButton backButton = new JButton("Back");
                    


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
                    optionMenu.setVisible(false);
                    optionMenu.remove(debitButton);
                    optionMenu.remove(creditButton);
                    optionMenu.remove(historyButton);
                    optionMenu.remove(savingsButton);
                    optionMenu.remove(creditLoanButton);
                    optionMenu.remove(DIPButton);
                    optionMenu.remove(logoutButton);
                    optionMenu.remove(label);

                    switch (option[0]){
                        case 5:
                            JLabel DIP = new JLabel();
                            DIP.setText("Deposit Interest Predictor");
                            DIP.setBounds(0, 10, 400, 40);
                            DIP.setFont(new Font("Arial", Font.BOLD, 20));
                            DIP.setHorizontalAlignment(JLabel.CENTER);
                            DIP.setVerticalAlignment(JLabel.TOP);

                            optionMenu.add(DIP);
                            optionMenu.setVisible(true);
                    }

// String[] column = {"No.", "Date", "Description", "Amount", "Type"};

// String[][] data = new String[5][100];

// JTable table = new JTable(data, column);

// JScrollPane scp = new JScrollPane(table);
// scp.setBounds(45,50,700,350);

// JButton OK = new JButton("OK");
// OK.setBounds(350, 420, 100, 30); 
// OK.setBorder(BorderFactory.createEtchedBorder());

// OK.setFocusable(false);
// OK.addActionListener(new ActionListener() {
//     public void actionPerformed(ActionEvent e) {
//         option[0] = 0;
//         history.dispose();
//     }
// });


// history.add(scp);
// history.add(label);
// history.add(OK);

// history.setVisible(true);

// int LogReg=JOptionPane.showOptionDialog(null,"Welcome to Ledger System!","Ledger System",JOptionPane.YES_NO_OPTION,JOptionPane.INFORMATION_MESSAGE,icon,new String[]{"Login","Register"},"Login");

// JLabel label = new JLabel();
// label.setText("HISTORY");
// label.setBounds(0, 10, 400, 40);
// label.setFont(new Font("Arial", Font.BOLD, 20)); 
// label.setHorizontalAlignment(JLabel.CENTER);
// label.setVerticalAlignment(JLabel.TOP);



// JButton creditButton = new JButton("Credit");
// creditButton.setBounds(50, 110, 300, 40); 
// creditButton.setBorder(BorderFactory.createEtchedBorder());
// creditButton.setFocusable(false);
// creditButton.addActionListener(new ActionListener() {
//     public void actionPerformed(ActionEvent e) {
//         option[0] = 1;
//         optionMenu.dispose();
//     }
// });

// JButton historyButton = new JButton("History");
// historyButton.setBounds(50, 160, 300, 40); 
// historyButton.setBorder(BorderFactory.createEtchedBorder());
// historyButton.setFocusable(false);
// historyButton.addActionListener(new ActionListener() {
//     public void actionPerformed(ActionEvent e) {
//         option[0] = 2;
//         optionMenu.dispose();
//     }
// });

// JButton savingsButton = new JButton("Savings");
// savingsButton.setBounds(50, 210, 300, 40); 
// savingsButton.setBorder(BorderFactory.createEtchedBorder());
// savingsButton.setFocusable(false);
// savingsButton.addActionListener(new ActionListener() {
//     public void actionPerformed(ActionEvent e) {
//         option[0] = 3;
//         optionMenu.dispose();
//     }
// });

// JButton creditLoanButton = new JButton("Credit Loan");
// creditLoanButton.setBounds(50, 260, 300, 40); 
// creditLoanButton.setBorder(BorderFactory.createEtchedBorder());
// creditLoanButton.setFocusable(false);
// creditLoanButton.addActionListener(new ActionListener() {
//     public void actionPerformed(ActionEvent e) {
//         option[0] = 4;
//         optionMenu.dispose();
//     }
// });

// JButton DIPButton = new JButton("Deposit Interest Predictor");
// DIPButton.setBounds(50, 310, 300, 40); 
// DIPButton.setBorder(BorderFactory.createEtchedBorder());
// DIPButton.setFocusable(false);
// DIPButton.addActionListener(new ActionListener() {
//     public void actionPerformed(ActionEvent e) {
//         option[0] = 5;
//         optionMenu.dispose();
//     }
// });

// JButton logoutButton = new JButton("Logout");
// logoutButton.setBounds(50, 360, 300, 40); 
// logoutButton.setBorder(BorderFactory.createEtchedBorder());
// logoutButton.setFocusable(false);
// logoutButton.addActionListener(new ActionListener() {
//     public void actionPerformed(ActionEvent e) {
//         option[0] = 6;
//         optionMenu.dispose();
//     }
// });

// optionMenu.add(debitButton);
// optionMenu.add(creditButton);
// optionMenu.add(historyButton);
// optionMenu.add(savingsButton);
// optionMenu.add(creditLoanButton);
// optionMenu.add(DIPButton);
// optionMenu.add(logoutButton);

// optionMenu.add(label);

// ImageIcon image = new ImageIcon("Slide1.png");
// optionMenu.setIconImage(image.getImage());

// optionMenu.setVisible(true);

while (option[0] == 100) {
    try {
        Thread.sleep(100); 
    } catch (InterruptedException e) {
        e.printStackTrace();
    }
}

// System.out.println(option[0]);

     }
}
