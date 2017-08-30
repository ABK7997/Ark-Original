package battle.spells.offensive;

import java.awt.Color;
import java.awt.Graphics;

import battle.Spell;
import characters.Playable;
import characters.Playable.STATE;
import entity.mobs.enemies.Enemy;
import entity.mobs.enemies.Enemy.STATES;

public class Burn extends Spell {

	private int eRes;
	private int res;
	
	public Burn(Playable p) {
		this.p = p;
		x = 0;
		y = 0;
		name = "Burn";
		cost = 15;
		dmg = p.getMag() * 3;
		type = "Offensive";
		description = "Strike one opponent with a flame; can cause Burn";
	}
	
	public void attack(Enemy e) {
		this.e = e;
		
		dmg = p.getMag() * 3;
		dmg = ((dmg / e.getMagDef()) * e.getMagMod()) / 100;
		eRes = e.getRes();
		
		x = e.getX()*2;
		y = e.getY()*2;
		
		p.setMP(-cost);
		animating = true;
	}
	
	public void animate(Graphics g) {
		anim++;
		g.setColor(Color.RED);
		
		if (anim >= 82) {
			g.fillOval(x, y, anim - 80, anim - 80);
			if (anim == 100) {
				int chance = random.nextInt(25);
				e.setHP(-dmg);
				e.setDP(dmg);
				e.changeState(STATES.HIT);
				if (chance + eRes < 30) {
					e.setBurned(true);
					e.setMessage("Burned");
				}
			}
		}
		if (anim >= 150){
			x = w / 2;
			y = 0;
			animating = false;
			anim = 0;
		}
	}
	
	public Burn(Enemy e) {
		this.e = e;
		x = 0;
		y = 0;
		cost = 15;
		dmg = e.getMag() * 3;
		type = "Offensive";
	}
	
	public void attack(Playable p) {
		this.p = p;
		
		dmg = e.getMag() * 3;
		dmg = ((dmg / p.getMagDef() * p.getMagMod())) / 100;
		res = e.getRes();
		
		x = (p.getX()) * 2;
		y = (p.getY()) * 2;
		
		e.setMP(-cost);
		animating = true;
	}
	
	public void animate2(Graphics g) {
		anim++;
		g.setColor(Color.RED);
		
		if (anim >= 82) {
			g.fillOval(x, y, anim - 80, anim - 80);
			if (anim == 100) {
				int chance = random.nextInt(25);
				p.setHP(-dmg);
				p.setDP(dmg);
				p.changeState(STATE.HIT);
				if (chance + res < 30) {
					p.setBurned(true);
					p.setMessage("Burned");
				}
			}
		}
		if (anim >= 130){
			x = 0;
			y = 0;
			animating = false;
			anim = 0;
		}
	}
	
}
