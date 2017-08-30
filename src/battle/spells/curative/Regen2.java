package battle.spells.curative;

import java.awt.Color;
import java.awt.Graphics;

import battle.Spell;
import characters.Playable;
import entity.mobs.enemies.Enemy;

public class Regen2 extends Spell {

	public Regen2(Playable p) {
		this.p = p;
		name = "Regen++";
		cost = 50;
		dmg = p.getMag() * 2;
		type = "Curative";
		description = "Heal one party member for a large amount over time";
	}

	public void heal(Playable m) {
		this.friend = m;
		
		p.setMP(-cost);
		dmg = p.getMag() * 2;
		m.setRegen(dmg);
		m.setRegenTimer(12);
		
		x = (m.getX()+64)*2;
		y = (m.getY()+5)*2;
		
		animating = true;
	}
	
	public void animate(Graphics g) {
		anim++;
		
		g.setColor(Color.BLUE);
		
		if (anim <= 32);
		else if (anim <= 82) {
			g.fillOval(x-10, y-32, 3 + (anim/3), 3 + (anim/3));
			g.fillOval(x+32, y+10, 15 + (anim/3), 15 + (anim/3));
			g.fillOval(x, y, 10 + (anim/3), 10 + (anim/3));
		}
		else if (anim <= 164) {
			if (anim == 84) {
				friend.setMessage("Regenerating");
			}
			g.fillOval(x-10, y-32, 45 - (anim/6), 45 - (anim/4));
			g.fillOval(x+32, y+10, 56 - (anim/6), 56 - (anim/4));
			g.fillOval(x, y, 51 - (anim/6), 51 - (anim/4));
		}
		else {
			animating = false;
			anim = 0;
		}
		
		
	}
	
	public Regen2(Enemy e) {
		this.e = e;
		cost = 5;
		dmg = e.getMag() * 2;
		type = "Curative";
	}

	public void heal(Enemy m) {
		eFriend = m;
		
		e.setMP(-cost);
		animating = true;
		
		x = (m.getX()-32)*2;
		y = m.getY()*2;
		
		m.setRegen(dmg);
		m.setRegenTimer(12);
	}
	
	public void animate2(Graphics g) {
		anim++;
		
		g.setColor(Color.BLUE);
		
		if (anim <= 32);
		else if (anim <= 82) {
			g.fillOval(x+10, y-32, 3 + (anim/3), 3 + (anim/3));
			g.fillOval(x-32, y+10, 15 + (anim/3), 15 + (anim/3));
			g.fillOval(x, y, 10 + (anim/3), 10 + (anim/3));
		}
		else if (anim <= 164) {
			if (anim == 84) {
				eFriend.setMessage("Regenerating");
			}
			
			g.fillOval(x+10, y-32, 45 - (anim/6), 45 - (anim/4));
			g.fillOval(x-32, y+10, 56 - (anim/6), 56 - (anim/4));
			g.fillOval(x, y, 51 - (anim/6), 51 - (anim/4));
		}
		else {
			animating = false;
			anim = 0;
		}
	}
	
}
