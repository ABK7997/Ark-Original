package levels.central;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import levels.Level;
import main.Story;
import tiles.MapTile;
import tiles.Solid;
import tiles.UnSolid;
import tiles.WarpTile;
import animations.orzeik.Orzeik_7;
import entity.mobs.npcs.common.Work_Drone;
import entity.mobs.npcs.common.Workman;
import graphics.LevelSprites;

public class Orzeik extends Level {

	public Orzeik() {
		levelName = "Orzeik";
		path = "Orzeik.png";
		
		switch (Story.mainStory) {
		case 0: path = "Orzeik.png"; break;
		case 1: path = "Orzeik.png"; break;
		default: path = "Orzeik_2.png"; break;
		}
		
		loadLevel(path);
		
		mapCoordinatesN = new int[] {50, 51};
		mapCoordinatesE = new int[] {50, 51};
		mapCoordinatesS = new int[] {50, 51};
		mapCoordinatesW = new int[] {50, 51};
		
		//Starting coordinates
		startNorth = new int[] {49, 75};
		startEast = new int[] {49, 75};
		startSouth = new int[] {49, 75};
		startWest = new int[] {49, 75};
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
		
		switch(Story.mainStory) {
		case 0: load0(); break;
		case 2: load2(); break;
		default: load2(); break;
		}
	}
	
	private void load0() {
		add(new Workman(40, 39, 47, Workman.workman1, 'x')); //Pacing outside main factory
		add(new Workman(33, 27, Workman.workman2)); //Hesitant outside secondary factory
		add(new Workman(60, 54, Workman.workman3)); //Standing outside clock tower
		add(new Workman(45, 49, 61, Workman.workman4, 'y')); //Pacing outside machine storage
		add(new Workman(63, 66, Workman.workman8)); //Outside store
		
		add(new Work_Drone(36, 60, Work_Drone.drone1)); //Machine storage entrance
		
		//add(new Rat(57, 76, 1, 1));
	}
	
	private void load2() {
		//Cutscenes
		if (Story.orzeik == 7) cutscenes.add(new Orzeik_7());
		
		//NPCs
		add(new Workman(40, 39, 47, Workman.workman1B, 'x')); //Pacing outside main factory
		add(new Workman(33, 27, Workman.workman2B)); //Hesitant outside secondary factory
		add(new Workman(60, 54, Workman.workman3B)); //Standing outside clock tower
		add(new Workman(45, 49, 61, Workman.workman4B, 'y')); //Pacing outside machine storage
		add(new Workman(63, 66, Workman.workman8B)); //Outside store
		
		add(new Work_Drone(36, 60, Work_Drone.drone1)); //Machine storage entrance
	}
	
	public void changeTiles() {
		Void = new UnSolid(LevelSprites.dirt);
		
		Floor0 = new UnSolid(LevelSprites.dirt);
		Floor1 = new UnSolid(LevelSprites.rocks);
		Floor2 = new UnSolid(LevelSprites.concreteFloor);
		Floor3 = new UnSolid(LevelSprites.basementFloor);
		Floor4 = new UnSolid(LevelSprites.junk);
		Floor5 = new UnSolid(LevelSprites.path);
		
		Wall0 = new Solid(LevelSprites.woodenWall);;
		Wall1 = new Solid(LevelSprites.concreteWall);
		Wall2 = new Solid(LevelSprites.stoneWall);
		Wall3 = new Solid(LevelSprites.stoneBrickWall);
		
		Portal0 = new WarpTile(LevelSprites.woodenDoor);
		Portal1 = new WarpTile(LevelSprites.metalDoor);
		
		Window0 = new Solid(LevelSprites.houseWindow);
		Window1 = new Solid(LevelSprites.factoryWindow);
		
		Roof0 = new Solid(LevelSprites.shingles);
		Roof1 = new Solid(LevelSprites.concreteRoof);
		
		Decoration0 = new Solid(LevelSprites.roofPipe);
		
		Misc5 = new Solid(LevelSprites.gate);
		
		Map0 = new MapTile(LevelSprites.path);
	}
	
}
