package characters;

import graphics.Sprite;
import graphics.SpriteSheet;
import battle.skills.party.Booster;
import battle.skills.party.Disperse_Power;
import battle.skills.party.Encumber;
import battle.skills.party.Furious_Swings;
import battle.skills.party.Iron_Defense;
import battle.techs.curative.Cleanse;
import battle.techs.physical.Acid;
import battle.techs.physical.Gas;
import battle.techs.physical.Ion_Beam;
import battle.techs.physical.Laser;
import battle.techs.physical.Missiles;
import battle.techs.physical.Nuke;
import battle.techs.physical.Partial_Destruct;
import battle.techs.physical.Rocket_Punch;
import battle.techs.physical.Scatter_Shot;
import battle.techs.physical.Self_Destruct;
import battle.techs.physical.Shrapnel;
import battle.techs.physical.Spark;
import battle.techs.physical.Swing;
import battle.techs.physical.Tetnis;
import battle.techs.physical.Torch;
import battle.techs.physical.Toxins;

public class Jorg extends Playable {
	
	public Jorg() {
		name = "Jorg";
		lv = 1;
		index = 4;
		
		x = 0;
		y = 0;
		
		type = "Cyborg";
	
		hp = maxHP = 250;
		mp = maxMP = 0;
		ep = maxEP = 4;
	
		pwr = basePwr = 30;
		dex = baseDex = 5;
		spd = baseSpd = 4;
		evd = baseEvd = 3;
		res = baseRes = 2;
		mag = baseMag = 15;
		eng = baseEng = 0;
	
		def = baseDef = 3;
		magDef = baseMagDef = 1; 
		
		down = new Sprite(32, 8, 3, SpriteSheet.playable);
		down_1 = new Sprite(32, 8, 4, SpriteSheet.playable);
		down_2 = new Sprite(32, 8, 5, SpriteSheet.playable);
		right = new Sprite(32, 9, 3, SpriteSheet.playable);
		right_1 = new Sprite(32, 9, 4, SpriteSheet.playable);
		right_2 = new Sprite(32, 9, 5, SpriteSheet.playable);
		left = new Sprite(32, 10, 3, SpriteSheet.playable);
		left_1 = new Sprite(32, 10, 4, SpriteSheet.playable);
		left_2 = new Sprite(32, 10, 5, SpriteSheet.playable);
		up = new Sprite(32, 11, 3, SpriteSheet.playable);
		up_1 = new Sprite(32, 11, 4, SpriteSheet.playable);
		up_2 = new Sprite(32, 11, 5, SpriteSheet.playable);
	
		ill = new Sprite(32, 12, 3, SpriteSheet.playable);
		attack_1 = new Sprite(32, 12, 4, SpriteSheet.playable);
		magic_1 = new Sprite(32, 12, 5, SpriteSheet.playable);
		attack_2 = new Sprite(32, 13, 4, SpriteSheet.playable);
		magic_2 = new Sprite(32, 13, 5, SpriteSheet.playable);
	
		hit = new Sprite(32, 14, 3, SpriteSheet.playable);
		flee = new Sprite(32, 14, 4, SpriteSheet.playable);
		dead = new Sprite(32, 14, 5, SpriteSheet.playable);
		
		defend = new Sprite(32, 15, 3, SpriteSheet.playable);
		item = new Sprite(32, 15, 4, SpriteSheet.playable);
		skill = new Sprite(32, 15, 5, SpriteSheet.playable);
		
		sprite = right;
		
		restoreSpells();
	}
	
	public void levelUp() {
		maxHP *= 1.0545;
		if (maxHP > 9999) maxHP = 9999;
		basePwr = (int) Math.ceil(basePwr * 1.0875);
		baseMag = (int) Math.ceil(baseMag * 1.0525);
		
		if (lv % 3 == 0) {
			baseDex += 2;
			baseSpd += 2;
			baseEvd += 2;
			baseRes++;
		}
		else if (lv % 2 == 0){
			baseDex += 2;
			baseSpd += 2;
			baseEvd ++;
			baseRes++;
		}
		else {
			baseDex += 2;
			baseSpd += 1;
			baseEvd++;
			baseRes++;
		}
		
		if (lv % 4 == 0) ep++;
		//if (lv == 30 || lv == 60) ep++;
		
		levelRestore();
		learnSpells();
	}
	
	public void learnSpells() {
		switch(lv) {
		case 1: learnSpell(new Swing(this)); break;
		case 5: learnSpell(new Rocket_Punch(this)); break;
		case 9: learnSkill(new Booster(this)); break;
		case 10: learnSpell(new Missiles(this)); break;
		case 11: learnSpell(new Acid(this)); break;
		case 12: learnSpell(new Spark(this)); break;
		case 13: learnSpell(new Torch(this)); break;
		case 14: learnSpell(new Toxins(this)); break;
		case 15: learnSpell(new Tetnis(this)); break;
		case 18: learnSpell(new Gas(this)); break;
		case 20: learnSpell(new Shrapnel(this)); break;
		case 23: learnSkill(new Encumber(this)); break;
		case 25: learnSpell(new Self_Destruct(this)); break;
		case 29: learnSkill(new Disperse_Power(this)); break;
		case 30: learnSpell(new Laser(this)); break;
		case 35: learnSpell(new Partial_Destruct(this)); break;
		case 40: learnSpell(new Scatter_Shot(this)); break;
		case 43: learnSkill(new Furious_Swings(this)); break;
		case 45: learnSpell(new Cleanse(this)); break;
		case 50: learnSpell(new Nuke(this)); break;
		case 60: learnSpell(new Ion_Beam(this)); break;
		
		default: break;
		}
	}
	
	public void restoreSpells() {
		resetSpells();
		for (int i = 0; i <= lv; i++) {
			switch(i) {
			case 1: offSpells.add(new Swing(this)); skills.add(new Iron_Defense(this)); break;
			case 5: offSpells.add(new Rocket_Punch(this)); break;
			case 9: skills.add(new Booster(this)); break;
			case 10: offSpells.add(new Missiles(this)); break;
			case 11: offSpells.add(new Acid(this)); break;
			case 12: offSpells.add(new Spark(this)); break;
			case 13: offSpells.add(new Torch(this)); break;
			case 14: offSpells.add(new Toxins(this)); break;
			case 15: offSpells.add(new Tetnis(this)); break;
			case 18: offSpells.add(new Gas(this)); break;
			case 20: offSpells.add(new Shrapnel(this)); break;
			case 23: skills.add(new Encumber(this)); break;
			case 25: offSpells.add(new Self_Destruct(this)); break;
			case 29: skills.add(new Disperse_Power(this)); break;
			case 30: offSpells.add(new Laser(this)); break;
			case 35: offSpells.add(new Partial_Destruct(this)); break;
			case 40: offSpells.add(new Scatter_Shot(this)); break;
			case 43: skills.add(new Furious_Swings(this)); break;
			case 45: cureSpells.add(new Cleanse(this)); break;
			case 50: offSpells.add(new Nuke(this)); break;
			case 60: offSpells.add(new Ion_Beam(this)); break;
			
			default: break;
			}
		}
	}
}
