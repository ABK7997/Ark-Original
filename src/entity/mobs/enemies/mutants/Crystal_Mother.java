package entity.mobs.enemies.mutants;

import states.Battle;
import main.Game;
import battle.spells.curative.Aura;
import battle.spells.defensive.Protect;
import battle.spells.offensive.Pulse;
import battle.spells.offensive.Shock;
import entity.mobs.enemies.Enemy;
import graphics.Sprite;
import graphics.SpriteSheet;

public class Crystal_Mother extends Enemy {
	
	public Crystal_Mother(int x, int y, int level, int num) { //controls spawn point/location of goon at any given frame		
		this.x = x << 5;
		this.y = y << 5;
		xa = 0;
		ya = 0;
		
		//STATS
		name = "Crystal Mother";
		type = "Mutant";
		lv = level;
		expGiven = 23 * level; //Experience awarded for defeating mob
		moneyGiven = random.nextInt(15 * level) + 35;
		
		hp = maxHP = 250 * level;
		mp = maxMP = 9999 * level;
		ep = maxEP = 0;
		
		pwr = basePwr = 50 * level;
		dex = baseDex = 1 * level;
		spd = baseSpd = 1 * level;
		evd = baseEvd = 1 * level;
		res = baseRes = 999 * level;
		mag = baseMag = 42 * level;
		def = baseDef = 2;
		magDef = baseMagDef = 4;
		
		fleeChance = 0;
		mobNum = num;
		encounterRange = 64;
		
		//Sprites
		left = new Sprite(64, 4, 0, SpriteSheet.mutants_2);;
		up = left;
		down = left;
		right = left;
		
		hit = left;
		ill = left;
		dead = new Sprite(64, 3, 1, SpriteSheet.mutants_2);;
		
		magic_1 = new Sprite(64, 4, 1, SpriteSheet.mutants_2);
		magic_2 = new Sprite(64, 4, 0, SpriteSheet.mutants_2);
		
		skill = new Sprite(64, 4, 1, SpriteSheet.mutants_2);
		
		sprite = down;
		
		//Move Selection
		moves = new int[] {2, 3, 4};
		
		attacks = false;
		offSpells.add(new Pulse(this));
		offSpells.add(new Shock(this));
		cureSpells.add(new Aura(this));
		defSpells.add(new Protect(this));
	}
	
	public void update() {
		if (Game.State == Game.STATE.GAME) {
			noMovement();
		}
	}
	
	public void behavior() {
		if (mp > 0) {
			if (Battle.turnNumber == 3) {
				choice = 3;
				spellChosen = cureSpells.get(random.nextInt(cureSpells.size()));
			}
			else if (Battle.turnNumber == 4) {
				choice = 4;
				spellChosen = defSpells.get(random.nextInt(defSpells.size()));
			}
			else {
				choice = 2;
				spellChosen = offSpells.get(random.nextInt(offSpells.size()));
			}
		}
		else choice = 0;
		
		if (Battle.turnNumber > 5) Battle.turnNumber = 0;
	}
	
}
