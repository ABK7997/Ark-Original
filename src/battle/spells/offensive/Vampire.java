package battle.spells.offensive;

import java.awt.Color;
import java.awt.Graphics;

import battle.Spell;
import characters.Playable;
import entity.mobs.enemies.Enemy;

public class Vampire extends Spell {

	public Vampire(Playable p) {
		this.p = p;
		x = 0;
		y = 0;
		name = "Vampire";
		cost = 30;
		dmg = p.getMaxHP() / 3;
		type = "Offensive";
		description = "Drain HP from an enemy";
	}
	
	public void attack(Enemy e) {
		this.e = e;
		
		dmg = p.getMaxHP() / 3;
		dmg = (dmg * e.getMagMod()) / 100;
		
		x = e.getX()*2;
		y = e.getY()*2;
		
		p.setMP(-cost);
		animating = true;
	}
	
	public void animate(Graphics g) {
		anim++;
		g.setColor(Color.RED);
		
		if (anim < 32);
		else if (anim < 82) {
			g.fillOval(x, y, anim/2, anim/2);
			if (anim == 80) {
				e.setHP(-dmg);
				e.setDP(dmg);
			}
		}
		else if (anim < 132) {
			if (anim == 130) {
				p.setHP(dmg);
				p.setCP(dmg);
			}
			g.fillOval(x, y, 20, 20);
			x-=5;
		}
		else {
			animating = false;
			anim = 0;
		}
		
	}
	
	public Vampire(Enemy e) {
		this.e = e;
		x = w/2;
		y = 0;
		cost = 5;
		dmg = e.getMaxMP()/8;
		type = "Offensive";
	}
	
	public void attack(Playable p) {
		this.p = p;
		
		dmg = e.getMaxHP() / 3;
		dmg = (dmg * p.getMagMod()) / 100;
		
		x = p.getX()*2;
		y = p.getY()*2;
		
		e.setMP(-cost);
		animating = true;
	}
	
	public void animate2(Graphics g) {
		anim++;
		g.setColor(Color.RED);
		
		if (anim >= 32) g.fillOval(x, y, 40, 40);
		if (anim < 32);
		else if (anim < 82) {
			g.fillOval(x, y, anim/2, anim/2);
			if (anim == 80) {
				p.setHP(-dmg);
				p.setDP(dmg);
			}
		}
		else if (anim < 132) {
			if (anim == 130) {
				e.setHP(dmg);
				e.setCP(dmg);
			}
			g.fillOval(x, y, 20, 20);
			x+=5;
		}
		
	}
	
}
