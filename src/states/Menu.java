package states;

import items.Inventory;
import items.Item;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import levels.Level;
import main.Game;
import main.SaveState;
import characters.Party;
import characters.Playable;
import characters.Playable.STATE;

public class Menu extends Level {
	
	private static ArrayList<Playable> party;
	private static ArrayList<Playable> partyPresent;
	private static ArrayList<Item> items;
	
	private int w = Game.frame.getWidth();
	private int h = Game.frame.getHeight();
	
	private Font font = new Font("Times New Roman", Font.BOLD, 18);
	private Font largeFont = new Font("Times New Roman", Font.BOLD, 30);
	private Font medFont = new Font("Calibri", Font.BOLD, 24);
	
	//Selection
	public static int choice = 1;
	public static int superChoice = 0;
	public static int partyChoice = 0;
	public static int spellChoice = 0;
	public static boolean spells = false;
	public static boolean chosen = false;
	public static boolean selecting = false;
	public static boolean reset = false;
	private int item = 0;
	
	private static int numChosen = 0;
	private static int party1 = -1;
	private static int party2 = -1;
	private static int party3 = -1;
	
	private enum STATES {
		MAIN, INVENTORY, SAVE, LOAD, PARTY, STATUS
	};
	
	private static STATES state = STATES.MAIN;
	
	public Menu() {
		loadLevel();
	}
	
	protected void loadLevel() {
		try {
			BufferedImage image = ImageIO.read(getClass().getResource(loadMap()));
			int w = width = image.getWidth();
			int h = height = image.getHeight();
			tiles = new int[w * h];
			image.getRGB(0, 0, w, h, tiles, 0, w);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static String loadMap() {
		switch (Game.levelName) {
		case "Orzeik": return "Menus/Orzeik.png";
		default: return "Menus/default.png";
		}
	}
	
	public void update() {
		switch (state) {
		case MAIN: 
			
			if (choice < 1) choice = 6;
			else if (choice > 6) choice = 1;
			
			if (chosen) {
				chosen = false;
				
				switch(choice) {
				case 1: state = STATES.INVENTORY; spells = true; selecting = false; choice = 0; break;
				case 2: state = STATES.STATUS; choice = 0; break;
				case 3: state = STATES.PARTY; choice = 0; break;
				case 4: state = STATES.SAVE; choice = 0; break;
				case 5: state = STATES.LOAD; choice = 0; break;
				case 6: System.exit(0); break;
				}
			}
			
			else if (reset) {
				reset = false;
				Game.State = Game.STATE.GAME;
			}
			
			break;
			
		case INVENTORY:
			if (selecting) {
				if (choice < 0) choice = party.size();
				else if (choice > party.size()) choice = 0;
			}
			else {
				if (superChoice < 0) superChoice = party.size();
				else if (superChoice > party.size()) superChoice = 0;
				
				switch (superChoice) {
				case 0:
					if (spellChoice < 0) spellChoice = items.size();
					else if (spellChoice > items.size()) spellChoice = 0;
					break;
					
				case 1:
					if (spellChoice < 0) spellChoice = party.get(0).getCures().size();
					else if (spellChoice > party.get(0).getCures().size()) spellChoice = 0;
					break;
					
				case 2:
					if (spellChoice < 0) spellChoice = party.get(1).getCures().size();
					else if (spellChoice > party.get(1).getCures().size()) spellChoice = 0;
					break;
					
				case 3:
					if (spellChoice < 0) spellChoice = party.get(2).getCures().size();
					else if (spellChoice > party.get(2).getCures().size()) spellChoice = 0;
					break;
				}
			}
			
			if (selecting && chosen) {
				chosen = false;
				selecting = false;
				if (choice == 0) {
					choice = 1;
				}
				else if (superChoice == 0) Inventory.useItem(items.get(item), party.get(choice-1));
				else {
					//For Techs
					if (party.get(superChoice-1).getMaxMP() == 0) { 
						if (party.get(superChoice-1).getEP() >= party.get(superChoice-1).getCures().get(spellChoice-1).getCost()) {
							party.get(superChoice-1).getCures().get(spellChoice-1).heal(party.get(choice-1));
						}
					} 
					//For Spells
					else if (party.get(superChoice-1).getMP() >= party.get(superChoice-1).getCures().get(spellChoice-1).getCost()) {
						party.get(superChoice-1).getCures().get(spellChoice-1).heal(party.get(choice-1));
					}
				}
			}
			
			else if (chosen) {
				chosen = false; 
				
				if (spellChoice == 0) {
					spells = false;
					choice = 1;
					spellChoice = 0;
					state = STATES.MAIN;
					return;
				}
				
				selecting = true;
				
				switch (superChoice) {
				case 0:
					item = spellChoice-1;
					if (items.get(item).getStock() > 0) {
						spellChoice = 1;
					}
					break;
				}
			}
			
			else if (reset) {
				reset = false;
				state = STATES.MAIN;
				choice = 1;
				spells = false;
				spellChoice = 0;
			}
			
			break;
			
		case STATUS:
			if (spells) {
				Playable p = party.get(partyChoice);
				
				if (superChoice < 0) {
					spellChoice = 0;
					superChoice = 4;
					if (p.getDefs().size() == 0) {
						superChoice = 3;
						if (p.getCures().size() == 0) {
							superChoice = 2;
							if (p.getOffs().size() == 0) superChoice = 1;
						}
					}
				}
				if (superChoice > 4) {
					superChoice = 0;
					spellChoice = 0;
				}
				
				if (reset) {
					reset = false;
					spells = false;
					superChoice = 0;
					spellChoice = 0;
				}
				
				switch(superChoice) {
				case 0: spellChoice = 0; break;
				case 1: 
					if (spellChoice < 0) spellChoice = p.getSkills().size()-1;
					else if (spellChoice > p.getSkills().size()-1) spellChoice = 0;
					break;
				case 2:
					if (spellChoice < 0) spellChoice = p.getOffs().size()-1;
					else if (spellChoice > p.getOffs().size()-1) spellChoice = 0;
					if (p.getOffs().size() == 0) {
						superChoice = 3;
						if (p.getCures().size() == 0) {
							superChoice = 4;
							if (p.getDefs().size() == 0) {
								superChoice = 0;
							}
						}
					}
					break;		
				case 3:
					if (spellChoice < 0) spellChoice = p.getCures().size()-1;
					else if (spellChoice > p.getCures().size()-1) spellChoice = 0;
					if (p.getCures().size() == 0) {
						superChoice = 4;
						if (p.getDefs().size() == 0) {
							superChoice = 0;
						}
					}
					break;
				case 4: 
					if (spellChoice < 0) spellChoice = p.getDefs().size()-1;
					else if (spellChoice > p.getDefs().size()-1) spellChoice = 0;
					if (p.getDefs().size() == 0) {
						superChoice = 0;
					}
					break;
				}
				
				switch(superChoice) {
				case 0: spellChoice = 0;
				}
				
				if (chosen) {
					chosen = false;
					if (superChoice == 0) {
						spells = false;
						choice = partyChoice+1; superChoice = 0;
					}
				}
			}
			
			else {
				if (choice < 1) choice = party.size()+1;
				else if (choice > party.size()+1) choice = 1;
			
				if (chosen) {
					chosen = false;
					if (choice == party.size()+1) {
						state = STATES.MAIN;
						choice = 2;
					}
					else {
						spells = true;
						partyChoice = choice-1;
						superChoice = 0; spellChoice = 0;
					}
				}
				else if (reset) {
					reset = false;
					state = STATES.MAIN;
					choice = 2;
				}
			}
			break;
		
		case PARTY: 
			if (choice < 0) choice = partyPresent.size();
			else if (choice > partyPresent.size()) choice = 0;
			
			if (chosen) {
				chosen = false;
				if (choice == 0) {
					state = STATES.MAIN;
					choice = 3;
					if (party1 == -1) return;
					else if (party2 == -1) {
						Party.changeParty(partyPresent.get(party1));
						party = Party.party;
					}
					else if (party3 == -1) {
						Party.changeParty(partyPresent.get(party1), partyPresent.get(party2));
						party = Party.party;
					}
					else {
						Party.changeParty(partyPresent.get(party1), partyPresent.get(party2), partyPresent.get(party3));
						party = Party.party;
					}
					reconfigureParty();
				}
				else {
					//Choose Member
					switch(numChosen) {
					
					case 0: party1 = choice-1; break;
					case 1: party2 = choice-1; break;
					case 2: party3 = choice-1; break;
					case 3: 
						party1 = choice-1;
						party2 = -1;
						party3 = -1;
						numChosen = 0;
					}
					
					numChosen++;
				}
			}
			
			else if (reset) {
				reset = false;
				state = STATES.MAIN;
				choice = 3;
			}
			
			break;
			
		case SAVE:
			if (choice < 0) choice = SaveState.saveList.length + 1;
			else if (choice > SaveState.saveList.length + 1) choice = 0;
			
			if (chosen) {
				chosen = false;
				state = STATES.MAIN;//return
				if (choice == 1) { //Create new save file
					if (SaveState.saveList.length == 0) {
						SaveState.createSave(0);
					}
					else if (SaveState.saveList.length < 10) SaveState.createSave(SaveState.saveList.length);
				}
				else {
					SaveState.saveGame(choice-2);
				}
				
				choice = 1;
			}
			
			else if (reset) {
				reset = false;
				state = STATES.MAIN;
				choice = 4;
			}
			
			break;
			
		case LOAD:
			if (choice < 0) choice = SaveState.saveList.length + 1;
			else if (choice > SaveState.saveList.length + 1) choice = 0;
			
			if (chosen) {
				chosen = false;
				if (choice == 0) state = STATES.MAIN; //return to main
				else if (choice == 1) SaveState.loadGame(SaveState.lastSave);
				else SaveState.loadGame(choice-2);
				choice = 1;
			}
			
			else if (reset) {
				reset = false;
				state = STATES.MAIN;
				choice = 5;
			}
			
			break;
			
		default: break;
		}
	}
	
	public void render(int xScroll, int yScroll, Screen screen, Graphics g) {
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
		
		switch(state) {
		case MAIN: 
			g.setColor(Color.WHITE);
			g.setFont(font);
			
			//PARTY INFORMATION
			for (int i = 0; i < party.size(); i++) {
				Playable p = party.get(i);
				
				p.render(screen);
				
				g.setColor(Color.WHITE);
				
				g.drawString("Lv. " + p.getLevel(), (p.getX()+32)*2, (p.getY()*2));
				
				if (p.getHP() < p.getMaxHP()/9) g.setColor(Color.RED);
				g.drawString("HP: " + p.getHP() + " / " + p.getMaxHP(), (p.getX()+32)*2, (p.getY()+10)*2); //HP
				g.setColor(Color.WHITE);
				
				if (p.getMP() == 0) g.drawString("EP: " + p.getEP() + " / " + p.getMaxEP(), (p.getX()+32)*2, (p.getY()+20)*2); //EP
				else g.drawString("MP: " + p.getMP() + " / " + p.getMaxMP(), (p.getX()+32)*2, (p.getY()+20)*2); //MP
				g.drawString("Exp: " + p.getExp() + " / " + p.getNextLv(), (p.getX()+32)*2, (p.getY()+30)*2); //EXP
			}
			
			g.setFont(largeFont);
			
			//Money
			g.drawString("Money: $" + Party.money, party.get(0).getX()*2, (party.get(party.size()-1).getY()+75) * 2);
			
			//MENU OPTIONS
			g.drawString("Inventory", w * 2/3, h/7);
			g.drawString("Status", w * 2/3, h/7 + 100);
			g.drawString("Party", w * 2/3, h/7 + 200);
			g.drawString("Save Game", w * 2/3, h/7 + 300);
			g.drawString("Load Game", w * 2/3, h/7 + 400);
			g.drawString("Exit Game", w * 2/3, h/7 + 500);
			
			g.setColor(Color.RED);
			switch(choice) {
			case 1: g.drawString("Inventory", w * 2/3, h/7); break;
			case 2: g.drawString("Status", w * 2/3, h/7 + 100); break;
			case 3: g.drawString("Party", w * 2/3, h/7 + 200); break;
			case 4: g.drawString("Save Game", w * 2/3, h/7 + 300); break;
			case 5: g.drawString("Load Game", w * 2/3, h/7 + 400); break;
			case 6: g.drawString("Exit Game", w * 2/3, h/7 + 500); break;
			}
			
			break;
			
		case INVENTORY:
			g.setFont(medFont); g.setColor(Color.WHITE);
			for (int i = 0; i < items.size(); i++) {
				if (superChoice == 0 && spellChoice-1 == i && !selecting) g.setColor(Color.RED);
				g.drawString(items.get(i).getName() + " x" + items.get(i).getStock(), w/3, h/10 + (25 * i));
				g.setColor(Color.WHITE);
			}
			
			if (superChoice == 0 && spellChoice > 0 && spellChoice <= items.size()) {
				g.drawString(items.get(spellChoice-1).getDescription(), w/3, h - 50);
			}
			
			if (!selecting && spellChoice == 0) g.setColor(Color.RED);
			g.drawString("Return", w/3, h/10 - 25);
			g.setColor(Color.WHITE);
			
			if (selecting) {
				if (choice == 0) g.setColor(Color.RED);
				g.drawString("Return", party.get(0).getX()*2, (party.get(0).getY()-32)*2);
				g.setColor(Color.WHITE);
			}
			
			for (int i = 0; i < party.size(); i++) {
				Playable p = party.get(i);
				
				p.render(screen);

				if (selecting && choice-1 == i) {
					g.setColor(Color.BLUE);
					g.fillOval(p.getX()*2, p.getY()*2, 15, 15);
				}
				
				//PARTY SPRITES and RELEVANT STATS
				g.setColor(Color.WHITE);
				if (p.getHP() < p.getMaxHP()/9) g.setColor(Color.RED);
				g.drawString("HP: " + p.getHP() + " / " + p.getMaxHP(), (p.getX()+32)*2, p.getY()*2); //HP
				g.setColor(Color.WHITE);
				
				if (p.getMaxMP() == 0) g.drawString("EP: " + p.getEP() + " / " + p.getMaxEP(), (p.getX()+32)*2, (p.getY()+10)*2); //EP
				else g.drawString("MP: " + p.getMP() + " / " + p.getMaxMP(), (p.getX()+32)*2, (p.getY()+10)*2); //MP
				
				//Curative Spells
				for (int j = 0; j < p.getCures().size(); j++) {
					if (superChoice == i+1 && spellChoice == j+1 && !selecting) {
						g.setColor(Color.WHITE);
						g.drawString(p.getCures().get(spellChoice-1).getDescription(), w/3, h - 50);
						g.setColor(Color.RED);
					}
					g.drawString(p.getCures().get(j).getName(), w/2 + (120 * i), h/10 + (25*j));
					g.setColor(Color.WHITE);
					
					if (p.getCures().get(j).isAnimating()) p.getCures().get(j).animate(g);
				}
			}
			
			break;
		
		case STATUS: 
			g.setFont(largeFont); g.setColor(Color.WHITE);
			if (choice == party.size()+1) g.setColor(Color.RED);
			g.drawString("Return", (w * 1/3) + 600, 25);
			
			g.setColor(Color.WHITE);
			//CHARACTER LIST
			for (int i = 0; i < party.size(); i++) {
				if (choice-1 == i) g.setColor(Color.RED);
				g.drawString(party.get(i).getName(), (w * 1/4) + (200 * i), 25);
				g.setColor(Color.WHITE);
			}
			
			if (choice < party.size()+1 && choice > 0) {
				Playable p = party.get(choice-1);
				p.render(screen);
				g.setColor(Color.WHITE);
			
				//ALL STATS
				g.drawString("Lv. " + p.getLevel(), w/6, h/6 - 50);
				
				g.drawString("HP: " + p.getHP() + " / " + p.getMaxHP(), w/6, h/6); //HP
				if (p.getMaxMP() == 0) g.drawString("EP: " + p.getEP() + " / " + p.getMaxEP(), w/6, h/6 + 50); //EP
				else g.drawString("MP: " + p.getMP() + " / " + p.getMaxMP(), w/6, h/6 + 50); //MP
				g.drawString("Exp: " + p.getExp() + " / " + p.getNextLv(), w/6, h/6 + 100); //Exp
			
				g.drawString("Power: " + p.getBasePwr(), w/6, h/6 + 200); //Pwr
				g.drawString("Dexterity: " + p.getBaseDex(), w/6, h/6 + 240); //Dex
				g.drawString("Speed: " + p.getBaseSpd(), w/6, h/6 + 280); //Spd
				g.drawString("Evasion: " + p.getBaseEvd(), w/6, h/6 + 320); //Evd
				g.drawString("Resistance: " + p.getBaseRes(), w/6, h/6 + 360); //Res
			
				if (p.getMaxMP() == 0) g.drawString("Tech Power: " + p.getBaseMag(), w/6, h/6 + 400); //Eng 
				else g.drawString("Magic Power: " + p.getBaseMag(), w/6, h/6 + 400); //Mag
			
				g.drawString("Physical Defense: x" + p.getBaseDef(), w/6, h/6 + 440); //Def
				g.drawString("Magical Defense: x" + p.getBaseMagDef(), w/6, h/6 + 480); //MagDef
			
				g.setFont(largeFont);
				//ALL SPELLS / TECHS and SKILLS
				g.drawString("SKILLS", w * 2/5, h/6); //SKILLS
				g.setFont(medFont);
				
				for (int i = 0; i < p.getSkills().size(); i++) { 
					if (superChoice == 1 && spellChoice == i) g.setColor(Color.RED);
					g.drawString(p.getSkills().get(i).getName(), w * 2/5, h/6 + ((i+1) * 30));
					g.setColor(Color.WHITE);
				}
				
				g.setFont(largeFont);
				if (p.getMaxMP() == 0) g.drawString("TECHS", w*31/48, h/6);
				else g.drawString("SPELLS", w*31/48, h/6);
				g.setFont(medFont);
				for (int i = 0; i < p.getOffs().size(); i++) { //OFFENSIVE
					if (superChoice == 2 && spellChoice == i) g.setColor(Color.RED);
					g.drawString(p.getOffs().get(i).getName(), w*7/12, h/6 + ((i+1) * 30));
					g.setColor(Color.WHITE);
				}
				for (int i = 0; i < p.getCures().size(); i++) { //CURATIVE
					if (superChoice == 3 && spellChoice == i) g.setColor(Color.RED);
					g.drawString(p.getCures().get(i).getName(), w*8/12, h/6 + ((i+1) * 30));
					g.setColor(Color.WHITE);
				}
				for (int i = 0; i < p.getDefs().size(); i++) { //DEFENSIVE
					if (superChoice == 4 && spellChoice == i) g.setColor(Color.RED);
					g.drawString(p.getDefs().get(i).getName(), w*9/12, h/6 + ((i+1) * 30));
					g.setColor(Color.WHITE);
				}
			}
			
			else {
				for (int i = 0; i < party.size(); i++) {
					party.get(i).render(screen);
				}
			}
			
			//Spell / Skill descriptions
			g.setColor(Color.WHITE);
			if (spells) {
				Playable p = party.get(partyChoice);
				
				if (superChoice == 0) g.setColor(Color.RED);
				g.drawString("Return", w*1/3, h/6);
				g.setColor(Color.WHITE);
				
				if (spellChoice > -1) {
				
				switch (superChoice) {
				case 1:
					if (p.getSkills().size() > 0) if (spellChoice < p.getSkills().size()) g.drawString(p.getSkills().get(spellChoice).getDescription(), w * 1/3, h*9/10);
					break;
				case 2: if (p.getOffs().size() > 0) if (spellChoice < p.getOffs().size()) g.drawString(p.getOffs().get(spellChoice).getDescription(), w * 1/3, h*9/10);
					break;
				case 3: if (p.getCures().size() > 0) if (spellChoice < p.getCures().size()) g.drawString(p.getCures().get(spellChoice).getDescription(), w * 1/3, h*9/10);
					break;
				case 4: if (p.getDefs().size() > 0) if (spellChoice < p.getDefs().size()) g.drawString(p.getDefs().get(spellChoice).getDescription(), w * 1/3, h*9/10);
					break;
				} 
				}
			}
			
			break;
		
		case PARTY:
			g.setFont(largeFont); g.setColor(Color.WHITE); 
			
			if (choice == 0) g.setColor(Color.RED);
			g.drawString("Accept Party", 100, 50);
			g.setColor(Color.WHITE);
			
			for (int i = 0; i < partyPresent.size(); i++) {
				
				if (party1 == i) g.setColor(Color.GREEN);
				else if (party2 == i) g.setColor(Color.GREEN);
				else if (party3 == i) g.setColor(Color.GREEN);
				
				if (choice-1 == i) g.setColor(Color.RED);
				g.drawString(partyPresent.get(i).getName(), 100, 100 + (i*50));
				g.setColor(Color.WHITE);
			}
			
			g.drawString("Active Party", w * 3/4, h/10);
			g.setColor(Color.GREEN);
			for (int i = 0; i < party.size(); i++) {
				g.drawString(party.get(i).getName(), w*3/4, h/10 + ((i+1)*40));
			}
			break;
			
		case SAVE:
			g.setFont(largeFont); g.setColor(Color.WHITE);
			
			if (choice == 0) g.setColor(Color.RED);
			g.drawString("Return", 100, 50);
			g.setColor(Color.WHITE);
			
			if (choice == 1) g.setColor(Color.RED);
			g.drawString("Create New Save", 100, 100);
			
			g.setColor(Color.WHITE);
			for (int i = 0; i < SaveState.saveList.length; i++) {
				if (choice == i + 2) g.setColor(Color.RED);
				if (SaveState.saveList[i] != null) g.drawString("Save #" + (i+1), 100, 150 + (i*50));
				g.setColor(Color.WHITE);
			}
			break;
			
		case LOAD:
			g.setFont(largeFont); g.setColor(Color.WHITE);
			
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
	
	public static void openMenu() {
		state = STATES.MAIN;
		party = Party.party;
		partyPresent = Party.partyPresent;
		items = Inventory.items;
		reconfigureParty();
		chosen = false;
		reset = false;
		choice = 1;
		superChoice = 0;
		spells = false;
		spellChoice = 0;
		
		party1 = -1;
		party2 = -1;
		party3 = -1;
	}
	
	public static void reconfigureParty() {
		for (int i = 0; i < party.size(); i++) {
			Playable p = party.get(i);
			p.changeCoordinates(2 * 32, (2 + (2 * i)) * 32);
			p.changeState(STATE.MENU);
		}
	}
	
}
