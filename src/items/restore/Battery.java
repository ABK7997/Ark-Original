package items.restore;

import characters.Playable;
import items.Item;

public class Battery extends Item {

	public Battery() {
		name = "Battery";
		type = "Recharge";
		effect = 5;
		cost = 250;
		index = 8;
		description = "Restores 5 EP";
	}
	
	public void useEffect(Playable p) {
		p.setEP(effect);
		p.setRP(effect);
	}
	
}
