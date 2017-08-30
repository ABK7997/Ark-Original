package battle.skills.party;

import java.awt.Graphics;

import battle.Skill;
import characters.Playable;
import entity.mobs.enemies.Enemy;

public class Immunity extends Skill {

	public Immunity(Playable p) {
		this.p = p;
		x = 0;
		y = 0;
		name = "Immunity";
		dmg = 9999;
		type = "Defensive";
		range = "Self";
		description = "Maximize Resistance for 5 turns";
	}
	
	public void alter(Playable m) {
		p.setRes(dmg);
		p.setResTimer(5);
		
		animating = true;
	}
	
	public void animate(Graphics g) {
		anim++;
		
		if (anim > 83) p.setMessage("Resistance+");
		
		if (anim > 86) {
			anim = 0;
			animating = false;
		}
	}
	
	public Immunity(Enemy e) {
		this.e = e;
		x = 0;
		y = 0;
		dmg = 9999;
		type = "Defensive";
	}
	
	public void alter(Enemy e) {
		this.e = e;
		
		e.setRes(dmg);
		e.setResTimer(6);
		
		animating = true;
	}
	
	public void animate2(Graphics g) {
		anim++;
		
		if (anim > 83) e.setMessage("Resistance+");
		
		if (anim > 86) {
			anim = 0;
			animating = false;
		}
	}
	
}

