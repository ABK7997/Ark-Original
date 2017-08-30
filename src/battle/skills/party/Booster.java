package battle.skills.party;

import java.awt.Graphics;

import battle.Skill;
import characters.Playable;
import entity.mobs.enemies.Enemy;

public class Booster extends Skill {

	public Booster(Playable p) {
		this.p = p;
		x = 0;
		y = 0;
		name = "Booster";
		type = "Defensive";
		range = "Self";
		description = "Increase Evasion for 4 turns";
	}
	
	public void alter(Playable m) {
		friend = m;
		friend.setEvd(25 + friend.getBaseEvd()*3);
		friend.setEvdTimer(4);
		
		animating = true;
	}
	
	public void animate(Graphics g) {
		anim++;
		
		if (anim > 83) friend.setMessage("Evasion +");
		
		if (anim > 86) {
			anim = 0;
			animating = false;
		}
	}
	
	public Booster(Enemy e) {
		this.e = e;
		x = 0;
		y = 0;
		dmg = e.getBaseEvd()*3;
		type = "Defensive";
	}
	
	public void alter(Enemy e) {
		this.e = e;
		
		e.setEvd(dmg);
		e.setEvdTimer(5);
		
		animating = true;
	}
	
	public void animate2(Graphics g) {
		anim++;
		
		if (anim > 83) e.setMessage("Evasion +");
		
		if (anim > 86) {
			anim = 0;
			animating = false;
		}
	}
	
}

