package battle.techs.defensive;

import java.awt.Graphics;

import characters.Playable;
import battle.Spell;

public class Small_Shell extends Spell {

	public Small_Shell(Playable p) {
		this.p = p;
		name = "Small Shell";
		cost = 3;
		dmg = 82;
		type = "Defensive";
		description = "Slightly reduce magic damage taken for a long duration";
	}

	public void alter(Playable m) {
		friend = m;
		
		m.setMagModTimer(15);
		p.setEP(-cost);
		
		animating = true;
	}
	
	public void animate(Graphics g) {
		anim++;
		if (anim == 83) {
			friend.setMagMod(dmg);
			friend.setMessage("Shell");
		}
		if (anim > 90) {
			anim = 0;
			animating = false;
		}
	}
}
