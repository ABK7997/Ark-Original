package tiles;

import states.Screen;
import graphics.Sprite;

public class WarpTile extends Tile {
	
	public WarpTile(Sprite sprite) {
		super(sprite);
	}
	
	public void render(int x, int y, Screen screen) {
		screen.renderTile(x*32, y*32, this);
	}
	
	public boolean solid() {
		return false;
	}
	
	public boolean warp() {
		return true;
	}

}
