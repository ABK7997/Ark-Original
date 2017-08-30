package entity.mobs.enemies.mutants;

import main.Game;
import battle.spells.offensive.Bolt;
import battle.spells.offensive.Poison_Pin;
import battle.spells.offensive.Shock;
import battle.techs.physical.Acid;
import entity.mobs.enemies.Enemy;
import graphics.Sprite;
import graphics.SpriteSheet;

public class Crystal_Spirit extends Enemy {
	
	public Crystal_Spirit(int x, int y, int level, int num) { //controls spawn point/location of goon at any given frame		
		this.x = x << 5;
		this.y = y << 5;
		xa = 0;
		ya = 0;
		
		//STATS
		name = "Crystal Spirit";
		type = "Mutant";
		lv = level;
		expGiven = 18 * level; //Experience awarded for defeating mob
		moneyGiven = random.nextInt(5 * level) + 15;
		speed = 2;
		chaseRange = 4 << 5;
		
		hp = maxHP = 150 * level;
		mp = maxMP = 999 * level;
		ep = maxEP = 0;
		
		pwr = basePwr = 45 * level;
		dex = baseDex = 1 * level;
		spd = baseSpd = 1 * level;
		evd = baseEvd = 4 * level;
		res = baseRes = 100 * level;
		mag = baseMag = 35 * level;
		def = baseDef = 1;
		magDef = baseMagDef = 6;
		
		fleeChance = 75 - (level * 5);
		mobNum = num;
		
		//Sprites
		up = new Sprite(32, 12, 0, SpriteSheet.mutants_1);
		up_1 = up;
		up_2 = up;
		
		down = up;
		down_1 = up;
		down_2 = up;
		
		right = up;
		right_1 = up;
		right_2 = up;
		
		left = up;
		left_1 = up;
		left_2 = up;
		
		ill = up;
		
		magic_1 = new Sprite(32, 13, 0, SpriteSheet.mutants_1);
		magic_2 = new Sprite(32, 13, 1, SpriteSheet.mutants_1);
		
		hit = left;
		skill = new Sprite(32, 12, 1, SpriteSheet.mutants_1);
		dead = new Sprite(32, 12, 2, SpriteSheet.mutants_1);
		
		sprite = down;
		
		//Move Selection
		moves = new int[] {2};
		
		attacks = false;
		offSpells.add(new Bolt(this));
		offSpells.add(new Poison_Pin(this));
		offSpells.add(new Acid(this));
		offSpells.add(new Shock(this));
	}
	
	public void update() {
		if (Game.State == Game.STATE.GAME) {
			chaserMovement();     
		}
	}
	
	public void behavior() {
		if (mp > 0) choice = 2;
		else choice = 0;
		
		spellChosen = offSpells.get(random.nextInt(offSpells.size()));
	}
	
}
