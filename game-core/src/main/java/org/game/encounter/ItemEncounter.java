package org.game.encounter;

import org.game.items.Item;
import org.game.items.ItemGenerator;
import org.game.messenging.UserMessenger;
import org.game.state.GameState;

public class ItemEncounter implements Encounter {
	
	protected static final String FOUND_ITEM = "You found a ";
	protected static final String PERIOD = ".";
	
	private ItemGenerator itemGenerator;
	private UserMessenger userMessenger;

	@Override
	public void resolveEncounter() {
		Item item = itemGenerator.rollForRandomItem();
		GameState.getInstance().getFoundItems().add(item);
		userMessenger.notifyUser(FOUND_ITEM + item.getName() + PERIOD);
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
