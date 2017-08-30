package battle.skills.party;

import java.awt.Graphics;

import battle.Skill;
import characters.Playable;

public class Swift_Shell extends Skill {

	public Swift_Shell(Playable p) {
		this.p = p;
		x = 0;
		y = 0;
		name = "Swift Shell";
		type = "Defensive";
		range = "All";
		description = "Double the party's magic defense for the active turn";
	}
	
	public void alter(Playable m) {
		animating = true;
		
	}
	
	public void animate(Graphics g) {
		anim++;
		
		if (anim == 84) {
			for (int i = 0; i < p.getParty().size(); i++) {
				p.getParty().get(i).setMagDef(p.getParty().get(i).getBaseMagDef()*2);
				p.getParty().get(i).setMessage("Magic Shell");
				p.getParty().get(i).setMagDefTimer(1);
			}
		}
		
		if (anim > 86) {
			anim = 0;
			animating = false;
		}
	}
	
}

