package org.game.encounters;

import org.game.items.ItemGenerator;
import org.game.items.ItemGeneratorFactory;
import org.game.state.GameState;

public class ItemEncounter implements Encounter {
	
	private ItemGenerator itemGenerator;

	@Override
	public void resolveEncounter(GameState gameState) {
		ItemGeneratorFactory itemGeneratorFactory = new ItemGeneratorFactory();
		itemGenerator = itemGeneratorFactory.createCommonItemGenerator();
		
		gameState.getItems().add(itemGenerator.rollForRandomItem());
	}

}
