package org.game.items;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.game.state.GameState;
import org.junit.jupiter.api.Test;

public class ItemTest {
	
	private class TestItem extends Item {

		public TestItem(String name) {
			super(name);
		}

		@Override
		public void applyEffect(GameState gameState) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	@Test
	public void testAddToInventory() {
		GameState gameState = new GameState();
		TestItem item = new TestItem("Test");
		
		assertEquals(0, gameState.getItems().size());
		
		item.addToInventory(gameState);
		
		assertEquals(1, gameState.getItems().size());
		assertEquals("Test", gameState.getItems().get(0).getName());
	}

}
