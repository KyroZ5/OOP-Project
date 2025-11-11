package posSystem;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.*;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;



public class Login extends JFrame implements ActionListener{
	
	JPanel p1 = new JPanel();
    JPanel p2 = new JPanel();

    JLabel lblUsername = new JLabel("Username: ");
    JLabel lblPassword = new JLabel("Password: ");
    JTextField txtUsername = new JTextField(15);
    JPasswordField txtPassword = new JPasswordField(15);   
    Color myColor = new Color(193, 234, 242); 
    JButton btnLogin = new JButton("Login");
    JButton btnCancel = new JButton("Exit");
       
   public Login() {
	   setSize(450, 400);
       setLocationRelativeTo(null);
       setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       setTitle("Login Form");
       setLayout(null);
       setResizable(false);

       add(p2);
       add(p1);

       p1.add(lblUsername);
       p1.add(lblPassword);
       p1.add(txtUsername);
       p1.add(txtPassword);
       p1.add(btnLogin);
       p1.add(btnCancel);
       p1.setBounds(0, 130, 450, 250);
       p1.setLayout(null);
       p1.setBackground(myColor);

       p2.setBounds(0, 0, 450, 130);
       p2.setLayout(null);
       p2.setBackground(myColor);
     
      

       lblUsername.setBounds(80, 35, 100, 30);
       txtUsername.setBounds(180, 35, 150, 30);
       lblPassword.setBounds(80, 85, 100, 30);
       txtPassword.setBounds(180, 85, 150, 30);
       btnLogin.setBounds(280, 170, 100, 30);
       btnCancel.setBounds(80, 170, 100, 30);
   

       btnLogin.addActionListener(this);
       btnCancel.addActionListener(this);

       
   }
   public void actionPerformed(ActionEvent ev) {
	   
       if (ev.getSource() == btnCancel) {
           int x = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit?", "Exit Form", JOptionPane.YES_NO_OPTION);
           if (x == JOptionPane.YES_OPTION) {
               System.exit(0);
           }
       } else if (ev.getSource() == btnLogin) {
           String UserL = txtUsername.getText().trim();
           String PassL = new String(txtPassword.getPassword()).trim(); // Proper handling for passwords
           boolean authenticated = false;

           int size = 0;
		   for (int i = 0; i < size; i++) {
        	   ArrayList<Users> accts = new ArrayList<>();
               if (accts.get(i).equalsIgnoreCase(UserL) && accts.get(i).equals(PassL)) {
                   authenticated = true;
                  

                   if (UserL.equalsIgnoreCase("admin") && PassL.equals("admin")) { 
                       //new SelectionAdmin().setVisible(true);
                       setVisible(false);
                       JOptionPane.showMessageDialog(null, "Welcome, " + UserL, "Login Successful", JOptionPane.INFORMATION_MESSAGE);
                   } else if (accts.get(i).equalsIgnoreCase(accts.get(i))) { 
                       //new SelectionCashier().setVisible(true);
                       setVisible(false);
                       JOptionPane.showMessageDialog(null, "Welcome, " + UserL, "Login Successful", JOptionPane.INFORMATION_MESSAGE);
                   }
               }
           }

           if (!authenticated) {
               JOptionPane.showMessageDialog(null, "Invalid username or password!", "Invalid Login", JOptionPane.ERROR_MESSAGE);
           }
       }
   
   }
	
public static void main(String[] args) {
    Login log = new Login();
    log.setVisible(true);
}
class Users {
	ArrayList<Users> accts = new ArrayList<>();
	private String username;
	private String password; 
	private String employeeName;
	Users(String username, String password, String employeeName){
		this.username = username;
		this.password = password;
		this.employeeName = employeeName;
	}
	public boolean equalsIgnoreCase(Users users) {
		// TODO Auto-generated method stub
		return false;
	}
	public boolean equalsIgnoreCase(String userL) {
		// TODO Auto-generated method stub
		return false;
	}
	public String getUsername() { 
		return username; }
    public String getPassword() { 
    	return password; }
    public String getEmployeeName() { 
    	return employeeName; }
    
	public void loadUsersFromFile() throws FileNotFoundException, IOException {
		
		try(BufferedReader br = new BufferedReader(new FileReader("users.txt"))){
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    Users user = new Users(parts[1], parts[2], parts[0]);
                    accts.add(user);
                }
            }
		}
	}	
}
}