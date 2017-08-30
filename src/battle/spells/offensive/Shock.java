package battle.spells.offensive;

import java.awt.Color;
import java.awt.Graphics;

import battle.Spell;
import characters.Playable;
import characters.Playable.STATE;
import entity.mobs.enemies.Enemy;
import entity.mobs.enemies.Enemy.STATES;

public class Shock extends Spell {

	private int eRes;
	private int res;
	
	public Shock(Playable p) {
		this.p = p;
		x = w/2;
		y = 0;
		name = "Shock";
		cost = 10;
		dmg = p.getMag() * 3;
		type = "Offensive";
		description = "Strike one opponent with electricity; can cause Paralysis";
	}
	
	public void attack(Enemy e) {
		this.e = e;
		
		dmg = p.getMag() * 3;
		dmg = (((dmg / e.getMagDef())) * e.getMagMod()) / 100;
		eRes = e.getRes();
		
		p.setMP(-cost);
		animating = true;
	}
	
	public void animate(Graphics g) {
		anim++;
		g.setColor(Color.YELLOW);
		
		if (anim >= 32) {
			g.fillOval(x-40, y-40, 20, 20);
			g.fillOval(x-40, y-40, 20, 20);
			g.fillOval(x, y, 20, 20);
		}
		if (anim >= 82) {
			if (anim == 100) {
				int chance = random.nextInt(25);
				e.setHP(-dmg);
				e.setDP(dmg);
				e.changeState(STATES.HIT);
				if (chance + eRes < 30) {
					e.setParalyzed(true);
					e.setMessage("Paralyzed");
				}
			}
			x += 8; y += 10;
			if (x > w) {
				x = w / 2;
				y = 0;
				animating = false;
				anim = 0;
			}
		}
	}
	
	public Shock(Enemy e) {
		this.e = e;
		x = w/2;
		y = 0;
		cost = 10;
		dmg = e.getMag() * 3;
		type = "Offensive";
	}
	
	public void attack(Playable p) {
		this.p = p;
		
		dmg = e.getMag() * 3;
		dmg = (((dmg / p.getMagDef()) * p.getMagMod())) / 100;
		res = p.getRes();
		
		e.setMP(-cost);
		animating = true;
	}
	
	public void animate2(Graphics g) {
		anim++;
		g.setColor(Color.YELLOW);
		
		if (anim >= 32) {
			g.fillOval(x+40, y-40, 20, 20);
			g.fillOval(x+40, y-40, 20, 20);
			g.fillOval(x, y, 20, 20);
		}
		if (anim >= 82) {
			if (anim == 100) {
				int chance = random.nextInt(25);
				p.setHP(-dmg);
				p.setDP(dmg);
				p.changeState(STATE.HIT);
				if (chance + res < 30) {
					p.setParalyzed(true);
					p.setMessage("Paralyzed");
				}
			}
			x -= 8; y += 10;
		}
		if (anim > 132) {
			x = w / 2;
			y = 0;
			animating = false;
			anim = 0;
		}
	}
	
}
