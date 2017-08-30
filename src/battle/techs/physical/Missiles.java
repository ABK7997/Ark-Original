package battle.techs.physical;

import java.awt.Color;
import java.awt.Graphics;

import battle.Spell;
import characters.Playable;
import characters.Playable.STATE;
import entity.mobs.enemies.Enemy;
import entity.mobs.enemies.Enemy.STATES;

public class Missiles extends Spell {
	
	public Missiles(Playable p) {
		this.p = p;
		name = "Missiles";
		cost = 5;
		dmg = p.getPwr();
		type = "Offensive";
		range = "All";
		description = "Hit all enemies";
	}
	
	public void attack(Enemy e) {
		this.e = e;
		
		x = w/2;
		y = h/2 - 125;
		
		dmg = p.getPwr();
		dmg /= e.getDef();
		
		p.setEP(-cost);
		animating = true;
	}
	
	public void animate(Graphics g) {
		anim++;
		
		g.setColor(Color.GRAY);
		if (anim < 32);
		else if (anim < 82) {
			g.fillOval(x, y-200, 30, 30);
			g.fillOval(x, y-100, 30, 30);
			g.fillOval(x, y, 30, 30);
			g.fillOval(x, y+100, 30, 30);
			g.fillOval(x, y+200, 30, 30);
		}
		else if (anim < 132) {
			g.fillOval(x, y-200, 30, 30);
			g.fillOval(x, y-100, 30, 30);
			g.fillOval(x, y, 30, 30);
			g.fillOval(x, y+100, 30, 30);
			g.fillOval(x, y+200, 30, 30);
			x+=5;
		}
		else {
			animating = false;
			anim = 0;
			x = w/2;
			y = h/2;
		}
		if (anim == 110) {
			for (int i = 0; i < e.getParty().size(); i ++) {
				dmg = ((p.getPwr()/e.getParty().get(i).getDef()) * e.getParty().get(i).getDefMod()) / 100;
				e.getParty().get(i).setHP(-dmg);
				e.getParty().get(i).setDP(dmg);
				e.getParty().get(i).changeState(STATES.HIT);
			}
		}
	}
	
	public Missiles(Enemy e) {
		this.e = e;
		cost = 5;
		dmg = e.getPwr();
		type = "Offensive";
	}
	
	public void attack(Playable p) {
		this.p = p;
		
		x = w/2;
		y = h/3;
		
		dmg = e.getPwr();
		dmg /= p.getDef();
		
		e.setEP(-cost);
		animating = true;
	}
	
	public void animate2(Graphics g) {
		anim++;
		g.setColor(Color.GRAY);
		if (anim < 32);
		else if (anim < 82) {
			g.fillOval(x, y-200, 30, 30);
			g.fillOval(x, y-100, 30, 30);
			g.fillOval(x, y, 30, 30);
			g.fillOval(x, y+100, 30, 30);
			g.fillOval(x, y+200, 30, 30);
		}
		else if (anim < 150) {
			g.fillOval(x, y-200, 30, 30);
			g.fillOval(x, y-100, 30, 30);
			g.fillOval(x, y, 30, 30);
			g.fillOval(x, y+100, 30, 30);
			g.fillOval(x, y+200, 30, 30);
			x-=10;
		}
		else {
			animating = false;
			anim = 0;
		}
		if (anim == 110) {
			for (int i = 0; i < p.getParty().size(); i ++) {
				dmg = ((e.getPwr()/p.getParty().get(i).getDef()) *  p.getParty().get(i).getDefMod()) / 100;
				p.getParty().get(i).setHP(-dmg);
				p.getParty().get(i).setDP(dmg);
				p.getParty().get(i).changeState(STATE.HIT);
			}
		}
	}
	
}
