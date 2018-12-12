package Controller;



import java.io.File;
import Model.Inventory;
import Model.Item;
import View.Display;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class LoadFile {
    private File file;
    private Inventory inv;
    private Scanner in;
    private Scanner sc;
    private String currLine;
    
    public LoadFile(String fileName) throws FileNotFoundException, IOException{
        file = new File(fileName + ".txt");
        sc = new Scanner(new FileReader(file));
        inv = new Inventory();
        loadData();
        sc.close();
        Display.run.addInv(inv);
    }
    
    public void loadData() throws IOException{
        currLine = sc.nextLine();
        if(currLine.equals("inv%")){
            currLine = sc.nextLine();
            inv.setName(currLine);
            currLine = sc.nextLine();
            while(currLine.equals("item%")){
                Item item = newItem();
                inv.addItem(item);
                currLine = sc.nextLine();
            }
        }
    }
    
    public Item newItem(){
        Item item = new Item();
        currLine = sc.nextLine();
        item.setName(currLine);
        currLine = sc.nextLine();
        item.plus(Integer.parseInt(currLine));
        currLine = sc.nextLine();
        while(currLine.equals("att%")){
            currLine = sc.nextLine();
            item.addAttribute(currLine);
            currLine = sc.nextLine();
        }
        return item;
    }

    public Inventory getInv() {
        return inv;
    }
}
