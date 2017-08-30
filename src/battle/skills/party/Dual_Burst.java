package battle.skills.party;

import java.awt.Color;
import java.awt.Graphics;

import battle.Skill;
import characters.Playable;
import entity.mobs.enemies.Enemy;

public class Dual_Burst extends Skill {

	public Dual_Burst(Playable p) {
		this.p = p;
		x = 0;
		y = 0;
		dmg = p.getEP() * 75;
		name = "Dual Burst - All EP";
		type = "Offensive";
		range = "All";
		description = "Use up all EP for a physical and magical attack";
	}
	
	public void attack(Enemy e) {
		this.e = e;
		p.setEP(-20);
		
		animating = true;
	}
	
	public void animate(Graphics g) {
		anim++;
		
		if (anim <= 32);
		else if (anim <= 132) {
			g.setColor(Color.GRAY);
			g.fillOval(e.getX()*2, e.getY()*2, anim*2/3, anim*2/3);
			if (anim == 130) {
				e.setHP(-dmg);
				e.setDP(dmg);
			}
			
			if (anim == 130) {
				dmg = ((dmg / e.getDef()) * e.getDefMod()) / 100;
				e.setHP(-dmg);
				e.setDP(dmg);
				
				dmg = p.getEP() * 75;
				dmg = ((dmg / e.getMagDef()) * e.getMagMod()) / 100;
				e.setHP(-dmg);
				e.setDP(dmg);
			}
		}
		
		else {
			animating = false;
			anim = 0;
		}
	}
}

