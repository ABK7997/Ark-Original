package battle;

import java.awt.Graphics;
import java.util.Random;

import characters.Playable;
import entity.mobs.enemies.Enemy;
import main.Game;

public class Skill {

	protected Random random = new Random();
	
	protected int x, y;
	protected int w = Game.frame.getWidth();
	protected int h = Game.frame.getHeight();
	protected String name;
	protected String effect;
	protected int dmg, anim;
	protected String type;
	protected String range = "Single";
	protected Playable p;
	protected Enemy e;
	protected boolean animating = false;
	protected String description = "Skill";
	protected Playable friend;
	protected Enemy eFriend;
	
	public void heal(Playable m) {
	}
	public void heal(Enemy m) {
	}
	
	public void attack(Enemy e) {
	}
	public void attack(Playable p) {
	}
	
	public void alter(Playable m) {
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
	
	public int getDmg() {
		return dmg;
	}
	
	public boolean isAnimating() {
		return animating;
	}
	
	public String getRange() {
		return range;
	}
	
	public String getDescription() {
		return description;
	}
	
}
