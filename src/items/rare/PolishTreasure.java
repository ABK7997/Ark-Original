package items.rare;

import states.Dialogue;
import graphics.Sprite;
import graphics.SpriteSheet;
import items.Inventory;
import items.Treasure;
import items.status.Polish;
import main.Game;

public class PolishTreasure extends Treasure{

	private Polish polish = new Polish();
	
	public PolishTreasure(int x, int y) {
		super(x, y);
		sprite = new Sprite(32, 1, 0, SpriteSheet.items);
	}
	
	public void checkInventory() {
		for (int i = 0; i < Inventory.items.size(); i++) {
			if (Inventory.items.get(i).getClass() == polish.getClass()) {
				inIn = true;
				Inventory.itemsFound[polish.getIndex()] = true;
			}
		} 
	}
	
	public void effect() {
		inIn = false;
		Inventory.items.add(new Polish());
		Game.State = Game.STATE.DIALOGUE;
		Dialogue.dialogue = info;
	}
	
	private String[] info = new String[] {
		"Found Polish. Polish is now availabe in stores.",
		"Polish fixes corrosion in machines, cygics, and cyborgs."
	};
	
}
