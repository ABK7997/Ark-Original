package items;

import items.restore.*;
import items.status.Antidote;
import items.status.Polish;
import items.status.Steroid;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import characters.Playable;

public class Inventory {

	public static ArrayList<Item> items = new ArrayList<Item>();
	public static boolean[] itemsFound = new boolean[25];
	public static int[] itemStocks = new int[25];
	
	public Inventory() {
		for (int i = 0; i < itemsFound.length; i++) {
			itemsFound[i] = false;
		}
		
		items.add(new LifeGem());
		itemsFound[20] = true;
	}
	
	public void update() {
	}
	
	public void render(Graphics g) {
		g.setColor(Color.BLACK);
	}
	
	public static void restoreAll(Item it, Playable p) {
		p.setHP(9999);
		if (p.getMaxMP() > 0) p.setMP(9999);
		else p.setEP(99);
		it.useItem();
	}
	
	public static void useItem(Item it, Playable p) {
		it.useEffect(p);
	}
	
	public ArrayList<Item> getItems() {
		return items;
	}
	
	public static void restoreItems() {
		items = new ArrayList<Item>();
		
		for (int i = 0; i < itemsFound.length; i++) {
			if (itemsFound[i] == false) continue;
			switch(i) {
			case 0: items.add(new Potion()); break;
			case 1: items.add(new SuperPotion()); break;
			case 4: items.add(new Herb()); break;
			case 8: items.add(new Battery()); break;
			
			case 11: items.add(new Antidote()); break;
			case 13: items.add(new Polish()); break;
			case 14: items.add(new Steroid()); break;
			
			case 17: items.add(new Elixir()); break;
			
			case 20: items.add(new LifeGem()); break;
			}
			items.get(items.size()-1).setStock(itemStocks[items.get(items.size()-1).getIndex()]);
		}
	}
	
	public static void saveStock() {
		for (int i = 0; i < items.size(); i++) {
			itemStocks[items.get(i).getIndex()] = items.get(i).getStock();
		}
	}
}
