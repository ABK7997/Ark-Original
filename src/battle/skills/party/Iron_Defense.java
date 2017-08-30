package battle.skills.party;

import java.awt.Graphics;

import battle.Skill;
import characters.Playable;
import entity.mobs.enemies.Enemy;

public class Iron_Defense extends Skill {

	public Iron_Defense(Playable p) {
		this.p = p;
		x = 0;
		y = 0;
		dmg = p.getBaseDef()*3;
		name = "Iron Defense";
		type = "Defensive";
		range = "Self";
		description = "Triple defense for the active turn";
	}
	
	public void alter(Playable m) {
		p.setDef(dmg);
		p.setDefTimer(1);
		
		animating = true;
	}
	
	public void animate(Graphics g) {
		anim++;
		
		if (anim > 83) p.setMessage("Defense x 3");
		
		if (anim > 86) {
			anim = 0;
			animating = false;
		}
	}
	
	public Iron_Defense(Enemy e) {
		this.e = e;
		x = 0;
		y = 0;
		dmg = e.getBaseDef()*3;
		type = "Defensive";
	}
	
	public void alter(Enemy e) {
		e.setDef(dmg);
		e.setDefTimer(2);
		
		animating = true;
	}
	
	public void animate2(Graphics g) {
		anim++;
		
		if (anim > 83) e.setMessage("Defense x 3");
		
		if (anim > 86) {
			anim = 0;
			animating = false;
		}
	}
	
}

