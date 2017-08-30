package levels;

import items.Treasure;

import java.util.ArrayList;

import states.Screen;
import tiles.Tile;
import animations.Animation;
import entity.Entity;
import entity.mobs.enemies.Enemy;
import entity.mobs.npcs.NPC;
import entity.mobs.npcs.ShopKeeper;

public class Level {

	protected static int width;
	protected static int height;
	protected int[] tilesNum;
	protected static int[] tiles;
	public String levelName;
	protected String levelMap;
	protected String path;
	protected String mapPath;
	
	public static int[] levelCoordinates = {3, 3};
	
	//Determines position on map
	public static int[] mapCoordinatesN = {0, 0};
	public static int[] mapCoordinatesE = {0, 0};
	public static int[] mapCoordinatesS= {0, 0};
	public static int[] mapCoordinatesW = {0, 0};
	
	//Player starting coordinates x and y for area transitions
	protected int[] startNorth = new int[2]; 
	protected int[] startEast = new int[2]; 
	protected int[] startSouth = new int[2]; 
	protected int[] startWest = new int[2]; 
	
	//TILES - vary by level
	public static Tile Void;
	public static Tile Floor0, Floor1, Floor2, Floor3, Floor4, Floor5, Floor6, Floor7;
	public static Tile Wall0, Wall1, Wall2, Wall3, Wall4, Wall5, Wall6, Wall7;
	public static Tile Portal0, Portal1, Portal2, Portal3;
	public static Tile Window0, Window1;
	public static Tile Roof0, Roof1;
	public static Tile Decoration0;
	public static Tile Misc0, Misc1, Misc2, Misc3, Misc4, Misc5;
	public static Tile NS0;
	public static Tile Stairs0, Stairs1, Stairs2;
	public static Tile Map0, Map1, Map2, Map3, Map4, Map5, Map6, Map7, Map8, Map9, Map10;
	public static Tile Map11, Map12, Map13, Map14, Map15, Map16, Map17, Map18, Map19, Map20;
	public static Tile Map21, Map22, Map23;
	public static Tile Rest0;
	
	//Cutscenes
	public static ArrayList<Animation> cutscenes = new ArrayList<Animation>();
	
	//Entities
	public static ArrayList<Enemy> enemies = new ArrayList<Enemy>();
	public static ArrayList<NPC> npcs = new ArrayList<NPC>();
	public static ShopKeeper sk = new ShopKeeper(-10, -10);
	public static Treasure t = new Treasure(-11, -11);
	
	public Level() {
	}
	
	public void update() {
		for (int i = 0; i < enemies.size(); i++) {
			enemies.get(i).update();
		}
		
		for (int i = 0; i < npcs.size(); i++) {
			npcs.get(i).update();
		}
		
		for (int i = 0; i < cutscenes.size(); i++) {
			if (cutscenes.get(i).isAnimating()) {
				cutscenes.get(i).update();
			}
		}
		
		levelSpecific();
		
		remove();
	}
	
	//Corner Pins - used as bounds to render one tile just beyond the edges of the screen size
	public void render(int xScroll, int yScroll, Screen screen) {
		screen.setOffsets(xScroll, yScroll);
		int x0 = xScroll / 32; //left edge of the screen >>4
		int x1 = (xScroll + screen.width) / 32; //right edge
		int y0 = yScroll / 32; //top edge
		int y1 = (yScroll + screen.height) / 32; //bottom edge
			
		for (int y = y0 - 1; y < y1 + 1; y++) {
			for (int x = x0 - 1; x < x1 + 1; x++) {
				if (x + y * 32 > 0 || x + y * 32 <= 20 * 30) {
					getTile(x, y).render(x, y, screen);
				}
			}
		}
		
		for (int i = 0; i < enemies.size(); i++) {
			enemies.get(i).render(screen);
		}
		
		for (int i = 0; i < npcs.size(); i++) {
			npcs.get(i).render(screen);
		}
		
		for (int i = 0; i < cutscenes.size(); i++) {
			cutscenes.get(i).render(screen);
		}
		
		sk.render(screen);
		t.render(screen);
	}
	
	public static void add(Entity e) {
		if (e instanceof Enemy) {
			enemies.add((Enemy) e);
		}
		if (e instanceof NPC) {
			npcs.add((NPC) e);
		}
	}
	
	public void remove() {
		for (int i = 0; i < enemies.size(); i++) {
			if (enemies.get(i).getRemoved() || enemies.get(i).getFinished()) {
				enemies.remove(enemies.get(i));
			}
		}
		for (int i = 0; i < npcs.size(); i++) {
			if (npcs.get(i).getRemoved()) {
				npcs.remove(npcs.get(i));
			}
		}
	}
	
	public Tile getTile(int x, int y) {
		if (x < 0 || y < 0 || x >= width || y >= height) return Void;
		
		switch (tiles[x + y * width]) {
		
		case 0xff000000: return Wall0;
		case 0xff000001: return Wall1;
		case 0xff000002: return Wall2;
		case 0xff000003: return Wall3;
		case 0xff000004: return Wall4;
		case 0xff000005: return Wall5;
		case 0xff000006: return Wall6;
		case 0xff000007: return Wall7;
		
		case 0xffC0C0C0: return Floor0;
		case 0xffC0C0C1: return Floor1;
		case 0xffC0C0C2: return Floor2;
		case 0xffC0C0C3: return Floor3;
		case 0xffC0C0C4: return Floor4;
		case 0xffC0C0C5: return Floor5;
		case 0xffC0C0C6: return Floor6;
		case 0xffC0C0C7: return Floor7;
		
		case 0xff00FFFF: return Portal0;
		case 0xff00FFF1: return Portal1;
		case 0xff00FFF2: return Portal2;
		case 0xff00FFF3: return Portal3;
		
		case 0xff0026FF: return Window0;
		case 0xff0026F1: return Window1;
		
		case 0xffA0A0A0: return Roof0;
		case 0xffA0A0A1: return Roof1;
		
		case 0xff7F0037: return Decoration0;
		
		case 0xff303030: return Misc0;
		case 0xff303031: return Misc1;
		case 0xff303032: return Misc2;
		case 0xff303033: return Misc3;
		case 0xff303034: return Misc4;
		case 0xff303035: return Misc5;
		
		case 0xff404040: return NS0;
		
		case 0xff808080: return Stairs0;
		case 0xff808081: return Stairs1;
		case 0xff808082: return Stairs2;
		
		case 0xff101010: return Map0;
		case 0xff101011: return Map1;
		case 0xff101012: return Map2;
		case 0xff101013: return Map3;
		case 0xff101014: return Map4;
		case 0xff101015: return Map5;
		case 0xff101016: return Map6;
		case 0xff101017: return Map7;
		case 0xff101018: return Map8;
		case 0xff101019: return Map9;
		case 0xff10101A: return Map10;
		case 0xff10101B: return Map11;
		case 0xff10101C: return Map12;
		case 0xff10101D: return Map13;
		case 0xff10101E: return Map14;
		case 0xff10101F: return Map15;
		case 0xff101020: return Map16;
		case 0xff101021: return Map17;
		case 0xff101022: return Map18;
		case 0xff101023: return Map19;
		case 0xff101024: return Map20;
		case 0xff101025: return Map21;
		case 0xff101026: return Map22;
		case 0xff101027: return Map23;
		
		case 0xff0094FF: return Rest0;
		
		default: return Void;
		}
	}
	
	public void changeTiles() {
	}
	
	protected void loadLevel(String path) {
	}
	
	public void loadAssets() {
	}
	
	public void clearAssets() {
		enemies = new ArrayList<Enemy>();
		npcs = new ArrayList<NPC>();
		sk = new ShopKeeper(-10, -10);
		t = new Treasure(-11, -11);
		cutscenes = new ArrayList<Animation>();
	}
	
	public String getMap() {
		return levelMap;
	}
	
	public String getName() {
		return levelName;
	}
	
	public String getFile() {
		return path;
	}
	public String getMapPath() {
		return mapPath;
	}
	
	//Level Specific Updates
	public void levelSpecific() {
		
	}
	
	public int[] getNorth() {
		return startNorth;
	}
	public int[] getEast() {
		return startEast;
	}
	public int[] getSouth() {
		return startSouth;
	}
	public int[] getWest() {
		return startWest;
	}
	
	public static int[] getTiles() {
		return tiles;
	}
	
	public static int getWidth() {
		return width;
	}
	public static int getHeight() {
		return height;
	}
}
