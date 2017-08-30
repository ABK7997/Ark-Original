package battle.skills.party;

import java.awt.Graphics;

import battle.Skill;
import characters.Playable;

public class Guard extends Skill {

	public Guard(Playable p) {
		this.p = p;
		x = 0;
		y = 0;
		name = "Guard";
		dmg = p.getMaxHP();
		type = "Defensive";
		range = "Single";
		description = "Decrease user's defense to boost others' for 3 turns";
	}
	
	public void alter(Playable m) {
		friend = m;
		
		animating = true;
	}
	
	public void animate(Graphics g) {
		anim++;
		
		if (anim == 84) {
			friend.setDef(friend.getBaseDef()*2);
			friend.setMessage("Defense x2");
			friend.setDefTimer(3);
			p.setDef(p.getBaseDef()/2);
			p.setMessage("Defense / 2");
			p.setDefTimer(3);
		}
		
		if (anim > 86) {
			anim = 0;
			animating = false;
		}
	}
	
}

