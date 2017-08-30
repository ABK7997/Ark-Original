package battle.skills.party;

import java.awt.Graphics;

import battle.Skill;
import characters.Playable;

public class Aim extends Skill {

	public Aim(Playable p) {
		this.p = p;
		x = 0;
		y = 0;
		name = "Aim";
		dmg = 99999;
		type = "Defensive";
		range = "Self";
		description = "Maximize Dexterity for 5 turns";
	}
	
	public void alter(Playable m) {
		p.setDex(dmg);
		p.setDexTimer(6);
		
		animating = true;
	}
	
	public void animate(Graphics g) {
		anim++;
		
		if (anim > 83) p.setMessage("Dexterity+");
		
		if (anim > 86) {
			anim = 0;
			animating = false;
		}
	}
	
}

