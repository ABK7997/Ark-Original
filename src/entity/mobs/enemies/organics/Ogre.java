package entity.mobs.enemies.organics;

import states.Battle;
import battle.skills.party.Bulk_Up;
import battle.skills.party.Wind_Up;
import entity.mobs.enemies.Enemy;
import graphics.Sprite;
import graphics.SpriteSheet;

public class Ogre extends Enemy {
	
	public Ogre(int x, int y, int level, int num) { //controls spawn point/location of goon at any given frame		
		this.x = x << 5;
		this.y = y << 5;
		xa = 0;
		ya = 0;
		
		//STATS
		name = "Ogre";
		type = "Organic";
		lv = level;
		expGiven = 10 * level; //Experience awarded for defeating mob
		moneyGiven = random.nextInt(6 * level) + 20;
		
		hp = maxHP = 100 * level;
		mp = maxMP = 0;
		ep = maxEP = 0;
		
		pwr = basePwr = 45 * level;
		dex = baseDex = 6 * level;
		spd = baseSpd = 3 * level;
		evd = baseEvd = 2 * level;
		res = baseRes = 12 * level;
		mag = baseMag = 0 * level;
		def = baseDef = 2;
		magDef = baseMagDef = 2;
		
		fleeChance = 90 - (level * 5);
		mobNum = num;
		encounterRange = 40;
		
		//Sprites
		up = new Sprite(32, 11, 0, SpriteSheet.organics_1);
		
		down = new Sprite(32, 9, 0, SpriteSheet.organics_1);
		
		right = new Sprite(32, 11, 2, SpriteSheet.organics_1);
		
		left = new Sprite(32, 10, 0, SpriteSheet.organics_1);
		
		attack_1 = new Sprite(32, 10, 1, SpriteSheet.organics_1);
		attack_2 = new Sprite(32, 10, 2, SpriteSheet.organics_1);
		
		skill = new Sprite(32, 10, 2, SpriteSheet.organics_1);
		magic_2 = new Sprite(32, 10, 1, SpriteSheet.organics_1);
		
		ill = new Sprite(32, 9, 2, SpriteSheet.organics_1);
		dead = new Sprite(32, 11, 1, SpriteSheet.organics_1);
		hit = new Sprite(32, 9, 1, SpriteSheet.organics_1);
		
		sprite = down;
		
		//Move Selection
		attacks = true;
		
		moves = new int[] {1, 5};
		
		skills.add(new Wind_Up(this));
		skills.add(new Bulk_Up(this));
	}
	
	public void update() {
	}
	
	public void behavior() {
		if (Battle.turnNumber % 4 == 0) {
			choice = moves[random.nextInt(2)];
			skillChosen = skills.get(random.nextInt(skills.size()));
		}
		else choice = 1;
	}
	
}
