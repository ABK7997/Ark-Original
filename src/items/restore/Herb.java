package items.restore;

import characters.Playable;
import items.Item;

public class Herb extends Item {

	public Herb() {
		name = "Herb";
		type = "Ether";
		effect = 50;
		cost = 500;
		index = 4;
		description = "Restores 50 MP";
	}
	
	public void useEffect(Playable p) {
		p.setMP(effect);
		p.setRP(effect);
	}
	
}
