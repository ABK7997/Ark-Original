package states;

import items.Inventory;
import items.Item;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;

import characters.Party;
import main.Game;

public class Store {

	public static boolean chosen = false;
	public static boolean reset = false;
	public static int choice = 0;
	private Font font = new Font("Times New Roman", Font.BOLD, 24);
	private static ArrayList<Item> in = Inventory.items;
	
	private int w = Game.frame.getWidth();
	private int h = Game.frame.getHeight();
	
	private enum STATES {
		MAIN, BUY, SELL
	}
	
	private String[] options = new String[] {
		"LEAVE",
		"BUY",
		"SELL"
	};
	
	private STATES state = STATES.MAIN;
	
	public Store() {
	}
	
	public void update() {
		switch (state) {
		
		case MAIN: 
			if (choice < 0) choice = 2;
			else if (choice > 2) choice = 0;
			
			if (chosen) {
				chosen = false;
				switch (choice) {
				case 0: Game.State = Game.STATE.GAME; choice = 1; break;
				case 1: state = STATES.BUY; choice = 0; break;
				case 2: state = STATES.SELL; choice = 0; break;
				}
			}
			
			else if (reset) {
				Game.State = Game.STATE.GAME; choice = 1;
			}
			
			break;
		
		case BUY:
			if (choice < 0) choice = in.size();
			else if (choice > in.size()) choice = 0;
			
			if (chosen) {
				chosen = false;
				
				if (choice == 0) {
					choice = 0;
					state = STATES.MAIN;
				}
				else {	
					if (Party.money >= in.get(choice-1).getCost()) {
						Party.money -= in.get(choice-1).getCost();
						Inventory.items.get(choice-1).addItem();
					}
				}
			}
			
			else if (reset) {
				reset = false;
				choice = 0;
				state = STATES.MAIN;
			}
			
			break;
		
		case SELL: 
			if (choice < 0) choice = Inventory.items.size();
			else if (choice > Inventory.items.size()) choice = 0;
			
			if (chosen) {
				chosen = false;
				
				if (choice == 0) {
					choice = 0;
					state = STATES.MAIN;
				}
				
				else {	
					if (in.get(choice-1).getStock() > 0) {
						Party.money += in.get(choice-1).getSell();
						in.get(choice-1).useItem();
					}
				}
			}
			
			else if (reset) {
				reset = false;
				choice = 0;
				state = STATES.MAIN;
			}
			
			break;
		
		default: break;
		}
	}
	
	public void render(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, w, h);
		
		g.setColor(Color.WHITE);
		g.setFont(font); 
		
		switch(state) {
		
		case MAIN:
			for (int i = 0; i < options.length; i++) {
				if (choice == i) g.setColor(Color.RED);
				g.drawString(options[i], w/6, h/6 + (40 * i));
				g.setColor(Color.WHITE);
			}
			break;
		
		case BUY:
			g.setColor(Color.WHITE);
			
			if (choice == 0) g.setColor(Color.RED);
			g.drawString("Return", w/6, h/6);
			g.setColor(Color.WHITE);
			
			g.drawString("STORE", w/6, h/6 - 50);
			for (int i = 0; i < in.size(); i++) {
				if (choice - 1 == i) g.setColor(Color.RED);
				g.drawString(in.get(i).getName() + " - " + in.get(i).getCost(), w/6, h/6 + ((i+1) * 30));
				g.setColor(Color.WHITE);
			}
			
			g.drawString("INVENTORY", w * 2/3, h/6-50);
			for (int i = 0; i < in.size(); i++) {
				g.drawString(in.get(i).getName() + " x" + in.get(i).getStock(), w*2/3, h/6 + (i * 30));
				g.setColor(Color.WHITE);
			}
			
			g.drawString("Money: $" + Party.money, w*2/3, h/6 + ((in.size()+1)*30));
			if (choice > 0 && choice < in.size()+1) g.drawString(in.get(choice-1).getDescription(), w*1/3, h/6 + ((choice) * 30));
			
			break;
			
		case SELL:
			g.setColor(Color.WHITE);
			
			if (choice == 0) g.setColor(Color.RED);
			g.drawString("Return", w/6, h/10 - 50);
			g.setColor(Color.WHITE);
			
			for (int i = 0; i < Inventory.items.size(); i++) {
				if (choice - 1 == i) g.setColor(Color.RED);
				g.drawString(in.get(i).getName() + " x" + in.get(i).getStock() + " " + " - $" + in.get(i).getSell(), w/6, h/10 + (i * 30));
				g.setColor(Color.WHITE);
			}
			
			g.drawString("Money: $" + Party.money, w/6 , h/6 + (in.size()*30));
			
			break;
			
		
		default: break;
		}
	}
	
	public static void openStore() {
		in = Inventory.items;
		reset = false;
	}
	
}
