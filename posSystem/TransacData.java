package posSystem;

import java.util.ArrayList;

public class TransacData {
    public static ArrayList<TransacData> transData = new ArrayList<>();

    private int transactionNo;
    private String dateAndTime;
    private double amount;

    public TransacData(int transactionNo, String dateAndTime, double amount) {
        this.transactionNo = transactionNo;
        this.dateAndTime = dateAndTime;
        this.amount = amount;
    }

    public int getTRansactionNo() { return transactionNo; }
    public String getDateAndTime() { return dateAndTime; }
    public double getAmount() { return amount; }

    public static void loadUser(ArrayList<String> lines) {
        transData .clear();
        for (String line : lines) {
            String[] parts = line.split(",");
            if (parts.length == 3) {
                transData.add(new TransacData(parts[0], parts[1], parts[2]));
            }
        }
    }

    public String saveUser() {
        return transactionNo+ "," + dateAndTime + "," + amount;
    }

}