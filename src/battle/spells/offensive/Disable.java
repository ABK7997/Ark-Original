package battle.spells.offensive;

import java.awt.Color;
import java.awt.Graphics;

import battle.Spell;
import characters.Playable;
import entity.mobs.enemies.Enemy;

public class Disable extends Spell {
	
	public Disable(Playable p) {
		this.p = p;
		x = 0;
		y = 0;
		name = "Disable";
		cost = 65;
		dmg = 145;
		type = "Offensive";
		description = "Greatly reduce an enemy's magic defense";
	}
	
	public void attack(Enemy e) {
		this.e = e;
		
		x = e.getX()*2;
		y = e.getY()*2;
		
		e.setMagMod(dmg);
		e.setMagModTimer(7);
		
		p.setMP(-cost);
		animating = true;
	}
	
	public void animate(Graphics g) {
		anim++;
		g.setColor(new Color(0xff0094FF));
		
		if (anim < 32);
		else if (anim < 132) {
			g.fillOval(x, y, 120-anim, 120-anim);
		}
		else {
			anim = 0;
			animating = false;
		}
	}
	
	public Disable(Enemy e) {
		this.e = e;
		x = 0;
		y = 0;
		cost = 25;
		type = "Offensive";
	}
	
	public void attack(Playable p) {
		this.p = p;
		
		x = (p.getX()) * 2;
		y = (p.getY()) * 2;
		
		p.setMagMod(dmg);
		p.setMagModTimer(7);
		
		e.setMP(-cost);
		animating = true;
	}
	
	public void animate2(Graphics g) {
		anim++;
		g.setColor(new Color(0xff0094FF));
		
		if (anim < 32);
		if (anim < 132) {
			g.fillOval(x, y, 120-anim/3, 120-anim/3);
		}
		else {
			anim = 0;
			animating = false;
		}
	}
	
}
