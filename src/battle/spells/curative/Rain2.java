package battle.spells.curative;

import java.awt.Color;
import java.awt.Graphics;

import characters.Playable;
import entity.mobs.enemies.Enemy;
import battle.Spell;

public class Rain2 extends Spell {

	public Rain2(Playable p) {
		x = 0;
		y = 0;
		this.p = p;
		name = "Rain+";
		cost = 40;
		dmg = p.getMag() * 13;
		type = "Curative";
		range = "All";
		description = "Heal all party members for a moderate amount";
	}

	public void heal(Playable m) {
		p.setMP(-cost);
		
		dmg = p.getMag() * 13;
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
			g.fillOval(x, y, 40, 40);
			g.fillOval(x+100, y-64, 40, 40);
			g.fillOval(x-50, y-160, 40, 40);
			g.fillOval(x+50, y-300, 40, 40);
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
	
	public Rain2(Enemy e) {
		this.e = e;
		cost = 40;
		dmg = e.getMag() * 7/2;
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
			g.fillOval(x, y, 40, 40);
			g.fillOval(x-128, y-64, 40, 40);
			g.fillOval(x+64, y-160, 40, 40);
			g.fillOval(x-64, y-300, 40, 40);
			
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
