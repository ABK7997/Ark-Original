package battle.skills.party;

import java.awt.Graphics;

import battle.Skill;
import characters.Playable;

public class Sacrifice extends Skill {

	public Sacrifice(Playable p) {
		this.p = p;
		x = 0;
		y = 0;
		name = "Sacrifice";
		dmg = p.getMaxHP();
		type = "Defensive";
		range = "All";
		description = "Sacrifice all HP to fully restore others' HP and MP";
	}
	
	public void alter(Playable m) {
		animating = true;
	}
	
	public void animate(Graphics g) {
		anim++;
		
		if (anim == 84) {
			for (int i = 0; i < p.getParty().size(); i++) {
				p.getParty().get(i).setHP(9999);
				p.getParty().get(i).setMP(9999);
				p.getParty().get(i).setEP(99);
				if (p.getParty().get(i).getMaxMP() > 0) p.getParty().get(i).setRP(9999);
				else p.getParty().get(i).setRP(99);
				p.getParty().get(i).setCP(9999);
			}
			p.setHP(-p.getMaxHP());
			p.setDP(9999);
			p.setRP(-9999);
			p.setCP(-9999);
		}
		
		if (anim > 86) {
			anim = 0;
			animating = false;
		}
	}
	
}

