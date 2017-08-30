package animations.orzeik;

import states.Dialogue;
import main.Game;
import main.Story;
import input.Player;
import entity.mobs.npcs.villains.Warden;
import animations.Animation;

public class Orzeik_1 extends Animation {

	//Another Day at the Factory
	public Orzeik_1() {
		x = 48 << 5;
		y = 33 << 5;
		range = 2 << 5;
		pre = true;
	}
	
	public void update() {
		if (Story.orzeik == 1) {
		
			if (anim == 0) {
				Player.x = 49 << 5;
				Player.y = 35 << 5;
				characters.add(new Warden(49, 31, warden1));
			}
			
			for (int i = 0; i < characters.size(); i++) {
				characters.get(i).update();
			}
			
			anim+=2;
			if (anim < (3 * block)) {
				Player.ya = -1;
			}
			else if (anim == 3 * block) {
				Player.ya = 0;
				Game.State = Game.STATE.DIALOGUE;
				Dialogue.dialogue = warden1;
			}
			else if (anim < 10 * block) {
				characters.get(0).moveX(2);
			}
			else if (anim < 17 * block) {
				characters.get(0).moveX(0);
				characters.get(0).moveY(-2);
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
		animating = true;
		finished = true;
	}
	
	//Dialogue
	private String[] warden1 = new String[] {"WARDEN: Ah! Ark, isn't it?", 
		"Oh, you're not in trouble, my boy!",
		"No, I've got a special asssignment for you.",
		"There's some rats that crept into the basement.",
		"Be a good lad and take care of them, will you?",
		"Oh, and you might need this.",
		"*RECIVED SWORD*"
	};
	
}
