package input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import states.Battle;
import states.Dead;
import states.Dialogue;
import states.Main;
import states.Map;
import states.Start;
import states.Menu;
import states.Store;
import levels.PartyCamp;
import main.Game;
import main.SaveState;
import main.Story;

public class Keyboard implements KeyListener {

	private boolean[] keys = new boolean[120];
	public boolean up, down, left, right;
	
	//Keys
	private final int l = 37;
	private final int u = 38;
	private final int r = 39;
	private final int d = 40;
	
	private final int v = 86;
	private final int c = 67;
	private final int x = 88;
	private final int z = 90;
	
	private final int esc = 27;
	
	public void update() {
		up = keys[KeyEvent.VK_W] || keys[KeyEvent.VK_UP];
		down = keys[KeyEvent.VK_S] || keys[KeyEvent.VK_DOWN];
		left = keys[KeyEvent.VK_A] || keys[KeyEvent.VK_LEFT];
		right = keys[KeyEvent.VK_D] || keys[KeyEvent.VK_RIGHT];
		
		if (Game.State == Game.STATE.ANIMATING) {
			up = false;
			down = false;
			left = false;
			right = false;
		}
	}
	
	public void keyPressed(KeyEvent e) {	
		keys[e.getKeyCode()] = true;
		
		int k = e.getKeyCode();
		
		//System.out.println(k);
		
		switch(Game.State) {
		case GAME:
			switch(k) {
			case esc: Game.State = Game.STATE.MAIN; Game.main.openMain(); break;
			case x: Game.State = Game.STATE.MENU; Menu.openMenu(); return;
			case v: Player.chosen = true; return;
			case z: 
				if (Game.levelName == "World_Map" && Story.mainStory > 3) {
					int x = Player.x >> 5;
					int y = Player.y >> 5;
					Player.x = 19 << 5;
					Player.y = 12 << 5;
					Game.levelName = "PartyCamp";
					SaveState.loadLevel();
					PartyCamp.mapCoordinatesN = new int[] {x, y};
					PartyCamp.mapCoordinatesS = new int[] {x, y};
				}
				break;
			
			default: break;
			} break;
			
		case MENU:
			switch(k) {
			case x: Game.State = Game.STATE.GAME; break;
			case v: Menu.chosen = true; return;
			case c: Menu.reset = true; return;
			
			case u: if (!Menu.spells || Menu.selecting) Menu.choice--;
			else if (!Menu.selecting) Menu.spellChoice--; break; 
			
			case d: if (!Menu.spells || Menu.selecting) Menu.choice++;
			else if (!Menu.selecting) Menu.spellChoice++; break; 
			
			case l: if (Menu.spells) Menu.superChoice--;
			else Menu.choice--;break; 
			
			case r: if (Menu.spells) Menu.superChoice++;
			else Menu.choice++;
			} break; 
			
		case BATTLE:
			if (Battle.phase == Battle.PHASE.MAGIC) {
				switch(k) {
				case v: Battle.chosen = true; break;
				
				case u: Battle.choice--; break;
				case d: Battle.choice++; break;
				case l: Battle.superChoice--; break;
				case r: Battle.superChoice++; break;
				
				case c: Battle.reset = true; break;
				}
			}
			
			else {
				switch(k) {
			
				case v: Battle.chosen = true; break;
				
				case u: Battle.choice--; break;
				case d: Battle.choice++; break;
				case l: Battle.choice -= 3; break;
				case r: Battle.choice += 3; break;
				
				case c: Battle.reset = true; break;
			
				default: break;
				}
			}
			break;
			
		case STORE:
			switch(k) {
			case v: Store.chosen = true; break;
			case c: Store.reset = true; break;
			
			case u: Store.choice--; break;
			case d: Store.choice++; break;
			case l: Store.choice--; break;
			case r: Store.choice++; break;
			} break;
			
		case START:
			switch(k) {
			case v: Start.chosen = true; break;
			case c: Start.reset = true; break;
			
			case u: Start.choice--; break;
			case d: Start.choice++; break;
			case l: Start.choice--; break;
			case r: Start.choice++; break;
			
			} break;
			
		case MAIN: 
			switch(k) {
			case esc: Game.State = Game.STATE.GAME; Main.choice = 1; break;
			case c: Main.reset = true; break;
			
			case v: Main.chosen = true; break;
			
			case u: Main.choice--; break;
			case d: Main.choice++; break;
			case l: Main.choice--; break;
			case r: Main.choice++; break;
			
			} break;
			
		case DEAD: 
			switch(k) {
			case v: Dead.chosen = true; break;
			
			case u: Dead.choice--; break;
			case d: Dead.choice++; break;
			case l: Dead.choice--; break;
			case r: Dead.choice++; break;
			
			} break;
			
		case MAP: 
			switch(k) {
			case v: Map.chosen = true; break;
			
			case u: Map.y--; break;
			case d: Map.y++; break;
			case l: Map.x--; break;
			case r: Map.x++; break;
			
			} break;
			
		case DIALOGUE:
			
			if (k == v) Dialogue.chosen = true; break;
			
		default: break;
		}
	}

	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;
	}

	public void keyTyped(KeyEvent e) { //UNUSED
	}

}
