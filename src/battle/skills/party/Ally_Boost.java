package battle.skills.party;

import java.awt.Graphics;

import battle.Skill;
import characters.Playable;

public class Ally_Boost extends Skill {

	public Ally_Boost(Playable p) {
		this.p = p;
		x = 0;
		y = 0;
		name = "Ally Boost";
		dmg = p.getMaxHP();
		type = "Defensive";
		range = "Single";
		description = "Max out an ally's defense, but minimize the user's";
	}
	
	public void alter(Playable m) {
		friend = m;
		
		animating = true;
	}
	
	public void animate(Graphics g) {
		anim++;
		
		if (anim == 84) {
			p.setMagDef(p.getBaseMagDef()/2);
			friend.setMagDef(p.getBaseMagDef()*2);
			p.setMagDefTimer(5);
			friend.setMagDefTimer(5);
			p.setMessage("Mag Def/2");
			friend.setMessage("Mag Def x2");
		}
		
		if (anim > 86) {
			anim = 0;
			animating = false;
		}
	}
	
}

