package battle.spells.defensive;

import java.awt.Color;
import java.awt.Graphics;

import battle.Spell;
import characters.Playable;

public class Leech extends Spell {

	public Leech(Playable p) {
		this.p = p;
		x = 0;
		y = 0;
		name = "Leech";
		cost = 20;
		dmg = p.getMaxHP() / 3;
		type = "Defensive";
		description = "Drain HP from an ally";
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
		g.setColor(Color.RED);
		
		if (anim < 32);
		else if (anim < 82) {
			g.fillOval(x, y, anim/2, anim/2);
			if (anim == 80) {
				friend.setHP(-dmg);
				friend.setDP(dmg);
			}
		}
		else if (anim < 132) {
			if (anim == 130) {
				p.setHP(dmg);
				p.setCP(dmg);
			}
			g.fillOval(x, y, 80 - anim/3, 80 - anim/3);
		}
		else {
			animating = false;
			anim = 0;
		}
	}
	
}
