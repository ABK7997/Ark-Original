package battle.techs.physical;

import java.awt.Graphics;

import battle.Spell;
import characters.Playable;
import characters.Playable.STATE;
import entity.mobs.enemies.Enemy;
import entity.mobs.enemies.Enemy.STATES;

public class Swing extends Spell {

	public Swing(Playable p) {
		this.p = p;
		x = 0;
		y = 0;
		name = "Swing";
		cost = 2;
		dmg = p.getPwr() * 3 / 2;
		type = "Offensive";
		description = "Hit a single enemy for somewhat greater than normal damage";
	}
	
	public void attack(Enemy e) {
		this.e = e;
		
		dmg = p.getPwr() * 3 / 2;
		dmg = ((dmg / e.getDef()) * e.getDefMod()) / 100;
		
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
	
	public Swing(Enemy e) {
		this.e = e;
		x = 0;
		y = 0;
		cost = 2;
		dmg = e.getPwr() * 3 / 2;
		type = "Offensive";
	}
	
	public void attack(Playable p) {
		this.p = p;
		
		dmg = e.getPwr() * 3 / 2;
		dmg = ((dmg / p.getDef()) * p.getDefMod()) / 100;
		
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
