package levels;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import entity.mobs.npcs.ShopKeeper;
import entity.mobs.npcs.friends.Dex_NPC;
import entity.mobs.npcs.friends.Orzy_NPC;
import main.Story;
import tiles.MapTile;
import tiles.RestTile;
import tiles.Solid;
import tiles.UnSolid;
import graphics.LevelSprites;

public class PartyCamp extends Level {

	public PartyCamp() {
		levelName = "PartyCamp";
		
		switch (Story.mainStory) {
		default: path = "PartyCamp4.png"; break;
		}
		
		loadLevel(path);
		
		mapCoordinatesN = new int[] {50, 51};
		mapCoordinatesS = new int[] {50, 51};
	}
	
	protected void loadLevel(String path) {	
		try {
			BufferedImage image = ImageIO.read(getClass().getResource(path));
			int w = width = image.getWidth();
			int h = height = image.getHeight();
			tiles = new int[w * h];
			image.getRGB(0, 0, w, h, tiles, 0, w);
		} catch (IOException e) {
			e.printStackTrace();
		}
		changeTiles();
	}
	
	public void loadAssets() {
		clearAssets();
		
		sk = new ShopKeeper(24, 20);
		
		switch(Story.mainStory) {
		default: load0();
		}
	}
	
	private void load0() {
		add(new Orzy_NPC(16, 3, Orzy_NPC.camp1));
		add(new Dex_NPC(24, 7, Dex_NPC.camp1));
	}
	
	public void changeTiles() {
		Void = new UnSolid(LevelSprites.swampGrass);
		
		Floor0 = new UnSolid(LevelSprites.dirt);
		
		Wall0 = new Solid(LevelSprites.water);
		
		Rest0 = new RestTile(LevelSprites.healCircle);
		
		Map0 = new MapTile(LevelSprites.swampGrass);
		
		Misc0 = new Solid(LevelSprites.campFire);
		Misc1 = new Solid(LevelSprites.tent);
		Misc2 = new Solid(LevelSprites.jungleRock);
	}
	
}
