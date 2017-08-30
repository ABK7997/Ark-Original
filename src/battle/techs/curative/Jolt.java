package battle.techs.curative;

import java.awt.Color;
import java.awt.Graphics;

import battle.Spell;
import characters.Playable;
import characters.Playable.STATE;

public class Jolt extends Spell {

	public Jolt(Playable p) {
		this.p = p;
		name = "Jolt";
		cost = 2;
		type = "Curative";
		description = "Wake ally from Sleep, but harm them slightly";
	}

	public void heal(Playable m) {
		this.friend = m;
		
		p.setEP(-cost);
		
		x = (m.getX()+16)*2;
		y = (m.getY()-64)*2;
		
		animating = true;
	}
	
	public void animate(Graphics g) {
		anim++;
		
		g.setColor(Color.YELLOW);
		
		if (anim <= 32);
		else if (anim <= 82) g.fillOval(x, y, 15, 15);
		else if (anim <= 120) {
			if (anim == 110) {
				friend.setSleep(false);
				friend.changeState(STATE.HIT);
				friend.changeState(STATE.NORMAL);
				friend.setHP(-friend.getHP()/25);
				friend.setDP(-friend.getHP()/25);
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
