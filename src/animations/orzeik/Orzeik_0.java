package animations.orzeik;

import states.Dialogue;
import main.Game;
import main.Story;
import animations.Animation;

public class Orzeik_0 extends Animation {

	public Orzeik_0() {
		x = 60 << 5;
		y = 71 << 5;
		range = 1 << 5;
		pre = true;
	}
	
	public void update() {
		if (Story.orzeik == 0) {
		Game.State = Game.STATE.DIALOGUE;
		Dialogue.dialogue = introduction;
		Story.orzeik++;
		}
		else {
			range = 0;
			Game.State = Game.STATE.GAME;
		}
		animating = false;
		
	}
	
	public void startAnimation() {
		animating = true;
		finished = true;
	}
	
	private String[] introduction = new String[] {
		"INTRODUCTION: A Man of Great Promise",
		"My name is Ark.",
		"I work at a factory in this town, Orzeik.",
		"We make weapons forged from the magic found in Agickan blood.",
		"Of course it's repulsive. But hey, we just leave here.",
		"The warden here is a hard man. Most Bashadans are.",
		"I've been told he wants to see me today.",
		"What could I have possibly done wrong?"
	};
	
}
