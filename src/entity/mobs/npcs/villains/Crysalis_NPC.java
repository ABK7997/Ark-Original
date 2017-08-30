package entity.mobs.npcs.villains;

import entity.mobs.npcs.NPC;
import graphics.Sprite;
import graphics.SpriteSheet;

public class Crysalis_NPC extends NPC {

	public Crysalis_NPC(int x, int y) {
		this.x = x << 5;
		this.y = y << 5;
		name = "Crysalis";
		c = 'i';
		
		down = new Sprite(96, 0, 0, SpriteSheet.bosses_2);
		down_1 = down;
		down_2 = down;
		
		left = new Sprite(96, 1, 0, SpriteSheet.bosses_2);
		left_1 = left;
		left_2 = left;
		
		right = left;
		right_1 = left;
		right_2 = left;
		
		up = down;
		up_1 = down;
		up_2 = down;
		
		sprite = down;
	}
}
