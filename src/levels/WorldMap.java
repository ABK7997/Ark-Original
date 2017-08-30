package levels;

import graphics.LevelSprites;
import graphics.Sprite;
import input.Player;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import animations.world.*;
import main.Game;
import main.SaveState;
import main.Story;
import tiles.MapTile;
import tiles.Solid;
import tiles.UnSolid;

public class WorldMap extends Level {

	public WorldMap() {
		levelName = "World_Map";
		
		switch (Story.mainStory) {
		default: path = "WorldMap.png"; break;
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
		
		switch(Story.mainStory) {
		default: load0();
		}
	}
	
	private void load0() {
		cutscenes.add(new WorldMap_0());
	}
	
	public void changeTiles() {
		Void = new UnSolid(LevelSprites.dirt);
		
		Floor0 = new UnSolid(LevelSprites.swampGrass);
		Floor1 = new UnSolid(LevelSprites.path);
		Floor2 = new UnSolid(LevelSprites.barren);
		Floor3 = new UnSolid(LevelSprites.rocky);
		Floor4 = new UnSolid(LevelSprites.mountainPath);
		Floor5 = new UnSolid(LevelSprites.desertCaveFloor);
		Floor6 = new UnSolid(LevelSprites.scarredShore2);
		
		Wall0 = new Solid(LevelSprites.mountains);
		Wall1 = new Solid(LevelSprites.trenches);
		Wall2 = new Solid(LevelSprites.trees);
		Wall3 = new Solid(LevelSprites.water);
		Wall4 = new Solid(LevelSprites.darkWater);
		Wall5 = new Solid(LevelSprites.barrenMountains);
		Wall6 = new Solid(LevelSprites.stoneBrickWall);
		
		Map0 = new MapTile(LevelSprites.orzeik);
		Map1 = new MapTile(LevelSprites.trench);
		Map2 = new MapTile(LevelSprites.swamp);
		Map3 = new MapTile(LevelSprites.mortisia);
		Map4 = new MapTile(LevelSprites.crystalCave);
		Map5 = new MapTile(LevelSprites.dropZone);
		Map6 = new MapTile(LevelSprites.machina);
		Map7 = new MapTile(LevelSprites.crystalMine);
		Map8 = new MapTile(LevelSprites.jurgo);
		Map9 = new MapTile(LevelSprites.wreckedBridge);
		Map10 = new MapTile(LevelSprites.ybrid);
		Map11 = new MapTile(LevelSprites.gyad);
		Map12 = new MapTile(LevelSprites.scarredShore);
		Map13 = new MapTile(LevelSprites.radioTower);
		Map14 = new MapTile(LevelSprites.borderlands);
		Map15 = new MapTile(LevelSprites.zancrete);
		Map16 = new MapTile(LevelSprites.moat);
		Map17 = new MapTile(LevelSprites.bashada);
		Map23 = new MapTile(LevelSprites.rocky);
	}
	
	public static void loadPlayer(String levelName) {
		if ((Player.y / 32) < Level.getHeight()/2) {
			Player.x = Level.mapCoordinatesS[0] << 5;
			Player.y = Level.mapCoordinatesS[1] << 5;
		}
		
		else if ((Player.y / 32) > Level.getHeight()/2) {
			Player.x = Level.mapCoordinatesN[0] << 5;
			Player.y = Level.mapCoordinatesN[1] << 5;
		}
		
		Game.levelName = "World_Map";
		SaveState.music.changeMusic();
		SaveState.loadLevel();
	}
	
	public static void changeArea(Sprite sprite, int y) {
		if (sprite == LevelSprites.orzeik) Game.levelName = "Orzeik";
		else if (sprite == LevelSprites.trench) Game.levelName = "Trench";
		else if (sprite == LevelSprites.swamp) Game.levelName = "Swamp";
		else if (sprite == LevelSprites.mortisia) Game.levelName = "Mortisia";
		else if (sprite == LevelSprites.crystalCave) Game.levelName = "Crystal Cave";
		
		int dir = 0;
		if ((Player.y >> 5) < y) dir = 0;
		else dir = 2;
		
		SaveState.changeArea(dir);
	}
	
}
