package levels.south;

import graphics.LevelSprites;
import items.rare.SuperPotionTreasure;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import animations.mortisia.Mortisia_0;
import levels.Level;
import main.Story;
import tiles.MapTile;
import tiles.Solid;
import tiles.UnSolid;
import tiles.WarpTile;
import entity.mobs.npcs.common.Mortisian;

public class Mortisia extends Level {
	
	public Mortisia() {
		levelName = "Mortisia";
		
		switch (Story.mainStory) {
		default: path = "Mortisia.png"; break;
		}
		
		loadLevel(path);
		
		startNorth = new int[] {54, 25};
		startEast = new int[] {54, 25};
		startSouth = new int[] {6, 51};
		startWest = new int[] {6, 51};
		
		mapCoordinatesS = new int[] {78, 86};
		mapCoordinatesE = new int[] {77, 87};
		mapCoordinatesN = new int[] {77, 89};
		mapCoordinatesW = new int[] {77, 89};
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
		
		t = new SuperPotionTreasure(45, 24);
		
		switch(Story.mortisia) {
		default: load0(); break;
		}
	}
	
	//Assets
	private void load0() {
		if (Story.mortisia == 0) cutscenes.add(new Mortisia_0());
		
		add(new Mortisian(45, 23, Mortisian.mortisia1)); //Elixir
		add(new Mortisian(44, 24, Mortisian.mortisia2)); //Elixir
		add(new Mortisian(53, 27, Mortisian.mortisia3)); //Eastern Gate
		add(new Mortisian(42, 40, Mortisian.mortisia4)); //Machine Study
		add(new Mortisian(29, 24, Mortisian.mortisia5)); //Castle
		add(new Mortisian(28, 45, Mortisian.mortisia6)); //South Gate
		add(new Mortisian(27, 43, Mortisian.silent)); //South Gate, silent
		add(new Mortisian(33, 43, Mortisian.silent)); //South Gate, silent
	}
	
	public void changeTiles() {
		Void = new UnSolid(LevelSprites.swampGrass);
		
		Wall0 = new Solid(LevelSprites.stoneBrickWall);
		Wall1 = new Solid(LevelSprites.stoneWall);
		Wall2 = new Solid(LevelSprites.treeTrunk);
		Wall3 = new Solid(LevelSprites.jungleStone);
		
		Floor0 = new UnSolid(LevelSprites.swampGrass);
		Floor1 = new UnSolid(LevelSprites.dirt);
		Floor2 = new UnSolid(LevelSprites.stoneBrickWall);
		
		Roof0 = new Solid(LevelSprites.shingles);
		
		Portal0 = new WarpTile(LevelSprites.stoneOpening);
		Portal1 = new WarpTile(LevelSprites.largeWoodenDoor);
		
		Stairs0 = new UnSolid(LevelSprites.concreteStairs_2);
		
		Misc0 = new Solid(LevelSprites.deadTree);
		Misc1 = new Solid(LevelSprites.swampTree);
		Misc2 = new Solid(LevelSprites.caveOpening);
		
		Map0 = new MapTile(LevelSprites.dirt);
	}
	
}
