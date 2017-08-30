package battle.spells.defensive;

import java.awt.Graphics;

import characters.Playable;
import entity.mobs.enemies.Enemy;
import battle.Spell;

public class Super extends Spell {

	public Super(Playable p) {
		this.p = p;
		name = "Super";
		cost = 100;
		dmg = 2;
		type = "Defensive";
		description = "Raise Speed, Dex, Evasion, and Resistance for several turns";
	}

	public void alter(Playable m) {
		friend = m;
		
		m.setDexTimer(12);
		m.setSpdTimer(12);
		m.setEvdTimer(12);
		m.setResTimer(12);
		p.setMP(-cost);
		
		animating = true;
	}
	
	public void animate(Graphics g) {
		anim++;
		if (anim == 83) {
			friend.setEvd(friend.getBaseEvd() * 2);
			friend.setDex(friend.getBaseDex() * 2);
			friend.setSpd(friend.getBaseSpd() * 2);
			friend.setRes(friend.getBaseRes() * 2);
			friend.setMessage("Spd+ Evd+ Dex+ Res+");
		}
		if (anim > 90) {
			anim = 0;
			animating = false;
		}
	}
	
	public Super(Enemy e) {
		this.e = e;
		cost = 100;
		dmg = 9999;
		type = "Defensive";
	}

	public void alter(Enemy m) {
		eFriend = m;
		
		m.setDexTimer(12);
		m.setSpdTimer(12);
		m.setEvdTimer(12);
		m.setResTimer(12);
		e.setMP(-cost);
		
		animating = true;
	}
	
	public void animate2(Graphics g) {
		anim++;
		if (anim == 83) {
			eFriend.setEvd(friend.getBaseEvd() * 2);
			eFriend.setDex(friend.getBaseDex() * 2);
			eFriend.setSpd(friend.getBaseSpd() * 2);
			eFriend.setRes(friend.getBaseRes() * 2);
			eFriend.setMessage("Spd+ Evd+ Dex+ Res+");
		}
		if (anim > 90) {
			anim = 0;
			animating = false;
		}
	}
}
