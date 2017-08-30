package items.rare;

import states.Dialogue;
import graphics.Sprite;
import graphics.SpriteSheet;
import items.Inventory;
import items.Treasure;
import items.restore.SuperPotion;
import main.Game;

public class SuperPotionTreasure extends Treasure{

	private SuperPotion potion = new SuperPotion();
	
	public SuperPotionTreasure(int x, int y) {
		super(x, y);
		sprite = new Sprite(32, 2, 0, SpriteSheet.items);
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
		Inventory.items.add(new SuperPotion());
		Game.State = Game.STATE.DIALOGUE;
		Dialogue.dialogue = info;
	}
	
	private String[] info = new String[] {
		"Found Super-Potion. The Super-Potion is now available at the Store.",
		"Super-Potions restore HP by 1000 points."
	};
	
}
