package characters;

import battle.skills.party.Energy_Donor;
import battle.skills.party.Guard;
import battle.skills.party.Harden;
import battle.skills.party.Swift_Guard;
import battle.skills.party.Wind_Up;
import battle.techs.physical.Nuke;
import battle.techs.physical.Rocket_Punch;
import battle.techs.physical.Scatter_Shot;
import battle.techs.physical.Shrapnel;
import battle.techs.physical.Swing;
import battle.techs.curative.*;
import battle.techs.defensive.*;
import graphics.Sprite;
import graphics.SpriteSheet;

public class Orzy extends Playable {

	public Orzy() {
		name = "Orzy";
		lv = 1;
		exp = 40;
		index = 2;
		
		x = 0;
		y = 0;
		
		type = "Machine";
	
		hp = maxHP = 300;
		mp = maxMP = 0;
		ep = maxEP = 5;
	
		pwr = basePwr = 25;
		dex = baseDex = 1;
		spd = baseSpd = 1;
		evd = baseEvd = 1;
		res = baseRes = 1;
		mag = baseMag = 20;
		eng = baseEng = 10;
	
		def = baseDef = 4;
		magDef = baseMagDef = 1;
		
		down = new Sprite(32, 8, 0, SpriteSheet.playable);
		down_1 = new Sprite(32, 8, 1, SpriteSheet.playable);
		down_2 = new Sprite(32, 8, 2, SpriteSheet.playable);
		right = new Sprite(32, 9, 0, SpriteSheet.playable);
		right_1 = new Sprite(32, 9, 1, SpriteSheet.playable);
		right_2 = new Sprite(32, 9, 2, SpriteSheet.playable);
		left = new Sprite(32, 10, 0, SpriteSheet.playable);
		left_1 = new Sprite(32, 10, 1, SpriteSheet.playable);
		left_2 = new Sprite(32, 10, 2, SpriteSheet.playable);
		up = new Sprite(32, 11, 0, SpriteSheet.playable);
		up_1 = new Sprite(32, 11, 1, SpriteSheet.playable);
		up_2 = new Sprite(32, 11, 2, SpriteSheet.playable);
	
		ill = new Sprite(32, 12, 0, SpriteSheet.playable);
		attack_1 = new Sprite(32, 12, 1, SpriteSheet.playable);
		magic_1 = new Sprite(32, 12, 2, SpriteSheet.playable);
		attack_2 = new Sprite(32, 13, 1, SpriteSheet.playable);
		magic_2 = new Sprite(32, 13, 2, SpriteSheet.playable);
	
		hit = new Sprite(32, 14, 0, SpriteSheet.playable);
		flee = new Sprite(32, 14, 1, SpriteSheet.playable);
		dead = new Sprite(32, 14, 2, SpriteSheet.playable);
		
		defend = new Sprite(32, 15, 0, SpriteSheet.playable);
		item = new Sprite(32, 15, 1, SpriteSheet.playable);
		skill = new Sprite(32, 15, 2, SpriteSheet.playable);
		
		sprite = right;
		
		restoreSpells();
	}
	
	//STATUS IMMUNITES
	public boolean getPoisoned() {
		poisoned = false;
		return false;
	}
	
	public void levelUp() {
		maxHP *= 1.0555;
		if (maxHP > 9999) maxHP = 9999;
		basePwr = (int) Math.ceil(basePwr * 1.084);
		baseMag = (int) Math.ceil(baseMag * 1.0541);
		
		if (lv % 3 == 0) {
			baseDex+=3;
			baseSpd++;
			baseEvd++;
			baseRes++;
		}
		else if (lv % 2 == 0) {
			baseDex+=3;
			baseSpd++;
			baseEvd++;
			baseRes++;
		}
		else {
			baseDex+=3;
			baseSpd++;
			baseEvd++;
			baseRes++;
		}
		
		if (lv % 4 == 0) maxEP++;
		
		levelRestore();
		learnSpells();
	}
	
	public void learnSpells() {
		switch(lv) {
		case 1: learnSpell(new Swing(this)); break;
		case 3: learnSpell(new Heal(this)); break;
		case 5: learnSkill(new Guard(this)); break;
		case 7: learnSpell(new Small_Shell(this)); break;
		case 8: learnSpell(new Small_Shield(this)); break;
		case 9: learnSpell(new Small_Barrier(this)); break;
		case 10: learnSpell(new Repair(this)); break;
		case 11: learnSpell(new Jolt(this)); break;
		case 12: learnSpell(new SuperCharge(this)); break;
		case 13: learnSkill(new Wind_Up(this)); break;
		case 15: learnSpell(new AeroHeal(this)); break;
		case 18: learnSpell(new Rocket_Punch(this)); break;
		case 20: learnSpell(new Heal2(this)); break;
		case 22: learnSpell(new Omni_Cover(this)); break;
		case 23: learnSpell(new Cleanse(this)); break;
		case 27: learnSpell(new Status_Shield(this)); break;
		case 35: learnSpell(new AeroHeal2(this)); break;
		case 36: learnSkill(new Energy_Donor(this)); break;
		case 37: learnSpell(new Shrapnel(this)); break;
		case 40: learnSpell(new Iron_Shield(this)); break;
		case 42: learnSpell(new Magic_Shield(this)); break;
		case 45: learnSkill(new Swift_Guard(this)); break;
		case 50: learnSpell(new Heal3(this)); break;
		case 55: learnSpell(new Scatter_Shot(this)); break;
		case 60: learnSpell(new AeroHeal3(this)); break;
		case 65: learnSpell(new Omni_Shield(this)); break;
		case 70: learnSpell(new Nuke(this)); break;
		default: break;
		}
	}
	
	public void restoreSpells() {
		resetSpells();
		skills.add(new Harden(this));
		for (int i = 0; i <= lv; i++) {
			switch(i) {
			case 1: offSpells.add(new Swing(this)); break;
			case 3: cureSpells.add(new Heal(this)); break;
			case 5: skills.add(new Guard(this)); break;
			case 7: defSpells.add(new Small_Shell(this)); break;
			case 8: defSpells.add(new Small_Shield(this)); break;
			case 9: defSpells.add(new Small_Barrier(this)); break;
			case 10: cureSpells.add(new Repair(this)); break;
			case 11: cureSpells.add(new Jolt(this)); break;
			case 12: defSpells.add(new SuperCharge(this)); break;
			case 13: skills.add(new Wind_Up(this)); break;
			case 15: cureSpells.add(new AeroHeal(this)); break;
			case 18: offSpells.add(new Rocket_Punch(this)); break;
			case 20: cureSpells.add(new Heal2(this)); break;
			case 22: defSpells.add(new Omni_Cover(this)); break;
			case 23: cureSpells.add(new Cleanse(this)); break;
			case 27: defSpells.add(new Status_Shield(this)); break;
			case 35: cureSpells.add(new AeroHeal2(this)); break;
			case 36: skills.add(new Energy_Donor(this)); break;
			case 37: offSpells.add(new Shrapnel(this)); break;
			case 40: defSpells.add(new Iron_Shield(this)); break;
			case 42: defSpells.add(new Magic_Shield(this)); break;
			case 45: skills.add(new Swift_Guard(this)); break;
			case 50: cureSpells.add(new Heal3(this)); break;
			case 55: offSpells.add(new Scatter_Shot(this)); break;
			case 60: cureSpells.add(new AeroHeal3(this)); break;
			case 65: defSpells.add(new Omni_Shield(this)); break;
			case 70: offSpells.add(new Nuke(this)); break;
			default: break;
			}
		}
	}
	
}
