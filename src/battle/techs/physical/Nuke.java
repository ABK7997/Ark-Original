package battle.techs.physical;

import java.awt.Color;
import java.awt.Graphics;

import battle.Spell;
import characters.Playable;
import characters.Playable.STATE;
import entity.mobs.enemies.Enemy;
import entity.mobs.enemies.Enemy.STATES;

public class Nuke extends Spell {
	
	public Nuke(Playable p) {
		this.p = p;
		name = "Nuke";
		cost = 18;
		dmg = p.getPwr()*2;
		type = "Offensive";
		range = "All";
		description = "Hit all enemies";
	}
	
	public void attack(Enemy e) {
		this.e = e;
		
		x = w/2;
		y = h/6;
		
		p.setEP(-cost);
		animating = true;
	}
	
	public void animate(Graphics g) {
		anim++;
		
		g.setColor(new Color(0xffFF9400));
		if (anim < 32);
		else if (anim < 82) {
			g.fillOval(x, y, 100, 100);
		}
		else if (anim < 132) {
			g.fillOval(x, y, anim*4, anim*4);
		}
		if (anim == 120) {
			for (int i = 0; i < e.getParty().size(); i ++) {
				dmg = p.getPwr() * 2;
				dmg = ((dmg/e.getParty().get(i).getDef()) * e.getParty().get(i).getDefMod()) / 100;
				e.getParty().get(i).setHP(-dmg);
				e.getParty().get(i).setDP(dmg);
				e.getParty().get(i).changeState(STATES.HIT);
			}
		}
		if (anim > 132) {
			animating = false;
			anim = 0;
			x = w/2;
			y = h/2;
		}
	}
	
	public Nuke(Enemy e) {
		this.e = e;
		cost = 5;
		dmg = e.getPwr();
		type = "Offensive";
	}
	
	public void attack(Playable p) {
		this.p = p;
		
		x = w/2;
		y = h/6;
		
		dmg /= p.getDef();
		
		e.setEP(-cost);
		animating = true;
	}
	
	public void animate2(Graphics g) {
		anim++;
		g.setColor(new Color(0xffFF9400));
		if (anim < 32);
		else if (anim < 82) {
			g.fillOval(x, y, 100, 100);
		}
		else if (anim < 132) {
			g.fillOval(x, y, anim*2, anim*2);
		}
		if (anim == 120) {
			for (int i = 0; i < e.getParty().size(); i ++) {
				dmg = e.getPwr() * 2;
				dmg = ((dmg/p.getParty().get(i).getDef()) * p.getParty().get(i).getDefMod()) / 100;
				p.getParty().get(i).setHP(-dmg);
				p.getParty().get(i).setDP(dmg);
				p.getParty().get(i).changeState(STATE.HIT);
			}
		}
		if (anim > 132) {
			animating = false;
			anim = 0;
			x = w/2;
			y = h/2;
		}
	}
	
}
