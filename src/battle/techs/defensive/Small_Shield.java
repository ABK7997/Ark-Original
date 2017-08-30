package battle.techs.defensive;

import java.awt.Graphics;

import characters.Playable;
import battle.Spell;

public class Small_Shield extends Spell {

	public Small_Shield(Playable p) {
		this.p = p;
		name = "Small Shield";
		cost = 3;
		dmg = 82;
		type = "Defensive";
		description = "Slightly reduce physical damage for an extended duration";
	}

	public void alter(Playable m) {
		friend = m;
		
		m.setDefModTimer(15);
		p.setEP(-cost);
		
		animating = true;
	}
	
	public void animate(Graphics g) {
		anim++;
		if (anim == 83) {
			friend.setDefMod(dmg);
			friend.setMessage("Shielded");
		}
		if (anim > 90) {
			anim = 0;
			animating = false;
		}
	}
}
