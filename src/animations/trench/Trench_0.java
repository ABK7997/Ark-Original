package animations.trench;

import states.Dialogue;
import main.Game;
import main.Story;
import animations.Animation;

public class Trench_0 extends Animation {

	public Trench_0() {
		x = 46 << 5;
		y = 11 << 5;
		range = 3 << 5;
		pre = true;
	}
	
	public void update() {
		if (Story.trench == 0) {
		Game.State = Game.STATE.DIALOGUE;
		Dialogue.dialogue = introduction;
		Story.trench++;
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
		"ACT I: An Unlikely Party",
		"Ark: The Mortisian Trench...",
		"Who knows what we'll find in here.",
		"The swamp is the on the other side of the pass."
	};
	
}
