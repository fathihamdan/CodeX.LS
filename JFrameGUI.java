import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLayeredPane;
import javax.swing.JTextField;
import javax.swing.JTextField;

public class JFrameGUI extends LedgerSystem{
    
    JFrameGUI(){



        JButton button1 = new JButton();
        button1.setBounds(50,50,100,50);
        button1.addActionListener(e -> System.out.println("button 1"));
        button1.setText("Button 1");

        JButton button2 = new JButton();
        button2.setBounds(200,50,100,50);
        button2.addActionListener(e -> System.out.println("button 2"));
        button2.setText("Button 2");
        

        JLabel label = new JLabel();
        label.setText("MENU");
        label.setBounds(200,10,100,30);//set position of the label
        // label.setFont(new Font("MV Boli", Font.PLAIN, 20));
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.TOP);

        // layeredPane.add(label, JLayeredPane.DEFAULT_LAYER);
        // layeredPane.add(button1, JLayeredPane.DEFAULT_LAYER);
        // layeredPane.add(button2, JLayeredPane.DEFAULT_LAYER);


        JTextField textField = new JTextField();
        textField.setBounds(10,10,200,30);



        JFrame frame = new JFrame("JLayeredPane");
        frame.setTitle("Ledger System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500,500);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.add(button1   );
        frame.add(button2);
        frame.add(label);
        // frame.add(textField);

        // frame.add(label);


        ImageIcon image = new ImageIcon("Slide1.png");//will create an image icon
        frame.setIconImage(image.getImage());//will set the image icon

        



        


    }


}
