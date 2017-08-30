package battle.spells.offensive;

import java.awt.Color;
import java.awt.Graphics;

import battle.Spell;
import characters.Playable;
import characters.Playable.STATE;
import entity.mobs.enemies.Enemy;
import entity.mobs.enemies.Enemy.STATES;

public class Rust extends Spell {

	private int eRes;
	private int res;
	
	public Rust(Playable p) {
		this.p = p;
		x = 0;
		y = 0;
		name = "Rust";
		cost = 10;
		dmg = p.getMag() * 3;
		type = "Offensive";
		description = "Strike one opponent with a flame; can cause Burn";
	}
	
	public void attack(Enemy e) {
		this.e = e;
		
		dmg = p.getMag() * 3;
		dmg = (((dmg / e.getMagDef())) * e.getMagMod()) / 100;
		eRes = e.getRes();
		
		x = e.getX()*2;
		y = e.getY()*2;
		
		p.setMP(-cost);
		animating = true;
	}
	
	public void animate(Graphics g) {
		anim++;
		g.setColor(new Color(0xff7F6A00));
		
		if (anim >= 82) {
			g.fillOval(x, y, anim - 80, anim - 80);
			if (anim == 100) {
				int chance = random.nextInt(25);
				e.setHP(-dmg);
				e.setDP(dmg);
				e.changeState(STATES.HIT);
				if (chance + eRes < 30) {
					if (p.getType() == "Machine" || p.getType() == "Cyborg" || p.getType() == "Cygic") {
					e.setCorroding(true);
					e.setMessage("Corroding");
					}
					else e.setMessage("Cannot be Corroded");
				}
			}
		}
		if (anim >= 140){
			x = w / 2;
			y = 0;
			animating = false;
			anim = 0;
		}
	}
	
	public Rust(Enemy e) {
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
		dmg = (((dmg / p.getMagDef()) * p.getMagMod())) / 100;
		res = e.getRes();
		
		x = (p.getX()) * 2;
		y = (p.getY()) * 2;
		
		e.setMP(-cost);
		animating = true;
	}
	
	public void animate2(Graphics g) {
		anim++;
		g.setColor(new Color(0xff7F6A00));
		
		if (anim >= 82) {
			g.fillOval(x, y, anim - 80, anim - 80);
			if (anim == 100) {
				int chance = random.nextInt(25);
				p.setHP(-dmg);
				p.setDP(dmg);
				p.changeState(STATE.HIT);
				if (chance + res < 30) {
					if (p.getType() == "Machine" || p.getType() == "Cyborg" || p.getType() == "Cygic") {
						p.setCorroding(true);
						p.setMessage("Corroding");
					}
					else p.setMessage("Cannot be Corroded");
				}
			}
		}
		if (anim >= 140){
			x = 0;
			y = 0;
			animating = false;
			anim = 0;
		}
	}
	
}
