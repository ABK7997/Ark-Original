package entity;

import main.Game;

import java.util.Random;

import states.Screen;
import levels.Level;

public abstract class Entity {

	public int x, y, xa, ya;
	protected boolean removed = false;
	public Level level = Game.level;
	protected final Random random = new Random();
	protected boolean finished = false;
	
	public void update() { //never used; inherited by subclasses	
	}
	
	public void render(Screen screen) { //never used; inherited by subclasses	
	}
	
	public void remove() { //method to remove entity from a level	
		removed = true;
	}
	
	public boolean getRemoved() { //returns whether entity has been removed or not
		return removed;
	}
	
	public void init(Level level) { //assigns any created entity to the level they are spawned into
		this.level = level;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setX(int position) {
		x = position;
	}
	
	public void setY(int position) {
		y = position;
	}
	
	public boolean getFinished() {
		return finished;
	}
	
	public void setFinished(boolean bool) {
		finished = bool;
	}
	
}
