/* import java.awt.*;
import java.awt.event.*;
import java.sql.*;

import javax.swing.*;

public class Welcome {
    public static void main(String[] args) throws Exception{
        
        JFrame f = new JFrame("CAR RENTAL SERVICES!");
        f.setLocation(550, 250);
        f.setSize(465, 350);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLayout(null);
        JLabel l0 = new JLabel();
        f.add(l0);
        l0.setText("Welcome to Car Rental Services!");
        l0.setFont(new Font("Arial", Font.BOLD,20));
        l0.setBounds(50, 10, 1000, 30);

        JLabel l1 = new JLabel("Username:");
        f.add(l1);
        l1.setBounds(85,100,100,20);
        JTextField t1 = new JTextField(10);
        f.add(t1);
        t1.setBounds(160,100,150,20);
        
        JLabel l2 = new JLabel("Password:");
        f.add(l2);
        l2.setBounds(85,150,100,20);
        JTextField t2 = new JTextField(10);
        f.add(t2);
        t2.setBounds(160,150,150,20);

        JButton b1 = new JButton("Login");
        f.add(b1);


        b1.setBounds(160,225,100,20);
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String s1=t1.getText();
                String s2=t2.getText();
                if(s1.equals("Joe") && s2.equals("1018") || (s1.equals("Alwin") && s2.equals("1050"))){
                    JOptionPane.showMessageDialog(f,"Welcome "+s1+ ". You are being redirected to the Home Page!");
                    f.dispose();
                    Rent reg = new Rent();
                    reg.rentACar();
                }
                else{
                    JOptionPane.showMessageDialog(f, "Login Unsuccessful. If you don't have an account, please sign up!");
                }
            }
        });

        JButton b2 = new JButton("Signup");
        f.add(b2);
        b2.setBounds(350,50,80,20);
        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    f.dispose();
                    Signup r = new Signup();
                    r.signupRegister();
            }           
        });
        f.setVisible(true);
    }
}
*/



import java.awt.*;
import java.awt.event.*;
import java.sql.*;

import javax.swing.*;

public class Welcome {
    public static void main(String[] args) {
        JFrame f = new JFrame("CAR RENTAL SERVICES!");
        f.setLocation(550, 250);
        f.setSize(465, 350);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLayout(null);
        JLabel l0 = new JLabel();
        f.add(l0);
        l0.setText("Welcome to Car Rental Services!");
        l0.setFont(new Font("Arial", Font.BOLD, 20));
        l0.setBounds(50, 10, 1000, 30);

        JLabel l1 = new JLabel("Username:");
        f.add(l1);
        l1.setBounds(85, 100, 100, 20);
        JTextField t1 = new JTextField(10);
        f.add(t1);
        t1.setBounds(160, 100, 150, 20);

        JLabel l2 = new JLabel("Password:");
        f.add(l2);
        l2.setBounds(85, 150, 100, 20);
        JPasswordField t2 = new JPasswordField(10); // Changed to JPasswordField for security
        f.add(t2);
        t2.setBounds(160, 150, 150, 20);

        JButton b1 = new JButton("Login");
        f.add(b1);
        b1.setBounds(160, 225, 100, 20);

        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/userdb1", "root", "HelloWorld");
            Statement stmt = con.createStatement();

            b1.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String username = t1.getText();
                    String password = new String(t2.getPassword());

                    try {
                        ResultSet rs = stmt.executeQuery("SELECT * FROM test1"); // Move ResultSet creation inside the action listener

                        // Declare a boolean variable to store the match status
                        boolean matchFound = false;

                        while (rs.next()) {
                            String dbUsername = rs.getString("username");
                            String dbPassword = rs.getString("password");

                            if (username.equals(dbUsername) && password.equals(dbPassword)) {
                                // If a match is found, set the variable to true and break the loop
                                matchFound = true;
                                break;
                            }
                        }

                        // Check the value of the variable after the loop
                        if (matchFound) {
                            // If true, show a success message and open a new GUI interface
                            JOptionPane.showMessageDialog(f, "Login successful!");
                            f.dispose();
                            Rent reg = new Rent();
                            reg.rentACar();
                        } else {
                            // If false, show an error message
                            JOptionPane.showMessageDialog(f, "Invalid username or password!");
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            });
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        f.setVisible(true);
    }
}
