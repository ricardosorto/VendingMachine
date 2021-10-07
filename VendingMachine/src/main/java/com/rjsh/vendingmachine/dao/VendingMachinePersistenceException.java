/**
 * @author:     Sorto
 * @email:      ricardojosesorto@yahoo.com
 * @date:       2021-10-03
 * @pruporse:   Vending Machine - VendingMachinePersistenceException
 */
package com.rjsh.vendingmachine.dao;

public class VendingMachinePersistenceException extends Exception
{
    public VendingMachinePersistenceException(String message)
    {
        super(message);
    }
    
    public VendingMachinePersistenceException(String message, Throwable cause)
    {
        super(message, cause);
    }
}
