package domain;
import java.util.HashMap;
import java.util.Map;


/**
 * @author Ahmed Salem
 *
 *  represents a basket of purchased items.  
 */


public class Basket {

    // maps the purchased item object to the count of occurrence in the command line  
	private Map<Item,Integer> items_map = null;

	
	public Basket(){}
	
	
	public Basket(HashMap<Item, Integer> items_map){
		   this.items_map = items_map;	
		}
		
	
	 public Map<Item, Integer> getItems_map(){
		if(null != items_map)
		  return items_map;
		else
			return new HashMap<Item,Integer>();
   	}
	
	
	 public void setItems_map(HashMap<Item, Integer> items_map){
		this.items_map = items_map;
   	}
	 
	 
	public boolean equals(Object o){
			Basket basket = (Basket)o;
			
			if(basket.getItems_map().equals(this.getItems_map())){
				return true;
			}else{
				return false;
			}
		}
		
		public int hashCode(){
			if(null != items_map)
			  return items_map.size();
			else
				return 0;
		} 
	
}
