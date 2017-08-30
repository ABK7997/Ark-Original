package tiles;

import main.Game;
import main.SaveState;
import states.Screen;
import graphics.Sprite;

public class Tile {

	public int mapX, mapY;
	public int x, y;
	public Sprite sprite;
	
	public Tile(Sprite sprite) {
		this.sprite = sprite;
	}
	
	public boolean solid() {
		return true;
	}
	
	public boolean warp() {
		return false;
	}
	
	public boolean map() {
		return false;
	}
	
	public boolean rest() {
		return false;
	}
	
	public void render(int x, int y, Screen screen) {
	}
	
	public int getX() {
		return mapX;
	}
	
	public int getY() {
		return mapY;
	}
	
	public void switchLevel() {
		switch(Game.levelName) {
		case "Orzeik": Game.levelName = "Orzeik_Sub"; break;
		case "Orzeik_Sub": Game.levelName = "Orzeik"; break;
		case "Trench": Game.levelName = "Trench_Sub"; break;
		case "Trench_Sub": Game.levelName = "Trench"; break;
		case "Swamp": Game.levelName = "Swamp_Sub"; break;
		case "Swamp_Sub": Game.levelName = "Swamp"; break;
		case "Mortisia": Game.levelName = "Mortisia_Sub"; break;
		case "Mortisia_Sub": Game.levelName = "Mortisia"; break;
		}
		
		SaveState.loadLevel();
	}
	
	public Sprite getSprite() {
		return sprite;
	}
	
}
