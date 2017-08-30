package animations.swamp;

import characters.Party;
import states.Dialogue;
import main.Game;
import main.Story;
import input.Player;
import entity.mobs.npcs.friends.Dex_NPC;
import entity.mobs.npcs.friends.Orzy_NPC;
import animations.Animation;

public class Swamp_0 extends Animation {

	//Another Day at the Factory
	public Swamp_0() {
		x = 28 << 5;
		y = 20 << 5;
		range = 1 << 5;
		pre = true;
	}
	
	public void update() {
		if (Story.swamp == 0) {
			for (int i = 0; i < characters.size(); i++) {
				characters.get(i).update();
			}
			
			if (anim == 0) {
				Player.x = 28 << 5;
				Player.y = 20 << 5;
				characters.add(new Dex_NPC(28, 30, orzy));
			}
			
			anim++;
			
			if (anim < (2 * block)) {
				Player.ya = 2;
			}
			
			else if (anim == 2 * block) {
				Player.ya = 0;
				characters.add(new Orzy_NPC(28, 24, orzy));
				characters.get(1).turn(2);
				Player.changeSprites(Party.ark);
			}
			
			else if (anim < 3 * block) {
				characters.get(1).moveX(-1);
			}
			else if (anim == 3 * block) {
				characters.get(1).moveY(1);
				Game.State = Game.STATE.DIALOGUE;
				Dialogue.dialogue = orzy;
			}
			
			else if (anim < 8 * block) {
				characters.get(0).moveY(-1);
			}
			else if (anim == 8 * block) {
				Game.State = Game.STATE.DIALOGUE;
				Dialogue.dialogue = dex;
			}
			else if (anim < 9 * block) {
				characters.get(0).moveY(-1, 1);
				characters.get(1).moveX(1, 2);
			}
			else {
				characters.remove(1); characters.remove(0);
				animating = false;
				Game.State = Game.STATE.GAME;
				Story.swamp++;
				Party.changeParty(Party.ark, Party.orzy, Party.dex);
				Party.addMember(Party.dex);
			}
		}
		else Game.State = Game.STATE.GAME;
	}
	
	public void startAnimation() {
		animating = true;
		finished = true;
	}
	
	//Dialogue
	private String[] orzy = new String[] {"Orzy: Someone is here.", 
			"I'm detecting their presence."
	};
	
	private String[] dex = new String[] {"Stranger: And right when I was about to ambush you.", 
			"Stupid machine ruined it.",
			"Ark: Ambush us?",
			"Orzy: Machines are forbidden from entering Mortisian territories.",
			"Ark: Great. Now I'm implicit in illegal activities.",
			"Stranger: As if the Bashadans cared about what was legal in our territory.",
			"What are you doing here trespassing?",
			"You're obviously too small to be a harvesting party.",
			"And you, robot! Aren't you an industrial drone?",
			"Orzy: Affirmative.",
			"Stranger: Then what are you doing here?",
			"Ark: Searching for something. Now please leave.",
			"Stranger: Ha! That means you're looking for crystals!",
			"When the big Harvesters fail, send in something smaller.",
			"Definitely less suspicious that way, I guess.",
			"I know where there are crystals. Big ones.",
			"Ark: Why tell us about it?",
			"Stranger: Use of the crystals is also forbidden in Mortisia, even by us.",
			"Ark: I can see a bit of greed in your eyes.",
			"Stranger: Right on the money, my friend.",
			"We'll fly under the radar of the Mortisians to the Crystal Cave.",
			"Then we can all have our fill.",
			"Ark: What do you need us for?",
			"Stranger: It's filled to the entrance with monsters, that's why.",
			"And nobody here is crazy enough to break the rules... or risk dying.",
			"Orzy: I have analyzed her pupils and body rhythms for irregularities.",
			"I do not think she is trying to trick us.",
			"Stranger: So, not so stupid after all...",
			"Well, there you have it I guess. Team?",
			"Ark: We are NOT a team, but if it means getting out of here faster...",
			"Fine.",
			"Orzy: Do not try to ambush us again.",
			"Stranger: Well, that's a given.",
			"The name's Dex by the way. Pleased to meet'cha.",
	};
	
}
