package org.game.encounters;

import org.game.items.ConsumableItem;
import org.game.state.GameState;

public class ItemEncounter implements Encounter {

	@Override
	public void resolveEncounter(GameState gameState) {
		this.addPotion(gameState);
	}
	
	private void addPotion(GameState gameState) {
		gameState.getItems().add(new ConsumableItem("Potion of Healing", 5));
	}

}
