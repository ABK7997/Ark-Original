package battle.techs.curative;

import java.awt.Color;
import java.awt.Graphics;

import characters.Playable;
import entity.mobs.enemies.Enemy;
import battle.Spell;

public class AeroHeal3 extends Spell {

	public AeroHeal3(Playable p) {
		x = 0;
		y = 0;
		this.p = p;
		name = "AirHeal++";
		cost = 12;
		dmg = p.getMag() * 18;
		type = "Curative";
		range = "All";
		description = "Heal all party members for a large amount";
	}

	public void heal(Playable m) {
		p.setMP(-cost);
		
		dmg = p.getMag() * 24;
		
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
			g.fillOval(x, y, 85, 85);
			g.fillOval(x-150, y-64, 85, 85);
			g.fillOval(x+100, y-160, 85, 85);
			g.fillOval(x-100, y-300, 85, 85);
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
	
	public AeroHeal3(Enemy e) {
		this.e = e;
		cost = 8;
		dmg = e.getMag();
		type = "Curative";
	}

	public void heal(Enemy m) {
		e.setMP(-cost);
		
		dmg = e.getMag() * 7;
		
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
			g.fillOval(x, y, 85, 85);
			g.fillOval(x-150, y-64, 85, 85);
			g.fillOval(x+100, y-160, 85, 85);
			g.fillOval(x-100, y-300, 85, 85);
			
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
