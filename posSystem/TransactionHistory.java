package posSystem;
import posSystem.TransacData;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

public class TransactionHistory extends JFrame implements ActionListener {

    JButton btnRefresh = new JButton("Refresh");
    JButton btnLogout = new JButton("Back");

    private DefaultTableModel tableModel;
    private JTable transacTable;

    ImageIcon logo = new ImageIcon("./img/logo-icon-dark-transparent.png");
    Color myColor = new Color(193, 234, 242);

    public TransactionHistory() {
        setSize(480, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Transaction History");
        setLayout(null);
        setResizable(false);
        setIconImage(logo.getImage());
        getContentPane().setBackground(myColor);
        setUndecorated(true);
        
        add(btnRefresh);
        add(btnLogout);

        btnRefresh.setBounds(10, 510, 80, 30);
        btnLogout.setBounds(390, 510, 80, 30);

        btnRefresh.addActionListener(this);
        btnLogout.addActionListener(this);

        tableModel = new DefaultTableModel(new String[]{"Transaction No.", "Date and Time", "Amount"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        transacTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(transacTable);
        scrollPane.setBounds(10, 50, 460, 450);
        add(scrollPane);

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
        } else if (ev.getSource() == btnLogout) {
            new SelectionCashier().setVisible(true);
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new TransactionHistory().setVisible(true);
    }
    
}