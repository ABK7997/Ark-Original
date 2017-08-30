package battle.spells.defensive;

import java.awt.Graphics;

import characters.Playable;
import entity.mobs.enemies.Enemy;
import battle.Spell;

public class Field extends Spell {

	public Field(Playable p) {
		this.p = p;
		name = "Field";
		cost = 30;
		dmg = 67;
		type = "Defensive";
		description = "Creates a temporary buffer against magic damage";
	}

	public void alter(Playable m) {
		friend = m;
		
		m.setMagModTimer(10);
		p.setMP(-cost);
		
		animating = true;
	}
	
	public void animate(Graphics g) {
		anim++;
		if (anim == 83) {
			friend.setMagMod(dmg);
			friend.setMessage("Magic Barrier");
		}
		if (anim > 90) {
			anim = 0;
			animating = false;
		}
	}
	
	public Field(Enemy e) {
		this.e = e;
		cost = 30;
		dmg = 67;
		type = "Defensive";
	}

	public void alter(Enemy m) {
		eFriend = m;
		
		m.setMagModTimer(10);
		e.setMP(-cost);
		
		animating = true;
	}
	
	public void animate2(Graphics g) {
		anim++;
		if (anim == 83) {
			eFriend.setMagMod(dmg);
			eFriend.setMessage("Magic Barrier");
		}
		if (anim > 90) {
			anim = 0;
			animating = false;
		}
	}
}
