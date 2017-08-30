package animations.orzeik;

import input.Player;
import main.Game;
import main.Story;
import states.Dialogue;
import animations.Animation;
import battle.spells.offensive.Bolt;
import characters.Party;
import entity.mobs.npcs.villains.Warden;

public class Orzeik_4 extends Animation {

	//A New Kind of Man
	public Orzeik_4() {
		x = 56 << 5;
		y = 64 << 5;
		range = 2 << 5;
		pre = true;
	}
	
	public void update() {
		if (Story.orzeik == 4) {
		
			if (anim == 0) {
				characters.add(new Warden(56, 60, warden1));
				characters.get(0).turn(2);
				Player.ya = -1;
			}
			
			anim++;
		
			for (int i = 0; i < characters.size(); i++) {
				characters.get(i).update();
			}

			if (anim == 1) {
				Player.ya = 0;
				characters.get(0).turn(2);
				Game.State = Game.STATE.DIALOGUE;
				Dialogue.dialogue = warden1;
			}
		
			else if (anim < 4 * block) {
				characters.get(0).moveY(-1);
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
		animating = true;
		finished = true;
		Player.ya = -1;
		Party.ark.learnSpell(new Bolt(Party.ark));
		Party.ark.levelRestore();
	}
	
	//Dialogue
	private String[] warden1 = new String[] {"(Several hours later...)",
			"Ark: Oh, my aching body...",
			"WARDEN: Ah, I see that you're awake finally.",
			"Careful, you've just been infused with magic.",
			"Ark: What? Magic?",
			"Warden: You see, Ark, you're a part of new experiment.",
			"I was impressed by your combat skills, so you were a good candidate.",
			"Ark: Candidate for what?",
			"Warden: The Agickans destroy our machines with their magic.",
			"Machines simply don't stand a chance.",
			"So we took some of their harvested blood...",
			"...and injected it into yours.",
			"The results are, so far, very promising.",
			"Ark: What? Are you crazy?",
			"Warden: I'd recommend taking it easy for a moment.",
			"Ark: Not a chance...",
			"Magic, huh? Let's see how you like it!",
			"Warden: Yes, Ark! Show me what you can do!"
	};
}
