package Controller;

import java.util.ArrayList;
import Model.Inventory;
import Model.Item;
import View.Display;
import static View.Display.selectionInput;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;


public class Program 
{
    int dispNum;
    
    // temp variable using for inventory number
    int number;
    // temp variable using for item number
    int number2;
    // temp variable using for attribute number
    int number3;
    
    private ArrayList<Inventory> inventories;
    Scanner in = new Scanner(System.in);
    
    public Program() throws IOException
    {
        inventories = new ArrayList<>();
        number = 0;
        initButtons();
        mainMenu();
    }
    
     public void newInv(String name) throws IOException
    {
        Inventory inv = new Inventory();
        addInv(inv);
        //menu code
        inv.setName(name);
        //call show inv
        Display.increaseMenuNumber();
        selectionInput.setVisible(true);
        Display.showButton.setVisible(true);
        Display.editButton.setVisible(true);
        number = inventories.size() - 1;
        showInv();
        
    }
    
    public void addInv(Inventory inv){
        inventories.add(inv);
    }
    
    public void editInv() throws IOException
    {
        number = (Integer.parseInt(selectionInput.getText()) - 1);
        
        if(number < inventories.size())
            inventories.get(number).setName(Display.userInput.getText());
        
        mainMenu();
    }
    
    public void showInv() throws IOException
    {
        Display.displayText.delete(0, Display.displayText.length());
        Display.displayText.append("Inventory: " + inventories.get(number).getName() + "\n");
        Display.displayText.append("Available items:\n");
        dispNum = 1;
        for(Item item: inventories.get(number).getItems()){
            Display.displayText.append("\t#" + (dispNum) + ": " + item.getName() + "\tQuantity: " + item.getQuantity() + "\n");
            dispNum++;
        }
        textInstructions();
        
        //hide unnesecary buttons
        Display.saveButton.setVisible(true);
        Display.backButton.setVisible(true);
        Display.deleteButton.setVisible(true);
        Display.addButton.setVisible(false);
        Display.subtractButton.setVisible(false);
        Display.submitButton.setVisible(false);
        Display.userInput.setVisible(false);
        Display.loadButton.setVisible(false);
        Display.newButton.setVisible(true);
        
        Display.newButton.setText("New Item");
}
    
    public void newItem(String name) throws IOException
    {
        number = inventories.size() - 1;
        
        //prompt for name and call show item
        Item item = new Item();
        inventories.get(number).getItems().add(item);

        item.setName(name);

        item.plus(1);
        //call show inv
        number2 = inventories.get(number).getItems().size() - 1;
        showItem();
        Display.increaseMenuNumber();
    }
    
    public void editItem() throws IOException
    {
        number2 = (Integer.parseInt(selectionInput.getText()) - 1);
        
        if(inventories.get(number).getItems().get(number2) != null)
            inventories.get(number).getItems().get(number2).setName(Display.userInput.getText());
        
        showInv();
    }
    
    public void showItem() throws IOException
    {
        Display.displayText.delete(0, Display.displayText.length());
        Display.displayText.append("Item: " + inventories.get(number).getItems().get(number2).getName() + "\t Quantity: " + inventories.get(number).getItems().get(number2).getQuantity() + "\n");
        Display.displayText.append("Available attributes:\n");
        dispNum = 1;
        for(String att: inventories.get(number).getItems().get(number2).getAttributes()){
            Display.displayText.append("\t#" + (dispNum) + ": " + att + "\n");
            dispNum++;
        }
        textInstructions();
        
        //hide unnesecary buttons
        Display.saveButton.setVisible(false);
        Display.backButton.setVisible(true);
        Display.deleteButton.setVisible(true);
        Display.addButton.setVisible(true);
        Display.subtractButton.setVisible(true);
        Display.submitButton.setVisible(false);
        Display.userInput.setVisible(false);
        Display.loadButton.setVisible(false);
        Display.newButton.setVisible(true);
        
        Display.newButton.setText("New Att");
    }
    
    public void newAtt(String desc) throws IOException{
        //menu code
        inventories.get(number).getItems().get(number2).getAttributes().add(desc);
        //call show inv
        showAtt(number, number2, inventories.get(number).getItems().get(number2).getAttributes().size()-1);
    }
    
    public void editAtt() throws IOException
    {
        number3 = (Integer.parseInt(selectionInput.getText()) - 1);
        
        if(inventories.get(number).getItems().get(number2).getAttributes().get(number3) != null)
            inventories.get(number).getItems().get(number2).getAttributes().remove(number3);
            inventories.get(number).getItems().get(number2).getAttributes().add(Display.userInput.getText());
        
        showItem();
        

    }
    
    public void showAtt(int invNum, int itemNum, int attNum) throws IOException{
        Display.displayText.delete(0, Display.displayText.length());
        Display.displayText.append("Attribute: " + inventories.get(number).getItems().get(number2).getAttributes().get(number3) + "\n");
        textInstructions();
        
        //hide unnesecary buttons
        Display.saveButton.setVisible(false);
        Display.backButton.setVisible(true);
        Display.deleteButton.setVisible(true);
        Display.addButton.setVisible(false);
        Display.subtractButton.setVisible(false);
        Display.submitButton.setVisible(false);
        Display.userInput.setVisible(false);
        Display.loadButton.setVisible(false);
        Display.newButton.setVisible(false);
}

    
    public void save(Inventory inv, String fileName) throws FileNotFoundException, IOException
    {
        //print invs and ask to select one to save
        SaveFile save = new SaveFile(inv, fileName);
        mainMenu();
    }
    
    public void load(String fileName) throws IOException
    {
        LoadFile load = new LoadFile(fileName);
        mainMenu();
    }
    
    public void mainMenu() throws IOException
    {        
        Display.displayText.delete(0, Display.displayText.length());
        Display.displayText.append("Welcome to the inventory management system.\n"
                + "Please enter your selection based on the following menu.\n\n");

        Display.displayText.append("Available inventories:\n");
        dispNum = 1;
        for(Inventory inv: inventories){
            Display.displayText.append("\t#" + (dispNum) + ": " + inv.getName() + "\n");
            dispNum++;
        }
        textInstructions();
        
        //hide unnesecary buttons
        Display.saveButton.setVisible(false);
        Display.backButton.setVisible(true);
        Display.deleteButton.setVisible(false);
        Display.addButton.setVisible(false);
        Display.subtractButton.setVisible(false);
        Display.submitButton.setVisible(false);
        Display.userInput.setVisible(false);
        Display.loadButton.setVisible(true);
        Display.newButton.setVisible(true);
        
        Display.newButton.setText("New Inv");
    }  
    
    public void initButtons(){
        //set button actions
        Display.newButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override 
            public void handle(ActionEvent e) {
                Display.submitButton.setVisible(true);
                Display.userInput.setVisible(true);
                Display.userInput.clear();
                Display.userInput.setPromptText("Please enter the name here");
                Display.submitButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override 
                public void handle(ActionEvent e) {
                    String userEntry;
                    try {
                        if(Display.menuNumber == 0)
                        {
                            userEntry = Display.userInput.getText();
                            newInv(userEntry);      
                            Display.submitButton.setVisible(false);
                            Display.userInput.setVisible(false);
                            Display.backButton.setVisible(true);
                        }
                        else if(Display.menuNumber == 1)
                        {
                            userEntry = Display.userInput.getText();
                            newItem(userEntry);
                            Display.submitButton.setVisible(false);
                            Display.userInput.setVisible(false);
                            Display.addButton.setVisible(true);
                            Display.subtractButton.setVisible(true);
                        }
                        else if(Display.menuNumber == 2)
                        {
                            userEntry = Display.userInput.getText();
                            newAtt(userEntry);         
                            Display.submitButton.setVisible(false);
                            Display.userInput.setVisible(false);
                            Display.addButton.setVisible(false);
                            Display.subtractButton.setVisible(false);
                        }
                    } 
                    catch (IOException ex) {
                        Logger.getLogger(Program.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
            }
        });
        
        Display.addButton.setOnAction(new EventHandler<ActionEvent>() 
        {
            @Override 
            public void handle(ActionEvent e) 
            {
                Display.submitButton.setVisible(true);
                Display.userInput.setVisible(true);
                Display.userInput.clear();
                Display.userInput.setPromptText("Please enter amount to add");
                Display.submitButton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override 
                    public void handle(ActionEvent e) {
                        inventories.get(number).getItems().get(number2).plus(Integer.parseInt(Display.userInput.getText()));
                        Display.submitButton.setVisible(false);
                        Display.userInput.setVisible(false);
                        Display.backButton.setVisible(true);
                        try {
                            showItem();
                        } catch (IOException ex) {
                            Logger.getLogger(Program.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });
            }
        });
        
        Display.subtractButton.setOnAction(new EventHandler<ActionEvent>() 
        {
            @Override 
            public void handle(ActionEvent e) 
            {
                Display.submitButton.setVisible(true);
                Display.userInput.setVisible(true);
                Display.userInput.clear();
                Display.userInput.setPromptText("Please enter amount to subtract");
                Display.submitButton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override 
                    public void handle(ActionEvent e) {
                        inventories.get(number).getItems().get(number2).minus(Integer.parseInt(Display.userInput.getText()));
                        Display.submitButton.setVisible(false);
                        Display.userInput.setVisible(false);
                        Display.backButton.setVisible(true);
                        try {
                            showItem();
                        } catch (IOException ex) {
                            Logger.getLogger(Program.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });
            }
        });
        
        Display.saveButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override 
            public void handle(ActionEvent e) {
                Display.submitButton.setVisible(true);
                Display.userInput.setVisible(true);
                Display.userInput.clear();
                Display.userInput.setPromptText("Please enter a name for this file");
                Display.submitButton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override 
                    public void handle(ActionEvent e) {
                        try {
                            save(inventories.get(number), Display.userInput.getText());
                        } catch (IOException ex) {
                            Logger.getLogger(Program.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });
            }
        });
        
        Display.loadButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override 
            public void handle(ActionEvent e) {
                Display.submitButton.setVisible(true);
                Display.userInput.setVisible(true);
                Display.userInput.clear();
                Display.userInput.setPromptText("Please enter the file name to load");
                Display.submitButton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override 
                    public void handle(ActionEvent e) {
                        try {
                            load(Display.userInput.getText());
                        } catch (IOException ex) {
                            Logger.getLogger(Program.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });
            }
        });

        Display.editButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override 
            public void handle(ActionEvent e) 
            {
                Display.submitButton.setVisible(true);
                Display.userInput.setVisible(true);
                Display.userInput.clear();
                Display.userInput.setPromptText("Please enter the updated name here");
                Display.submitButton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override 
                    public void handle(ActionEvent e) {
                        try {
                            if(Display.menuNumber == 0)
                            {
                                editInv();      
                                Display.submitButton.setVisible(false);
                                Display.userInput.setVisible(false);
                                Display.backButton.setVisible(true);
                            }
                            else if(Display.menuNumber == 1)
                            {
                                editItem();
                                Display.submitButton.setVisible(false);
                                Display.userInput.setVisible(false);
                                Display.addButton.setVisible(true);
                                Display.subtractButton.setVisible(true);
                            }
                            else if(Display.menuNumber == 2)
                            {
                                editAtt();        
                                Display.submitButton.setVisible(false);
                                Display.userInput.setVisible(false);
                                Display.addButton.setVisible(false);
                                Display.subtractButton.setVisible(false);
                            }
                        } 
                        catch (IOException ex) {
                            Logger.getLogger(Program.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        Display.selectionInput.clear();
                    }
                });    
            }
        });
        
        Display.deleteButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override 
            public void handle(ActionEvent e) 
            {
                Display.submitButton.setVisible(true);
                Display.userInput.setVisible(true);
                Display.userInput.clear();
                if(Display.menuNumber == 1)
                    Display.userInput.setPromptText("Hit submit if you wish to delete this inventory");
                else if(Display.menuNumber == 2)
                    Display.userInput.setPromptText("Hit submit if you wish to delete this item");
                else if(Display.menuNumber == 3)
                    Display.userInput.setPromptText("Hit submit if you wish to delete this attribute");
                Display.submitButton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override 
                    public void handle(ActionEvent e) {
                        if(Display.menuNumber == 1)
                        {
                            inventories.remove(number);
                            try {
                                mainMenu();
                            } catch (IOException ex) {
                                Logger.getLogger(Program.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                        else if(Display.menuNumber == 2)
                        {
                            inventories.get(number).getItems().remove(number2);
                            try {
                                showInv();
                            } catch (IOException ex) {
                                Logger.getLogger(Program.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                        else if(Display.menuNumber == 3)
                        {
                            inventories.get(number).getItems().get(number2).getAttributes().remove(number3);
                            try {
                                showItem();
                            } catch (IOException ex) {
                                Logger.getLogger(Program.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }
                });    
            }
        });
        
        Display.exitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override 
            public void handle(ActionEvent e) {
                System.exit(0);
            }
        });
        
        Display.showButton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) 
                    {
                        if(Display.menuNumber == 0)
                        {     
                            number = (Integer.parseInt(Display.selectionInput.getText()) - 1);

                            if(inventories.get(number) != null)
                            {
                                Display.increaseMenuNumber();
                                try 
                                {
                                    showInv();
                                } 
                                catch (IOException ex) 
                                {
                                    Logger.getLogger(Program.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }

                        }
                        else if(Display.menuNumber == 1)
                        {
                            number2 = (Integer.parseInt(Display.selectionInput.getText()) - 1);

                            if(inventories.get(number).getItems().get(number2) != null)
                            {
                                Display.increaseMenuNumber();
                                try
                                {
                                    showItem();
                                }
                                catch (IOException ex)
                                {
                                    Logger.getLogger(Program.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                        }
                        else if(Display.menuNumber == 2)
                        {
                            number3 = (Integer.parseInt(Display.selectionInput.getText()) - 1);

                            if(inventories.get(number).getItems().get(number2).getAttributes().get(number3) != null)
                            {
                                Display.increaseMenuNumber();
                                try
                                {
                                    showAtt(number, number2, number3);
                                }
                                catch (IOException ex)
                                {
                                    Logger.getLogger(Program.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                        }
                        
                        Display.selectionInput.clear();
                    }

                });

        Display.backButton.setOnAction(new EventHandler<ActionEvent>() 
        {
            @Override 
            public void handle(ActionEvent e) 
            {
                if(Display.menuNumber == 3)
                {
                    Display.decreaseMenuNumber();
                    try 
                    {
                        showItem();
                    } 
                    catch (IOException ex) 
                    {
                        Logger.getLogger(Program.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                else if(Display.menuNumber == 2)
                {
                    Display.decreaseMenuNumber();
                    try 
                    {
                        showInv();
                    } 

                    catch (IOException ex) 
                    {
                        Logger.getLogger(Program.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                else if(Display.menuNumber == 1)
                {
                    Display.decreaseMenuNumber();
                    try 
                    {
                        mainMenu();
                    } 
                    catch (IOException ex) 
                    {
                        Logger.getLogger(Program.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                else
                {
                    try 
                    {
                        mainMenu();
                    } 
                    catch (IOException ex) 
                    {
                    Logger.getLogger(Program.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                Display.submitButton.setVisible(false);
                Display.userInput.clear();
                Display.userInput.setVisible(false);
                Display.selectionInput.clear();
                
            }

        });
    }
    
    public void textInstructions(){
        Display.displayText.append("\n\nPlease use the buttons on the left\nto navigate the program");
        Display.displayArea.setText(Display.displayText.toString());
    }
}
