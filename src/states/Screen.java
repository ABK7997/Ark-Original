package states;

import graphics.Sprite;

import tiles.Tile;

public class Screen {

	//Dimensions (uses Game dimensions)
	public int width, height;
			
	//Map and Rendering
	public int[] tiles = new int[0];
	public int[] pixels;
			
	//Offsets
	public int xOffset, yOffset;
	
	public Screen(int width, int height) {
		this.width = width;
		this.height = height;
		pixels = new int[width * height]; //pixels on the screen is the same as the dimensions of the screen and, by extension, the game
	}
	
	//CLEAR SCREEN
	public void clear() {
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = 0;
		}
	}
	
	//Render individual TILE (not used by Screen object itself)
	public void renderTile(int xp, int yp, Tile tile) {
		xp -= xOffset; //map moves in the opposite direction of the player
		yp -= yOffset;
		
		tile.mapX = xp;
		tile.mapY = yp;
		
		for (int y = 0; y < tile.sprite.SIZE; y++) {
			int ya = y + yp; //ya = absolute position relative to the whole map
			
			for (int x = 0; x < tile.sprite.SIZE; x++) {
				int xa = x + xp;
				//stops RENDERING beyond the edges of the screen
				if (xa < -tile.sprite.SIZE || xa >= width || ya < -tile.sprite.SIZE || ya >= height) continue;
				if (xa < 0) xa = 0;
				if (ya < 0) ya = 0;
				pixels[xa + ya * width] = tile.sprite.pixels[x + y * tile.sprite.SIZE]; //image of tile does not use offsets
			}
		}
	}
		
	//Render any MOB
	public void renderMob(int xp, int yp, Sprite sprite) { //renders player
		xp -= xOffset;
		yp -= yOffset;
		for (int y = 0; y < sprite.SIZE; y++) {
			int ya = y + yp;
			for (int x = 0; x < sprite.SIZE; x++) {
				int xa = x + xp;
				if (xa < -sprite.SIZE || xa >= width || ya < 0 || ya >= height) break;
				if (xa < 0) xa = 0;
				int col = sprite.pixels[x + y * sprite.SIZE];
				if (col!= 0xffFF00DC) pixels[xa + ya * width] = col;
			}
		}
	}
	
	//OFFSETS
	public void setOffsets(int xOffset, int yOffset) {
		this.xOffset = xOffset;
		this.yOffset = yOffset;
	}
	
}
