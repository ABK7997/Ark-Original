package battle.techs.curative;

import java.awt.Color;
import java.awt.Graphics;

import characters.Playable;
import entity.mobs.enemies.Enemy;
import battle.Spell;

public class AeroHeal2 extends Spell {

	public AeroHeal2(Playable p) {
		x = 0;
		y = 0;
		this.p = p;
		name = "AirHeal+";
		cost = 8;
		dmg = p.getMag() * 12;
		type = "Curative";
		range = "All";
		description = "Heal all party members for a moderate amount";
	}

	public void heal(Playable m) {
		p.setMP(-cost);
		
		dmg = p.getMag() * 16;
		
		x = p.getX()*2;
		y = 0;

		animating = true;
	}
	
	public void animate(Graphics g) {
		anim++;
		g.setColor(Color.WHITE);
		
		if (anim <= 82);
		else {
			y+=5;
			g.fillOval(x, y, 40, 40);
			g.fillOval(x-100, y-64, 40, 40);
			g.fillOval(x+50, y-160, 40, 40);
			g.fillOval(x-50, y-300, 40, 40);
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
	
	public AeroHeal2(Enemy e) {
		this.e = e;
		cost = 8;
		dmg = e.getMag();
		type = "Curative";
	}

	public void heal(Enemy m) {
		e.setMP(-cost);
		
		dmg = e.getMag() * 5/2;
		
		x = e.getX()*2;
		y = 0;

		animating = true;
	}
	
	public void animate2(Graphics g) {
		anim++;
		g.setColor(Color.WHITE);
		
		if (anim <= 82);
		else if (anim <= 164) {
			y+=5;
			g.fillOval(x, y, 40, 40);
			g.fillOval(x-100, y-64, 40, 40);
			g.fillOval(x+50, y-160, 40, 40);
			g.fillOval(x-50, y-300, 40, 40);
			
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
