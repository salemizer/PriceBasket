package pricebasket;
import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import domain.Basket;
import domain.Item;
import domain.PriceList;

/**
 * @author Ahmed Salem
 *
 */	

public class Checkout {


	
 
	/** @param  basket, priceList 
	 *
	 *  initiates the checkout process, and prints total 
	 */	
  public static void proceedToCheckout(Basket basket, PriceList priceList) {

		Map<Item, Integer> items_map = basket.getItems_map();
		Set<Item> items_set = priceList.getItems();

		double subTotal = 0;
		double apples_discount = 0;
		double soup_discount = 0;

		subTotal = calculateSubtotal(items_set, items_map);

		apples_discount = applyApplesOffer(items_map);
		soup_discount = applySoupOffer(items_map);

		printTotal(subTotal, apples_discount, soup_discount);
}

	
  
	
  
  
  
  static void printTotal(double subTotal, double apples_discount,
			double soup_discount) {

		DecimalFormat df = new DecimalFormat("####0.00");

		System.out.println(" \n SubTotal: £" + df.format(subTotal));

		if (soup_discount <= 0 & apples_discount <= 0) {
			System.out.println("(no offers available)");
		} else {
			if (soup_discount > 0) {
				System.out.println("2 tins soup, bread half priced: -"
						+ soup_discount);
			}
			if (apples_discount > 0) {
				System.out.println("Apples 10% off: -" + apples_discount);
			}
		}

		System.out.println("Total: £"
				+ df.format(subTotal - soup_discount - apples_discount));
		
		System.out.println("--------------------------- \n");
	}

	

  
  
  
  
  /** 
	 *  @return subTotal Double
	 */	
   public static Double calculateSubtotal(Set<Item> items_set,
			Map<Item, Integer> items_map) {

		double subTotal = 0;
		// boolean success = false;

		try {
			Iterator iterator = items_map.entrySet().iterator();

			while (iterator.hasNext()) {

				Entry<Item, Integer> entry = (Map.Entry<Item, Integer>) iterator
						.next();
				Item entryKey = entry.getKey();
				Integer entryValue = entry.getValue();

				if (items_set.contains(entryKey)) {

					Iterator items_set_iterator = items_set.iterator();

					while (items_set_iterator.hasNext()) {

						Item item = (Item) items_set_iterator.next();

						double price = item.getPrice();

						if (entryKey.equals(item)) {

							entryKey.setPrice(price);
							subTotal += (entryValue * price);
							break;
						}
					}
				}
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		} finally {
			return subTotal;
		}
	}

	
   
   
   /**   
	 *  @return discount Double, zero if offer not applicable
	 *
	 */	
   public static Double applySoupOffer(Map<Item, Integer> items_map) {

		double discount = 0;

		Item soup_item = new Item();
		soup_item.setName("soup");

		Item bread_item = new Item();
		bread_item.setName("bread");

		if (items_map.containsKey(soup_item)) {

			int quantity = items_map.get(soup_item);
			int pair_of_soup = quantity / 2;

			if (items_map.containsKey(bread_item)) {

				Iterator iterator = items_map.entrySet().iterator();

				while (iterator.hasNext()) {
					Map.Entry<Item, Integer> entry = (Map.Entry<Item, Integer>) iterator
							.next();
					Item item = entry.getKey();

					if (bread_item.equals(item)) {

						int bread_count = entry.getValue();
						double price = item.getPrice();

						while (pair_of_soup-- > 0 && bread_count-- > 0) {
							discount += price / 2;

						}
					}
				}

			}
		}

		return discount;
	}

	
    
    
   
   /**   
	 *  @return discount Double, zero if offer not applicable
	 *
	 */	
  public static Double applyApplesOffer(Map<Item, Integer> items_map) {

		double discount = 0;

		Item apples_item = new Item();
		apples_item.setName("apples");

		if (items_map.containsKey(apples_item)) {

			Iterator iterator = items_map.entrySet().iterator();

			while (iterator.hasNext()) {
				Map.Entry<Item, Integer> entry = (Map.Entry<Item, Integer>) iterator
						.next();
				Item item = entry.getKey();
				String name = item.getName();
				if (name.equalsIgnoreCase("apples")) {
					int quantity = entry.getValue();
					double price = item.getPrice();
					double d = (price * 10) / 100;
					discount = quantity * d;

					break;
				}
			}
		}
		return discount;
	}

}
