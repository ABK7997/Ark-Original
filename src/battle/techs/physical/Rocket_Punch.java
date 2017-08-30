package battle.techs.physical;

import java.awt.Graphics;

import battle.Spell;
import characters.Playable;
import characters.Playable.STATE;
import entity.mobs.enemies.Enemy;
import entity.mobs.enemies.Enemy.STATES;

public class Rocket_Punch extends Spell {

	public Rocket_Punch(Playable p) {
		this.p = p;
		x = 0;
		y = 0;
		name = "Rocket Punch";
		cost = 3;
		dmg = p.getPwr() * 5 / 2;
		type = "Offensive";
		description = "Hit a single enemy for much greater than normal damage";
	}
	
	public void attack(Enemy e) {
		this.e = e;
		
		dmg /= e.getDef();
		
		p.setEP(-cost);
		animating = true;
	}
	
	public void animate(Graphics g) {
		anim++;
		if (anim == 82) {
			e.setHP(-dmg);
			e.setDP(dmg);
			e.changeState(STATES.HIT);
		}
		if (anim > 130) {
			animating = false;
			anim = 0;
		}
	}
	
	public Rocket_Punch(Enemy e) {
		this.e = e;
		x = 0;
		y = 0;
		cost = 3;
		dmg = e.getPwr() * 5 / 2;
		type = "Offensive";
	}
	
	public void attack(Playable p) {
		this.p = p;
		
		dmg = e.getPwr() * 5 / 2;
		dmg = ((dmg/p.getDef()) * p.getDefMod()) / 100;
		
		e.setEP(-cost);
		animating = true;
	}
	
	public void animate2(Graphics g) {
		anim++;
		if (anim == 82) {
			p.setHP(-dmg);
			p.setDP(dmg);
			p.changeState(STATE.HIT);
		}
		if (anim > 130) {
			animating = false;
			anim = 0;
		}
	}
	
}
