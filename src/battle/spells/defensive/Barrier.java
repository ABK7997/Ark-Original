package battle.spells.defensive;

import java.awt.Graphics;

import characters.Playable;
import entity.mobs.enemies.Enemy;
import battle.Spell;

public class Barrier extends Spell {

	public Barrier(Playable p) {
		this.p = p;
		name = "Barrier";
		cost = 50;
		dmg = 70;
		type = "Defensive";
		description = "Cast Protect and Field";
	}

	public void alter(Playable m) {
		friend = m;
		
		m.setDefModTimer(8);
		m.setMagModTimer(8);
		p.setMP(-cost);
		
		animating = true;
	}
	
	public void animate(Graphics g) {
		anim++;
		if (anim == 83) {
			friend.setDefMod(dmg);
			friend.setMagMod(dmg);
			friend.setMessage("Physical/Magic Barrier");
		}
		if (anim > 90) {
			anim = 0;
			animating = false;
		}
	}
	
	public Barrier(Enemy e) {
		this.e = e;
		cost = 30;
		dmg = 70;
		type = "Defensive";
	}

	public void alter(Enemy m) {
		eFriend = m;
		
		m.setDefModTimer(8);
		m.setMagModTimer(8);
		e.setMP(-cost);
		
		animating = true;
	}
	
	public void animate2(Graphics g) {
		anim++;
		if (anim == 83) {
			eFriend.setDefMod(dmg);
			eFriend.setMagMod(dmg);
			eFriend.setMessage("Physical/Magic Barrier");
		}
		if (anim > 90) {
			anim = 0;
			animating = false;
		}
	}
}
