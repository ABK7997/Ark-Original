package battle.skills.party;

import java.awt.Graphics;

import battle.Skill;
import characters.Playable;
import entity.mobs.enemies.Enemy;

public class Charge extends Skill {

	public Charge(Playable p) {
		this.p = p;
		x = 0;
		y = 0;
		name = "Charge";
		dmg = p.getBaseMag() * 2;
		type = "Defensive";
		range = "Self";
		description = "Double magical power for one turn";
	}
	
	public void alter(Playable m) {
		p.setMag(dmg);
		p.setMagTimer(1);
		
		animating = true;
	}
	
	public void animate(Graphics g) {
		anim++;
		
		if (anim > 83) p.setMessage("Magic x 2");
		
		if (anim > 86) {
			anim = 0;
			animating = false;
		}
	}
	
	public Charge(Enemy e) {
		this.e = e;
		x = 0;
		y = 0;
		dmg = e.getBaseMag() * 2;
		type = "Defensive";
	}
	
	public void alter(Enemy e) {
		e.setMag(dmg);
		e.setMagTimer(2);
		
		animating = true;
	}
	
	public void animate2(Graphics g) {
		anim++;
		
		if (anim > 83) e.setMessage("Magic x 2");
		
		if (anim > 86) {
			anim = 0;
			animating = false;
		}
	}
	
}

