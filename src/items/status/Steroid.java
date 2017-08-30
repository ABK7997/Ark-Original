package items.status;

import characters.Playable;
import items.Item;

public class Steroid extends Item {

	public Steroid() {
		name = "Steroid";
		type = "Stat";
		cost = 500;
		index = 14;
		description = "Cures Paralysis";
	}
	
	public void useEffect(Playable p) {
		p.setParalyzed(false);
	}
	
}
