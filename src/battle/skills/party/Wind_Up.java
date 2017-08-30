package battle.skills.party;

import java.awt.Graphics;

import battle.Skill;
import characters.Playable;
import entity.mobs.enemies.Enemy;

public class Wind_Up extends Skill {

	public Wind_Up(Playable p) {
		this.p = p;
		x = 0;
		y = 0;
		name = "Wind Up";
		dmg = p.getBasePwr() * 2;
		type = "Defensive";
		range = "Self";
		description = "Double Power for 3 turns";
	}
	
	public void alter(Playable m) {
		p.setPwr(dmg);
		p.setPwrTimer(4);
		
		animating = true;
	}
	
	public void animate(Graphics g) {
		anim++;
		
		if (anim > 83) p.setMessage("Power x 2");
		
		if (anim > 86) {
			anim = 0;
			animating = false;
		}
	}
	
	public Wind_Up(Enemy e) { //Acts for 4 turns
		this.e = e;
		x = 0;
		y = 0;
		dmg = e.getBasePwr() * 2;
		type = "Defensive";
	}
	
	public void alter(Enemy m) {
		e = m;
		
		dmg = e.getBasePwr() * 2;
		
		e.setPwr(dmg);
		e.setPwrTimer(4);
		
		animating = true;
	}
	
	public void animate2(Graphics g) {
		anim++;
		
		if (anim > 83) e.setMessage("Power x 2");
		
		if (anim > 86) {
			anim = 0;
			animating = false;
		}
	}
}

