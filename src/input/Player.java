package input;

import entity.mobs.enemies.Enemy;
import entity.mobs.enemies.EnemyParty;
import entity.mobs.npcs.NPC;
import entity.mobs.npcs.ShopKeeper;
import graphics.Sprite;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;

import levels.Level;
import levels.WorldMap;
import main.Game;
import states.Battle;
import states.Dialogue;
import states.Screen;
import states.Store;
import animations.Animation;
import characters.Party;
import characters.Playable;

public class Player {
	
	//position and movement
	public static int x, y;
	public static int xa, ya;
	private Keyboard input;
	public static Level level;
	
	//Animations
	private static Playable character;
	private static Sprite sprite;
	private int anim = 0;
	private int dir = 0;
	private int speed = 4;
	private static boolean walking = false;
	private boolean collision = false;
	private boolean resting = false;
	private boolean action = false;
	private static int distance = 0;
	
	private Font font = new Font("Times New Roman", Font.BOLD, 30);
	
	//Sprites
	private static Sprite up, up_1, up_2;
	private static Sprite down, down_1, down_2;
	private static Sprite right, right_1, right_2;
	private static Sprite left, left_1, left_2;
	//private static Sprite attack_1, attack_2, magic_1, magic_2;
	//private static Sprite hit, ill, flee, dead;
	//private static Sprite defend, item, skill;
	
	//Battle
	public static boolean fleeing = false;
	private static int fleeTimer = 0;
	
	//Environment Interaction
	public static boolean chosen = false;
	
	//Regular player
	public Player(int x, int y, Keyboard input, Level level) { //Player is generated as a specific location
		Player.x = x << 5; //x refers back to Entity x
		Player.y = y << 5; //y refers back to Entity y
		this.input = input;
		Player.level = level;
		
		changeSprites(Party.ark);
		
		sprite = down;
	}
	
	public void update() { //automatically override superclass update methods		
		if (input.up) {
			ya -= speed;
			dir = 0;
		}
		if (input.down) {
			ya += speed;
			dir = 2;
		}
		if (input.left) {
			xa -= speed;
			dir = 3;
		}
		if (input.right) {
			xa += speed;
			dir = 1;
		}
		
		if (xa > 0) dir = 1;
		else if (xa < 0) dir = 3;
		else if (ya > 0) dir = 2;
		else if (ya < 0) dir = 0;
		
		if (anim > 9999) anim = 0;
		if (fleeTimer > 9999) fleeTimer = 0;
		fleeTimer++;
		
		if (fleeTimer > 400) fleeing = false;
		
		tileCollision(xa, ya);
		
		if (xa != 0 || ya != 0) {
			walking = true;
			if (!collision) move(xa, ya);
			anim+=2;
		}
		
		else anim = 0;
		
		if (!fleeing && Game.State == Game.STATE.GAME) enemyCollision();
		npcRange();
		shopRange();
		triggerCutscene();
		rareItem();
		
		if (chosen) chosen = false;
	}

	public void render(Screen screen, Graphics g) {
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
		
		g.setColor(Color.WHITE);
		g.setFont(font);
		if (resting) g.drawString("PARTY FULLY RESTORED", Game.frame.getWidth()/3, Game.frame.getHeight()/3);
		
		if (action) g.drawString("!", Game.frame.getWidth()/2, Game.frame.getHeight()/2);
		
		screen.renderMob(x, y, sprite); 
		xa = 0; ya = 0;
	}
	
	private static void move(int xa, int ya) {
			x += xa;
			y += ya;
	}
	
	private void tileCollision(int xa, int ya) { 
		for (int c = 0; c < 4; c++) { //detecting corners for precise collision detection
			int xc = (x + xa + c % 2 * 5 + 13) / 32;
			int yc = (y + ya + c % 2 * 5 + 25) / 32;
			if(level.getTile(xc, yc).solid()) collision = true;
			else collision = false;
			
			if (level.getTile(xc, yc).warp()) level.getTile(xc, yc).switchLevel();
			else if (level.getTile(xc, yc).rest()) {
				resting = true;
				for (int i = 0; i < Party.partyPresent.size(); i++) {
					Party.partyPresent.get(i).levelRestore();
				}
			}
			else resting = false;
			if (level.getTile(xc, yc).map()) {
				if (level.levelName == "World_Map") {
					Sprite sprite = level.getTile(xc, yc).getSprite();
					WorldMap.changeArea(sprite, yc);
				}
				else WorldMap.loadPlayer(level.levelName);
			}
		}
	}
	
	public static void changeSprite(Sprite s) {
		sprite = s;
	}
	
	public static void changeSprites(Playable p) {
		character = p;
		
		up = character.getUp();
		up_1 = character.getUp_1();
		up_2 = character.getUp_2();
		
		down = character.getDown();
		down_1 = character.getDown_1();
		down_2 = character.getDown_2();
		
		right = character.getRight();
		right_1 = character.getRight_1();
		right_2 = character.getRight_2();
		
		left = character.getLeft();
		left_1 = character.getLeft_1();
		left_2 = character.getLeft_2();
		
		/*
		attack_1 = character.getAttack_1();
		attack_2 = character.getAttack_2();
		magic_1 = character.getMagic_1();
		magic_2 = character.getMagic_2();
		
		hit = character.getHit();
		ill = character.getIlled();
		flee = character.getFlee();
		dead = character.getDead();
		
		defend = character.getDefend();
		item = character.getItemPose();
		skill = character.getSkill();
		*/
	}
	
	private void enemyCollision() {
		ArrayList<Enemy> en = Level.enemies;
		fleeTimer = 0;
		
		//player coordinates
		int px = x;
		int py = y;
		
		for (int i = 0; i < en.size(); i++) {
			Enemy e = en.get(i);
			int mx = e.getX();
			int my = e.getY();
			
			int dx = px - mx;
			int dy = py - my;
		
			int dist = (int) Math.sqrt((dx*dx) + (dy*dy)); 
		
			if (dist <= en.get(i).getEncounterRange()) { //In range
				if (en.get(i).getType() == "Boss") { //Set Boss battle
				}
				
				if (en.get(i).getNum() == 1) EnemyParty.changeParty(e);
				else EnemyParty.changeParty(e, e.getNum());
				
				Game.State = Game.STATE.BATTLE;
				Game.battleScreen = new Battle();
			}
		}
	}
	
	//Player contacts friendly NPC
	private void npcRange() {
		//player coordinates
		int px = x;
		int py = y;
			
		for (int i = 0; i < Level.npcs.size(); i++) {
			NPC n = Level.npcs.get(i);
				
			int mx = n.getX();
			int my = n.getY();
			
			int dx = px - mx;
			int dy = py - my;
			
			int dist = (int) Math.sqrt((dx*dx) + (dy*dy)); 
			
			if (dist <= 32 && chosen) {
				Game.State = Game.STATE.DIALOGUE;
				
				//Set Dialogue
				Dialogue.dialogue = n.getDialogue();	
			}
		}
	}

	private void shopRange() {
		int px = x;
		int py = y;
		
		ShopKeeper sk = Level.sk;
		
		int mx = sk.getX();
		int my = sk.getY();
		
		int dx = px - mx;
		int dy = py - my;
		
		int dist = (int) Math.sqrt((dx*dx) + (dy*dy));
		
		if (dist <= 64 && chosen) {
			Game.State = Game.STATE.STORE;
			Store.openStore();
			Store.chosen = false;
			Store.choice = 0;
		}
	}
	
	private void triggerCutscene() {
		int px = x;
		int py = y;
					
		for (int i = 0; i < Level.cutscenes.size(); i++) {
			Animation a = Level.cutscenes.get(i);
			
			int mx = a.getX();
			int my = a.getY();
				
			int dx = px - mx;
			int dy = py - my;
				
			int dist = (int) Math.sqrt((dx*dx) + (dy*dy)); 
				
			if (dist <= a.getRange()) {
				if (a.getPre() && !a.isFinished()) {
					xa = 0; ya = 0;
					a.startAnimation();
					Game.State = Game.STATE.ANIMATING;
					input.update();
				}
			}
		}
	}

	private void rareItem() {
		int px = x;
		int py = y;
		
		int mx = Level.t.getX();
		int my = Level.t.getY();
		
		int dx = px - mx;
		int dy = py - my;
		
		int dist = (int) Math.sqrt((dx*dx) + (dy*dy));
		
		Level.t.checkInventory();
		
		if (dist <= 40 && !Level.t.getIn()) {
			action = true;
			if (chosen) {
				Level.t.effect();
			}
		}
		else action = false;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	//Cutscene Animations
	public static void moveX(int x, int dist) {
		if (distance >= dist << 5) xa = 0;
		xa = x;
		walking = true;
		if (x == 0) walking = false;
		distance++;
		move(xa, ya);
	}
	
	public static void moveY(int y, int dist) {
		if (distance >= dist << 5) ya = 0;
		ya = y;
		walking = true;
		if (y == 0) walking = false;
		distance++;
		move(xa, ya);
	}
	
	public static void turn(int dir) {
		switch(dir) {
		case 0: sprite = up; break;
		case 1: sprite = right; break;
		case 2: sprite = down; break;
		case 3: sprite = left; break;
		}
	}
}
