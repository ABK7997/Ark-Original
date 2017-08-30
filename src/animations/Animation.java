package animations;

import java.util.ArrayList;

import states.Screen;
import entity.mobs.npcs.NPC;

public class Animation {

	protected int x, y, range;
	protected int anim;
	protected int block = 32;
	protected boolean animating = false;
	protected boolean pre = false; //Prerequesites for the cutscene to start
	protected boolean finished = false;
	
	protected ArrayList<NPC> characters = new ArrayList<NPC>();
	
	public void update() {
	}
	
	public void render(Screen screen) {
		for (int i = 0; i < characters.size(); i++) {
			characters.get(i).render(screen);
		}
	}
	
	public void animation() {
	}
	
	//Animation
	public boolean isAnimating() {
		return animating;
	}
	public boolean isFinished() {
		return finished;
	}
	public boolean getPre() {
		return pre;
	}
	public void setPre() {
		pre = true;
	}
	public void startAnimation() {
	}
	
	//Location
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public int getRange() {
		return range;
	}
	
}
