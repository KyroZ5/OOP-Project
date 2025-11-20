package posSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Add extends JFrame implements ActionListener {
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    
    JPanel p1 = new JPanel(); 
    JPanel p2 = new JPanel();
    Color myColor = new Color(100, 150, 135); 
    
    JLabel lblregName = new JLabel("Name: ");
    JLabel lblregUsername = new JLabel("Username: ");
    JLabel lblregPassword = new JLabel("Password: ");
    
    JTextField txtregName = new JTextField();
    JTextField txtregUsername = new JTextField();
    JPasswordField txtregPassword = new JPasswordField();

    JButton btnregLogin = new JButton("Register");
    JButton btnregCancel = new JButton("Cancel");

    Font font = new Font("Montserrat", Font.BOLD, 15);
    Font font2 = new Font("Montserrat", Font.BOLD, 20);

    	
    ImageIcon logo = new ImageIcon("./img/logo-icon-dark-transparent.png");

    ImageIcon BLogo = new ImageIcon("./img/logo-light-transparent.png");
    Image img = BLogo.getImage();
    Image newLogo = img.getScaledInstance(350, 80, java.awt.Image.SCALE_SMOOTH);
    ImageIcon Logo = new ImageIcon(newLogo);
    JLabel bLogo = new JLabel();
    JLabel newRegis = new JLabel("Register New Account");

    public Add(POSMain loginInstance) {
        setSize(420, 380);
        setLocationRelativeTo(null);
        setTitle("Account Creation Form");
        setLayout(null);
        setResizable(false);
        setIconImage(logo.getImage());
        getContentPane().setBackground(Color.WHITE);
        setUndecorated(true);
        
        p2.setBounds(0, 0, 450, 230);
        p2.setLayout(null);
        p2.setBorder(BorderFactory.createTitledBorder(""));
        p2.setBackground(myColor);
        p2.add(bLogo);
        p2.setBorder(null);
        bLogo.setBounds(40, 10, 375, 105);
        bLogo.setIcon(Logo);

        add(p1);
        add(p2);

        p1.add(lblregName);
        p1.add(lblregUsername);
        p1.add(lblregPassword);
        p1.add(txtregName);
        p1.add(txtregUsername);
        p1.add(txtregPassword);
        p1.add(btnregLogin);
        p1.add(btnregCancel);
        p2.add(newRegis);

        p1.setBounds(0, 130, 450, 250);
        p1.setLayout(null);
        p1.setBorder(BorderFactory.createTitledBorder(""));
        p1.setBackground(myColor);
        p1.setBorder(null);

        newRegis.setBounds(130, 100, 250, 30);
        newRegis.setFont(font2);
        
        lblregName.setBounds(80, 20, 100, 30);
        lblregName.setFont(font);
        txtregName.setBounds(180, 20, 150, 30);
        
        lblregUsername.setBounds(80, 60, 100, 30);
        lblregUsername.setFont(font);
        txtregUsername.setBounds(180, 60, 150, 30);
        
        lblregPassword.setBounds(80, 100, 100, 30);
        lblregPassword.setFont(font);
        txtregPassword.setBounds(180, 100, 150, 30);

        btnregCancel.setBounds(80, 170, 100, 30);
        btnregCancel.setFont(font);
        btnregCancel.addActionListener(this);

        btnregLogin.setBounds(240, 170, 100, 30);
        btnregLogin.setFont(font);
        btnregLogin.addActionListener(this);
    }
    @Override
    public void actionPerformed(ActionEvent ev) {
        if (ev.getSource() == btnregLogin) {
            String empName = txtregName.getText().trim();
            String username = txtregUsername.getText().trim();
            String pass = new String(txtregPassword.getPassword()).trim();

            if (!empName.isEmpty() && !username.isEmpty() && !pass.isEmpty()) {
                boolean exists = false;
                for (Users u : Users.accts) {
                    if (u.getUsername().equals(username)) {
                        exists = true;
                        break;
                    }
                }
                if (exists) {
                    JOptionPane.showMessageDialog(this, "Username already exists! Please choose a different one.", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    Users.accts.add(new Users(username, pass, empName));
                    JOptionPane.showMessageDialog(this, "User registered successfully!", "Registration", JOptionPane.INFORMATION_MESSAGE);
                    new Admin().setVisible(true);
                	this.dispose();
                }
            } else {
                JOptionPane.showMessageDialog(this, "Fields cannot be empty!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if (ev.getSource() == btnregCancel) {
        	new Admin().setVisible(true);
        	this.dispose();
        }
    }
}
