package battle.skills.party;

import java.awt.Color;
import java.awt.Graphics;

import battle.Skill;
import characters.Playable;
import entity.mobs.enemies.Enemy;

public class Drain_Burst extends Skill {

	private Enemy e;
	
	public Drain_Burst(Playable p) {
		this.p = p;
		x = 0;
		y = 0;
		dmg = p.getMP() * 10;
		name = "Drain Burst - All MP";
		type = "Offensive";
		range = "All";
		description = "Expend all MP for a magical blast";
	}
	
	public void attack(Enemy e) {
		this.e = e;
		dmg = ((dmg / e.getMagDef()) * e.getMagMod()) / 100;
		
		p.setMP(-9999);
		
		animating = true;
	}
	
	public void animate(Graphics g) {
		anim++;
		
		if (anim <= 32);
		else if (anim <= 132) {
			g.setColor(new Color(0xff00AAFF));
			g.fillOval(e.getX()*2, e.getY()*2, anim*2/3, anim*2/3);
			if (anim == 130) {
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

