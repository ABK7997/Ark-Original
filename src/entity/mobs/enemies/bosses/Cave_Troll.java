package entity.mobs.enemies.bosses;

import main.Game;
import main.Story;
import states.Battle;
import states.Screen;
import battle.skills.party.Wind_Up;
import battle.spells.offensive.Bolt;
import entity.mobs.enemies.Enemy;
import graphics.Sprite;
import graphics.SpriteSheet;

public class Cave_Troll extends Enemy {
	
	public Cave_Troll(int x, int y, int level, int num) { //controls spawn point/location of goon at any given frame		
		this.x = x << 5;
		this.y = y << 5;
		xa = 0;
		ya = 0;
		
		dir = 2;
		
		//STATS
		name = "Cave Troll";
		type = "Boss";
		lv = level;
		expGiven = 50 * level; //Experience awarded for defeating mob
		moneyGiven = 200;
		
		hp = maxHP = 600 * level;
		mp = maxMP = 30;
		ep = maxEP = 0;
		
		pwr = basePwr = 100 * level;
		dex = baseDex = 10 * level;
		spd = baseSpd = 8 * level;
		evd = baseEvd = 7 * level;
		res = baseRes = 20 * level;
		mag = baseMag = 20 * level;
		def = baseDef = 2;
		magDef = baseMagDef = 2;
		
		fleeChance = 0;
		mobNum = num;
		
		encounterRange = 50;
		
		//Sprites
		left = new Sprite(64, 3, 0, SpriteSheet.bosses_1);
		
		attack_1 = new Sprite(64, 3, 1, SpriteSheet.bosses_1);
		attack_2 = new Sprite(64, 4, 0, SpriteSheet.bosses_1);
		
		magic_1 = new Sprite(64, 4, 0, SpriteSheet.bosses_1);
		magic_2 = new Sprite(64, 3, 1, SpriteSheet.bosses_1);
		
		skill = new Sprite(64, 4, 0, SpriteSheet.bosses_1);
		
		ill = new Sprite(64, 3, 0, SpriteSheet.bosses_1);
		dead = new Sprite(64, 5, 0, SpriteSheet.bosses_1);
		hit = new Sprite(64, 4, 1, SpriteSheet.bosses_1);
		
		sprite = left;
		
		//Move Selection
		attacks = true;
		
		moves = new int[] {1, 2, 5};
		
		skills.add(new Wind_Up(this));
		
		offSpells.add(new Bolt(this));
	}
	
	public void update() {
		//anim++;
		//if (anim > 9999) anim = 0;
	}
	
	public void render(Screen screen) {
		screen.renderMob(x, y, sprite); 
		if (Game.State == Game.STATE.BATTLE) {
			animate();
		}
	}
	
	public void behavior() {
		if (Battle.turnNumber == 0) {
			Story.mainStory = 2;
			Story.trench = 2;
		}
		
		if (Battle.turnNumber % 2 == 0) {
			if (mp > 0) choice = moves[random.nextInt(2) + 1];
			else {
				int chance = random.nextInt(2);
				if (chance == 0) choice = 1;
				else choice = 2;
			}
			skillChosen = skills.get(0);
			spellChosen = offSpells.get(0);
		}
		else choice = 1; //Attack
	}
	
}
