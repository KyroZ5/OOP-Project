package posSystem;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.*;

public class TransactionHistory extends JFrame implements ActionListener {

    JButton btnRefresh = new JButton("Refresh");
    JButton btnBack = new JButton("Back");
    JPanel historyPanel = new JPanel();
    JPanel controlPanel = new JPanel();
    DefaultTableModel tableModel;
    JTable transacTable;

    ImageIcon logo = new ImageIcon("./img/logo-icon-dark-transparent.png");
    Color myColor = new Color(100, 150, 135); 
    
    public TransactionHistory() {
    	setSize(500, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("History System");
        setUndecorated(true);
        setLayout(new BorderLayout());
        setIconImage(logo.getImage());
        
        tableModel = new DefaultTableModel(new String[]{"Transaction No.", "Date and Time", "Amount"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        transacTable = new JTable(tableModel);
        transacTable.setRowHeight(20);
        transacTable.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < transacTable.getColumnCount(); i++) {
        	transacTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
        JTableHeader header = transacTable.getTableHeader();
        header.setPreferredSize(new Dimension(header.getWidth(), 25));
        header.setFont(new Font("Segoe UI", Font.BOLD, 15));
        JScrollPane scrollPane = new JScrollPane(transacTable);
        
        Font titleFont = new Font("Segoe UI", Font.BOLD, 15); 
        TitledBorder historyBorder = BorderFactory.createTitledBorder("History List");
        historyBorder.setTitleFont(titleFont);
        historyPanel.setLayout(new BorderLayout());
        historyPanel.setBorder(historyBorder);
        historyPanel.add(scrollPane, BorderLayout.CENTER);
        historyPanel.setBackground(myColor);        
        
        Font titleFont1 = new Font("Segoe UI", Font.BOLD, 16); 
        TitledBorder inventoryBorder1 = BorderFactory.createTitledBorder("Controls");
        inventoryBorder1.setTitleFont(titleFont1);
        controlPanel.setLayout(null);
        controlPanel.setPreferredSize(new Dimension(700, 80)); // Ensure space for buttons
        controlPanel.setBorder(inventoryBorder1);
  
        controlPanel.setBackground(myColor);
        controlPanel.add(btnRefresh);
        controlPanel.add(btnBack);

        btnRefresh.setBounds(80, 25, 150, 40);
        btnBack.setBounds(270, 25, 150, 40);


        btnRefresh.addActionListener(this);
        btnBack.addActionListener(this);


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
                "₱" + String.format("%.2f", t.getAmount())
            });
        }
    }
    	
    @Override
    public void actionPerformed(ActionEvent ev) {
       if (ev.getSource() == btnRefresh) {
            loadTransactions();
            JOptionPane.showMessageDialog(this, "List refreshed!", "Info", JOptionPane.INFORMATION_MESSAGE);
        } else if (ev.getSource() == btnBack) {
            new SelectionCashier().setVisible(true);
            setVisible(false);
        }
    }
}