import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class MyFrame {
    public static void main(String[] args) {
int []option ={100};


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

String[][] data = new String[5][100];

JTable table = new JTable(data, column);

JScrollPane scp = new JScrollPane(table);
scp.setBounds(45,50,700,350);

JButton OK = new JButton("OK");
OK.setBounds(350, 420, 100, 30); 
OK.setBorder(BorderFactory.createEtchedBorder());

OK.setFocusable(false);
OK.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
        option[0] = 0;
        history.dispose();
    }
});


history.add(scp);
history.add(label);
history.add(OK);

history.setVisible(true);

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
