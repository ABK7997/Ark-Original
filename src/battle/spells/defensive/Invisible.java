package battle.spells.defensive;

import java.awt.Graphics;

import characters.Playable;
import entity.mobs.enemies.Enemy;
import battle.Spell;

public class Invisible extends Spell {

	public Invisible(Playable p) {
		this.p = p;
		name = "Invisibility";
		cost = 75;
		dmg = 9999;
		type = "Defensive";
		description = "Avoid physical damage for a few turns";
	}

	public void alter(Playable m) {
		friend = m;
		
		m.setEvdTimer(4);
		p.setMP(-cost);
		
		animating = true;
	}
	
	public void animate(Graphics g) {
		anim++;
		if (anim == 83) {
			friend.setEvd(friend.getBaseEvd());
			friend.setEvd(dmg);
			friend.setMessage("Untouchable");
		}
		if (anim > 90) {
			anim = 0;
			animating = false;
		}
	}
	
	public Invisible(Enemy e) {
		this.e = e;
		cost = 75;
		dmg = 9999;
		type = "Defensive";
	}

	public void alter(Enemy m) {
		eFriend = m;
		
		m.setEvdTimer(4);
		e.setMP(-cost);
		
		animating = true;
	}
	
	public void animate2(Graphics g) {
		anim++;
		if (anim == 83) {
			eFriend.setEvd(friend.getBaseEvd());
			eFriend.setEvd(dmg);
			eFriend.setMessage("Untouchable");
		}
		if (anim > 90) {
			anim = 0;
			animating = false;
		}
	}
}
