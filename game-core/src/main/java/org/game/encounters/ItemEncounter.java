package org.game.encounters;

import org.game.items.ItemGenerator;
import org.game.items.ItemGeneratorFactory;
import org.game.state.GameState;

public class ItemEncounter implements Encounter {
	
	private ItemGenerator itemGenerator;

	@Override
	public void resolveEncounter(GameState gameState) {		
		gameState.getItems().add(itemGenerator.rollForRandomItem());
	}
	
	public ItemGenerator getItemGenerator() {
		return itemGenerator;
	}

	public void setItemGenerator(ItemGenerator itemGenerator) {
		this.itemGenerator = itemGenerator;
	}

}
