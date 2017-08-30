package battle.spells.offensive;

import java.awt.Color;
import java.awt.Graphics;

import battle.Spell;
import characters.Playable;
import entity.mobs.enemies.Enemy;

public class Weaken extends Spell {
	
	public Weaken(Playable p) {
		this.p = p;
		x = 0;
		y = 0;
		name = "Weaken";
		cost = 25;
		dmg = 120;
		type = "Offensive";
		description = "Reduce an enemy's physical defense";
	}
	
	public void attack(Enemy e) {
		this.e = e;
		
		x = e.getX()*2;
		y = e.getY()*2;
		
		e.setDefMod(dmg);
		e.setDefModTimer(10);
		
		p.setMP(-cost);
		animating = true;
	}
	
	public void animate(Graphics g) {
		anim++;
		g.setColor(Color.GRAY);
		
		if (anim < 32);
		else if (anim < 132) {
			g.fillOval(x, y, 80-anim/2, 80-anim/2);
		}
		else {
			anim = 0;
			animating = false;
		}
	}
	
	public Weaken(Enemy e) {
		this.e = e;
		x = 0;
		y = 0;
		cost = 25;
		dmg = 120;
		type = "Offensive";
	}
	
	public void attack(Playable p) {
		this.p = p;
		
		x = (p.getX()) * 2;
		y = (p.getY()) * 2;
		
		p.setDefMod(dmg);
		p.setDefModTimer(10);
		
		e.setMP(-cost);
		animating = true;
	}
	
	public void animate2(Graphics g) {
		anim++;
		g.setColor(Color.GRAY);
		
		if (anim < 32);
		if (anim < 132) {
			g.fillOval(x, y, 80-anim/2, 80-anim/2);
		}
		else {
			anim = 0;
			animating = false;
		}
	}
	
}
