package items.rare;

import states.Dialogue;
import graphics.Sprite;
import graphics.SpriteSheet;
import items.Inventory;
import items.Treasure;
import items.status.Antidote;
import main.Game;

public class AntidoteTreasure extends Treasure{

	private Antidote antidote = new Antidote();
	
	public AntidoteTreasure(int x, int y) {
		super(x, y);
		sprite = new Sprite(32, 1, 0, SpriteSheet.items);
	}
	
	public void checkInventory() {
		for (int i = 0; i < Inventory.items.size(); i++) {
			if (Inventory.items.get(i).getClass() == antidote.getClass()) {
				inIn = true;
				Inventory.itemsFound[antidote.getIndex()] = true;
			}
		} 
	}
	
	public void effect() {
		inIn = false;
		Inventory.items.add(new Antidote());
		Game.State = Game.STATE.DIALOGUE;
		Dialogue.dialogue = info;
	}
	
	private String[] info = new String[] {
		"Found Antidote. Antidotes are now availabe in stores.",
		"Antidotes heal organics, mutants, hybrids, and cyborgs of poison."
	};
	
}
