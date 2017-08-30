package animations.mortisia;

import states.Dialogue;
import input.Player;
import main.Game;
import main.SaveState;
import main.Story;
import animations.Animation;
import characters.Party;
import entity.mobs.npcs.friends.Dex_NPC;
import entity.mobs.npcs.friends.Orzy_NPC;

public class Mortisia_0 extends Animation {

	//Another Day at the Factory
	public Mortisia_0() {
		x = 55 << 5;
		y = 24 << 5;
		range = 3 << 5;
		pre = true;
	}
	
	public void update() {
		if (Story.crystalCave == 0) {
			for (int i = 0; i < characters.size(); i++) {
				characters.get(i).update();
			}
			
			if (anim == 0) {
				Story.mortisia = 1;
				Player.x = 53 << 5;
				Player.y = 25 << 5;
				Player.changeSprites(Party.ark);
				characters.add(new Orzy_NPC(Player.x >> 5, Player.y >> 5, null));
				characters.add(new Dex_NPC(Player.x >> 5, (Player.y >> 5) + 1, null));
			}
			
			anim++;
			
			if (anim < 2 * block) {
				Player.xa = 1;
				characters.get(1).moveX(1, 2);
			}
			else if (anim == 2 * block) {
				characters.get(0).turn(1);
				characters.get(1).turn(3);
				Player.xa = -1;
				Game.State = Game.STATE.DIALOGUE;
				Dialogue.dialogue = dialogue1;
			}
			else if (anim < 4 * block) {
				characters.get(0).moveX(1, 2);
			}
			
			else {
				SaveState.loadLevel2();
				characters.remove(1);
				characters.remove(0);
				animating = false;
				Game.State = Game.STATE.GAME;
			}	
		}
		else Game.State = Game.STATE.GAME;
	}
	
	public void startAnimation() {
		animating = true;
		finished = true;
	}
	
	//Dialogue
	private String[] dialogue1 = new String[] {
		"Ark: What is it, Orzy?",
		"Orzy:...",
		"I have been contemplating much that I have seen.",
		"Dex: It's all the lost drones, isn't it? The expired machines.",
		"Ark: You'll end up like them one day.",
		"Dex: Unless a Mortisian were to save your hardware.",
		"Orzy: This is foolishness. Keep moving.",
		"Ark: *Sigh*",
		"If you say so, boss."
	};
	
}
