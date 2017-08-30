package battle.skills.party;

import java.awt.Graphics;

import battle.Skill;
import characters.Playable;
import entity.mobs.enemies.Enemy;

public class Bulk_Up extends Skill {

	public Bulk_Up(Playable p) {
		this.p = p;
		x = 0;
		y = 0;
		name = "Bulk Up";
		dmg = p.getBasePwr() * 3;
		type = "Defensive";
		range = "Self";
		description = "Triple power for one turn";
	}
	
	public void alter(Playable m) {
		dmg = p.getBasePwr()*3;
		
		p.setPwr(dmg);
		p.setPwrTimer(1);
		
		animating = true;
	}
	
	public void animate(Graphics g) {
		anim++;
		
		if (anim > 83) p.setMessage("Power x 3");
		
		if (anim > 86) {
			anim = 0;
			animating = false;
		}
	}
	
	public Bulk_Up(Enemy e) {
		this.e = e;
		x = 0;
		y = 0;
		dmg = e.getBasePwr() * 3;
		type = "Defensive";
	}
	
	public void alter(Enemy m) {
		e = m;
		dmg = e.getBasePwr() * 3;
		
		e.setPwr(e.getBasePwr()*3);
		e.setPwrTimer(2);
		
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

