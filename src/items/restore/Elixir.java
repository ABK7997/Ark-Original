package items.restore;

import characters.Playable;
import items.Item;

public class Elixir extends Item {

	public Elixir() {
		name = "Elixir";
		type = "Elixir";
		effect = 9999;
		cost = 15000;
		index = 17;
		description = "Restores all HP and MP";
	}
	
	public void useEffect(Playable p) {
		p.setHP(9999);
		p.setCP(9999);
		if (p.getMaxMP() == 0) {
			p.setRP(99);
			p.setEP(99);
		}
		else {
			p.setRP(999);
			p.setMP(999);
		}
	}
	
}
