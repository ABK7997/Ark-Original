package states;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import levels.Level;
import main.Game;
import main.Story;

public class Map {
	public static int x = 1;
	public static int y = 1;
	public static boolean chosen = false;
	
	private Font font = new Font("Verdana", Font.BOLD, 20);
	private int h = Game.frame.getHeight();
	private int w = Game.frame.getWidth();
	
	public static String[][] locations = {
			{"?", "?", "?", "?", "?"},
			{"?", "?", "?", "?", "?"},
			{"?", "?", "Orzeik", "?", "?"},
			{"?", "?", "Trench", "?", "?"},
			{"?", "?", "Swamp", "Mortisia", "Crystal Cave"},
			};
	
	public Map() {
	}
	
	public void update() {
		if (x < 1) x = 5;
		else if (x > 5) x = 1;
		
		if (y < 1) y = 5;
		else if (y > 5) y = 1;
		
		if (chosen) {
			chosen = false;
			
			//Change Area via Map
			if (locations[y-1][x-1] != "?") {
				int direction;
				
				if (y > Level.levelCoordinates[1]) direction = 0;
				else if (y < Level.levelCoordinates[1]) direction = 2;
				else if (x > Level.levelCoordinates[0]) direction = 3;
				else if (x < Level.levelCoordinates[0]) direction = 1;
				else direction = 0;
				
				Level.levelCoordinates = new int[] {x-1, y-1};
				
				//SaveState.changeArea(locations[y-1][x-1], direction);
			}
		}
	}
	
	public void render(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, w, h);
		
		g.setColor(Color.BLACK);
		g.setFont(font);
		
		//Create Map
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				g.drawRect((i*144) + w/6, j*144, 144, 144);
				if (x == i+1 && y == j+1) g.setColor(Color.RED);
				g.drawString(locations[j][i], i*144 + w/6 + 1, ((j+1)*144) - 72);
				g.setColor(Color.BLACK);
			}
		}
	}
	
	public static void openMap() {
		for (int i = 0; i <= Story.mainStory; i++) {
			switch(i) {
			case 2: locations[4][2] = "Swamp"; break;
			case 3: locations[4][3] = "Mortisia"; break;
			}
		}
	}
	
}
