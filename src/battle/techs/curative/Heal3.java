package battle.techs.curative;

import java.awt.Color;
import java.awt.Graphics;

import battle.Spell;
import characters.Playable;
import entity.mobs.enemies.Enemy;

public class Heal3 extends Spell {

	public Heal3(Playable p) {
		this.p = p;
		name = "Heal++";
		cost = 10;
		dmg = p.getMag() * 20;
		type = "Curative";
		description = "Heal one party member for a large amount";
	}

	public void heal(Playable m) {
		this.friend = m;
		
		dmg = p.getMag() * 28;
		p.setEP(-cost);
		
		x = (m.getX()+64)*2;
		y = (m.getY()+5)*2;
		
		animating = true;
	}
	
	public void animate(Graphics g) {
		anim++;
		
		g.setColor(new Color(0xff8FFF3A));
		
		if (anim <= 32);
		else if (anim <= 82) {
			g.fillOval(x-10, y-32, 3 + (anim), 3 + (anim/2));
			g.fillOval(x+32, y+10, 15 + (anim), 15 + (anim/2));
			g.fillOval(x, y, 10 + (anim), 10 + (anim/2));
		}
		else if (anim <= 164) {
			g.fillOval(x-10, y-32, 45 - (anim/2), 45 - (anim/3));
			g.fillOval(x+32, y+10, 56 - (anim/2), 56 - (anim/3));
			g.fillOval(x, y, 51 - (anim/6), 51 - (anim/6));
			if (anim == 83) {
				if (friend.getHP() > 0) {
					friend.setHP(dmg);
					friend.setCP(dmg);
				}
			}
		}
		else {
			animating = false;
			anim = 0;
		}
		
		
	}
	
	public Heal3(Enemy e) {
		this.e = e;
		cost = 5;
		dmg = e.getMag() * 18;
		type = "Curative";
	}

	public void heal(Enemy m) {
		eFriend = m;
		
		e.setMP(-cost);
		animating = true;
		
		x = (m.getX()-32)*2;
		y = m.getY()*2;
	}
	
	public void animate2(Graphics g) {
		anim++;
		
		g.setColor(new Color(0xff8FFF3A));
		
		if (anim <= 32);
		else if (anim <= 82) {
			g.fillOval(x+10, y-32, 3 + (anim/2), 3 + (anim/3));
			g.fillOval(x-32, y+10, 15 + (anim/2), 15 + (anim/3));
			g.fillOval(x, y, 10 + (anim/2), 10 + (anim/3));
		}
		else if (anim <= 164) {
			g.fillOval(x+10, y-32, 45 - (anim/4), 45 - (anim/5));
			g.fillOval(x-32, y+10, 56 - (anim/4), 56 - (anim/5));
			g.fillOval(x, y, 51 - (anim/4), 51 - (anim/5));
			if (anim == 83) {
				if (eFriend.getHP() > 0) {
					eFriend.setHP(dmg);
					eFriend.setCP(dmg);
				}
			}
		}
		else {
			animating = false;
			anim = 0;
		}
	}
	
}
