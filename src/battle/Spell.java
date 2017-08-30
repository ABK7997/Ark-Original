package battle;

import java.awt.Graphics;
import java.util.Random;

import main.Game;
import characters.Playable;
import entity.mobs.enemies.Enemy;

public abstract class Spell {

	protected int x, y;
	protected Random random = new Random();
	protected int anim = 0;
	protected int w = Game.frame.getWidth();
	protected int h = Game.frame.getHeight();
	protected String name;
	protected int dmg, cost;
	protected String type;
	protected String range = "Single";
	protected Playable p;
	protected Playable friend;
	protected Enemy e;
	protected Enemy eFriend;
	protected boolean animating = false;
	protected boolean usable = false;
	protected String description = "Spell";
	
	public void heal(Playable m) {
	}
	
	public void heal(Enemy e) {
	}
	
	public void attack(Enemy e) {
	}
	
	public void attack(Playable p) {
	}
	
	public void alter(Playable p) {
	}
	
	public void alter(Enemy e) {
	}
	
	public void animate(Graphics g) {
	}
	public void animate2(Graphics g) {
	}
	
	public String getName() {
		return name;
	}
	
	public String getType() {
		return type;
	}
	
	public int getCost() {
		return cost;
	}
	
	public int getDmg() {
		return dmg;
	}
	
	public boolean isAnimating() {
		return animating;
	}
	
	public boolean usable() {
		return usable;
	}
	
	public String getRange() {
		return range;
	}
	
	public String getDescription() {
		return description;
	}
}
