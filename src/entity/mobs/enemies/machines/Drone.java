package entity.mobs.enemies.machines;

import battle.techs.physical.Swing;
import main.Game;
import entity.mobs.enemies.Enemy;
import graphics.Sprite;
import graphics.SpriteSheet;

public class Drone extends Enemy {
	
	public Drone(int x, int y, int level, int dir, int num) { //controls spawn point/location of goon at any given frame		
		this.x = x << 5;
		this.y = y << 5;
		xa = 0;
		ya = 0;
		
		//STATS
		name = "Worker Drone";
		type = "Machine";
		lv = level;
		expGiven = 5 * level; //Experience awarded for defeating mob
		moneyGiven = random.nextInt(8 * level) + 4;
		
		hp = maxHP = 30 * level;
		mp = maxMP = 0 * level;
		ep = maxEP = 1 * level;
		
		pwr = basePwr = 15 * level;
		dex = baseDex = 3 * level;
		spd = baseSpd = 1 * level;
		evd = baseEvd = 1 * level;
		res = baseRes = 1 * level;
		mag = baseMag = 10 * level;
		def = baseDef = 4;
		magDef = baseMagDef = 1;
		
		fleeChance = 100 - (level * 5);
		mobNum = num;
		
		if (dir == 0) xa = 0;
		else if (dir == 1) ya = 0;
		
		//Sprites
		up = new Sprite(32, 3, 0, SpriteSheet.machines_1);
		up_1 = new Sprite(32, 3, 1, SpriteSheet.machines_1);
		up_2 = new Sprite(32, 3, 2, SpriteSheet.machines_1);
		
		down = new Sprite(32, 0, 0, SpriteSheet.machines_1);
		down_1 = new Sprite(32, 0, 1, SpriteSheet.machines_1);
		down_2 = new Sprite(32, 0, 2, SpriteSheet.machines_1);
		
		right = new Sprite(32, 1, 0, SpriteSheet.machines_1);
		right_1 = new Sprite(32, 1, 1, SpriteSheet.machines_1);
		right_2 = new Sprite(32, 1, 2, SpriteSheet.machines_1);
		
		left = new Sprite(32, 2, 0, SpriteSheet.machines_1);
		left_1 = new Sprite(32, 2, 1, SpriteSheet.machines_1);
		left_2 = new Sprite(32, 2, 2, SpriteSheet.machines_1);
		
		attack_1 = new Sprite(32, 7, 1, SpriteSheet.machines_1);
		attack_2 = new Sprite(32, 6, 1, SpriteSheet.machines_1);
		
		magic_1 = new Sprite(32, 7, 2, SpriteSheet.machines_1);
		magic_2 = new Sprite(32, 6, 2, SpriteSheet.machines_1);
		
		skill = new Sprite(32, 4, 2, SpriteSheet.machines_1);
		
		ill = new Sprite(32, 7, 0, SpriteSheet.machines_1);
		dead = new Sprite(32, 5, 2, SpriteSheet.machines_1);
		hit = new Sprite(32, 5, 0, SpriteSheet.machines_1);
		
		sprite = down;
		
		moves = new int[] {1, 2};
		
		//Move Selection
		offSpells.add(new Swing(this));
		attacks = true;
	}
	
	public void update() {
		if (Game.State == Game.STATE.GAME) {
			chaserMovement();
		}
	}
	
	public void behavior() {
		choice = moves[random.nextInt(2)];
		spellChosen = offSpells.get(0);
	}
	
}
