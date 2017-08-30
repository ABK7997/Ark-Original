package battle.techs.magic;

import java.awt.Color;
import java.awt.Graphics;

import battle.Spell;
import characters.Playable;
import characters.Playable.STATE;
import entity.mobs.enemies.Enemy;
import entity.mobs.enemies.Enemy.STATES;

public class Scatter_Burst extends Spell {

	private int x1, x2;
	private int y1, y2;
	private Enemy t1, t2, t3;
	private Playable p1, p2, p3;
	
	public Scatter_Burst(Playable p) {
		this.p = p;
		x = w/2;
		y = 0;
		name = "Scatter Burst";
		cost = 3;
		dmg = p.getMag() * 3/2;
		type = "Offensive";
		range = "Random";
		description = "Hit 3 random targets with low-magic bursts";
	}
	
	public void attack(Enemy e) {
		t1 = e.getParty().get(random.nextInt(e.getParty().size()));
		t2 = e.getParty().get(random.nextInt(e.getParty().size()));
		t3 = e.getParty().get(random.nextInt(e.getParty().size()));
		
		p.setEP(-cost);
		animating = true;
		
		x = w/3;
		y = h/3;
		x1 = x;
		y1 = y;
		x2 = x;
		y2 = y;
	}
	
	public void animate(Graphics g) {
		anim++;
		
		g.setColor(new Color(0xff0088FF));
		
		if (anim < 32);
		else if (anim < 82) {
			g.fillOval(x, y, 40, 40);
		}
		else if (anim < 164) {
			g.fillOval(x, y, 40, 40);
			g.fillOval(x1, y1, 40, 40);
			g.fillOval(x2, y2, 40, 40);
			x+=5;
			x1+=5;
			x2+=5;
			y1+=3;
			y2-=3;
		}
		else {
			animating = false;
			anim = 0;
		}
		
		if (anim == 132) {
			dmg = p.getMag() * 3/2;
			dmg = ((dmg / t1.getMagDef()) * t1.getMagMod()) / 100;
			t1.setHP(-dmg);
			t1.setDP(dmg);
			t1.changeState(STATES.HIT);
			
			dmg = p.getMag() * 3/2;
			dmg = ((dmg / t2.getMagDef()) * t2.getMagMod()) / 100;
			t2.setHP(-dmg);
			t2.setDP(dmg);
			t2.changeState(STATES.HIT);
			
			dmg = p.getMag() * 3/2;
			dmg = ((dmg / t3.getMagDef()) * t3.getMagMod()) / 100;
			t3.setHP(-dmg);
			t3.setDP(dmg);
			t3.changeState(STATES.HIT);
		}
	}
	
	public Scatter_Burst(Enemy e) {
		this.e = e;
		x = w/2;
		y = 0;
		cost = 3;
		dmg = e.getMag() * 3/2;
		type = "Offensive";
	}
	
	public void attack(Playable p) {
		p1 = p.getParty().get(random.nextInt(e.getParty().size()));
		p2 = p.getParty().get(random.nextInt(e.getParty().size()));
		p3 = p.getParty().get(random.nextInt(e.getParty().size()));
		
		e.setMP(-cost);
		animating = true;
		
		x = w/2;
		y = h/3;
		x1 = x;
		y1 = y;
		x2 = x;
		y2 = y;
	}
	
	public void animate2(Graphics g) {
		anim++;
		
		g.setColor(new Color(0xff0088FF));
		
		if (anim < 32);
		else if (anim < 82) {
			g.fillOval(x, y, 40, 40);
		}
		else if (anim < 164) {
			g.fillOval(x, y, 40, 40);
			g.fillOval(x1, y1, 40, 40);
			g.fillOval(x2, y2, 40, 40);
			x-=5;
			x1-=5;
			x2-=5;
			y1+=3;
			y2-=3;
		}
		else {
			animating = false;
			anim = 0;
		}
		
		if (anim == 132) {
			dmg = e.getMag() * 3/2;
			dmg = ((dmg / p1.getMagDef()) * p1.getMagMod()) / 100;
			p1.setHP(-dmg);
			p1.setDP(dmg);
			p1.changeState(STATE.HIT);
			
			dmg = p.getMag() * 3/2;
			dmg = ((dmg / p2.getMagDef()) * p2.getMagMod()) / 100;
			p2.setHP(-dmg);
			p2.setDP(dmg);
			p2.changeState(STATE.HIT);
			
			dmg = p.getMag() * 3/2;
			dmg = ((dmg / p3.getMagDef()) * p3.getMagMod()) / 100;
			p3.setHP(-dmg);
			p3.setDP(dmg);
			p3.changeState(STATE.HIT);
		}
	}
	
}
