package entity.mobs.npcs.common;

import entity.mobs.npcs.NPC;
import graphics.Sprite;
import graphics.SpriteSheet;

public class Workman extends NPC {

	public Workman(int x, int y, String[] dialogue) {
		this.x = x << 5;
		this.y = y << 5;
		this.dialogue = dialogue;
		name = "Worker";
		c = 'i';
		
		down = new Sprite(32, 0, 0, SpriteSheet.npcs_1);
		down_1 = new Sprite(32, 0, 1, SpriteSheet.npcs_1);
		down_2 = new Sprite(32, 0, 2, SpriteSheet.npcs_1);
		
		right = new Sprite(32, 1, 0, SpriteSheet.npcs_1);
		right_1 = new Sprite(32, 1, 1, SpriteSheet.npcs_1);
		right_2 = new Sprite(32, 1, 2, SpriteSheet.npcs_1);
		
		left = new Sprite(32, 2, 0, SpriteSheet.npcs_1);
		left_1 = new Sprite(32, 2, 1, SpriteSheet.npcs_1);
		left_2 = new Sprite(32, 2, 2, SpriteSheet.npcs_1);
		
		up = new Sprite(32, 3, 0, SpriteSheet.npcs_1);
		up_1 = new Sprite(32, 3, 1, SpriteSheet.npcs_1);
		up_2 = new Sprite(32, 3, 2, SpriteSheet.npcs_1);
		
		sprite = down;
	}
	
	public Workman(int x, int y, int p, String[] dialogue, char c) {
		this.x = x << 5;
		this.y = y << 5;
		this.dialogue = dialogue;
		this.c = c;
		if (c == 'x') this.point1 = x;
		else this.point1 = y;
		this.point2 = p;
		name = "Worker";
		
		down = new Sprite(32, 0, 0, SpriteSheet.npcs_1);
		down_1 = new Sprite(32, 0, 1, SpriteSheet.npcs_1);
		down_2 = new Sprite(32, 0, 2, SpriteSheet.npcs_1);
		
		right = new Sprite(32, 1, 0, SpriteSheet.npcs_1);
		right_1 = new Sprite(32, 1, 1, SpriteSheet.npcs_1);
		right_2 = new Sprite(32, 1, 2, SpriteSheet.npcs_1);
		
		left = new Sprite(32, 2, 0, SpriteSheet.npcs_1);
		left_1 = new Sprite(32, 2, 1, SpriteSheet.npcs_1);
		left_2 = new Sprite(32, 2, 2, SpriteSheet.npcs_1);
		
		up = new Sprite(32, 3, 0, SpriteSheet.npcs_1);
		up_1 = new Sprite(32, 3, 1, SpriteSheet.npcs_1);
		up_2 = new Sprite(32, 3, 2, SpriteSheet.npcs_1);
		
		sprite = down;
	}
	
	//Orzeik
	public static String[] workman1 = {"Another day at the factory.",
		"I hear they're considering another paycut.",
		"I don't think my family can survive that."};
		
	public static String[] workman2 = {"I hope the Warden's not in there.",
		"He was chewing some poor guy out last night for not working fast enough.",
		"Stupid Bashadan overseers."};
		
	public static String[] workman3 = {"The clock tower runs your life around here.",
		"When you hear the bell ring, you'd better be at work already.",
		"And you don't go home until it rings again..."};
		
	public static String[] workman4 = {"Those machines in there creep me out.",
		"I know they're just used for labor, but the size of those claws!",
		"I don't trust machines. Never have. Never will.",
		"Don't give me that look."};
	
	public static String[] workman8 = {"This is the Orzeik store.",
		"Talk to the man inside if you want to shop or sell."};
		
	//Orzeik Sub Areas
	public static String[] workman5 = {"Is it time to work again already?",
		"I think I only got four hours of sleep.",
		"I was up all night being pushed around by the Warden."};
		
	public static String[] workman6 = {"I don't want to work today.",
		"Maybe I'll just stay here.",
		"Sigh...I know I'll end up going in anyway... I need the money."};

	public static String[] workman7 = {"I'm hiding from the Warden.",
		"I made him mad, you see.",
		"I hear he sets the cyborg guards after bad workers..."};
	
	//Orzeik Main Area AFTER INTRO
	public static String[] workman1B = {"Word has it that you're leaving, Ark.",
		"Where're you going?"};
		
	public static String[] workman2B = {"Why are you walking around with that machine?",
		"Don't you know who it is?",
		"Just... just keep it away from me."};
		
	public static String[] workman3B = {"I suppose the clock won't be running your life anymore, eh?"};
		
	public static String[] workman4B = {"Hey! That's the machine master you're walking with!",
		"What do you think you're doing?"};
	
	public static String[] workman8B = {"You might want to stock up while in town.", 
		"You never know when you'll be able to find another store."};
	
	//Orzeik Sub Areas AFTER INTRO
	public static String[] workman5B = {"Finally, all those rats are gone."};
		
	public static String[] workman6B = {"Hey, listen for a moment...", 
		"Can you hear anything from behind this wall?", 
		"What's the deal with that anyway?"};

	public static String[] workman7B = {"I thought I would be in trouble with the Warden...", 
		"But... now he's in such a good mood all of a sudden.",
		"What happened?"};
}
