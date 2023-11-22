package org.game.items;

import org.game.state.GameState;

public class ConsumableItem extends Item {
	
	String name;
	int healingPotency;
	
	public ConsumableItem(String name, int healingPotency) {
		super(name);
		this.healingPotency = healingPotency;
	}

	@Override
	public void applyEffect(GameState gameState) {
		int initialHealth = gameState.getCharacter().getHealthPoints();
		gameState.getCharacter().setHealthPoints(initialHealth + healingPotency);
		if (gameState.getItems().contains(this)) {
			gameState.getItems().remove(this);
		}
	}

}
