package org.game.encounters;

import org.game.items.Item;
import org.game.items.ItemGenerator;
import org.game.messenging.UserMessenger;
import org.game.state.GameState;

public class ItemEncounter implements Encounter {
	
	protected static final String PICK_UP_ITEM = "You picked up a ";
	protected static final String PERIOD = ".";
	
	private ItemGenerator itemGenerator;
	private UserMessenger userMessenger;

	@Override
	public void resolveEncounter(GameState gameState) {
		Item item = itemGenerator.rollForRandomItem();
		gameState.getItems().add(item);
		userMessenger.notifyUser(PICK_UP_ITEM + item.getName() + PERIOD);
	}

	public ItemGenerator getItemGenerator() {
		return itemGenerator;
	}

	public void setItemGenerator(ItemGenerator itemGenerator) {
		this.itemGenerator = itemGenerator;
	}
	
	public UserMessenger getUserMessenger() {
		return userMessenger;
	}

	public void setUserMessenger(UserMessenger userMessenger) {
		this.userMessenger = userMessenger;
	}

}
