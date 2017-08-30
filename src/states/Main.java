package states;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.Game;
import main.SaveState;

public class Main {
	public static int choice = 1;
	public static boolean chosen = false;
	public static boolean reset = false;
	
	private Font font = new Font("Calibri", Font.BOLD, 30);
	private int h = Game.frame.getHeight();
	
	private String path;
	private Image img;
	
	private String[] options = new String[] {
		"Resume",
		"Load Game",
		"Exit"
	};
	
	private enum STATE {
		MAIN, LOAD, SETTINGS
	};
	
	private STATE state = STATE.MAIN;
	
	public Main() {
	}
	
	public void update() {
		switch(state) {
		case MAIN:
			
			if (choice < 1) choice = 3;
			else if (choice > 3) choice = 1;
			
			if (chosen) {
				chosen = false;
				switch(choice) {
				case 1: Game.State = Game.STATE.GAME; break;
				case 2: state = STATE.LOAD; break;
				case 3: System.exit(0); break;
				}
				choice = 1;
			}
			else if (reset) {
				chosen = false;
				reset = false;
				Game.State = Game.STATE.GAME; break;
			}
			
			break;
			
		case LOAD:
			if (choice < 0) choice = SaveState.saveList.length;
			else if (choice > SaveState.saveList.length) choice = 0;
			
			if (chosen) {
				chosen = false;
				if (choice == 0) {
					choice = 1;
					state = STATE.MAIN; //return to main
				}
				else if (choice == 1) SaveState.loadGame(SaveState.lastSave);
				else SaveState.loadGame(choice);
			}
			else if (reset) {
				reset = false;
				choice = 1;
				state = STATE.MAIN;
			}
			
			break;
			
		default: break;
		}
	}
	
	public void render(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, Game.frame.getWidth(), h);
		
		switch(state) {
		
		case MAIN:
			//Level Map
			g.drawImage(img, Game.frame.getWidth()/3, 25, null);
			
			g.setColor(Color.WHITE);
			g.setFont(font);
			g.drawString("ARK", 5, 25);
			
			for (int i = 0; i < options.length; i++) {
				if (choice == i+1) g.setColor(Color.RED);
				g.drawString(options[i], 10, h/3 + (i*40));
				g.setColor(Color.WHITE);
			}
			break;
			
		case LOAD:
			g.setFont(font); g.setColor(Color.WHITE);
			
			if (choice == 0) g.setColor(Color.RED);
			g.drawString("Return", 100, 50);
			g.setColor(Color.WHITE);
			
			if (choice == 1) g.setColor(Color.RED);
			g.drawString("Load Last Save", 100, 100);
			
			g.setColor(Color.WHITE);
			for (int i = 0; i < SaveState.saveList.length; i++) {
				if (choice == i + 2) g.setColor(Color.RED);
				if (SaveState.saveList[i] != null) g.drawString("Load #" + (i+1), 100, 150 + (i*50));
				g.setColor(Color.WHITE);
			}
			break;
			
		default: break;		
		}
	}
	
	public void openMain() {
		choice = 1;
		
		switch(Game.levelName) {
		case "World_Map": path = "levelMaps/WorldMap.png"; break;
		case "PartyCamp": path = "levelMaps/WorldMap.png"; break;
		
		case "Orzeik": path = "levelMaps/OrzeikMap.png"; break;
		case "Orzeik_Sub": path = "levelMaps/OrzeikMap.png"; break;
		case "Trench": path = "levelMaps/TrenchMap.png"; break;
		case "Trench_Sub": path = "levelMaps/TrenchMap.png"; break;
		case "Swamp": path = "levelMaps/SwampMap.png"; break;
		case "Swamp_Sub": path = "levelMaps/SwampMap.png"; break;
		case "Mortisia": path = "levelMaps/MortisiaMap.png"; break;
		case "Mortisia_Sub": path = "levelMaps/MortisiaMap.png"; break;
		case "Crystal Cave": path = "levelMaps/Crystal_CaveMap.png"; break;
		}
		
		try {
			img = ImageIO.read(getClass().getResource(path));
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Could not read image file");
		}
	}
	
}
