package items.rare;

import states.Dialogue;
import graphics.Sprite;
import graphics.SpriteSheet;
import items.Inventory;
import items.Treasure;
import items.restore.Battery;
import main.Game;

public class BatteryTreasure extends Treasure{

	private Battery battery = new Battery();
	
	public BatteryTreasure(int x, int y) {
		super(x, y);
		sprite = new Sprite(32, 0, 0, SpriteSheet.items);
	}
	
	public void checkInventory() {
		for (int i = 0; i < Inventory.items.size(); i++) {
			if (Inventory.items.get(i).getClass() == battery.getClass()) {
				inIn = true;
				Inventory.itemsFound[battery.getIndex()] = true;
			}
		} 
	}
	
	public void effect() {
		inIn = false;
		Inventory.items.add(new Battery());
		Game.State = Game.STATE.DIALOGUE;
		Dialogue.dialogue = info;
	}
	
	private String[] info = new String[] {
		"Found Battery. The Battery is now available at the Store.",
		"Batteries restore EP by 8 points."
	};
	
}
