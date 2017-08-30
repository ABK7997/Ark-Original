package battle.techs.magic;

import java.awt.Color;
import java.awt.Graphics;

import battle.Spell;
import characters.Playable;
import characters.Playable.STATE;
import entity.mobs.enemies.Enemy;
import entity.mobs.enemies.Enemy.STATES;

public class Burst extends Spell {

	public Burst(Playable p) {
		this.p = p;
		x = w/2;
		y = 0;
		name = "Burst";
		cost = 2;
		dmg = p.getMag() * 3;
		type = "Offensive";
		description = "A short burst of low magic damage";
	}
	
	public void attack(Enemy e) {
		this.e = e;
		
		dmg = p.getMag() * 3;
		dmg = ((dmg / e.getMagDef()) * e.getMagMod()) / 100;
		
		p.setEP(-cost);
		animating = true;
		
		x = (e.getX()-128)*2;
		y = e.getY()*2;
	}
	
	public void animate(Graphics g) {
		anim++;
		g.setColor(new Color(0xff0088FF));
		
		if (anim <= 32);
		else if (anim <= 82) {
			g.fillOval((p.getX()+24)*2, p.getY()*2, 20, 20);
		}
		else if (anim <= 164) {
			g.fillOval(x, y, 20, 20);
			x+=10;
			if (anim == 120) {
				e.setHP(-dmg);
				e.setDP(dmg);
				e.changeState(STATES.HIT);
			}
		}
		else {
			anim = 0;
			animating = false;
		}
	}
	
	public Burst(Enemy e) {
		this.e = e;
		x = w/2;
		y = 0;
		cost = 5;
		dmg = e.getMag() * 3;
		type = "Offensive";
	}
	
	public void attack(Playable p) {
		this.p = p;
		
		dmg = e.getMag() * 3;
		dmg = ((dmg / p.getMagDef() * p.getMagMod())) / 100;
		
		x = p.getX() + 200;
		y = p.getY() - 10;
		
		e.setMP(-cost);
		animating = true;
	}
	
	public void animate2(Graphics g) {
		anim++;
		g.setColor(new Color(0xff0099FF));
		
		if (anim <= 32);
		else if (anim <= 82) {
			g.fillOval(e.getX()-32, e.getY(), 15, 15);
		}
		else if (anim <= 164) {
			g.fillOval(x, y, 15, 15);
			x-=5;
			if (anim == 120) {
				p.setHP(-dmg);
				p.setDP(dmg);
				p.changeState(STATE.HIT);
			}
		}
		else {
			anim = 0;
			animating = false;
		}
	}
	
}