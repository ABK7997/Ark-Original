package characters;

import graphics.Sprite;
import graphics.SpriteSheet;
import battle.skills.party.Ally_Boost;
import battle.skills.party.Dual_Burst;
import battle.skills.party.Magic_Brace;
import battle.skills.party.Omni_Boost;
import battle.skills.party.Swift_Shell;
import battle.techs.curative.Heal;
import battle.techs.curative.Heal2;
import battle.techs.curative.Heal3;
import battle.techs.defensive.Status_Shield;
import battle.techs.defensive.SuperCharge;
import battle.techs.magic.*;

public class Zee extends Playable {
	
	public Zee() {
		name = "Zee";
		lv = 1;
		index = 5;
		
		x = 0;
		y = 0;
		
		type = "Cygic";
	
		hp = maxHP = 200;
		mp = maxMP = 0;
		ep = maxEP = 3;
	
		pwr = basePwr = 15;
		dex = baseDex = 2;
		spd = baseSpd = 2;
		evd = baseEvd = 2;
		res = baseRes = 4;
		mag = baseMag = 10;
		eng = baseEng = 0;
	
		def = baseDef = 2;
		magDef = baseMagDef = 2; 
		
		down = new Sprite(32, 0, 6, SpriteSheet.playable);
		down_1 = new Sprite(32, 0, 7, SpriteSheet.playable);
		down_2 = new Sprite(32, 0, 8, SpriteSheet.playable);
		right = new Sprite(32, 1, 6, SpriteSheet.playable);
		right_1 = new Sprite(32, 1, 7, SpriteSheet.playable);
		right_2 = new Sprite(32, 1, 8, SpriteSheet.playable);
		left = new Sprite(32, 2, 6, SpriteSheet.playable);
		left_1 = new Sprite(32, 2, 7, SpriteSheet.playable);
		left_2 = new Sprite(32, 2, 8, SpriteSheet.playable);
		up = new Sprite(32, 3, 6, SpriteSheet.playable);
		up_1 = new Sprite(32, 3, 7, SpriteSheet.playable);
		up_2 = new Sprite(32, 3, 8, SpriteSheet.playable);
	
		ill = new Sprite(32, 4, 6, SpriteSheet.playable);
		attack_1 = new Sprite(32, 4, 7, SpriteSheet.playable);
		magic_1 = new Sprite(32, 4, 8, SpriteSheet.playable);
		attack_2 = new Sprite(32, 5, 7, SpriteSheet.playable);
		magic_2 = new Sprite(32, 5, 8, SpriteSheet.playable);
	
		hit = new Sprite(32, 6, 6, SpriteSheet.playable);
		flee = new Sprite(32, 6, 7, SpriteSheet.playable);
		dead = new Sprite(32, 6, 8, SpriteSheet.playable);
		
		defend = new Sprite(32, 7, 6, SpriteSheet.playable);
		item = new Sprite(32, 7, 7, SpriteSheet.playable);
		skill = new Sprite(32, 7, 8, SpriteSheet.playable);
		
		sprite = right;
		
		restoreSpells();
	}
	
	//STATUS IMMUNITES
	public boolean getPoisoned() {
		poisoned = false;
		return false;
	}
	
	public void levelUp() {
		maxHP *= 1.0505;
		if (maxHP > 9999) maxHP = 9999;
		basePwr = (int) Math.ceil(basePwr * 1.08);
		baseMag = (int) Math.ceil(baseMag * 1.0476);
		
		if (lv % 3 == 0) {
			baseDex++;
			baseSpd++;
			baseEvd++;
			baseRes += 2;
		}
		else if (lv % 2 == 0){
			baseDex++;
			baseSpd++;
			baseEvd++;
			baseRes += 2;
		}
		else {
			baseDex++;
			baseSpd++;
			baseEvd++;
			baseRes++;
		}
		
		if (lv % 4 == 0) ep++;
		//if (lv == 25 || lv == 50 || lv == 75) ep++;
		
		levelRestore();
		learnSpells();
	}
	
	public void learnSpells() {
		switch(lv) {
		case 10: learnSpell(new Scatter_Burst(this)); break;
		case 12: learnSkill(new Magic_Brace(this)); break;
		case 15: learnSpell(new Omni_Burst(this)); break;
		case 20: learnSpell(new Burst2(this)); break;
		case 25: learnSpell(new Implosion(this)); break;
		case 27: learnSkill(new Ally_Boost(this)); break;
		case 30: learnSpell(new Scatter_Burst2(this)); break;
		case 33: learnSpell(new Heal(this)); break;
		case 35: learnSpell(new Partial_Implosion(this)); break;
		case 40: learnSpell(new Omni_Burst2(this)); break;
		case 42: learnSkill(new Swift_Shell(this)); break;
		case 43: learnSpell(new SuperCharge(this)); break;
		case 45: learnSpell(new Status_Shield(this)); break;
		case 50: learnSpell(new Burst3(this)); break;
		case 52: learnSpell(new Heal2(this)); break;
		case 54: learnSkill(new Omni_Boost(this)); break;
		case 55: learnSpell(new Scatter_Burst3(this)); break;
		case 60: learnSpell(new Omni_Burst3(this)); break;
		case 65: learnSpell(new Magic_Beam(this)); break;
		case 75: learnSpell(new Heal3(this)); break;
		}
	}
	
	public void restoreSpells() {
		resetSpells();
		for (int i = 0; i <= lv; i++) {
			switch(i) {
			case 1: offSpells.add(new Burst(this)); skills.add(new Dual_Burst(this)); break;
			case 10: offSpells.add(new Scatter_Burst(this)); break;
			case 12: skills.add(new Magic_Brace(this)); break;
			case 15: offSpells.add(new Omni_Burst(this)); break;
			case 20: offSpells.add(new Burst2(this)); break;
			case 25: offSpells.add(new Implosion(this)); break;
			case 27: skills.add(new Ally_Boost(this)); break;
			case 30: offSpells.add(new Scatter_Burst2(this)); break;
			case 33: cureSpells.add(new Heal(this)); break;
			case 35: offSpells.add(new Partial_Implosion(this)); break;
			case 40: offSpells.add(new Omni_Burst2(this)); break;
			case 42: skills.add(new Swift_Shell(this)); break;
			case 43: defSpells.add(new SuperCharge(this)); break;
			case 45: defSpells.add(new Status_Shield(this)); break;
			case 50: offSpells.add(new Burst3(this)); break;
			case 52: cureSpells.add(new Heal2(this)); break;
			case 54: skills.add(new Omni_Boost(this)); break;
			case 55: offSpells.add(new Scatter_Burst3(this)); break;
			case 60: offSpells.add(new Omni_Burst3(this)); break;
			case 65: offSpells.add(new Magic_Beam(this)); break;
			case 75: cureSpells.add(new Heal3(this)); break;
			}
		}
	}
}
