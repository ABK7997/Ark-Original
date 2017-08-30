package levels.central;

import graphics.LevelSprites;
import items.rare.PotionTreasure;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import levels.Level;
import main.Story;
import tiles.RestTile;
import tiles.Solid;
import tiles.UnSolid;
import tiles.WarpTile;
import animations.orzeik.Orzeik_0;
import animations.orzeik.Orzeik_1;
import animations.orzeik.Orzeik_2;
import animations.orzeik.Orzeik_3;
import animations.orzeik.Orzeik_4;
import animations.orzeik.Orzeik_5;
import animations.orzeik.Orzeik_6;
import entity.mobs.enemies.cyborgs.Cyborg_Guard;
import entity.mobs.enemies.machines.Drone;
import entity.mobs.enemies.organics.Rat;
import entity.mobs.npcs.ShopKeeper;
import entity.mobs.npcs.common.Cyborg;
import entity.mobs.npcs.common.Work_Drone;
import entity.mobs.npcs.common.Workman;
import entity.mobs.npcs.friends.Orzy_NPC;
import entity.mobs.npcs.villains.Warden;

public class Orzeik_Sub extends Level {
	
	public Orzeik_Sub() {
		levelName = "Orzeik_Sub";
		
		switch (Story.mainStory) {
		case 1: path = "Orzeik_Sub2.png"; break;
		default: path = "Orzeik_Sub.png"; break;
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
	
	public void levelSpecific() {
		if (Story.orzeik < 4) {
			for (int i = 0; i < enemies.size(); i++) {
				if (enemies.get(i).getHP() > 0) return;
				cutscenes.get(2).setPre();
			}
		}
	}
	
	public void loadAssets() {
		clearAssets();
		switch (Story.mainStory) {
		case 0: load0(); break;
		case 1: load1(); break;
		case 2: load2(); break;
		default: load2(); break;
		}
		
		//ShopKeeper
		sk = new ShopKeeper(65, 60);
		t = new PotionTreasure(64, 62);
	}
	
	public void load0() {
		//Cutscenes
		cutscenes.add(new Orzeik_0());
		cutscenes.add(new Orzeik_1());
		cutscenes.add(new Orzeik_2());
		cutscenes.add(new Orzeik_3());
				
		add(new Workman(38, 64, Workman.workman5)); //House
		add(new Workman(28, 71, Workman.workman6)); //House
		add(new Workman(67, 35, Workman.workman7)); //Clock Tower
		add(new Workman(51, 28, 28, Workman.workman1, 'y')); //Main Factory
		add(new Workman(48, 28, 28, Workman.workman1, 'y')); //Main Factory
		add(new Workman(45, 28, 28, Workman.workman1, 'y')); //Main Factory
		add(new Workman(42, 28, 28, Workman.workman1, 'y')); //Main Factory
		add(new Workman(67, 21, 21, Workman.workman1, 'y')); //Secondary Factory top right
		add(new Workman(64, 21, 21, Workman.workman1, 'y')); //Secondary Factory top right
				
		add(new Work_Drone(40, 52, Work_Drone.drone2)); //Machine Storage top right corner; expository
		add(new Work_Drone(35, 56, Work_Drone.drone3)); //Machine Storage entrance; hostile
		add(new Work_Drone(45, 25, 25, Work_Drone.drone4, 'y')); //Main Factory; tired
		add(new Work_Drone(48, 25, 25, Work_Drone.drone5, 'y')); //Main Factory; frustrated
		add(new Work_Drone(51, 25, 25, Work_Drone.drone6, 'y')); //Main Factory;
		add(new Work_Drone(49, 14, 16, Work_Drone.drone7, 'x')); //Main Factory Upstairs;
		add(new Work_Drone(49, 16, 18, Work_Drone.drone8, 'x')); //Main Factory Upstairs;
		add(new Work_Drone(31, 19, 19, Work_Drone.drone9, 'y')); //Left Top Secondary Factory;
		add(new Work_Drone(27, 38, 32, Work_Drone.drone1, 'x')); //Main Factory Upstairs;
		add(new Work_Drone(31, 38, 38, Work_Drone.drone1, 'y')); //Left Top Secondary Factory;
				
		add(new Cyborg(47, 32, Cyborg.silent));
		add(new Cyborg(51, 32, Cyborg.silent));
		
		//Important NPCs
		add(new Warden(48, 6, Warden.orzeik1)); //In his office
		add(new Orzy_NPC(29, 54, Orzy_NPC.orzeik1));
				
		//Enemies
		add(new Rat(48, 41, 1, 2));
		add(new Rat(48, 41, 1, 3));
		add(new Rat(48, 41, 1, 2));
	}
	
	public void load1() {
		//Cutscenes
		if (Story.orzeik < 6) {
			cutscenes.add(new Orzeik_4());
			cutscenes.add(new Orzeik_5());
		}
		
		//NPCS
		add(new Work_Drone(55, 24, Work_Drone.drone10));
		
		//Enemies
		add(new Cyborg_Guard(54, 57, 1, 1));
		add(new Cyborg_Guard(50, 57, 1, 1));
		
		add(new Drone(46, 38, 1, 1, 1));
		add(new Drone(41, 30, 1, 1, 1));
		add(new Drone(54, 42, 1, 1, 2));
		add(new Drone(51, 31, 1, 1, 3));
	}
	
	public void load2() {
		if (Story.orzeik < 8) cutscenes.add(new Orzeik_6());
		
		//NPCs
		add(new Workman(46, 38, Workman.workman5B)); //House
		add(new Workman(48, 46, Workman.workman6B)); //House
		add(new Workman(67, 35, Workman.workman7B)); //Clock Tower
		add(new Workman(51, 28, 28, Workman.workman1, 'y')); //Main Factory
		add(new Workman(48, 28, 28, Workman.workman1, 'y')); //Main Factory
		add(new Workman(45, 28, 28, Workman.workman1, 'y')); //Main Factory
		add(new Workman(42, 28, 28, Workman.workman1, 'y')); //Main Factory
		add(new Workman(67, 21, 21, Workman.workman1, 'y')); //Secondary Factory top right
		add(new Workman(64, 21, 21, Workman.workman1, 'y')); //Secondary Factory top right
				
		add(new Work_Drone(40, 52, Work_Drone.drone2)); //Machine Storage top right corner; expository
		add(new Work_Drone(35, 56, Work_Drone.drone3B)); //Machine Storage entrance; hostile
		add(new Work_Drone(45, 25, 25, Work_Drone.drone4B, 'y')); //Main Factory; tired
		add(new Work_Drone(48, 25, 25, Work_Drone.drone5B, 'y')); //Main Factory; frustrated
		add(new Work_Drone(51, 25, 25, Work_Drone.drone6B, 'y')); //Main Factory;
		add(new Work_Drone(49, 14, 16, Work_Drone.drone7B, 'x')); //Main Factory Upstairs;
		add(new Work_Drone(49, 16, 18, Work_Drone.drone8B, 'x')); //Main Factory Upstairs;
		add(new Work_Drone(31, 19, 19, Work_Drone.drone9, 'y')); //Left Top Secondary Factory;
		add(new Work_Drone(27, 38, 32, Work_Drone.drone1, 'x')); //Main Factory Upstairs;
		add(new Work_Drone(31, 38, 38, Work_Drone.drone1, 'y')); //Left Top Secondary Factory;
		
		add(new Warden(48, 6, Warden.orzeik1)); //In his office
	}
	
	public void changeTiles() {
		Void = new UnSolid(LevelSprites.black);
		
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
		
		Portal0 = new WarpTile(LevelSprites.black);
		
		Window0 = new Solid(LevelSprites.houseWindow);
		Window1 = new Solid(LevelSprites.factoryWindow);
		
		Roof0 = new Solid(LevelSprites.shingles);
		Roof1 = new Solid(LevelSprites.concreteRoof);
		
		Decoration0 = new Solid(LevelSprites.roofPipe);
		
		Misc0 = new Solid(LevelSprites.crate);
		Misc1 = new Solid(LevelSprites.conveyorBelt_1);
		Misc2 = new Solid(LevelSprites.conveyorBelt_2);
		Misc3 = new Solid(LevelSprites.machineStorage);
		Misc4 = new Solid(LevelSprites.metalDoor);
		
		Rest0 = new RestTile(LevelSprites.bed);
		
		Stairs0 = new UnSolid(LevelSprites.concreteStairs_2);
		Stairs1 = new UnSolid(LevelSprites.concreteStairs_3);
		Stairs2 = new UnSolid(LevelSprites.ladder);
	}
	
}
