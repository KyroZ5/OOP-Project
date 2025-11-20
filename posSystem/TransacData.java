package posSystem;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class TransacData {
    public static ArrayList<TransacData> transData = new ArrayList<>();

    private int transactionNo;
    private String dateAndTime;
    private double amount;
    private String empName;
    private double balance;
    
    public TransacData(int transactionNo, double amount, double balance) {
        this.transactionNo = transactionNo;
        this.dateAndTime = generateFormattedTimestamp();
        this.amount = amount;
        this.empName = Users.getStaff();
        this.balance = balance;
    }
    public int getTransactionNo() { return transactionNo; }
    public String getDateAndTime() { return dateAndTime; }
    public double getAmount() { return amount; }
    public String getEmployeeName() { return empName; }
   
    public double getBalance() {
        return balance;
    }
    private String generateFormattedTimestamp() {
        String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        String time = new SimpleDateFormat("hh:mm a").format(new Date());
        return date + " " + time;
    }
}