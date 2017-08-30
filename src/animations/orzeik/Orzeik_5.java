package animations.orzeik;

import input.Player;
import levels.Level;
import main.Game;
import main.SaveState;
import main.Story;
import states.Dialogue;
import animations.Animation;
import entity.mobs.enemies.bosses.Security_Mech;
import entity.mobs.npcs.friends.Orzy_NPC;
import entity.mobs.npcs.villains.Warden;

public class Orzeik_5 extends Animation {

	//SECURITY MECH BOSS FIGHT
	public Orzeik_5() {
		x = 53 << 5;
		y = 13 << 5;
		range = 1 << 5;
		pre = true;
		characters.add(new Warden(48, 12, beforeBattle));
	}
	
	public void update() {
		if (Story.orzeik == 5) {
			
			for (int i = 0; i < characters.size(); i++) {
				characters.get(i).update();
			}
			
			if (anim == 0) {
				Player.x = 54 << 5;
				Player.y = 13 << 5;
			}
		
			anim++;
		
			if (anim < 3 * block/2) {
				Player.ya = 2; Player.xa = -2;
			}
			else if (anim < 6 * block/2) {
				Player.ya = 0; Player.xa = -2;
			}
			else if (anim < 7 * block/2) {
				Player.xa = 0; Player.ya = -2;
			}
			else if (anim == 112) {
				Game.State = Game.STATE.DIALOGUE;
				Dialogue.dialogue = beforeBattle;
			}
			else if (anim == 113) {
				Level.add(new Security_Mech(48, 14, 1, 1));
				Game.State = Game.STATE.GAME;
			}
			else if (anim == 115) {
				characters.add(new Orzy_NPC(57, 13, beforeBattle));
				Game.State = Game.STATE.DIALOGUE;
				Dialogue.dialogue = afterBattle;
			}
			else if (anim < 8 * block/2) {
				Player.ya = -1;
			}
			else if (anim < 9 * block/2) {
				Player.ya = 6;
			}
			else if (anim == 9 * block/2) {
				Player.ya = -1;
				Game.State = Game.STATE.DIALOGUE;
				Dialogue.dialogue = leash;
			}
			else if (anim < 16 * block/2) {
				Game.State = Game.STATE.ANIMATING;
				Player.ya = 0;
				characters.get(1).moveX(-2);
			}
			else if (anim < 17 * block/2) {
				characters.get(1).moveY(2);
			}
			else if (anim == 272) {
				Game.State = Game.STATE.DIALOGUE;
				Dialogue.dialogue = orzy;
			}
			else {
				animating = false;
				Story.mainStory = 2;
				Player.x = 60 * 32;
				Player.y = 71 * 32;
				SaveState.loadLevel();
				Game.State = Game.STATE.GAME;
				Story.orzeik++;
			}
		}
		else Game.State = Game.STATE.GAME;
	}
	
	public void startAnimation() {
		Game.State = Game.STATE.ANIMATING;
		animating = true;
		finished = true;
	}
	
	private String[] beforeBattle = new String[] {
		"Warden: Well done, Ark! Bashada is proud of you.",
		"Ark: Shut up! Why did you do this to me?",
		"Warden: We need elite soldiers, Ark. Ordinary humans aren't enough anymore.",
		"Ark: All just to harvest blood from Agickans?",
		"Warden: Don't try to make it sound so simple, Ark.",
		"You've already seen for yourself what magic can do.",
		"And it is the only thing that can save you from THIS!"
	};
	
	private String[] afterBattle = new String[] {
		"Warden: Do you see the potential, Ark?",
		"Your powers are weak, but they manage to do so much.",
		"This is what fighting Agickans is like. Hopeless...",
		"Ark: I have no interest in being a part of your army.",
		"With this power I can finally break out of Orzeik!"
	};
	
	private String[] leash = new String[] {
		"Ark: Agh! What is this?",
		"I can't feel my limbs anymore!",
		"Warden: You didn't honestly think that I would give you magic without restraint?",
		"Just under the skin of your back neck is a device that paralyzes you when I push this button.",
		"Ark: AAAAAAAAAH!",
		"Warden: Oh, and it hurts a lot, too.",
		"But I'm willing to play nice, Ark.",
		"Ark: What do you mean?",
		"Warden: There's something I need done.",
		"Something that Bashada needs to be done.",
		"You are the first successful experiment in human magic. You might be able to do it.",
		"Ark: What do you want from me?",
		"Warden: You see, harvesting blood from Agickans is actually rather inefficient.",
		"We need a stronger source of magic to sustain our projects.",
		"Ark: What do you need me for then?",
		"Warden: The magic source is a kind of crystal found in the Mortisian Swamps.",
		"Our harvesters can't penetrate the swamp's density, and it's toxic to humans.",
		"But YOU, Ark, your newfound magical blood will protect you from the toxins.",
		"Just as the Agickans suffer no ill effects from it.",
		"If you do this, you'll be free from having to work as a factory laborer forever.",
		"Ark: And I'm supposed to do this alone?",
		"Warden: Oh, I wouldn't dream of it!"
	};
	
	private String[] orzy = new String[] {
		"Orzy: Unit reporting as requested, Warden.",
		"Ark: This is a bad joke, right?",
		"Warden: This is Orzy. He will be accompanying you.",
		"Ark: I repeat: This is a joke.",
		"Orzy: I have detailed files on the Mortisian Swamps.",
		"My companionship will increase your odds of survival by 83%.",
		"Ark: Goody goody.",
		"Warden: Orzy is one of the only machines to ever survive a mission to the swamps.",
		"He is essential.",
		"Ark: He's a watchdog. He makes sure I don't run off.",
		"Warden: That's not untrue.",
		"Ark: So... let's assume I do complete this mission...",
		"How do I know you'll set me free?",
		"Warden: Ha ha ha... You don't.",
		"Orzy: Your options are limited.",
		"Warden: Indeed.",
		"So Ark? ... What will it be?",
		"I could, alternatively, have you locked up for research instead?",
		"Ark: You've made your point...",
		"When do we leave? I was sick of this place anyway..."
	};
	
}
