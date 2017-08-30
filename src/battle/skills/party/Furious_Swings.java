package battle.skills.party;

import java.awt.Color;
import java.awt.Graphics;

import battle.Skill;
import characters.Playable;
import entity.mobs.enemies.Enemy;
import entity.mobs.enemies.Enemy.STATES;

public class Furious_Swings extends Skill {

	private int x1, x2;
	private int y1, y2;
	private Enemy t1, t2, t3, t4;
	
	public Furious_Swings(Playable p) {
		this.p = p;
		x = 0;
		y = 0;
		name = "Furious Swings";
		dmg = p.getPwr() * 3/2;
		type = "Offensive";
		range = "Random";
		description = "Hit four random enemies for reduced damage";
	}
	
	public void attack(Enemy e) {
		this.e = e;
		
		t1 = e.getParty().get(random.nextInt(e.getParty().size()));
		t2 = e.getParty().get(random.nextInt(e.getParty().size()));
		t3 = e.getParty().get(random.nextInt(e.getParty().size()));
		t4 = e.getParty().get(random.nextInt(e.getParty().size()));
		
		x = w/2;
		y = h/3;
		x1 = x;
		y1 = y;
		x2 = x;
		y2 = y;
		
		animating = true;
	}
	
	public void animate(Graphics g) {
		anim++;
		
		g.setColor(Color.GRAY);
		
		if (anim < 32);
		else if (anim < 82) {
			g.fillOval(x, y, 40, 40);
		}
		else if (anim < 132) {
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
		
		if (anim == 105) {
			dmg = p.getPwr() * 2/5;
			dmg = ((dmg / t1.getDef()) * t1.getDefMod()) / 100;
			t1.setHP(-dmg);
			t1.setDP(dmg);
			t1.changeState(STATES.HIT);
			
			dmg = p.getPwr() * 2/5;
			dmg = ((dmg / t2.getDef()) * t2.getDefMod()) / 100;
			t2.setHP(-dmg);
			t2.setDP(dmg);
			t2.changeState(STATES.HIT);
			
			dmg = p.getPwr() * 2/5;
			dmg = ((dmg / t3.getDef()) * t3.getDefMod()) / 100;
			t3.setHP(-dmg);
			t3.setDP(dmg);
			t3.changeState(STATES.HIT);
			
			dmg = p.getPwr() * 2/5;
			dmg = ((dmg / t4.getDef()) * t4.getDefMod()) / 100;
			t4.setHP(-dmg);
			t4.setDP(dmg);
			t4.changeState(STATES.HIT);
		}
	}
	
}
