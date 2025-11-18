package posSystem;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class TransacData {
    public static ArrayList<TransacData> transData = new ArrayList<>();

    private int transactionNo;
    private String dateAndTime;
    private double amount;

    public TransacData(int transactionNo, double amount) {
        this.transactionNo = transactionNo;
        this.dateAndTime = generateFormattedTimestamp();
        this.amount = amount;
    }

    public int getTransactionNo() { return transactionNo; }
    public String getDateAndTime() { return dateAndTime; }
    public double getAmount() { return amount; }

    private String generateFormattedTimestamp() {
        String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        String time = new SimpleDateFormat("hh:mm a").format(new Date());
        return date + " " + time;
    }
}