package pricebasket;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import domain.Basket;
import domain.Item;
import domain.PriceList;

/**
 * @author Ahmed Salem
 *
 */

public class PriceBasket {

	
	/** @param fileLocStr  the path to CSV file 
	 *  @return PriceList  PriceList object populated with Item object(s)
	 *
	 *  Create a PriceList object and populate with item(s) from external CSV file 
	 */
	
     public static PriceList populatePriceList(String fileLocStr){
		
        String csvFileLocation = fileLocStr;
        BufferedReader br = null;
        String line = "";
        
        int row = 0;

        PriceList priceList = null;
        
        
        try {

            br = new BufferedReader(new FileReader(csvFileLocation));
            
            HashSet<Item> items_set = new HashSet<Item>();
            
            while ((line = br.readLine()) != null) {

            	if(row ++ > 0){
                    String[] items = line.split(",");
            		Item item = new Item(items[0], Double.parseDouble(items[1]));
            		items_set.add(item);
            	}
            }
            
            priceList = new PriceList(items_set);
            
        } catch (FileNotFoundException fex) {
        	System.err.println(fex.getMessage());
        	fex.printStackTrace();
        } catch (IOException ioex) {
        	System.err.println(ioex.getMessage());
        	ioex.printStackTrace();
        }catch (Exception ex) {
        	System.err.println(ex.getMessage());
        	ex.printStackTrace();
        }
        finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException ex) {
                	System.err.println(ex.getMessage());
                    ex.printStackTrace();
                }
            } 
        }
        return priceList;
	}


     
     
     
 	/** @param str_arr    the path to CSV file 
 	 *  @return Basket    Basket object populated with Item object(s)
 	 *
 	 *  Create a Basket object. populate with Items from command line 
 	 */
      public static Basket fillBasket(String []str_arr){

		 Basket basket = null;
		 HashMap<Item, Integer> basket_map = new HashMap<Item, Integer>();
	
			
			for(String str:str_arr){
			
				Item newItem = new Item();
				newItem.setName(str);
				
				if(basket_map.containsKey(newItem)){
					int i = basket_map.get(newItem);
					basket_map.put(newItem, ++i);
				}else{
					basket_map.put(newItem, 1);
				}
			}
			
		 basket = new Basket(basket_map);
				 
		return basket; 
	}
	
	
   
      
  	/** 
  	 *   main method
  	 *   
  	 *   Note: Input scanner works in infinite loop.
  	 *    
  	 */
  
      
	public static void main(String[]args){
        
		PriceBasket bb = new PriceBasket();
//	    PriceList priceList = populatePriceList("items.csv");
	    PriceList priceList = populatePriceList("c:\\mystuff\\items.csv");
		
		Set <Item> items_set = priceList.getItems();
			
		if(null != priceList && ! items_set.isEmpty()){
			
			Scanner scanner = new Scanner(System.in);
			
		try{
			PriceBasket pp = new PriceBasket();
			while(true){
				String s = scanner.nextLine();
				String [] str_arr = s.split("\\s");
				if(str_arr[0].equalsIgnoreCase("PriceBasket")){
					
					Basket basket = fillBasket(str_arr);
					
		            Map<Item, Integer> items_map =  basket.getItems_map();
					
					if(null != basket && ! items_map.isEmpty()){
						Checkout.proceedToCheckout(basket, priceList);
					}
					
				}else if(str_arr[0].equalsIgnoreCase("exit")){
					scanner.close();
					return;
				}else{
					System.err.println("pricebasket command is missing. please type pricebasket !!");
				}
				
			}	
		}catch(Throwable th){
			scanner.close();
			System.err.print(th.getMessage());
			th.printStackTrace();
		}
	}	
  }
}
