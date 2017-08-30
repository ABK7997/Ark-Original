package animations.world;

import states.Dialogue;
import main.Game;
import main.Story;
import animations.Animation;

public class WorldMap_0 extends Animation {

	public WorldMap_0() {
		x = 77 << 5;
		y = 89 << 5;
		range = 1 << 5;
		pre = true;
	}
	
	public void update() {
		if (Story.mainStory == 4) {
		Game.State = Game.STATE.DIALOGUE;
		Dialogue.dialogue = partyCamp;
		Story.mainStory = 5;
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
	
	private String[] partyCamp = new String[] {
		"The Party Camp is now available.",
		"Press 'z' while on the world map to visit the Party Camp."
	};
	
}
