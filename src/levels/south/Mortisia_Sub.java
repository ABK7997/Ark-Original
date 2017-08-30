package levels.south;

import graphics.LevelSprites;
import items.rare.BatteryTreasure;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import animations.mortisia.Mortisia_1;
import levels.Level;
import main.Story;
import tiles.MapTile;
import tiles.RestTile;
import tiles.Solid;
import tiles.UnSolid;
import tiles.WarpTile;
import entity.mobs.npcs.ShopKeeper;
import entity.mobs.npcs.common.Mortisian;
import entity.mobs.npcs.common.Work_Drone;

public class Mortisia_Sub extends Level {

	public Mortisia_Sub() {
		levelName = "Mortisia_Sub";
		
		switch (Story.mainStory) {
		default: path = "Mortisia_Sub.png"; break;
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
		
		t = new BatteryTreasure(50, 37);
		sk = new ShopKeeper(48, 19);
		
		switch(Story.mortisia) {
		default: load0(); break;
		}
	}
	
	//Assets
	private void load0() {
		cutscenes.add(new Mortisia_1());
		
		add(new Work_Drone(42, 32, Work_Drone.captured));
		
		add(new Mortisian(43, 33, Mortisian.scientist1)); //Machine Study
		add(new Mortisian(41, 33, Mortisian.scientist2)); //Machine Study
		add(new Mortisian(50, 36, Mortisian.scientist3)); //Machine Study
		add(new Mortisian(47, 33, Mortisian.scientist4)); //Machine Study
		add(new Mortisian(25, 35, Mortisian.inn1)); //Innkeeper
		add(new Mortisian(20, 33, Mortisian.inn2)); //Inn
		add(new Mortisian(32, 10, Mortisian.castle1)); //CastleGuard
		add(new Mortisian(33, 10, Mortisian.castle2)); //Castle Guard
	}
	
	public void changeTiles() {
		Void = new Solid(LevelSprites.black);
		
		Wall0 = new Solid(LevelSprites.stoneBrickWall);
		Wall1 = new Solid(LevelSprites.stoneWall);
		Wall2 = new Solid(LevelSprites.treeTrunk);
		
		Floor0 = new UnSolid(LevelSprites.swampGrass);
		Floor1 = new UnSolid(LevelSprites.dirt);
		Floor2 = new UnSolid(LevelSprites.swampGrass);
		Floor3 = new UnSolid(LevelSprites.path);
		
		Portal0 = new WarpTile(LevelSprites.black);
		Portal1 = new WarpTile(LevelSprites.largeWoodenDoor);
		
		Stairs0 = new UnSolid(LevelSprites.concreteStairs_2);
		
		Misc0 = new Solid(LevelSprites.machineStorage);
		Misc1 = new Solid(LevelSprites.largeWoodenDoor);
		
		Map0 = new MapTile(LevelSprites.dirt);
		
		Rest0 = new RestTile(LevelSprites.bed);
	}
	
}
