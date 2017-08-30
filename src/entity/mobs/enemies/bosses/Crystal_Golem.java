package entity.mobs.enemies.bosses;

import main.Game;
import main.Story;
import states.Battle;
import states.Screen;
import battle.skills.party.Charge;
import battle.spells.curative.Aura;
import battle.spells.defensive.Protect;
import battle.spells.offensive.Bolt;
import battle.spells.offensive.Pulse;
import entity.mobs.enemies.Enemy;
import graphics.Sprite;
import graphics.SpriteSheet;

public class Crystal_Golem extends Enemy {
	
	public Crystal_Golem(int x, int y, int level, int num) { //controls spawn point/location of goon at any given frame		
		this.x = x << 5;
		this.y = y << 5;
		xa = 0;
		ya = 0;
		
		//STATS
		name = "Crystal Golem";
		type = "Boss";
		lv = level;
		expGiven = 100 * level; //Experience awarded for defeating mob
		moneyGiven = 500;
		
		hp = maxHP = 1200 * level;
		mp = maxMP = 999 * level;
		ep = maxEP = 0;
		
		pwr = basePwr = 100 * level;
		dex = baseDex = 15 * level;
		spd = baseSpd = 1 * level;
		evd = baseEvd = 1 * level;
		res = baseRes = 30 * level;
		mag = baseMag = 30 * level;
		def = baseDef = 2;
		magDef = baseMagDef = 4;
		
		fleeChance = 0;
		mobNum = num;
		
		encounterRange = 70;
		
		//Sprites
		left = new Sprite(64, 6, 0, SpriteSheet.bosses_1);
		
		attack_1 = new Sprite(64, 6, 0, SpriteSheet.bosses_1);
		attack_2 = new Sprite(64, 5, 1, SpriteSheet.bosses_1);
		
		magic_1 = new Sprite(64, 6, 1, SpriteSheet.bosses_1);
		magic_2 = new Sprite(64, 7, 1, SpriteSheet.bosses_1);
		
		skill = attack_2;
		
		ill = left;
		dead = new Sprite(64, 7, 0, SpriteSheet.bosses_1);
		hit = left;
		
		sprite = left;
		
		//Move Selection
		moves = new int[] {1, 2, 3, 4, 5};
		
		attacks = true;
		
		offSpells.add(new Bolt(this));
		offSpells.add(new Pulse(this));
		
		cureSpells.add(new Aura(this));
		
		defSpells.add(new Protect(this));
		
		skills.add(new Charge(this));
	}
	
	public void update() {
	}
	
	public void render(Screen screen) {
		screen.renderMob(x, y, sprite); 
		if (Game.State == Game.STATE.BATTLE) {
			animate();
		}
	}
	
	public void behavior() {
		switch(Battle.turnNumber) {
		case 0: 
			choice = 4;
			choice2 = 1;
			spellChosen = defSpells.get(0);
			Story.swamp = 3;
			Story.mainStory = 3;
			break;
			
		case 1: 
			if (defMod == 100) {
				choice = 4;
				choice2 = 1;
				spellChosen = defSpells.get(0);
			}
			else {
				choice = 1;
				choice2 = 2;
				spellChosen = offSpells.get(0);
			}
			break;
		case 2: 
			choice = 2;
			spellChosen = offSpells.get(random.nextInt(offSpells.size()));
			break;
		case 3:
			choice = 5;
			choice2 = 1;
			skillChosen = skills.get(0);
			break;
		case 4: 
			choice = 2;
			spellChosen = offSpells.get(0);
			break;
		case 5: 
			choice = 3;
			choice2 = 1;
			spellChosen = cureSpells.get(0);
			Battle.turnNumber = 0;
			break;
		}
	}
	
}
