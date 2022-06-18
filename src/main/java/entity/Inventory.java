package entity;

import entity.item.Item;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;

import java.util.Collections;
import java.util.Comparator;

public class Inventory {
    public static ObservableList<Item> items = FXCollections.observableArrayList();
    private FilteredList<Item> filteredItems = new FilteredList<Item>(items);
    class ItemComparator implements Comparator<Item> {
        // Method
        // Sorting in ascending order of name
        public int compare(Item a, Item b)
        {
            if (a.getType().compareTo(b.getType())==0){
                return a.getName().compareTo(b.getName());
            }
            return a.getType().compareTo(b.getType());
        }
    }
    private ItemComparator comparator = new ItemComparator();
    public void sort(){
        Collections.sort(items,  comparator);
    }
    public FilteredList<Item> filterConsumables(){
        filteredItems.setPredicate(item -> item.getType().contains("Consumables"));
        return filteredItems;
    }
}
