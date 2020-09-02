package domain;
import java.util.HashSet;
import java.util.Set;


/**
 * @author Ahmed Salem
 *
 *  represents a PriceList of all available items. 
 */


public class PriceList {
	
	// Set is used to maintain uniqueness and avoid duplicates
	private Set <Item> items = null;

	
	public PriceList(){}
	
	
	public PriceList(HashSet<Item> items){
	   this.items = items;	
	}
	
	
	 public Set<Item> getItems(){
		if(null != items)
		  return items;
		else
			return new HashSet<Item>();
  	}
	
	
	 public void setItems(HashSet<Item> items){
		this.items = items;
  	}
	 
	 
	 
     public boolean equals(Object o){
			PriceList priceList = (PriceList)o;
			
			if(priceList.getItems().equals(this.getItems())){
				return true;
			}else{
				return false;
			}
		}
	
     
	public int hashCode(){
			if(null != items)
			  return items.size();
			else
				return 0;
		}    
}
