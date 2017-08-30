package battle.skills.party;

import java.awt.Graphics;

import battle.Skill;
import characters.Playable;
import characters.Playable.STATE;
import entity.mobs.enemies.Enemy;
import entity.mobs.enemies.Enemy.STATES;

public class Wide_Sweep extends Skill {

	public Wide_Sweep(Playable p) {
		this.p = p;
		x = 0;
		y = 0;
		name = "Wide Sweep";
		dmg = p.getPwr() * 3/5;
		type = "Offensive";
		description = "Hit all enemies for reduced damage";
		range = "All";
	}
	
	public void attack(Enemy m) {
		this.e = m;
		
		dmg = p.getPwr() * 2/5;
		
		animating = true;
	}
	
	public void animate(Graphics g) {
		anim++;
		
		if (anim == 83) {
			for (int i = 0; i < e.getParty().size(); i++) {
				dmg = ((dmg/e.getParty().get(i).getDef())*e.getParty().get(i).getDefMod()) / 100;
				e.getParty().get(i).setHP(-dmg);
				e.getParty().get(i).setDP(dmg);
				e.getParty().get(i).changeState(STATES.HIT);
			}
		}
		
		if (anim > 86) {
			anim = 0;
			animating = false;
		}
	}
	
	public Wide_Sweep(Enemy e) {
		this.e = e;
		x = 0;
		y = 0;
		dmg = e.getPwr() * 2/5;
		type = "Offensive";
		range = "All";
	}
	
	public void attack(Playable m) {
		this.p = m;
		
		dmg = e.getPwr() * 2/5;
		
		animating = true;
	}
	
	public void animate2(Graphics g) {
		anim++;
		
		if (anim == 83) {
			for (int i = 0; i < p.getParty().size(); i++) {
				dmg = e.getPwr()*2/5;
				dmg = ((dmg/p.getParty().get(i).getDef())*p.getParty().get(i).getDefMod()) / 100;
				p.getParty().get(i).setHP(-dmg);
				p.getParty().get(i).setDP(dmg);
				p.getParty().get(i).changeState(STATE.HIT);
			}
		}
		
		if (anim > 86) {
			anim = 0;
			animating = false;
		}
	}
}

