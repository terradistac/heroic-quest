package org.game.encounter;

import org.game.items.CommonItemGenerator;
import org.game.items.ItemGeneratorFactory;
import org.game.messenging.UserMessenger;
import org.game.messenging.UserMessengerFactory;

public class EncounterFactory {
	
	protected UserMessenger userMessenger;
	
	public Encounter generateCommonItemEncounter() {
		
		ItemEncounter encounter = new ItemEncounter();
		encounter.setUserMessenger(getUserMessenger());
		
		ItemGeneratorFactory itemGeneratorFactory = new ItemGeneratorFactory();
		CommonItemGenerator itemGenerator = itemGeneratorFactory.createCommonItemGenerator();
		
		encounter.setItemGenerator(itemGenerator);
		return encounter;
	}
	
	public Encounter generateNonEventEncounter() {
		return new NonEventEncounter();
	}
	
	public UserMessenger getUserMessenger() {
		if (this.userMessenger == null) {
			this.userMessenger =  new UserMessengerFactory().generateSystemOutUserMessenger();
		}
		return this.userMessenger;
	}

}
