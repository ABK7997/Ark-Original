package animations.orzeik;

import states.Dialogue;
import main.Game;
import main.Story;
import animations.Animation;

public class Orzeik_6 extends Animation {

	//The morning after
	public Orzeik_6() {
		x = 60 << 5;
		y = 71 << 5;
		range = 1 << 5;
		pre = true;
	}
	
	public void update() {
		if (Story.orzeik == 6) {
			animating = false;
			Game.State = Game.STATE.DIALOGUE;
			Dialogue.dialogue = thoughts;
			Story.orzeik++;
		}
		else Game.State = Game.STATE.GAME;
		animating = false;
		range = 0;
		
	}
	
	public void startAnimation() {
		animating = true;
		finished = true;
		
	}
	
	private String[] thoughts = new String[] {
		"The Next Morning...",
		"Ark: Can I be free? Does it matter?",
		"I'm leaving this trashy place. That's a decent start.",
		"It'll have to do..."
	};
	
}
