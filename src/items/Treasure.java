package items;

import states.Screen;
import graphics.Sprite;
import graphics.SpriteSheet;

public class Treasure {

	protected int x, y;
	protected Sprite sprite = new Sprite(32, 0, 0, SpriteSheet.items);;
	protected boolean inIn = false;
	
	public Treasure(int x, int y) {
		this.x = x << 5;
		this.y = y << 5;
	}
	
	public void render(Screen screen) {
		screen.renderMob(x, y, sprite);
	}
	
	public void effect() {
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	//If item is or is not already in inventory
	public boolean getIn() {
		return inIn;
	}
	
	public void checkInventory() {
	}
	
}
