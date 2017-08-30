package music;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import main.Game;

public class Music_Player {

	private static Clip clip;
	
	public Music_Player() {
	}
	
	public void changeMusic() {
		stopSound(); 
			switch (Game.levelName) {
			case "World_Map": playSound("Main Theme.wav"); break;
			
			case "Orzeik": playSound("Orzeik.wav"); break;
			
			case "Orzeik_Sub": playSound("Orzeik.wav"); break;
			case "Trench": playSound("Trench.wav"); break;
			case "Trench_Sub": playSound("Trench.wav"); break;
			case "Swamp": playSound("Swamp.wav"); break;
			case "Swamp_Sub": playSound("Swamp.wav"); break;
			case "Mortisia": playSound("Mortisia.wav"); break;
			case "Mortisia_Sub": playSound("Mortisia.wav"); break;
			case "Crystal Cave": playSound("Crystal Cave.wav"); break;
			default: break;
			}
	}
	
	public void mainTheme() {
		stopSound(); 
		playSound("Main Theme.wav");
	}
	
	public void playSound(String file) {
		stopSound();
		try {
			URL defaultSound = getClass().getResource(file);
		    AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(defaultSound);
		    clip = AudioSystem.getClip();
		    clip.open(audioInputStream);
		    clip.loop(Clip.LOOP_CONTINUOUSLY);
		    clip.start( );
		} catch(Exception e) {
			System.out.println("Error with playing sound.");
	        e.printStackTrace();
		}
	}
	
	public static void stopSound() {
		if (clip != null) {
			clip.stop(); 
			clip.close();
		}
	}
	
}
