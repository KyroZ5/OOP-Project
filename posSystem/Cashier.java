package posSystem;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Copies;
import javax.print.attribute.standard.JobName;

public class Cashier extends JFrame implements ActionListener{

	 Color myColor = new Color(100, 150, 135); 

     JPanel leftPanel;   
     JPanel rightPanel;  
     JPanel headerPanel;
     JLabel lblDateTime;
     JLabel bLogo; 
     JLabel lblUser = new JLabel("Logged in as: " + Users.getStaff());
     JPanel transactionPanel;
     DefaultTableModel transactionModel;
     JTable transactionTable;
     
     JPanel paymentPanel;
     JPanel barcodePanel;
     JLabel lblBarcode = new JLabel("Barcode:");
     JTextField txtBarcode = new JTextField(15);
     JButton btnAddItem = new JButton("Add Item");
     JButton btnDeleteItem = new JButton("Delete Item");

     JPanel qtyPanel;
     JLabel lblQty = new JLabel("Adjust Quantity:");
     JTextField txtQty = new JTextField(5);
     JButton btnApplyQty = new JButton("Apply Quantity");

     JPanel cashInputPanel;
     JLabel lblTotal = new JLabel("Total: ₱0.00");
     JLabel lblCashReceived = new JLabel("Cash Received:");
     JTextField txtCashReceived = new JTextField(50);
     JButton btnProcessPayment = new JButton("Process Payment");
     JButton btnReset = new JButton("Clear All");
     JLabel lblChange = new JLabel("Change: ₱0.00");

     JPanel numPadPanel;
     
     JPanel logoutPanel;
     JButton btnBack = new JButton("Menu");

     JPanel receiptPanel;
     JTextArea receiptArea = new JTextArea();
     JScrollPane receiptScroll = new JScrollPane(receiptArea);
     JButton btnPrintReceipt = new JButton("Print Receipt");

    private Map<String, InventoryItem> inventoryMap = new HashMap<>();

    class InventoryItem {
        String barcode;
        String itemName;
        int stock;
        double price;
        InventoryItem(String barcode, String itemName, int stock, double price) {
            this.barcode = barcode;
            this.itemName = itemName;
            this.stock = stock;
            this.price = price;
        }
    }

    ImageIcon logo = new ImageIcon("./img/logo-icon-dark-transparent.png");
    ImageIcon BLogo = new ImageIcon("./img/logo-light-transparent.png");
    Image img = BLogo.getImage();
    Image newLogo = img.getScaledInstance(780, 120, Image.SCALE_SMOOTH);
    ImageIcon Logo = new ImageIcon(newLogo);

    private String centerText(String text, int width) {
        int padSize = (width - text.length()) / 2;
        StringBuilder padding = new StringBuilder();
        for (int i = 0; i < padSize; i++) {
            padding.append(" ");
        }
        return padding.toString() + text;
    }

    public Cashier() {
    	setSize(1460,900);
    	setLocationRelativeTo(null);
        setUndecorated(true);
        setTitle("Pentagram POS (Point-of-Sale) System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //setExtendedState(JFrame.MAXIMIZED_BOTH);
        setIconImage(logo.getImage());
        getContentPane().setBackground(myColor);
    
        // Header Panel
        headerPanel = new JPanel(null);
        headerPanel.setBackground(myColor);
        headerPanel.setBounds(20, 5, 1350, 115);
        bLogo = new JLabel(Logo);
        bLogo.setBounds(20, 5, 1350, 115);
        headerPanel.add(bLogo);
        add(headerPanel);

        
        // Transaction Panel
        transactionPanel = new JPanel(null);
        transactionPanel.setBackground(myColor);
        transactionPanel.setBorder(BorderFactory.createTitledBorder("Transaction"));
        transactionPanel.setBounds(20, 120, 900, 500);	

        transactionModel = new DefaultTableModel(
            new String[]{"Barcode", "Item Name", "Qty", "Price (₱)", "Subtotal (₱)"}, 0) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        transactionTable = new JTable(transactionModel);
        transactionTable.setRowHeight(100);
        transactionTable.setRowMargin(5);
        transactionTable.setFont(new Font("Segoe UI", Font.PLAIN, 30));
        transactionTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        JTableHeader header = transactionTable.getTableHeader();
        header.setPreferredSize(new Dimension(header.getWidth(), 50));
        header.setFont(new Font("Segoe UI", Font.PLAIN, 30));
        
        DefaultTableCellRenderer render = new DefaultTableCellRenderer();
        render.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < transactionTable.getColumnCount(); i++) {
            transactionTable.getColumnModel().getColumn(i).setCellRenderer(render);
            transactionTable.getColumnModel().getColumn(i).setPreferredWidth(176);
        }

        JScrollPane transScroll = new JScrollPane(transactionTable);
        transScroll.setBounds(10, 20, 883, 470);
        transactionPanel.add(transScroll);
        add(transactionPanel);

        // Payment Panel
        paymentPanel = new JPanel(null);
        paymentPanel.setBackground(myColor);
        paymentPanel.setBorder(BorderFactory.createTitledBorder("Payment"));
        paymentPanel.setBounds(940, 120, 500, 760);
        lblUser.setBounds(-10, 690, 500, 80);
        lblUser.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblUser.setForeground(Color.WHITE);
        lblUser.setHorizontalAlignment(SwingConstants.RIGHT);
        paymentPanel.add(lblUser);
        lblBarcode.setBounds(20, 30, 150, 40);
        lblBarcode.setFont(new Font("Segoe UI", Font.BOLD, 25));
        txtBarcode.setBounds(20, 80, 200, 40);
        txtBarcode.setFont(new Font("Segoe UI", Font.BOLD, 25));
        btnAddItem.setBounds(230, 30, 250, 40);
        btnAddItem.setFont(new Font("Segoe UI", Font.BOLD, 25));
        btnDeleteItem.setBounds(230, 80, 250, 40);
        btnDeleteItem.setFont(new Font("Segoe UI", Font.BOLD, 25));
        paymentPanel.add(lblBarcode);
        paymentPanel.add(txtBarcode);
        paymentPanel.add(btnAddItem);
        paymentPanel.add(btnDeleteItem);

        lblQty.setBounds(20, 140, 250, 40);
        lblQty.setFont(new Font("Segoe UI", Font.BOLD, 25));
        txtQty.setBounds(20, 180, 200, 40);
        txtQty.setFont(new Font("Segoe UI", Font.BOLD, 25));
        btnApplyQty.setBounds(230, 180, 250, 40);
        btnApplyQty.setFont(new Font("Segoe UI", Font.BOLD, 25));
        paymentPanel.add(lblQty);
        paymentPanel.add(txtQty);
        paymentPanel.add(btnApplyQty);

        lblTotal.setBounds(20, 310, 500, 30);
        lblTotal.setFont(new Font("Segoe UI", Font.BOLD, 25));
        paymentPanel.add(lblTotal);

        lblCashReceived.setBounds(20, 220, 250, 40);
        lblCashReceived.setFont(new Font("Segoe UI", Font.BOLD, 25));
        txtCashReceived.setBounds(20, 260, 200, 40);
        txtCashReceived.setFont(new Font("Segoe UI", Font.BOLD, 25));
        btnProcessPayment.setBounds(230, 260, 250, 40);
        btnProcessPayment.setFont(new Font("Segoe UI", Font.BOLD, 25));
        paymentPanel.add(lblCashReceived);
        paymentPanel.add(txtCashReceived);
        paymentPanel.add(btnProcessPayment);

        lblChange.setBounds(250, 310, 500, 30);
        lblChange.setFont(new Font("Segoe UI", Font.BOLD, 25));
        paymentPanel.add(lblChange);

        numPadPanel = new JPanel(new GridLayout(4, 3, 5, 5));
        numPadPanel.setBounds(20, 360, 460, 350);
        numPadPanel.setBackground(myColor);
        String[] keys = {"7", "8", "9", "4", "5", "6", "1", "2", "3", "0", ".", "Clear"};
        for (String key : keys) {
            JButton btn = new JButton(key);
            btn.setFont(new Font("Arial", Font.BOLD, 24));
            btn.setFocusable(false);
            btn.addActionListener(this);
            numPadPanel.add(btn);
        }
        paymentPanel.add(numPadPanel);
        add(paymentPanel);

        // Receipt Panel
        receiptPanel = new JPanel(null);
        receiptPanel.setBackground(myColor);
        receiptPanel.setBorder(BorderFactory.createTitledBorder("Receipt"));
        receiptPanel.setBounds(20, 630, 900, 250);
        
        lblDateTime = new JLabel();
        lblDateTime.setFont(new Font("Arial", Font.BOLD, 38));
        lblDateTime.setForeground(Color.BLACK);
        lblDateTime.setHorizontalAlignment(SwingConstants.RIGHT);
        lblDateTime.setBounds(640, 150, 250, 105);
        receiptPanel.add(lblDateTime);
        
        Timer timer = new Timer(1000, e -> {
            String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
            String time = new SimpleDateFormat("hh:mm a").format(new Date());
            lblDateTime.setForeground(Color.WHITE);
            lblDateTime.setText("<html><div style='text-align: right;'>" + date + "<br>" + time + "</div></html>");
        });
        timer.start();
       
        // Logged in User
        receiptArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        receiptArea.setEditable(false);
        receiptScroll = new JScrollPane(receiptArea);
        receiptScroll.setBounds(10, 20, 640, 220);
        receiptPanel.add(receiptScroll);
        btnReset.setBounds(660, 70, 230, 40);
        btnReset.setFont(new Font("Segoe UI", Font.BOLD, 25));
        receiptPanel.add(btnReset);
        btnPrintReceipt.setBounds(660, 20, 230, 40);
        btnPrintReceipt.setFont(new Font("Segoe UI", Font.BOLD, 25));
        btnBack.setBounds(660, 120, 230, 40);
        btnBack.setFont(new Font("Segoe UI", Font.BOLD, 25));
        receiptPanel.add(btnBack);
        receiptPanel.add(btnPrintReceipt);
        add(receiptPanel);

        // Logout Panel
        logoutPanel = new JPanel(null);
        logoutPanel.setBackground(myColor);
        logoutPanel.setBounds(1320, 500, 120, 50);
        
        //logoutPanel.add(btnBack);
        add(logoutPanel);

        // Listeners
        txtBarcode.addActionListener(this);
        btnAddItem.addActionListener(this);
        btnDeleteItem.addActionListener(this);
        btnApplyQty.addActionListener(this);
        btnProcessPayment.addActionListener(this);
        btnReset.addActionListener(this);
        btnBack.addActionListener(this);
        btnPrintReceipt.addActionListener(this);

        loadInventoryData();
        receiptArea.setText("");
    }
    
    private void loadInventoryData() {
        inventoryMap.clear();
        for (Item item : InventoryData.getItems()) {
            inventoryMap.put(item.getBarcode(), new InventoryItem(
                item.getBarcode(),
                item.getName(),
                item.getStock(),
                item.getPrice()
            ));
        }
    }
    
    private void saveInventoryData() {
        for (InventoryItem item : inventoryMap.values()) {
            for (Item invItem : InventoryData.getItems()) {
                if (invItem.getBarcode().equals(item.barcode)) {
                    invItem.setStock(item.stock);
                    invItem.setPrice(item.price);
                    break;
                }
            }
        }
    }
    
    private void addItemToTransaction(String barcode) {
        if(barcode.isEmpty()) return;
        if(!inventoryMap.containsKey(barcode)) {
            JOptionPane.showMessageDialog(this, "Item not found in inventory!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        InventoryItem item = inventoryMap.get(barcode);
        if(item.stock <= 0) {
            JOptionPane.showMessageDialog(this, "This item is out of stock!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        int rowIndex = -1;
        for(int i = 0; i < transactionModel.getRowCount(); i++) {
            if(transactionModel.getValueAt(i, 0).toString().equals(barcode)) {
                rowIndex = i;
                break;
            }
        }
        int newQty = (rowIndex != -1) ? Integer.parseInt(transactionModel.getValueAt(rowIndex, 2).toString()) + 1 : 1;
        if(newQty > item.stock) {
            JOptionPane.showMessageDialog(this, "Insufficient stock! Available: " + item.stock, "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if(rowIndex != -1) {
            transactionModel.setValueAt(newQty, rowIndex, 2);
            double subtotal = newQty * item.price;
            transactionModel.setValueAt(String.format("₱%.2f", subtotal), rowIndex, 4);
        } else {
            double subtotal = 1 * item.price;
            transactionModel.addRow(new Object[]{barcode, item.itemName, 1, String.format("₱%.2f", item.price), String.format("₱%.2f", subtotal)});
        }
        updateTotal();
    }
    
    private void deleteSelectedItem() {
        int selectedIndex = transactionTable.getSelectedRow();
        if(selectedIndex != -1) {
            transactionModel.removeRow(selectedIndex);
            updateTotal();
        } else {
            JOptionPane.showMessageDialog(this, "Select an item to delete.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void updateTotal() {
        double total = 0.0;
        for(int i = 0; i < transactionModel.getRowCount(); i++) {
            String subStr = transactionModel.getValueAt(i, 4).toString().replace("₱", "");
            total += Double.parseDouble(subStr);
        }
        lblTotal.setText("Total: ₱" + String.format("%.2f", total));
    }
    
    
    private void processPayment() {
        double total = 0.0;
        for (int i = 0; i < transactionModel.getRowCount(); i++) {
            String subStr = transactionModel.getValueAt(i, 4).toString().replace("₱", "");
            total += Double.parseDouble(subStr);
        }

        double cash;
        try {
            cash = Double.parseDouble(txtCashReceived.getText().trim());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid cash amount!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (cash < total) {
            JOptionPane.showMessageDialog(this, "Insufficient cash!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        double balance = cash - total;
        lblChange.setText("Balance: ₱" + String.format("%.2f", balance));
        int transactionNo = TransacData.transData.size() + 1;
        TransacData.transData.add(new TransacData(transactionNo, total, balance));
        JOptionPane.showMessageDialog(this, "Transaction recorded successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        generateReceipt(total, cash, balance);
        updateInventoryAfterSale();


        	
    }
    private void generateReceipt(double total, double cash, double balance) {
        receiptArea.setText("");
        
        receiptArea.append("         \n\n");
        receiptArea.append("         PENTAGRAM RECEIPT\n");
        receiptArea.append("---------------------------------------------------------------\n");
        
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd hh:mm a");
        receiptArea.append("Date: " + dateFormat.format(new Date()) + "\n");
        receiptArea.append("Transaction # " + TransacData.transData.size() + "\n");
        receiptArea.append("Staff: " + Users.getStaff()+"\n\n");
       
        receiptArea.append(String.format("%-3s %-5s %-5s %-5s\n", "Qty ->", "Item ->", "Price ->", "Subtotal"));
        receiptArea.append("---------------------------------------------------------------\n");
        
        for (int i = 0; i < transactionModel.getRowCount(); i++) {
            String itemName = transactionModel.getValueAt(i, 1).toString();
            String qty = transactionModel.getValueAt(i, 2).toString();
            String price = transactionModel.getValueAt(i, 3).toString();
            String subtotal = transactionModel.getValueAt(i, 4).toString();
            receiptArea.append(String.format("%-3s %-31s %-10s %-5s\n", qty, itemName,  price, subtotal));
        }
        receiptArea.append("---------------------------------------------------------------\n");
        receiptArea.append("Total:   P" + String.format("%.2f", total) + "\n");
        receiptArea.append("Cash:    P" + String.format("%.2f", cash) + "\n");
        receiptArea.append("Change: P" + String.format("%.2f", balance) + "\n\n\n\n");
        receiptArea.append("      THANK YOU FOR SHOPPING!\n");
        receiptArea.append("         \n\n\n\n");
    }
    
    private void printReceiptDirect() {
        try {
            String receiptText = receiptArea.getText();
            byte[] receiptBytes = receiptText.getBytes("UTF-8");
            DocFlavor flavor = DocFlavor.BYTE_ARRAY.AUTOSENSE;
            PrintService[] printServices = PrintServiceLookup.lookupPrintServices(flavor, null);
            if (printServices.length > 0) {
                PrintService selectedService = printServices[0];
                for (PrintService service : printServices) {
                    if (service.getName().toLowerCase().contains("xprinter-58")) {
                        selectedService = service;
                        break;
                    }
                }
                DocPrintJob printJob = selectedService.createPrintJob();
                Doc doc = new SimpleDoc(receiptBytes, flavor, null);
                PrintRequestAttributeSet attributes = new HashPrintRequestAttributeSet();
                attributes.add(new Copies(1));
                attributes.add(new JobName("Pentagram POS Receipt", null));
                printJob.print(doc, attributes);
            } else {
                JOptionPane.showMessageDialog(this, "No printer found! Please check your USB printer connection.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch(Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Printing error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void updateInventoryAfterSale() {
        for(int i = 0; i < transactionModel.getRowCount(); i++) {
            String barcode = transactionModel.getValueAt(i, 0).toString();
            int soldQty = Integer.parseInt(transactionModel.getValueAt(i, 2).toString());
            if(inventoryMap.containsKey(barcode)) {
                InventoryItem item = inventoryMap.get(barcode);
                item.stock -= soldQty;
                if(item.stock <= 0) {
                    JOptionPane.showMessageDialog(this, "Warning: " + item.itemName + " is now out of stock!", "Out of Stock", JOptionPane.WARNING_MESSAGE);
                    item.stock = 0;
                }
            }
        }
        saveInventoryData();
    }
    
    private void resetSale() {
        transactionModel.setRowCount(0);
        txtCashReceived.setText("");
        txtBarcode.setText("");
        txtQty.setText("");
        lblTotal.setText("Total: ₱0.00");
        lblChange.setText("Change: ₱0.00");
        receiptArea.setText("");
    }
   
    private void applyQuantityAdjustment() {
        int selectedRow = transactionTable.getSelectedRow();
        if(selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Select an item in the transaction table to adjust quantity.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String barcode = transactionModel.getValueAt(selectedRow, 0).toString();
        InventoryItem item = inventoryMap.get(barcode);
        try {
            int newQty = Integer.parseInt(txtQty.getText().trim());
            if(newQty < 1) {
                JOptionPane.showMessageDialog(this, "Quantity must be at least 1.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if(newQty > item.stock) {
                JOptionPane.showMessageDialog(this, "Insufficient stock! Available: " + item.stock, "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            transactionModel.setValueAt(newQty, selectedRow, 2);
            double subtotal = newQty * item.price;
            transactionModel.setValueAt(String.format("₱%.2f", subtotal), selectedRow, 4);
            updateTotal();
            txtQty.setText("");
        } catch(NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid quantity entered.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();
        if(src == btnAddItem || src == txtBarcode) {
            String barcode = txtBarcode.getText().trim();
            addItemToTransaction(barcode);
            txtBarcode.setText("");
        }
        else if(src == btnDeleteItem) {
            deleteSelectedItem();
        }
        else if(src == btnProcessPayment) {
            processPayment();
        }
        else if(src == btnReset) {
            resetSale();
        }
        else if(src == btnPrintReceipt) {
            printReceiptDirect();
        }
        else if(src == btnBack) {
            if (Users.isAdmin()){
            new SelectionAdmin().setVisible(true);
            this.dispose();
            } else {
            new SelectionCashier().setVisible(true);
            this.dispose();
            }
        }
        else if(src == btnApplyQty) {
            applyQuantityAdjustment();
        }
        else if(src instanceof JButton) {
            String cmd = e.getActionCommand();
            if(cmd.equals("Clear")) {
                if(txtBarcode.isFocusOwner()) {
                    txtBarcode.setText("");
                } else if(txtCashReceived.isFocusOwner()) {
                    txtCashReceived.setText("");
                } else if(txtQty.isFocusOwner()) {
                    txtQty.setText("");
                } else {
                    txtBarcode.requestFocusInWindow();
                    txtBarcode.setText("");
                }
            }
            else if("0123456789.".contains(cmd)) {
                if(txtBarcode.isFocusOwner()) {
                    txtBarcode.setText(txtBarcode.getText() + cmd);
                } else if(txtCashReceived.isFocusOwner()) {
                    txtCashReceived.setText(txtCashReceived.getText() + cmd);
                } else if(txtQty.isFocusOwner()) {
                    txtQty.setText(txtQty.getText() + cmd);
                } else {
                    txtBarcode.requestFocusInWindow();
                    txtBarcode.setText(txtBarcode.getText() + cmd);
                }
            }
        }
    }
}
