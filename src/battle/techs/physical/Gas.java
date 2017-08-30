package battle.techs.physical;

import java.awt.Color;
import java.awt.Graphics;

import battle.Spell;
import characters.Playable;
import characters.Playable.STATE;
import entity.mobs.enemies.Enemy;
import entity.mobs.enemies.Enemy.STATES;

public class Gas extends Spell {

	private int eRes;
	private int res;
	
	public Gas(Playable p) {
		this.p = p;
		name = "Gas";
		cost = 4;
		dmg = p.getPwr();
		type = "Offensive";
		description = "Spray noxious gas; high chance of Sleep";
	}
	
	public void attack(Enemy e) {
		this.e = e;
		
		dmg = p.getPwr();
		dmg = ((dmg / e.getDef()) * e.getDefMod()) / 100;
		eRes = e.getRes();
		
		x = (e.getX()-32)*2;
		y = (e.getY())*2;
		
		p.setEP(-cost);
		animating = true;
	}
	
	public void animate(Graphics g) {
		anim++;
		g.setColor(new Color(0xff5B7F00));
		
		if (anim >= 82) {
			g.fillOval(x, y, 20, 20);
			if (anim == 100) {
				int chance = random.nextInt(75);
				e.setHP(-dmg);
				e.setDP(dmg);
				e.changeState(STATES.HIT);
				if (chance + eRes < 75) {
					e.setSleep(true);
					e.setMessage("Passed Out");
				}
			}
			x+=2;
		}
		if (anim >= 140){
			x = w / 2;
			y = 0;
			animating = false;
			anim = 0;
		}
	}
	
	public Gas(Enemy e) {
		this.e = e;
		x = w/2;
		y = 0;
		cost = 4;
		dmg = e.getPwr();
		type = "Offensive";
	}
	
	public void attack(Playable p) {
		this.p = p;
		
		dmg = e.getPwr();
		dmg = ((dmg / p.getMagDef() * p.getMagMod())) / 100;
		res = p.getRes();
		
		x = (e.getX()+32)*2;
		y = (e.getY())*2;
		
		e.setEP(-cost);
		animating = true;
	}
	
	public void animate2(Graphics g) {
		anim++;
		g.setColor(new Color(0xff5B7F00));
		
		if (anim >= 82) {
			g.fillOval(x, y, 20, 20);
			if (anim == 100) {
				int chance = random.nextInt(75);
				p.setHP(-dmg);
				p.setDP(dmg);
				p.changeState(STATE.HIT);
				if (chance + res < 75) {
					p.setSleep(true);
					p.setMessage("Passed Out");
				}
			}
			x-=2;
		}
		if (anim >= 140){
			x = w / 2;
			y = 0;
			animating = false;
			anim = 0;
		}
	}
	
}