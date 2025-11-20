package posSystem;

import java.util.ArrayList;

public class InventoryData {
    public static ArrayList<Item> items = new ArrayList<>();

    public static void loadSampleItems() {
        if (items.isEmpty()) {
        
        }
    }

    public static ArrayList<Item> getItems() {
        return items;
    }
}