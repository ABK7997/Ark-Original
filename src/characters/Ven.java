package characters;

import graphics.Sprite;
import graphics.SpriteSheet;
import battle.skills.party.Auto_Immune;
import battle.skills.party.Drain_Burst;
import battle.skills.party.Magic_Swing;
import battle.skills.party.Muscled;
import battle.skills.party.Sacrifice;
import battle.spells.curative.Rain;
import battle.spells.curative.Rain2;
import battle.spells.curative.Rain3;
import battle.spells.defensive.Barrier;
import battle.spells.defensive.Field;
import battle.spells.defensive.Focus;
import battle.spells.defensive.Invisible;
import battle.spells.defensive.Leech;
import battle.spells.defensive.Omni_Barrier;
import battle.spells.defensive.Omni_Field;
import battle.spells.defensive.Omni_Protect;
import battle.spells.defensive.Parasite;
import battle.spells.defensive.Protect;
import battle.spells.defensive.Super;
import battle.spells.offensive.Cripple;
import battle.spells.offensive.Disable;
import battle.spells.offensive.Drain;
import battle.spells.offensive.Maim;
import battle.spells.offensive.Pulse;
import battle.spells.offensive.Pulse2;
import battle.spells.offensive.Pulse3;
import battle.spells.offensive.Vampire;
import battle.spells.offensive.Weaken;

public class Ven extends Playable {
	
	public Ven() {
		name = "Ven";
		lv = 1;
		index = 6;
		
		x = 0;
		y = 0;
		
		type = "Hybrid";
	
		hp = maxHP = 175;
		mp = maxMP = 60;
		ep = maxEP = 0;
	
		pwr = basePwr = 20;
		dex = baseDex = 4;
		spd = baseSpd = 5;
		evd = baseEvd = 5;
		res = baseRes = 5;
		mag = baseMag = 17;
		eng = baseEng = 0;
	
		def = baseDef = 1;
		magDef = baseMagDef = 3; 
		
		down = new Sprite(32, 8, 6, SpriteSheet.playable);
		down_1 = new Sprite(32, 8, 7, SpriteSheet.playable);
		down_2 = new Sprite(32, 8, 8, SpriteSheet.playable);
		right = new Sprite(32, 9, 6, SpriteSheet.playable);
		right_1 = new Sprite(32, 9, 7, SpriteSheet.playable);
		right_2 = new Sprite(32, 9, 8, SpriteSheet.playable);
		left = new Sprite(32, 10, 6, SpriteSheet.playable);
		left_1 = new Sprite(32, 10, 7, SpriteSheet.playable);
		left_2 = new Sprite(32, 10, 8, SpriteSheet.playable);
		up = new Sprite(32, 11, 6, SpriteSheet.playable);
		up_1 = new Sprite(32, 11, 7, SpriteSheet.playable);
		up_2 = new Sprite(32, 11, 8, SpriteSheet.playable);
	
		ill = new Sprite(32, 12, 6, SpriteSheet.playable);
		attack_1 = new Sprite(32, 12, 7, SpriteSheet.playable);
		magic_1 = new Sprite(32, 12, 8, SpriteSheet.playable);
		attack_2 = new Sprite(32, 13, 7, SpriteSheet.playable);
		magic_2 = new Sprite(32, 13, 8, SpriteSheet.playable);
	
		hit = new Sprite(32, 14, 6, SpriteSheet.playable);
		flee = new Sprite(32, 14, 7, SpriteSheet.playable);
		dead = new Sprite(32, 14, 8, SpriteSheet.playable);
		
		defend = new Sprite(32, 15, 6, SpriteSheet.playable);
		item = new Sprite(32, 15, 7, SpriteSheet.playable);
		skill = new Sprite(32, 15, 8, SpriteSheet.playable);
		
		sprite = right;
		
		restoreSpells();
	}
	
	//STATUS IMMUNITES
	public boolean getCorroding() {
		corroding = false;
		return false;
	}
	
	public void levelUp() {
		maxHP *= 1.049;
		maxMP *= 1.0795;
		if (maxHP > 9999) maxHP = 9999;
		if (maxMP > 999) maxMP = 999; 
		basePwr = (int) Math.ceil(basePwr * 1.082);
		baseMag = (int) Math.ceil(baseMag * 1.0525);
		
		if (lv % 3 == 0) {
			baseDex += 2;
			baseSpd += 2;
			baseEvd += 2;
			baseRes += 2;
		}
		else if (lv % 2 == 0){
			baseDex += 2;
			baseSpd += 2;
			baseEvd += 2;
			baseRes += 2;
		}
		else {
			baseDex++;
			baseSpd += 2;
			baseEvd += 2;
			baseRes += 2;
		}
		
		levelRestore();
		learnSpells();
	}
	
	public void learnSpells() {
		switch(lv) {
		case 3: learnSpell(new Field(this)); break;
		case 5: learnSpell(new Focus(this)); break;
		case 8: learnSpell(new Rain(this)); break;
		case 9: learnSpell(new Pulse(this)); break;
		case 10: learnSpell(new Weaken(this)); break;
		case 12: learnSpell(new Cripple(this)); break;
		case 15: learnSpell(new Vampire(this)); break;
		case 17: learnSpell(new Leech(this)); break;
		case 20: learnSkill(new Sacrifice(this)); break;
		case 23: learnSpell(new Pulse2(this)); break;
		case 25: learnSpell(new Rain2(this)); break;
		case 26: learnSkill(new Magic_Swing(this)); break;
		case 28: learnSpell(new Barrier(this)); break;
		case 30: learnSpell(new Drain(this)); break;
		case 32: learnSpell(new Parasite(this)); break;
		case 35: learnSkill(new Auto_Immune(this)); break;
		case 40: learnSpell(new Omni_Protect(this)); break;
		case 42: learnSpell(new Omni_Field(this)); break;
		case 45: learnSpell(new Maim(this)); break;
		case 46: learnSpell(new Pulse3(this)); break;
		case 47: learnSpell(new Disable(this)); break;
		case 50: learnSpell(new Omni_Barrier(this)); break;
		case 52: learnSpell(new Rain3(this)); break;
		case 55: learnSkill(new Muscled(this)); break;
		case 60: learnSpell(new Invisible(this)); break;
		case 65: learnSpell(new Super(this)); break;
		default: break;
		}
	}
	
	public void restoreSpells() {
		resetSpells();
		for (int i = 0; i <= lv+1; i++) {
			switch(i) {
			case 1: defSpells.add(new Protect(this)); skills.add(new Drain_Burst(this)); break;
			case 3: defSpells.add(new Field(this)); break;
			case 5: defSpells.add(new Focus(this)); break;
			case 8: cureSpells.add(new Rain(this)); break;
			case 9: offSpells.add(new Pulse(this)); break;
			case 10: offSpells.add(new Weaken(this)); break;
			case 12: offSpells.add(new Cripple(this)); break;
			case 15: offSpells.add(new Vampire(this)); break;
			case 17: defSpells.add(new Leech(this)); break;
			case 20: skills.add(new Sacrifice(this)); break;
			case 23: offSpells.add(new Pulse2(this)); break;
			case 25: cureSpells.add(new Rain2(this)); break;
			case 26: skills.add(new Magic_Swing(this)); break;
			case 28: defSpells.add(new Barrier(this)); break;
			case 30: offSpells.add(new Drain(this)); break;
			case 32: defSpells.add(new Parasite(this)); break;
			case 35: skills.add(new Auto_Immune(this)); break;
			case 40: defSpells.add(new Omni_Protect(this)); break;
			case 42: defSpells.add(new Omni_Field(this)); break;
			case 45: offSpells.add(new Maim(this)); break;
			case 46: offSpells.add(new Pulse3(this)); break;
			case 47: offSpells.add(new Disable(this)); break;
			case 50: defSpells.add(new Omni_Barrier(this)); break;
			case 52: cureSpells.add(new Rain3(this)); break;
			case 55: skills.add(new Muscled(this));
			case 60: defSpells.add(new Invisible(this)); break;
			case 65: defSpells.add(new Super(this)); break;
			default: break;
			}
		}
	}
}
