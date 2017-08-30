package levels.south;

import graphics.LevelSprites;
import items.rare.ElixirTreasure;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import levels.Level;
import main.Story;
import tiles.MapTile;
import tiles.RestTile;
import tiles.Solid;
import tiles.UnSolid;
import animations.mortisia.CrystalCave_0;
import entity.mobs.enemies.mutants.Crystal_Mage;
import entity.mobs.enemies.mutants.Crystal_Mother;
import entity.mobs.enemies.mutants.Crystal_Skeleton;
import entity.mobs.enemies.mutants.Crystal_Spirit;

public class Crystal_Cave extends Level {
	
	public Crystal_Cave() {
		levelName = "Crystal Cave";
		
		switch (Story.mainStory) {
		default: path = "Crystal_Cave.png"; break;
		}
		
		loadLevel(path);
		
		startNorth = new int[] {7, 30};
		startEast = new int[] {7, 30};
		startSouth = new int[] {7, 30};
		startWest = new int[] {7, 30};
		
		mapCoordinatesN = new int[] {94, 85};
		mapCoordinatesE = new int[] {94, 85};
		mapCoordinatesS = new int[] {94, 85};
		mapCoordinatesW = new int[] {94, 85};
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
		
		t = new ElixirTreasure(5, 21);
		
		switch(Story.crystalCave) {
		default: load0(); break;
		}
	}
	
	//Assets
	private void load0() {
		cutscenes.add(new CrystalCave_0());
		
		//Crystal Mothers
		add(new Crystal_Mother(13, 13, 1, 1));
		add(new Crystal_Mother(64, 34, 1, 1));
		add(new Crystal_Mother(49, 16, 1, 1));
		
		//Treasure and Entrance Area
		add(new Crystal_Spirit(24, 3, 1, 1));
		add(new Crystal_Skeleton(18, 34, 1, 1));
		add(new Crystal_Mage(12, 39, 1, 1));
		
		//Wide Path 1
		add(new Crystal_Skeleton(12, 50, 1, 2));
		add(new Crystal_Skeleton(12, 54, 1, 2));
		add(new Crystal_Skeleton(12, 60, 1, 3));
		
		add(new Crystal_Mage(13, 66, 1, 2));
		add(new Crystal_Mage(13, 71, 1, 2));
		add(new Crystal_Mage(13, 76, 1, 3));
		
		add(new Crystal_Spirit(21, 84, 1, 3));
		add(new Crystal_Spirit(24, 84, 1, 2));
		add(new Crystal_Spirit(30, 84, 1, 3));
		
		add(new Crystal_Skeleton(14, 88, 1, 5));
		
		add(new Crystal_Spirit(49, 68, 1, 3));
		add(new Crystal_Spirit(54, 42, 1, 3));
		
		//Narrow Path 1
		add(new Crystal_Skeleton(25, 51, 1, 5));
		add(new Crystal_Mage(33, 62, 1, 5));
		add(new Crystal_Spirit(37, 27, 1, 4));
		
		//Left Path 2
		add(new Crystal_Mage(80, 54, 1, 3));
		add(new Crystal_Mage(83, 46, 1, 3));
		add(new Crystal_Mage(78, 35, 1, 4));
		add(new Crystal_Mage(82, 33, 1, 4));
		
		//Right Path 2
		add(new Crystal_Skeleton(78, 69, 1, 3));
		add(new Crystal_Skeleton(88, 68, 1, 3));
		add(new Crystal_Skeleton(95, 58, 1, 4));
		add(new Crystal_Skeleton(93, 40, 1, 4));
		
		//Top Path 3
		add(new Crystal_Mage(62, 4, 1, 5));
		
		//Bottom Path 3
		add(new Crystal_Skeleton(63, 18, 1, 5));
	}
	
	public void changeTiles() {
		Void = new UnSolid(LevelSprites.crystalFloor);
		
		Floor0 = new UnSolid(LevelSprites.caveFloor);
		
		Wall0 = new Solid(LevelSprites.caveWall);
		Wall1 = new Solid(LevelSprites.crystalWall);
		
		Misc0 = new Solid(LevelSprites.crystal);
		Misc1 = new Solid(LevelSprites.crystalStalagmite);
		
		Map0 = new MapTile(LevelSprites.crystalFloor);
		
		Rest0 = new RestTile(LevelSprites.healCircle);
	}
	
}
