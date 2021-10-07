/**
 * @author:     Sorto
 * @email:      ricardojosesorto@yahoo.com
 * @date:       2021-10-03
 * @pruporse:   Vending Machine - VendingMachineDaoException
 */
package com.rjsh.vendingmachine.dao;


public class VendingMachineDaoException extends Exception
{
    public VendingMachineDaoException(String message)
    {
        super(message);
    }
    
    public VendingMachineDaoException(String message, Throwable cause)
    {
        super(message, cause);
    }
}
