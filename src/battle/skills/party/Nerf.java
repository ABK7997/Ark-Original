package battle.skills.party;

import java.awt.Graphics;

import battle.Skill;
import characters.Playable;
import entity.mobs.enemies.Enemy;

public class Nerf extends Skill {

	public Nerf(Playable p) {
		this.p = p;
		x = 0;
		y = 0;
		name = "Nerf";
		type = "Offensive";
		range = "Single";
		description = "Half an enemy's power for 3 turns";
	}
	
	public void attack(Enemy m) {
		this.e = m;
		
		e.setPwr(e.getBasePwr()/2);
		e.setPwrTimer(3);
		
		animating = true;
	}
	
	public void animate(Graphics g) {
		anim++;
		
		if (anim == 83) {
			e.setMessage("Power / 2");
		}
		
		if (anim > 86) {
			anim = 0;
			animating = false;
		}
	}
	
}

