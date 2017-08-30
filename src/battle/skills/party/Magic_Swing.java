package battle.skills.party;

import java.awt.Graphics;

import battle.Skill;
import characters.Playable;
import entity.mobs.enemies.Enemy;
import entity.mobs.enemies.Enemy.STATES;

public class Magic_Swing extends Skill {

	public Magic_Swing(Playable p) {
		this.p = p;
		x = 0;
		y = 0;
		name = "Magic Swing";
		dmg = p.getMag();
		type = "Offensive";
		description = "Hit all enemies for Base Magic damage";
		range = "All";
	}
	
	public void attack(Enemy m) {
		this.e = m;
		
		animating = true;
	}
	
	public void animate(Graphics g) {
		anim++;
		
		if (anim == 83) {
			for (int i = 0; i < e.getParty().size(); i++) {
				dmg = p.getMag();
				dmg = ((dmg/e.getParty().get(i).getMagDef())*e.getParty().get(i).getMagMod()) / 100;
				e.getParty().get(i).setHP(-dmg);
				e.getParty().get(i).setDP(dmg);
				e.getParty().get(i).changeState(STATES.HIT);
			}
		}
		
		if (anim > 86) {
			anim = 0;
			animating = false;
		}
	}
	
}

