package tiles;

import states.Screen;
import tiles.Tile;
import graphics.Sprite;

public class Solid extends Tile {

	public Solid(Sprite sprite) {
		super(sprite);
	}
	
	public void render(int x, int y, Screen screen) {
		screen.renderTile(x*32, y*32, this);
	}
}
