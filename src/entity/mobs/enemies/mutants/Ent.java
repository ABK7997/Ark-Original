package entity.mobs.enemies.mutants;

import states.Battle;
import battle.spells.curative.Rain;
import battle.spells.defensive.Protect;
import battle.spells.offensive.Bolt;
import battle.spells.offensive.Pulse;
import entity.mobs.enemies.Enemy;
import graphics.Sprite;
import graphics.SpriteSheet;

public class Ent extends Enemy {
	
	public Ent(int x, int y, int level, int num) { //controls spawn point/location of goon at any given frame		
		this.x = x << 5;
		this.y = y << 5;
		
		//STATS
		name = "Ent";
		type = "Mutant";
		lv = level;
		expGiven = 12 * level; //Experience awarded for defeating mob
		moneyGiven = random.nextInt(7 * level) + 15;
		
		hp = maxHP = 100 * level;
		mp = maxMP = 300 * level;
		ep = maxEP = 0;
		
		pwr = basePwr = 70 * level;
		dex = baseDex = 10 * level;
		spd = baseSpd = 1 * level;
		evd = baseEvd = 5 * level;
		res = baseRes = 25 * level;
		mag = baseMag = 18 * level;
		def = baseDef = 2;
		magDef = baseMagDef = 4;
		
		fleeChance = 70 - (level * 5);
		mobNum = num;
		encounterRange = 64;
		
		//Sprites
		left = new Sprite(64, 0, 0, SpriteSheet.mutants_2);
		left_1 = new Sprite(64, 0, 0, SpriteSheet.mutants_2);
		left_2 = new Sprite(64, 0, 0, SpriteSheet.mutants_2);
		
		right = new Sprite(64, 0, 0, SpriteSheet.mutants_2);
		right_1 = new Sprite(64, 0, 0, SpriteSheet.mutants_2);
		right_2 = new Sprite(64, 0, 0, SpriteSheet.mutants_2);
		
		up = new Sprite(64, 0, 0, SpriteSheet.mutants_2);
		up_1 = new Sprite(64, 0, 0, SpriteSheet.mutants_2);
		up_2 = new Sprite(64, 0, 0, SpriteSheet.mutants_2);
		
		down = new Sprite(64, 0, 0, SpriteSheet.mutants_2);
		down_1 = new Sprite(64, 0, 0, SpriteSheet.mutants_2);
		down_2 = new Sprite(64, 0, 0, SpriteSheet.mutants_2);
		
		ill = new Sprite(64, 2, 0, SpriteSheet.mutants_2);
		dead = new Sprite(64, 3, 0, SpriteSheet.mutants_2);
		hit = new Sprite(64, 2, 1, SpriteSheet.mutants_2);
		
		attack_1 = new Sprite(64, 1, 0, SpriteSheet.mutants_2);
		attack_2 = new Sprite(64, 0, 1, SpriteSheet.mutants_2);
		
		magic_1 = new Sprite(64, 1, 1, SpriteSheet.mutants_2);
		magic_2 = new Sprite(64, 1, 0, SpriteSheet.mutants_2);
		
		sprite = left;
		
		//Move Selection
		moves = new int[] {1, 2, 4};
		
		attacks = false;
		offSpells.add(new Bolt(this));
		offSpells.add(new Pulse(this));
		cureSpells.add(new Rain(this));
		defSpells.add(new Protect(this));
	}
	
	public void update() {
		noMovement();
	}
	
	public void behavior() {
		if (hp <= 30) {
			choice = 3;
			spellChosen = cureSpells.get(random.nextInt(cureSpells.size()));
		}
		
		else if (Battle.turnNumber % 2 == 0) {
			if (mp > 0) {
				choice = moves[random.nextInt(2)+1];
			} else choice = 1;
			if (choice == 2) spellChosen = offSpells.get(random.nextInt(offSpells.size()));
			else if (choice == 4) spellChosen = defSpells.get(0);
		}
		else choice = 1;
	}
	
}
