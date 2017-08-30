package battle.spells.offensive;

import java.awt.Color;
import java.awt.Graphics;

import battle.Spell;
import characters.Playable;
import characters.Playable.STATE;
import entity.mobs.enemies.Enemy;
import entity.mobs.enemies.Enemy.STATES;

public class Poison_Pin extends Spell {

	private int eRes;
	private int res;
	
	public Poison_Pin(Playable p) {
		this.p = p;
		x = 0;
		y = 0;
		name = "Poison Pin";
		cost = 10;
		dmg = p.getMag() * 3;
		type = "Offensive";
		description = "Shoot a toxic dart; can cause Poison";
	}
	
	public void attack(Enemy e) {
		this.e = e;
		
		dmg = p.getMag() * 3;
		dmg = ((dmg / e.getMagDef()) * e.getMagMod()) / 100;
		eRes = e.getRes();
		
		x = (e.getX()-64)*2;
		y = e.getY()*2;
		
		p.setMP(-cost);
		animating = true;
	}
	
	public void animate(Graphics g) {
		anim++;
		g.setColor(Color.GREEN);
		
		if (anim >= 82) {
			g.fillOval(x, y, 15, 15);
			if (anim == 100) {
				int chance = random.nextInt(25);
				e.setHP(-dmg);
				e.setDP(dmg);
				e.changeState(STATES.HIT);
				if (chance + eRes < 30) {
					if (p.getType() != "Machine" && p.getType() != "Cygic") {
						e.setPoisoned(true);
						e.setMessage("Poisoned");
					}
					else {
						e.setMessage("Cannot be Poisoned");
					}
				}
			}
			x+=3;
		}
		if (anim >= 135) {
			x = 0;
			y = 0;
			animating = false;
			anim = 0;
		}
	}
	
	public Poison_Pin(Enemy e) {
		this.e = e;
		x = 0;
		y = 0;
		cost = 10;
		dmg = e.getMag() * 3;
		type = "Offensive";
	}
	
	public void attack(Playable p) {
		this.p = p;
		
		dmg = e.getMag() * 3;
		dmg = ((dmg / p.getMagDef() * p.getMagMod())) / 100;
		res = e.getRes();
		
		x = (p.getX()+96)*2;
		y = p.getY()*2;
		
		e.setMP(-cost);
		animating = true;
	}
	
	public void animate2(Graphics g) {
		anim++;
		g.setColor(Color.GREEN);
		
		if (anim < 82);
		
		else if (anim < 132) {
			g.fillOval(x, y, 15, 15);
			if (anim == 100) {
				int chance = random.nextInt(25);
				p.setHP(-dmg);
				p.setDP(dmg);
				p.changeState(STATE.HIT);
				if (chance + res < 30) {
					if (p.getType() != "Machine" && p.getType() != "Cygic") {
						p.setPoisoned(true);
						p.setMessage("Poisoned");
					}
					else p.setMessage("Cannot be Poisoned");
				}
			}
			x-=2;
		}
		else {
			x = 0;
			y = 0;
			animating = false;
			anim = 0;
		}
	}
	
}
