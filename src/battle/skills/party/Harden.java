package battle.skills.party;

import java.awt.Graphics;

import battle.Skill;
import characters.Playable;
import entity.mobs.enemies.Enemy;

public class Harden extends Skill {

	public Harden(Playable p) {
		this.p = p;
		x = 0;
		y = 0;
		dmg = 9999;
		name = "Harden";
		type = "Defensive";
		range = "Self";
		description = "Invincible against physical damage for the active turn";
	}
	
	public void alter(Playable m) {
		p.setDef(dmg);
		p.setDefTimer(1);
		
		animating = true;
	}
	
	public void animate(Graphics g) {
		anim++;
		
		if (anim > 83) p.setMessage("Invincible");
		
		if (anim > 86) {
			anim = 0;
			animating = false;
		}
	}
	
	public Harden(Enemy e) {
		this.e = e;
		x = 0;
		y = 0;
		dmg = 9999;
		name = "Harden";
		type = "Defensive";
	}
	
	public void alter(Enemy e) {
		e.setDef(dmg);
		e.setDefTimer(2);
		
		animating = true;
	}
	
	public void animate2(Graphics g) {
		anim++;
		
		if (anim > 83) e.setMessage("Power x 3");
		
		if (anim > 86) {
			anim = 0;
			animating = false;
		}
	}
	
}

