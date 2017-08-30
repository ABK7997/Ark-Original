package battle.spells.defensive;

import java.awt.Graphics;

import characters.Playable;
import entity.mobs.enemies.Enemy;
import battle.Spell;

public class Omni_Protect extends Spell {

	public Omni_Protect(Playable p) {
		this.p = p;
		name = "Omni-Protect";
		cost = 100;
		dmg = 67;
		type = "Defensive";
		range = "All";
		description = "Casts Protect on all party members";
	}

	public void alter(Playable m) {
		friend = p;
		
		p.setMP(-cost);
		
		animating = true;
	}
	
	public void animate(Graphics g) {
		anim++;
		if (anim == 83) {
			for (int i = 0; i < friend.getParty().size(); i++) {
				friend.getParty().get(i).setDefModTimer(8);
				friend.getParty().get(i).setDefMod(dmg);
				friend.getParty().get(i).setMessage("Physical Barrier");
			}
		}
		if (anim > 90) {
			anim = 0;
			animating = false;
		}
	}
	
	public Omni_Protect(Enemy e) {
		this.e = e;
		cost = 30;
		dmg = 67;
		type = "Defensive";
	}

	public void alter(Enemy m) {
		eFriend = e;
		
		e.setMP(-cost);
		
		animating = true;
	}
	
	public void animate2(Graphics g) {
		anim++;
		if (anim == 83) {
			for (int i = 0; i < friend.getParty().size(); i++) {
				eFriend.getParty().get(i).setDefModTimer(8);
				eFriend.getParty().get(i).setDefMod(dmg);
				eFriend.getParty().get(i).setMessage("Physical Barrier");
			}
		}
		if (anim > 90) {
			anim = 0;
			animating = false;
		}
	}
}
