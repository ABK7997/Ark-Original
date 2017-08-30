package entity.mobs.npcs.common;

import entity.mobs.npcs.NPC;
import graphics.Sprite;
import graphics.SpriteSheet;

public class Old_Drone extends NPC {

	public Old_Drone(int x, int y, String[] dialogue) {
		this.x = x << 5;
		this.y = y << 5;
		this.dialogue = dialogue;
		name = "Old Drone";
		c = 'i';
		
		down = new Sprite(32, 8, 0, SpriteSheet.machines_1);
		down_1 = new Sprite(32, 8, 1, SpriteSheet.machines_1);
		down_2 = new Sprite(32, 8, 2, SpriteSheet.machines_1);
		
		right = new Sprite(32, 9, 0, SpriteSheet.machines_1);
		right_1 = new Sprite(32, 9, 1, SpriteSheet.machines_1);
		right_2 = new Sprite(32, 9, 2, SpriteSheet.machines_1);
		
		left = new Sprite(32, 10, 0, SpriteSheet.machines_1);
		left_1 = new Sprite(32, 10, 1, SpriteSheet.machines_1);
		left_2 = new Sprite(32, 10, 2, SpriteSheet.machines_1);
		
		up = new Sprite(32, 11, 0, SpriteSheet.machines_1);
		up_1 = new Sprite(32, 11, 1, SpriteSheet.machines_1);
		up_2 = new Sprite(32, 11, 2, SpriteSheet.machines_1);
		
		sprite = down;
	}
	
	public Old_Drone(int x, int y, int p, String[] dialogue, char c) {
		this.x = x << 5;
		this.y = y << 5;
		this.dialogue = dialogue;
		this.c = c;
		if (c == 'x') this.point1 = x;
		else this.point1 = y;
		this.point2 = p;
		name = "Work Drone";

		down = new Sprite(32, 8, 0, SpriteSheet.machines_1);
		down_1 = new Sprite(32, 8, 1, SpriteSheet.machines_1);
		down_2 = new Sprite(32, 8, 2, SpriteSheet.machines_1);
		
		right = new Sprite(32, 9, 0, SpriteSheet.machines_1);
		right_1 = new Sprite(32, 9, 1, SpriteSheet.machines_1);
		right_2 = new Sprite(32, 9, 2, SpriteSheet.machines_1);
		
		left = new Sprite(32, 10, 0, SpriteSheet.machines_1);
		left_1 = new Sprite(32, 10, 1, SpriteSheet.machines_1);
		left_2 = new Sprite(32, 10, 2, SpriteSheet.machines_1);
		
		up = new Sprite(32, 11, 0, SpriteSheet.machines_1);
		up_1 = new Sprite(32, 11, 1, SpriteSheet.machines_1);
		up_2 = new Sprite(32, 11, 2, SpriteSheet.machines_1);
		
		sprite = down;
	}
	
	//Swamp
	public static String[] drone1 = {
		"Thhhhhhhhh-is unit is ssssssstilllll functionnnning...", 
		"Thhhh-ere is n-n-nothing I can d-do to stop the d-d-decay.",
		"Ark: So this is what happens to expired drones..."
	};
	public static String[] drone2 = {
		"Th-the oth...er drones h-have g-gone rogue.",
		"I...I...I...will soon become one, one, one of them as well.",
	};
	public static String[] drone3 = {
		"N-no drone sh-should ever be ffffforced to come here...",
		"It, it, it is surely our end."
	};
	public static String[] drone4 = {
		"I-It's...not so bad h-h-h-here...",
		"E-even my ffffunctions seem to w-work better h-here.",
		"Maybe I will c-c-cease to exist here.",
		"Th-that would not be so bad."
	};
}
