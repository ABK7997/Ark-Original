package battle.spells.curative;

import java.awt.Color;
import java.awt.Graphics;

import battle.Spell;
import characters.Playable;
import characters.Playable.STATE;

public class Fix extends Spell {

	public Fix(Playable p) {
		this.p = p;
		name = "Fix";
		cost = 5;
		type = "Curative";
		description = "Repair Corrosion";
	}

	public void heal(Playable m) {
		this.friend = m;
		
		p.setMP(-cost);
		
		x = (m.getX()+64)*2;
		y = (m.getY()+5)*2;
		
		animating = true;
	}
	
	public void animate(Graphics g) {
		anim++;
		
		g.setColor(new Color(0xff6B2A00));
		
		if (anim <= 32);
		else if (anim <= 82) g.fillOval(x, y, 10 + (anim/3), 10 + (anim/4));
		else if (anim <= 164) {
			g.fillOval(x, y, 51 - (anim/6), 51 - (anim/6));
			if (anim == 83) {
				friend.setCorroding(false);
				friend.changeState(STATE.NORMAL);
			}
		}
		else {
			animating = false;
			anim = 0;
		}
		
		
	}
	
}
