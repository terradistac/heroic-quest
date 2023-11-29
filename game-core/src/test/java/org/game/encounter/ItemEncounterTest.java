package org.game.encounter;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.easymock.EasyMock;
import org.game.items.ConsumableItem;
import org.game.items.ItemGenerator;
import org.game.messenging.UserMessenger;
import org.game.state.GameState;
import org.junit.jupiter.api.Test;

public class ItemEncounterTest {

	@Test
	public void testResolveEncounter() {
		UserMessenger userMessenger = EasyMock.createMock(UserMessenger.class);
		ItemGenerator itemGenerator = EasyMock.createMock(ItemGenerator.class);
		
		EasyMock.expect(itemGenerator.rollForRandomItem()).andReturn(new ConsumableItem("Potion", 5));
		EasyMock.replay(itemGenerator);
		
		ItemEncounter itemEncounter = new ItemEncounter();
		itemEncounter.setItemGenerator(itemGenerator);
		itemEncounter.setUserMessenger(userMessenger);
		GameState gameState = GameState.getInstance();
		
		userMessenger.notifyUser(ItemEncounter.FOUND_ITEM + "Potion" + ItemEncounter.PERIOD);
		EasyMock.replay(userMessenger);
		assertEquals(0, gameState.getFoundItems().size());
		
		itemEncounter.resolveEncounter();
		
		assertEquals(1, gameState.getFoundItems().size());
		EasyMock.verify(userMessenger);
	}
}
