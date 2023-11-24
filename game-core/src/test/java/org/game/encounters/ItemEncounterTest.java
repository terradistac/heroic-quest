package org.game.encounters;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.game.items.CommonItemGenerator;
import org.game.state.GameState;
import org.junit.jupiter.api.Test;

public class ItemEncounterTest {

	@Test
	public void testResolveEncounter() {
		ItemEncounter itemEncounter = new ItemEncounter();
		itemEncounter.setItemGenerator(new CommonItemGenerator());
		GameState gameState = new GameState();
		
		assertEquals(0, gameState.getItems().size());
		
		itemEncounter.resolveEncounter(gameState);
		
		assertEquals(1, gameState.getItems().size());
	}
}
