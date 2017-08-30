package entity.mobs.npcs.common;

import entity.mobs.npcs.NPC;
import graphics.Sprite;
import graphics.SpriteSheet;

public class Mortisian extends NPC {

	public Mortisian(int x, int y, String[] dialogue) {
		this.x = x << 5;
		this.y = y << 5;
		this.dialogue = dialogue;
		name = "Worker";
		c = 'i';
		
		down = new Sprite(32, 12, 0, SpriteSheet.npcs_1);
		right = new Sprite(32, 13, 0, SpriteSheet.npcs_1);
		left = new Sprite(32, 14, 0, SpriteSheet.npcs_1);
		up = new Sprite(32, 15, 0, SpriteSheet.npcs_1);
		
		sprite = down;
	}
	
	public Mortisian(int x, int y, int p, String[] dialogue, char c) {
		this.x = x << 5;
		this.y = y << 5;
		this.dialogue = dialogue;
		this.c = c;
		if (c == 'x') this.point1 = x;
		else this.point1 = y;
		this.point2 = p;
		name = "Worker";
		
		down = new Sprite(32, 12, 0, SpriteSheet.npcs_1);
		down_1 = new Sprite(32, 12, 1, SpriteSheet.npcs_1);
		down_2 = new Sprite(32, 12, 2, SpriteSheet.npcs_1);
		
		right = new Sprite(32, 13, 0, SpriteSheet.npcs_1);
		right_1 = new Sprite(32, 13, 1, SpriteSheet.npcs_1);
		right_2 = new Sprite(32, 13, 2, SpriteSheet.npcs_1);
		
		left = new Sprite(32, 14, 0, SpriteSheet.npcs_1);
		left_1 = new Sprite(32, 14, 1, SpriteSheet.npcs_1);
		left_2 = new Sprite(32, 14, 2, SpriteSheet.npcs_1);
		
		up = new Sprite(32, 15, 0, SpriteSheet.npcs_1);
		up_1 = new Sprite(32, 15, 1, SpriteSheet.npcs_1);
		up_2 = new Sprite(32, 15, 2, SpriteSheet.npcs_1);
		
		sprite = down;
	}
	
	//Mortisia
	public static String[] mortisia1 = new String[] {
		"Have you tried the new Super Potion?",
		"The shopkeeper is giving away free samples!",
		"Made right here in Mortisia."
	};
	
	public static String[] mortisia2 = new String[] {
		"I took one sip of that new Potion and felt like a new man!"
	};
	
	public static String[] mortisia3 = new String[] {
		"Beyond to the east lies the Crystal Cave.",
		"You weren't possibly thinking about going there, were you?"
	};
	
	public static String[] mortisia4 = new String[] {
		"They're studying a captured machine in there.",
		"The jungle is full of them.",
		"I had given up hope that they could be repaired."
	};
	
	public static String[] mortisia5 = new String[] {
		"This is the Mortisian Castle.",
		"Not much, I realize, but it's ours.",
		"I doubt the king will see you."
	};
	
	public static String[] mortisia6 = new String[] {
		"Welcome to Mortisia, traveler.",
		"You made it through the swamp, so you must be Agickan like us.",
		"We welcome all who flee from Bashada."
	};
	
	public static String[] silent = new String[] {
		"..."
	};
	
	//Mortisa_Sub
	public static String[] scientist1 = new String[] {
		"They said it couldn't be done.",
		"That no machine can survive expiration.",
		"The Bashadans were wrong.",
		"I always knew how poorly they treated their workers.",
		"I had no idea they treated their machines even worse."
	};
	public static String[] scientist2 = new String[] {
		"What's the queer look on your face?",
		"We saved this drone.",
		"Agickans helping a machine, yes."
	};
	public static String[] scientist3 = new String[] {
		"The drone had run out of energy when we found it.",
		"We stole batteries from Bashada and learned how to make our own."
	};
	public static String[] scientist4 = new String[] {
		"Several drones were sacrificed to revive that one.",
		"Now we can do the same for these abandoned machines."
	};
	public static String[] inn1 = new String[] {
		"Your stay is free, travelers.",
		"Money is of little use out here except to trade with the shopkeeper."
	};
	public static String[] inn2 = new String[] {
		"I fled from Bashada seven weeks ago during a riot.",
		"The wounds I sustained haven't healed."
	};
	public static String[] castle1 = new String[] {
		"The king is not present, I'm afraid.",
		"I'm forbidden to tell you anything more."
	};
	public static String[] castle2 = new String[] {
		"Not to be rude, but you're getting dirt on the floor."
	};
}
