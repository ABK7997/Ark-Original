package entity.mobs.enemies.cyborgs;

import main.Game;
import battle.skills.party.Bulk_Up;
import battle.techs.physical.Swing;
import entity.mobs.enemies.Enemy;
import graphics.Sprite;
import graphics.SpriteSheet;

public class Cyborg_Guard extends Enemy {
	
	public Cyborg_Guard(int x, int y, int level, int num) { //controls spawn point/location of goon at any given frame		
		this.x = x << 5;
		this.y = y << 5;
		xa = 0;
		ya = 0;
		
		//STATS
		name = "Cyborg Guard";
		type = "Cyborg";
		lv = level;
		expGiven = 5 * level; //Experience awarded for defeating mob
		moneyGiven = random.nextInt(6 * level) + 12;
		
		hp = maxHP = 30 * level;
		mp = maxMP = 0 * level;
		ep = maxEP = 1 * level;
		
		pwr = basePwr = 25 * level;
		dex = baseDex = 3 * level;
		spd = baseSpd = 4 * level;
		evd = baseEvd = 3 * level;
		res = baseRes = 2 * level;
		mag = baseMag = 5 * level;
		def = baseDef = 3 * level;
		magDef = baseMagDef = 1;
		
		fleeChance = 90 - (level * 5);
		mobNum = num;
		
		chaseRange = 4 << 5;
		
		//Sprites
		down = new Sprite(32, 7, 0, SpriteSheet.cyborgs_1);
		down_1 = new Sprite(32, 7, 1, SpriteSheet.cyborgs_1);
		down_2 = new Sprite(32, 7, 2, SpriteSheet.cyborgs_1);
		
		right = new Sprite(32, 5, 0, SpriteSheet.cyborgs_1);
		right_1 = new Sprite(32, 5, 1, SpriteSheet.cyborgs_1);
		right_2 = new Sprite(32, 5, 2, SpriteSheet.cyborgs_1);
		
		left = new Sprite(32, 6, 0, SpriteSheet.cyborgs_1);
		left_1 = new Sprite(32, 6, 1, SpriteSheet.cyborgs_1);
		left_2 = new Sprite(32, 6, 2, SpriteSheet.cyborgs_1);
		
		up = new Sprite(32, 4, 0, SpriteSheet.cyborgs_1);
		up_1 = new Sprite(32, 4, 1, SpriteSheet.cyborgs_1);
		up_2 = new Sprite(32, 4, 2, SpriteSheet.cyborgs_1);
		
		ill = new Sprite(32, 3, 0, SpriteSheet.cyborgs_1);
		attack_1 = new Sprite(32, 3, 1, SpriteSheet.cyborgs_1);
		magic_1 = new Sprite(32, 3, 2, SpriteSheet.cyborgs_1);
		attack_2 = new Sprite(32, 2, 1, SpriteSheet.cyborgs_1);
		magic_2 = new Sprite(32, 2, 2, SpriteSheet.cyborgs_1);
	
		hit = new Sprite(32, 1, 0, SpriteSheet.cyborgs_1);
		dead = new Sprite(32, 1, 2, SpriteSheet.cyborgs_1);
		skill = new Sprite(32, 0, 2, SpriteSheet.cyborgs_1);
		
		sprite = down;
		
		moves = new int[] {1, 2, 5};
		
		//Move Selection
		attacks = true;
		offSpells.add(new Swing(this));
		skills.add(new Bulk_Up(this));
	}
	
	public void update() {
		if (Game.State == Game.STATE.GAME) {
			chaserMovement();
		}
	}
	
	public void behavior() {
		choice = moves[random.nextInt(3)];
		spellChosen = offSpells.get(0);
		skillChosen = skills.get(0);
	}
	
}
