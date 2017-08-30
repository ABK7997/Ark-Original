package animations.orzeik;

import characters.Party;
import input.Player;
import main.Game;
import main.Story;
import states.Dialogue;
import animations.Animation;
import entity.mobs.npcs.friends.Orzy_NPC;

public class Orzeik_7 extends Animation {

	//An Unlikely Duo
	public Orzeik_7() {
		x = 58 << 5;
		y = 75 << 5;
		range = 1 << 5;
		pre = true;
	}
	
	public void update() {
		if (Story.orzeik == 7) {
			
			for (int i = 0; i < characters.size(); i++) {
				characters.get(i).update();
			}
		
			if (anim == 0) {
				Player.x = 58 << 5;
				Player.y = 75 << 5;
				characters.add(new Orzy_NPC(55, 75, departure));
			}
			
			anim ++;
			
			if (anim < 1 * block) {
			}
			else if (anim < 2 * block) {
				Player.ya = 0;
				characters.get(0).moveX(1);
			}
			else if (anim == 64) {
				Game.State = Game.STATE.DIALOGUE;
				Dialogue.dialogue = departure;
			}
			else if (anim < 4 * block) {
				characters.get(0).moveX(1);
			}
			else {
				Game.State = Game.STATE.GAME;
				characters.remove(0);
				animating = false;
				Story.orzeik++;
				Party.changeParty(Party.ark, Party.orzy);
				Party.addMember(Party.orzy);
			}
		}
		
		else Game.State = Game.STATE.GAME;
	}
	
	public void startAnimation() {
		Game.State = Game.STATE.ANIMATING;
		animating = true;
		finished = true;
	}
	
	private String[] departure = new String[] {
		"Orzy: Are you ready to leave, Ark?",
		"Ark: *Sigh* As ready as I'll ever be.",
		"Orzy: The gates are open. We head south.",
		"Ark: Just... don't talk to me as much as possible.",
		"Orzy: Order accepted."
	};
}
