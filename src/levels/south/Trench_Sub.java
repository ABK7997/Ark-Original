package levels.south;

import graphics.LevelSprites;
import items.rare.PolishTreasure;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import levels.Level;
import main.Story;
import tiles.RestTile;
import tiles.Solid;
import tiles.UnSolid;
import tiles.WarpTile;
import entity.mobs.enemies.bosses.Cave_Troll;
import entity.mobs.enemies.organics.Rat;
import entity.mobs.npcs.ShopKeeper;

public class Trench_Sub extends Level {

	public Trench_Sub() {
		levelName = "Trench_Sub";
		
		switch (Story.mainStory) {
		default: path = "Trench_Sub.png"; break;
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
		
		t = new PolishTreasure(55, 20);
		sk = new ShopKeeper(10, 39);
		
		switch(Story.trench) {
		case 1: load1(); break;
		default: load2(); break;
		}
	}
	
	//Assets
	private void load1() {
		//Cave 1
		add(new Rat(57, 28, 2, 3));
		add(new Rat(57, 28, 2, 2));
		add(new Rat(57, 28, 2, 2));
		add(new Rat(57, 28, 2, 2));
		
		add(new Rat(71, 33, 2, 4));
		add(new Rat(71, 33, 2, 3));
		
		//Cave 2
		add(new Rat(26, 55, 2, 3));
		
		//Boss
		add(new Cave_Troll(55, 67, 1, 1));
	}
	
	private void load2() {
		//Cave 1
		add(new Rat(57, 28, 2, 3));
		add(new Rat(57, 28, 2, 2));
		add(new Rat(57, 28, 2, 2));
		add(new Rat(57, 28, 2, 2));
				
		add(new Rat(71, 33, 2, 4));
		add(new Rat(71, 33, 2, 3));
				
		//Cave 2
		add(new Rat(26, 55, 2, 3));
	}
	
	public void changeTiles() {
		Void = new Solid(LevelSprites.desertCaveWall);
		
		Wall0 = new Solid(LevelSprites.desertCaveWall);
		
		Floor0 = new UnSolid(LevelSprites.desertCaveFloor);
	
		Portal0 = new WarpTile(LevelSprites.desertCaveExit);
		
		Rest0 = new RestTile(LevelSprites.desertHealCircle);
	}
	
}
