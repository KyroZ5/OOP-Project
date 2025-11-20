package posSystem;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class InventoryAdmin extends JFrame implements ActionListener {

	ArrayList<Item> items = InventoryData.getItems();
	JPanel logoPanel = new JPanel();
    JPanel inventoryPanel = new JPanel();
    JPanel controlPanel = new JPanel();

    DefaultTableModel tableModel;
    JTable inventoryTable;

    JButton btnAdd = new JButton("Add Item");
    JButton btnEdit = new JButton("Edit Item");
    JButton btnDelete = new JButton("Delete Item");
    JButton btnRefresh = new JButton("Refresh List");
    JButton btnBack = new JButton("Back");
    
    ImageIcon BLogo = new ImageIcon("./img/logo-light-transparent.png");
    Image img = BLogo.getImage();
    Image newLogo = img.getScaledInstance(350, 80, Image.SCALE_SMOOTH);
    ImageIcon Logo = new ImageIcon(newLogo);
    JLabel bLogo = new JLabel();

    ImageIcon logo = new ImageIcon("./img/logo-icon-dark-transparent.png");
    Color myColor = new Color(100, 150, 135); 

    public InventoryAdmin() {
        setSize(900, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Inventory System");
        setUndecorated(true);
        setLayout(new BorderLayout());
        setIconImage(logo.getImage());
        
        logoPanel.add(bLogo);
        logoPanel.setBackground(myColor);
        bLogo.setBounds(50, -5, 375, 105);
        bLogo.setIcon(Logo);

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
        TitledBorder inventoryBorder = BorderFactory.createTitledBorder("INVENTORY");
        inventoryBorder.setTitleFont(titleFont);
        inventoryPanel.setLayout(new BorderLayout());
        inventoryPanel.setBorder(inventoryBorder);
        inventoryPanel.add(scrollPane, BorderLayout.CENTER);
        inventoryPanel.setBackground(myColor);

        Font titleFont1 = new Font("Segoe UI", Font.BOLD, 16); 
        TitledBorder inventoryBorder1 = BorderFactory.createTitledBorder("CONTROLS");
        inventoryBorder1.setTitleFont(titleFont1);
        controlPanel.setLayout(null);
        controlPanel.setPreferredSize(new Dimension(700, 80)); // Ensure space for buttons
        controlPanel.setBorder(inventoryBorder1);
        controlPanel.setBackground(myColor);
        
        btnAdd.setBounds(20, 25, 120, 40);
        btnEdit.setBounds(150, 25, 120, 40);
        btnDelete.setBounds(280, 25, 120, 40);
        btnRefresh.setBounds(410, 25, 120, 40);
        btnBack.setBounds(740, 25, 120	, 40);
        
        controlPanel.add(btnAdd);
        controlPanel.add(btnEdit);
        controlPanel.add(btnDelete);
        controlPanel.add(btnRefresh);
        controlPanel.add(btnBack);
        
        btnAdd.setEnabled(true);
        btnEdit.setEnabled(true);
        btnDelete.setEnabled(true);
        btnRefresh.setEnabled(true);

        btnAdd.addActionListener(this);
        btnEdit.addActionListener(this);
        btnDelete.addActionListener(this);
        btnRefresh.addActionListener(this);
        btnBack.addActionListener(this);
        
        InventoryData.loadSampleItems();
        refreshTable();
        add(logoPanel, BorderLayout.NORTH);
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
    	if (e.getSource() == btnAdd) {
    	    String barcode = JOptionPane.showInputDialog("Enter barcode:");
    	    String name = JOptionPane.showInputDialog("Enter item name:");
    	    String stockStr = JOptionPane.showInputDialog("Enter stock quantity:");
    	    String priceStr = JOptionPane.showInputDialog("Enter price:");

    	    try {
    	        int stock = Integer.parseInt(stockStr.trim());
    	        double price = Double.parseDouble(priceStr.trim());

    	        if (stock <= 0 || price <= 0) {
    	            JOptionPane.showMessageDialog(this, "Stock and price must be greater than 0.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
    	            return;
    	        }
    	        boolean merged = false;
    	        for (Item item : InventoryData.items) {
    	            if (item.getBarcode().equals(barcode.trim())) {
    	                item.setStock(item.getStock() + stock);
    	                item.setPrice(price);
    	                merged = true;
    	                break;
    	            }
    	        }
    	        if (!merged) {
    	            InventoryData.items.add(new Item(barcode.trim(), name.trim(), stock, price));
    	        }
    	        refreshTable();
    	        JOptionPane.showMessageDialog(this, merged ? "Item merged successfully!" : "Item added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
    	    } catch (Exception ex) {
    	        JOptionPane.showMessageDialog(this, "Invalid input!", "Error", JOptionPane.ERROR_MESSAGE);
    	    }
    	} else if (e.getSource() == btnEdit) {
    	    int selectedRow = inventoryTable.getSelectedRow();
    	    if (selectedRow != -1) {
    	        Item item = items.get(selectedRow);
    	        String newName = JOptionPane.showInputDialog("Edit item name:", item.getName());
    	        String newStockStr = JOptionPane.showInputDialog("Edit stock quantity:", item.getStock());
    	        String newPriceStr = JOptionPane.showInputDialog("Edit price:", item.getPrice());
    	        try {
    	            int newStock = Integer.parseInt(newStockStr.trim());
    	            double newPrice = Double.parseDouble(newPriceStr.trim());
    	            if (newStock < 0 || newPrice < 0) {
    	                JOptionPane.showMessageDialog(this, "Stock and price must be 0 or higher.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
    	                return;
    	            }
    	            item.setName(newName.trim());
    	            item.setStock(newStock);
    	            item.setPrice(newPrice);
    	            refreshTable();
    	            JOptionPane.showMessageDialog(this, "Item updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
    	        } catch (Exception ex) {
    	            JOptionPane.showMessageDialog(this, "Invalid input!", "Error", JOptionPane.ERROR_MESSAGE);
    	        }
    	    } else {
    	        JOptionPane.showMessageDialog(this, "Select an item to edit.", "Error", JOptionPane.ERROR_MESSAGE);
    	    }
    	
            
        } else if (e.getSource() == btnDelete) {
            int selectedRow = inventoryTable.getSelectedRow();
            if (selectedRow != -1) {
                items.remove(selectedRow);
                refreshTable();
                JOptionPane.showMessageDialog(this, "Item deleted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Select an item to delete.", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } else if (e.getSource() == btnRefresh) {
            refreshTable();
            JOptionPane.showMessageDialog(this, "Inventory refreshed!", "Info", JOptionPane.INFORMATION_MESSAGE);

        } else if (e.getSource() == btnBack) {
        	SelectionAdmin sa = new SelectionAdmin();
            sa.setVisible(true);
            this.dispose();
        }
    }

    public ArrayList<Item> getItems() {
        return items;
    }
}