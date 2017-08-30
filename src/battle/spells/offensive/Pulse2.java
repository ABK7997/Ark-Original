package battle.spells.offensive;

import java.awt.Color;
import java.awt.Graphics;

import battle.Spell;
import characters.Playable;
import characters.Playable.STATE;
import entity.mobs.enemies.Enemy;
import entity.mobs.enemies.Enemy.STATES;

public class Pulse2 extends Spell {
	
	public Pulse2(Playable p) {
		this.p = p;
		x = w/2;
		y = 0;
		name = "Pulse+";
		cost = 50;
		dmg = p.getMag() * 7/2;
		type = "Offensive";
		range = "All";
		description = "Attack all enemies with large magic spheres";
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
			g.fillOval(x+90, y, 35, 35);
			g.fillOval(x+45, y, 35, 35);
			g.fillOval(x, y, 35, 35);
			g.fillOval(x-45, y, 35, 35);
			g.fillOval(x-90, y, 35, 35);
		}
		if (anim >= 82) {
			if (anim == 100) {
				for (int i = 0; i < e.getParty().size(); i++) {
					dmg = p.getMag() * 7/2;
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
	
	public Pulse2(Enemy e) {
		this.e = e;
		x = w/2;
		y = 0;
		cost = 50;
		dmg = e.getMag() * 7/2;
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
			g.fillOval(x+90, y, 35, 35);
			g.fillOval(x+45, y, 35, 35);
			g.fillOval(x, y, 35, 35);
			g.fillOval(x-45, y, 35, 35);
			g.fillOval(x-90, y, 35, 35);
		}
		if (anim >= 82) {
			if (anim == 100) {
				for (int i = 0; i < p.getParty().size(); i++) {
					dmg = p.getMag() * 7/2;
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
