package entity.mobs.enemies.mutants;

import states.Battle;
import main.Game;
import battle.spells.defensive.Field;
import battle.spells.defensive.Protect;
import battle.spells.offensive.Bolt;
import entity.mobs.enemies.Enemy;
import graphics.Sprite;
import graphics.SpriteSheet;

public class Crystal_Skeleton extends Enemy {
	
	public Crystal_Skeleton(int x, int y, int level, int num) { //controls spawn point/location of goon at any given frame		
		this.x = x << 5;
		this.y = y << 5;
		xa = 0;
		ya = 0;
		
		//STATS
		name = "Crystal Skeleton";
		type = "Mutant";
		lv = level;
		expGiven = 18 * level; //Experience awarded for defeating mob
		moneyGiven = random.nextInt(5 * level) + 15;
		speed = 2;
		chaseRange = 2 << 5;
		
		hp = maxHP = 80 * level;
		mp = maxMP = 500 * level;
		ep = maxEP = 0;
		
		pwr = basePwr = 70 * level;
		dex = baseDex = 15 * level;
		spd = baseSpd = 10 * level;
		evd = baseEvd = 17 * level;
		res = baseRes = 50 * level;
		mag = baseMag = 25 * level;
		def = baseDef = 2;
		magDef = baseMagDef = 3;
		
		fleeChance = 65 - (level * 5);
		mobNum = num;
		
		//Sprites
		up = new Sprite(32, 15, 0, SpriteSheet.mutants_1);
		up_1 = new Sprite(32, 15, 1, SpriteSheet.mutants_1);
		up_2 = new Sprite(32, 15, 2, SpriteSheet.mutants_1);
		
		down = new Sprite(32, 14, 0, SpriteSheet.mutants_1);
		down_1 = new Sprite(32, 14, 1, SpriteSheet.mutants_1);
		down_2 = new Sprite(32, 14, 2, SpriteSheet.mutants_1);
		
		right = new Sprite(32, 17, 0, SpriteSheet.mutants_1);
		right_1 = new Sprite(32, 17, 1, SpriteSheet.mutants_1);
		right_2 = new Sprite(32, 17, 2, SpriteSheet.mutants_1);
		
		left = new Sprite(32, 16, 0, SpriteSheet.mutants_1);
		left_1 = new Sprite(32, 16, 1, SpriteSheet.mutants_1);
		left_2 = new Sprite(32, 16, 2, SpriteSheet.mutants_1);
		
		ill = left;
		dead = new Sprite(32, 18, 2, SpriteSheet.mutants_1);
		hit = new Sprite(32, 13, 2, SpriteSheet.mutants_1);
		
		magic_1 = new Sprite(32, 19, 0, SpriteSheet.mutants_1);
		magic_2 = new Sprite(32, 19, 1, SpriteSheet.mutants_1);
		
		attack_1 = new Sprite(32, 18, 0, SpriteSheet.mutants_1);
		attack_2 = new Sprite(32, 18, 1, SpriteSheet.mutants_1);
		
		sprite = down;
		
		//Move Selection
		moves = new int[] {1, 2, 4};
		
		attacks = true;
		offSpells.add(new Bolt(this));
		defSpells.add(new Protect(this));
		defSpells.add(new Field(this));
	}
	
	public void update() {
		if (Game.State == Game.STATE.GAME) {
			chaserMovement();     
		}
	}
	
	public void behavior() {
		if (Battle.turnNumber % 2 == 0) {
			if (mp > 0) {
				choice = moves[random.nextInt(2)+1];
				if (choice == 2) spellChosen = offSpells.get(random.nextInt(offSpells.size()));
				else if (choice == 4) spellChosen = defSpells.get(random.nextInt(defSpells.size()));
			}
			else choice = 1;
		}
		else choice = 1;
	}
	
}
