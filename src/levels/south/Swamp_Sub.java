package levels.south;

import graphics.LevelSprites;
import items.rare.SteroidTreasure;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import levels.Level;
import main.Story;
import tiles.MapTile;
import tiles.RestTile;
import tiles.Solid;
import tiles.UnSolid;
import tiles.WarpTile;
import animations.swamp.Swamp_2;
import entity.mobs.enemies.bosses.Crystal_Golem;
import entity.mobs.enemies.mutants.Hermit;
import entity.mobs.enemies.organics.Frog;
import entity.mobs.enemies.organics.Ogre;
import entity.mobs.npcs.common.Old_Drone;

public class Swamp_Sub extends Level {

	public Swamp_Sub() {
		levelName = "Swamp_Sub";
		
		switch (Story.mainStory) {
		default: path = "Swamp_Sub.png"; break;
		}
		
		loadLevel(path);
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
		
		t = new SteroidTreasure(101, 13);
		
		switch(Story.trench) {
		case 0: load0(); break;
		default: load0(); break;
		}
	}
	
	//Assets
	private void load0() {
		cutscenes.add(new Swamp_2());
		
		//Rest Area
		add(new Old_Drone(37, 141, Old_Drone.drone4));
		
		//Northeast Cave
		add(new Ogre(113, 23, 1, 5));
		
		add(new Frog(119, 10, 1, 4));
		add(new Frog(120, 11, 1, 4));
		
		add(new Hermit(128, 22, 1, 4));
		add(new Hermit(128, 22, 1, 4));
		
		if (Story.swamp < 3) add(new Crystal_Golem(180, 174, 1, 1));
	}
	
	public void changeTiles() {
		Void = new Solid(LevelSprites.caveWall);
		
		Wall0 = new Solid(LevelSprites.caveWall);
		Wall3 = new Solid(LevelSprites.darkWater);
		
		Floor0 = new UnSolid(LevelSprites.caveFloor);
		
		Portal0 = new WarpTile(LevelSprites.caveExit);
		Portal1 = new WarpTile(LevelSprites.caveOpening);
		
		Rest0 = new RestTile(LevelSprites.healCircle);
		
		Map0 = new MapTile(LevelSprites.caveFloor);
	}
	
}
