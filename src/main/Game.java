package main;

import input.Keyboard;
import input.Player;
import items.Inventory;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import characters.Party;
import entity.mobs.enemies.EnemyParty;
import levels.Level;
import music.Music_Player;
import states.Battle;
import states.Dead;
import states.Dialogue;
import states.Main;
import states.Map;
import states.Start;
import states.Menu;
import states.Screen;
import states.Store;

public class Game extends Canvas implements Runnable {
	
	private static final long serialVersionUID = 4192979835196130185L;
	
	//Core Components
	private Thread thread;
	private static Game game;
	private static boolean running = false;
	private Keyboard key;
	public static FullScreen frame;
	
	//Game Components
	public static Level level;
	public static String levelName = "Orzeik_Sub";
	public static Player player;
	public static Party party;
	public static EnemyParty eParty;
	public static Inventory in;
	
	//Screen
	private BufferedImage image;
	private int[] pixels;
	
	//States
	public enum STATE {
		START, GAME, MAIN, MENU, BATTLE, DEAD, STORE, DIALOGUE, ANIMATING, MAP
	};
	
	private static Start start;
	private static Screen screen;
	public static Battle battleScreen;
	private static Menu menu;
	private static Dialogue dialogue;
	private static Store store;
	public static Main main;
	private static Dead dead;
	private static Map map;
	private static Music_Player music;
	
	public static STATE State = STATE.START;
	
	//GAME CONSTRUCTOR
	public Game() {
		frame = new FullScreen();
		
		image = new BufferedImage(frame.getWidth(), frame.getHeight(), BufferedImage.TYPE_INT_RGB);
		pixels = ((DataBufferInt)image.getRaster().getDataBuffer()).getData(); //accessing image pixels stored in an array
		
		music = new Music_Player();
		
		start = new Start(music);
		screen = new Screen(frame.getWidth(), frame.getHeight());
		menu = new Menu(); in = new Inventory();
		dialogue = new Dialogue();
		store = new Store();
		main = new Main();
		dead = new Dead();
		map = new Map();
		
		setSize(frame.getWidth(), frame.getHeight());
		frame.add(this);
		
		key = new Keyboard();
		player = new Player(60, 71, key, level);
		party = new Party();
		eParty = new EnemyParty();
		
		addKeyListener(new KeyListener() {

			public void keyPressed(KeyEvent e) {
				key.keyPressed(e);
			}
			public void keyReleased(KeyEvent e) {
				key.keyReleased(e);
			}
			public void keyTyped(KeyEvent e) {
			}
		});
		
		requestFocus();
	}
	
	//RUN AND STOP
		public synchronized void start() { //Creat thread for this game and start it
			running = true;
			thread = new Thread(this, "Main");
			thread.start();
		}
		
		public synchronized void stop() {
			running = false;
			try {
				thread.join(); //combines all threads so they stop all together at the same time
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		public void run() {
			long time = System.nanoTime();
			final double ns = 1000000000.0 / 60.0;
			double delta = 0.0;
			
			while (running) {
				long now = System.nanoTime();
				delta += (now - time) / ns;
				time = now;
				
				while (delta >= 1) {
					delta = 0;
					update();
				}
				render();
			}
			stop();
		}
	
	//UPDATE
	public void update() {
		switch (State) {
		case GAME: 
			level.update();
			key.update();
			player.update();
			break;
			
		case BATTLE: battleScreen.update(); break;
		case MENU: menu.update(); break;
		case DIALOGUE: dialogue.update(); break;
		case STORE: store.update(); break;
		case ANIMATING: 
			level.update();
			player.update(); 
			break;
			
		case START: start.update(); break;
		case MAIN: main.update(); break;
		case MAP: map.update(); break;
		case DEAD: dead.update(); break;
		default:break;
		}
	}
	
	//RENDER
	public void render() {
		
		BufferStrategy bs = getBufferStrategy(); //location for generated images to sit before being rendered on-screen
		if (bs == null) {
			createBufferStrategy(3); //creates buffer with three image slots
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		g.drawImage(image, 0, 0, frame.getWidth()*2, frame.getHeight()*2, null);
		
		screen.clear(); //erases pixels to be colored again in next frame; prevents images from overlapping
		
		//STATE-SPECIFC RENDERING/////////////////////////////////////////////////////////////////////////////////////
		
		//GAME
		switch (State) {
		
		//MAIN GAME
		case GAME: 
			int xScroll = Player.x - frame.getWidth()/4; int yScroll = Player.y - frame.getHeight()/4;
			
			level.render(xScroll, yScroll, screen);
			player.render(screen, g);
	
			for (int i = 0; i < pixels.length/2; i++) {
				if (i > (i/680) && i < (i/1360)) continue;
				pixels[i] = screen.pixels[i];
			}
			break;
			
		//BATTLE SCREEN
		case BATTLE: 
			battleScreen.render(0, 0, screen, g);
	
			for (int i = 0; i < pixels.length/2; i++) {
				if (i > (i/680) && i < (i/1360)) continue;
				pixels[i] = screen.pixels[i];
			}
			break;
		
		//DISPLAY MENU
		case MENU:
			menu.render(0, 0, screen, g);
			
			for (int i = 0; i < pixels.length/2; i++) {
				if (i > (i/680) && i < (i/1360)) continue;
				pixels[i] = screen.pixels[i];
			} break;
		
		//CHARACTER DIALOGUE
		case DIALOGUE:
			int xScroll2 = Player.x - frame.getWidth()/4; int yScroll2 = Player.y - frame.getHeight()/4;
			level.render(xScroll2, yScroll2, screen);
			player.render(screen, g);
			dialogue.render(g);
	
			for (int i = 0; i < pixels.length/2; i++) {
				pixels[i] = screen.pixels[i];
			}
			
			break;
			
		//STORE
		case STORE: store.render(g); break;
			
		//CUTSCENE ANIMATIONS
		case ANIMATING: int xScroll3 = Player.x - frame.getWidth()/4; int yScroll3 = Player.y - frame.getHeight()/4;
			level.render(xScroll3, yScroll3, screen);
			player.render(screen, g);
	
			for (int i = 0; i < pixels.length/2; i++) {
				if (i > (i/680) && i < (i/1360)) continue;
				pixels[i] = screen.pixels[i];
			}
			
			break;
		
		//STARTUP / PAUSE SCREEN
		case START: start.render(g); break;
		
		//MAIN MENU
		case MAIN: main.render(g); break;
		
		//MAP
		case MAP: map.render(g); break;
		
		//GAME OVER
		case DEAD: dead.render(g); break;
		
		default: break;
		}
		
		//////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		g.dispose();
		bs.show();
	}
	
	//MAIN
	public static void main(String[] args) {
		game = new Game();
		
		game.start();
	}

}
