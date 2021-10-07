/**
 * @author:     Sorto
 * @email:      ricardojosesorto@yahoo.com
 * @date:       2021-10-03
 * @pruporse:   Vending Machine - App
 */
package com.rjsh.vendingmachine;
import com.rjsh.vendingmachine.controller.VendingMachineController;
import com.rjsh.vendingmachine.dao.VendingMachineDao;
import com.rjsh.vendingmachine.dao.VendingMachineDaoFileImpl;
import com.rjsh.vendingmachine.ui.UserIO;
import com.rjsh.vendingmachine.ui.UserIOConsoleImpl;
import com.rjsh.vendingmachine.ui.VendingMachineView;


public class App
{
    public static void main(String[] args)
    {
    // Instantiate the UserIO implementation
        UserIO myIo = new UserIOConsoleImpl();
        // Instantiate the View and wire the UserIO implementation into it
        VendingMachineView myView = new VendingMachineView(myIo);
        // Instantiate the DAO
        VendingMachineDao myDao = new VendingMachineDaoFileImpl();
        // Instantiate the Audit DAO
        //VendingMachineAuditDao myAuditDao = new VendingMachineAuditDaoFileImpl();
        // Instantiate the Service Layer and wire the DAO and Audit DAO into it
        //VendingMachineServiceLayer myService = new VendingMachineServiceLayerImpl(myDao, myAuditDao);
        // Instantiate the Controller and wire the Service Layer into it
        VendingMachineController controller = new VendingMachineController(myDao, myView);
        // Kick off the Controller
        controller.run();
    }
}
