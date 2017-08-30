package characters;

import graphics.Sprite;
import graphics.SpriteSheet;
import battle.skills.party.Blood_Donor;
import battle.skills.party.Charge;
import battle.skills.party.Immunity;
import battle.skills.party.Magic_Guard;
import battle.skills.party.Reduce;
import battle.spells.curative.Aura;
import battle.spells.curative.Aura2;
import battle.spells.curative.Aura3;
import battle.spells.curative.Resurrect;
import battle.spells.curative.Revive;
import battle.spells.offensive.*;

public class Dex extends Playable {
	
	public Dex() {
		name = "Dex";
		lv = 3;
		index = 3;
		exp = 36;
		nextLevel = 72;
		
		x = 0;
		y = 0;
		
		type = "Mutant";
	
		hp = maxHP = 165;
		mp = maxMP = 94;
		ep = maxEP = 0;
	
		pwr = basePwr = 12;
		dex = baseDex = 5;
		spd = baseSpd = 6;
		evd = baseEvd = 6;
		res = baseRes = 11;
		mag = baseMag = 22;
		eng = baseEng = 0;
	
		def = baseDef = 1;
		magDef = baseMagDef = 4; 
		
		down = new Sprite(32, 0, 3, SpriteSheet.playable);
		down_1 = new Sprite(32, 0, 4, SpriteSheet.playable);
		down_2 = new Sprite(32, 0, 5, SpriteSheet.playable);
		right = new Sprite(32, 1, 3, SpriteSheet.playable);
		right_1 = new Sprite(32, 1, 4, SpriteSheet.playable);
		right_2 = new Sprite(32, 1, 5, SpriteSheet.playable);
		left = new Sprite(32, 2, 3, SpriteSheet.playable);
		left_1 = new Sprite(32, 2, 4, SpriteSheet.playable);
		left_2 = new Sprite(32, 2, 5, SpriteSheet.playable);
		up = new Sprite(32, 3, 3, SpriteSheet.playable);
		up_1 = new Sprite(32, 3, 4, SpriteSheet.playable);
		up_2 = new Sprite(32, 3, 5, SpriteSheet.playable);
	
		ill = new Sprite(32, 4, 3, SpriteSheet.playable);
		attack_1 = new Sprite(32, 4, 4, SpriteSheet.playable);
		magic_1 = new Sprite(32, 4, 5, SpriteSheet.playable);
		attack_2 = new Sprite(32, 5, 4, SpriteSheet.playable);
		magic_2 = new Sprite(32, 5, 5, SpriteSheet.playable);
	
		hit = new Sprite(32, 6, 3, SpriteSheet.playable);
		flee = new Sprite(32, 6, 4, SpriteSheet.playable);
		dead = new Sprite(32, 6, 5, SpriteSheet.playable);
		
		defend = new Sprite(32, 7, 3, SpriteSheet.playable);
		item = new Sprite(32, 7, 4, SpriteSheet.playable);
		skill = new Sprite(32, 7, 5, SpriteSheet.playable);
		
		sprite = right;
		
		restoreSpells();
	}
	
	//STATUS IMMUNITES
	public boolean getCorroding() {
		corroding = false;
		return false;
	}
	
	public void levelUp() {
		maxHP *= 1.048;
		maxMP *= 1.0851;
		if (maxHP > 9999) maxHP = 9999;
		if (maxMP > 999) maxMP = 999; 
		basePwr = (int) Math.ceil(baseMag * 1.079);
		baseMag = (int) Math.ceil(baseMag * 1.0541);
		
		if (lv % 3 == 0) {
			baseDex++;
			baseSpd+=2;
			baseEvd+=2;
			baseRes+=3;
		}
		else if (lv % 2 == 0) {
			baseDex++;
			baseSpd++;
			baseEvd+=2;
			baseRes+=2;
		}
		else {
			baseSpd++;
			baseEvd++;
			baseRes+=2;
		}
		levelRestore();
		learnSpells();
	}
	
	//Learn Spells
		public void learnSpells() {
			switch(lv) {
			case 3: learnSpell(new Poison_Pin(this)); break;
			case 5: learnSpell(new Spray(this)); break;
			case 9: learnSpell(new Rust(this)); break;
			case 10: learnSpell(new Aura(this)); break;
			case 11: learnSkill(new Immunity(this)); break;
			case 12: learnSpell(new Pulse(this)); break;
			case 15: learnSpell(new Bolt2(this)); break;
			case 18: learnSpell(new Shock(this)); break;
			case 20: learnSpell(new Spray2(this)); break;
			case 22: learnSkill(new Blood_Donor(this)); break;
			case 25: learnSpell(new Burn(this)); break;
			case 27: learnSpell(new Infection(this)); break;
			case 29: learnSkill(new Magic_Guard(this)); break;
			case 30: learnSpell(new Pulse2(this)); break;
			case 31: learnSpell(new Drain(this)); break;
			case 32: learnSpell(new Aura2(this)); break;
			case 33: learnSpell(new Drug(this)); break;
			case 37: learnSpell(new Vampire(this)); break;
			case 40: learnSpell(new Bolt3(this)); break;
			case 45: learnSkill(new Reduce(this)); break;
			case 50: learnSpell(new Spray3(this)); break;
			case 55: learnSpell(new Revive(this)); break;
			case 60: learnSpell(new Pulse3(this)); break;
			case 62: learnSpell(new Aura3(this)); break;
			case 80: learnSpell(new Resurrect(this)); break;
			}
		}
	
	
	
	//Load Spells
		public void restoreSpells() {
			resetSpells();
			for (int i = 0; i <= lv; i++) {
				switch(i) {
				case 1: offSpells.add(new Bolt(this)); skills.add(new Charge(this)); break;
				case 3: offSpells.add(new Poison_Pin(this)); break;
				case 5: offSpells.add(new Spray(this)); break;
				case 9: offSpells.add(new Rust(this)); break;
				case 10: cureSpells.add(new Aura(this)); break;
				case 11: skills.add(new Immunity(this)); break;
				case 12: offSpells.add(new Pulse(this)); break;
				case 15: offSpells.add(new Bolt2(this)); break;
				case 18: offSpells.add(new Shock(this)); break;
				case 20: offSpells.add(new Spray2(this)); break;
				case 22: skills.add(new Blood_Donor(this)); break;
				case 25: offSpells.add(new Burn(this)); break;
				case 27: offSpells.add(new Infection(this)); break;
				case 29: skills.add(new Magic_Guard(this)); break;
				case 30: offSpells.add(new Pulse2(this)); break;
				case 31: offSpells.add(new Drain(this)); break;
				case 32: cureSpells.add(new Aura2(this)); break;
				case 33: offSpells.add(new Drug(this)); break;
				case 37: offSpells.add(new Vampire(this)); break;
				case 40: offSpells.add(new Bolt3(this)); break;
				case 45: skills.add(new Reduce(this)); break;
				case 50: offSpells.add(new Spray3(this)); break;
				case 55: cureSpells.add(new Revive(this)); break;
				case 60: offSpells.add(new Pulse3(this)); break;
				case 62: cureSpells.add(new Aura3(this)); break;
				case 80: cureSpells.add(new Resurrect(this)); break;
				default: break;
				}
			}
		}
}
