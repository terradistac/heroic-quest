package org.game.user;

import java.util.Scanner;

import org.game.action.inventory.BasicInventoryManager;
import org.game.action.inventory.MessengerInventoryManagerWrapper;
import org.game.items.Item;
import org.game.messenging.SystemOutUserMessenger;
import org.game.messenging.UserMessenger;
import org.game.state.GameState;

public class FoundItemUserOptions implements UserOptions {

	@Override
	public void provideAvailableUserInputOptions() {
		UserMessenger messenger = new SystemOutUserMessenger();
		MessengerInventoryManagerWrapper inventoryManager = new MessengerInventoryManagerWrapper(new BasicInventoryManager());
		inventoryManager.setUserMessenger(messenger);
		
		Scanner sc = new Scanner(System.in);
		String input = "";
		for (Item foundItem : GameState.getInstance().getFoundItems()) {
			messenger.notifyUser("Keep the " + foundItem.getName() + "? Y/N");
			input = sc.next();
			if (("Y").equalsIgnoreCase(input)) {
				inventoryManager.addToInventory(foundItem);
			} else {
				messenger.notifyUser("You discarded the " + foundItem.getName());
			}
		}
		GameState.getInstance().getFoundItems().clear();
	}

}
