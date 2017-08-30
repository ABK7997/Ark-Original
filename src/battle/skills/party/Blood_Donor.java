package battle.skills.party;

import java.awt.Graphics;

import battle.Skill;
import characters.Playable;

public class Blood_Donor extends Skill {

	public Blood_Donor(Playable p) {
		this.p = p;
		x = 0;
		y = 0;
		name = "Blood Donor";
		dmg = p.getMaxHP()/3;
		type = "Defensive";
		range = "All";
		description = "Sacrifice 1/3 Max HP and give it to others";
	}
	
	public void alter(Playable m) {
		animating = true;
	}
	
	public void animate(Graphics g) {
		anim++;
		
		dmg = p.getMaxHP()/3;
		
		if (anim == 84) {
			for (int i = 0; i < p.getParty().size(); i++) {
				p.getParty().get(i).setHP(dmg);
				p.getParty().get(i).setCP(dmg);
			}
			p.setHP(-dmg);
			p.setCP(-dmg);
			p.setDP(dmg);
		}
		
		if (anim > 86) {
			anim = 0;
			animating = false;
		}
	}
	
}

