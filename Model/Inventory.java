package Model;

import java.util.ArrayList;

public class Inventory {
    //define vars
    private String name;
    private ArrayList<Item> items;
    
    public Inventory(){
        //initalize variables
        name = "";
        items = new ArrayList<Item>();
    }
    
    public void addItem(Item item){
        items.add(item);
    }
    
    public void deleteItem(Item item){
        items.remove(item);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    } 
}
