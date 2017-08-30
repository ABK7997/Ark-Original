package battle.spells.offensive;

import java.awt.Color;
import java.awt.Graphics;

import battle.Spell;
import characters.Playable;
import characters.Playable.STATE;
import entity.mobs.enemies.Enemy;
import entity.mobs.enemies.Enemy.STATES;

public class Spray3 extends Spell {

	private Enemy t1, t2, t3;
	private Playable p1, p2, p3;
	
	public Spray3(Playable p) {
		this.p = p;
		x = w/2;
		y = 0;
		name = "Spray++";
		cost = 75;
		dmg = p.getMag() * 15;
		type = "Offensive";
		range = "Random";
		description = "Attack three random targets with massive magic spheres";
	}
	
	public void attack(Enemy e) {
		t1 = e.getParty().get(random.nextInt(e.getParty().size()));
		t2 = e.getParty().get(random.nextInt(e.getParty().size()));
		t3 = e.getParty().get(random.nextInt(e.getParty().size()));
		
		changeTargets(t1);
		changeTargets(t2);
		changeTargets(t3);
		
		p.setMP(-cost);
		animating = true;
	}
	
	public void changeTargets(Enemy e) {
		while (e.getHP() == 0) e = e.getParty().get(random.nextInt(e.getParty().size()));
	}
	
	public void animate(Graphics g) {
		anim++;
		g.setColor(Color.BLUE);
		
		if (anim >= 32) {
			g.fillOval(x+80, y, 50, 50);
			g.fillOval(x, y, 50, 50);
			g.fillOval(x-80, y, 50, 50);
		}
		if (anim >= 82) {
			if (anim == 100) {
				dmg = p.getMag() * 15;
				dmg = ((dmg / t1.getMagDef()) * t1.getMagMod()) / 100;
				t1.setHP(-dmg);
				t1.setDP(dmg);
				t1.changeState(STATES.HIT);
				
				dmg = p.getMag() * 15;
				dmg = ((dmg / t2.getMagDef()) * t2.getMagMod()) / 100;
				t2.setHP(-dmg);
				t2.setDP(dmg);
				t2.changeState(STATES.HIT);
				
				dmg = p.getMag() * 15;
				dmg = ((dmg / t3.getMagDef()) * t3.getMagMod()) / 100;
				t3.setHP(-dmg);
				t3.setDP(dmg);
				t3.changeState(STATES.HIT);
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
	
	public Spray3(Enemy e) {
		this.e = e;
		x = w/2;
		y = 0;
		cost = 75;
		dmg = e.getMag() * 15;
		type = "Offensive";
	}
	
	public void attack(Playable p) {
		p1 = p.getParty().get(random.nextInt(e.getParty().size()));
		p2 = p.getParty().get(random.nextInt(e.getParty().size()));
		p3 = p.getParty().get(random.nextInt(e.getParty().size()));
		
		e.setMP(-cost);
		animating = true;
	}
	
	public void animate2(Graphics g) {
		anim++;
		g.setColor(Color.BLUE);
		
		if (anim >= 32) {
			g.fillOval(x+80, y, 50, 50);
			g.fillOval(x, y, 50, 50);
			g.fillOval(x-80, y, 50, 50);
		}
		if (anim >= 82) {
			if (anim == 100) {
				dmg = e.getMag() * 15;
				dmg = ((dmg / p1.getMagDef()) * p1.getMagMod()) / 100;
				p1.setHP(-dmg);
				p1.setDP(dmg);
				p1.changeState(STATE.HIT);
				
				dmg = e.getMag() * 15;
				dmg = ((dmg / p2.getMagDef()) * p2.getMagMod()) / 100;
				p2.setHP(-dmg);
				p2.setDP(dmg);
				p2.changeState(STATE.HIT);
				
				dmg = e.getMag() * 15;
				dmg = ((dmg / p3.getMagDef()) * p3.getMagMod()) / 100;
				p3.setHP(-dmg);
				p3.setDP(dmg);
				p3.changeState(STATE.HIT);
			}
			x -= 8; y += 10;
			if (x > w) {
				x = w / 2;
				y = 0;
				animating = false;
				anim = 0;
			}
		}
	}
	
}
