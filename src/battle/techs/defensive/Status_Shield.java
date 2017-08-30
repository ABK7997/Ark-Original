package battle.techs.defensive;

import java.awt.Graphics;

import characters.Playable;
import entity.mobs.enemies.Enemy;
import battle.Spell;

public class Status_Shield extends Spell {

	public Status_Shield(Playable p) {
		this.p = p;
		name = "Status Shield";
		cost = 8;
		dmg = 4;
		type = "Defensive";
		description = "Quadruple Resistance for a long duration";
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
			friend.setRes(friend.getBaseRes());
			friend.setRes(dmg);
			friend.setMessage("Resistance x4");
		}
		if (anim > 90) {
			anim = 0;
			animating = false;
		}
	}
	
	public Status_Shield(Enemy e) {
		this.e = e;
		cost = 8;
		dmg = 4;
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
			eFriend.setRes(eFriend.getBaseRes());
			eFriend.setRes(dmg);
			eFriend.setMessage("Resistance x4");
		}
		if (anim > 90) {
			anim = 0;
			animating = false;
		}
	}
}
