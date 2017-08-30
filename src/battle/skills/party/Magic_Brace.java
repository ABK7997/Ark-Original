package battle.skills.party;

import java.awt.Graphics;

import battle.Skill;
import characters.Playable;
import entity.mobs.enemies.Enemy;

public class Magic_Brace extends Skill {

	public Magic_Brace(Playable p) {
		this.p = p;
		x = 0;
		y = 0;
		dmg = p.getBaseMagDef()*2;
		name = "Iron Defense";
		type = "Defensive";
		range = "Self";
		description = "Double magic defense for 2 turns";
	}
	
	public void alter(Playable m) {
		p.setMagDef(dmg);
		p.setMagDefTimer(3);
		
		animating = true;
	}
	
	public void animate(Graphics g) {
		anim++;
		
		if (anim > 83) p.setMessage("Magic Def x 2");
		
		if (anim > 86) {
			anim = 0;
			animating = false;
		}
	}
	
	public Magic_Brace(Enemy e) {
		this.e = e;
		x = 0;
		y = 0;
		dmg = e.getBaseMagDef()*2;
		type = "Defensive";
	}
	
	public void alter(Enemy e) { //Acts for 4 turns
		e.setMagDef(dmg);
		e.setMagDefTimer(5);
		
		animating = true;
	}
	
	public void animate2(Graphics g) {
		anim++;
		
		if (anim > 83) e.setMessage("Magic Def x 2");
		
		if (anim > 86) {
			anim = 0;
			animating = false;
		}
	}
	
}

