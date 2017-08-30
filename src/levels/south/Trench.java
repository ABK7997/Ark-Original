package levels.south;

import graphics.LevelSprites;
import items.rare.AntidoteTreasure;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import levels.Level;
import main.Story;
import tiles.MapTile;
import tiles.Solid;
import tiles.UnSolid;
import tiles.WarpTile;
import animations.trench.Trench_0;
import entity.mobs.enemies.machines.Expired_Drone;
import entity.mobs.enemies.organics.Ogre;
import entity.mobs.enemies.organics.Spider;

public class Trench extends Level {

	public Trench() {
		levelName = "Trench";
		
		switch (Story.mainStory) {
		default: path = "Trench.png"; break;
		}
		
		//Starting Coordinates
		startNorth = new int[] {47, 12};
		startEast = new int[] {47, 12};
		startSouth = new int[] {58, 128};
		startWest = new int[] {47, 12};
		
		mapCoordinatesN = new int[] {53, 60};
		mapCoordinatesE = new int[] {53, 60};
		mapCoordinatesS = new int[] {53, 58};
		mapCoordinatesW = new int[] {53, 60};
		
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
		
		t = new AntidoteTreasure(36, 48);
		
		switch(Story.trench) {
		case 2: load2(); break;
		default: load1(); break;
		}
	}
	
	//Assets
	private void load1() {
		cutscenes.add(new Trench_0());
		
		add(new Ogre(34, 44, 1, 2));
		add(new Ogre(37, 60, 1, 3));
		
		//Pass 1
		add(new Expired_Drone(44, 25, 1, 1, 2));
		add(new Expired_Drone(48, 27, 1, 1, 1));
		add(new Expired_Drone(19, 23, 1, 1, 3));
		
		add(new Spider(20, 24, 1, 3));
		add(new Spider(20, 24, 1, 2));
		add(new Spider(27, 24, 1, 2));
		
		//Pass 2
		add(new Expired_Drone(52, 48, 1, 1, 2));
		add(new Expired_Drone(52, 48, 1, 1, 3));
		add(new Expired_Drone(48, 64, 1, 1, 3));
		
		add(new Spider(63, 40, 1, 3));
		add(new Spider(69, 59, 1, 4));
		
		//Pass 3
		add(new Spider(32, 80, 1, 2));
		add(new Spider(32, 80, 1, 2));
	}
	
	private void load2() {
		//Pass 1
		add(new Expired_Drone(44, 25, 1, 1, 2));
		add(new Expired_Drone(48, 27, 1, 1, 1));
		add(new Expired_Drone(19, 23, 1, 1, 3));
		add(new Expired_Drone(65, 25, 1, 1, 2));
		add(new Expired_Drone(65, 25, 1, 1, 2));
				
		add(new Spider(20, 24, 1, 3));
		add(new Spider(20, 24, 1, 2));
		add(new Spider(27, 24, 1, 3));
		add(new Spider(27, 24, 1, 2));
				
		//Pass 2
		add(new Expired_Drone(52, 48, 1, 1, 2));
		add(new Expired_Drone(52, 48, 1, 1, 3));
		add(new Expired_Drone(48, 64, 1, 1, 3));
		add(new Expired_Drone(48, 64, 1, 1, 2));
				
		add(new Spider(63, 40, 1, 3));
		add(new Spider(69, 59, 1, 2));
		add(new Spider(69, 59, 1, 4));
				
		//Pass 3
		add(new Spider(32, 80, 1, 2));
		add(new Spider(32, 80, 1, 2));
				
		//Exit
		add(new Spider(35, 119, 1, 3));
		add(new Spider(35, 119, 1, 2));
		add(new Spider(35, 119, 1, 4));
		add(new Spider(35, 119, 1, 3));
				
		add(new Expired_Drone(82, 119, 1, 1, 3));
		add(new Expired_Drone(82, 119, 1, 1, 2));
		add(new Expired_Drone(82, 119, 1, 1, 4));
		add(new Expired_Drone(82, 119, 1, 1, 2));
	}
	
	public void changeTiles() {
		Void = new Solid(LevelSprites.mountainWall);
		
		Wall0 = new Solid(LevelSprites.mountainWall);
		
		Floor0 = new UnSolid(LevelSprites.mountainPath);
		
		Portal0 = new WarpTile(LevelSprites.desertCaveOpening);
		
		Misc0 = new Solid(LevelSprites.mountainRock);
		
		Map0 = new MapTile(LevelSprites.mountainPath);
	}
	
}
