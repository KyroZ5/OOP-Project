
package posSystem;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class TransactionHistoryAdmin extends JFrame implements ActionListener {

    JButton btnDel = new JButton("Delete");
    JButton btnRefresh = new JButton("Refresh");
    JButton btnLogout = new JButton("Back");
    
    private DefaultTableModel tableModel;
    private JTable transacTable;
   

    ImageIcon logo = new ImageIcon("./img/logo-icon-dark-transparent.png");
    
    Color myColor = new Color(193, 234, 242);

    public TransactionHistoryAdmin() {
        setSize(500, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Transaction History");
        setLayout(null);
        setResizable(false);
        setIconImage(logo.getImage());
        getContentPane().setBackground(myColor);

       
        add(btnDel);
        add(btnRefresh);
        add(btnLogout);

      
        btnDel.setBounds(190, 10, 80, 30);
        btnRefresh.setBounds(280, 10, 80, 30);
        btnLogout.setBounds(390, 10, 80, 30);

       
        btnDel.addActionListener(this);
        btnRefresh.addActionListener(this);
        btnLogout.addActionListener(this);
        
        tableModel = new DefaultTableModel(new String[]{"Tansaction No.", "Date and Time", "Amount"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        transacTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(transacTable);
        scrollPane.setBounds(10, 50, 460, 500);
        add(scrollPane);

        transacTable.getColumnModel().getColumn(2);

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

    private void saveUsersToFile() {
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
         if (ev.getSource() == btnDel) {
            int selectedRow = transacTable.getSelectedRow();
            if (selectedRow != -1) {
                JPasswordField pf = new JPasswordField();
                int okCxl = JOptionPane.showConfirmDialog(this, pf, "Enter admin password to delete:", JOptionPane.OK_CANCEL_OPTION);
                if (okCxl == JOptionPane.OK_OPTION && new String(pf.getPassword()).equals("admin")) {
                    String usernameToDelete = tableModel.getValueAt(selectedRow, 1).toString();
                    tableModel.removeRow(selectedRow);
                    Users.accts.removeIf(u -> u.getUsername().equals(usernameToDelete));
                    saveUsersToFile();
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
        } else if (ev.getSource() == btnLogout) {
            new SelectionAdmin().setVisible(true);
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new Admin().setVisible(true);
    }
}
