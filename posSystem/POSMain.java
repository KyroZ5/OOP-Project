package posSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class POSMain extends JFrame implements ActionListener {
	
	public static int increment = 0;
	
	JPanel p1 = new JPanel();
    JPanel p2 = new JPanel();
    JPanel p3 = new JPanel();
    
    JLabel lblUsername = new JLabel(" ");
    JLabel lblPassword = new JLabel(" ");
    JTextField txtUsername = new JTextField(15);
    JPasswordField txtPassword = new JPasswordField(15);   
    Color myColor = new Color(100, 150, 70); 
    JButton btnLogin = new JButton("Login");
    JButton btnCancel = new JButton("Exit");
    
    JLabel lblWelcome = new JLabel("Welcome");
    JLabel lblLine = new JLabel("___________________");
    JLabel lblSub = new JLabel("Enter login details below");	

    ImageIcon BLogo = new ImageIcon("./img/logo-light-transparent.png");
    Image img = BLogo.getImage();
    Image newLogo = img.getScaledInstance(350, 80, Image.SCALE_SMOOTH);
    ImageIcon Logo = new ImageIcon(newLogo);
    JLabel bLogo = new JLabel();
    ImageIcon logo = new ImageIcon("./img/logo-icon-dark-transparent.png");
    Font font = new Font("Montserrat", Font.BOLD, 15);
    
    ImageIcon ULogo = new ImageIcon("./img/user.png");
	Image imgU = ULogo.getImage();
	Image newULogo = imgU.getScaledInstance(60,60,java.awt.Image.SCALE_SMOOTH);
	ImageIcon uuLogo = new ImageIcon(newULogo);
	JLabel uLogo = new JLabel();
	ImageIcon ulogo = new ImageIcon("./img/user.png");	
	
	ImageIcon LLogo = new ImageIcon("./img/lock.png");
	Image imgL = LLogo.getImage();
	Image newLLogo = imgL.getScaledInstance(40,40,java.awt.Image.SCALE_SMOOTH);
	ImageIcon llLogo = new ImageIcon(newLLogo);
	JLabel lLogo = new JLabel();
	ImageIcon llogo = new ImageIcon("./img/lock.png");	

	ImageIcon PLogo = new ImageIcon("./img/pic.jpg");
	Image imgP = PLogo.getImage();
	Image newPLogo = imgP.getScaledInstance(400,400,java.awt.Image.SCALE_SMOOTH);
	ImageIcon ppLogo = new ImageIcon(newPLogo);
	JLabel pLogo = new JLabel();
	ImageIcon plogo = new ImageIcon("./img/pic.jpg");	

    public POSMain() {
    	 setSize(650, 400);
         setLocationRelativeTo(null);
         setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         setTitle("Login Form");
         setLayout(null);
         setResizable(false);
         setIconImage(logo.getImage());
         setUndecorated(true);
         
         add(p2);
         add(p1);
         add(p3);
         
         p1.add(lblUsername);
         p1.add(lblPassword);
         p1.add(txtUsername);
         p1.add(txtPassword);
         p1.add(btnLogin);
         p1.add(btnCancel);
         p1.setBounds(200, 130, 450, 275);
         p1.setLayout(null);
         p1.setBackground(myColor);
         
         p1.add(uLogo);
         uLogo.setBounds(100, -3, 375, 105);
         uLogo.setIcon(uuLogo);
         
         p1.add(lLogo);
         lLogo.setBounds(110, 50, 375, 105);
         lLogo.setIcon(llLogo);
         
         p3.setBounds(0, 0, 650, 400);
         p3.setLayout(null);
         p3.setBackground(myColor);
         p3.add(pLogo);
         pLogo.setBounds(0, 0, 700, 400);
         pLogo.setIcon(ppLogo);
         
         lblUsername.setBounds(80, 35, 150, 30);
         lblUsername.setFont(font);
         txtUsername.setBounds(150, 35, 200, 30);
         
         lblPassword.setBounds(80, 85, 150, 30);
         lblPassword.setFont(font);
         txtPassword.setBounds(150, 85, 200, 30);

         btnLogin.setBounds(80, 170, 100, 30);
         btnLogin.setFont(font);
         btnCancel.setBounds(280, 170, 100, 30);
         btnCancel.setFont(font);

         btnLogin.addActionListener(this);
         btnCancel.addActionListener(this);
         
         p2.add(lblWelcome);
         p2.add(lblSub);
         p2.add(lblLine);
         p2.setBounds(200, 0, 450, 130);
         p2.setLayout(null);
         p2.setBackground(myColor);
         p2.add(bLogo);
         bLogo.setBounds(50, -5, 375, 105);
         bLogo.setIcon(Logo);
         
         lblWelcome.setFont(new Font("Montserrat", Font.BOLD, 22));
         lblWelcome.setBounds(210, 80, 200, 30);
         lblWelcome.setForeground(Color.WHITE); 

         lblLine.setFont(new Font("Montserrat", Font.PLAIN, 14));
         lblLine.setBounds(180, 92, 200, 20);
         lblLine.setForeground(Color.WHITE);

         lblSub.setFont(new Font("Montserrat", Font.PLAIN, 14));
         lblSub.setBounds(180, 110, 200, 20);
         lblSub.setForeground(Color.WHITE); 

        // Placeholder for Username
         txtUsername.setText("Username");
         txtUsername.setForeground(Color.GRAY);
         txtUsername.addFocusListener(new FocusAdapter() {
             public void focusGained(FocusEvent e) {
                 if (txtUsername.getText().equals("Username")) {
                     txtUsername.setText("");
                     txtUsername.setForeground(Color.BLACK);
                 }
             }
             public void focusLost(FocusEvent e) {
                 if (txtUsername.getText().isEmpty()) {
                     txtUsername.setForeground(Color.GRAY);	
                     txtUsername.setText("Username");
                 }
             }
         });

         // Placeholder for Password
         txtPassword.setText("Password");
         txtPassword.setForeground(Color.GRAY);
         txtPassword.setEchoChar((char) 0); // Show text instead of masking
         txtPassword.addFocusListener(new FocusAdapter() {
             public void focusGained(FocusEvent e) {
                 if (new String(txtPassword.getPassword()).equals("Password")) {
                     txtPassword.setText("");
                     txtPassword.setForeground(Color.BLACK);
                     txtPassword.setEchoChar('•'); // Restore masking
                 }
             }
             public void focusLost(FocusEvent e) {
                 if (new String(txtPassword.getPassword()).isEmpty()) {
                     txtPassword.setForeground(Color.GRAY);
                     txtPassword.setText("Password");
                     txtPassword.setEchoChar((char) 0); // Show placeholder
                 }
             }
         });
    }
   
    @Override
    public void actionPerformed(ActionEvent ev) {
        if (ev.getSource() == btnCancel) {
            int x = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit?", "Exit Form", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        } else if (ev.getSource() == btnLogin) {
            String userL = txtUsername.getText().trim();
            String passL = new String(txtPassword.getPassword()).trim();

            if (userL.isEmpty() || passL.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Fields cannot be empty", "Error", JOptionPane.WARNING_MESSAGE);
                return;
            }
            defaultAdminExists();
            Users matchedUser = null;
            for (Users acc : Users.accts) {
                System.out.println(acc.getUsername() + " " + acc.getPassword() + " " + acc.getEmployeeName());
                if (userL.equals(acc.getUsername()) && passL.equals(acc.getPassword())) {
                    matchedUser = acc;
                    break;
                }
            }
            if (matchedUser != null) {
                JOptionPane.showMessageDialog(null, "Welcome, " + matchedUser.getEmployeeName(), "Login Successful", JOptionPane.INFORMATION_MESSAGE);

                if (matchedUser.getUsername().equalsIgnoreCase("admin")) {
                    new SelectionAdmin().setVisible(true);
                } else {
                    new SelectionCashier().setVisible(true);
                }
                setVisible(false);
            } else {
                JOptionPane.showMessageDialog(null, "Invalid username or password!", "Invalid Login", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void defaultAdminExists() {
        for (Users acc : Users.accts) {
            if (acc.getUsername().equals("admin")) {
                return;
            }
        }
        Users.accts.add(new Users("admin", "admin", "Administrator"));
    }
 public static void main(String[] args) {
         
	 POSMain pos = new POSMain();
     pos.setVisible(true);
 	}
}