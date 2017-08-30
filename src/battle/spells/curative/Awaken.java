package battle.spells.curative;

import java.awt.Color;
import java.awt.Graphics;

import battle.Spell;
import characters.Playable;
import characters.Playable.STATE;

public class Awaken extends Spell {

	public Awaken(Playable p) {
		this.p = p;
		name = "Awaken";
		cost = 5;
		type = "Curative";
		description = "Wake ally from Sleep";
	}

	public void heal(Playable m) {
		this.friend = m;
		
		p.setMP(-cost);
		
		x = (m.getX()+16)*2;
		y = (m.getY()-64)*2;
		
		animating = true;
	}
	
	public void animate(Graphics g) {
		anim++;
		
		g.setColor(Color.BLACK);
		
		if (anim <= 32);
		else if (anim <= 82) g.fillOval(x, y, 15, 15);
		else if (anim <= 120) {
			if (anim == 110) {
				friend.setSleep(false);
				friend.changeState(STATE.HIT);
				friend.changeState(STATE.NORMAL);
			}
			g.fillOval(x, y, 15, 15);
			y+=3;
		}
		else {
			animating = false;
			anim = 0;
		}
		
		
	}
	
}
