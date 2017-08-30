package battle.skills.party;

import java.awt.Graphics;

import battle.Skill;
import characters.Playable;

public class Energy_Donor extends Skill {

	public Energy_Donor(Playable p) {
		this.p = p;
		x = 0;
		y = 0;
		name = "Energy Donor";
		dmg = p.getMaxHP()/3;
		type = "Defensive";
		range = "All";
		description = "Sacrifice all EP to restore others' MP and EP";
	}
	
	public void alter(Playable m) {
		animating = true;
	}
	
	public void animate(Graphics g) {
		anim++;
		
		double d = (p.getEP()/p.getMaxEP()) + 1;
		
		if (anim == 84) {
			for (int i = 0; i < p.getParty().size(); i++) {
				if (p.getParty().get(i).getMaxMP() > 0) {
					p.getParty().get(i).setMP((int) (p.getParty().get(i).getMP()*d));
					p.getParty().get(i).setRP((int) (p.getParty().get(i).getMP()*d));
				}
				else {
					p.getParty().get(i).setMP((int) (p.getParty().get(i).getEP()*d));
					p.getParty().get(i).setRP((int) (p.getParty().get(i).getEP()*d));
				}
			}
			p.setSP(99);
			p.setRP((int) (-p.getEP()*d));
			p.setEP(-99);
		}
		
		if (anim > 86) {
			anim = 0;
			animating = false;
		}
	}
	
}

