package battle.skills.party;

import java.awt.Graphics;

import battle.Skill;
import characters.Playable;

public class Muscled extends Skill {

	public Muscled(Playable p) {
		this.p = p;
		x = 0;
		y = 0;
		name = "Muscled";
		dmg = p.getBasePwr() * 2;
		type = "Defensive";
		range = "Self";
		description = "Double Power for 4 turns";
	}
	
	public void alter(Playable m) {
		p.setPwr(dmg);
		p.setPwrTimer(5);
		
		animating = true;
	}
	
	public void animate(Graphics g) {
		anim++;
		
		if (anim > 83) p.setMessage("Power x 2");
		
		if (anim > 86) {
			anim = 0;
			animating = false;
		}
	}
	
}

