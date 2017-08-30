package battle.skills.party;

import java.awt.Graphics;

import battle.Skill;
import characters.Playable;

public class Omni_Boost extends Skill {

	public Omni_Boost(Playable p) {
		this.p = p;
		x = 0;
		y = 0;
		name = "Omni-Boost";
		dmg = p.getMaxHP();
		type = "Defensive";
		range = "Self";
		description = "Take half damage for 3 turns";
	}
	
	public void alter(Playable m) {
		animating = true;
	}
	
	public void animate(Graphics g) {
		anim++;
		
		if (anim == 84) {
			p.setDef(p.getBaseDef()*2);
			p.setMagDef(p.getBaseMagDef()*2);
			p.setDefTimer(3);
			p.setMagDefTimer(3);
		}
		
		if (anim > 86) {
			anim = 0;
			animating = false;
		}
	}
	
}

