package org.game.encounters;

import java.util.Random;

import org.game.items.CommonItemGenerator;
import org.game.items.ItemGeneratorFactory;

public class CommonRandomEncounterGenerator implements RandomEncounterGenerator {

	@Override
	public Encounter rollEncounter() {
		Encounter encounter = null;
		
		if (this.getRandomNumber() <= 2 ) {
			encounter = new EncounterFactory().generateCommonItemEncounter();
		} 
		return encounter;
	}
	
	protected int getRandomNumber() {
		Random random = new Random();
		int randomResult = random.nextInt(1, 10);
		return randomResult;
	}

}
