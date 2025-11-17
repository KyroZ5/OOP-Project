package posSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SelectionAdmin extends JFrame implements ActionListener{
	
	JPanel newLogin = new JPanel();

	ImageIcon MLogo = new ImageIcon("./img/logo-light-transparent.png");
    Image img = MLogo.getImage();
    Image newLogo = img.getScaledInstance(130, 30, Image.SCALE_SMOOTH);
    ImageIcon Logo = new ImageIcon(newLogo);
    JLabel mLogo = new JLabel();
    ImageIcon mlogo = new ImageIcon("./img/logo-icon-dark-transparent.png");
	
	ImageIcon ALogo = new ImageIcon("./img/Transaction.png");
	Image imgA = ALogo.getImage();
	Image newALogo = imgA.getScaledInstance(80,80,java.awt.Image.SCALE_SMOOTH);
	ImageIcon aLogo = new ImageIcon(newALogo);
	JButton btnTransaction = new JButton(aLogo);
	
	ImageIcon BLogo = new ImageIcon("./img/cashier1.png");
	Image imgB = BLogo.getImage();
	Image newBLogo = imgB.getScaledInstance(80,80,java.awt.Image.SCALE_SMOOTH);	
	ImageIcon bLogo = new ImageIcon(newBLogo);
	JButton btnCashier = new JButton(bLogo);
	
	ImageIcon CLogo = new ImageIcon("./img/admin.png");
	Image imgC = CLogo.getImage();
	Image newCLogo = imgC.getScaledInstance(75,80,java.awt.Image.SCALE_SMOOTH);
	ImageIcon cLogo = new ImageIcon(newCLogo);
	JButton btnAdmin = new JButton(cLogo);
	
	ImageIcon DLogo = new ImageIcon("./img/inventory.png");
	Image imgD = DLogo.getImage();
	Image newDLogo = imgD.getScaledInstance(80,80,java.awt.Image.SCALE_SMOOTH);
	ImageIcon dLogo = new ImageIcon(newDLogo);
	JButton btnInventory = new JButton(dLogo);
	
	JLabel lblTransaction = new JLabel("Transactions");
	JLabel lblCashier = new JLabel("Cashier");
	JLabel lblAdmin = new JLabel("Accounts");
	JLabel lblInven = new JLabel("Inventory");
	Color myColor = new Color(100, 150, 70); 
    Font font = new Font("Montserrat", Font.BOLD, 15);
    ImageIcon logo = new ImageIcon("./img/logo-icon-dark-transparent.png");
    
    JButton btnOut = new JButton("Logout");
    
    public SelectionAdmin() {
    	setSize(470, 235);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setTitle("Pentagram");
        setIconImage(logo.getImage());
        setUndecorated(true);
        
        add(newLogin);
 
        mLogo.setBounds(320, -20, 150, 80);
        mLogo.setIcon(Logo);
        
        newLogin.add(mLogo);
        newLogin.add(btnCashier);
        newLogin.add(btnAdmin);
        newLogin.add(btnInventory);
        newLogin.add(btnTransaction);
        newLogin.add(btnOut);
        newLogin.add(lblTransaction);
        newLogin.add(lblCashier);
        newLogin.add(lblAdmin);
        newLogin.add(lblInven);
        newLogin.isOpaque();
        newLogin.setBackground(myColor);     
        newLogin.setBounds(0,0,470,235);
	    newLogin.setLayout(null);
	    
	    btnOut.setBounds(350,200,100,25);
	    
	    btnInventory.setBounds(40,60,80,80);
	    btnInventory.setBackground(myColor);
	    btnCashier.setBounds(140,60,80,80);
	    btnCashier.setBackground(myColor);
	    btnAdmin.setBounds(240,60,80,80);
	    btnAdmin.setBackground(myColor);
	    lblInven.setBounds(45,115,120,80);
	    lblInven.setEnabled(true); 
        lblInven.setFont(font);
	    lblCashier.setBounds(150,115,120,80);
        lblCashier.setFont(font);
	    lblAdmin.setBounds(245,115,120,80);
	    lblAdmin.setEnabled(true); 
        lblAdmin.setFont(font);
	    btnTransaction.setBounds(340,60,80,80);
	    btnTransaction.setBackground(myColor);
        lblTransaction.setBounds(335,115,120,80);
	    lblTransaction.setEnabled(true); 
        lblTransaction.setFont(font);
	    btnTransaction.setEnabled(true); 
	    btnCashier.setEnabled(true); 
	    btnAdmin.setEnabled(true);
	    btnInventory.setEnabled(true);
	    btnOut.setEnabled(true);
	    btnTransaction.addActionListener(this); 
	    btnOut.addActionListener(this);
	    btnCashier.addActionListener(this); 
	    btnAdmin.addActionListener(this);
	    btnInventory.addActionListener(this);
		
		
    }
	public static void main(String[] args) {
    	SelectionAdmin login = new SelectionAdmin();
        login.setVisible(true);
	
    }
    @Override
   	public void actionPerformed(ActionEvent ev) {
   		if(ev.getSource()==btnCashier) {
   	       
            setVisible(false);
   			
   		}else if(ev.getSource()==btnAdmin) {
   	
   	        setVisible(false);
   			
   		}else if(ev.getSource()==btnInventory) {
   		
   	        setVisible(false);
   			
   		}else if(ev.getSource()==btnTransaction) {
        	
        	  
        }else if(ev.getSource()==btnOut) {
              int x = JOptionPane.showConfirmDialog(null, "Are you sure you want to logout?", "Logging out", JOptionPane.YES_NO_OPTION);
              if (x == JOptionPane.YES_OPTION) {
            	  POSMain pos = new POSMain();
            	  pos.setVisible(true);
            	  this.dispose();
            	  
       }else if(x == JOptionPane.NO_OPTION) {}
   	   }  
    }
}