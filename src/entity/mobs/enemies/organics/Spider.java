package entity.mobs.enemies.organics;

import battle.spells.offensive.Poison_Pin;
import states.Battle;
import main.Game;
import entity.mobs.enemies.Enemy;
import graphics.Sprite;
import graphics.SpriteSheet;

public class Spider extends Enemy {
	
	public Spider(int x, int y, int level, int num) { //controls spawn point/location of goon at any given frame		
		this.x = x << 5;
		this.y = y << 5;
		xa = 0;
		ya = 0;
		
		//STATS
		name = "Spider";
		type = "Organic";
		lv = level;
		expGiven = 5 * level; //Experience awarded for defeating mob
		moneyGiven = random.nextInt(5 * level) + 10;
		chaseRange = 4 << 5;
		
		hp = maxHP = 50 * level;
		mp = maxMP = 15 * level;
		ep = maxEP = 0;
		
		pwr = basePwr = 35 * level;
		dex = baseDex = 8 * level;
		spd = baseSpd = 8 * level;
		evd = baseEvd = 6 * level;
		res = baseRes = 5 * level;
		mag = baseMag = 10 * level;
		def = baseDef = 2;;
		magDef = baseMagDef = 2;
		
		fleeChance = 100 - (level * 5);
		mobNum = num;
		
		//Sprites
		up = new Sprite(32, 6, 0, SpriteSheet.organics_1);
		up_1 = new Sprite(32, 6, 1, SpriteSheet.organics_1);
		up_2 = new Sprite(32, 6, 1, SpriteSheet.organics_1);
		
		down = new Sprite(32, 5, 0, SpriteSheet.organics_1);
		down_1 = new Sprite(32, 5, 1, SpriteSheet.organics_1);
		down_2 = new Sprite(32, 5, 1, SpriteSheet.organics_1);
		
		right = new Sprite(32, 8, 0, SpriteSheet.organics_1);
		right_1 = new Sprite(32, 8, 1, SpriteSheet.organics_1);
		right_2 = new Sprite(32, 8, 1, SpriteSheet.organics_1);
		
		left = new Sprite(32, 7, 0, SpriteSheet.organics_1);
		left_1 = new Sprite(32, 7, 1, SpriteSheet.organics_1);
		left_2 = new Sprite(32, 7, 1, SpriteSheet.organics_1);
		
		attack_1 = new Sprite(32, 7, 1, SpriteSheet.organics_1);
		attack_2 = new Sprite(32, 7, 2, SpriteSheet.organics_1);
		
		magic_1 = new Sprite(32, 5, 2, SpriteSheet.organics_1);
		magic_2 = new Sprite(32, 7, 2, SpriteSheet.organics_1);
		
		ill = new Sprite(32, 5, 2, SpriteSheet.organics_1);
		dead = new Sprite(32, 8, 2, SpriteSheet.organics_1);
		hit = new Sprite(32, 6, 2, SpriteSheet.organics_1);
		
		sprite = down;
		
		//Move Selection
		attacks = true;
		moves = new int[] {1, 2};
		
		offSpells.add(new Poison_Pin(this));
	}
	
	public void update() {
		if (Game.State == Game.STATE.GAME) {
			chaserMovement();
		}
	}
	
	public void behavior() {
		if (Battle.turnNumber % 3 == 0 && mp > 0) {
			choice = moves[random.nextInt(2)];
			spellChosen = offSpells.get(0);
		}
		else choice = 1;
	}
	
}
