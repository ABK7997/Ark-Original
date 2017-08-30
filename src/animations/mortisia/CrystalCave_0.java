package animations.mortisia;

import input.Player;
import levels.Level;
import main.Game;
import main.SaveState;
import main.Story;
import states.Dialogue;
import animations.Animation;
import characters.Party;
import entity.mobs.enemies.bosses.Crysalis;
import entity.mobs.npcs.friends.Dex_NPC;
import entity.mobs.npcs.friends.Orzy_NPC;
import entity.mobs.npcs.villains.Crysalis_NPC;

public class CrystalCave_0 extends Animation {

	//Another Day at the Factory
	public CrystalCave_0() {
		x = 45 << 5;
		y = 12 << 5;
		range = 1 << 5;
		pre = true;
	}
	
	public void update() {
		if (Story.crystalCave == 0) {
			for (int i = 0; i < characters.size(); i++) {
				characters.get(i).update();
			}
			
			if (anim == 0) {
				Player.x = 45 << 5;
				Player.y = 12 << 5;
				Player.changeSprites(Party.ark);
				characters.add(new Orzy_NPC((Player.x >> 5) + 1, Player.y >> 5, null));
				characters.add(new Dex_NPC((Player.x >> 5) - 1, Player.y >> 5, null));
			}
			
			anim++;
			if (anim < 3 * block) {
				Player.ya = -1;
				characters.get(0).moveY(-1, 3);
				characters.get(1).moveY(-1, 3);
			}
			else if (anim == 3 * block) {
				Game.State = Game.STATE.DIALOGUE;
				Dialogue.dialogue = dialogue1;
			}
			else if (anim == (3 * block) + 1) {
				Game.State = Game.STATE.GAME;
				Level.add(new Crysalis((Player.x >> 5), (Player.y >> 5), 1, 1));
			}
			else if (anim == (3*block) + 3) {
				Player.ya = -1;
				characters.add(new Crysalis_NPC(44, 4));
				Game.State = Game.STATE.DIALOGUE;
				Dialogue.dialogue = dialogue2;
			}
			else if (anim < 4 * block) {
				Player.moveY(-1, 1);
			}
			else if (anim == 4 * block) {
				Game.State = Game.STATE.DIALOGUE;
				Dialogue.dialogue = dialogue3;
			}
			else if (anim < 5 * block) {
				Player.ya = 4;
				characters.get(0).moveY(4, 2);
				characters.get(1).moveY(4, 2);
			}
			else if (anim == 5 * block) {
				Game.State = Game.STATE.DIALOGUE;
			}
			else {
				Game.State = Game.STATE.START;
				animating = false;
				/* For continuation into ACT II
				characters.remove(2);
				characters.remove(1);
				characters.remove(0);
				Story.crystalCave = 1;
				animating = false;
				Game.levelName = "Mortisia_Sub";
				SaveState.loadLevel();
				Player.x = 34 << 5;
				Player.y = 33 << 5;
				*/
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
		"Dex: This is it.",
		"The highest concentration of magic in Mortisia.",
		"I don't even know if anybody's ever set foot in here before.",
		"Orzy: I detect something coming.",
		"Dex: I sense it, too.",
		"Ark: Um... really?",
		"I don't sense anything...",
		"Dex: You will in a moment.",
		"Orzy: Its readings are extremely high.",
		"Prepare yourselves!"
	};
	
	private String[] dialogue2 = new String[] {
		"Dex: D-did we kill it?",
		"Orzy: No. Like the Crystal Golem, it is merely weakened.",
		"Ark: What is it?",
		"???: This being has a name: CRYSALIS.",
		"Dex: I-it talks!?",
		"Crysalis: Yes, it speaks. Trespassers, why have you come?",
		"You have come to steal magic energy, have you not?",
		"Like the Bashadans and their machines?",
		"You even have a drone with you.",
		"Ark: um, um..."
	};
	
	private String[] dialogue3 = new String[] {
		"Ark: I confess we had ill intentions for this place.",
		"But I do not come here of my own will.",
		"It was the only way to redeem my freedom from the Bashadans.",
		"Crysalis: The Bashadans drive out many from their lands and even more flee.",
		"However, you should have disobeyed regardless.",
		"I cannot forgive this trangression.",
		"If I release you, you will try to find crystals elsewhere.",
		"The flow of magic must not be disturbed.",
		"You cannot leave.",
		"Dex: I don't like where this is going.",
		"Orzy: We should leave now.",
		"Ark: You don't have to tell me twice!"
	};
	
	private String[] cliffhanger = new String[] {
		"To Be Continued in Act II"
	};
	
}
