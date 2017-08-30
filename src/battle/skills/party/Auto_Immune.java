package battle.skills.party;

import java.awt.Graphics;

import battle.Skill;
import characters.Playable;
import entity.mobs.enemies.Enemy;

public class Auto_Immune extends Skill {

	public Auto_Immune(Playable p) {
		this.p = p;
		x = 0;
		y = 0;
		name = "Auto-Immunity";
		type = "Offensive";
		description = "Severely reduce an enemy's Resistance";
		range = "Single";
	}
	
	public void attack(Enemy m) {
		this.e = m;
		
		e.setRes(0);
		e.setResTimer(7);
		
		animating = true;
	}
	
	public void animate(Graphics g) {
		anim++;
		
		if (anim == 83) {
			e.setMessage("Resistance -");
		}
		
		if (anim > 86) {
			anim = 0;
			animating = false;
		}
	}
	
}

