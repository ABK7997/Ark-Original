package entity.mobs.enemies.mutants;

import main.Game;
import battle.spells.curative.Aura;
import battle.spells.curative.Rain;
import battle.spells.offensive.Bolt;
import battle.spells.offensive.Pulse;
import entity.mobs.enemies.Enemy;
import graphics.Sprite;
import graphics.SpriteSheet;

public class Crystal_Mage extends Enemy {
	
	public Crystal_Mage(int x, int y, int level, int num) { //controls spawn point/location of goon at any given frame		
		this.x = x << 5;
		this.y = y << 5;
		xa = 0;
		ya = 0;
		
		//STATS
		name = "Crystal Mage";
		type = "Mutant";
		lv = level;
		expGiven = 15 * level; //Experience awarded for defeating mob
		moneyGiven = random.nextInt(9 * level) + 18;
		speed = 1;
		
		hp = maxHP = 125 * level;
		mp = maxMP = 999 * level;
		ep = maxEP = 0;
		
		pwr = basePwr = 20 * level;
		dex = baseDex = 5 * level;
		spd = baseSpd = 6 * level;
		evd = baseEvd = 6 * level;
		res = baseRes = 75 * level;
		mag = baseMag = 30 * level;
		def = baseDef = 1;
		magDef = baseMagDef = 4;
		
		fleeChance = 85 - (level * 5);
		mobNum = num;
		
		//Sprites
		up = new Sprite(32, 9, 0, SpriteSheet.mutants_1);
		up_1 = new Sprite(32, 9, 1, SpriteSheet.mutants_1);
		up_2 = new Sprite(32, 9, 2, SpriteSheet.mutants_1);
		
		down = new Sprite(32, 6, 0, SpriteSheet.mutants_1);
		down_1 = new Sprite(32, 6, 1, SpriteSheet.mutants_1);
		down_2 = new Sprite(32, 6, 2, SpriteSheet.mutants_1);
		
		right = new Sprite(32, 7, 0, SpriteSheet.mutants_1);
		right_1 = new Sprite(32, 7, 1, SpriteSheet.mutants_1);
		right_2 = new Sprite(32, 7, 2, SpriteSheet.mutants_1);
		
		left = new Sprite(32, 8, 0, SpriteSheet.mutants_1);
		left_1 = new Sprite(32, 8, 1, SpriteSheet.mutants_1);
		left_2 = new Sprite(32, 8, 2, SpriteSheet.mutants_1);
		
		ill = new Sprite(32, 10, 2, SpriteSheet.mutants_1);
		dead = new Sprite(32, 11, 0, SpriteSheet.mutants_1);
		hit = new Sprite(32, 11, 1, SpriteSheet.mutants_1);
		
		magic_1 = new Sprite(32, 10, 0, SpriteSheet.mutants_1);
		magic_2 = new Sprite(32, 10, 1, SpriteSheet.mutants_1);
		
		attack_1 = left;
		attack_2 = magic_2;
		
		sprite = down;
		
		//Move Selection
		moves = new int[] {1, 2, 3};
		
		attacks = true;
		offSpells.add(new Bolt(this));
		offSpells.add(new Pulse(this));
		cureSpells.add(new Aura(this));
		cureSpells.add(new Rain(this));
	}
	
	public void update() {
		if (Game.State == Game.STATE.GAME) {
			randomMovement();
		}
	}
	
	public void behavior() {
		if (hp <= 50 && mp > 0) { //Heal
			choice = 3;
			spellChosen = cureSpells.get(random.nextInt(cureSpells.size()));
		}
		else if (mp > 0) { //Attack
			choice = 2;
			spellChosen = offSpells.get(random.nextInt(offSpells.size()));
		}
		else choice = 1; //Out of MP; weak physical attack
	}
	
}
