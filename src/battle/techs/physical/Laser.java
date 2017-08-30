package battle.techs.physical;

import java.awt.Color;
import java.awt.Graphics;

import battle.Spell;
import characters.Playable;
import characters.Playable.STATE;
import entity.mobs.enemies.Enemy;
import entity.mobs.enemies.Enemy.STATES;

public class Laser extends Spell {

	public Laser(Playable p) {
		this.p = p;
		x = 0;
		y = 0;
		name = "Laser";
		cost = 6;
		dmg = p.getPwr() * 4;
		type = "Offensive";
		description = "Hit a single enemy with a powerful laser";
	}
	
	public void attack(Enemy e) {
		this.e = e;
		
		dmg = p.getPwr() * 4;
		dmg = ((dmg / e.getDef()) * e.getDefMod()) / 100;
		
		x = (p.getX()+48)*2;
		y = (p.getY()+5)*2;
		
		p.setEP(-cost);
		animating = true;
	}
	
	public void animate(Graphics g) {
		anim++;
		
		g.setColor(Color.RED);
		
		if (anim < 32);
		else if (anim < 82) {
			g.fillOval(x, y, 20, 20);
		}
		else if (anim < 132) {
			g.fillOval(x, y, 400, 35);
		}
		else {
			animating = false;
			anim = 0;
		}
		
		if (anim == 83) {
			e.setHP(-dmg);
			e.setDP(dmg);
			e.changeState(STATES.HIT);
		}
	}
	
	public Laser(Enemy e) {
		this.e = e;
		cost = 6;
		dmg = e.getPwr() * 4;
		type = "Offensive";
	}
	
	public void attack(Playable p) {
		this.p = p;
		
		dmg = e.getPwr() * 4;
		dmg = ((dmg / p.getDef()) * p.getDefMod()) / 100;
		
		x = (e.getX()-32)*2;
		y = (e.getY()+5)*2;
		
		e.setEP(-cost);
		animating = true;
	}
	
	public void animate2(Graphics g) {
		anim++;
		
		g.setColor(Color.RED);
		
		if (anim < 32);
		else if (anim < 82) {
			g.fillOval(x, y, 20, 20);
		}
		else if (anim < 132) {
			g.fillOval(x-400, y, 400, 35);
		}
		
		if (anim == 83) {
			e.setHP(-dmg);
			e.setDP(dmg);
			e.changeState(STATES.HIT);
		}
		if (anim > 130) {
			animating = false;
			anim = 0;
		}
		else {
			animating = false;
			anim = 0;
		}
		
		if (anim == 83) {
			p.setHP(-dmg);
			p.setDP(dmg);
			p.changeState(STATE.HIT);
		}
	}
	
}