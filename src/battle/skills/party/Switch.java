package battle.skills.party;

import java.awt.Graphics;

import battle.Skill;
import characters.Playable;
import entity.mobs.enemies.Enemy;

public class Switch extends Skill {

	public Switch(Playable p) {
		this.p = p;
		x = 0;
		y = 0;
		name = "Switch";
		dmg = p.getMaxMP() / p.getMag();
		type = "Offensive";
		description = "Drain MP to cripple enemies. Only works with half to full MP";
		range = "All";
	}
	
	public void attack(Enemy m) {
		this.e = m;
		
		for (int i = 0; i < m.getParty().size(); i++) {
			m.getParty().get(i).setDefMod((100 * p.getMP()*2)/p.getMaxMP());
			m.getParty().get(i).setDefModTimer(6);
		}
		
		p.setMP(-999);
		
		animating = true;
	}
	
	public void animate(Graphics g) {
		anim++;
		
		if (anim == 83) {
			for (int i = 0; i < e.getParty().size(); i++) {
				e.getParty().get(i).setMessage("Crippled");
			}
		}
		
		if (anim > 86) {
			anim = 0;
			animating = false;
		}
	}
	
}

