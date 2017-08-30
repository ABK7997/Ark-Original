package levels.south;

import graphics.LevelSprites;
import items.rare.HerbTreasure;

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
import animations.swamp.Swamp_0;
import animations.swamp.Swamp_1;
import entity.mobs.enemies.machines.Lost_Drone;
import entity.mobs.enemies.machines.Old_Harvester;
import entity.mobs.enemies.mutants.Ent;
import entity.mobs.enemies.mutants.Hermit;
import entity.mobs.enemies.organics.Frog;
import entity.mobs.npcs.ShopKeeper;
import entity.mobs.npcs.common.Old_Drone;

public class Swamp extends Level {

	private Old_Harvester oh = new Old_Harvester(-12, -12, 1, 1);
	
	public Swamp() {
		levelName = "Swamp";
		
		switch (Story.mainStory) {
		default: path = "Swamp.png"; break;
		}
		
		mapCoordinatesS = new int[] {56, 79}; //NORTH
		mapCoordinatesE = new int[] {63, 87};
		mapCoordinatesN = new int[] {63, 87}; //SOUTH
		mapCoordinatesW = new int[] {56, 79};
		
		//Starting Coordinates
		startNorth = new int[] {4, 8}; //Actually starts north
		startEast = new int[] {153, 175};
		startSouth = new int[] {153, 175}; //Actually starts south
		startWest = new int[] {4, 8};
		
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
		
		t = new HerbTreasure(95, 87);
		sk = new ShopKeeper(98, 188);
		
		switch(Story.trench) {
		case 0: load0(); break;
		default: load0(); break;
		}
	}
	
	//Assets
	private void load0() {
		cutscenes.add(new Swamp_0());
		cutscenes.add(new Swamp_1());
		
		//Drone NPCs
		add(new Old_Drone(72, 21, Old_Drone.drone1));
		add(new Old_Drone(79, 95, Old_Drone.drone2));
		add(new Old_Drone(37, 141, Old_Drone.drone3));
		
		//Hermits
		add(new Hermit(40, 35, 1, 2)); //Northwest entrance
		add(new Hermit(41, 35, 1, 2));
		add(new Hermit(38, 38, 1, 2));
		
		add(new Hermit(146, 43, 1, 3)); //Eastern edge
		add(new Hermit(144, 42, 1, 3));
		add(new Hermit(147, 46, 1, 4));
		add(new Hermit(143, 127, 1, 4)); //Lower Eastern Edge
		add(new Hermit(146, 127, 1, 4));
		add(new Hermit(147, 129, 1, 4));
		
		add(new Hermit(46, 94, 1, 3)); //Western Edge
		add(new Hermit(49, 94, 1, 3));
		add(new Hermit(46, 97, 1, 3));
		
		add(new Hermit(39, 156, 1, 4)); //West Swamp
		add(new Hermit(39, 156, 1, 3));
		
		add(new Hermit(98, 150, 1, 4)); //Center Swamp
		add(new Hermit(98, 145, 1, 4));
		add(new Hermit(95, 145, 1, 4));
		add(new Hermit(95, 140, 1, 4));
		
		add(new Hermit(146, 182, 1, 5)); //East Swamp
		add(new Hermit(146, 182, 1, 4));
		
		add(new Hermit(71, 70, 1, 4)); //Center Island 1
		add(new Hermit(71, 70, 1, 4));
		
		//Frogs
		add(new Frog(46, 41, 1, 2)); //Northwest entrance
		add(new Frog(46, 41, 1, 2));
		add(new Frog(46, 41, 1, 2));
		
		add(new Frog(120, 22, 1, 3)); //Northeast Cave
		add(new Frog(120, 22, 1, 3));
		add(new Frog(120, 22, 1, 2));
		
		add(new Frog(52, 130, 1, 3)); //Jungle / Swamp Border
		add(new Frog(52, 130, 1, 2));
		add(new Frog(52, 130, 1, 3));
		
		add(new Frog(62, 162, 1, 2)); //Swamp
		add(new Frog(62, 162, 1, 2));
		add(new Frog(62, 162, 1, 4));
		add(new Frog(62, 162, 1, 3));
		add(new Frog(91, 177, 1, 3));
		add(new Frog(91, 177, 1, 4));
		add(new Frog(91, 177, 1, 3));
		add(new Frog(91, 177, 1, 5));
		add(new Frog(130, 183, 1, 4));
		add(new Frog(130, 183, 1, 2));
		add(new Frog(130, 183, 1, 3));
		add(new Frog(130, 183, 1, 3));
		
		add(new Frog(106, 79, 1, 3)); //Center Island 2
		add(new Frog(106, 79, 1, 3));
		add(new Frog(106, 79, 1, 3));
		
		//Lost Drones
		add(new Lost_Drone(96, 31, 1, 2)); //Northeast Cave
		add(new Lost_Drone(96, 32, 1, 2)); 
		add(new Lost_Drone(98, 31, 1, 2)); 
		
		add(new Lost_Drone(50, 54, 1, 3)); //West Edge
		add(new Lost_Drone(49, 53, 1, 4)); 
		add(new Lost_Drone(51, 54, 1, 4)); 
		add(new Lost_Drone(48, 55, 1, 3)); 
		
		add(new Lost_Drone(111, 135, 1, 3)); //Jungle / Swamp Border
		add(new Lost_Drone(105, 135, 1, 3));
		add(new Lost_Drone(116, 135, 1, 3));
		
		add(new Lost_Drone(105, 110, 1, 3)); //Center Island 3 
		add(new Lost_Drone(105, 110, 1, 3)); 
		
		add(new Lost_Drone(62, 189, 1, 4)); //West Swamp
		add(new Lost_Drone(62, 189, 1, 5)); 
		add(new Lost_Drone(62, 174, 1, 5));
		add(new Lost_Drone(62, 174, 1, 5));
		
		add(new Lost_Drone(145, 187, 1, 5)); //East Swamp
		add(new Lost_Drone(145, 190, 1, 5));
		add(new Lost_Drone(145, 192, 1, 5));
		
		//Old Harvesters
		add(new Old_Harvester(93, 87, 1, 1)); //Northern Jungle
		add(new Old_Harvester(122, 96, 1, 1));
		add(new Old_Harvester(154, 32, 1, 1));
		add(new Old_Harvester(158, 36, 1, 1));
		
		add(new Old_Harvester(151, 142, 1, 1)); //Southern Swamp
		add(new Old_Harvester(146, 154, 1, 1));
		add(new Old_Harvester(57, 173, 1, 1));
		add(new Old_Harvester(36, 174, 1, 1));
		
		//Ents
		add(new Ent(52, 27, 1, 1)); //Northern passage
		add(new Ent(64, 53, 1, 2)); //Center Islands - southwest entrance
		add(new Ent(119, 72, 1, 2)); //Center Islands - northeast entrance
		add(new Ent(80, 112, 1, 2)); //Swamp
		add(new Ent(152, 73, 1, 3)); //Swamp
		add(new Ent(145, 172, 1, 3)); //Swamp
		add(new Ent(160, 84, 1, 3)); //Eastern edge
	}
	
	public void changeTiles() {
		Void = new Solid(LevelSprites.jungleStone);
		
		Wall0 = new Solid(LevelSprites.jungleStone);
		Wall1 = new Solid(LevelSprites.treeTrunk);
		Wall2 = new Solid(LevelSprites.water);
		Wall3 = new Solid(LevelSprites.darkWater);
		Wall4 = new Solid(LevelSprites.marshStone);
		
		Floor0 = new UnSolid(LevelSprites.swampGrass);
		Floor1 = new UnSolid(LevelSprites.marshGround);
		Floor2 = new UnSolid(LevelSprites.grassyWater);
		Floor3 = new UnSolid(LevelSprites.marshyWater);
		Floor4 = new UnSolid(LevelSprites.lilipad);
		
		Roof0 = new Solid(LevelSprites.leaves);
		
		Portal0 = new WarpTile(LevelSprites.caveOpening);
		
		Misc0 = new Solid(LevelSprites.swampTree);
		Misc1 = new Solid(LevelSprites.deadTree);
		Misc2 = new Solid(LevelSprites.swampBush);
		Misc3 = new Solid(LevelSprites.jungleRock);
		Misc4 = new Solid(LevelSprites.marshRock);
		
		Map0 = new MapTile(LevelSprites.swampGrass);
		
		Rest0 = new RestTile(LevelSprites.healCircle);
	}
	
	public void levelSpecific() {
		for (int i = 0; i < enemies.size(); i++) {
			if (enemies.get(i).getClass() == oh.getClass()) {
				if (enemies.get(i).getHP() == 0) cutscenes.get(1).setPre();
			}
		}
	}
	
}
