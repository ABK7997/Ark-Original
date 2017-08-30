package entity.mobs.npcs;

import states.Screen;
import entity.mobs.Mob;
import graphics.Sprite;
import graphics.SpriteSheet;

public class ShopKeeper extends Mob {
	
	public ShopKeeper(int x, int y) {
		this.x = x << 5;
		this.y = y << 5;
		
		down = new Sprite(32, 0, 9, SpriteSheet.playable);
		
		sprite = down;
	}
	
	public boolean getShop() {
		return true;
	}
	
	public void render(Screen screen) {
		screen.renderMob(x, y, sprite);
	}
	
}
