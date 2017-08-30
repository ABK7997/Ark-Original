package animations.swamp;

import characters.Party;
import states.Dialogue;
import main.Game;
import main.Story;
import input.Player;
import entity.mobs.npcs.friends.Dex_NPC;
import animations.Animation;

public class Swamp_2 extends Animation {

	//Another Day at the Factory
	public Swamp_2() {
		x = 160 << 5;
		y = 175 << 5;
		range = 3 << 5;
		pre = true;
	}
	
	public void update() {
		if (Story.swamp < 2) {
			for (int i = 0; i < characters.size(); i++) {
				characters.get(i).update();
			}
			
			if (anim == 0) {
				Player.x = 161 << 5;
				Player.y = 173 << 5;
				Player.changeSprites(Party.ark);
				characters.add(new Dex_NPC(Player.x >> 5, Player.y >> 5, warning));
			}
			
			anim++;
			
			if (anim < 2 * block) {
				characters.get(0).moveY(1);
			}
			else if (anim == 2 * block) {
				Game.State = Game.STATE.DIALOGUE;
				Dialogue.dialogue = warning;
			}
			else if (anim < 4 * block) {
				characters.get(0).moveY(-1);
			}
			else {
				Game.State = Game.STATE.GAME;
				characters.remove(0);
				animating = false;
				Story.swamp = 2;
			}
		}
		else Game.State = Game.STATE.GAME;
	}
	
	public void startAnimation() {
		animating = true;
		finished = true;
	}
	
	//Dialogue
	private String[] warning = new String[] {
			"Dex: Um... something I forgot to mention...",
			"Ark: ...Uh-huh... And?",
			"Dex: There's a golem guarding the entrance to Mortisia.",
			"It's been there for a decade or so since the war ended.",
			"Just as a precaution against Bashadans, you know?",
			"Ark: You mean against people like us?",
			"Dex: Basically.",
			"We just need to disable it for awhile to get past it.",
			"Ark: Wonderful..."
	};
	
}
