package org.game.event;

import org.game.encounter.Encounter;

public interface RandomEventGenerator {
	
	public Encounter rollEncounter();

}
