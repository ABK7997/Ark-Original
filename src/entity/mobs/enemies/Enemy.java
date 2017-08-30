package entity.mobs.enemies;

import input.Player;

import java.util.ArrayList;
import java.util.Random;

import characters.Playable;
import characters.Playable.STATE;
import main.Game;
import states.Battle;
import states.Screen;
import battle.Skill;
import battle.Spell;
import entity.mobs.Mob;

public abstract class Enemy extends Mob implements Cloneable {
	
	//RNG
	protected Random random = new Random();
	
	//Timing
	protected long time = 0;
	protected long elapsed = 0;
	protected int ax, ay; //Resets coordinates after a physical attack
	
	//Movement and Animation
	protected boolean inRange;
	protected int anim = 0;
	public boolean animating = false;
	protected boolean hasGone = false;
	
	//Player Detection
	protected int encounterRange = 32;
	protected int chaseRange;
	
	//Stats
	protected String name = "";
	protected String type;
	protected int lv, expGiven, moneyGiven;
	protected int maxHP, maxMP, maxEP;
	protected int hp, mp, ep;
	protected int pwr, dex, spd, evd, res, mag, eng, def, magDef;
	protected int basePwr, baseDex, baseSpd, baseEvd, baseRes, baseMag, baseEng, baseDef, baseMagDef;
	protected int defMod = 100;
	protected int magMod = 100;
	
	protected int fleeChance;
	protected int mobNum;
	
	//Status Effects
	protected boolean statusEffected = false;
	protected boolean poisoned = false;
	protected boolean burned = false;
	protected boolean corroding = false;
	protected boolean paralyzed = false;
	protected boolean illness = false;
	protected boolean asleep = false;
	
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
	
	protected int regen;
	
	//Battle Message
	protected String message = "";
	
	//Attacks
	protected int[] moves;
	protected Playable target;
	protected Enemy friendly = this;
	protected int choice = 0; protected int choice2 = 0;
	protected int dp = 0;
	protected int cp = 0;
	protected int rp = 0;
	protected int sp = 0;
	protected boolean attacks;
	
	//Magic / Tech / Skill
	protected ArrayList<Spell> offSpells = new ArrayList<Spell>();
	protected ArrayList<Spell> cureSpells = new ArrayList<Spell>();
	protected ArrayList<Spell> defSpells = new ArrayList<Spell>();
	protected int typeChosen = 0;
	protected Spell spellChosen;
	
	protected ArrayList<Skill> skills = new ArrayList<Skill>();
	protected Skill skillChosen;
	
	protected ArrayList<Enemy> party = new ArrayList<Enemy>();
	
	//Boss ID
	protected int id;
	
	//Battle States
	public enum STATES {
		NORMAL, ATTACK, MAGIC, SKILL, DEAD, ILL, HIT;
	};
	
	private STATES state = STATES.NORMAL;
	
	public void update() { //Default movement	
		if (hp == 0) removed = true;
		
		if (Game.State == Game.STATE.GAME) {
			
			int xa = 0; int ya = 0;
		
			elapsed = System.currentTimeMillis() - time;
			if (elapsed >= 3000) {
			
				time = System.currentTimeMillis();
			
				movement = random.nextInt(7);
			}
		
			if (movement == 0) xa = speed;
			else if (movement == 1) xa = speed;
			else if (movement == 2) ya = -speed;
			else if (movement == 3) ya = -speed;
		
			if (xa != 0 || ya != 0 && !collision(xa, ya)) { //enables movement
				move(xa, ya);
			}
		
			else {
				walking = false;
			}
		}
	}
	
	public void noMovement() {
	}
	
	public void render(Screen screen) {
		if (Game.State == Game.STATE.GAME) {
			if (dir == 0) { //Up
				sprite = up;
				if (walking) {
					if (anim % 40 > 20) {
						sprite = up_1;
					}
					else if (anim % 40 > 10){
						sprite = up_2;
					}
				}
			}
			
			else if (dir == 2) { //Down
				sprite = down;
				if (walking) {
					if (anim % 40 > 20) {
						sprite = down_1;
					}
					else if (anim % 40 > 10) {
						sprite = down_2;
					}
				}
			}
		
			else if (dir == 1) { //Right
				sprite = right;
				if (walking) {
					if (anim % 40 > 20) {
						sprite = right_1;
					}
					else if (anim % 40 > 10) {
						sprite = right_2;
					}
				}
			}
		
			else if (dir == 3) { //Left
				sprite = left;
				if (walking) {
					if (anim % 40 > 20) {
						sprite = left_1;
					}
					else if (anim % 40 > 10) {
						sprite = left_2;
					}
				}
			}
		
			xa = 0; ya = 0;
		}
		
		else animate();
		
		screen.renderMob(x, y, sprite); 
	}
	
	//Random AI
	public void randomMovement() {
		elapsed = System.currentTimeMillis() - time;
		if (elapsed >= 3000) {
			
			time = System.currentTimeMillis();
			
			movement = random.nextInt(5);
		}
		
		if (movement == 0) xa = speed;
		else if (movement == 1) xa = -speed;
		else if (movement == 2) ya = speed;
		else if (movement == 3) ya = -speed;
		
		//Turn in opposite direction if colliding with a walll
		if (collision(xa, ya) && movement == 0) movement = 1;
		else if (collision(xa, ya) && movement == 1) movement = 0;
		else if (collision(xa, ya) && movement == 2) movement = 3;
		else if (collision(xa, ya) && movement == 3) movement = 2;
		
		if (xa != 0 || ya != 0 && !collision(xa, ya)) { //enables movement
			move(xa, ya);
			walking = true;
		}
		else {
			walking = false;
		}
		
		if (anim > 9999) anim = 0;
		anim+=2;
	}
	
	//Linear AI
	public void linearMovement() {
		move(xa, ya);
		walking = true;
		
		if (anim > 9999) anim = 0;
		anim++;
	}
	
	//Chaser AI
	public void chaserMovement() {
		int xa = 0; int ya = 0;
		
		chaserDistance();
		
		if (inRange) {
			follow();
			return;
		}
		
		elapsed = System.currentTimeMillis() - time;
		if (elapsed >= 3000) {
			
			time = System.currentTimeMillis();
			
			movement = random.nextInt(5);
		}
		
		if (movement == 0) xa = speed*3/4;
		else if (movement == 1) xa = -speed*3/4;
		else if (movement == 2) ya = speed*3/4;
		else if (movement == 3) ya = -speed*3/4;
		
		//Turn in opposite direction if colliding with a walll
		if (collision(xa, ya) && movement == 0) movement = 1;
		else if (collision(xa, ya) && movement == 1) movement = 0;
		else if (collision(xa, ya) && movement == 2) movement = 3;
		else if (collision(xa, ya) && movement == 3) movement = 2;
		
		if (xa != 0 || ya != 0 && !collision(xa, ya)) { //enables movement
			move(xa, ya);
			walking = true;
		}
		else {
			walking = false;
		}
		
		if (anim > 9999) anim = 0;
		anim+=2;
	}
	
	public void chaserDistance() {
		//Chaser coordinates
		int cx = x;
		int cy = y;
		
		//Player coordinates
		Player player = Game.player;
		int px = player.getX();
		int py = player.getY();
		
		//horizontal and vertical distance
		int dx = cx - px;
		int dy = cy - py;
		
		//total distance (Pythagorean Theorem)
		double dist = Math.sqrt((dx*dx) + (dy*dy));
		if (dist <= chaseRange) inRange = true;
		else inRange = false;
	}
	
	public void follow() {
		int xa = 0;
		int ya = 0;
		Player player = Game.player;
		if (x < player.getX()) xa = speed;
		if (x > player.getX()) xa = -speed;
		if (y < player.getY()) ya = speed;
		if (y > player.getY()) ya = -speed;
		
		if (xa != 0 || ya != 0) { //enables movement
			walking = true;
			move(xa, ya);
		}
		else {
			walking = false;
		}
		
		if (anim > 9999) anim = 0;
		anim+=2;
	}
	
	//Battle Behavior and Move Selection
	public void behavior() {
	}
	
	//STAT GETTERS
	
	//Basic Info
	public String getName() {
		return name;
	}
	public String getType() {
		return type;
	}
	public int getHP() {
		return hp;
	}
	public int getMP() {
		return mp;
	}
	public int getEP() {
		return ep;
	}
	
	//Battle stats
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
	
	//STATUS EFFECT GETTERS
	public boolean getStatusEffected() {
		return statusEffected;
	}
	public boolean getPoisoned() {
		if (type == "Machine" || type == "Cygic") return false;
		else return poisoned;
	}
	public boolean getBurned() {
		if (burned) {
			poisoned = false;
			corroding = false;
		}
		return burned;
	}
	public boolean getCorroding() {
		if (type == "Organic" || type == "Mutant" || type == "Hybrid") return false;
		else return corroding;
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
	
	//BASE STATES
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
	public void setHP(int num) {
		hp += num;
		if (hp <= 0) {
			hp = 0;
			state = STATES.DEAD;
		}
		else if (hp > maxHP) hp = maxHP;
	}
	public void setMP(int num) {
		mp += num;
		if (mp <= 0) mp = 0;
		else if (mp > maxMP) mp = maxMP;
	}
	public void setEP(int num) {
		ep += num;
		if (ep <= 0) mp = 0;
		else if (mp > maxMP) mp = maxMP;
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
	
	//Regen
	public void setRegen(int num) {
		regen = num;
	}
	public int getRegen() {
		return regen;
	}
	
	//Experience and Money given
	public int getExp() {
		return expGiven;
	}
	
	public int getMoney() {
		return moneyGiven;
	}
	
	//Chance to flee successfully from mob
	public int getFleeChance() {
		return fleeChance;
	}
	
	//Battle range
	public int getEncounterRange() {
		return encounterRange;
	}
	
	public void setEncounterRange(int num) {
		encounterRange += num;
	}
	
	public void changeEncounterRange(int num) {
		encounterRange = num;
	}
	
	//Monster party size
	public int getNum() {
		return mobNum;
	}
	
	//Boss id
	public int getID() {
		return id;
	}
	
	//Change coordinates for the start of a battle
	public void changeCoordinates(int x, int y) {
		this.x = x;
		this.y = y;
		ax = x;
		ay = y;
	}
	
	//Clone
	protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
	
	//Animation
	public void animate() {
		if (Game.State == Game.STATE.BATTLE) {
			switch (state) {
			
			//ATTACK
			case ATTACK: anim++;
			
			if (anim <= 32) {
				sprite = left;
				x--;
			}
			else if (anim < 64) {
				x = target.getX() + 48; y = target.getY();
				sprite = attack_1;
			} 
			else if (anim == 64) {
				int attack = 0;
				Playable target = getTarget();
				
				int ePwr = getPwr();
				int eDex = getDex();
				
				int def = target.getDef();
				int evd = target.getEvd();
				
				attack = ((ePwr / def) * target.getDefMod()) / 100;
				
				int chance = random.nextInt(100);
				
				if (chance + (evd - eDex) < 95) {
					target.setHP(-attack);
					target.changeState(STATE.HIT);
					target.setDP(attack);
					if (target.getSleep() == true) {
						target.setSleep(false);
						target.setMessage("Awakened");
					}
				}
				
				else setMessage("Attack Missed");
			}
			else if (anim <= 96) {
				sprite = attack_2;
			}
			else if (anim <= 128) {
				sprite = left;
				x = ax - 32; y = ay;
			}
			else if (anim <= 160) {
				sprite = left;
				x++;
			}
			else {
				state = STATES.NORMAL;
				anim = 0; hasGone = true;
				Battle.secondMove = true;
			}
			
			break;
			
			//MAGIC
			case MAGIC: anim++;
			if (anim <= 32) {
				sprite = left;
				x--;
			}
			else if (anim <= 82) {
				sprite = magic_1;
			}
			else if (anim <= 132) {
				sprite = magic_2;
			}
			else if (anim <= 164) {
				sprite = left;
				x++;
			}
			else {
				state = STATES.NORMAL;
				anim = 0; hasGone = true;
				Battle.secondMove = true;
			} break;
			
			//SKILL
			case SKILL: anim++;
			if (anim <= 32) {
				sprite = left;
				x--;
			}
			else if (anim <= 82) {
				sprite = skill;
			}
			else if (anim <= 132) {
				sprite = magic_2;
			}
			else if (anim <= 164) {
				sprite = left;
				x++;
			}
			else {
				state = STATES.NORMAL;
				anim = 0; hasGone = true;
				Battle.secondMove = true;
			} break;
			
			//Take a HIT
			case HIT: anim++; animating = true;
			
			if (anim <= 30) {
				sprite = hit;
			}
			else {
				anim = 0; 
				state = STATES.NORMAL;
				animating = false;
			}
			
			break;
			
			//OTHERS
			case DEAD: sprite = dead; animating = false; choice = 0; anim = 0; break;
			case ILL: sprite = ill; animating = false; break;
			
			case NORMAL: sprite = left; animating= false; anim = 0;
			if (hp == 0) state = STATES.DEAD;
			else {
				setStatusEffected();
				if (statusEffected) state = STATES.ILL; break;
			}
			
			default: sprite = left; animating = false; break;
			}
		}
	}
	
	public void startAnimation() {
		animating = true; anim = 0; 
	}
	
	public boolean isAnimating() {
		return animating;
	}

	public boolean hasGone() {
		animating = false;
		return hasGone;
	}
	
	public void setGone(boolean bool) {
		hasGone = bool;
	}
	
	public void changeState(STATES attack) {
		this.state = attack;
	}
	
	//TARGETING
	public Playable getTarget() {
		return target;
	}
	public Enemy getFriendly() {
		return friendly;
	}
	public int getChoice() {
		return choice;
	}
	public int getChoice2() {
		return choice2;
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
	public void setTarget(Playable p) {
		target = p;
	}
	public void setFriendly(Enemy e) {
		friendly = e;
	}
	public void setChoice(int num) {
		choice = num;
	}
	public void setChoice2(int num) {
		choice2 = num;
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
	
	public void setParty(ArrayList<Enemy> enemies) {
		party = enemies;
	}
	public ArrayList<Enemy> getParty() {
		return party;
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
	public void setSpellType(int num) {
		typeChosen = num;
	}
	public void setSpell(Spell spell) {
		spellChosen = spell;
	}
	public void setSkillChosen(Skill skill) {
		skillChosen = skill;
	}
	
	public boolean doesAttack() {
		return attacks;
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
		else regenTimer--;
	}
	
	//BATTLE MESSAGE
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String string) {
		message = string;
	}
	
}
