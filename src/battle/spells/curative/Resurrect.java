package battle.spells.curative;

import java.awt.Color;
import java.awt.Graphics;

import battle.Spell;
import characters.Playable;
import characters.Playable.STATE;

public class Resurrect extends Spell {

	public Resurrect(Playable p) {
		this.p = p;
		name = "Resurrect";
		cost = 100;
		type = "Curative";
		description = "Revive an ally from KO with full HP";
	}

	public void heal(Playable m) {
		this.friend = m;
		
		p.setMP(-cost);
		
		x = (m.getX())*2;
		y = (m.getY()-32)*2;
		
		animating = true;
	}
	
	public void animate(Graphics g) {
		anim++;
		
		g.setColor(new Color(0xffFFD700));
		
		if (anim <= 32);
		else if (anim <= 82) g.fillOval(x, y, 70, 70);
		else if (anim <= 150) {
			if (anim == 149) {
				if (friend.getHP() == 0) {
					friend.setHP(9999);
					friend.changeState(STATE.NORMAL);
				}
			}
			g.fillOval(x, y, 70, 70);
			y++;
		}
		else {
			animating = false;
			anim = 0;
		}
		
		
	}
	
}
