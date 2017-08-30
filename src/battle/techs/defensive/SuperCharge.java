package battle.techs.defensive;

import java.awt.Graphics;

import characters.Playable;
import entity.mobs.enemies.Enemy;
import battle.Spell;

public class SuperCharge extends Spell {

	public SuperCharge(Playable p) {
		this.p = p;
		name = "Supercharge";
		cost = 6;
		dmg = 2;
		type = "Defensive";
		description = "Double Evasion for a long duration";
	}

	public void alter(Playable m) {
		friend = m;
		
		m.setEvdTimer(10);
		p.setEP(-cost);
		
		animating = true;
	}
	
	public void animate(Graphics g) {
		anim++;
		if (anim == 83) {
			friend.setEvd(friend.getBaseEvd());
			friend.setEvd(dmg);
			friend.setMessage("Evasion Doubled");
		}
		if (anim > 90) {
			anim = 0;
			animating = false;
		}
	}
	
	public SuperCharge(Enemy e) {
		this.e = e;
		cost = 6;
		dmg = 2;
		type = "Defensive";
	}

	public void alter(Enemy m) {
		eFriend = m;
		
		m.setEvdTimer(10);
		e.setEP(-cost);
		
		animating = true;
	}
	
	public void animate2(Graphics g) {
		anim++;
		if (anim == 83) {
			eFriend.setEvd(eFriend.getBaseEvd());
			eFriend.setEvd(dmg);
			eFriend.setMessage("Evasion Doubled");
		}
		if (anim > 90) {
			anim = 0;
			animating = false;
		}
	}
}
