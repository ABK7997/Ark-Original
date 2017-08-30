package entity.mobs.enemies.bosses;

import main.Game;
import states.Battle;
import states.Screen;
import battle.skills.party.Charge;
import battle.skills.party.Wind_Up;
import battle.spells.defensive.Field;
import battle.spells.defensive.Protect;
import battle.spells.offensive.Bolt;
import battle.spells.offensive.Pulse;
import battle.spells.offensive.Shock;
import entity.mobs.enemies.Enemy;
import graphics.Sprite;
import graphics.SpriteSheet;

public class Crysalis extends Enemy {
	
	public Crysalis(int x, int y, int level, int num) { //controls spawn point/location of goon at any given frame		
		this.x = x << 5;
		this.y = y << 5;
		xa = 0;
		ya = 0;
		
		//STATS
		name = "Crysalis";
		type = "Boss";
		lv = level;
		expGiven = 500 * level; //Experience awarded for defeating mob
		moneyGiven = 2000;
		
		hp = maxHP = 30 * level;
		mp = maxMP = 9999 * level;
		ep = maxEP = 0;
		
		pwr = basePwr = 100 * level;
		dex = baseDex = 40 * level;
		spd = baseSpd = 12 * level;
		evd = baseEvd = 10 * level;
		res = baseRes = 75 * level;
		mag = baseMag = 45 * level;
		def = baseDef = 2;
		magDef = baseMagDef = 3;
		
		fleeChance = 0;
		mobNum = num;
		
		encounterRange = 25 << 5;
		
		//Sprites
		down = new Sprite(96, 0, 0, SpriteSheet.bosses_2);
		left = new Sprite(96, 1, 0, SpriteSheet.bosses_2);
		right = new Sprite(96, 3, 0, SpriteSheet.bosses_2);
		up = new Sprite(96, 3, 1, SpriteSheet.bosses_2);
		
		attack_1 = new Sprite(96, 2, 0, SpriteSheet.bosses_2);
		attack_2 = new Sprite(96, 0, 1, SpriteSheet.bosses_2);
		
		magic_1 = new Sprite(96, 1, 1, SpriteSheet.bosses_2);
		magic_2 = attack_1;
		
		skill = new Sprite(96, 2, 1, SpriteSheet.bosses_2);
		
		ill = left;
		hit = left;
		dead = left;
		
		sprite = left;
		
		//Move Selection
		moves = new int[] {1, 2, 4, 5};
		
		attacks = true;
		
		offSpells.add(new Bolt(this));
		offSpells.add(new Pulse(this));
		offSpells.add(new Shock(this));
		
		defSpells.add(new Protect(this));
		defSpells.add(new Field(this));
		
		skills.add(new Charge(this));
		skills.add(new Wind_Up(this));
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
			choice = 5; 
			choice2 = 1;
			skillChosen = skills.get(0); 
			break;
		case 1: 
			choice = 2; 
			spellChosen = offSpells.get(random.nextInt(offSpells.size())); 
			break;
		case 2: 
			choice = 5; 
			choice2 = 1;
			skillChosen = skills.get(1); 
			break;
		case 3: 
			choice = 1; 
			choice2 = 1;
			break;
		case 4: 
			choice = moves[random.nextInt(2)]; 
			choice2 = 1;
			spellChosen = offSpells.get(random.nextInt(offSpells.size())); 
			break;
		case 5: 
			choice = moves[random.nextInt(2)]; 
			choice2 = 1;
			spellChosen = offSpells.get(random.nextInt(offSpells.size())); 
			break;
		case 6: 
			choice = 5; 
			choice2 = 1;
			skillChosen = skills.get(0); 
			break;
		case 7: 
			choice = 2; 
			spellChosen = offSpells.get(1); 
			break;
		case 8: 
			choice = 4; spellChosen = defSpells.get(random.nextInt(defSpells.size())); 
			Battle.turnNumber = 0; 
			break;
		}
		
	}
	
}
