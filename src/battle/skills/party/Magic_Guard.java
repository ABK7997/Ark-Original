package battle.skills.party;

import java.awt.Graphics;

import battle.Skill;
import characters.Playable;

public class Magic_Guard extends Skill {

	public Magic_Guard(Playable p) {
		this.p = p;
		x = 0;
		y = 0;
		name = "Magic Guard";
		type = "Defensive";
		range = "Single";
		description = "Decrease user's mag-def to boost anothers' for 3 turns";
	}
	
	public void alter(Playable m) {
		friend = m;
		
		animating = true;
	}
	
	public void animate(Graphics g) {
		anim++;
		
		if (anim == 84) {
			friend.setMagDef(friend.getBaseMagDef()*2);
			friend.setMessage("Magic Def x2");
			friend.setMagDefTimer(3);
			p.setMagDef(p.getBaseMagDef()/2);
			p.setMessage("Magic Def / 2");
			p.setMagDefTimer(3);
		}
		
		if (anim > 86) {
			anim = 0;
			animating = false;
		}
	}
	
}

