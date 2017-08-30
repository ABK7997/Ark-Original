package battle.spells.curative;

import java.awt.Color;
import java.awt.Graphics;

import battle.Spell;
import characters.Playable;
import entity.mobs.enemies.Enemy;

public class Aura2 extends Spell {

	public Aura2(Playable p) {
		this.p = p;
		name = "Aura+";
		cost = 20;
		dmg = p.getMag() * 16;
		type = "Curative";
		description = "Heal one party member for a moderate amount";
	}

	public void heal(Playable m) {
		this.friend = m;
		
		p.setMP(-cost);
		
		dmg = p.getMag() * 16;
		x = (m.getX()+64)*2;
		y = (m.getY()+5)*2;
		
		animating = true;
	}
	
	public void animate(Graphics g) {
		anim++;
		
		g.setColor(new Color(0xff00AAAA));
		
		if (anim <= 32);
		else if (anim <= 82) {
			g.fillOval(x-10, y-32, 3 + (anim/3), 3 + (anim/3));
			g.fillOval(x+32, y+10, 15 + (anim/3), 15 + (anim/3));
			g.fillOval(x, y, 10 + (anim/3), 10 + (anim/3));
		}
		else if (anim <= 164) {
			g.fillOval(x-10, y-32, 45 - (anim/6), 45 - (anim/6));
			g.fillOval(x+32, y+10, 56 - (anim/6), 56 - (anim/6));
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
	
	public Aura2(Enemy e) {
		this.e = e;
		cost = 20;
		dmg = e.getMag() * 10;
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
		
		g.setColor(new Color(0xff00AAAA));
		
		if (anim <= 32);
		else if (anim <= 82) {
			g.fillOval(x+10, y-32, 3 + (anim/3), 3 + (anim/3));
			g.fillOval(x-32, y+10, 15 + (anim/3), 15 + (anim/3));
			g.fillOval(x, y, 10 + (anim/3), 10 + (anim/3));
		}
		else if (anim <= 164) {
			g.fillOval(x+10, y-32, 45 - (anim/6), 45 - (anim/6));
			g.fillOval(x-32, y+10, 56 - (anim/6), 56 - (anim/6));
			g.fillOval(x, y, 51 - (anim/6), 51 - (anim/6));
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
