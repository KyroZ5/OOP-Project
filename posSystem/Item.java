package posSystem;

public class Item {
    private String barcode;
    private String name;
    private int stock;
    private double price;

    public Item(String barcode, String name, int stock, double price) {
        this.barcode = barcode;
        this.name = name;
        this.stock = stock;
        this.price = price;
    }

    public String getBarcode() { return barcode; }
    public String getName() { return name; }
    public int getStock() { return stock; }
    public double getPrice() { return price; }

    public void setName(String name) { this.name = name; }
    public void setStock(int stock) { this.stock = stock; }
    public void setPrice(double price) { this.price = price; }
    
    public String saveItems() {
        return barcode + "," + name + "," + stock + "," + price;
    }
    
    
}