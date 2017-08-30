package characters;

import graphics.Sprite;
import graphics.SpriteSheet;
import main.Story;
import battle.skills.party.Aim;
import battle.skills.party.Bulk_Up;
import battle.skills.party.Nerf;
import battle.skills.party.Switch;
import battle.skills.party.Wide_Sweep;
import battle.spells.curative.*;
import battle.spells.defensive.Barrier;
import battle.spells.defensive.Field;
import battle.spells.defensive.Protect;
import battle.spells.offensive.Bolt;
import battle.spells.offensive.Spray;
import battle.spells.offensive.Spray2;
import battle.spells.offensive.Spray3;

public class Ark extends Playable {
	
	public Ark() {
		name = "Ark";
		lv = 1;
		exp = 0;
		index = 1;
		
		x = 0;
		y = 0;
		
		type = "Organic";
	
		hp = maxHP = 225;
		mp = maxMP = 40;
		ep = maxEP = 0;
	
		pwr = basePwr = 40;
		dex = baseDex = 6;
		spd = baseSpd = 6;
		evd = baseEvd = 6;
		res = baseRes = 3;
		mag = baseMag = 15;
		eng = baseEng = 0;
	
		def = baseDef = 2;
		magDef = baseMagDef = 2; 
		
		down = new Sprite(32, 0, 0, SpriteSheet.playable);
		down_1 = new Sprite(32, 0, 1, SpriteSheet.playable);
		down_2 = new Sprite(32, 0, 2, SpriteSheet.playable);
		right = new Sprite(32, 1, 0, SpriteSheet.playable);
		right_1 = new Sprite(32, 1, 1, SpriteSheet.playable);
		right_2 = new Sprite(32, 1, 2, SpriteSheet.playable);
		left = new Sprite(32, 2, 0, SpriteSheet.playable);
		left_1 = new Sprite(32, 2, 1, SpriteSheet.playable);
		left_2 = new Sprite(32, 2, 2, SpriteSheet.playable);
		up = new Sprite(32, 3, 0, SpriteSheet.playable);
		up_1 = new Sprite(32, 3, 1, SpriteSheet.playable);
		up_2 = new Sprite(32, 3, 2, SpriteSheet.playable);
	
		ill = new Sprite(32, 4, 0, SpriteSheet.playable);
		attack_1 = new Sprite(32, 4, 1, SpriteSheet.playable);
		magic_1 = new Sprite(32, 4, 2, SpriteSheet.playable);
		attack_2 = new Sprite(32, 5, 1, SpriteSheet.playable);
		magic_2 = new Sprite(32, 5, 2, SpriteSheet.playable);
	
		hit = new Sprite(32, 6, 0, SpriteSheet.playable);
		flee = new Sprite(32, 6, 1, SpriteSheet.playable);
		dead = new Sprite(32, 6, 2, SpriteSheet.playable);
		
		defend = new Sprite(32, 7, 0, SpriteSheet.playable);
		item = new Sprite(32, 7, 1, SpriteSheet.playable);
		skill = new Sprite(32, 7, 2, SpriteSheet.playable);
		
		sprite = right;
		
		restoreSpells();
	}
	
	//STATUS IMMUNITES
	public boolean getCorroding() {
		corroding = false;
		return false;
	}
	
	public void levelUp() {
		maxHP *= 1.0523;
		maxMP *= 1.0769;
		if (maxHP > 9999) maxHP = 9999;
		if (maxMP > 999) maxMP = 999; 
		basePwr = (int) Math.ceil(basePwr * 1.089);
		baseMag = (int) Math.ceil(baseMag * 1.0476);
		
		if (lv % 3 == 0) {
			baseDex += 3;
			baseSpd += 3;
			baseEvd += 3;
			res += 2;
		}
		else if (lv % 2 == 0){
			baseDex += 2;
			baseSpd += 2;
			baseEvd += 2;
			res += 1;
		}
		else {
			baseDex += 2;
			baseSpd += 2;
			baseEvd += 2;
			res += 1;
		}
		
		levelRestore();
		learnSpells();
	}
	
	public void learnSpells() {
		switch(lv) {
		case 3: learnSpell(new Aura(this)); break;
		case 5: learnSpell(new Cure(this)); break;
		case 7: learnSpell(new Fix(this)); break;
		case 9: learnSkill(new Aim(this)); break;
		case 10: learnSpell(new Rain(this)); break;
		case 12: learnSpell(new Spray(this)); break;
		case 13: learnSpell(new Rejuvenate(this)); break;
		case 15: learnSpell(new Aura2(this)); break;
		case 18: learnSpell(new Cool(this)); break;
		case 19: learnSkill(new Switch(this)); break;
		case 20: learnSpell(new Regen(this)); break;
		case 22: learnSpell(new Vaccinate(this)); break;
		case 23: learnSpell(new Awaken(this)); break;
		case 25: learnSpell(new Revive(this)); break;
		case 27: learnSpell(new Protect(this)); break;
		case 28: learnSpell(new Field(this)); break;
		case 30: learnSpell(new Restore(this)); break;
		case 31: learnSkill(new Wide_Sweep(this)); break;
		case 33: learnSpell(new Barrier(this)); break;
		case 35: learnSpell(new Rain2(this)); break;
		case 36: learnSpell(new Spray2(this)); break;
		case 40: learnSpell(new Aura3(this)); break;
		case 45: learnSkill(new Nerf(this)); break;
		case 50: learnSpell(new Regen2(this)); break;
		case 60: learnSpell(new Rain3(this)); break;
		case 62: learnSpell(new Spray3(this)); break;
		case 65: learnSpell(new Resurrect(this)); break;
		default: break;
		}
	}
	
	public void restoreSpells() {
		resetSpells();
		skills.add(new Bulk_Up(this));
		for (int i = 0; i <= lv; i++) {
			switch(i) {
			case 1: if (Story.mainStory > 0) offSpells.add(new Bolt(this)); break;
			case 3: cureSpells.add(new Aura(this)); break;
			case 5: cureSpells.add(new Cure(this)); break;
			case 7: cureSpells.add(new Fix(this)); break;
			case 9: skills.add(new Aim(this)); break;
			case 10: cureSpells.add(new Rain(this)); break;
			case 12: offSpells.add(new Spray(this)); break;
			case 13: cureSpells.add(new Rejuvenate(this)); break;
			case 15: cureSpells.add(new Aura2(this)); break;
			case 18: cureSpells.add(new Cool(this)); break;
			case 20: cureSpells.add(new Regen(this)); break;
			case 19: skills.add(new Switch(this)); break;
			case 22: cureSpells.add(new Vaccinate(this)); break;
			case 23: cureSpells.add(new Awaken(this)); break;
			case 25: cureSpells.add(new Revive(this)); break;
			case 27: defSpells.add(new Protect(this)); break;
			case 28: defSpells.add(new Field(this)); break;
			case 30: cureSpells.add(new Restore(this)); break;
			case 31: skills.add(new Wide_Sweep(this)); break;
			case 33: defSpells.add(new Barrier(this)); break;
			case 35: cureSpells.add(new Rain2(this)); break;
			case 36: offSpells.add(new Spray2(this)); break;
			case 40: cureSpells.add(new Aura3(this)); break;
			case 45: skills.add(new Nerf(this)); break;
			case 50: cureSpells.add(new Regen2(this)); break;
			case 60: cureSpells.add(new Rain3(this)); break;
			case 62: offSpells.add(new Spray3(this)); break;
			case 65: cureSpells.add(new Resurrect(this)); break;
			default: break;
			}
		}
	}
	
	
}
