package states;

import input.Player;
import items.Inventory;
import items.Item;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;

import levels.Level;
import main.Game;
import main.SaveState;
import music.Music_Player;
import battle.Skill;
import battle.Spell;
import characters.Party;
import characters.Playable;
import characters.Playable.STATE;
import entity.mobs.enemies.Enemy;
import entity.mobs.enemies.Enemy.STATES;
import entity.mobs.enemies.EnemyParty;

public class Battle extends Level {
	
	private Random random = new Random();
	private Music_Player music = new Music_Player();
	
	private static ArrayList<Item> items;
	private static int mon;
	
	public static int turnNumber = 0;
	
	//Battle Menus
	private int w = Game.frame.getWidth();
	private int h = Game.frame.getHeight();
	
	//Graphics g
	private Font font = new Font("Calibri", Font.BOLD, w/75);
	
	//Parties
	public static ArrayList<Playable> party = Party.party;
	public static ArrayList<Enemy> eParty = EnemyParty.eParty;
	
	//Menu Selection
	public static int charSelect = 0;
	public static int choice = 1;
	public static int superChoice = 1;
	public static boolean chosen = false;
	public static boolean reset = false;
	
	//Enemy Selection
	public static int chooseEnemy;
	
	//Animation Sequence
	private int[] order;
	public static boolean secondMove = false;
	
	//STATES
	public static enum PHASE {
		PAUSE, SELECTION, PARTYSELECTION, ANIMATING, TARGET, MAGIC, SKILL, ITEM, END
	}
	
	public static PHASE phase = PHASE.SELECTION;
	
	public Battle() {
		loadLevel();
		beginBattle();
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
	
	public void update() {
		if (charSelect < 0) charSelect = 0;
		
		Playable p = party.get(charSelect);
		
		switch(phase) {
		case SELECTION: //MENU SELECTION
			if (p.getHP() == 0) {
				charSelect++;
				if (charSelect == party.size()) {
					startAnimation();
					charSelect = 0;
				}
			}
			else if (p.getSleep()) {
				charSelect++;
				if (charSelect == party.size()) {
					startAnimation();
					charSelect = 0;
				}
			}
			
			if (choice > 6) {
				choice = 1;
			}
			else if (choice < 1) {
				choice = 6;
			}
		
			if (charSelect > party.size() - 1) {
				charSelect = 0;
				choice = 1;
			}
		
			if (chosen) {
				chosen = false;
				
				p.setChoice(choice);
				
				switch (choice) {
				case 1: phase = PHASE.TARGET; choice = 1; break;
				case 2: charSelect++; choice = 1; if (charSelect == party.size()) startAnimation(); break;
				case 3: charSelect++; choice = 1; if (charSelect == party.size()) startAnimation(); break;
				case 4: 
					choice = 1;
					if (p.getMP() > 0) phase = PHASE.MAGIC;
					else if (p.getEP() > 0) phase = PHASE.MAGIC;
					else p.setMessage("NO MP/EP");
					break;
				case 5: phase = PHASE.SKILL; choice = 1; break;
				case 6: phase = PHASE.ITEM; choice = 0; break;
				}
			} break;
			
		case TARGET: //TARGET ENEMY
			if (choice > eParty.size()) {
				choice = 1;
			}
			else if (choice < 1) {
				choice = eParty.size();
			}
			
			if (eParty.get(choice-1).getHP() <= 0) choice++;
			
			if (chosen) {
				chosen = false;
				
				p.setTarget(eParty.get(choice-1));
				
				charSelect++; choice = 1;
				
				if (charSelect == party.size()) {
					startAnimation();
				}
				else phase = PHASE.SELECTION;
				
				if (p.getHP() == 0) {
					charSelect++;
				}
				if (charSelect == party.size()) {
					startAnimation();
				}
			} break;
			
		case PARTYSELECTION: //TARGET PARTY MEMBER
			if (choice > party.size()) {
				choice = 1;
			}
			else if (choice < 1) {
				choice = party.size();
			}
			
			if (chosen) {
				chosen = false;
				
				p.setFriendly(party.get(choice-1));
				charSelect++; choice = 1;
				if (party.get(charSelect-1).getHP() <= 0) charSelect++;
				
				if (charSelect == party.size()) {
					startAnimation();
				}
				else phase = PHASE.SELECTION; 
			} break;
				
		case MAGIC: //SELECT MAGIC SPELL OR TECH
			if (superChoice > 2 && p.getDefs().size() == 0) superChoice = 1;
			else if (superChoice > 3) {
				if (p.getOffs().size() == 0) superChoice = 2;
				else superChoice = 1;
			}
			else if (superChoice < 1) {
				if (p.getDefs().size() == 0) superChoice = 2;
				else superChoice = 3;
			}
			
			if (p.getOffs().size() == 0 && p.getCures().size() == 0 && p.getDefs().size() == 0) {
				choice = 0;
			}
			
			if (superChoice == 1 && p.getOffs().size() == 0) superChoice++; 
			if (superChoice == 2 && p.getCures().size() == 0) superChoice++;
			if (superChoice == 3 && p.getDefs().size() == 0) superChoice++;
			
			switch (superChoice) {
			case 1: 
				if (choice > p.getOffs().size()) choice = 0;
				else if (choice < 0) choice = p.getOffs().size(); break;
			case 2:
				if (choice > p.getCures().size()) choice = 0;
				else if (choice < 0) choice = party.get(charSelect).getCures().size(); break;
			case 3:
				if (choice > p.getDefs().size()) choice = 0;
				else if (choice < 0) choice = p.getDefs().size(); break;
			}
			
			if (chosen) {
				chosen = false;
				
				if (choice == 0) {
					choice = 1;
					phase = PHASE.SELECTION;
					return;
				}
				
				if (superChoice == 1) {
					p.setSpell(p.getOffs().get(choice-1));
					if (p.getMaxMP() > 0) {
						if (p.getOffs().get(choice-1).getCost() > p.getMP()) return;
					}
					else if (p.getMaxEP() > 0) {
						if (p.getOffs().get(choice-1).getCost() > p.getEP()) return;
					}
					if (p.getOffs().get(choice-1).getRange() != "Single") {
						p.setTarget(eParty.get(0));
						charSelect++;
						if (charSelect == party.size()) startAnimation();
						else {
							phase = PHASE.SELECTION;
						}
					}
					else phase = PHASE.TARGET; 
				}
				else if (superChoice == 2) {
					p.setSpell(p.getCures().get(choice-1));
					if (p.getMaxMP() > 0) {
						if (p.getCures().get(choice-1).getCost() > p.getMP()) return;
					}
					else if (p.getMaxEP() > 0) {
						if (p.getCures().get(choice-1).getCost() > p.getEP()) return;
					}
					if (p.getCures().get(choice-1).getRange() != "Single") {
						p.setTarget(eParty.get(0));
						charSelect++;
						if (charSelect == party.size()) startAnimation();
						else {
							phase = PHASE.SELECTION;
						}
					}
					else phase = PHASE.PARTYSELECTION;
				}
				else if (superChoice == 3) {
					p.setSpell(p.getDefs().get(choice-1));
					if (p.getMaxMP() > 0) {
						if (p.getDefs().get(choice-1).getCost() > p.getMP()) return;
					}
					else if (p.getMaxEP() > 0) {
						if (p.getDefs().get(choice-1).getCost() > p.getEP()) return;
					}
					if (p.getDefs().get(choice-1).getRange() != "Single") {
						p.setTarget(eParty.get(0));
						charSelect++;
						if (charSelect == party.size()) startAnimation();
						else {
							phase = PHASE.SELECTION;
						}
					}
				else phase = PHASE.PARTYSELECTION;
				}
				
				choice = 1;
				superChoice = 1;
				
			} break;
			
		case SKILL: //CHARACTER SKILLS
			if (choice > p.getSkills().size()) choice = 0;
			else if (choice < 0) choice = p.getSkills().size();
			
			if (chosen) {
				chosen = false;
				
				if (choice == 0) {
					choice = 1;
					phase = PHASE.SELECTION;
					return;
				}
				
				p.setSkillChosen(p.getSkills().get(choice-1));
				
				switch(p.getSkillChosen().getType()) {
				case "Offensive": if (p.getSkills().get(choice-1).getRange() == "Single") {
					choice = 1;
					phase = PHASE.TARGET; return;
				} 
				else {
					p.setTarget(eParty.get(0));
					choice = 1;
					charSelect++;
				} break;
				
				case "Defensive": if (p.getSkills().get(choice-1).getRange() == "Single") {
					choice = 1;
					phase = PHASE.PARTYSELECTION; return;
				} 
				else {
					p.setFriendly(party.get(charSelect));
					choice = 1;
					charSelect++;
				} break;
				
				}
				
				if (charSelect == party.size()) startAnimation();
				else {
					phase = PHASE.SELECTION;
				}
			} break;
			
		case ITEM: //USE ITEM from INVENTORY
			
			if (choice < 0) choice = items.size();
			if (choice > items.size()) choice = 0;
			
			if (chosen) {
				chosen = false;
				
				if (choice == 0) {
					choice = 1;
					phase = PHASE.SELECTION;
					return;
				}
				
				if (Inventory.items.get(choice-1).getStock() > 0) {
					p.setItem(Inventory.items.get(choice-1));
					phase = PHASE.PARTYSELECTION;
				}
			}
			
			break;
			
		case END: //VICTORY
			if (chosen) {
				chosen = false;
				endBattle();
			}
			break;
			
		case ANIMATING: attackSequence(); break;
		default:
			break;
		}
		
		//RESET TURN
		if (phase != PHASE.ANIMATING) {
			if (reset) {
				reset = false;
				choice = 1;
				if (phase == PHASE.SELECTION) charSelect--;
				else phase = PHASE.SELECTION;
			}
		}
		
		//CHARACTER ANIMATIONS - ALWAYS RUNNING
		for (int i = 0; i < party.size(); i++) {
			party.get(i).update();
		}
	
		for (int i = 0; i < eParty.size(); i++) {
			eParty.get(i).update();
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
		
		//STAT and COMMAND WINDOW
		g.setColor(Color.BLACK);
		g.fillRect(0, h * 3/4, w, h/4);
		
		g.setFont(font);
		g.setColor(Color.WHITE);
		
		for (int i = 0; i < party.size(); i++) {
			Playable p = party.get(i);
			
			//Name
			g.drawString(p.getName(), 5 + (i * w/6), h * 3/4);
			
			//HP
			if (p.getHP() <= p.getMaxHP() / 9) g.setColor(Color.RED);
			g.drawString("HP: " + p.getHP() + " / " + p.getMaxHP(), 5 + (i * w/6), h * 3/4 + h/38);
			g.setColor(Color.WHITE);
			
			//MP / EP
			if (p.getMaxMP() == 0) g.drawString("EP: " + p.getEP() + " / " + p.getMaxEP(), 5 + (i * w/6), h * 3/4 + h/19);
			else g.drawString("MP: " + p.getMP() + " / " + p.getMaxMP(), 5 + (i * w/6), h * 3/4 + h/19);
			
			//Moves
			g.drawRect(5 + w/6 * i, h * 3/4 + h/12, w/15, h/30);
			g.drawRect(5 + w/6 * i, h * 3/4 + h/8, w/15, h/30);
			g.drawRect(5 + w/6 * i, h * 3/4 + h/6, w/15, h/30);
			
			g.drawRect(5 + w/6 * i + w/15, h * 3/4 + h/12, w/15, h/30);
			g.drawRect(5 + w/6 * i + w/15, h * 3/4 + h/8, w/15, h/30);
			g.drawRect(5 + w/6 * i + w/15, h * 3/4 + h/6, w/15, h/30);
			
			//STATUSES
			g.drawString("x" + p.getPwr()/p.getBasePwr() + "/x" + p.getMag()/p.getBaseMag() 
					+ "/" + (100 + (100 - p.getDefMod())) + "%/" + (100 + (100 - p.getMagMod())) + "%", 
					5 + w/6 * i, h * 3/4 + h/6 + 45);
			
			if (phase == PHASE.SELECTION) {
			if (choice == 1 && charSelect == i) g.setColor(Color.GREEN);
				g.drawString("Attack", 5 + w/6 * i, h * 3/4 + h/12 + h/30); g.setColor(Color.WHITE);
			if (choice == 2 && charSelect == i) g.setColor(Color.GREEN);
				g.drawString("Defend", 5 + w/6 * i, h * 3/4 + h/8 + h/30); g.setColor(Color.WHITE);
			if (choice == 3 && charSelect == i) g.setColor(Color.GREEN);
				g.drawString("Flee", 5 + w/6 * i, h * 3/4 + h/6 + h/30); g.setColor(Color.WHITE);
			
			if (choice == 4 && charSelect == i) g.setColor(Color.GREEN);
				if (p.getMaxMP() == 0) g.drawString("Tech", 5 + w/6 * i + w/15, h * 3/4 + h/12 + h/30);
				else g.drawString("Magic", 5 + w/6 * i + w/15, h * 3/4 + h/12 + h/30);
				g.setColor(Color.WHITE);
			
			if (choice == 5 && charSelect == i) g.setColor(Color.GREEN);
				g.drawString("Skill", 5 + w/6 * i + w/15, h * 3/4 + h/8 + h/30); g.setColor(Color.WHITE);
			if (choice == 6 && charSelect == i) g.setColor(Color.GREEN);
				g.drawString("Item", 5 + w/6 * i + w/15, h * 3/4 + h/6 + h/30); g.setColor(Color.WHITE);
			}
		}
		
		//ENEMY STATS
		for (int i = 0; i < eParty.size(); i++) {
			Enemy e = eParty.get(i);
			
			if (e.getHP() > 0) {
				//Name
				g.drawString(e.getName(), ((i+6) * w * 1/11), h * 3/4);
			
				//HP
				if (choice == i + 1 && phase == PHASE.TARGET) g.setColor(Color.RED);
				g.drawString("HP: " + e.getHP() + " / " + e.getMaxHP(), ((i+6) * w * 1/11), h * 3/4 + h/38);
				g.setColor(Color.WHITE);
				
				//STATUSES
				g.drawString("Pwr: x" + e.getPwr()/e.getBasePwr(), ((i+6) * w * 1/11), h * 3/4 + h/38 + 50);
				g.drawString("Mag/Eng: x" + e.getMag()/e.getBaseMag(), ((i+6) * w * 1/11), h * 3/4 + h/38 + 75);
				g.drawString("Def: " + (100 + (100 - e.getDefMod()) + "%"), ((i+6) * w * 1/11), h * 3/4 + h/38 + 100);
				g.drawString("Mag Def: " + (100 + (100 - e.getMagMod()) + "%"), ((i+6) * w * 1/11), h * 3/4 + h/38 + 125);
			}
		}
		
		//PARTY MEMBERS, SPELLS, and STATUSES
		for (int i = 0; i < party.size(); i++) {
			Playable p = party.get(i);
			
			p.render(screen);
			
			//Party Selection
			if (choice == i+1 && phase == PHASE.PARTYSELECTION) {
				g.setColor(Color.BLUE);
				g.fillOval(p.getX()*2, p.getY()*2, 15, 15);
			}
			
			//Active spells
			for (int j = 0; j < p.getOffs().size(); j++) {
				if (p.getOffs().get(j).isAnimating()) p.getOffs().get(j).animate(g);
			}
			
			for (int j = 0; j < p.getCures().size(); j++) {
				if (p.getCures().get(j).isAnimating()) p.getCures().get(j).animate(g);
			}
			
			for (int j = 0; j < p.getDefs().size(); j++) {
				if (p.getDefs().get(j).isAnimating()) p.getDefs().get(j).animate(g);
			}
			
			//Active Skills
			for (int j = 0; j < p.getSkills().size(); j++) {
				if (p.getSkills().get(j).isAnimating()) {
					p.getSkills().get(j).animate(g);
				}
			}
			
			//NEGATIVE Status Effects
			if (p.getPoisoned()) {
				g.setColor(Color.GREEN);
				g.fillOval((p.getX()+32)*2, (p.getY())*2, 12, 12);
				g.fillOval((p.getX()+40)*2, (p.getY()-5)*2, 9, 9);
				g.fillOval((p.getX()+28)*2, (p.getY()-5)*2, 7, 7);
			}
			else if (p.getCorroding()) {
				g.setColor(new Color(0xffE26B1B));
				g.fillOval((p.getX()+32)*2, (p.getY())*2, 12, 12);
				g.fillOval((p.getX()+40)*2, (p.getY()-5)*2, 9, 9);
				g.fillOval((p.getX()+28)*2, (p.getY()-5)*2, 7, 7);
			}
			
			if (p.getBurned()) {
				g.setColor(Color.RED);
				g.fillOval((p.getX()+32)*2, (p.getY())*2, 10, 10);
				g.fillOval((p.getX()+30)*2, (p.getY()+2)*2, 11, 11);
				g.fillOval((p.getX()+34)*2, (p.getY()+2)*2, 11, 11);
				g.fillOval((p.getX()+32)*2, (p.getY()+4)*2, 10, 10);
				g.fillOval((p.getX()+32)*2, (p.getY()-1)*2, 10, 10);
				g.fillOval((p.getX()+32)*2, (p.getY()-1)*2, 10, 10);
				
				g.setColor(Color.ORANGE);
				g.fillOval(((p.getX()+32)*2)+1, (p.getY()+3)*2, 7, 7);
				
				g.setColor(Color.GRAY);
				g.fillOval((p.getX()+33)*2, (p.getY()-4)*2, 7, 7);
				g.fillOval((p.getX()+35)*2, (p.getY()-6)*2, 7, 7);
				g.fillOval((p.getX()+33)*2, (p.getY()-8)*2, 7, 7);
				g.fillOval((p.getX()+35)*2, (p.getY()-10)*2, 7, 7);
			}
			
			if (p.getIll()) {
				g.setColor(Color.BLUE);
				g.fillRect((p.getX()+19)*2, (p.getY()-1)*2, 3, 15);
				g.fillRect((p.getX()+21)*2, (p.getY())*2, 3, 16);
				g.fillRect((p.getX()+23)*2, (p.getY()-1)*2, 3, 15);
			}
			
			if (p.getSleep()) {
				g.setColor(Color.WHITE);
				g.drawString("zzz...", ((p.getX()+32)*2), p.getY()*2);
			}
			
			//POSITIVE Status Effects
			if (p.getDefMod() < 100) {
				g.setColor(Color.GRAY);
				g.fillRect((p.getX()+32)*2, (p.getY())*2, 10, 64);
			}
			
			if (p.getMagMod() < 100) {
				g.setColor(Color.CYAN);
				g.fillRect((p.getX()+42)*2, (p.getY())*2, 10, 64);
			}
		}
		
		//ENEMY PARTY MEMBERS and ENEMY SPELLS
		for (int i = 0; i < eParty.size(); i++) {
			Enemy e = eParty.get(i);
			
			e.render(screen);
			
			//Targeting
			if (choice == i+1 && phase == PHASE.TARGET) {
				g.setColor(Color.RED);
				g.fillOval((e.getX()*2)-15, (e.getY()*2)+15, 15, 15);
			}
			
			//Active spells
			for (int j = 0; j < e.getOffs().size(); j++) {
				if (e.getOffs().get(j).isAnimating()) e.getOffs().get(j).animate2(g);
			}
			
			for (int j = 0; j < e.getCures().size(); j++) {
				if (e.getCures().get(j).isAnimating()) e.getCures().get(j).animate2(g);
			}
			
			for (int j = 0; j < e.getDefs().size(); j++) {
				if (e.getDefs().get(j).isAnimating()) e.getDefs().get(j).animate2(g);
			}
			
			//Active Skills
			for (int j = 0; j < e.getSkills().size(); j++) {
				if (e.getSkills().get(j).isAnimating()) e.getSkills().get(j).animate2(g);
			}
			
			//NEGATIVE Status Effects
			if (e.getPoisoned()) {
				g.setColor(Color.GREEN);
				g.fillOval((e.getX()-10)*2, (e.getY())*2, 12, 12);
				g.fillOval((e.getX()-14)*2, (e.getY()-5)*2, 9, 9);
				g.fillOval((e.getX()-4)*2, (e.getY()-5)*2, 7, 7);
			}
			else if (e.getCorroding()) {
				g.setColor(new Color(0xffE26B1B));
				g.fillOval((e.getX()-10)*2, (e.getY())*2, 12, 12);
				g.fillOval((e.getX()-14)*2, (e.getY()-5)*2, 9, 9);
				g.fillOval((e.getX()-4)*2, (e.getY()-5)*2, 7, 7);
			}
			
			if (e.getBurned()) {
				g.setColor(Color.RED);
				g.fillOval((e.getX()-10)*2, (e.getY())*2, 10, 10);
				g.fillOval((e.getX()-8)*2, (e.getY()+2)*2, 11, 11);
				g.fillOval((e.getX()-12)*2, (e.getY()+2)*2, 11, 11);
				g.fillOval((e.getX()-10)*2, (e.getY()+4)*2, 10, 10);
				g.fillOval((e.getX()-10)*2, (e.getY()-1)*2, 10, 10);
				g.fillOval((e.getX()-10)*2, (e.getY()-1)*2, 10, 10);
				
				g.setColor(Color.ORANGE);
				g.fillOval(((e.getX()-10)*2)+1, (e.getY()+3)*2, 7, 7);
				
				g.setColor(Color.GRAY);
				g.fillOval((e.getX()-10)*2, (e.getY()-3)*2, 7, 7);
				g.fillOval((e.getX()-8)*2, (e.getY()-5)*2, 7, 7);
				g.fillOval((e.getX()-10)*2, (e.getY()-7)*2, 7, 7);
				g.fillOval((e.getX()-8)*2, (e.getY()-9)*2, 7, 7);
			}
			
			if (e.getIll()) {
				g.setColor(Color.BLUE);
				g.fillRect((e.getX()+1)*2, (e.getY()-1)*2, 3, 15);
				g.fillRect((e.getX()+3)*2, (e.getY())*2, 3, 16);
				g.fillRect((e.getX()+5)*2, (e.getY()-1)*2, 3, 15);
			}
			
			if (e.getSleep()) {
				g.setColor(Color.WHITE);
				g.drawString("zzz...", ((e.getX()-12)*2), e.getY()*2);
			}
			
			//POSITIVE Status Effects
			if (e.getDefMod() < 100) {
				g.setColor(Color.GRAY);
				g.fillRect((e.getX()-18)*2, (e.getY())*2, 10, 64);
			}
			
			if (e.getMagMod() < 100) {
				g.setColor(Color.CYAN);
				g.fillRect((e.getX()-30)*2, (e.getY())*2, 10, 64);
			}
		}
		
		//DISPLAY BATTLE EFFECTS
		for (int i = 0; i < party.size(); i++) {
			Playable p = party.get(i);
			
			g.setColor(Color.RED); //DP
			if (p.getDP() < 0) g.drawString("" + p.getDP(), (p.getX()*2)-45, (p.getY()*2));
			
			g.setColor(new Color(0xffFF00FF)); //SP
			if (p.getSP() < 0) g.drawString("" + p.getSP(), (p.getX()*2)-45, (p.getY()*2)+20);
			
			g.setColor(Color.GREEN); //CP
			if (p.getCP() > 0) g.drawString("+" + p.getCP(), (p.getX()*2)-45, (p.getY()*2)+60);
			
			g.setColor(new Color(0xff0088FF)); //RP
			if (p.getRP() > 0) g.drawString("+" + p.getRP(), (p.getX()*2)-45, (p.getY()*2)+75);
			
			g.setColor(Color.WHITE); //BATTLE MESSAGE
			if (p.getMessage() != "") {
				g.drawString(p.getMessage(), (p.getX()*2)-50, (p.getY()*2)+80);
			}
		}
		
		for (int i = 0; i < eParty.size(); i++) {
			Enemy e = eParty.get(i);
			
			if (e.getDP() < 0) {
				g.setColor(Color.RED); //DP
				g.drawString("" + eParty.get(i).getDP(), (e.getX()*2) + 70, (e.getY()*2));
			}
			
			if (e.getCP() > 0) {
				g.setColor(Color.GREEN); //CP
				g.drawString("+" + eParty.get(i).getCP(), (e.getX()*2) + 70, (e.getY()*2)+50);
			}
			if (i < 3 && eParty.get(i).getMessage() != "") {
				g.setColor(Color.WHITE);
				g.drawString(eParty.get(i).getMessage(), (e.getX()*2), (e.getY()*2)+80);
			}
			
		}
		
		g.setColor(Color.WHITE);
		
		switch(phase) {
		case MAGIC:
			g.setColor(Color.BLACK);
			g.fillRect(w/3, h/38, w/2, h * 2/3);
			
			g.setColor(Color.WHITE);
			if (choice == 0) g.setColor(Color.RED);
			g.drawString("Return", w/3, h/19);
			
			ArrayList<Spell> o = party.get(charSelect).getOffs();
			ArrayList<Spell> c = party.get(charSelect).getCures();
			ArrayList<Spell> d = party.get(charSelect).getDefs();
			
			g.setColor(Color.WHITE);
			
			for (int i = 0; i < o.size(); i++) {
				if (choice == i+1 && superChoice == 1) {
					if (choice-1 == i) g.drawString(o.get(i).getDescription(), w/3 + 5, h*2/3);
					g.setColor(Color.BLUE);
				}
				g.drawString(o.get(i).getName() + " - " + o.get(i).getCost(), w/3 + 5, h/13 + (i * h/38));
				g.setColor(Color.WHITE);
				
			}
			
			for (int i = 0; i < c.size(); i++) {
				if (choice == i+1 && superChoice == 2) {
					if (choice-1 == i) g.drawString(c.get(i).getDescription(), w/3 + 5, h*2/3);
					g.setColor(Color.BLUE);
				}
				g.drawString(c.get(i).getName() + " - " + c.get(i).getCost(), w/3 + w/7, h/13 + (i * h/38));
				g.setColor(Color.WHITE);
			}
			
			for (int i = 0; i < d.size(); i++) {
				if (choice == i+1 && superChoice == 3) {
					if (choice-1 == i) g.drawString(d.get(i).getDescription(), w/3 + 5, h*2/3);
					g.setColor(Color.BLUE);
				}
				g.drawString(d.get(i).getName() + " - " + d.get(i).getCost(), w/3 + (w/7 * 2), h/13 + (i * h/38));
				g.setColor(Color.WHITE);
				
			} break;
			
		case SKILL:
			g.setColor(Color.BLACK);
			g.fillRect(w/3, h/38, w/3, h * 2/3);
		
			g.setColor(Color.WHITE);
			if (choice == 0) g.setColor(Color.RED);
			g.drawString("Return", w/3, h/19);
			
			ArrayList<Skill> s = party.get(charSelect).getSkills();
		
			g.setColor(Color.WHITE);
			for (int i = 0; i < s.size(); i++) {
				if (choice == i+1) g.setColor(Color.YELLOW);
				g.drawString(s.get(i).getName(), w/3 + 5, h/13 + (i * h/38));
				g.setColor(Color.WHITE);
				if (choice-1 == i) g.drawString(s.get(i).getDescription(), w/3 + 5, h*2/3);
			} break;
			
		case ITEM:
			g.setColor(Color.BLACK);
			g.fillRect(w/3, h/38, w/6, h*2/3);
			
			g.setColor(Color.WHITE);
			if (choice == 0) g.setColor(Color.RED);
			g.drawString("Return", w/3, h/19);
			
			g.setColor(Color.WHITE);
			for (int i = 0; i < items.size(); i++) {
				if (choice-1 == i) {
					g.drawString(items.get(i).getDescription(), w/3 + 5, h * 2/3);
					g.setColor(Color.RED);
				}
				g.drawString(items.get(i).getName() + " x" + items.get(i).getStock(), w/3 + 5, h/13 + (i * h/38));
				g.setColor(Color.WHITE);
			} break;
			
		case END:
			g.setColor(Color.BLACK);
			g.fillRect(w/3, h/38, w/3, h*2/3);
			
			g.setColor(Color.WHITE);
			int gained = eParty.get(0).getExp() * eParty.size();
			g.drawString("Experience Gained: " + eParty.get(0).getExp() + " x" + eParty.size() + " = " + gained, w/3 + 5, h/19);
			
			for (int i = 0; i < party.size(); i++) {
				Playable p = party.get(i);
				
				g.drawString(p.getName() + ", Lv. " + p.getLevel(), w/3 + 5, h/9 + (i * h/10));
				g.drawString(p.getPriorExp() + " + " + gained + " = " + (p.getPriorExp() + gained), w/3 + 5, h/7 + (i * (h/10)));
				
				g.drawString(p.getExp() + " / " + p.getNextLv(), w/3 + 5, h/6 + (i * h/10));
				if (p.getLevel() > p.getPriorLv()) {
					g.drawString("Level Up! " + p.getPriorLv() + " to " + p.getLevel(), w/3 + w/10, h/9 + (i * h/10));
				}
				
				if (p.getNewSpell() != "") {
					g.drawString("New Spell: " + p.getNewSpell(), w/3 + w/10, h/7 + (i * (h/10)));
				}
				else if (p.getNewSkill() != "") {
					g.drawString("New Skill: " + p.getNewSkill(), w/3 + w/10, h/7 + (i * (h/10)));
				}
			} 
			int mGain = eParty.get(0).getMoney() * eParty.size();
			g.drawString("Money Gained: " + mon + " + " + mGain + " = " + Party.money, w/3 + 5, h/6 + (party.size() * h/12));
			
			break;
			
		default: break;
		}
		
	}
	
	public void beginBattle() {
		phase = PHASE.SELECTION;
		choice = 1;
		chosen = false;
		
		party = Party.party;
		eParty = EnemyParty.eParty;
		
		turnNumber = 0;
		items = Inventory.items;
		mon = Party.money;
		
		if (eParty.get(0).getType() == "Boss") {
			music.playSound("Boss.wav");
		}
		else music.playSound("Battle.wav");
		
		for (int i = 0; i < party.size(); i++) {
			Playable p = party.get(i);
			p.setParty(party);
			
			p.changeCoordinates(3 * 32, (2 + (2 * i)) * 32);
			p.changeState(STATE.NORMAL);
			
			p.setPwr(p.getBasePwr());
			p.setDex(p.getBaseDex());
			p.setSpd(p.getBaseSpd());
			p.setEvd(p.getBaseEvd());
			p.setRes(p.getBaseRes());
			p.setMag(p.getBaseMag());
			p.setEng(p.getBaseEng());
			
			p.setDef(p.getBaseDef());
			p.setMagDef(p.getBaseMagDef());
			
			p.setPwrTimer(0);
			p.setDexTimer(0);
			p.setSpdTimer(0);
			p.setEvdTimer(0);
			p.setResTimer(0);
			p.setMagTimer(0);
			p.setDefTimer(0);
			p.setMagDefTimer(0);
			p.timer();
			
			p.setDP(party.get(i).getDP());
			p.setCP(-party.get(i).getCP());
			p.setRP(-party.get(i).getRP());
			p.setSP(-party.get(i).getSP());
			p.setMessage("");
			
			p.setStatusEffected();
		}
		
		for (int i = 0; i < eParty.size(); i++) {
			if (i == 3) break;
			eParty.get(i).changeCoordinates(8 * 32, ((1 + i*2)) * 32);
			eParty.get(i).setParty(eParty);
		}
		
		for (int i = 3; i < eParty.size(); i++) {
			eParty.get(i).changeCoordinates(11 * 32, ((2 + (i-3) * 2)) * 32);
			eParty.get(i).setParty(eParty);
		}
	}
	
	public String loadMap() {
		switch (Game.levelName) {
		case "Orzeik": return "BattleScreens/Orzeik.png"; 
		case "Orzeik_Sub": return "BattleScreens/Orzeik_Sub.png";
		case "Swamp": return "BattleScreens/Default_Floor.png";
		default: return "BattleScreens/Orzeik.png";
		}
	}

	//Set Order of Attack
	public void setOrder() {
		order = new int[party.size()];
		
		if (party.size() == 2) {
			if (party.get(0).getSpd() >= party.get(1).getSpd()) {
				order[0] = 0;
				order[1] = 1;
			}
			else {
				order[0] = 1;
				order[1] = 0;
			}
		}
		else if (party.size() == 3) {
			int spd0 = party.get(0).getSpd(); int spd1 = party.get(1).getSpd(); int spd2 = party.get(2).getSpd();
			
			if (spd0 >= spd1 && spd0 >= spd2) {
				order[0] = 0;
				if (spd1 >= spd2) { // 0 1 2
					order[1] = 1;
					order[2] = 2;
				}
				else { // 0 2 1
					order[1] = 2; 
					order[2] = 1; 
				}
			}
			
			else if (spd1 > spd0 && spd1 >= spd2) {
				order[0] = 1;
				if (spd0 >= spd1) { // 1 0 2
					order[1] = 0;
					order[2] = 2;
				}
				else { // 1 2 0
					order[1] = 2;
					order[2] = 0;
				}
			}
			
			else if (spd2 > spd0 && spd2 > spd1) {
				order[0] = 2;
				if (spd0 >= spd1) { // 2 0 1
					order[1] = 0;
					order[2] = 1;
				}
				else { // 2 1 0
					order[1] = 1;
					order[2] = 0;
				}
			}
		}
		else order[0] = 0;
	}
	
	public void startAnimation() {
		charSelect = 0;
		setOrder();
		enemyChoices();
		for (int i = 0; i < eParty.size(); i++) {
			eParty.get(i).setGone(false);
			eParty.get(i).setDP(eParty.get(i).getDP());
			eParty.get(i).setCP(-eParty.get(i).getCP());
			eParty.get(i).setRP(-eParty.get(i).getRP());
			eParty.get(i).setSP(eParty.get(i).getSP());
		}
		for (int i = 0; i < party.size(); i++) {
			party.get(i).setDP(party.get(i).getDP());
			party.get(i).setCP(-party.get(i).getCP());
			party.get(i).setRP(-party.get(i).getRP());
			party.get(i).setSP(party.get(i).getSP());
		}
		phase = PHASE.ANIMATING;
	}
	
	public void enemyChoices() {
		for (int i = 0; i < eParty.size(); i++) {
			Enemy e = eParty.get(i);
			
			if (e.getChoice() == 3 || e.getChoice() == 4) {
				e.setFriendly(eParty.get(random.nextInt(eParty.size())));
			}
			else if (e.getChoice() == 5) {
				e.setFriendly(eParty.get(random.nextInt(eParty.size())));
				e.setTarget(party.get(random.nextInt(party.size())));
			}
			else e.setTarget(party.get(random.nextInt(party.size())));
			
			if (e.getHP() > 0) e.behavior();
			else e.setChoice(0);
		}
	}
	
	private void changeTarget(Enemy e) {
		e.setTarget(party.get(random.nextInt(party.size())));
		if (e.getTarget().getHP() == 0) {
			changeTarget(e);
		}
	}
	
	private void changeTarget(Playable p) {
		for (int i = 0; i < eParty.size(); i++) {
			p.setTarget(eParty.get(i));
			if (eParty.get(i).getHP() > 0) return;
		}
	}
	
	private void changeFriendly(Enemy e) {
		e.setFriendly(eParty.get(random.nextInt(eParty.size())));
		for (int i = 0; i < 20; i++) {
			if (e.getFriendly().getHP() == 0) {
				e.setFriendly(eParty.get(random.nextInt(eParty.size())));
			}
			else return;
		}
		
		for (int i = 0; i < eParty.size(); i++) {
			e.setFriendly(eParty.get(i));
			if (eParty.get(i).getHP() != 0) return;
		}
	}
	
	//Animation
	public void attackSequence() {
		switch(party.size()) {
		case 1:
			if (party.get(order[0]).getChoice() != 0) {
				party.get(order[0]).startAnimation();
				move(party.get(order[0]).getChoice(), order[0]);
				party.get(order[0]).setChoice(0);
			}
			
			else if (!party.get(order[0]).isAnimating()) {
				enemyAnimations();
				endTurn();
			}
			break;
			
		case 2:
			if (party.get(order[0]).getChoice() != 0) {
				party.get(order[0]).startAnimation();
				move(party.get(order[0]).getChoice(), order[0]);
				party.get(order[0]).setChoice(0);
			}
			
			else if (party.get(order[1]).getChoice() != 0 && !party.get(order[0]).isAnimating()) {
				party.get(order[1]).startAnimation();
				move(party.get(order[1]).getChoice(), order[1]);
				party.get(order[1]).setChoice(0);
			}
			
			else if (!party.get(order[0]).isAnimating() && !party.get(order[1]).isAnimating()) {
				enemyAnimations();
				endTurn();
			} break;
			
		case 3:
			if (party.get(order[0]).getChoice() != 0) {
				party.get(order[0]).startAnimation();
				move(party.get(order[0]).getChoice(), order[0]);
				party.get(order[0]).setChoice(0);
			}
			
			else if (party.get(order[1]).getChoice() != 0 && !party.get(order[0]).isAnimating()) {
				party.get(order[1]).startAnimation();
				move(party.get(order[1]).getChoice(), order[1]);
				party.get(order[1]).setChoice(0);
			}	
			
			else if (party.get(order[2]).getChoice()!= 0 && !party.get(order[0]).isAnimating() && !party.get(order[1]).isAnimating()) {
				party.get(order[2]).startAnimation();
				move(party.get(order[2]).getChoice(), order[2]);
				party.get(order[2]).setChoice(0);
			}
			
			else if (!party.get(order[0]).isAnimating() && !party.get(order[1]).isAnimating() && !party.get(order[2]).isAnimating()) {
				enemyAnimations();
				endTurn();
			}
			break;
		}
	}
	
	public void enemyAnimations() {
		for (int i = 0; i < eParty.size(); i++) {
			Enemy e = eParty.get(i);
			
			if (e.getHP() == 0) {
				e.setGone(true);
			}
			else if (e.getChoice() != 0) {
				e.startAnimation();
				enemyMove(i);
			}
			else if (secondMove == true) {
				secondMove = false;
				changeTarget(e);
				changeFriendly(e);
				e.startAnimation();
				enemyMove(i);
			}
			
			if (!e.hasGone()) {
				return;
			}
		}
	}
	
	//Individual Move
	public void move(int move, int member) {
		Playable p = party.get(member);
		if (p.getHP() == 0) move = 0; //Check player death
		
		else if (p.getParalyzed()) { //Check paralysis
			int chance = random.nextInt(100);
			if (chance <= 33) {
				p.setMessage("Paralyzed");
				p.stopAnimation();
				return;
			}
		}
		
		Playable friend = p.getFriendly();
		
		p.setDef(p.getBaseDef());
		
		switch (move) {
		//ATTACK
		
		case 1: p.changeState(STATE.ATTACK); 
		if (p.getTarget().getHP() == 0) changeTarget(p);
		break;
		
		//DEFEND
		case 2: p.changeState(STATE.DEFEND);
		p.setDef(p.getBaseDef()*2);
		p.setDefTimer(1);
		break;
		
		//FLEE
		case 3: p.changeState(STATE.FLEE); 
		int fleeChance = eParty.get(0).getFleeChance(); int rand = random.nextInt(100);
		if (rand <= fleeChance) {
			Player.fleeing = true;
			endBattle();
			return;
		} 
		else if (eParty.get(0).getFleeChance() == 0) p.setMessage("Cannot Flee");
		else p.setMessage("Couldn't Flee");
		break;
		
		//MAGIC or TECH
		case 4: p.changeState(STATE.MAGIC);
		switch (p.getSpellChosen().getType()) {
			case "Offensive": 
			if (p.getTarget().getHP() == 0) changeTarget(p);
				
			Enemy target4 = p.getTarget();
				
			p.getSpellChosen().attack(target4);
			break;
			
			case "Curative": p.getSpellChosen().heal(friend); break;
			
			case "Defensive": p.getSpellChosen().alter(friend); break;
		}
		break;
		
		//SKILL
		case 5: p.changeState(STATE.SKILL); 
		switch (p.getSkillChosen().getType()) {
		case "Offensive": 
			if (p.getTarget().getHP() == 0) changeTarget(p);
			
			Enemy target5 = p.getTarget();
			p.getSkillChosen().attack(target5);
		case "Curative": p.getSkillChosen().heal(friend);
		case "Defensive": p.getSkillChosen().alter(friend);
		} break;
		
		//ITEM
		case 6: p.changeState(STATE.ITEM);
		break;
		
		default: break;
		}
		
		checkDeath();
	}

	public void enemyMove(int num) {
		Enemy e = eParty.get(num);
		if (e.getHP() == 0) choice = 0; //Check enemy death
		else if (e.getParalyzed()) { //Check paralysis
			int chance = random.nextInt(100);
			if (chance <= 33) {
				e.setMessage("Paralyzed");
				e.hasGone();
				e.setChoice(0);
				return;
			}
		}
		
		int choice = e.getChoice();
		if (e.getChoice() == 0 && e.getChoice2() != 0) {
			choice = e.getChoice2();
		}
		
		switch(choice) {
		
		//ATTACK
		case 1: e.changeState(STATES.ATTACK); 
		
		if (e.getTarget().getHP() == 0) {
			changeTarget(e);
		}
		
		break;
		
		//OFFENSIVE MAGIC
		case 2: e.changeState(STATES.MAGIC); 
		
		if (e.getTarget().getHP() == 0) {
			changeTarget(e);
		}
		
		Playable target2 = e.getTarget();
		e.getSpellChosen().attack(target2); 
		break;
		
		//CURATIVE MAGIC
		case 3: e.changeState(STATES.MAGIC); 
		if (e.getFriendly().getHP() == 0) {
			changeFriendly(e);
		}
		
		Enemy friend3 = e.getFriendly();
		
		e.getSpellChosen().heal(friend3); 
		friend3.setCP(e.getSpellChosen().getDmg());
		break;
		
		//DEFENSIVE MAGIC
		case 4: e.changeState(STATES.MAGIC); 
		
		e.getSpellChosen().alter(e);
		break;
			
		//SKILL
		case 5: e.changeState(STATES.SKILL); 
		Playable target5 = e.getTarget();
		Enemy friend5 = e.getFriendly();
		
		switch (e.getSkillChosen().getType()) {
		case "Offensive": 
			if (e.getTarget().getHP() == 0) {
				changeTarget(e);
			}
			e.getSkillChosen().attack(target5);
		case "Curative": e.getSkillChosen().heal(friend5);
		case "Defensive": e.getSkillChosen().alter(e);
		} break;
		
		//DEAD
		default: break;
		}
		
		checkDeath();
		
		if (e.getChoice() == 0) e.setChoice2(0);
		e.setChoice(0);
	}

	//Per Turn
	public void endTurn() {
		for (int i = 0; i < eParty.size(); i++) {
			if (!eParty.get(i).hasGone()) return;
		}
		phase = PHASE.SELECTION;
		choice = 1; chosen = false;
			
		turnNumber++;
		
		checkDeath();
		
		//Stat Timers
		for (int i = 0; i < party.size(); i++) {
			//if (party.get(i).getMaxMP() == 0 && turnNumber % 2 == 0) 
			party.get(i).setEP(1);
			party.get(i).timer();
			party.get(i).setMessage("");
		}
		for (int i = 0; i < eParty.size(); i++) {
			//if (eParty.get(i).getMaxMP() == 0 && turnNumber % 2 == 0) 
			eParty.get(i).setEP(1);
			eParty.get(i).timer();
			eParty.get(i).setMessage("");
			}
		checkEffects();
	}
	
	public void checkEffects() {
		for (int i = 0; i < party.size(); i++) {
			Playable p = party.get(i);
			
			//Status Effects
			if (p.getPoisoned()) {
				if ((p.getHP() * 5)/100 == 0) {
					p.setDP(1);
					p.setHP(-1);
				}
				else {
					p.setDP((p.getHP() * 5)/100);
					p.setHP(-(p.getHP() * 5)/100);
				}
			}
			if (p.getCorroding()) { 
				if ((p.getHP() * 5)/100 == 0) {
					p.setDP(1);
					p.setHP(-1);
				}
				else {
					p.setDP((p.getHP() * 5)/100);
					p.setHP(-(p.getHP() * 5)/100);
				}
			}
			if (p.getBurned()) {
				if (p.getHP()/10 == 0) {
					p.setDP(1);
					p.setHP(-1);
				}
				else {
					p.setDP(p.getHP()/10);
					p.setHP(-p.getHP()/10);
				}
			}
			if (p.getIll()) {
				if (p.getMaxMP() == 0) {
					p.setEP(-1);
					p.setSP(1);
				}
				else {
					if ((p.getMaxMP()*5)/100 == 0) {
						p.setSP(1);
						p.setMP(-1);
					}
					else {
						p.setSP((p.getMP() * 5)/100);
						p.setMP(-(p.getMP() * 5)/100);
					}
				}
			}
			if (p.getSleep()) {
				int chance = random.nextInt(5);
				if (chance == 0) p.setSleep(false);
			}
			
			p.setStatusEffected();
			
			//Regen
			if (p.getRegen() > 0) {
				p.setHP(p.getRegen());
				p.setCP(p.getRegen());
			}
		}
		
		for (int i = 0; i < eParty.size(); i++) {
			Enemy e = eParty.get(i);
			if (e.getPoisoned()) {
				if ((e.getHP() * 5)/100 == 0) {
					e.setDP(1);
					e.setHP(-1);
				}
				else {
					e.setDP((e.getHP() * 5)/100);
					e.setHP(-(e.getHP() * 5)/100);
				}
			}
			if (e.getCorroding()) { 
				if ((e.getHP() * 5)/100 == 0) {
					e.setDP(1);
					e.setHP(-1);
				}
				else {
					e.setDP((e.getHP() * 5)/100);
					e.setHP(-(e.getHP() * 5)/100);
				}
			}
			if (e.getBurned()) {
				if (e.getHP()/10 == 0) {
					e.setDP(1);
					e.setHP(-1);
				}
				else {
					e.setDP(e.getHP()/10);
					e.setHP(-e.getHP()/10);
				}
			}
			if (e.getIll()) {
				if (e.getMaxMP() == 0) {
					e.setEP(-1);
					e.setSP(1);
				}
				else {
					if ((e.getMaxMP()*5)/100 == 0) {
						e.setSP(1);
						e.setMP(-1);
					}
					else {
						e.setSP((e.getMP() * 5)/100);
						e.setMP(-(e.getMP() * 5)/100);
					}
				}
			}
			if (e.getSleep()) {
				int chance = random.nextInt(5);
				if (chance == 0) e.setSleep(false);
			}
			
			e.setStatusEffected();
			
			//Regen
			if (e.getRegen() > 0) {
				e.setHP(e.getRegen());
				e.setCP(e.getRegen());
			}
		}
	}
	
	public void checkDeath() {
		boolean dead = true;
		boolean enemiesDead = true;
		
		for (int i = 0; i < eParty.size(); i++) {
			if (eParty.get(i).getHP() > 0) {
				enemiesDead = false;
			}
		}
		for (int i = 0; i < party.size(); i++) {
			if (party.get(i).getHP() > 0) {
				dead = false;
			}
		}
		
		if (enemiesDead) victory();
		else if (dead) endGame();
	}
	
	//END BATTLE
	public void victory() {
		phase = PHASE.END;
		chosen = false;
		music.playSound("Victory.wav");
		
		Party.money += eParty.get(0).getMoney() * eParty.size();
		
		for (int i = 0; i < party.size(); i++) {
			if (party.get(i).getHP() > 0) party.get(i).changeState(STATE.VICTORY);
			party.get(i).setPriorLv(); party.get(i).setPriorExp();
			party.get(i).gainExp(eParty.get(0).getExp() * eParty.size());
		}
		
		for (int i = 0; i < eParty.size(); i++) {
			eParty.get(i).remove();
		}
	}
	
	public void endBattle() {
		Game.State = Game.STATE.GAME;
		SaveState.loadLevel2();
		music.changeMusic();
		eParty.get(0).remove();
		secondMove = false;
		for (int i = 0; i < party.size(); i++) {
			Playable p = party.get(i);
			p.changeState(STATE.NORMAL);
			p.setNewSpell();
			p.setPoisoned(false);
			p.setCorroding(false);
			p.setBurned(false);
			p.setIll(false);
			p.setSleep(false);
			p.setParalyzed(false);
		}
	}
	
	public void endGame() {
		Game.State = Game.STATE.DEAD;
	}
	
}
