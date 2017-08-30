package battle.skills.party;

import java.awt.Graphics;

import battle.Skill;
import characters.Playable;
import entity.mobs.enemies.Enemy;

public class Reduce extends Skill {

	public Reduce(Playable p) {
		this.p = p;
		x = 0;
		y = 0;
		name = "Nerf";
		type = "Offensive";
		description = "Half an enemy's magic power for 3 turns";
		range = "Single";
	}
	
	public void attack(Enemy m) {
		this.e = m;
		
		e.setMag(e.getBaseMag()/2);
		e.setMagTimer(3);
		
		animating = true;
	}
	
	public void animate(Graphics g) {
		anim++;
		
		if (anim == 83) {
			e.setMessage("Magic / 2");
		}
		
		if (anim > 86) {
			anim = 0;
			animating = false;
		}
	}
	
}

