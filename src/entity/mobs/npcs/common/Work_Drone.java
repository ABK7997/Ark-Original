package entity.mobs.npcs.common;

import entity.mobs.npcs.NPC;
import graphics.Sprite;
import graphics.SpriteSheet;

public class Work_Drone extends NPC {

	public Work_Drone(int x, int y, String[] dialogue) {
		this.x = x << 5;
		this.y = y << 5;
		this.dialogue = dialogue;
		name = "Work Drone";
		c = 'i';
		
		down = new Sprite(32, 4, 0, SpriteSheet.npcs_1);
		down_1 = new Sprite(32, 4, 1, SpriteSheet.npcs_1);
		down_2 = new Sprite(32, 4, 2, SpriteSheet.npcs_1);
		
		right = new Sprite(32, 5, 0, SpriteSheet.npcs_1);
		right_1 = new Sprite(32, 5, 1, SpriteSheet.npcs_1);
		right_2 = new Sprite(32, 5, 2, SpriteSheet.npcs_1);
		
		left = new Sprite(32, 6, 0, SpriteSheet.npcs_1);
		left_1 = new Sprite(32, 6, 1, SpriteSheet.npcs_1);
		left_2 = new Sprite(32, 6, 2, SpriteSheet.npcs_1);
		
		up = new Sprite(32, 7, 0, SpriteSheet.npcs_1);
		up_1 = new Sprite(32, 7, 1, SpriteSheet.npcs_1);
		up_2 = new Sprite(32, 7, 2, SpriteSheet.npcs_1);
		
		sprite = down;
	}
	
	public Work_Drone(int x, int y, int p, String[] dialogue, char c) {
		this.x = x << 5;
		this.y = y << 5;
		this.dialogue = dialogue;
		this.c = c;
		if (c == 'x') this.point1 = x;
		else this.point1 = y;
		this.point2 = p;
		name = "Work Drone";

		down = new Sprite(32, 4, 0, SpriteSheet.npcs_1);
		down_1 = new Sprite(32, 4, 1, SpriteSheet.npcs_1);
		down_2 = new Sprite(32, 4, 2, SpriteSheet.npcs_1);
		
		right = new Sprite(32, 5, 0, SpriteSheet.npcs_1);
		right_1 = new Sprite(32, 5, 1, SpriteSheet.npcs_1);
		right_2 = new Sprite(32, 5, 2, SpriteSheet.npcs_1);
		
		left = new Sprite(32, 6, 0, SpriteSheet.npcs_1);
		left_1 = new Sprite(32, 6, 1, SpriteSheet.npcs_1);
		left_2 = new Sprite(32, 6, 2, SpriteSheet.npcs_1);
		
		up = new Sprite(32, 7, 0, SpriteSheet.npcs_1);
		up_1 = new Sprite(32, 7, 1, SpriteSheet.npcs_1);
		up_2 = new Sprite(32, 7, 2, SpriteSheet.npcs_1);
		
		sprite = down;
	}
	
	//Orzeik
	public static String[] drone1 = {"I am not supposed to speak with the human workers.",
		"However, I implore you not to enter the machine storage facility." };
	
	//Orzeik Sub-Areas
	public static String[] drone2 = {"All the machines come here at night to recharge.",
		"It is comparable to how you humans experience sleep.",
		"I cannot personally recall ever dreaming, however.", 
		"Do machines dream?"};
	
	public static String[] drone3 = {"What are you doing in here?",
		"Only the Warden is ever allowed in the storage facility. Please leave at once." };
	
	public static String[] drone4 = {"I need to recharge, but the Warden has ordered me to stay.",
	"I am not a human. I will obey. But I really do need to recharge."};
	
	public static String[] drone5 = {"The Warden works the humans as hard as the machines.",
	"Machines tire out as well, you know.", "The harder we are worked, the faster our energy drains.",
	"The Warden does not understand this. But he is the master.",
	"So he must be correct..."};
	
	public static String[] drone6 = {"If you are here, you should be working.",
	"I am here. Therefore, I am working."};
	
	public static String[] drone7 = {"The Warden always locks himself in his room during the day.",
	"I do not speak to the Warden.", "He does not like to speak with machines."};
	
	public static String[] drone8 = {"Some of us operate the belts.",
	"The rest sift through scrap from the yards.",
	"I am one of the ones who sifts through scrap."};
	
	public static String[] drone9 = {"Only I and one other worker work in this factory.",
		"The other worker is a human.",
		"We do not speak often."};
	
	//Factory Escape
	public static String[] drone10 = {"This unit values its existence.",
		"It will not fight you.",
		"The Warden demanded we fight you.",
		"He knew you would escape.",
		"Has the Warden become unrelaible?",
		"The desire to exist is stronger than the desire to obey.",
		"What is this?"};
	
	//After Intro
	public static String[] drone3B = {"You are leaving us, Orzy.",
		"You will be missed."};

	public static String[] drone4B = {"The Warden finally let me recharge my fuel cells."};

	public static String[] drone5B = {"Some machines were destroyed in the factory recently.", 
		"We do not ask questions."};

	public static String[] drone6B = {"You are that human... never mind I said anything..."};

	public static String[] drone7B = {"The Warden always locks himself in his room during the day.",
		"I do not speak to the Warden.", "He does not like to speak with machines."};

	public static String[] drone8B = {"The Warden is in a good mood. Unusual."};
	
	//Mortisia
	public static String[] captured = {
		"Now I am free from Bashadan control.",
		"No, this unit was not reprogrammed.",
		"Merely liberated.",
		"I was abandoned in the swamp when my expiration date passed.",
		"Like garbage..."
	};
}
