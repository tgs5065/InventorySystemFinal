package Model;

import java.util.ArrayList;

public class Item 
{
    private String name;
    private ArrayList<String> attributes;
    private int quantity;
    
    public Item()
    {
        name = "null";
        attributes = new ArrayList<String>();
        quantity = 0;
    }
    
    public void addAttribute(String attribute)
    {
        attributes.add(attribute);
    }
    
    public void listAttributes()
    {
        for(int i = 0; i < attributes.size(); i++)
        {
            System.out.println("#" + (i+1) + ": " + attributes.get(i));
        }
    }
    
    public void plus(int add)
    {
        quantity += add;
    }
    
    public void minus(int subtract)
    {
        if(quantity - subtract  < 0)
            System.out.println("Quantity can't be less than zero!");
        else
            quantity -= subtract;
    }

    public int getQuantity() 
    {
        return quantity;
    }
    
    public String getName() 
    {
        return name;
    }

    public void setName(String name) 
    {
        this.name = name;
    }

    public ArrayList<String> getAttributes() {
        return attributes;
    }

    public void setAttributes(ArrayList<String> attributes) 
    {
        this.attributes = attributes;
    }
    
    public void setAttribute(String att, int number)
    {
        attributes.set(number, att);
    }   
}

