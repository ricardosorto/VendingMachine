/**
 * @author:     Sorto
 * @email:      ricardojosesorto@yahoo.com
 * @date:       2021-10-03
 * @pruporse:   Vending Machine - VendingMachineDaoFileImpl
 */
package com.rjsh.vendingmachine.dao;
import com.rjsh.vendingmachine.dto.Inventory;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;


public class VendingMachineDaoFileImpl implements VendingMachineDao
{
    private Map<String, Inventory> inventoryLibraries = new HashMap<>();
    private ArrayList<Inventory> inventoryEdit;
    
    public static final String ROSTER_FILE = "VendingMachine.txt";
    public static final String DELIMITER = "::";

    @Override
    public Inventory addInventory(String itemId, Inventory inventory) throws VendingMachineDaoException
    {
        loadRoster();
        Inventory prevInventory = inventoryLibraries.put(itemId, inventory);
        writeRoster();
        return prevInventory;
    }

    @Override
    public List<Inventory> getAllInventory() throws VendingMachineDaoException
    {
        loadRoster();
        return new ArrayList<Inventory>(inventoryLibraries.values());
    }

    @Override
    public Inventory getInventory(String itemId) throws VendingMachineDaoException
    {
        loadRoster();
        return inventoryLibraries.get(itemId);
    }

    @Override
    public Inventory removeInventory(String itemId) throws VendingMachineDaoException
    {
        loadRoster();
        Inventory removedInventory = inventoryLibraries.remove(itemId);
        writeRoster();
        return removedInventory;
    }

    @Override
    public List<Inventory> editInventoryField(String itemId, int itemEdit, String newDataEdit) throws VendingMachineDaoException
    {
        List<Inventory> editedInventory;
        editedInventory = editFieldInventory(itemId, itemEdit, newDataEdit);
        return editedInventory;
    }
    
    @Override
    public double calculateAmountCoins(double[] amount)
    {
        double value;
        value = CalculateAmount(amount);
        return value;
    }
    
    @Override
    public double buyingItem(double amount, String item) throws VendingMachineDaoException
    {
        double valueChange = 0.00;
        valueChange = buyingTheItem(amount, item);
        return valueChange;
    }
    
    private Inventory unmarshallInventory(String inventoryAsText)
    {
        String[] inventoryTokens = inventoryAsText.split(DELIMITER);
        
        String itemId = inventoryTokens[0];
        
        Inventory inventoryFromFile = new Inventory(itemId);
        
        // Index 1 - DVD Release Date
        inventoryFromFile.setItemName(inventoryTokens[1]);

        // Index 2 - DVD MPAA Rating
        inventoryFromFile.setItemCost(inventoryTokens[2]);

        // Index 3 - DVD Director's Name
        inventoryFromFile.setItemNumber(inventoryTokens[3]);
        
        return inventoryFromFile;
    }
    
    private void loadRoster() throws VendingMachineDaoException
    {
        Scanner scanner;

        try {
            // Create Scanner for reading the file
            scanner = new Scanner(
                    new BufferedReader(
                            new FileReader(ROSTER_FILE)));
        } catch (FileNotFoundException e) {
            throw new VendingMachineDaoException(
                    "-_- Could not load roster data into memory.", e);
        }
        // currentLine holds the most recent line read from the file
        String currentLine;
        // currentStudent holds the most recent student unmarshalled
        Inventory currentInventory;
        // Go through ROSTER_FILE line by line, decoding each line into a 
        // Student object by calling the unmarshallStudent method.
        // Process while we have more lines in the file
        while (scanner.hasNextLine()) {
            // get the next line in the file
            currentLine = scanner.nextLine();
            // unmarshall the line into a Student
            currentInventory = unmarshallInventory(currentLine);

            // We are going to use the dvdTitle as the map key for our student object.
            // Put currentStudent into the map using student id as the key
            inventoryLibraries.put(currentInventory.getItemId(), currentInventory);
        }
        // close scanner
        scanner.close();
    }
    
    private String marshallInventory(Inventory aInventory)
    {
        // It's not a complicated process. Just get out each property,
        // and concatenate with our DELIMITER as a kind of spacer. 

        // Start with the DVD Title, since that's supposed to be first.
        String inventoryAsText = aInventory.getItemId() + DELIMITER;

        // DVD Release Date
        inventoryAsText += aInventory.getItemName() + DELIMITER;

        // DVD MPAA Rating
        inventoryAsText += aInventory.getItemCost() + DELIMITER;
        
        // DVD Aditional Information
        inventoryAsText += aInventory.getItemNumber();

        // We have now turned a DvdLibrary to text! Return it!
        return inventoryAsText;
    }
    
    private void writeRoster() throws VendingMachineDaoException
    {
        PrintWriter out;
        
        try
        {
            out = new PrintWriter(new FileWriter(ROSTER_FILE));
        }
        catch (IOException e)
        {
            throw new VendingMachineDaoException("Could not save student data.", e);
        }
        
        String inventoryAsText;
        List<Inventory> inventoryList = this.getAllInventory();
        for(Inventory currentInventory : inventoryList)
        {
            // turn a DvdLibrary into a String
            inventoryAsText = marshallInventory(currentInventory);
            // write the DvdLibrary object to the file
            out.println(inventoryAsText);
            // force PrintWriter to write line to the file
            out.flush();
        }
        // Clean up
        out.close();
    }
    
    private List<Inventory> editFieldInventory(String itemId, int fielEdit, String newData) throws VendingMachineDaoException
    {
        //Declaration variables
        boolean readEditFileSearch = false;
        String[][] findEditLines;
        String[] lines;
        
        ArrayList<Inventory> listEdit = new ArrayList<Inventory>();
        
        listEdit = ReadFile();
        
        Iterator<Inventory> itrEditInventory = listEdit.iterator();
        
        findEditLines = new String[listEdit.size()][4];
        lines = new String[listEdit.size()];
        int i = 0;
        while(itrEditInventory.hasNext())
        {
            Inventory objInvent = itrEditInventory.next();
            
            findEditLines[i][0] = objInvent.getItemId();
            findEditLines[i][1] = objInvent.getItemName();
            findEditLines[i][2] = objInvent.getItemCost();
            findEditLines[i][3] = objInvent.getItemNumber();
            
            i++;
        }
        
        for(int j = 0; j < listEdit.size(); j++)
        {
            if(findEditLines[j][0].equals(itemId))
            {
                findEditLines[j][fielEdit - 1] = newData;
            }
        }
        
        for (int k = 0; k < listEdit.size(); k++)
        {
            lines[k] = findEditLines[k][0] + "::" + findEditLines[k][1] + "::" + findEditLines[k][2] + 
                    "::" + findEditLines[k][3];
        }
        
        readEditFileSearch = CreateFileInventoryArray(lines);
        
        return listEdit;
    }
    
    private ArrayList ReadFile()
    {
        // Create an ArrayList of the object Class  "DVDLibraryClass"
        ArrayList<Inventory> listInventory = new ArrayList<Inventory>();

        // Istantiate the file where the data is
        File pathFile = new File(ROSTER_FILE);
        Scanner s;
        
        try
        {
            // read the file in the path
            s = new Scanner(pathFile);
            
            //Get the data from pathFile
            while (s.hasNextLine())
            {
		String line = s.nextLine();
		String [] cutString = line.split("::");
		Inventory objInvet = new Inventory();

		// Pongo los atributos al objeto "partido"
                objInvet.setItemId(cutString[0]);
                objInvet.setItemName(cutString[1]);
                objInvet.setItemCost(cutString[2]);
                objInvet.setItemNumber(cutString[3]);

		//add object "objDvdLib" to ArrayList
                listInventory.add(objInvet);
            }
            s.close();
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        
        return listInventory;
    }
    
    private boolean CreateFileInventoryArray(String[] lines)
    {
        boolean created = false;
        
        try
        {
            PrintWriter out = new PrintWriter(new FileWriter(ROSTER_FILE));
            for (String line : lines)
            {
                out.println(line);
            }
                
            out.flush();
            out.close();
            
            created = true;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        
        return created;
    }

    private double CalculateAmount(double[] amount)
    {
        double amountConveted = 0.00;
        BigDecimal value = new BigDecimal(0);
        
        for(int i = 0; i < 4; i++)
        {
            amountConveted = amountConveted + amount[i];
        }
        
        amountConveted = amountConveted / 100;
        value = value.setScale(2, RoundingMode.HALF_UP);
        
        amountConveted = value.doubleValue();
        
        return amountConveted;
    }

    private double buyingTheItem(double amount, String itemId) throws VendingMachineDaoException 
    {
        double valueChange = 0.00;
        ArrayList<Inventory> readFile = new ArrayList<Inventory>();
        readFile = ReadFile();
        Iterator<Inventory> itrEditInventory = readFile.iterator();
        
        String[][] findEditLines = new String[3][4];
        int i = 0;
        while(itrEditInventory.hasNext())
        {
            Inventory objInvent = itrEditInventory.next();
            
            findEditLines[i][0] = objInvent.getItemId();
            findEditLines[i][1] = objInvent.getItemName();
            findEditLines[i][2] = objInvent.getItemCost();
            findEditLines[i][3] = objInvent.getItemNumber();
            
            i++;
        }
        
        int position = Integer.parseInt(itemId);
        
        String getValue = findEditLines[position][3];
        int modifyValue = Integer.parseInt(getValue);
        modifyValue--;
        String newData = String.valueOf(modifyValue);
        List<Inventory> editedInventory;
        editedInventory = editFieldInventory(itemId, 4, newData);
        
        if(itemId.equals("1"))
        {
            valueChange = amount - 2.50;
        }
        else if(itemId.equals("2"))
        {
            valueChange = amount - 3.00;
        }
        else if(itemId.equals("3"))
        {
            valueChange = amount - 2.00;
        }
        
        return valueChange;
    }
    
}
