package battle.techs.curative;

import java.awt.Color;
import java.awt.Graphics;

import battle.Spell;
import characters.Playable;
import characters.Playable.STATE;

public class Cleanse extends Spell {

	public Cleanse(Playable p) {
		this.p = p;
		name = "Restore";
		cost = 3;
		type = "Curative";
		description = "Cure all status effects";
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
		
		g.setColor(new Color(0xff007F7F));
		
		if (anim <= 32);
		else if (anim <= 82) g.fillOval(x, y, 10 + (anim/3), 10 + (anim/4));
		else if (anim <= 164) {
			g.fillOval(x, y, 51 - (anim/6), 51 - (anim/6));
			if (anim == 83) {
				friend.setPoisoned(false);
				friend.setBurned(false);
				friend.setCorroding(false);
				friend.setSleep(false);
				friend.setParalyzed(false);
				friend.setIll(false);
				friend.changeState(STATE.NORMAL);
			}
		}
		else {
			animating = false;
			anim = 0;
		}
		
	}
	
}
