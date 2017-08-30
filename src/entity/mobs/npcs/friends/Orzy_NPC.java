package entity.mobs.npcs.friends;

import entity.mobs.npcs.NPC;
import graphics.Sprite;
import graphics.SpriteSheet;

public class Orzy_NPC extends NPC {

	public Orzy_NPC(int x, int y, String[] dialogue) {
		this.x = x << 5;
		this.y = y << 5;
		this.dialogue = dialogue;
		name = "Orzy";
		c = 'i';
		
		down = new Sprite(32, 8, 0, SpriteSheet.playable);
		down_1 = new Sprite(32, 8, 1, SpriteSheet.playable);
		down_2 = new Sprite(32, 8, 2, SpriteSheet.playable);
		right = new Sprite(32, 9, 0, SpriteSheet.playable);
		right_1 = new Sprite(32, 9, 1, SpriteSheet.playable);
		right_2 = new Sprite(32, 9, 2, SpriteSheet.playable);
		left = new Sprite(32, 10, 0, SpriteSheet.playable);
		left_1 = new Sprite(32, 10, 1, SpriteSheet.playable);
		left_2 = new Sprite(32, 10, 2, SpriteSheet.playable);
		up = new Sprite(32, 11, 0, SpriteSheet.playable);
		up_1 = new Sprite(32, 11, 1, SpriteSheet.playable);
		up_2 = new Sprite(32, 11, 2, SpriteSheet.playable);
	
		ill = new Sprite(32, 12, 0, SpriteSheet.playable);
		attack_1 = new Sprite(32, 12, 1, SpriteSheet.playable);
		magic_1 = new Sprite(32, 12, 2, SpriteSheet.playable);
		attack_2 = new Sprite(32, 13, 1, SpriteSheet.playable);
		magic_2 = new Sprite(32, 13, 2, SpriteSheet.playable);
	
		hit = new Sprite(32, 14, 0, SpriteSheet.playable);
		flee = new Sprite(32, 14, 1, SpriteSheet.playable);
		dead = new Sprite(32, 14, 2, SpriteSheet.playable);
		
		defend = new Sprite(32, 15, 0, SpriteSheet.playable);
		item = new Sprite(32, 15, 1, SpriteSheet.playable);
		skill = new Sprite(32, 15, 2, SpriteSheet.playable);
		
		sprite = down;
	}
	
	public Orzy_NPC(int x, int y, int p, String[] dialogue, char c) {
		this.x = x << 5;
		this.y = y << 5;
		this.dialogue = dialogue;
		this.c = c;
		if (c == 'x') this.point1 = x;
		else this.point1 = y;
		this.point2 = p;
		name = "Orzy";
		
		down = new Sprite(32, 8, 0, SpriteSheet.playable);
		down_1 = new Sprite(32, 8, 1, SpriteSheet.playable);
		down_2 = new Sprite(32, 8, 2, SpriteSheet.playable);
		right = new Sprite(32, 9, 0, SpriteSheet.playable);
		right_1 = new Sprite(32, 9, 1, SpriteSheet.playable);
		right_2 = new Sprite(32, 9, 2, SpriteSheet.playable);
		left = new Sprite(32, 10, 0, SpriteSheet.playable);
		left_1 = new Sprite(32, 10, 1, SpriteSheet.playable);
		left_2 = new Sprite(32, 10, 2, SpriteSheet.playable);
		up = new Sprite(32, 11, 0, SpriteSheet.playable);
		up_1 = new Sprite(32, 11, 1, SpriteSheet.playable);
		up_2 = new Sprite(32, 11, 2, SpriteSheet.playable);
	
		ill = new Sprite(32, 12, 0, SpriteSheet.playable);
		attack_1 = new Sprite(32, 12, 1, SpriteSheet.playable);
		magic_1 = new Sprite(32, 12, 2, SpriteSheet.playable);
		attack_2 = new Sprite(32, 13, 1, SpriteSheet.playable);
		magic_2 = new Sprite(32, 13, 2, SpriteSheet.playable);
	
		hit = new Sprite(32, 14, 0, SpriteSheet.playable);
		flee = new Sprite(32, 14, 1, SpriteSheet.playable);
		dead = new Sprite(32, 14, 2, SpriteSheet.playable);
		
		defend = new Sprite(32, 15, 0, SpriteSheet.playable);
		item = new Sprite(32, 15, 1, SpriteSheet.playable);
		skill = new Sprite(32, 15, 2, SpriteSheet.playable);
		
		sprite = down;
	}
	
	//Orzeik
	public static String[] orzeik1 = {"Hello, worker.",
		"You are the one called Ark, yes?",
		"Just confirming my knowledge.", 
		"I am known as Orzy.", 
		"I am the master of all of Orzeik's machines.", 
		"You should be on your way.", 
		"There is important work to be done at the main factory."};
	
	//Party Camp
	public static String[] camp1 = {
		"Ark: We've got a golden opportunity to escape from the empire, you know.",
		"Orzy: Please stop trying to convince me to become a traitor, Ark.",
		"I simply will not do it. It is not in my design.",
		"Ark: I've met a few drones who would disagree."
	};
}
