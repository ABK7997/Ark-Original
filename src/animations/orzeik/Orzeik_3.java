package animations.orzeik;

import input.Player;
import main.Game;
import main.SaveState;
import main.Story;
import states.Dialogue;
import animations.Animation;

public class Orzeik_3 extends Animation {

	//TRANSITION
	
	public Orzeik_3() {
		x = 60 << 5;
		y = 71 << 5;
		range = 1 << 5;
		if (Story.orzeik == 3) pre = true;
	}
	
	public void update() {
		if (Story.orzeik == 3) {
		
			anim ++;
		
			if (anim == 5) {
				Game.State = Game.STATE.DIALOGUE;
				Dialogue.dialogue = thoughts;
			}
		
			if (anim > 30) {
				animating = false;
				Story.mainStory = 1;
				Player.x = 56 * 32;
				Player.y = 64 * 32;
				SaveState.loadLevel();
				Game.State = Game.STATE.GAME;
				Story.orzeik++;
			}
		
		}
		
		else {
			animating = false;
			Game.State = Game.STATE.GAME;
		}
	}
	
	public void startAnimation() {
		animating = true;
		finished = true;
	}
	
	private String[] thoughts = new String[] {
		"What was that all about?",
		"Good thing I know how to swing a sword.",
		"Seems a little outdated these days.",
		"I don't like what the Warden said.",
		"Has he got plans for me?",
		"*Ark goes to bed*"
	};
	
}
