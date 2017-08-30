package entity.mobs.enemies.machines;

import battle.skills.party.Wide_Sweep;
import battle.techs.physical.Missiles;
import battle.techs.physical.Rocket_Punch;
import entity.mobs.enemies.Enemy;
import graphics.Sprite;
import graphics.SpriteSheet;

public class Old_Harvester extends Enemy {
	
	public Old_Harvester(int x, int y, int level, int num) { //controls spawn point/location of goon at any given frame		
		this.x = x << 5;
		this.y = y << 5;
		xa = 0;
		ya = 0;
		
		//STATS
		name = "Old Harvester";
		type = "Machine";
		lv = level;
		expGiven = 20 * level; //Experience awarded for defeating mob
		moneyGiven = random.nextInt(20 * level) + 50;
		
		hp = maxHP = 250 * level;
		mp = maxMP = 0 * level;
		ep = maxEP = 30 * level;
		
		pwr = basePwr = 75 * level;
		dex = baseDex = 1 * level;
		spd = baseSpd = 1 * level;
		evd = baseEvd = 1 * level;
		res = baseRes = 6 * level;
		mag = baseMag = 40 * level;
		def = baseDef = 4;
		magDef = baseMagDef = 1;
		
		fleeChance = 120 - (level * 5);
		mobNum = num;
		
		encounterRange = 64;
		
		//Sprites
		down = new Sprite(64, 2, 0, SpriteSheet.machines_2);
		
		left = new Sprite(64, 0, 0, SpriteSheet.machines_2);
		
		magic_1 = new Sprite(64, 1, 0, SpriteSheet.machines_2);
		magic_2 = new Sprite(64, 0, 1, SpriteSheet.machines_2);
		
		attack_1 = left;
		attack_2 = magic_2;
		
		skill = new Sprite(64, 1, 1, SpriteSheet.machines_2);
		
		hit = left;
		dead = down;
		
		sprite = down;
		
		moves = new int[] {1, 2, 5};
		
		//Move Selection
		offSpells.add(new Missiles(this));
		offSpells.add(new Rocket_Punch(this));
		skills.add(new Wide_Sweep(this));
		attacks = true;
		
		spellChosen = offSpells.get(0);
		skillChosen = skills.get(0);
	}
	
	public void update() {
	}
	
	public void behavior() {
		if (ep > 0) {
			choice = moves[random.nextInt(moves.length)];
			spellChosen = offSpells.get(random.nextInt(offSpells.size()));
		}
		else choice = 1;
	}
	
}
