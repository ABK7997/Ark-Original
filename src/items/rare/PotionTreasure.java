package items.rare;

import states.Dialogue;
import graphics.Sprite;
import graphics.SpriteSheet;
import items.Inventory;
import items.Treasure;
import items.restore.Potion;
import main.Game;

public class PotionTreasure extends Treasure{

	private Potion potion = new Potion();
	
	public PotionTreasure(int x, int y) {
		super(x, y);
		sprite = new Sprite(32, 0, 0, SpriteSheet.items);
	}
	
	public void checkInventory() {
		for (int i = 0; i < Inventory.items.size(); i++) {
			if (Inventory.items.get(i).getClass() == potion.getClass()) {
				inIn = true;
				Inventory.itemsFound[potion.getIndex()] = true;
			}
		} 
	}
	
	public void effect() {
		inIn = false;
		Inventory.items.add(new Potion());
		Game.State = Game.STATE.DIALOGUE;
		Dialogue.dialogue = info;
	}
	
	private String[] info = new String[] {
		"Found Potion. The Potion is now available at the Store.",
		"Potions restore HP by 250 points."
	};
	
}
