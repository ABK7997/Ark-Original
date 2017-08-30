package items.status;

import characters.Playable;
import items.Item;

public class Polish extends Item {

	public Polish() {
		name = "Polish";
		type = "Stat";
		cost = 100;
		index = 13;
		description = "Heals Corrosion";
	}
	
	public void useEffect(Playable p) {
		p.setCorroding(false);
	}
	
}
