package animations.swamp;

import main.Game;
import main.Story;
import states.Dialogue;
import animations.Animation;

public class Swamp_1 extends Animation {

	//Another Day at the Factory
	public Swamp_1() {
		x = 0 << 5;
		y = 0 << 5;
		range = 200 << 5;
		pre = false;
	}
	
	public void update() {
		if (Story.swamp == 1) {
			for (int i = 0; i < characters.size(); i++) {
				characters.get(i).update();
			}
			Game.State = Game.STATE.DIALOGUE;
			Dialogue.dialogue = harvester;
			animating = false;
			Story.swamp++;
		}
		else Game.State = Game.STATE.GAME;
	}
	
	public void startAnimation() {
		animating = true;
		finished = true;
	}
	
	//Dialogue
	private String[] harvester = new String[] {
		"Ark: What's a harvester still doing here?",
		"Dex: Bashada never comes back to remove them when they get stuck here.",
		"Why would they? It's just too difficult.",
		"Ark: That thing was tough.",
		"Dex: It's nothing compared to a fully-functioning Harvester."
	};
	
}
