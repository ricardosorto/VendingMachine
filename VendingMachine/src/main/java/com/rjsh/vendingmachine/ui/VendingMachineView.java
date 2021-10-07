/**
 * @author:     Sorto
 * @email:      ricardojosesorto@yahoo.com
 * @date:       2021-10-03
 * @pruporse:   Vending Machine - VendingMachineView
 */
package com.rjsh.vendingmachine.ui;
import com.rjsh.vendingmachine.dto.Inventory;
import java.util.List;

public class VendingMachineView
{
    private UserIO io;
    
    public VendingMachineView(UserIO io)
    {
        this.io = io;
    }
    
    public int printMenuAndGetSelection()
    {
        io.print("Main Menu");
        io.print("1. Buy items");
        io.print("2. Manage inventory");
        io.print("3. Exit");

        return io.readInt("Please select from the above choices.", 1, 3);
    }
    
    public int printMenuBuyItems()
    {
        io.print("Main Menu");
        io.print("1. Buy Cheese Ruffles ----- $2.50");
        io.print("2. Buy Classic Coca-Cola -- $3.00");
        io.print("3. Buy Pure Life Water ---- $2.00");
        io.print("4. Exit");

        return io.readInt("Please select from the above choices.", 1, 4);
    }
    
    public int printMenuManageItems()
    {
        io.print("Manage Item Menu");
        io.print("1. Add items");
        io.print("2. Edit items");
        io.print("3. Exit");

        return io.readInt("Please select from the above choices.", 1, 3);
    }
    
    public void displayLowAmountBanner()
    {
        io.print("The amount is insufficient to buy the product, please enter another amount.");
    }
    
    public String setInventoryToFind()
    { 
        return io.readString("Please enter the Item ID to edit");
    }
    
    public String getInventoryFielToEdit(String optionEdit)
    {
        io.print(" ");
        return io.readString("Please enter the " + optionEdit + " to edit");
    }
    
    public double[] displayEnterCoinsMessage()
    {
        double[] amount = new double[4];
        io.print("Please enter coins as follows: ");
        amount[0] = io.readDouble("Plese enter the pennies:");
        amount[1] = io.readDouble("Plese enter the nicles:");
        amount[2] = io.readDouble("Plese enter the dimes:");
        amount[3] = io.readDouble("Plese enter the quarters:");
        
        return amount;
    }
    
    public Inventory getNewInventoryInfo()
    {
        String itemId = io.readString("Please enter Item Id");
        String itemName = io.readString("Please enter Item Name");
        String itemCost = io.readString("Please enter Item Cost");
        String itemNumber = io.readString("Please enter Item Number");
        Inventory currentInventory = new Inventory(itemId);
        currentInventory.setItemName(itemName);
        currentInventory.setItemCost(itemCost);
        currentInventory.setItemNumber(itemNumber);
        return currentInventory;
    }
    
    public void displayCreateInventoryBanner()
    {
        io.print("=== Create Inventory ===");
    }
    
    public void displayCreateSuccessBanner()
    {
        io.readString("Inventory successfully created.  Please hit enter to continue");
    }
    
    public void displayInventoryList(List<Inventory> inventoryList)
    {
        for (Inventory currentInventory : inventoryList)
        {
            String inventoryInfo = String.format("#%s : %s %s",
                  currentInventory.getItemId(),
                  currentInventory.getItemName(),
                  currentInventory.getItemCost(),
                  currentInventory.getItemNumber());
            io.print(inventoryInfo);
        }
        io.readString("Please hit enter to continue.");
    }
    
    public void displayDisplayAllBanner()
    {
        io.print("=== Display All Inventory ===");
    }

    public String getItemIdChoice()
    {
        return io.readString("Please enter the Item ID.");
    }
    
    public void displayRemoveInventoryBanner()
    {
        io.print("=== Remove Inventory ===");
    }

    public void displayRemoveResult(Inventory inventoryRecord)
    {
        if(inventoryRecord != null)
        {
          io.print("Inventory successfully removed.");
        }
        else
        {
          io.print("No such inventory.");
        }
        io.readString("Please hit enter to continue.");
    }
    
    public void displayEditInventoryBanner()
    {
        io.print(" ");
        io.print("=== Edit Inventory ===");
        io.print(" ");
    }
    
    public void displayEditedInventoryBanner()
    {
        io.print(" ");
        io.print("=== Edited Inventory ===");
        io.print(" ");
    }
    
    public void displayExitBanner()
    {
        io.print("Good Bye!!!");
    }

    public void displayUnknownCommandBanner()
    {
        io.print("Unknown Command!!!");
    }
    
    public void displayErrorMessage(String errorMsg)
    {
        io.print("=== ERROR ===");
        io.print(errorMsg);
    }
}
