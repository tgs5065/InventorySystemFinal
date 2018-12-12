package Controller;

import java.io.File;
import Model.Inventory;
import Model.Item;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class SaveFile {
    private File file;
    private Inventory inv;
    private PrintWriter writer;
    private Scanner in;
    
    public SaveFile(Inventory inv, String fileName) throws FileNotFoundException{
        this.inv = inv;
        file = new File(fileName + ".txt");
        writer = new PrintWriter(file);
        writeData();
        writer.close();
    }
    
    public void writeData(){
        writer.println("inv%");
        writer.println(inv.getName());
        ArrayList<Item> items = inv.getItems();
        for(Item item: items){
            writer.println("item%");
            writer.println(item.getName());
            writer.println(item.getQuantity());
            ArrayList<String> attrs = item.getAttributes();
            for(String att: attrs){
                writer.println("att%");
                writer.println(att);
            }
            writer.println("%att");
        }
        writer.println("%item");
        writer.println("%inv");
    }
}
