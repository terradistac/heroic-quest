package org.game.encounters;

import org.game.items.CommonItemGenerator;
import org.game.items.ItemGeneratorFactory;

public class EncounterFactory {
	
	public Encounter generateCommonItemEncounter() {
		ItemEncounter encounter = new ItemEncounter();
		ItemGeneratorFactory itemGeneratorFactory = new ItemGeneratorFactory();
		CommonItemGenerator itemGenerator = itemGeneratorFactory.createCommonItemGenerator();
		
		encounter = new ItemEncounter();
		encounter.setItemGenerator(itemGenerator);
		return encounter;
	}

}
