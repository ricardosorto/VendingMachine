/**
 * @author:     Sorto
 * @email:      ricardojosesorto@yahoo.com
 * @date:       2021-10-03
 * @pruporse:   Vending Machine - Coin
 */
package com.rjsh.vendingmachine.dto;


public class Coin
{
    //PENNIES, NICKELS, DIMES, QUARTERS;
    
    private int value;
    
    public Coin(int value)
    {
        this.value = value;
    }
    
    public int getValue()
    {
        return this.value;
    }
    
    public void setValue(int value)
    {
        this.value = value;
    }
}
