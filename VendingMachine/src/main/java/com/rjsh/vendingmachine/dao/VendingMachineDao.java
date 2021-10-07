/**
 * @author:     Sorto
 * @email:      ricardojosesorto@yahoo.com
 * @date:       2021-10-03
 * @pruporse:   Vending Machine - VendingMachineDao
 */
package com.rjsh.vendingmachine.dao;
import com.rjsh.vendingmachine.dto.Inventory;
import java.util.List;

public interface VendingMachineDao
{
    Inventory addInventory(String itemId, Inventory inventory) throws VendingMachineDaoException;
    
    List<Inventory> getAllInventory() throws VendingMachineDaoException;
    
    Inventory getInventory(String itemId) throws VendingMachineDaoException;
    
    Inventory removeInventory(String itemId) throws VendingMachineDaoException;
    
    List<Inventory> editInventoryField(String itemId, int itemfield, String newDataEdit) throws VendingMachineDaoException;
    
    double calculateAmountCoins(double[] amount);
    
    double buyingItem(double amount, String item) throws VendingMachineDaoException;
}
