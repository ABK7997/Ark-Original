package battle.spells.defensive;

import java.awt.Color;
import java.awt.Graphics;

import battle.Spell;
import characters.Playable;

public class Parasite extends Spell {

	public Parasite(Playable p) {
		this.p = p;
		x = 0;
		y = 0;
		name = "Parasite";
		cost = 1;
		dmg = p.getMaxMP() / 3;
		type = "Defensive";
		description = "Drain MP from an ally";
	}
	
	public void alter(Playable m) {
		friend = m;
		
		x = m.getX()*2;
		y = m.getY()*2;
		
		p.setMP(-cost);
		animating = true;
	}
	
	public void animate(Graphics g) {
		anim++;
		g.setColor(new Color(0xffB200FF));
		
		if (anim < 32);
		else if (anim < 82) {
			g.fillOval(x, y, anim/2, anim/2);
			if (anim == 80) {
				friend.setMP(-dmg);
				friend.setSP(dmg);
			}
		}
		else if (anim < 132) {
			if (anim == 130) {
				p.setMP(dmg);
				p.setRP(dmg);
			}
			g.fillOval(x, y, 80 - anim/3, 80 - anim/3);
		}
		else {
			animating = false;
			anim = 0;
		}
	}
	
}
