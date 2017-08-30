package main;

import input.Player;
import items.Inventory;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import levels.central.Orzeik;
import levels.central.Orzeik_Sub;
import levels.south.*;
import levels.Level;
import levels.PartyCamp;
import levels.WorldMap;
import music.Music_Player;
import characters.Party;
import characters.Playable;

public class SaveState {

	public static Music_Player music = new Music_Player();
	public static String[] saveList = new String[] {};
	public static int lastSave = 0;
	
	public SaveState() {
	}
	
	public static void loadLevel() {
		switch(Game.levelName) {
		case "World_Map": Game.level = new WorldMap(); break;
		case "PartyCamp": Game.level = new PartyCamp(); break;
		
		case "Orzeik": Game.level = new Orzeik(); break;
		case "Orzeik_Sub": Game.level = new Orzeik_Sub(); break;
		
		case "Trench": Game.level = new Trench(); break;
		case "Trench_Sub": Game.level = new Trench_Sub(); break;
		
		case "Swamp": Game.level = new Swamp(); break;
		case "Swamp_Sub": Game.level = new Swamp_Sub(); break;
		
		case "Mortisia": Game.level = new Mortisia(); break;
		case "Mortisia_Sub": Game.level = new Mortisia_Sub(); break;
		
		case "Crystal Cave": Game.level = new Crystal_Cave(); break;
		
		default: break;
		}
		
		Game.level.loadAssets();
		Player.level = Game.level;
		Game.State = Game.STATE.GAME;
	}
	
	public static void loadLevel2() {
		switch(Game.levelName) {
		case "World_Map": Game.level = new WorldMap(); break;
		
		case "Orzeik": Game.level = new Orzeik(); break;
		case "Orzeik_Sub": Game.level = new Orzeik_Sub(); break;
		
		case "Trench": Game.level = new Trench(); break;
		case "Trench_Sub": Game.level = new Trench_Sub(); break;
		
		case "Swamp": Game.level = new Swamp(); break;
		case "Swamp_Sub": Game.level = new Swamp_Sub(); break;
		
		case "Mortisia": Game.level = new Mortisia(); break;
		case "Mortisia_Sub": Game.level = new Mortisia_Sub(); break;
		
		case "Crystal Cave": Game.level = new Crystal_Cave(); break;
		
		default: break;
		}
		
		Player.level = Game.level;
		Game.State = Game.STATE.GAME;
	}
	
	/*
	public static void changeArea(String area, int dir) {
		Game.levelName = area;
		loadLevel();
		switch(dir) {
		case 0: 
			Player.x = Game.level.getNorth()[0] << 5;
			Player.y = Game.level.getNorth()[1] << 5;
			break;
		case 1: 
			Player.x = Game.level.getEast()[0] << 5;
			Player.y = Game.level.getEast()[1] << 5;
			break;
		case 2: 
			Player.x = Game.level.getSouth()[0] << 5;
			Player.y = Game.level.getSouth()[1] << 5;
			break;
		case 3: 
			Player.x = Game.level.getWest()[0] << 5;
			Player.y = Game.level.getWest()[1] << 5;
			break;
		default: break;
		}
		
		music.changeMusic();
	} */
	
	public static void changeArea(int dir) {
		loadLevel();
		switch(dir) {
		case 0: 
			Player.x = Game.level.getNorth()[0] << 5;
			Player.y = Game.level.getNorth()[1] << 5;
			break;
		case 1: 
			Player.x = Game.level.getEast()[0] << 5;
			Player.y = Game.level.getEast()[1] << 5;
			break;
		case 2: 
			Player.x = Game.level.getSouth()[0] << 5;
			Player.y = Game.level.getSouth()[1] << 5;
			break;
		case 3: 
			Player.x = Game.level.getWest()[0] << 5;
			Player.y = Game.level.getWest()[1] << 5;
			break;
		default: break;
		}
		
		music.changeMusic();
	}
	
	//CREATE SAVE FILES
	public static void createSave(int num) {
		saveList = new String[num + 1];
		
		for (int i = 0; i < saveList.length; i++) {
			saveList[i] = "SavedGame" + i + ".sav";
		}
		
		saveList[num] = "SavedGame" + num + ".sav";
		
		saveGame(num);
	}
	
	//STORE SAVE FILES
	public static void saveSaves() {
		try {
			FileOutputStream saveFile = new FileOutputStream("Saves.sav");
			ObjectOutputStream save = new ObjectOutputStream(saveFile);
			
			save.writeObject(saveList);
			save.writeObject(lastSave);
			
			save.close();
		}
		catch (IOException e) {
			System.out.println("Could not store Save Files");
			e.printStackTrace();
		}
	}
	
	public static void loadSaves() {
		try {
			FileInputStream saveFile = new FileInputStream("Saves.sav");
			ObjectInputStream restore = new ObjectInputStream(saveFile);
			
			saveList = (String[]) restore.readObject();
			lastSave = (int) restore.readObject();
			
			restore.close();
		}
		catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("Could not load Save Files");
			
		}
	}
	
	//SAVE DATA
	public static void saveGame(int num) {
		lastSave = num;
		
		try {
			FileOutputStream saveFile = new FileOutputStream("SavedGame" + num + ".sav");
			
			ObjectOutputStream save = new ObjectOutputStream(saveFile); //stream to save objects to indicate save file
			
			//Info to Save
			save.writeObject(Player.x);
			save.writeObject(Player.y);
			
			//Party Stats
			
			save.writeObject(Party.partyConfiguration);
			save.writeObject(Party.partyAvailable);
			save.writeObject(Party.money);
			
			for (int i = 0; i < Party.wholeParty.size(); i++) {
				Playable p = Party.wholeParty.get(i);
				
				save.writeObject(p.getLevel());
				save.writeObject(p.getExp());
				save.writeObject(p.getNextLv());
				
				save.writeObject(p.getHP());
				save.writeObject(p.getMP());
				save.writeObject(p.getEP());
				
				save.writeObject(p.getMaxHP());
				save.writeObject(p.getMaxMP());
				save.writeObject(p.getMaxEP());
				
				save.writeObject(p.getBasePwr());
				save.writeObject(p.getBaseDex());
				save.writeObject(p.getBaseSpd());
				save.writeObject(p.getBaseEvd());
				save.writeObject(p.getBaseRes());
				save.writeObject(p.getBaseMag());
				save.writeObject(p.getBaseDef());
				save.writeObject(p.getBaseMagDef());
			}
			
			//Level and Story info
			save.writeObject(Game.levelName);
			save.writeObject(Level.levelCoordinates);
			
			save.writeObject(Story.mainStory);
			
			save.writeObject(Story.orzeik);
			save.writeObject(Story.c2);
			save.writeObject(Story.c3);
			save.writeObject(Story.c4);
			save.writeObject(Story.c5);
			save.writeObject(Story.c6);
			save.writeObject(Story.c7);
			
			save.writeObject(Story.trench);
			save.writeObject(Story.dropZone);
			save.writeObject(Story.swamp);
			save.writeObject(Story.mortisia);
			save.writeObject(Story.crystalCave);
			
			save.writeObject(Story.bashada);
			save.writeObject(Story.n2);
			save.writeObject(Story.n3);
			save.writeObject(Story.n4);
			
			save.writeObject(Story.e1);
			save.writeObject(Story.e2);
			save.writeObject(Story.e3);
			save.writeObject(Story.e4);
			
			save.writeObject(Story.machina);
			save.writeObject(Story.badlands);
			save.writeObject(Story.jurgo);
			save.writeObject(Story.w4);
			save.writeObject(Story.w5);
			
			//Inventory
			Inventory.saveStock();
			save.writeObject(Inventory.itemsFound);
			save.writeObject(Inventory.itemStocks);
			
			//Party Camp Location
			save.writeObject(PartyCamp.mapCoordinatesN);
			save.writeObject(PartyCamp.mapCoordinatesS);
			
			save.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		saveSaves();
	}
	
	public static void loadGame(int num) {
		lastSave = num;
		
		try {
			FileInputStream saveFile = new FileInputStream("SavedGame" + num + ".sav");
			ObjectInputStream restore = new ObjectInputStream(saveFile);
			
			//Info to Save
			Player.x = (int) restore.readObject();
			Player.y = (int) restore.readObject();
			
			//Party Stats
			Party.partyConfiguration = (int[]) restore.readObject();
			Party.partyAvailable = (int[]) restore.readObject();
			Party.money = (int) restore.readObject();
			
			for (int i = 0; i < Party.wholeParty.size(); i++) {
				Playable p = Party.wholeParty.get(i);
				
				p.restoreLv((int) restore.readObject());
				p.restoreExp((int) restore.readObject());
				p.restoreNextLv((int) restore.readObject());
				
				p.restoreHP((int) restore.readObject());
				p.restoreMP((int) restore.readObject());
				p.restoreEP((int) restore.readObject());
				
				p.restoreMaxHP((int) restore.readObject());
				p.restoreMaxMP((int) restore.readObject());
				p.restoreMaxEP((int) restore.readObject());
				
				p.restoreBasePwr((int) restore.readObject());
				p.restoreBaseDex((int) restore.readObject());
				p.restoreBaseSpd((int) restore.readObject());
				p.restoreBaseEvd((int) restore.readObject());
				p.restoreBaseRes((int) restore.readObject());
				p.restoreBaseMag((int) restore.readObject());
				p.restoreBaseDef((int) restore.readObject());
				p.restoreBaseMagDef((int) restore.readObject());
			}
			
			//Story and Level elements
			Game.levelName = (String) restore.readObject();
			Level.levelCoordinates = (int[]) restore.readObject();
			
			Story.mainStory = (int) restore.readObject();
			
			Story.orzeik = (int) restore.readObject();
			Story.c2 = (int) restore.readObject();
			Story.c3 = (int) restore.readObject();
			Story.c4 = (int) restore.readObject();
			Story.c5 = (int) restore.readObject();
			Story.c6 = (int) restore.readObject();
			Story.c7 = (int) restore.readObject();
			
			Story.trench = (int) restore.readObject();
			Story.dropZone = (int) restore.readObject();
			Story.swamp = (int) restore.readObject();
			Story.mortisia = (int) restore.readObject();
			Story.crystalCave = (int) restore.readObject();
			
			Story.bashada = (int) restore.readObject();
			Story.n2 = (int) restore.readObject();
			Story.n3 = (int) restore.readObject();
			Story.n4 = (int) restore.readObject();
			
			Story.e1 = (int) restore.readObject();
			Story.e2 = (int) restore.readObject();
			Story.e3 = (int) restore.readObject();
			Story.e4 = (int) restore.readObject();
			
			Story.machina = (int) restore.readObject();
			Story.badlands = (int) restore.readObject();
			Story.jurgo = (int) restore.readObject();
			Story.w4 = (int) restore.readObject();
			Story.w5 = (int) restore.readObject();
			
			//Inventory
			Inventory.itemsFound = (boolean[]) restore.readObject();
			Inventory.itemStocks = (int[]) restore.readObject();
			
			//Party Camp Coordinates
			loadLevel(); 
			PartyCamp.mapCoordinatesN = (int[]) restore.readObject();
			PartyCamp.mapCoordinatesS = (int[]) restore.readObject();
			
			restore.close();
			
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		Party.restoreParty();
		for (int i = 0; i < Party.party.size(); i++) {
			Party.party.get(i).restoreSpells();
		}
		SaveState.music.changeMusic();
		Inventory.restoreItems();
	}
	
	//ERASE DATA
	public static void eraseSaves() {
		saveList = new String[] {};
		lastSave = 0;
		saveSaves();
		loadSaves();
	}
}
