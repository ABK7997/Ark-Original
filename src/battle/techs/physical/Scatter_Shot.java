package battle.techs.physical;

import java.awt.Color;
import java.awt.Graphics;

import battle.Spell;
import characters.Playable;
import characters.Playable.STATE;
import entity.mobs.enemies.Enemy;
import entity.mobs.enemies.Enemy.STATES;

public class Scatter_Shot extends Spell {

	private int x1, x2;
	private int y1, y2;
	private Enemy t1, t2, t3;
	private Playable p1, p2, p3;
	
	public Scatter_Shot(Playable p) {
		this.p = p;
		x = 0;
		y = 0;
		name = "Scatter Shot";
		cost = 14;
		dmg = p.getPwr() * 3;
		type = "Offensive";
		range = "Random";
		description = "Hit three random enemies with large shrapnel";
	}
	
	public void attack(Enemy e) {
		this.e = e;
		
		t1 = e.getParty().get(random.nextInt(e.getParty().size()));
		t2 = e.getParty().get(random.nextInt(e.getParty().size()));
		t3 = e.getParty().get(random.nextInt(e.getParty().size()));
		
		changeTargets(t1);
		changeTargets(t2);
		changeTargets(t3);
		
		dmg /= e.getDef();
		
		x = w/2;
		y = h/3;
		x1 = x;
		y1 = y;
		x2 = x;
		y2 = y;
		
		p.setEP(-cost);
		animating = true;
	}
	
	public void changeTargets(Enemy e) {
		while (e.getHP() == 0) e = e.getParty().get(random.nextInt(e.getParty().size()));
	}
	
	public void animate(Graphics g) {
		anim++;
		
		g.setColor(Color.DARK_GRAY);
		
		if (anim < 32);
		else if (anim < 82) {
			g.fillOval(x, y, 75, 75);
		}
		else if (anim < 132) {
			g.fillOval(x, y, 75, 75);
			g.fillOval(x1, y1, 75, 75);
			g.fillOval(x2, y2, 75, 75);
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
		
		if (anim == 105) {
			dmg = p.getPwr() * 3/2;
			dmg = ((dmg / t1.getDef()) * t1.getDefMod()) / 100;
			t1.setHP(-dmg);
			t1.setDP(dmg);
			t1.changeState(STATES.HIT);
			
			dmg = p.getPwr() * 3/2;
			dmg = ((dmg / t2.getDef()) * t2.getDefMod()) / 100;
			t2.setHP(-dmg);
			t2.setDP(dmg);
			t2.changeState(STATES.HIT);
			
			dmg = p.getPwr() * 3/2;
			dmg = ((dmg / t3.getDef()) * t3.getDefMod()) / 100;
			t3.setHP(-dmg);
			t3.setDP(dmg);
			t3.changeState(STATES.HIT);
		}
	}
	
	public Scatter_Shot(Enemy e) {
		this.e = e;
		cost = 4;
		dmg = e.getPwr() * 3/2;
		type = "Offensive";
	}
	
	public void attack(Playable p) {
		this.p = p;
		
		p1 = p.getParty().get(random.nextInt(e.getParty().size()));
		p2 = p.getParty().get(random.nextInt(e.getParty().size()));
		p3 = p.getParty().get(random.nextInt(e.getParty().size()));
		
		dmg /= p.getDef();
		
		x = w/2;
		y = h/3;
		x1 = x;
		y1 = y;
		x2 = x;
		y2 = y;
		
		e.setEP(-cost);
		animating = true;
	}
	
	public void animate2(Graphics g) {
		anim++;
		
		g.setColor(Color.DARK_GRAY);
		
		if (anim < 32);
		else if (anim < 82) {
			g.fillOval(x, y, 75, 75);
		}
		else if (anim < 132) {
			g.fillOval(x, y, 75, 75);
			g.fillOval(x1, y1, 75, 75);
			g.fillOval(x2, y2, 75, 75);
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
		
		if (anim == 105) {
			dmg = e.getPwr() * 3/2;
			dmg = ((dmg / p1.getDef()) * p1.getDefMod()) / 100;
			p1.setHP(-dmg);
			p1.setDP(dmg);
			p1.changeState(STATE.HIT);
			
			dmg = e.getPwr() * 3/2;
			dmg = ((dmg / p2.getDef()) * p2.getDefMod()) / 100;
			p2.setHP(-dmg);
			p2.setDP(dmg);
			p2.changeState(STATE.HIT);
			
			dmg = e.getPwr() * 3/2;
			dmg = ((dmg / p3.getDef()) * p3.getDefMod()) / 100;
			p3.setHP(-dmg);
			p3.setDP(dmg);
			p3.changeState(STATE.HIT);
		}
	}
	
}
