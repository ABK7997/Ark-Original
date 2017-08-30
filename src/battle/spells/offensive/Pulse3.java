package battle.spells.offensive;

import java.awt.Color;
import java.awt.Graphics;

import battle.Spell;
import characters.Playable;
import characters.Playable.STATE;
import entity.mobs.enemies.Enemy;
import entity.mobs.enemies.Enemy.STATES;

public class Pulse3 extends Spell {
	
	public Pulse3(Playable p) {
		this.p = p;
		x = w/2;
		y = 0;
		name = "Pulse++";
		cost = 100;
		dmg = p.getMag() * 9;
		type = "Offensive";
		range = "All";
		description = "Attack all enemies with massive magic spheres";
	}
	
	public void attack(Enemy e) {
		this.e = e;
		
		p.setMP(-cost);
		animating = true;
	}
	
	public void animate(Graphics g) {
		anim++;
		g.setColor(Color.BLUE);
		
		if (anim >= 32) {
			g.fillOval(x+130, y, 50, 50);
			g.fillOval(x+65, y, 50, 50);
			g.fillOval(x, y, 50, 50);
			g.fillOval(x-65, y, 50, 50);
			g.fillOval(x-130, y, 50, 50);
		}
		if (anim >= 82) {
			if (anim == 100) {
				for (int i = 0; i < e.getParty().size(); i++) {
					dmg = p.getMag() * 9;
					dmg = ((dmg / e.getParty().get(i).getMagDef()) * e.getParty().get(i).getMagMod()) / 100;
					e.getParty().get(i).setHP(-dmg);
					e.getParty().get(i).setDP(dmg);
					e.getParty().get(i).changeState(STATES.HIT);
				}
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
	
	public Pulse3(Enemy e) {
		this.e = e;
		x = w/2;
		y = 0;
		cost = 100;
		dmg = e.getMag() * 9;
		type = "Offensive";
	}
	
	public void attack(Playable p) {
		this.p = p;
		
		e.setMP(-cost);
		animating = true;
	}
	
	public void animate2(Graphics g) {
		anim++;
		g.setColor(Color.BLUE);
		
		if (anim >= 32) {
			g.fillOval(x+130, y, 50, 50);
			g.fillOval(x+65, y, 50, 50);
			g.fillOval(x, y, 50, 50);
			g.fillOval(x-65, y, 50, 50);
			g.fillOval(x-130, y, 50, 50);
		}
		if (anim >= 82) {
			if (anim == 100) {
				for (int i = 0; i < p.getParty().size(); i++) {
					dmg = p.getMag() * 9;
					dmg = ((dmg / p.getParty().get(i).getMagDef()) * p.getParty().get(i).getMagMod()) / 100;
					p.getParty().get(i).setHP(-dmg);
					p.getParty().get(i).setDP(dmg);
					p.getParty().get(i).changeState(STATE.HIT);
				}
			}
			x -= 8; y += 10;
			if (x > w) {
				x = w / 2;
				y = 0;
				animating = false;
				anim = 0;
			}
		}
	}
	
}
