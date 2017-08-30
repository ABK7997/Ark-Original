package entity.mobs.enemies.organics;

import states.Battle;
import battle.spells.offensive.Poison_Pin;
import battle.spells.offensive.Shock;
import main.Game;
import entity.mobs.enemies.Enemy;
import graphics.Sprite;
import graphics.SpriteSheet;

public class Frog extends Enemy {
	
	public Frog(int x, int y, int level, int num) { //controls spawn point/location of goon at any given frame		
		this.x = x << 5;
		this.y = y << 5;
		xa = 0;
		ya = 0;
		
		//STATS
		name = "Swamp Frog";
		type = "Organic";
		lv = level;
		expGiven = 3 * level; //Experience awarded for defeating mob
		moneyGiven = random.nextInt(5 * level) + 20;
		speed = 3;
		
		hp = maxHP = 75 * level;
		mp = maxMP = 30 * level;
		ep = maxEP = 0;
		
		pwr = basePwr = 35 * level;
		dex = baseDex = 12 * level;
		spd = baseSpd = 10 * level;
		evd = baseEvd = 12 * level;
		res = baseRes = 20 * level;
		mag = baseMag = 15 * level;
		def = baseDef = 2;
		magDef = baseMagDef = 2;
		
		fleeChance = 95 - (level * 5);
		mobNum = num;
		
		//Sprites
		up = new Sprite(32, 13, 0, SpriteSheet.organics_1);
		up_1 = new Sprite(32, 13, 1, SpriteSheet.organics_1);
		up_2 = new Sprite(32, 13, 1, SpriteSheet.organics_1);
		
		down = new Sprite(32, 12, 0, SpriteSheet.organics_1);
		down_1 = new Sprite(32, 12, 1, SpriteSheet.organics_1);
		down_2 = new Sprite(32, 12, 1, SpriteSheet.organics_1);
		
		right = new Sprite(32, 15, 0, SpriteSheet.organics_1);
		right_1 = new Sprite(32, 15, 1, SpriteSheet.organics_1);
		right_2 = new Sprite(32, 15, 1, SpriteSheet.organics_1);
		
		left = new Sprite(32, 14, 0, SpriteSheet.organics_1);
		left_1 = new Sprite(32, 14, 1, SpriteSheet.organics_1);
		left_2 = new Sprite(32, 14, 1, SpriteSheet.organics_1);
		
		attack_1 = new Sprite(32, 14, 0, SpriteSheet.organics_1);
		attack_2 = new Sprite(32, 14, 1, SpriteSheet.organics_1);
		
		ill = new Sprite(32, 15, 2, SpriteSheet.organics_1);
		dead = new Sprite(32, 14, 2, SpriteSheet.organics_1);
		hit = new Sprite(32, 13, 2, SpriteSheet.organics_1);
		
		magic_1 = new Sprite(32, 14, 0, SpriteSheet.organics_1);
		magic_2 = new Sprite(32, 12, 2, SpriteSheet.organics_1);
		
		sprite = down;
		
		//Move Selection
		moves = new int[] {1, 2};
		
		attacks = true;
		offSpells.add(new Shock(this));
		offSpells.add(new Poison_Pin(this));
	}
	
	public void update() {
		if (Game.State == Game.STATE.GAME) {
			randomMovement();
		}
	}
	
	public void behavior() {
		if (Battle.turnNumber % 2 == 0 && mp > 0) {
			choice = moves[random.nextInt(2)];
			spellChosen = offSpells.get(random.nextInt(offSpells.size()));
		}
		else choice = 1;
	}
	
}
