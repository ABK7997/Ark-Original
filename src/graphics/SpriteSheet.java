package graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpriteSheet {

	private String path; //system path to resources/spritsheet
	public final int SIZE;
	public int[] pixels; //pixel array for individual sprite pixels
	
	//paths are made availabe through the java library by adding the res folder
	public static SpriteSheet city = new SpriteSheet("Environments/City_Factory.png", 480);
	public static SpriteSheet caves = new SpriteSheet("Environments/Mountains_Caves.png", 480);
	public static SpriteSheet swamp = new SpriteSheet("Environments/Jungle_Swamp.png", 480);
	public static SpriteSheet badlands = new SpriteSheet("Environments/Badlands_Barrens.png", 480);
	public static SpriteSheet items = new SpriteSheet("Environments/Items.png", 224);
	
	public static SpriteSheet areas = new SpriteSheet("Environments/Areas.png", 320);
	
	public static SpriteSheet playable = new SpriteSheet("Mobs/main.png", 512);
	public static SpriteSheet organics_1 = new SpriteSheet("Mobs/organics_1.png", 1024);
	public static SpriteSheet cyborgs_1 = new SpriteSheet("Mobs/cyborgs_1.png", 1024);
	public static SpriteSheet machines_1 = new SpriteSheet("Mobs/machines_1.png", 1024);
	public static SpriteSheet machines_2 = new SpriteSheet("Mobs/machines_2.png", 1024);
	public static SpriteSheet mutants_1 = new SpriteSheet("Mobs/mutants_1.png", 1024);
	public static SpriteSheet mutants_2 = new SpriteSheet("Mobs/mutants_2.png", 1024);
	public static SpriteSheet bosses_1 = new SpriteSheet("Mobs/bosses_1.png", 1024);
	public static SpriteSheet bosses_2 = new SpriteSheet("Mobs/bosses_2.png", 960);
	
	public static SpriteSheet npcs_1 = new SpriteSheet("Mobs/npcs_1.png", 512);
	public static SpriteSheet villains_1 = new SpriteSheet("Mobs/villains_1.png", 512);
	
	public SpriteSheet(String path, int size) {
		this.path = path;
		SIZE = size;
		pixels = new int[SIZE * SIZE];
		try {
			load();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void load() throws IOException {
		BufferedImage image = ImageIO.read(getClass().getResource(path)); //load in spritesheet as an image
		int width = SIZE;
		int height = SIZE;
		image.getRGB(0, 0, width, height, pixels, 0, width); //convert image into the pixel array
	}
	
}
