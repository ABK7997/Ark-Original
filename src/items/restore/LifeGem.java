package items.restore;

import characters.Playable;
import items.Item;

public class LifeGem extends Item {

	public LifeGem() {
		name = "Life Gem";
		type = "Cure";
		effect = 0;
		cost = 2000;
		index = 20;
		description = "Revives fallen ally";
	}
	
	public void useEffect(Playable p) {
		p.restoreHP(p.getMaxHP() / 5);
		p.setCP(p.getMaxHP() / 5);
	}
	
}
