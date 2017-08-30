package items.rare;

import states.Dialogue;
import graphics.Sprite;
import graphics.SpriteSheet;
import items.Inventory;
import items.Treasure;
import items.restore.Elixir;
import main.Game;

public class ElixirTreasure extends Treasure{

	private Elixir elixir = new Elixir();
	
	public ElixirTreasure(int x, int y) {
		super(x, y);
		sprite = new Sprite(32, 2, 0, SpriteSheet.items);
	}
	
	public void checkInventory() {
		for (int i = 0; i < Inventory.items.size(); i++) {
			if (Inventory.items.get(i).getClass() == elixir.getClass()) {
				inIn = true;
				Inventory.itemsFound[elixir.getIndex()] = true;
			}
		} 
	}
	
	public void effect() {
		inIn = false;
		Inventory.items.add(new Elixir());
		Game.State = Game.STATE.DIALOGUE;
		Dialogue.dialogue = info;
	}
	
	private String[] info = new String[] {
		"Found Elixir. The Elixir is now available in the store.",
		"Elixirs cure all HP and MP"
	};
	
}
