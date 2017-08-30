package battle.spells.curative;

import java.awt.Color;
import java.awt.Graphics;

import characters.Playable;
import entity.mobs.enemies.Enemy;
import battle.Spell;

public class Rain extends Spell {

	public Rain(Playable p) {
		x = 0;
		y = 0;
		this.p = p;
		name = "Rain";
		cost = 15;
		dmg = p.getMag() * 6;
		type = "Curative";
		range = "All";
		description = "Heal all party members for a small amount";
	}

	public void heal(Playable m) {
		p.setMP(-cost);
		
		dmg = p.getMag() * 6;
		x = p.getX()*2;
		y = 0;

		animating = true;
		anim = 0;
	}
	
	public void animate(Graphics g) {
		anim++;
		g.setColor(new Color(0xff00AAAA));
		
		if (anim <= 82);
		else {
			y+=5;
			g.fillOval(x, y, 15, 15);
			g.fillOval(x-64, y-64, 15, 15);
			g.fillOval(x+32, y-160, 15, 15);
			g.fillOval(x-32, y-300, 15, 15);
			if (anim == 130) {
				for (int i = 0; i < p.getParty().size(); i++) {
					Playable f = p.getParty().get(i);
					if (f.getHP() > 0) {
						f.setHP(dmg);
						f.setCP(dmg);
					}
				}
			}
		}
		if (y > h) {
			animating = false;
		}
	}
	
	public Rain(Enemy e) {
		this.e = e;
		cost = 15;
		dmg = e.getMag() * 3/2;
		type = "Curative";
	}

	public void heal(Enemy m) {
		e.setMP(-cost);
		
		x = e.getX()*2;
		y = 0;

		animating = true;
	}
	
	public void animate2(Graphics g) {
		anim++;
		g.setColor(new Color(0xff00AAAA));
		
		if (anim <= 82);
		else if (anim <= 164) {
			y+=5;
			g.fillOval(x, y, 20, 20);
			g.fillOval(x-64, y-64, 20, 20);
			g.fillOval(x+32, y-160, 20, 20);
			g.fillOval(x-32, y-300, 20, 20);
			
			if (anim == 130) {
				for (int i = 0; i < e.getParty().size(); i++) {
					Enemy f = e.getParty().get(i);
					if (f.getHP() > 0) {
						f.setHP(dmg);
						f.setCP(dmg);
					}
				}
			}
		}
		if (y > h) {
			animating = false;
		}
	}
	
}
