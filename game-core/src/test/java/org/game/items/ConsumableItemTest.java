package org.game.items;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.game.state.GameCharacter;
import org.game.state.GameState;
import org.junit.jupiter.api.Test;

public class ConsumableItemTest {
	
	@Test
	public void testApplyEffect_potionInInventory() {
		ConsumableItem potion = new ConsumableItem("Potion of Healing", 5);
		GameState gameState = new GameState();
		GameCharacter gameCharacter = new GameCharacter();
		gameCharacter.setHealthPoints(5);
		gameState.setCharacter(gameCharacter);
		gameState.getItems().add(potion);
		
		assertEquals(1, gameState.getItems().size());
		potion.applyEffect(gameState);
		assertEquals(10, gameState.getCharacter().getHealthPoints());
		assertEquals(0, gameState.getItems().size());
	}
	
	@Test
	public void testApplyEffect_potionInInventory_doesNotRemoveOtherItem() {
		ConsumableItem potion0 = new ConsumableItem("Potion of Invisibility", 0);
		ConsumableItem potion = new ConsumableItem("Potion of Healing", 5);
		JunkItem junkItem = new JunkItem("Old Boot");
		ConsumableItem potion2 = new ConsumableItem("Potion of Firebreathing", 0);
		
		GameState gameState = new GameState();
		GameCharacter gameCharacter = new GameCharacter();
		gameCharacter.setHealthPoints(5);
		gameState.setCharacter(gameCharacter);
		gameState.getItems().add(potion0);
		gameState.getItems().add(potion);
		gameState.getItems().add(junkItem);
		gameState.getItems().add(potion2);
		
		assertEquals(4, gameState.getItems().size());
		potion.applyEffect(gameState);
		assertEquals(3, gameState.getItems().size());
		assertEquals("Potion of Invisibility", gameState.getItems().get(0).getName());
		assertEquals("Old Boot", gameState.getItems().get(1).getName());
		assertEquals("Potion of Firebreathing", gameState.getItems().get(2).getName());
	}

}
