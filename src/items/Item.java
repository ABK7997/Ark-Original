package items;

import characters.Playable;

public class Item {

	protected String name, type;
	protected int effect;
	protected int stock = 1;
	protected int cost, index;
	protected String description;
	
	public void addItem() {
		stock++;
	}
	
	public void useItem() {
		stock--;
	}
	
	public void setStock(int num) {
		stock = num;
	}
	
	public String getName() {
		return name;
	}
	
	public int getStock() {
		return stock;
	}
	
	public String getType() {
		return type;
	}
	
	public int getEffect() {
		return effect;
	}
	
	public void useEffect(Playable p) {
	}
	
	public int getCost() {
		return cost;
	}
	
	public int getSell() {
		return cost / 3;
	}
	
	public String getDescription() {
		return description;
	}
	
	public int getIndex() {
		return index;
	}
}
