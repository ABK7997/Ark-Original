package entity.mobs.enemies.organics;

import main.Game;
import entity.mobs.enemies.Enemy;
import graphics.Sprite;
import graphics.SpriteSheet;

public class Rat extends Enemy {
	
	public Rat(int x, int y, int level, int num) { //controls spawn point/location of goon at any given frame		
		this.x = x << 5;
		this.y = y << 5;
		xa = 0;
		ya = 0;
		
		//STATS
		name = "Rat";
		type = "Organic";
		lv = level;
		expGiven = 3 * level; //Experience awarded for defeating mob
		moneyGiven = random.nextInt(5 * level) + 5;
		
		hp = maxHP = 20 * level;
		mp = maxMP = 0;
		ep = maxEP = 0;
		
		pwr = basePwr = 20 * level;
		dex = baseDex = 5 * level;
		spd = baseSpd = 5 * level;
		evd = baseEvd = 5 * level;
		res = baseRes = 3 * level;
		mag = baseMag = 5 * level;
		def = baseDef = 2;;
		magDef = baseMagDef = 2;
		
		fleeChance = 100 - (level * 5);
		mobNum = num;
		
		//Sprites
		up = new Sprite(32, 0, 0, SpriteSheet.organics_1);
		up_1 = new Sprite(32, 0, 1, SpriteSheet.organics_1);
		up_2 = new Sprite(32, 0, 2, SpriteSheet.organics_1);
		
		down = new Sprite(32, 3, 0, SpriteSheet.organics_1);
		down_1 = new Sprite(32, 3, 1, SpriteSheet.organics_1);
		down_2 = new Sprite(32, 3, 2, SpriteSheet.organics_1);
		
		right = new Sprite(32, 1, 0, SpriteSheet.organics_1);
		right_1 = new Sprite(32, 1, 1, SpriteSheet.organics_1);
		right_2 = new Sprite(32, 1, 2, SpriteSheet.organics_1);
		
		left = new Sprite(32, 2, 0, SpriteSheet.organics_1);
		left_1 = new Sprite(32, 2, 1, SpriteSheet.organics_1);
		left_2 = new Sprite(32, 2, 2, SpriteSheet.organics_1);
		
		attack_1 = new Sprite(32, 2, 0, SpriteSheet.organics_1);
		attack_2 = new Sprite(32, 2, 2, SpriteSheet.organics_1);
		
		ill = new Sprite(32, 4, 0, SpriteSheet.organics_1);
		dead = new Sprite(32, 4, 1, SpriteSheet.organics_1);
		hit = new Sprite(32, 4, 2, SpriteSheet.organics_1);
		
		sprite = down;
		
		//Move Selection
		attacks = true;
	}
	
	public void update() {
		if (Game.State == Game.STATE.GAME) {
			randomMovement();
		}
	}
	
	public void behavior() {
		choice = 1;
	}
	
}
