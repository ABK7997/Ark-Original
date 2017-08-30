package entity.mobs.npcs.common;

import entity.mobs.npcs.NPC;
import graphics.Sprite;
import graphics.SpriteSheet;

public class Cyborg extends NPC {

	public Cyborg(int x, int y, String[] dialogue) {
		this.x = x << 5;
		this.y = y << 5;
		this.dialogue = dialogue;
		name = "Worker";
		c = 'i';
		
		down = new Sprite(32, 7, 0, SpriteSheet.cyborgs_1);
		down_1 = new Sprite(32, 7, 1, SpriteSheet.cyborgs_1);
		down_2 = new Sprite(32, 7, 2, SpriteSheet.cyborgs_1);
		
		right = new Sprite(32, 6, 0, SpriteSheet.cyborgs_1);
		right_1 = new Sprite(32, 6, 1, SpriteSheet.cyborgs_1);
		right_2 = new Sprite(32, 6, 2, SpriteSheet.cyborgs_1);
		
		left = new Sprite(32, 5, 0, SpriteSheet.cyborgs_1);
		left_1 = new Sprite(32, 5, 1, SpriteSheet.cyborgs_1);
		left_2 = new Sprite(32, 5, 2, SpriteSheet.cyborgs_1);
		
		up = new Sprite(32, 4, 0, SpriteSheet.cyborgs_1);
		up_1 = new Sprite(32, 4, 1, SpriteSheet.cyborgs_1);
		up_2 = new Sprite(32, 4, 2, SpriteSheet.cyborgs_1);
		
		sprite = down;
	}
	
	public Cyborg(int x, int y, int p, String[] dialogue, char c) {
		this.x = x << 5;
		this.y = y << 5;
		this.dialogue = dialogue;
		this.c = c;
		if (c == 'x') this.point1 = x;
		else this.point1 = y;
		this.point2 = p;
		name = "Worker";
		
		down = new Sprite(32, 0, 0, SpriteSheet.cyborgs_1);
		down_1 = new Sprite(32, 0, 1, SpriteSheet.cyborgs_1);
		down_2 = new Sprite(32, 0, 2, SpriteSheet.cyborgs_1);
		
		right = new Sprite(32, 1, 0, SpriteSheet.cyborgs_1);
		right_1 = new Sprite(32, 1, 1, SpriteSheet.cyborgs_1);
		right_2 = new Sprite(32, 1, 2, SpriteSheet.cyborgs_1);
		
		left = new Sprite(32, 2, 0, SpriteSheet.cyborgs_1);
		left_1 = new Sprite(32, 2, 1, SpriteSheet.cyborgs_1);
		left_2 = new Sprite(32, 2, 2, SpriteSheet.cyborgs_1);
		
		up = new Sprite(32, 3, 0, SpriteSheet.cyborgs_1);
		up_1 = new Sprite(32, 3, 1, SpriteSheet.cyborgs_1);
		up_2 = new Sprite(32, 3, 2, SpriteSheet.cyborgs_1);
		
		sprite = down;
	}
	
	//Orzeik
	public static String[] silent = {"..."};
	
}
