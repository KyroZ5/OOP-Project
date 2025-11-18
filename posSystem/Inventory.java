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

    JButton btnAdd = new JButton("...");
    JButton btnEdit = new JButton("...");
    JButton btnDelete = new JButton("...");
    JButton btnRefresh = new JButton("...");
    JButton btnBack = new JButton("Back");

    ImageIcon logo = new ImageIcon("./img/logo-icon-dark-transparent.png");
    Color myColor = new Color(100, 150, 135); 

    public Inventory() {
        setSize(700, 650);
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

        controlPanel.setLayout(new FlowLayout());
        controlPanel.setBorder(BorderFactory.createTitledBorder("Controls"));
        Font titleFont1 = new Font("Segoe UI", Font.BOLD, 16); 
        TitledBorder inventoryBorder1 = BorderFactory.createTitledBorder("Controls");
        inventoryBorder1.setTitleFont(titleFont1);
        
        controlPanel.setBackground(myColor);
        controlPanel.add(btnAdd);
        controlPanel.add(btnEdit);
        controlPanel.add(btnDelete);
        controlPanel.add(btnRefresh);
        controlPanel.add(btnBack);

        btnAdd.setEnabled(false);
        btnEdit.setEnabled(false);
        btnDelete.setEnabled(false);
        btnRefresh.setEnabled(false);

        btnAdd.addActionListener(this);
        btnEdit.addActionListener(this);
        btnDelete.addActionListener(this);
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
    	if (e.getSource() == btnAdd) {
    	    String barcode = JOptionPane.showInputDialog("Enter barcode:");
    	    String name = JOptionPane.showInputDialog("Enter item name:");
    	    String stockStr = JOptionPane.showInputDialog("Enter stock quantity:");
    	    String priceStr = JOptionPane.showInputDialog("Enter price:");

    	    try {
    	        int stock = Integer.parseInt(stockStr.trim());
    	        double price = Double.parseDouble(priceStr.trim());

    	        Item newItem = new Item(barcode.trim(), name.trim(), stock, price);
    	        items.add(newItem);
    	        refreshTable();  

    	        JOptionPane.showMessageDialog(this, "Item added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
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
                    item.setName(newName.trim());
                    item.setStock(Integer.parseInt(newStockStr.trim()));
                    item.setPrice(Double.parseDouble(newPriceStr.trim()));
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
        	SelectionCashier sc = new SelectionCashier();
            sc.setVisible(true);
            this.dispose();
        }
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public static void main(String[] args) {
        new Inventory().setVisible(true);
    }
}