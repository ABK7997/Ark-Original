package characters;

import input.Player;

import java.util.ArrayList;

public class Party {

	public static ArrayList<Playable> party = new ArrayList<Playable>();
	public static ArrayList<Playable> partyPresent = new ArrayList<Playable>();
	public static ArrayList<Playable> wholeParty = new ArrayList<Playable>();
	
	public static int[] partyConfiguration = new int[] {1, 0, 0};
	public static int[] partyAvailable = new int[] {1, 0, 0, 0, 0, 0};
	
	public static Ark ark = new Ark();
	public static Orzy orzy = new Orzy();
	public static Dex dex = new Dex();
	public static Jorg jorg = new Jorg();
	public static Zee zee = new Zee();
	public static Ven ven = new Ven();
	
	public static int money = 85;
	
	public Party() {
		restoreParty();
		
		//changeParty(ark);
		//addMember(ark); 
		//addMember(orzy);
		//addMember(dex);
		//addMember(jorg);
		//addMember(zee);
		//addMember(ven);
		
		wholeParty.add(ark);
		wholeParty.add(orzy);
		wholeParty.add(dex);
		wholeParty.add(jorg);
		wholeParty.add(zee);
		wholeParty.add(ven);
	}
	
	//CHANGE PARTY CONFIGURATION
	public static void changeParty(Playable p1) {
		Player.changeSprites(p1);
		party = new ArrayList<Playable>();
		party.add(p1);
		partyConfiguration = new int[] {p1.getIndex()};
	}
	
	public static void changeParty(Playable p1, Playable p2) {
		Player.changeSprites(p1);
		party = new ArrayList<Playable>();
		party.add(p1);
		party.add(p2);
		partyConfiguration = new int[] {p1.getIndex(), p2.getIndex()};
	}
	
	public static void changeParty(Playable p1, Playable p2, Playable p3) {
		Player.changeSprites(p1);
		party = new ArrayList<Playable>();
		party.add(p1);
		party.add(p2);
		party.add(p3);
		partyConfiguration = new int[] {p1.getIndex(), p2.getIndex(), p3.getIndex()};
	}
	
	public static void addMember(Playable p) {
		partyPresent.add(p);
		for (int i = 0; i < partyAvailable.length; i++) {
			if (partyAvailable[i] != 0) continue;
			else {
				partyAvailable[i] = p.getIndex();
				return;
			}
		}
	}
	
	public static void removeMember(Playable p) {
		partyPresent.remove(p);
		for (int i = 0; i < partyAvailable.length; i++) {
			if (partyAvailable[i] == p.getIndex()) {
				partyAvailable[i] = 0;
			}
		}
	}
 	
	public static void restoreParty() {
		party = new ArrayList<Playable>();
		for (int i = 0; i < partyConfiguration.length; i++) {
			switch (partyConfiguration[i]) {
			case 1: party.add(ark); break;
			case 2: party.add(orzy); break;
			case 3: party.add(dex); break;
			case 4: party.add(jorg); break;
			case 5: party.add(zee); break;
			case 6: party.add(ven); break;
			default: break;
			}
		}
		
		partyPresent = new ArrayList<Playable>();
		for (int i = 0; i < partyAvailable.length; i++) {
			switch(partyAvailable[i]) {
			case 1: partyPresent.add(ark); break;
			case 2: partyPresent.add(orzy); break;
			case 3: partyPresent.add(dex); break;
			case 4: partyPresent.add(jorg); break;
			case 5: partyPresent.add(zee); break;
			case 6: partyPresent.add(ven); break;
			default: break;
			}
		}
	}
 	
}
