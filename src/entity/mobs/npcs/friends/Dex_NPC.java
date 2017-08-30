package entity.mobs.npcs.friends;

import entity.mobs.npcs.NPC;
import graphics.Sprite;
import graphics.SpriteSheet;

public class Dex_NPC extends NPC {

	public Dex_NPC(int x, int y, String[] dialogue) {
		this.x = x << 5;
		this.y = y << 5;
		this.dialogue = dialogue;
		name = "Dex";
		c = 'i';
		
		down = new Sprite(32, 0, 3, SpriteSheet.playable);
		down_1 = new Sprite(32, 0, 4, SpriteSheet.playable);
		down_2 = new Sprite(32, 0, 5, SpriteSheet.playable);
		right = new Sprite(32, 1, 3, SpriteSheet.playable);
		right_1 = new Sprite(32, 1, 4, SpriteSheet.playable);
		right_2 = new Sprite(32, 1, 5, SpriteSheet.playable);
		left = new Sprite(32, 2, 3, SpriteSheet.playable);
		left_1 = new Sprite(32, 2, 4, SpriteSheet.playable);
		left_2 = new Sprite(32, 2, 5, SpriteSheet.playable);
		up = new Sprite(32, 3, 3, SpriteSheet.playable);
		up_1 = new Sprite(32, 3, 4, SpriteSheet.playable);
		up_2 = new Sprite(32, 3, 5, SpriteSheet.playable);
	
		ill = new Sprite(32, 4, 3, SpriteSheet.playable);
		attack_1 = new Sprite(32, 4, 4, SpriteSheet.playable);
		magic_1 = new Sprite(32, 4, 5, SpriteSheet.playable);
		attack_2 = new Sprite(32, 5, 4, SpriteSheet.playable);
		magic_2 = new Sprite(32, 5, 5, SpriteSheet.playable);
	
		hit = new Sprite(32, 6, 3, SpriteSheet.playable);
		flee = new Sprite(32, 6, 4, SpriteSheet.playable);
		dead = new Sprite(32, 6, 5, SpriteSheet.playable);
		
		defend = new Sprite(32, 7, 3, SpriteSheet.playable);
		item = new Sprite(32, 7, 4, SpriteSheet.playable);
		skill = new Sprite(32, 7, 5, SpriteSheet.playable);
		
		sprite = down;
	}
	
	public Dex_NPC(int x, int y, int p, String[] dialogue, char c) {
		this.x = x << 5;
		this.y = y << 5;
		this.dialogue = dialogue;
		this.c = c;
		if (c == 'x') this.point1 = x;
		else this.point1 = y;
		this.point2 = p;
		name = "Orzy";
		
		down = new Sprite(32, 0, 3, SpriteSheet.playable);
		down_1 = new Sprite(32, 0, 4, SpriteSheet.playable);
		down_2 = new Sprite(32, 0, 5, SpriteSheet.playable);
		right = new Sprite(32, 1, 3, SpriteSheet.playable);
		right_1 = new Sprite(32, 1, 4, SpriteSheet.playable);
		right_2 = new Sprite(32, 1, 5, SpriteSheet.playable);
		left = new Sprite(32, 2, 3, SpriteSheet.playable);
		left_1 = new Sprite(32, 2, 4, SpriteSheet.playable);
		left_2 = new Sprite(32, 2, 5, SpriteSheet.playable);
		up = new Sprite(32, 3, 3, SpriteSheet.playable);
		up_1 = new Sprite(32, 3, 4, SpriteSheet.playable);
		up_2 = new Sprite(32, 3, 5, SpriteSheet.playable);
	
		ill = new Sprite(32, 4, 3, SpriteSheet.playable);
		attack_1 = new Sprite(32, 4, 4, SpriteSheet.playable);
		magic_1 = new Sprite(32, 4, 5, SpriteSheet.playable);
		attack_2 = new Sprite(32, 5, 4, SpriteSheet.playable);
		magic_2 = new Sprite(32, 5, 5, SpriteSheet.playable);
	
		hit = new Sprite(32, 6, 3, SpriteSheet.playable);
		flee = new Sprite(32, 6, 4, SpriteSheet.playable);
		dead = new Sprite(32, 6, 5, SpriteSheet.playable);
		
		defend = new Sprite(32, 7, 3, SpriteSheet.playable);
		item = new Sprite(32, 7, 4, SpriteSheet.playable);
		skill = new Sprite(32, 7, 5, SpriteSheet.playable);
		
		sprite = down;
	}
	
	//Party Camp
	public static String[] camp1 = new String[] {
		"Ark: Why do you care so much about harvesting crystals anyway?",
		"It's dangerous and illegal. Is the power really worth it?",
		"Dex: I don't know.",
		"Ark: You... don't know...",
		"Dex: Nobody does. The Mortisians never tried harvesting.",
		"I want to see if we can gain anything.",
		"Ark: Wasn't that the Bashadans' mistakes?",
		"Dex: If it will help us fight them, I'm willing to fight dirty.",
		"Ark: Be careful where that road takes you."
	};
}
