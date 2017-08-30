package battle.techs.defensive;

import java.awt.Graphics;

import characters.Playable;
import entity.mobs.enemies.Enemy;
import battle.Spell;

public class Iron_Shield extends Spell {

	public Iron_Shield(Playable p) {
		this.p = p;
		name = "Iron Shield";
		cost = 8;
		dmg = 50;
		type = "Defensive";
		description = "Reduces physical damage taken by half for a short time";
	}

	public void alter(Playable m) {
		friend = m;
		
		m.setDefModTimer(5);
		p.setEP(-cost);
		
		animating = true;
	}
	
	public void animate(Graphics g) {
		anim++;
		if (anim == 83) {
			friend.setDefMod(dmg);
			friend.setMessage("Shielded");
		}
		if (anim > 90) {
			anim = 0;
			animating = false;
		}
	}
	
	public Iron_Shield(Enemy e) {
		this.e = e;
		cost = 8;
		dmg = 50;
		type = "Defensive";
	}

	public void alter(Enemy m) {
		eFriend = m;
		
		m.setDefModTimer(5);
		e.setMP(-cost);
		
		animating = true;
	}
	
	public void animate2(Graphics g) {
		anim++;
		if (anim == 83) {
			eFriend.setDefMod(dmg);
			eFriend.setMessage("Shielded");
		}
		if (anim > 90) {
			anim = 0;
			animating = false;
		}
	}
}
