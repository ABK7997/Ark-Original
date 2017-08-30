package states;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import main.Game;
import main.SaveState;

public class Dead {
	public static int choice = 1;
	public static boolean chosen = false;
	
	private Font font = new Font("Verdana", Font.BOLD, 30);
	private int h = Game.frame.getHeight();
	private int w = Game.frame.getWidth();
	
	public Dead() {
	}
	
	public void update() {
		if (choice < 1) choice = 2;
		else if (choice > 2) choice = 1;
		
		if (chosen) {
			chosen = false;
			
			switch(choice) {
			case 1: SaveState.loadGame(SaveState.lastSave); break;
			case 2: System.exit(0);
			}
		}
	}
	
	public void render(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, w, h);
		
		g.setColor(Color.WHITE);
		g.setFont(font);
		
		g.drawString("Load Last Save", 5, h/3);
		g.drawString("Exit", 5, h/3 + font.getSize());
		
		g.setColor(Color.RED);
		switch(choice) {
		case 1: g.drawString("Load Last Save", 5, h/3); break;
		case 2: g.drawString("Exit", 5, h/3 + font.getSize()); break;
		}
	}
	
}
