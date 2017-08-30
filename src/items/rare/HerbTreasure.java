package items.rare;

import graphics.Sprite;
import graphics.SpriteSheet;
import items.Inventory;
import items.Treasure;
import items.restore.Herb;
import main.Game;
import states.Dialogue;

public class HerbTreasure extends Treasure{

	private Herb herb = new Herb();
	
	public HerbTreasure(int x, int y) {
		super(x, y);
		sprite = new Sprite(32, 2, 0, SpriteSheet.items);
	}
	
	public void checkInventory() {
		for (int i = 0; i < Inventory.items.size(); i++) {
			if (Inventory.items.get(i).getClass() == herb.getClass()) {
				inIn = true;
				Inventory.itemsFound[herb.getIndex()] = true;
			}
		} 
	}
	
	public void effect() {
		inIn = false;
		Inventory.items.add(new Herb());
		Game.State = Game.STATE.DIALOGUE;
		Dialogue.dialogue = info;
	}
	
	private String[] info = new String[] {
		"Found Herb. The Herb is now available at the Store.",
		"Herbs restore MP by 50 points."
	};
	
}
