package battle.spells.offensive;

import java.awt.Color;
import java.awt.Graphics;

import battle.Spell;
import characters.Playable;
import characters.Playable.STATE;
import entity.mobs.enemies.Enemy;
import entity.mobs.enemies.Enemy.STATES;

public class Bolt3 extends Spell {

	public Bolt3(Playable p) {
		this.p = p;
		x = w/2;
		y = 0;
		name = "Bolt++";
		cost = 50;
		dmg = p.getMag() * 25;
		type = "Offensive";
		description = "Attack one enemy with a massive magical sphere";
	}
	
	public void attack(Enemy e) {
		this.e = e;
		
		dmg = p.getMag() * 25;
		dmg = ((dmg / e.getMagDef()) * e.getMagMod()) / 100;
		
		p.setMP(-cost);
		animating = true;
	}
	
	public void animate(Graphics g) {
		anim++;
		g.setColor(Color.BLUE);
		
		if (anim >= 32) g.fillOval(x, y, 75, 75);
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
	
	public Bolt3(Enemy e) {
		this.e = e;
		x = w/2;
		y = 0;
		cost = 50;
		dmg = e.getMag() * 25;
		type = "Offensive";
	}
	
	public void attack(Playable p) {
		this.p = p;
		
		dmg = e.getMag() * 25;
		dmg = ((dmg / p.getMagDef() * p.getMagMod())) / 100;
		
		e.setMP(-cost);
		animating = true;
	}
	
	public void animate2(Graphics g) {
		anim++;
		g.setColor(Color.BLUE);
		
		if (anim >= 32) g.fillOval(x, y, 40, 40);
		if (anim >= 82) {
			x -= 8; y += 10;
			if (anim == 100) {
				p.setHP(-dmg);
				p.setDP(dmg);
				p.changeState(STATE.HIT);
			}
			if (x < w) {
				x = w / 2;
				y = 0;
				animating = false;
				anim = 0;
			}
		}
	}
	
}
