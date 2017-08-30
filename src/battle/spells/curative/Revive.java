package battle.spells.curative;

import java.awt.Color;
import java.awt.Graphics;

import battle.Spell;
import characters.Playable;

public class Revive extends Spell {

	public Revive(Playable p) {
		this.p = p;
		name = "Revive";
		cost = 50;
		type = "Curative";
		description = "Revive an ally from KO";
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
		
		g.setColor(Color.WHITE);
		
		if (anim <= 32);
		else if (anim <= 82) g.fillOval(x, y, 40, 40);
		else if (anim <= 150) {
			if (anim == 149) {
				if (friend.getHP() == 0) {
					friend.reviveHP(friend.getMaxHP()/20);
				}
			}
			g.fillOval(x, y, 40, 40);
			y++;
		}
		else {
			animating = false;
			anim = 0;
		}
		
		
	}
	
}
