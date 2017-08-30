package entity.mobs.enemies.mutants;

import main.Game;
import battle.spells.curative.Aura;
import battle.spells.curative.Rain;
import battle.spells.offensive.Bolt;
import battle.spells.offensive.Pulse;
import entity.mobs.enemies.Enemy;
import graphics.Sprite;
import graphics.SpriteSheet;

public class Hermit extends Enemy {
	
	public Hermit(int x, int y, int level, int num) { //controls spawn point/location of goon at any given frame		
		this.x = x << 5;
		this.y = y << 5;
		xa = 0;
		ya = 0;
		
		//STATS
		name = "Hermit";
		type = "Mutant";
		lv = level;
		expGiven = 10 * level; //Experience awarded for defeating mob
		moneyGiven = random.nextInt(5 * level) + 10;
		speed = 1;
		
		hp = maxHP = 70 * level;
		mp = maxMP = 500 * level;
		ep = maxEP = 0;
		
		pwr = basePwr = 10 * level;
		dex = baseDex = 3 * level;
		spd = baseSpd = 4 * level;
		evd = baseEvd = 3 * level;
		res = baseRes = 35 * level;
		mag = baseMag = 25 * level;
		def = baseDef = 1;
		magDef = baseMagDef = 4;
		
		fleeChance = 80 - (level * 5);
		mobNum = num;
		
		//Sprites
		up = new Sprite(32, 3, 0, SpriteSheet.mutants_1);
		up_1 = new Sprite(32, 3, 1, SpriteSheet.mutants_1);
		up_2 = new Sprite(32, 3, 2, SpriteSheet.mutants_1);
		
		down = new Sprite(32, 0, 0, SpriteSheet.mutants_1);
		down_1 = new Sprite(32, 0, 1, SpriteSheet.mutants_1);
		down_2 = new Sprite(32, 0, 2, SpriteSheet.mutants_1);
		
		right = new Sprite(32, 1, 0, SpriteSheet.mutants_1);
		right_1 = new Sprite(32, 1, 1, SpriteSheet.mutants_1);
		right_2 = new Sprite(32, 1, 2, SpriteSheet.mutants_1);
		
		left = new Sprite(32, 2, 0, SpriteSheet.mutants_1);
		left_1 = new Sprite(32, 2, 1, SpriteSheet.mutants_1);
		left_2 = new Sprite(32, 2, 2, SpriteSheet.mutants_1);
		
		ill = new Sprite(32, 4, 2, SpriteSheet.mutants_1);
		dead = new Sprite(32, 5, 0, SpriteSheet.mutants_1);
		hit = new Sprite(32, 5, 1, SpriteSheet.mutants_1);
		
		magic_1 = new Sprite(32, 4, 0, SpriteSheet.mutants_1);
		magic_2 = new Sprite(32, 4, 1, SpriteSheet.mutants_1);
		
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
		if (hp <= 25 && mp > 0) { //Heal
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
