package items.status;

import characters.Playable;
import items.Item;

public class Antidote extends Item {

	public Antidote() {
		name = "Antidote";
		type = "Stat";
		cost = 100;
		index = 11;
		description = "Cures Poison";
	}
	
	public void useEffect(Playable p) {
		p.setPoisoned(false);
	}
	
}
