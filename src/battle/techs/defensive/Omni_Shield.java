package battle.techs.defensive;

import java.awt.Graphics;

import characters.Playable;
import entity.mobs.enemies.Enemy;
import battle.Spell;

public class Omni_Shield extends Spell {

	public Omni_Shield(Playable p) {
		this.p = p;
		name = "Omni Shield";
		cost = 12;
		dmg = 50;
		type = "Defensive";
		description = "Reduce all damage by half for a short time";
	}

	public void alter(Playable m) {
		friend = m;
		
		m.setDefModTimer(4);
		m.setMagModTimer(4);
		p.setMP(-cost);
		
		animating = true;
	}
	
	public void animate(Graphics g) {
		anim++;
		if (anim == 83) {
			friend.setDefMod(dmg);
			friend.setMagMod(dmg);
			friend.setMessage("Omni-Shield");
		}
		if (anim > 90) {
			anim = 0;
			animating = false;
		}
	}
	
	public Omni_Shield(Enemy e) {
		this.e = e;
		cost = 12;
		dmg = 50;
		type = "Defensive";
	}

	public void alter(Enemy m) {
		eFriend = m;
		
		m.setDefModTimer(5);
		m.setMagModTimer(5);
		e.setMP(-cost);
		
		animating = true;
	}
	
	public void animate2(Graphics g) {
		anim++;
		if (anim == 83) {
			eFriend.setDefMod(dmg);
			eFriend.setMagMod(dmg);
			eFriend.setMessage("Omni-Shield");
		}
		if (anim > 90) {
			anim = 0;
			animating = false;
		}
	}
}
