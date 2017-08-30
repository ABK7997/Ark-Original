package battle.skills.party;

import java.awt.Graphics;

import battle.Skill;
import characters.Playable;

public class Disperse_Power extends Skill {

	public Disperse_Power(Playable p) {
		this.p = p;
		x = 0;
		y = 0;
		name = "Disperse Power";
		dmg = 2;
		type = "Defensive";
		range = "All";
		description = "Sacrifice Pwr to boost others' for 6 turns";
	}
	
	public void alter(Playable m) {
		animating = true;
	}
	
	public void animate(Graphics g) {
		anim++;
		
		if (anim == 84) {
			for (int i = 0; i < p.getParty().size(); i++) {
				p.getParty().get(i).setPwr(p.getParty().get(i).getBasePwr()*2);
				p.getParty().get(i).setMessage("Power x2");
				p.getParty().get(i).setPwrTimer(6);
			}
			p.setMessage("Power Drained");
			p.setPwr(p.getBasePwr()/3);
			p.setPwrTimer(6);
		}
		
		if (anim > 86) {
			anim = 0;
			animating = false;
		}
	}
	
}

