package battle.techs.defensive;

import java.awt.Graphics;

import characters.Playable;
import battle.Spell;

public class Omni_Cover extends Spell {

	public Omni_Cover(Playable p) {
		this.p = p;
		name = "Omni-Cover";
		cost = 7;
		dmg = 82;
		type = "Defensive";
		range = "All";
		description = "Casts a small damage barrier on all party members";
	}

	public void alter(Playable m) {
		friend = p;
		
		p.setMP(-cost);
		
		animating = true;
	}
	
	public void animate(Graphics g) {
		anim++;
		if (anim == 83) {
			for (int i = 0; i < friend.getParty().size(); i++) {
				friend.getParty().get(i).setDefModTimer(12);
				friend.getParty().get(i).setDefMod(dmg);
				friend.getParty().get(i).setMagModTimer(12);
				friend.getParty().get(i).setMagMod(dmg);
				friend.getParty().get(i).setMessage("Omni-Cover");
			}
		}
		if (anim > 90) {
			anim = 0;
			animating = false;
		}
	}
}
