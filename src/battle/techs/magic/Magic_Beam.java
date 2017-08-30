package battle.techs.magic;

import java.awt.Color;
import java.awt.Graphics;

import battle.Spell;
import characters.Playable;
import characters.Playable.STATE;
import entity.mobs.enemies.Enemy;
import entity.mobs.enemies.Enemy.STATES;

public class Magic_Beam extends Spell {

	public Magic_Beam(Playable p) {
		this.p = p;
		x = 0;
		y = 0;
		name = "Magic Beam";
		cost = 20;
		dmg = p.getMag() * 28;
		type = "Offensive";
		description = "Hit a single enemy with a massive magic blast";
	}
	
	public void attack(Enemy e) {
		this.e = e;
		
		dmg = ((dmg / e.getMagDef()) * e.getMagMod()) / 100;
		
		x = (p.getX()+48)*2;
		y = (p.getY()+5)*2;
		
		p.setEP(-cost);
		animating = true;
	}
	
	public void animate(Graphics g) {
		anim++;
		
		g.setColor(new Color(0xff00FFFF));
		
		if (anim < 32);
		else if (anim < 82) {
			g.fillOval(x, y, 40, 40);
		}
		else if (anim < 132) {
			g.fillOval(x, y, 400, 70);
		}
		else {
			animating = false;
			anim = 0;
		}
		
		if (anim == 83) {
			e.setHP(-dmg);
			e.setDP(dmg);
			e.changeState(STATES.HIT);
		}
	}
	
	public Magic_Beam(Enemy e) {
		this.e = e;
		cost = 20;
		dmg = e.getMag() * 28;
		type = "Offensive";
	}
	
	public void attack(Playable p) {
		this.p = p;
		
		dmg = p.getMag()*28;
		dmg = ((dmg / p.getMagDef()) * p.getMagMod()) / 100;
		
		x = (e.getX()-32)*2;
		y = (e.getY()+5)*2;
		
		e.setEP(-cost);
		animating = true;
	}
	
	public void animate2(Graphics g) {
		anim++;
		
		g.setColor(new Color(0xff00FFFF));
		
		if (anim < 32);
		else if (anim < 82) {
			g.fillOval(x, y, 40, 40);
		}
		else if (anim < 132) {
			g.fillOval(x-400, y, 400, 70);
		}
		
		if (anim == 83) {
			e.setHP(-dmg);
			e.setDP(dmg);
			e.changeState(STATES.HIT);
		}
		if (anim > 130) {
			animating = false;
			anim = 0;
		}
		else {
			animating = false;
			anim = 0;
		}
		
		if (anim == 83) {
			p.setHP(-dmg);
			p.setDP(dmg);
			p.changeState(STATE.HIT);
		}
	}
	
}
