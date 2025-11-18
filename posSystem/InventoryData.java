package posSystem;

import java.util.ArrayList;

public class InventoryData {
    public static ArrayList<Item> items = new ArrayList<>();

    public static void loadSampleItems() {
        if (items.isEmpty()) {
            items.add(new Item("001", "Apple", 50, 10.0));
            items.add(new Item("002", "Banana", 30, 5.0));
            items.add(new Item("003", "Orange", 20, 8.5));
            items.add(new Item("004", "Orange", 20, 8.5));
            items.add(new Item("005", "Orange", 20, 8.5));
            items.add(new Item("006", "Orange", 20, 8.5));
            items.add(new Item("007", "Orange", 20, 8.5));
            items.add(new Item("008", "Orange", 20, 8.5));
            items.add(new Item("009", "Orange", 20, 8.5));
            items.add(new Item("010", "Orange", 20, 8.5));
            items.add(new Item("011", "Orange", 20, 8.5));
            items.add(new Item("012", "Orange", 20, 8.5));
            items.add(new Item("013", "Orange", 20, 8.5));
        }
    }

    public static ArrayList<Item> getItems() {
        return items;
    }
}