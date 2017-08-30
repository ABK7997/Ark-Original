package entity.mobs.enemies.machines;

import battle.techs.physical.Acid;
import battle.techs.physical.Swing;
import main.Game;
import entity.mobs.enemies.Enemy;
import graphics.Sprite;
import graphics.SpriteSheet;

public class Expired_Drone extends Enemy {
	
	public Expired_Drone(int x, int y, int level, int dir, int num) { //controls spawn point/location of goon at any given frame		
		this.x = x << 5;
		this.y = y << 5;
		xa = 0;
		ya = 0;
		
		//STATS
		name = "Expired Drone";
		type = "Machine";
		lv = level;
		expGiven = 6 * level; //Experience awarded for defeating mob
		moneyGiven = random.nextInt(5 * level) + 10;
		
		hp = maxHP = 50 * level;
		mp = maxMP = 0 * level;
		ep = maxEP = 3 * level;
		
		pwr = basePwr = 20 * level;
		dex = baseDex = 4 * level;
		spd = baseSpd = 2 * level;
		evd = baseEvd = 3 * level;
		res = baseRes = 2 * level;
		mag = baseMag = 20 * level;
		def = baseDef = 4;
		magDef = baseMagDef = 1;
		
		fleeChance = 97 - (level * 5);
		mobNum = num;
		
		if (dir == 0) xa = 0;
		else if (dir == 1) ya = 0;
		
		//Sprites
		up = new Sprite(32, 11, 0, SpriteSheet.machines_1);
		up_1 = new Sprite(32, 11, 1, SpriteSheet.machines_1);
		up_2 = new Sprite(32, 11, 2, SpriteSheet.machines_1);
		
		down = new Sprite(32, 8, 0, SpriteSheet.machines_1);
		down_1 = new Sprite(32, 8, 1, SpriteSheet.machines_1);
		down_2 = new Sprite(32, 8, 2, SpriteSheet.machines_1);
		
		right = new Sprite(32, 9, 0, SpriteSheet.machines_1);
		right_1 = new Sprite(32, 9, 1, SpriteSheet.machines_1);
		right_2 = new Sprite(32, 9, 2, SpriteSheet.machines_1);
		
		left = new Sprite(32, 10, 0, SpriteSheet.machines_1);
		left_1 = new Sprite(32, 10, 1, SpriteSheet.machines_1);
		left_2 = new Sprite(32, 10, 2, SpriteSheet.machines_1);
		
		attack_1 = new Sprite(32, 15, 1, SpriteSheet.machines_1);
		attack_2 = new Sprite(32, 14, 1, SpriteSheet.machines_1);
		
		magic_1 = new Sprite(32, 15, 2, SpriteSheet.machines_1);
		magic_2 = new Sprite(32, 14, 2, SpriteSheet.machines_1);
		
		skill = new Sprite(32, 12, 2, SpriteSheet.machines_1);
		
		ill = new Sprite(32, 15, 0, SpriteSheet.machines_1);
		dead = new Sprite(32, 13, 2, SpriteSheet.machines_1);
		hit = new Sprite(32, 13, 0, SpriteSheet.machines_1);
		
		sprite = down;
		
		moves = new int[] {1, 2};
		
		//Move Selection
		offSpells.add(new Swing(this)); //0
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
