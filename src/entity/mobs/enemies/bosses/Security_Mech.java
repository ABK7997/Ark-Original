package entity.mobs.enemies.bosses;

import states.Screen;
import battle.techs.physical.Swing;
import main.Game;
import entity.mobs.enemies.Enemy;
import graphics.Sprite;
import graphics.SpriteSheet;

public class Security_Mech extends Enemy {
	
	public Security_Mech(int x, int y, int level, int num) { //controls spawn point/location of goon at any given frame		
		this.x = x << 5;
		this.y = y << 5;
		xa = 0;
		ya = 0;
		
		//STATS
		name = "Factory Mech";
		type = "Boss";
		lv = level;
		expGiven = 30 * level; //Experience awarded for defeating mob
		moneyGiven = 100;
		
		hp = maxHP = 175 * level;
		mp = maxMP = 0 * level;
		ep = maxEP = 5 * level;
		
		pwr = basePwr = 50 * level;
		dex = baseDex = 4 * level;
		spd = baseSpd = 6 * level;
		evd = baseEvd = 3 * level;
		res = baseRes = 1 * level;
		mag = baseMag = 15 * level;
		def = baseDef = 4 * level;
		magDef = baseMagDef = 1 * level;
		
		fleeChance = 0 - (level * 5);
		mobNum = num;
		encounterRange = 100;
		
		//Sprites
		left = new Sprite(64, 0, 0, SpriteSheet.bosses_1);
		
		attack_1 = new Sprite(64, 1, 0, SpriteSheet.bosses_1);
		attack_2 = new Sprite(64, 1, 1, SpriteSheet.bosses_1);
		
		magic_1 = new Sprite(64, 1, 0, SpriteSheet.bosses_1);
		magic_2 = new Sprite(64, 0, 1, SpriteSheet.bosses_1);
		
		hit = new Sprite(64, 2, 1, SpriteSheet.bosses_1);
		dead = new Sprite(64, 2, 0, SpriteSheet.bosses_1);
		
		sprite = left;
		
		moves = new int[] {1, 2};
		
		//Move Selection
		offSpells.add(new Swing(this));
		attacks = true;
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
		choice = moves[random.nextInt(2)];
		choice2 = 0;
		spellChosen = offSpells.get(0);
	}
	
}
