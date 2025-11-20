
package posSystem;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.*;

public class Admin extends JFrame implements ActionListener {

    JButton btnAdd = new JButton("Add");
    JButton btnEdit = new JButton("Edit");
    JButton btnDel = new JButton("Delete");
    JButton btnRefresh = new JButton("Refresh");
    JButton btnBack = new JButton("Back");
    
    JPanel historyPanel = new JPanel();
    JPanel controlPanel = new JPanel();
    
    DefaultTableModel tableModel;
    JTable userTable;
 
    ImageIcon logo = new ImageIcon("./img/logo-icon-dark-transparent.png");
    Color myColor = new Color(100, 150, 135); 

    public Admin() {
        setSize(580, 610);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Admin");
        setLayout(null);
        setUndecorated(true);
        setLayout(new BorderLayout());
        setIconImage(logo.getImage());
        
        tableModel = new DefaultTableModel(new String[]{"Name", "Username", "Password"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        userTable = new JTable(tableModel);
        userTable.setRowHeight(20);
        userTable.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < userTable.getColumnCount(); i++) {
        	userTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
        JTableHeader header = userTable.getTableHeader();
        header.setPreferredSize(new Dimension(header.getWidth(), 25));
        header.setFont(new Font("Segoe UI", Font.BOLD, 15));
        JScrollPane scrollPane = new JScrollPane(userTable);
        
        Font titleFont = new Font("Segoe UI", Font.BOLD, 15); 
        TitledBorder historyBorder = BorderFactory.createTitledBorder("ACCOUNTS");
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
        controlPanel.add(btnAdd);
        controlPanel.add(btnEdit);
        controlPanel.add(btnDel);
        controlPanel.add(btnRefresh);
        controlPanel.add(btnBack);

        btnAdd.setBounds(10, 25, 100, 40);
        btnEdit.setBounds(120, 25, 100, 40);
        btnDel.setBounds(230, 25, 100, 40);
        btnRefresh.setBounds(340, 25, 100, 40);
        btnBack.setBounds(470, 25, 100, 40);

        btnAdd.addActionListener(this);
        btnEdit.addActionListener(this);
        btnDel.addActionListener(this);
        btnRefresh.addActionListener(this);
        btnBack.addActionListener(this);
        
        userTable.getColumnModel().getColumn(2).setCellRenderer(new PasswordRenderer());
        
        add(historyPanel, BorderLayout.CENTER);
        add(controlPanel, BorderLayout.SOUTH);
        loadUsers();
    }

    class PasswordRenderer extends DefaultTableCellRenderer {
        private boolean showPasswords = false;
        @Override
        protected void setValue(Object value) {
            setText((value != null && !showPasswords) ? "****" : value.toString());
        }
    }

    private void loadUsers() {
        tableModel.setRowCount(0);
        for (Users user : Users.accts) {
            tableModel.addRow(new String[]{
                user.getEmployeeName(),
                user.getUsername(),
                user.getPassword()
            });
        }
    }

    private void saveUsers() {
        Users.accts.clear();
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            String name = tableModel.getValueAt(i, 0).toString();
            String username = tableModel.getValueAt(i, 1).toString();
            String password = tableModel.getValueAt(i, 2).toString();
            Users.accts.add(new Users(username, password, name));
        }
    }

    @Override
    public void actionPerformed(ActionEvent ev) {
        if (ev.getSource() == btnAdd) {
            new Add(null).setVisible(true);
            this.dispose();
        } else if (ev.getSource() == btnEdit) {
            int selectedRow = userTable.getSelectedRow();
            if (selectedRow != -1) {
                String currentName = tableModel.getValueAt(selectedRow, 0).toString();
                String currentUsername = tableModel.getValueAt(selectedRow, 1).toString();
                String currentPassword = tableModel.getValueAt(selectedRow, 2).toString();

                JPasswordField pf = new JPasswordField();
                int okCxl = JOptionPane.showConfirmDialog(this, pf, "Enter current password to confirm:", JOptionPane.OK_CANCEL_OPTION);
                if (okCxl == JOptionPane.OK_OPTION && new String(pf.getPassword()).equals(currentPassword)) {
                    String newName = JOptionPane.showInputDialog("Enter new name:", currentName);
                    String newUsername = JOptionPane.showInputDialog("Enter new username:", currentUsername);
                    JPasswordField pfNew = new JPasswordField();
                    int okNew = JOptionPane.showConfirmDialog(this, pfNew, "Enter new password:", JOptionPane.OK_CANCEL_OPTION);
                    if (okNew == JOptionPane.OK_OPTION) {
                        String newPassword = new String(pfNew.getPassword());
                        tableModel.setValueAt(newName, selectedRow, 0);
                        tableModel.setValueAt(newUsername, selectedRow, 1);
                        tableModel.setValueAt(newPassword, selectedRow, 2);

                        Users.accts.removeIf(u -> u.getUsername().equals(currentUsername));
                        Users.accts.add(new Users(newUsername, newPassword, newName));
                        saveUsers();
                        JOptionPane.showMessageDialog(this, "User updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Password confirmation failed.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Select a user to edit.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if (ev.getSource() == btnDel) {
            int selectedRow = userTable.getSelectedRow();
            if (selectedRow != -1) {
                JPasswordField pf = new JPasswordField();
                int okCxl = JOptionPane.showConfirmDialog(this, pf, "Enter admin password to delete:", JOptionPane.OK_CANCEL_OPTION);
                if (okCxl == JOptionPane.OK_OPTION && new String(pf.getPassword()).equals("admin")) {
                    String usernameToDelete = tableModel.getValueAt(selectedRow, 1).toString();
                    tableModel.removeRow(selectedRow);
                    Users.accts.removeIf(u -> u.getUsername().equals(usernameToDelete));
                    saveUsers();
                    JOptionPane.showMessageDialog(this, "User deleted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "Incorrect password! Deletion failed.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Select a user to delete.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if (ev.getSource() == btnRefresh) {
            loadUsers();
            JOptionPane.showMessageDialog(this, "List refreshed!", "Info", JOptionPane.INFORMATION_MESSAGE);
        } else if (ev.getSource() == btnBack) {
            new SelectionAdmin().setVisible(true);
            setVisible(false);
        }
    }
}