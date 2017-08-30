package animations.orzeik;

import input.Player;
import states.Dialogue;
import main.Game;
import main.Story;
import entity.mobs.npcs.villains.Warden;
import animations.Animation;

public class Orzeik_2 extends Animation {

	//A Job Well Done
	public Orzeik_2() {
		x = 40 << 5;
		y = 31 << 5;
		range = 1 << 5;
		pre = false;
	}
	
	public void update() {
		if (Story.orzeik == 2) {
			Game.State = Game.STATE.ANIMATING;
			
			for (int i = 0; i < characters.size(); i++) {
				characters.get(i).update();
			}
		
			if (anim == 0) {
				Player.x = 40 << 5;
				Player.y = 31 << 5;
			}
			
			anim++;
			if (anim < 6 * block) {
				characters.get(0).moveX(-2, 5);
			}
			else if (anim == 192) {
				Game.State = Game.STATE.DIALOGUE;
				Dialogue.dialogue = warden1;
			}
			else if (anim < 12 * block) {
				characters.get(0).moveX(2, 6);
			}
			else {
				Game.State = Game.STATE.GAME;
				animating = false;
				characters.remove(0);
				Story.orzeik++;
			}
		
		}
		
		else Game.State = Game.STATE.GAME;
	}
	
	public void startAnimation() {
		Game.State = Game.STATE.ANIMATING;
		anim = 0;
		characters.add(new Warden(54, 31, warden1));
		animating = true;
		finished = true;
	}
	
	//Dialogue
	private String[] warden1 = new String[] {"WARDEN: Excellent job, Ark.", 
		"You know, working in a factory like this doesn't suit you.",
		"You swing that sword very well.",
		"I can think of some uses for that.",
		"Oh, listen to me blabber!",
		"Take the rest of the day off, Ark.",
		"Just go home and rest. It'll do you good."
	};
}
