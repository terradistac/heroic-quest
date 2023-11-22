package org.game.items;

import org.game.state.GameState;

public abstract class Item {
	
	public Item (String name) {
		this.name = name;
	}
	
	String name;
	
	public abstract void applyEffect(GameState gameState);
	
	public void addToInventory(GameState gameState) {
		gameState.getItems().add(this);
	}
	
	public String getName() {
		return this.name;
	}

}
