package states;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import levels.Level;
import main.Game;

public class Dialogue {

	public static boolean chosen = false;
	
	private Font font = new Font("Times New Roman", Font.BOLD, 24);
	private int w = Game.frame.getWidth();
	private int h = Game.frame.getHeight();
	
	public static String[] dialogue;
	public static int num = 0;
	
	public Dialogue() {
	}
	
	public void update() {
		if (chosen) {
			chosen = false;
			num++;
			if (num == dialogue.length) {
				closeDialogue();
			}
		}
	}
	
	public void render(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(w/12, h * 2/3, w * 5/6, h * 2/9);
		
		g.setFont(font);
		g.setColor(Color.WHITE);
		g.drawString(dialogue[num], w/12 + 15, h * 2/3 + 40);
		
		if (chosen) g.setColor(Color.RED);
		g.drawString("-->", w * 5/6, h*2/3 + 100);
	}
	
	public void closeDialogue() {
		num = 0;
		
		for (int i = 0; i < Level.cutscenes.size(); i ++) {
			if (Level.cutscenes.get(i).isAnimating()) {
				Game.State = Game.STATE.ANIMATING; return;
			}
		}
		Game.State = Game.STATE.GAME;
		chosen = false;
	}
	
}
