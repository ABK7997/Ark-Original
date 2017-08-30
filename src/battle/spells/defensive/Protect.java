package battle.spells.defensive;

import java.awt.Graphics;

import characters.Playable;
import entity.mobs.enemies.Enemy;
import battle.Spell;

public class Protect extends Spell {

	public Protect(Playable p) {
		this.p = p;
		name = "Protect";
		cost = 30;
		dmg = 67;
		type = "Defensive";
		description = "Creates a temporary buffer against physical damage";
	}

	public void alter(Playable m) {
		friend = m;
		
		m.setDefModTimer(10);
		p.setMP(-cost);
		
		animating = true;
	}
	
	public void animate(Graphics g) {
		anim++;
		if (anim == 83) {
			friend.setDefMod(dmg);
			friend.setMessage("Physical Barrier");
		}
		if (anim > 90) {
			anim = 0;
			animating = false;
		}
	}
	
	public Protect(Enemy e) {
		this.e = e;
		cost = 30;
		dmg = 67;
		type = "Defensive";
	}

	public void alter(Enemy m) {
		eFriend = m;
		
		m.setDefModTimer(10);
		e.setMP(-cost);
		
		animating = true;
	}
	
	public void animate2(Graphics g) {
		anim++;
		if (anim == 83) {
			eFriend.setDefMod(dmg);
			eFriend.setMessage("Physical Barrier");
		}
		if (anim > 90) {
			anim = 0;
			animating = false;
		}
	}
}
