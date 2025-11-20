package posSystem;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Inventory extends JFrame implements ActionListener {

	ArrayList<Item> items = InventoryData.getItems();
    JPanel inventoryPanel = new JPanel();
    JPanel controlPanel = new JPanel();
    DefaultTableModel tableModel;
    JTable inventoryTable;
    JButton btnRefresh = new JButton("Refresh");
    JButton btnBack = new JButton("Back");
    ImageIcon logo = new ImageIcon("./img/logo-icon-dark-transparent.png");
    Color myColor = new Color(100, 150, 135); 

    public Inventory() {
        setSize(700, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Inventory System");
        setUndecorated(true);
        setLayout(new BorderLayout());
        setIconImage(logo.getImage());

        tableModel = new DefaultTableModel(new String[]{"Barcode", "Item Name", "Stock", "Price (₱)"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        inventoryTable = new JTable(tableModel);
        inventoryTable.setRowHeight(50);
        inventoryTable.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < inventoryTable.getColumnCount(); i++) {
            inventoryTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
        JTableHeader header = inventoryTable.getTableHeader();
        header.setPreferredSize(new Dimension(header.getWidth(), 50));
        header.setFont(new Font("Segoe UI", Font.PLAIN, 30));
        JScrollPane scrollPane = new JScrollPane(inventoryTable);


        Font titleFont = new Font("Segoe UI", Font.BOLD, 20); 
        TitledBorder inventoryBorder = BorderFactory.createTitledBorder("Inventory List");
        inventoryBorder.setTitleFont(titleFont);
        inventoryPanel.setLayout(new BorderLayout());
        inventoryPanel.setBorder(inventoryBorder);
        inventoryPanel.add(scrollPane, BorderLayout.CENTER);
        inventoryPanel.setBackground(myColor);

        Font titleFont1 = new Font("Segoe UI", Font.BOLD, 16); 
        TitledBorder inventoryBorder1 = BorderFactory.createTitledBorder("Controls");
        inventoryBorder1.setTitleFont(titleFont1);
        controlPanel.setLayout(null);
        controlPanel.setPreferredSize(new Dimension(700, 80)); // Ensure space for buttons
        controlPanel.setBorder(inventoryBorder1);
  
        controlPanel.setBackground(myColor);
        controlPanel.add(btnRefresh);
        controlPanel.add(btnBack);

        btnRefresh.setBounds(350, 25, 150, 40);
        btnBack.setBounds(525, 25, 150, 40);

        btnRefresh.setEnabled(true);
        btnBack.setEnabled(true);
        
        btnRefresh.addActionListener(this);
        btnBack.addActionListener(this);
        
        InventoryData.loadSampleItems();
        refreshTable();
        add(inventoryPanel, BorderLayout.CENTER);
        add(controlPanel, BorderLayout.SOUTH);
    }
    
    private void refreshTable() {
        tableModel.setRowCount(0);
        for (Item item : InventoryData.items) {
            tableModel.addRow(new Object[]{
                item.getBarcode(),
                item.getName(),
                item.getStock(),
                "₱" + item.getPrice()
            });
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
     if (e.getSource() == btnRefresh) {
            refreshTable();
            JOptionPane.showMessageDialog(this, "Inventory refreshed!", "Info", JOptionPane.INFORMATION_MESSAGE);

        } else if (e.getSource() == btnBack) {
        	SelectionCashier sc = new SelectionCashier();
            sc.setVisible(true);
            this.dispose();
        }
    }
    public ArrayList<Item> getItems() {
        return items;
    }
}