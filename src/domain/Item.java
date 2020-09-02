package domain;

/**
 * @author Ahmed Salem
 *
 */

public class Item {

	private String name;
	private double price;

	
	public Item(){}
	
	
	public Item(String name){
		this.name = name;
	}
	
	
	public Item(String name, double price){
		this.name = name;
		this.price = price;
	}

	
	public String getName(){
		return this.name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	
	public double getPrice(){
		return this.price;
	}
	
	
	public void setPrice(double price){
		this.price = price;
	}
	
	
	public boolean equals(Object o){
		Item item = (Item)o;
		if(item.getName().equalsIgnoreCase(this.getName())){
			return true;
		}else{
			return false;
		}
	}
	
	public int hashCode(){
		return name.length();
	}
}
