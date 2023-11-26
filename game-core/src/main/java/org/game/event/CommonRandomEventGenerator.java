package org.game.event;

import java.util.Random;

import org.game.encounter.Encounter;
import org.game.encounter.EncounterFactory;
import org.game.items.CommonItemGenerator;
import org.game.items.ItemGeneratorFactory;

public class CommonRandomEventGenerator implements RandomEventGenerator {

	@Override
	public Encounter rollEncounter() {
		Encounter encounter = null;
		
		if (this.getRandomNumber() <= 2 ) {
			encounter = new EncounterFactory().generateCommonItemEncounter();
		}  else {
			encounter = new EncounterFactory().generateNonEventEncounter();
		}
		return encounter;
	}
	
	protected int getRandomNumber() {
		Random random = new Random();
		int randomResult = random.nextInt(1, 10);
		return randomResult;
	}

}
