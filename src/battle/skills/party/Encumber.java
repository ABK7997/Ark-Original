package battle.skills.party;

import java.awt.Graphics;

import battle.Skill;
import characters.Playable;
import entity.mobs.enemies.Enemy;

public class Encumber extends Skill {

	public Encumber(Playable p) {
		this.p = p;
		x = 0;
		y = 0;
		name = "Encumber";
		type = "Offensive";
		description = "Severely reduce an enemy's Evasion";
		range = "Single";
	}
	
	public void attack(Enemy m) {
		this.e = m;
		
		e.setEvd(e.getBaseEvd()/10);
		e.setEvdTimer(6);
		
		animating = true;
	}
	
	public void animate(Graphics g) {
		anim++;
		
		if (anim == 83) {
			e.setMessage("Evasion -");
		}
		
		if (anim > 86) {
			anim = 0;
			animating = false;
		}
	}
	
}

