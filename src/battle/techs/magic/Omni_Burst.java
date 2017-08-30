package battle.techs.magic;

import java.awt.Color;
import java.awt.Graphics;

import battle.Spell;
import characters.Playable;
import characters.Playable.STATE;
import entity.mobs.enemies.Enemy;
import entity.mobs.enemies.Enemy.STATES;

public class Omni_Burst extends Spell {

	private int x1, x2, x3, x4;
	private int y1, y2, y3, y4;
	
	public Omni_Burst(Playable p) {
		this.p = p;
		x = w/2;
		y = 0;
		name = "Omni-Burst";
		cost = 4;
		dmg = p.getMag() * 2;
		type = "Offensive";
		range = "All";
		description = "Hit all foes with low-magic bursts";
	}
	
	public void attack(Enemy e) {
		this.e = e;
		
		p.setEP(-cost);
		animating = true;
		
		x = w/2;
		y = h/3;
		x1 = x;
		y1 = y - 300;
		x2 = x;
		y2 = y - 150;
		x3 = x;
		y3 = y + 150;
		x4 = x;
		y4 = y + 300;
	}
	
	public void animate(Graphics g) {
		anim++;
		
		g.setColor(new Color(0xff0088FF));
		
		if (anim < 32);
		else if (anim < 82) {
			g.fillOval(x, y, 40, 40);
			g.fillOval(x1, y1, 40, 40);
			g.fillOval(x2, y2, 40, 40);
			g.fillOval(x3, y3, 40, 40);
			g.fillOval(x4, y4, 40, 40);
		}
		else if (anim < 164) {
			g.fillOval(x, y, 40, 40);
			g.fillOval(x1, y1, 40, 40);
			g.fillOval(x2, y2, 40, 40);
			g.fillOval(x3, y3, 40, 40);
			g.fillOval(x4, y4, 40, 40);
			x+=6;
			x1+=6;
			x2+=6;
			x3+=6;
			x4+=6;
			y1+=5;
			y2+=5;
			y3-=5;
			y4-=5;
		}
		else {
			animating = false;
			anim = 0;
		}
		
		if (anim == 132) {
			for (int i = 0; i < e.getParty().size(); i++) {
				dmg = p.getMag()*2;
				dmg = ((dmg / e.getParty().get(i).getMagDef()) * e.getParty().get(i).getMagMod()) / 100;
				e.getParty().get(i).setHP(-dmg);
				e.getParty().get(i).setDP(dmg);
				e.getParty().get(i).changeState(STATES.HIT);
			}
		}
	}
	
	public Omni_Burst(Enemy e) {
		this.e = e;
		x = w/2;
		y = 0;
		cost = 4;
		dmg = e.getMag() * 2;
		type = "Offensive";
	}
	
	public void attack(Playable p) {
		e.setMP(-cost);
		animating = true;
		
		x = w/3;
		y = h/3;
		x1 = x;
		y1 = y - 300;
		x2 = x;
		y2 = y - 150;
		x3 = x;
		y3 = y + 150;
		x4 = x;
		y4 = y + 300;
	}
	
	public void animate2(Graphics g) {
		anim++;
		
		g.setColor(new Color(0xff0088FF));
		
		if (anim < 32);
		else if (anim < 82) {
			g.fillOval(x, y, 40, 40);
			g.fillOval(x1, y1, 40, 40);
			g.fillOval(x2, y2, 40, 40);
			g.fillOval(x3, y3, 40, 40);
			g.fillOval(x4, y4, 40, 40);
		}
		else if (anim < 164) {
			g.fillOval(x, y, 40, 40);
			g.fillOval(x1, y1, 40, 40);
			g.fillOval(x2, y2, 40, 40);
			g.fillOval(x3, y3, 40, 40);
			g.fillOval(x4, y4, 40, 40);
			x-=6;
			x1-=6;
			x2-=6;
			x3-=6;
			x4-=6;
			y1+=5;
			y2+=5;
			y3-=5;
			y4-=5;
		}
		else {
			animating = false;
			anim = 0;
		}
		
		if (anim == 132) {
			for (int i = 0; i < p.getParty().size(); i++) {
				dmg = e.getMag()*2;
				dmg = (p.getParty().get(i).getMagDef() * p.getParty().get(i).getMagMod()) / 100;
				p.setHP(-dmg);
				p.setDP(dmg);
				p.changeState(STATE.HIT);
			}
		}
	}
	
}
