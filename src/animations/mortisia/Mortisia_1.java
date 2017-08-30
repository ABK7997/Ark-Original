package animations.mortisia;

import states.Dialogue;
import input.Player;
import main.Game;
import main.Story;
import animations.Animation;
import entity.mobs.npcs.friends.Dex_NPC;
import entity.mobs.npcs.friends.Orzy_NPC;

public class Mortisia_1 extends Animation {

	//Another Day at the Factory
	public Mortisia_1() {
		x = 33 << 5;
		y = 33 << 5;
		range = 3 << 5;
		pre = true;
	}
	
	public void update() {
		if (Story.crystalCave == 1) {
			for (int i = 0; i < characters.size(); i++) {
				characters.get(i).update();
			}
			
			if (anim == 0) {
				Player.x = 34 << 5;
				Player.y = 33 << 5;
				characters.add(new Orzy_NPC(33, 34, null));
				characters.add(new Dex_NPC(35, 34, null));
				characters.get(0).moveX(1);
				characters.get(1).moveX(-1);
			}
			
			anim++;
			
			if (anim < 2 * block);
			
			else if (anim == 2 * block) {
				Game.State = Game.STATE.DIALOGUE;
				Dialogue.dialogue = dialogue1;
			}
			
			else {
				Story.mainStory = 4;
				Game.State = Game.STATE.GAME;
				Story.crystalCave = 2;
				animating = false;
			}	
		}
		else Game.State = Game.STATE.GAME;
	}
	
	public void startAnimation() {
		animating = true;
		finished = true;
	}
	
	//Dialogue
	private String[] dialogue1 = new String[] {
		"ACT II: THE FRUIT OF INDUSTRY",
		"",
		"Ark: So what now? We're obviously not getting past Crysalis.",
		"Orzy: There are alternative crystal sources.",
		"Ark: You know, I was really hoping that you would just say, ",
		"'Let's give up and go home, fellas.'",
		"Orzy: Unacceptable. The mission must be completed.",
		"Dex: And I still want some crystals anyway.",
		"Ark: Ugh... fine, so where is the next best place for crystals?",
		"Orzy: An abandoned mine in the western districts.",
		"Ark: Abandoned? Why didn't we just go there in the first place?",
		"Orzy: The mine has been unstable for years. It is off-limits.",
		"Ark: But the whole point of sending me was because I was immune to magical illness.",
		"What's the point of us going to a regular mine? We could use some help.",
		"Orzy: The mine is unstable because magical faults broke through the rock.",
		"It is still toxic. Otherwise, the empire would have continued operations.",
		"Dex: Of course they would have...",
		"Ark: You haven't learned anything about your empire yet, have you?",
		"How will you feel when they toss you out after you expire?",
		"Orzy: ... Refusal to respond.",
		"Ark: Well, that's cheap.",
		"Dex: We're wasting time.",
		"Let's just get moving.",
		"Um... what's the best way there?",
		"Orzy: The Drop-Zone overpass on the west side of the river.",
		"Dex: Uh, the WHAT-zone?",
		"Ark: It's a narrow straight with sharp cliffs.",
		"You don't want to fall into those deadly water. Should be fun.",
		"Dex: Can't be worse than the swamplands.",
		"Ark: You wanna bet?"
	};
	
}
