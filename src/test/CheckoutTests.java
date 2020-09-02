package test;
import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.HashSet;

import org.junit.Test;

import pricebasket.Checkout;
import domain.Item;


public class CheckoutTests {


	
	@SuppressWarnings("deprecation")
	@Test
	public void testCalculateSubtotal(){
		
        	HashMap<Item,Integer> items_map = new HashMap<Item,Integer>();
        	Item milk_item1 = new Item("milk");
        	Item bread_item1 = new Item("bread");
        	Item apples_item1 = new Item("apples");
        	Item unknown_item1 = new Item("unknown1");
        	Item unknown_item2 = new Item("unknown2");
        
        	
        	items_map.put(milk_item1,1);
        	items_map.put(apples_item1,1);
        	items_map.put(bread_item1,1);
        	items_map.put(unknown_item1,1);
        	items_map.put(unknown_item2,1);
       
        	
        	HashSet<Item> items_set = new HashSet<Item>();
        	
        	Item milk_item2 = new Item("milk", 1.30);
        	Item bread_item2 = new Item("bread", 0.80);
        	Item apples_item2 = new Item("apples",1.00);
        	Item soup_item2 = new Item("soup",0.65);
        	
        	items_set.add(milk_item2);
        	items_set.add(apples_item2);
        	items_set.add(bread_item2);
        	items_set.add(soup_item2);
        	
        	
        	assertEquals(new Double(3.10), Checkout.calculateSubtotal(items_set, items_map));   
	}

	
	
	
	
	@SuppressWarnings("deprecation")
	@Test
	public void testapplySoupOffer(){
		
        	HashMap<Item,Integer> items_map = new HashMap<Item,Integer>();
        	
        	Item milk_item1 = new Item("milk", 1.30);
        	Item bread_item1 = new Item("bread", 0.80);
        	Item soup_item1 = new Item("soup", 0.65);
        	
        	
        	items_map.put(milk_item1,1);
        	items_map.put(bread_item1,2);
        	items_map.put(soup_item1,5);
        	
        	assertEquals(new Double(0.80), Checkout.applySoupOffer(items_map));   
	}
	
	
	
	@SuppressWarnings("deprecation")
	@Test
	public void testapplyApplesOffer(){
		
        	HashMap<Item,Integer> items_map = new HashMap<Item,Integer>();
        	
        	Item apples_item1 = new Item("apples", 1.00);
        	Item soup_item1 = new Item("soup", 0.65);
        	
        	
        	items_map.put(apples_item1,2);
        	items_map.put(soup_item1,1);
        	
        	assertEquals(new Double(0.20), Checkout.applyApplesOffer(items_map));   
	}
	
}
