package test;
import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.HashSet;

import junit.framework.Assert;

import org.junit.Test;

import pricebasket.PriceBasket;
import domain.Basket;
import domain.Item;
import domain.PriceList;
import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.hasItems;
//import static org.hamcrest.Matchers.containsInAnyOrder;


public class PriceBasketTests {


	
	
	@SuppressWarnings("deprecation")
	@Test
	public void testPopulatePriceList(){
		 	
            String fileLocStr = "c:\\mystuff\\items.csv";
        	
        	HashSet<Item> items_set = new HashSet<Item>();
        	Item milk_item = new Item("milk");
        	Item bread_item = new Item("bread");
        	Item soup_item = new Item("soup");
        	Item apples_item = new Item("apples");
        	
        	items_set.add(milk_item);
        	items_set.add(bread_item);
        	items_set.add(soup_item);
        	items_set.add(apples_item);
        	
        	PriceList priceList = new PriceList(items_set);
        	
        	
        	assertEquals(priceList, PriceBasket.populatePriceList(fileLocStr));
	}
	
	
	

	@SuppressWarnings("deprecation")
	@Test
	public void testFillBasket(){
		 	
        	String [] str_arr = new String[3];
        	str_arr[0] = "milk";
        	str_arr[1] = "milk";
        	str_arr[2] = "bread";
        	
        	HashMap<Item,Integer> items_map = new HashMap<Item,Integer>();
        	Item milk1 = new Item("milk");
        	Item bread1 = new Item("bread");
        	
        	items_map.put(milk1, 2);
        	items_map.put(bread1, 1);

        	
        	Basket basket = new Basket(items_map);
        	
        	assertEquals(basket, PriceBasket.fillBasket(str_arr));
	}
	

}
