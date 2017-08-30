package battle.spells.defensive;

import java.awt.Graphics;

import characters.Playable;
import entity.mobs.enemies.Enemy;
import battle.Spell;

public class Focus extends Spell {

	public Focus(Playable p) {
		this.p = p;
		name = "Focus";
		cost = 10;
		dmg = 9999;
		type = "Defensive";
		description = "Maximize dexterity for several turns";
	}

	public void alter(Playable m) {
		friend = m;
		
		m.setDexTimer(10);
		p.setMP(-cost);
		
		animating = true;
	}
	
	public void animate(Graphics g) {
		anim++;
		if (anim == 83) {
			friend.setEvd(dmg);
			friend.setMessage("Eagle Eye");
		}
		if (anim > 90) {
			anim = 0;
			animating = false;
		}
	}
	
	public Focus(Enemy e) {
		this.e = e;
		cost = 10;
		dmg = 9999;
		type = "Defensive";
	}

	public void alter(Enemy m) {
		eFriend = m;
		
		m.setDexTimer(10);
		e.setMP(-cost);
		
		animating = true;
	}
	
	public void animate2(Graphics g) {
		anim++;
		if (anim == 83) {
			eFriend.setEvd(dmg);
			eFriend.setMessage("Eagle Eye");
		}
		if (anim > 90) {
			anim = 0;
			animating = false;
		}
	}
}
