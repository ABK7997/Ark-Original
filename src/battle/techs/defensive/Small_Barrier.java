package battle.techs.defensive;

import java.awt.Graphics;

import characters.Playable;
import battle.Spell;

public class Small_Barrier extends Spell {

	public Small_Barrier(Playable p) {
		this.p = p;
		name = "Small Barrier";
		cost = 4;
		dmg = 82;
		type = "Defensive";
		description = "Slightly reduce all damage taken for a long duration";
	}

	public void alter(Playable m) {
		friend = m;
		
		m.setDefModTimer(12);
		m.setMagModTimer(12);
		p.setEP(-cost);
		
		animating = true;
	}
	
	public void animate(Graphics g) {
		anim++;
		if (anim == 83) {
			friend.setDefMod(dmg);
			friend.setMagMod(dmg);
			friend.setMessage("Mini-Barrier");
		}
		if (anim > 90) {
			anim = 0;
			animating = false;
		}
	}
}
