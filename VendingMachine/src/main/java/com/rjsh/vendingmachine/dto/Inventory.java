/**
 * @author:     Sorto
 * @email:      ricardojosesorto@yahoo.com
 * @date:       2021-10-03
 * @pruporse:   Vending Machine - Inventory
 */
package com.rjsh.vendingmachine.dto;


public class Inventory
{
    private String itemId;
    private String itemName;
    private String itemCost;
    private String itemNumber;
    
    public Inventory(String itemId)
    {
        this.itemId = itemId;
    }

    public Inventory() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public String getItemName()
    {
        return itemName;
    }
    
    public void setItemName(String itemName)
    {
        this.itemName = itemName;
    }
    
    public String getItemCost()
    {
        return itemCost;
    }
    
    public void setItemCost(String itemCost)
    {
        this.itemCost = itemCost;
    }
    
    public String getItemNumber()
    {
        return itemNumber;
    }
    
    public void setItemNumber(String itemNumber)
    {
        this.itemNumber = itemNumber;
    }
    
    public String getItemId()
    {
        return itemId;
    }
    
    public void setItemId(String itemId)
    {
        this.itemId = itemId;
    }
}
