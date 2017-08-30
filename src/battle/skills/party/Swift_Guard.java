package battle.skills.party;

import java.awt.Graphics;

import battle.Skill;
import characters.Playable;

public class Swift_Guard extends Skill {

	public Swift_Guard(Playable p) {
		this.p = p;
		x = 0;
		y = 0;
		name = "Swift Guard";
		type = "Defensive";
		range = "All";
		description = "Double the party's physical defense for the active turn";
	}
	
	public void alter(Playable m) {
		animating = true;
		
	}
	
	public void animate(Graphics g) {
		anim++;
		
		if (anim == 84) {
			for (int i = 0; i < p.getParty().size(); i++) {
				p.getParty().get(i).setDef(p.getParty().get(i).getBaseDef()*2);
				p.getParty().get(i).setMessage("Defending");
				p.getParty().get(i).setDefTimer(1);
			}
		}
		
		if (anim > 86) {
			anim = 0;
			animating = false;
		}
	}
	
}

