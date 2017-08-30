package battle.techs.magic;

import java.awt.Color;
import java.awt.Graphics;

import battle.Spell;
import characters.Playable;
import characters.Playable.STATE;
import entity.mobs.enemies.Enemy;
import entity.mobs.enemies.Enemy.STATES;

public class Partial_Implosion extends Spell {
	
	public Partial_Implosion(Playable p) {
		this.p = p;
		name = "Partial Implosion";
		cost = 15;
		dmg = p.getMag()*25;
		type = "Offensive";
		range = "All";
		description = "Hits all enemies, but drains 1/2 of the user's HP";
	}
	
	public void attack(Enemy e) {
		this.e = e;
		
		x = p.getX()*2;
		y = p.getY()*2;
		
		p.setEP(-cost);
		animating = true;
	}
	
	public void animate(Graphics g) {
		anim++;
		
		g.setColor(new Color(0xff00137F));
		if (anim < 32);
		else if (anim < 82) {
			g.fillOval(x, y, 750-(anim*8), 750-(anim*8));
		}
		else if (anim < 132) {
			g.fillOval(x, y, anim*3, anim*3);
			x+=6;
			y-=2;
		}
		else {
			p.setHP(-p.getMaxHP()/2);
			animating = false;
			anim = 0;
		}
		if (anim == 120) {
			for (int i = 0; i < e.getParty().size(); i ++) {
				dmg = p.getMag() * 25;
				dmg = ((dmg/e.getParty().get(i).getMagDef()) * e.getParty().get(i).getMagMod()) / 100;
				e.getParty().get(i).setHP(-dmg);
				e.getParty().get(i).setDP(dmg);
				e.getParty().get(i).changeState(STATES.HIT);
			}
		}
	}
	
	public Partial_Implosion(Enemy e) {
		this.e = e;
		cost = 15;
		dmg = e.getMag()*25;
		type = "Offensive";
	}
	
	public void attack(Playable p) {
		this.p = p;
		
		x = e.getX()*2;
		y = e.getY()*2;
		
		dmg /= p.getDef();
		
		e.setEP(-cost);
		animating = true;
	}
	
	public void animate2(Graphics g) {
		anim++;
		g.setColor(new Color(0xff00137F));
		if (anim < 32);
		else if (anim < 82) {
			g.fillOval(x, y, 750-(anim*8), 750-(anim*8));
		}
		else if (anim < 132) {
			g.fillOval(x, y, anim*3, anim*3);
			x-=6;
			y-=2;
		}
		else {
			e.setHP(-e.getMaxHP()/2);
			animating = false;
			anim = 0;
		}
		if (anim == 120) {
			for (int i = 0; i < e.getParty().size(); i ++) {
				dmg = e.getMag() * 25;
				dmg = ((dmg/p.getParty().get(i).getMagDef()) * p.getParty().get(i).getMagMod()) / 100;
				p.getParty().get(i).setHP(-dmg);
				p.getParty().get(i).setDP(dmg);
				p.getParty().get(i).changeState(STATE.HIT);
			}
		}
	}
	
}
