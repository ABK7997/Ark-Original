package items.restore;

import characters.Playable;
import items.Item;

public class SuperPotion extends Item {

	public SuperPotion() {
		name = "Super Potion";
		type = "Cure";
		effect = 1000;
		cost = 500;
		index = 1;
		description = "Restores 1000 HP";
	}
	
	public void useEffect(Playable p) {
		p.setHP(effect);
		p.setCP(effect);
	}
	
}
