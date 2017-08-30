package states;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import main.Game;
import main.SaveState;
import music.Music_Player;

public class Start {
	public static int choice = 1;
	public static boolean chosen = false;
	public static boolean reset = false;
	
	private Font font = new Font("Calibri", Font.BOLD, 30);
	private int h = Game.frame.getHeight();
	private int w = Game.frame.getWidth();
	
	//Options
	private String[] options = new String[] {
		"Load Game",
		"New Game",
		"Settings",
		"How to Play",
		"Exit"
	};
	
	//Controls
	private String[] controls = new String[] {
		"Up: up arrow key",
		"Left: left arrow key",
		"Down: down arrow key",
		"Right: right arrow key",
		"",
		"Select: V",
		"Cancel/Back: C",
		"Menu: X",
		"Party Camp: Z"
		};
	
	private String[] settings = new String[] {
		"Return",
		"Clear All Saves"
	};
	
	private Music_Player music;
	
	private enum STATE {
		MAIN, LOAD, SETTINGS, HTP
	};
	
	private STATE state = STATE.MAIN;
	
	public Start(Music_Player music) {
		this.music = music; 
		music.mainTheme();
		
		SaveState.loadSaves();
	}
	
	public void update() {
		switch(state) {
		case MAIN: 
			if (choice < 1) choice = 5;
			else if (choice > 5) choice = 1;
			
			if (chosen) {
				chosen = false;
				switch(choice) {
				case 1: if (SaveState.saveList.length > 0) state = STATE.LOAD; choice = 1; break;
				case 2: SaveState.loadLevel(); music.changeMusic(); choice = 1; break;
				case 3: state = STATE.SETTINGS; choice = 0; break;
				case 4: state = STATE.HTP; break;
				case 5: System.exit(0); break;
				}
			}
			break;
			
		case LOAD:
			if (choice < 0) choice = SaveState.saveList.length+1;
			else if (choice > SaveState.saveList.length+1) choice = 0;
			
			if (chosen) {
				chosen = false;
				if (choice == 0) state = STATE.MAIN; //return to main
				else if (choice == 1) SaveState.loadGame(SaveState.lastSave);
				else SaveState.loadGame(choice-2);
				choice = 1;
			}
			else if (reset) {
				reset = false;
				choice = 1;
				state = STATE.MAIN;
			}
			
			break;
			
		case SETTINGS: 
			if (choice < 0) choice = 1;
			else if (choice > 1) choice = 0;
			
			if (chosen) {
				chosen = false;
				switch (choice) {
				case 0: state = STATE.MAIN; choice = 3; break;
				case 1: SaveState.eraseSaves(); break;
				}
			}
			else if (reset) {
				reset = false;
				state = STATE.MAIN;
				choice = 3;
			}
			
			break;
			
		case HTP:
			if (chosen) {
				chosen = false;
				state = STATE.MAIN;
				choice = 4;
			}
			else if (reset) {
				reset = false;
				state = STATE.MAIN;
				choice = 4;
			}
			
		default: break;
		}
	}
	
	public void render(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, Game.frame.getWidth(), h);
		
		switch(state) {
		case MAIN:
			g.setColor(Color.WHITE);
			g.setFont(font);
			g.drawString("ARK", 5, 25);
			
			for (int i = 0; i < options.length; i++) {
				if (choice == i+1) g.setColor(Color.RED);
				g.drawString(options[i], 10, h/3 + (40 * i));
				g.setColor(Color.WHITE);
			}
			
			break;
			
		case LOAD:
			g.setFont(font); g.setColor(Color.WHITE);
			
			if (choice == 0) g.setColor(Color.RED);
			g.drawString("Return", 100, 50);
			g.setColor(Color.WHITE);
			
			if (choice == 1) g.setColor(Color.RED);
			if (SaveState.saveList.length == 0) g.setColor(Color.GRAY);
			g.drawString("Load Last Save", 100, 100);
			
			g.setColor(Color.WHITE);
			for (int i = 0; i < SaveState.saveList.length; i++) {
				if (choice == i + 2) g.setColor(Color.RED);
				if (SaveState.saveList[i] != null) g.drawString("Load #" + (i+1), 100, 150 + (i*50));
				g.setColor(Color.WHITE);
			}
			break;
			
		case SETTINGS:
			g.setFont(font); g.setColor(Color.WHITE);
			
			for (int i = 0; i < settings.length; i++) {
				if (choice == i) g.setColor(Color.RED);
				g.drawString(settings[i], 100, 50 + (i * 50));
				g.setColor(Color.WHITE);
			}
			break;
			
		case HTP: 
			g.setFont(font); g.setColor(Color.RED);
			
			g.drawString("Return", w/6, h/10);
			
			g.setColor(Color.WHITE);
			for (int i = 0; i < controls.length; i++) {
				g.drawString(controls[i], w/6, h/4 + (i * 35));
			}
			
		}
	}
	
}
