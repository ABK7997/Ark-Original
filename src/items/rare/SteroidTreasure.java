package items.rare;

import states.Dialogue;
import graphics.Sprite;
import graphics.SpriteSheet;
import items.Inventory;
import items.Treasure;
import items.status.Steroid;
import main.Game;

public class SteroidTreasure extends Treasure{

	private Steroid steroid = new Steroid();
	
	public SteroidTreasure(int x, int y) {
		super(x, y);
		sprite = new Sprite(32, 2, 0, SpriteSheet.items);
	}
	
	public void checkInventory() {
		for (int i = 0; i < Inventory.items.size(); i++) {
			if (Inventory.items.get(i).getClass() == steroid.getClass()) {
				inIn = true;
				Inventory.itemsFound[steroid.getIndex()] = true;
			}
		} 
	}
	
	public void effect() {
		inIn = false;
		Inventory.items.add(new Steroid());
		Game.State = Game.STATE.DIALOGUE;
		Dialogue.dialogue = info;
	}
	
	private String[] info = new String[] {
		"Found Steroid. The Steroid is now available at the Store.",
		"Steroids cure Paralysis."
	};
	
}
