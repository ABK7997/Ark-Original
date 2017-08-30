package battle.spells.offensive;

import java.awt.Color;
import java.awt.Graphics;

import battle.Spell;
import characters.Playable;
import characters.Playable.STATE;
import entity.mobs.enemies.Enemy;
import entity.mobs.enemies.Enemy.STATES;

public class Bolt extends Spell {

	public Bolt(Playable p) {
		this.p = p;
		x = w/2;
		y = 0;
		name = "Bolt";
		cost = 5;
		dmg = p.getMag() * 4;
		type = "Offensive";
		description = "Attack one enemy with a magical sphere";
	}
	
	public void attack(Enemy e) {
		this.e = e;
		
		dmg = p.getMag() * 4;
		dmg = ((dmg / e.getMagDef()) * e.getMagMod()) / 100;
		
		p.setMP(-cost);
		animating = true;
	}
	
	public void animate(Graphics g) {
		anim++;
		g.setColor(Color.BLUE);
		
		if (anim >= 32) g.fillOval(x, y, 20, 20);
		if (anim >= 82) {
			if (anim == 100) {
				e.setHP(-dmg);
				e.setDP(dmg);
				e.changeState(STATES.HIT);
			}
			x += 8; y += 10;
			if (x > w) {
				x = w / 2;
				y = 0;
				animating = false;
				anim = 0;
			}
		}
	}
	
	public Bolt(Enemy e) {
		this.e = e;
		x = w/2;
		y = 0;
		cost = 5;
		dmg = e.getMag() * 4;
		type = "Offensive";
	}
	
	public void attack(Playable p) {
		this.p = p;
		
		x = w/2;
		y = 0;
		
		dmg = e.getMag() * 4;
		dmg = ((dmg / p.getMagDef() * p.getMagMod())) / 100;
		
		e.setMP(-cost);
		animating = true;
	}
	
	public void animate2(Graphics g) {
		anim++;
		g.setColor(Color.BLUE);
		
		if (anim >= 32) g.fillOval(x, y, 20, 20);
		if (anim >= 82) {
			if (anim == 100) {
				p.setHP(-dmg);
				p.setDP(dmg);
				p.changeState(STATE.HIT);
			}
			x -= 8; y += 10;
			if (x < 0) {
				animating = false;
				anim = 0;
			}
		}
	}
	
}
