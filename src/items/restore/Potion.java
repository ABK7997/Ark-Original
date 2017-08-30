package items.restore;

import characters.Playable;
import items.Item;

public class Potion extends Item {

	public Potion() {
		name = "Potion";
		type = "Cure";
		effect = 250;
		cost = 100;
		index = 0;
		description = "Restores 250 HP";
	}
	
	public void useEffect(Playable p) {
		p.setHP(effect);
		p.setCP(effect);
	}
	
}
