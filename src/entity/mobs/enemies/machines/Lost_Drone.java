package entity.mobs.enemies.machines;

import main.Game;
import battle.techs.physical.Acid;
import battle.techs.physical.Rocket_Punch;
import entity.mobs.enemies.Enemy;
import graphics.Sprite;
import graphics.SpriteSheet;

public class Lost_Drone extends Enemy {
	
	public Lost_Drone(int x, int y, int level, int num) { //controls spawn point/location of goon at any given frame		
		this.x = x << 5;
		this.y = y << 5;
		xa = 0;
		ya = 0;
		
		//STATS
		name = "Lost Drone";
		type = "Machine";
		lv = level;
		expGiven = 7 * level; //Experience awarded for defeating mob
		moneyGiven = random.nextInt(5 * level) + 15;
		
		hp = maxHP = 70 * level;
		mp = maxMP = 0 * level;
		ep = maxEP = 4 * level;
		
		pwr = basePwr = 45 * level;
		dex = baseDex = 4 * level;
		spd = baseSpd = 2 * level;
		evd = baseEvd = 3 * level;
		res = baseRes = 2 * level;
		mag = baseMag = 25 * level;
		def = baseDef = 4;
		magDef = baseMagDef = 1;
		
		fleeChance = 95 - (level * 5);
		mobNum = num;
		
		//Sprites
		up = new Sprite(32, 19, 0, SpriteSheet.machines_1);
		up_1 = new Sprite(32, 19, 1, SpriteSheet.machines_1);
		up_2 = new Sprite(32, 19, 2, SpriteSheet.machines_1);
		
		down = new Sprite(32, 16, 0, SpriteSheet.machines_1);
		down_1 = new Sprite(32, 16, 1, SpriteSheet.machines_1);
		down_2 = new Sprite(32, 16, 2, SpriteSheet.machines_1);
		
		right = new Sprite(32, 17, 0, SpriteSheet.machines_1);
		right_1 = new Sprite(32, 17, 1, SpriteSheet.machines_1);
		right_2 = new Sprite(32, 17, 2, SpriteSheet.machines_1);
		
		left = new Sprite(32, 18, 0, SpriteSheet.machines_1);
		left_1 = new Sprite(32, 18, 1, SpriteSheet.machines_1);
		left_2 = new Sprite(32, 18, 2, SpriteSheet.machines_1);
		
		attack_1 = new Sprite(32, 23, 1, SpriteSheet.machines_1);
		attack_2 = new Sprite(32, 22, 1, SpriteSheet.machines_1);
		
		magic_1 = new Sprite(32, 23, 2, SpriteSheet.machines_1);
		magic_2 = new Sprite(32, 22, 2, SpriteSheet.machines_1);
		
		skill = new Sprite(32, 20, 2, SpriteSheet.machines_1);
		
		ill = new Sprite(32, 23, 0, SpriteSheet.machines_1);
		dead = new Sprite(32, 21, 2, SpriteSheet.machines_1);
		hit = new Sprite(32, 21, 0, SpriteSheet.machines_1);
		
		sprite = down;
		
		moves = new int[] {1, 2};
		
		//Move Selection
		offSpells.add(new Rocket_Punch(this)); //0
		offSpells.add(new Acid(this)); //1
		
		attacks = true;
	}
	
	public void update() {
		if (Game.State == Game.STATE.GAME) {
			chaserMovement();
		}
	}
	
	public void behavior() {
		if (ep > 0) choice = moves[random.nextInt(2)];
		else choice = 1;
		if (target.getType() == "Machine" || target.getType() == "Cyborg" || target.getType() == "Cygic") {
			spellChosen = offSpells.get(random.nextInt(2));
		}
		else spellChosen = offSpells.get(0);
	}
	
}
