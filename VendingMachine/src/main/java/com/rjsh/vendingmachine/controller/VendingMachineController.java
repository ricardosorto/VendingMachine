/**
 * @author:     Sorto
 * @email:      ricardojosesorto@yahoo.com
 * @date:       2021-10-03
 * @pruporse:   Vending Machine - App
 */
package com.rjsh.vendingmachine.controller;
import com.rjsh.vendingmachine.dao.VendingMachineDao;
import com.rjsh.vendingmachine.dao.VendingMachineDaoException;
import com.rjsh.vendingmachine.dao.VendingMachinePersistenceException;
import com.rjsh.vendingmachine.dto.Inventory;
import com.rjsh.vendingmachine.ui.UserIO;
import com.rjsh.vendingmachine.ui.VendingMachineView;
import java.util.List;


public class VendingMachineController 
{
    private VendingMachineView view;
    private VendingMachineDao dao;
    private UserIO io;
    
    public VendingMachineController(VendingMachineDao dao,  VendingMachineView view)
    {
        this.dao = dao;
        this.view = view;
    }
    
    public void run()
    {
        boolean keepGoing = true;
        int menuSelection = 0;
        try
        {
            while (keepGoing)
            {
                menuSelection = getMenuSelection();

                switch (menuSelection)
                {
                    case 1:
                        getSelectionBuyItems();
                        break;
                    case 2:
                        editMenuInventory();
                        break;
                    case 3:
                        keepGoing = false;
                        break;
                    default:
                        unknownCommand();
                }
            }
            exitMessage();
        }
        catch(VendingMachinePersistenceException e)
        {
            view.displayErrorMessage(e.getMessage());
        }
    }
    
    private int getMenuSelection()
    {
        return view.printMenuAndGetSelection();
    }
    
    //1 - Add new item to inventory
    private void createInventory() throws VendingMachineDaoException
    {
        view.displayCreateInventoryBanner();
        Inventory newItemId = view.getNewInventoryInfo();
        dao.addInventory(newItemId.getItemId(), newItemId);
        view.displayCreateSuccessBanner();
    }
    
    //2 - Remove item from inventory
    private void removeInventory() throws VendingMachineDaoException
    {
        view.displayRemoveInventoryBanner();
        String itemId = view.getItemIdChoice();
        Inventory removedInventory = dao.removeInventory(itemId);
        view.displayRemoveResult(removedInventory);
    }
    
    //3 - Menu buy items
    private void getSelectionBuyItems()
    {
        boolean buykeepGoing = true;
        int menuBuySelection = 0;
        double amount = 0.00;
        
        try
        {
            while(buykeepGoing)
            {
                amount = getAmountToBuy();
                
                if(amount > 2.00)
                {
                    menuBuySelection = getSelectionToBuy();
                    switch (menuBuySelection)
                    {
                        case 1://1. Buy Cheese Ruffles ----- $2.50
                            if(amount < 2.50)
                            {
                                view.displayLowAmountBanner();
                            }
                            else
                            {
                                buyingItemInventory(1                                                                                                                                                                            );
                            }
                            break;
                        case 2://2. Buy Classic Coca-Cola -- $3.00
                            if(amount < 2.50)
                            {
                                view.displayLowAmountBanner();
                            }
                            else
                            {
                                //go to buy ruffles
                            }
                            break;
                        case 3://3. Buy Pure Life Water ---- $2.00
                            //go to buy water
                            break;
                        case 4:
                            buykeepGoing = false;
                            break;
                        default:
                            //
                    }
                }
                else
                {
                    view.displayLowAmountBanner();
                }
            }
            exitBuyItemMessage();
        }
        catch(VendingMachinePersistenceException e)
        {
            view.displayErrorMessage(e.getMessage());
        }
    }
    
    private double getAmountToBuy()
    {
        double value = 0.00;
        double[] amount;
        amount = view.displayEnterCoinsMessage();
        value = dao.calculateAmountCoins(amount);
        return value;
    }
    
    private double buyingItemInventory()
    {
        double amountCh = 0.00;
        dao.buyingItem(0, item);
        return amountCh;
    }
    
    private int getSelectionToBuy()
    {
        return view.printMenuBuyItems();
    }
    
    //4 - Edit the information for an existing DVD in the collection
    private void editMenuInventory()
    {
        boolean editkeepGoing = true;
        int menuEditSelection = 0;
        try
        {
            while (editkeepGoing)
            {
                menuEditSelection = getEditMenuSelection();

                switch (menuEditSelection)
                {
                    case 1:
                        createInventory();
                        break;
                    case 2:
                        editInventoryField(2, "1");
                        break;
                    case 3:
                        editkeepGoing = false;
                        break;
                    default:
                        unknownCommand();
                }
            }
            exitEditMenuInventory();
        }
        catch(VendingMachinePersistenceException e)
        {
            view.displayErrorMessage(e.getMessage());
        }
    }
    
    private void editInventoryField(int fieldModify, String fieldModifyString) throws VendingMachineDaoException
    {
        view.displayEditInventoryBanner();
        String itemIdfind = view.setInventoryToFind();
        String newDataEdit = view.getInventoryFielToEdit(fieldModifyString);
        List<Inventory> inventoryList = dao.editInventoryField(itemIdfind, fieldModify, newDataEdit);
        view.displayEditedInventoryBanner();
    }
    
    private void unknownCommand()
    {
        view.displayUnknownCommandBanner();
    }
    
    private void exitMessage()
    {
        view.displayExitBanner();
    }
    
    private void exitEditMenuInventory()
    {
        view.displayExitBanner();
    }

    private void exitBuyItemMessage()
    {
        view.displayExitBanner();
    }
}
