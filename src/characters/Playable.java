package characters;

import items.Inventory;
import items.Item;

import java.util.ArrayList;
import java.util.Random;

import entity.mobs.enemies.Enemy;
import entity.mobs.enemies.Enemy.STATES;
import battle.Skill;
import battle.Spell;
import states.Screen;
import graphics.Sprite;

public class Playable {

	protected Random random = new Random();
	
	protected String name;
	protected Sprite sprite;
	protected int lv = 1; protected int priorLv;
	protected int exp = 0; protected int priorExp;
	protected int nextLevel = 50;
	protected int index;
	
	//Animation
	protected int x, y;
	protected int ax, ay; //rests coordinates after a physical attack
	protected int anim;
	protected Screen screen;
	protected boolean animating = false;
	
	protected String newSpell = "";
	protected String newSkill = "";
	
	//Stats
	protected String type;
	protected int maxHP, maxMP, maxEP;
	protected int hp, mp, ep;
	protected int pwr, dex, spd, evd, res, mag, eng, def, magDef;
	protected int basePwr, baseDex, baseSpd, baseEvd, baseRes, baseMag, baseEng, baseDef, baseMagDef;
	protected int defMod = 100;
	protected int magMod = 100;
	protected int regen;
	
	//Status Effects
	protected boolean statusEffected = false;
	protected boolean poisoned = false;
	protected boolean burned = false;
	protected boolean corroding = false;
	protected boolean paralyzed = false;
	protected boolean illness = false;
	protected boolean asleep = false;
	
	//MAGIC / TECH / SKILL
	protected ArrayList<Spell> offSpells = new ArrayList<Spell>();
	protected ArrayList<Spell> cureSpells = new ArrayList<Spell>();
	protected ArrayList<Spell> defSpells = new ArrayList<Spell>();
	protected int typeChosen = 0;
	protected Spell spellChosen;
	
	protected ArrayList<Skill> skills = new ArrayList<Skill>();
	protected Skill skillChosen;
	
	protected ArrayList<Playable> party;
	
	//Sprites
	protected Sprite up, up_1, up_2;
	protected Sprite down, down_1, down_2;
	protected Sprite right, right_1, right_2;
	protected Sprite left, left_1, left_2;
	
	protected Sprite ill, attack_1, attack_2, magic_1, magic_2;
	protected Sprite hit, flee, dead;
	protected Sprite defend, item, skill;
	
	//Attack
	protected Enemy target;
	protected Playable friendly;
	protected int choice;
	protected int dp = 0;
	protected int cp = 0;
	protected int rp = 0;
	protected int sp = 0;
	protected Item itemChosen;
	
	//Timers
	protected int pwrTimer = 0;
	protected int dexTimer = 0;
	protected int spdTimer = 0;
	protected int evdTimer = 0;
	protected int resTimer = 0;
	protected int magTimer = 0;
	protected int defTimer = 0;
	protected int magDefTimer = 0;
	
	protected int dModTimer = 0;
	protected int mModTimer = 0;
	protected int regenTimer = 0;
	
	//Battle Message
	protected String message = "";
	
	public enum STATE {
		NORMAL, ILL, ATTACK, MAGIC, HIT, FLEE, DEAD, ITEM, SKILL, DEFEND, MENU, VICTORY;
	};
	
	private STATE state = STATE.NORMAL;
	
	//BATTLE ANIMATIONS
	public void update() {
		switch (state) {
		
		//ATTACK
		case ATTACK: anim++;
		if (anim <= 16) {
			sprite = right;
			x+=2;
		}
		else if (anim < 40) {
			x = target.getX() - 48; y = target.getY();
			sprite = attack_1;
		} 
		else if (anim == 40) {
			int attack = 0;
			Enemy target = getTarget();
			
			int pwr = getPwr();
			int dex = getDex();
			
			int eDef = target.getDef();
			int eEvd = target.getEvd();
			
			attack = ((pwr / eDef) * target.getDefMod()) / 100;
			
			int chance = random.nextInt(95);
			
			if (chance + (eEvd - dex) < 95) {
				target.setHP(-attack);
				target.changeState(STATES.HIT);
				target.setDP(attack);
				if (target.getSleep() == true) {
					target.setSleep(false);
					target.setMessage("Awakened");
				}
			}
			
			else setMessage("Attack Missed");
		}
		else if (anim <= 65) {
			sprite = attack_2;
		}
		else if (anim <= 75) {
			sprite = right;
			x = ax + 32; y = ay;
		}
		else if (anim <= 91) {
			sprite = right;
			x-=2;
		}
		else {
			state = STATE.NORMAL;
			anim = 0;
		}
		
		break;
		
		//NEGATIVE STATUS EFFECT
		case ILL: sprite = ill; break;
		
		//MAGIC
		case MAGIC: anim++;
		
		if (anim <= 16) {
			sprite = right;
			x+=2;
		}
		else if (anim <= 41) {
			sprite = magic_1;
		}
		else if (anim <= 64) {
			sprite = magic_2;
		}
		else if (anim <= 80) {
			sprite = right;
			x-=2;
		}
		else {
			state = STATE.NORMAL;
			anim = 0;
		} break;
		
		//USE ITEM
		case ITEM: anim++;
		if (anim <= 32) {
			sprite = right;
			x++;
		}
		else if (anim <= 82) {
			sprite = item;
		}
		else if (anim == 83) {
			Inventory.useItem(itemChosen, friendly);
		}
		else if (anim <= 132) {
			sprite = magic_2;
		}
		else if (anim <= 164) {
			sprite = right;
			x--;
		}
		else {
			state = STATE.NORMAL;
			anim = 0;
		} break;
		
		//USE SKILL
		case SKILL: anim++;
		if (anim <= 16) {
			sprite = right;
			x++;
		}
		else if (anim <= 41) {
			sprite = skill;
		}
		else if (anim <= 66) {
			sprite = magic_2;
		}
		else if (anim <= 82) {
			sprite = right;
			x--;
		}
		else {
			state = STATE.NORMAL;
			anim = 0;
		} break;
		
		//Take a HIT
		case HIT: anim++; 
		
		if (anim <= 35) sprite = hit;
		else {
			anim = 0; 
			state = STATE.NORMAL;
		}
		
		break;
		
		case VICTORY: anim++;
		if (anim > 30) {
			anim = 0;
		}
		else if (anim > 15) {
			sprite = magic_2;
		}
		else sprite = right;
		
		break;
		
		case FLEE: sprite = flee; animating = false; anim = 0; break;
		case DEAD: sprite = dead; animating = false; anim = 0; choice = 0; break;
		case DEFEND: sprite = defend; animating = false; anim = 0; break;
		
		case NORMAL: sprite = right; animating = false; anim = 0;
		if (hp == 0) state = STATE.DEAD; 
		else if (statusEffected) state = STATE.ILL; 
		else state = STATE.NORMAL; break;
		
		case MENU: sprite = down;
		if (hp == 0) state = STATE.DEAD; 
		else if (statusEffected) state = STATE.ILL; break;
		
		default: sprite = right; animating = false; break;
		}
	}
	
	public void render(Screen screen) {
		screen.renderMob(x, y, sprite);
	}
	
	//ANIMATIONS
	public void animate() {
		
	}
	
	//GETTER METHODS
	public String getName() {
		return name;
	}
	public String getType() {
		return type;
	}
	
	//STAT VALUES
	public int getHP() {
		return hp;
	}
	public int getMP() {
		return mp;
	}
	public int getEP() {
		return ep;
	}
	public int getPwr() {
		return pwr;
	}
	public int getDex() {
		return dex;
	}
	public int getSpd() {
		return spd;
	}
	public int getEvd() {
		return evd;
	}
	public int getRes() {
		return res;
	}
	public int getMag() {
		return mag;
	}
	public int getEng() {
		return eng;
	}
	public int getDef() {
		return def;
	}
	public int getMagDef() {
		return magDef;
	}
	
	public int getDefMod() {
		return defMod;
	}
	public int getMagMod() {
		return magMod;
	}
	public int getRegen() {
		return regen;
	}
	
	public int getExp() {
		return exp;
	}
	public int getLevel() {
		return lv;
	}
	public int getNextLv() {
		return nextLevel;
	}
	public int getPriorLv() {
		return priorLv;
	}
	public int getPriorExp() {
		return priorExp;
	}
	
	//STATUS EFFECT GETTERS
	public boolean getStatusEffected() {
		return statusEffected;
	}
	public boolean getPoisoned() {
		return poisoned;
	}
	public boolean getBurned() {
		if (burned) {
			poisoned = false;
			corroding = false;
		}
		return burned;
	}
	public boolean getCorroding() {
		return corroding;
	}
	public boolean getParalyzed() {
		return paralyzed;
	}
	public boolean getIll() {
		return illness;
	}
	public boolean getSleep() {
		if (asleep) paralyzed = false;
		return asleep;
	}
	
	//BASE STATS Base
	public int getMaxHP() {
		return maxHP;
	}
	public int getMaxMP() {
		return maxMP;
	}
	public int getMaxEP() {
		return maxEP;
	}
	public int getBasePwr() {
		return basePwr;
	}
	public int getBaseDex() {
		return baseDex;
	}
	public int getBaseSpd() {
		return baseSpd;
	}
	public int getBaseEvd() {
		return baseEvd;
	}
	public int getBaseRes() {
		return baseRes;
	}
	public int getBaseMag() {
		return baseMag;
	}
	public int getBaseEng() {
		return baseEng;
	}
	public int getBaseDef() {
		return baseDef;
	}
	public int getBaseMagDef() {
		return baseMagDef;
	}
	
	//SETTER METHODS
	public void reviveHP(int num) {
		hp = num;
		state = STATE.NORMAL;
	}
	public void setHP(int num) {
		hp += num;
		if (hp <= 0) {
			hp = 0;
			state = STATE.DEAD;
		}
		else if (hp > maxHP) hp = maxHP;
	}
	public void setMP(int num) {
		mp += num;
		if (mp < 0) mp = 0;
		else if (mp > maxMP) mp = maxMP;
	}
	public void setEP(int num) {
		ep += num;
		if (ep < 0) ep = 0;
		else if (ep > maxEP) ep = maxEP;
	}
	public void setPwr(int num) {
		pwr = num;
	}
	public void setDex(int num) {
		dex = num;
	}
	public void setSpd(int num) {
		spd = num;
	}
	public void setEvd(int num) {
		evd = num;
	}
	public void setRes(int num) {
		res = num;
	}
	public void setMag(int num) {
		mag = num;
	}
	public void setEng(int num) {
		eng = num;
	}
	public void setDef(int num) {
		def = num;
	}
	public void setMagDef(int num) {
		magDef = num;
	}
	public void setDefMod(int num) {
		defMod = num;
	}
	public void setMagMod(int num) {
		magMod = num;
	}
	public void setRegen(int num) {
		regen = num;
	}
	
	public void setParty(ArrayList<Playable> players) {
		party = players;
	}
	
	//Stat Effect Setters
	public void setStatusEffected() {
		if (!poisoned && !burned && !corroding && !paralyzed && !illness && !asleep) statusEffected = false;
		else statusEffected = true;
	}
	public void setPoisoned(boolean bool) {
		poisoned = bool;
	}
	public void setBurned(boolean bool) {
		burned = bool;
		if (burned) {
			poisoned = false;
			corroding = false;
		}
	}
	public void setCorroding(boolean bool) {
		corroding = bool;
	}
	public void setParalyzed(boolean bool) {
		paralyzed = bool;
	}
	public void setIll(boolean bool) {
		illness = bool;
	}
	public void setSleep(boolean bool) {
		asleep = bool;
		if (asleep) paralyzed = false;
	}
	
	//SET BASE STATS for LEVEL-UP
	public void setMaxHP(int num) {
		maxHP *= num;
	}
	public void setMaxMP(int num) {
		maxMP *= num;
	}
	public void setMaxEP(int num) {
		maxEP *= num;
	}
	public void setBasePwr(int num) {
		basePwr += num;
	}
	public void setBaseDex(int num) {
		baseDex += num;
	}
	public void setBaseSpd(int num) {
		baseSpd += num;
	}
	public void setBaseEvd(int num) {
		baseEvd += num;
	}
	public void setBaseRes(int num) {
		baseRes += num;
	}
	public void setBaseMag(int num) {
		baseMag += num;
	}
	public void setBaseEng(int num) {
		baseEng += num;
	}
	public void setBaseDef(int num) {
		baseDef += num;
	}
	public void setBaseMagDef(int num) {
		baseMagDef += num;
	}
	
	public void gainExp(int num) {
		exp += num;
		int extra = 0;
		
		while (exp >= nextLevel) {
			if (exp >= nextLevel) {
				lv++;
				extra = (exp - nextLevel);
				setNextLv();
				if (extra < 0) extra = -extra;
				levelUp();
			}
			
			exp = 0 + extra;
		}
	}
	
	public void setNextLv() {
		nextLevel *= 1.2;
	}
	public void levelUp() {
		
	}
	public void levelRestore() {
		hp = maxHP;
		mp = maxMP;
		ep = maxEP;
	}
	public void checkSpells() {
	}
	
	public void setPriorLv() {
		priorLv = lv;
	}
	public void setPriorExp() {
		priorExp = exp;
	}
	
	//Restore Stats from Save File
	public void restoreLv(int num) {
		lv = num;
	}
	public void restoreExp(int num) {
		exp = num;
	}
	public void restoreNextLv(int num) {
		nextLevel = num;
	}
	
	public void restoreHP(int num) {
		hp = num;
	}
	public void restoreMP(int num) {
		mp = num;
	}
	public void restoreEP(int num) {
		ep = num;
	}
	
	public void restoreMaxHP(int num) {
		maxHP = num;
	}
	public void restoreMaxMP(int num) {
		maxMP = num;
	}
	public void restoreMaxEP(int num) {
		maxEP = num;
	}
	public void restoreBasePwr(int num) {
		basePwr = num;
	}
	public void restoreBaseDex(int num) {
		baseDex = num;
	}
	public void restoreBaseSpd(int num) {
		baseSpd = num;
	}
	public void restoreBaseEvd(int num) {
		baseEvd = num;
	}
	public void restoreBaseRes(int num) {
		baseRes = num;
	}
	public void restoreBaseMag(int num) {
		baseMag = num;
	}
	public void restoreBaseEng(int num) {
		baseEng = num;
	}
	public void restoreBaseDef(int num) {
		baseDef = num;
	}
	public void restoreBaseMagDef(int num) {
		baseMagDef = num;
	}
	
	//SPRITES
	public Sprite getUp() {
		return up;
	}
	public Sprite getUp_1() {
		return up_1;
	}
	public Sprite getUp_2() {
		return up_2;
	}
	public Sprite getDown() {
		return down;
	}
	public Sprite getDown_1() {
		return down_1;
	}
	public Sprite getDown_2() {
		return down_2;
	}
	public Sprite getLeft() {
		return left;
	}
	public Sprite getLeft_1() {
		return left_1;
	}
	public Sprite getLeft_2() {
		return left_2;
	}
	public Sprite getRight() {
		return right;
	}
	public Sprite getRight_1() {
		return right_1;
	}
	public Sprite getRight_2() {
		return right_2;
	}
	public Sprite getAttack_1() {
		return ill;
	}
	public Sprite getAttack_2() {
		return attack_1;
	}
	public Sprite getMagic_1() {
		return magic_1;
	}
	public Sprite getMagic_2() {
		return magic_2;
	}
	public Sprite getIlled() {
		return ill;
	}
	public Sprite getHit() {
		return hit;
	}
	public Sprite getFlee() {
		return flee;
	}
	public Sprite getDead() {
		return dead;
	}
	public Sprite getDefend() {
		return defend;
	}
	public Sprite getSkill() {
		return skill;
	}
	public Sprite getItemPose() {
		return item;
	}
	
	
	//Location on-screen
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public void changeCoordinates(int x, int y) {
		this.x = x;
		this.y = y;
		ax = x;
		ay = y;
	}
	
	//Change state or animation
	public void changeState(STATE state) {
		this.state = state;
	}
	public boolean isAnimating() {
		return animating;
	}
	public void startAnimation() {
		animating = true;
	}
	public void stopAnimation() {
		animating = false;
	}
	
	//TARGETING
	public Enemy getTarget() {
		return target;
	}
	public Playable getFriendly() {
		return friendly;
	}
	public int getChoice() {
		return choice;
	}
	public int getDP() {
		return dp;
	}
	public int getCP() {
		return cp;
	}
	public int getRP() {
		return rp;
	}
	public int getSP() {
		return sp;
	}
	public void setTarget(Enemy e) {
		target = e;
	}
	public void setFriendly(Playable p) {
		friendly = p;
	}
	public void setChoice(int num) {
		choice = num;
	}
	public void setDP(int num) {
		dp -= num;
	}
	public void setCP(int num) {
		cp += num;
	}
	public void setRP(int num) {
		rp += num;
	}
	public void setSP(int num) {
		sp -= num;
	}
	
	//BATTLE MESSAGE
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String string) {
		message = string;
	}
	
	//SPELLS, TECHS, and SKILLS
	public ArrayList<Spell> getCures() {
		return cureSpells;
	}
	public ArrayList<Spell> getOffs() {
		return offSpells;
	}
	public ArrayList<Spell> getDefs() {
		return defSpells;
	}
	
	public ArrayList<Skill> getSkills() {
		return skills;
	}
	
	public ArrayList<Playable> getParty() {
		return party;
	}
		
	public void learnSpell(Spell spell) {
		switch (spell.getType()) {
		case "Curative": cureSpells.add(spell); break;
		case "Offensive": offSpells.add(spell); break;
		case "Defensive": defSpells.add(spell); break;
		}
		newSpell = spell.getName();
	}
	public void learnSkill(Skill skill) {
		skills.add(skill);
		newSkill = skill.getName();
	}
	
	public Spell getSpellChosen() {
		return spellChosen;
	}
	public int getTypeChosen() {
		return typeChosen;
	}
	public Skill getSkillChosen() {
		return skillChosen;
	}
	public Item getItem() {
		return itemChosen;
	}
	
	public void setSpellType(int num) {
		typeChosen = num;
	}
	public void setSpell(Spell spell) {
		spellChosen = spell;
	}
	public void setSkillChosen(Skill skill) {
		skillChosen = skill;
	}
	public void setItem(Item it) {
		itemChosen = it;
	}
	
	//STAT EFFECT TIMERS SETTERS
	public void setPwrTimer(int num) {
		pwrTimer = num;
	}
	public void setDexTimer(int num) {
		dexTimer = num;
	}
	public void setSpdTimer(int num) {
		spdTimer = num;
	}
	public void setEvdTimer(int num) {
		evdTimer = num;
	}
	public void setResTimer(int num) {
		resTimer = num;
	}
	public void setMagTimer(int num) {
		magTimer = num;
	}
	public void setDefTimer(int num) {
		defTimer = num;
	}
	public void setMagDefTimer(int num) {
		magDefTimer = num;
	}
	public void setDefModTimer(int num) {
		dModTimer = num;
	}
	public void setMagModTimer(int num) {
		mModTimer = num;
	}
	public void setRegenTimer(int num) {
		regenTimer = num;
	}
	
	//Spell/skill learned
	public String getNewSpell() {
		return newSpell;
	}
	public void setNewSpell() {
		newSpell = "";
		newSkill = "";
	}
	
	public String getNewSkill() {
		return newSkill;
	}
	
	//TIMER DECAY
	public void timer() {
		
		if (pwrTimer == 0) pwr = basePwr;
		pwrTimer--;
		
		if (dexTimer == 0) dex = baseDex;
		dexTimer--;
		
		if (spdTimer == 0) spd = baseSpd;
		spdTimer--;
		
		if (evdTimer == 0) evd = baseEvd;
		evdTimer--;
		
		if (resTimer == 0) res = baseRes;
		resTimer--;
		
		if (magTimer == 0) mag = baseMag;
		magTimer--;
		
		if (defTimer == 0) def = baseDef;
		defTimer--;
		
		if (magDefTimer == 0) magDef = baseMagDef;
		magDefTimer--;
		
		if (dModTimer == 0) defMod = 100;
		dModTimer--;
		
		if (mModTimer == 0) magMod = 100;
		mModTimer--;
		
		if (regenTimer == 0) regen = 0;
		else setHP(regen);
	}
	
	public void restoreSpells() {
	}
	
	public void resetSpells() {
		cureSpells = new ArrayList<Spell>();
		offSpells = new ArrayList<Spell>();
		defSpells = new ArrayList<Spell>();
		skills = new ArrayList<Skill>();
	}
	
	public int getIndex() {
		return index;
	}
}

