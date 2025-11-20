	package posSystem;
	
	import javax.swing.*;
	import javax.swing.border.TitledBorder;
	import javax.swing.table.DefaultTableCellRenderer;
	import javax.swing.table.DefaultTableModel;
	import javax.swing.table.JTableHeader;
	import java.awt.*;
	import java.awt.event.*;
	
	public class TransactionHistoryAdmin extends JFrame implements ActionListener {
	
	    JButton btnDel = new JButton("Delete");
	    JButton btnRefresh = new JButton("Refresh");
	    JButton btnBack = new JButton("Back");
	    JPanel logoPanel = new JPanel();
	    JPanel historyPanel = new JPanel();
	    JPanel controlPanel = new JPanel();
	    DefaultTableModel tableModel;
	    JTable transacTable;


	    ImageIcon BLogo = new ImageIcon("./img/logo-light-transparent.png");
	    Image img = BLogo.getImage();
	    Image newLogo = img.getScaledInstance(350, 80, Image.SCALE_SMOOTH);
	    ImageIcon Logo = new ImageIcon(newLogo);
	    JLabel bLogo = new JLabel();

	    ImageIcon logo = new ImageIcon("./img/logo-light-transparent.png");
	    Color myColor = new Color(100, 150, 135); 
	
	    public TransactionHistoryAdmin() {
	    	setSize(900, 600);
	        setLocationRelativeTo(null);
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        setTitle("History System");
	        setUndecorated(true);
	        setLayout(new BorderLayout());
	        setIconImage(logo.getImage());
	        
	        logoPanel.add(bLogo);
	        logoPanel.setBackground(myColor);
	        bLogo.setBounds(50, -5, 375, 105);
	        bLogo.setIcon(Logo);
	       
	        tableModel = new DefaultTableModel(new String[]{"Transaction No.", "Date and Time", "Amount", "Change", "Staff"}, 0) {
	            @Override
	            public boolean isCellEditable(int row, int column) {
	                return false;
	            }
	        };
	        transacTable = new JTable(tableModel);
	        transacTable.setRowHeight(35);
	        transacTable.setFont(new Font("Segoe UI", Font.PLAIN, 20));
	        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
	        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
	        for (int i = 0; i < transacTable.getColumnCount(); i++) {
	        	transacTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
	        }
	        JTableHeader header = transacTable.getTableHeader();
	        header.setPreferredSize(new Dimension(header.getWidth(), 30));
	        header.setFont(new Font("Segoe UI", Font.BOLD, 20));
	        JScrollPane scrollPane = new JScrollPane(transacTable);
	        
	        Font titleFont = new Font("Segoe UI", Font.BOLD, 15); 
	        TitledBorder historyBorder = BorderFactory.createTitledBorder("TRANSACTION HISTORY");
	        historyBorder.setTitleFont(titleFont);
	        historyPanel.setLayout(new BorderLayout());
	        historyPanel.setBorder(historyBorder);
	        historyPanel.add(scrollPane, BorderLayout.CENTER);
	        historyPanel.setBackground(myColor);        
	        
	        Font titleFont1 = new Font("Segoe UI", Font.BOLD, 16); 
	        TitledBorder inventoryBorder1 = BorderFactory.createTitledBorder("CONTROLS");
	        inventoryBorder1.setTitleFont(titleFont1);
	        controlPanel.setLayout(null);
	        controlPanel.setPreferredSize(new Dimension(700, 80)); // Ensure space for buttons
	        controlPanel.setBorder(inventoryBorder1);
	        
	        controlPanel.setBackground(myColor);
	        controlPanel.add(btnDel);
	        controlPanel.add(btnRefresh);
	        controlPanel.add(btnBack);
	
	        btnDel.setBounds(20, 25, 140, 40);
	        btnRefresh.setBounds(170, 25, 140, 40);
	        btnBack.setBounds(740, 25, 140, 40);
	
	        btnDel.addActionListener(this);
	        btnRefresh.addActionListener(this);
	        btnBack.addActionListener(this);
	        
	        add(logoPanel, BorderLayout.NORTH);
	        add(historyPanel, BorderLayout.CENTER);
	        add(controlPanel, BorderLayout.SOUTH);
	        loadTransactions();
	    }
	    private void loadTransactions() {
	        tableModel.setRowCount(0);
	        for (TransacData t : TransacData.transData) {
	            tableModel.addRow(new Object[]{
	                t.getTransactionNo(),
	                t.getDateAndTime(),
	                "₱" + String.format("%.2f", t.getAmount()),
	                "₱" + String.format("%.2f", t.getBalance()),
	                t.getEmployeeName()
	            });
	        }
	    }
	    @Override
	    public void actionPerformed(ActionEvent ev) {
	        if (ev.getSource() == btnDel) {
	            int selectedRow = transacTable.getSelectedRow();
	            if (selectedRow != -1) {
	                int confirm = JOptionPane.showConfirmDialog(this, "Delete selected transaction?", "Confirm", JOptionPane.YES_NO_OPTION);
	                if (confirm == JOptionPane.YES_OPTION) {
	                    TransacData.transData.remove(selectedRow);
	                    loadTransactions();
	                    JOptionPane.showMessageDialog(this, "Transaction deleted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
	                }
	            } else {
	                JOptionPane.showMessageDialog(this, "Select a transaction to delete.", "Error", JOptionPane.ERROR_MESSAGE);
	            }
	        } else if (ev.getSource() == btnRefresh) {
	            loadTransactions();
	            JOptionPane.showMessageDialog(this, "List refreshed!", "Info", JOptionPane.INFORMATION_MESSAGE);
	        } else if (ev.getSource() == btnBack) {
	            new SelectionAdmin().setVisible(true);
	            setVisible(false);
	        }
	    }
	}