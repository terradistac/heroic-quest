package org.game.user.terminal;

import java.util.List;
import java.util.Scanner;

import org.game.items.EquipmentItem;
import org.game.items.Item;
import org.game.messenging.UserMessenger;
import org.game.messenging.UserMessengerFactory;
import org.game.state.GameState;
import org.game.user.UserOptions;

public class InventorySystemOutUserOptions implements UserOptions {

	@Override
	public void provideAvailableUserInputOptions() {
		UserMessenger messenger = new UserMessengerFactory().generateSystemOutUserMessenger();
		Scanner sc = SystemOutUserInterface.getScanner();
		String input = "";
		
		messenger.notifyUser("You have the following items in your inventory:");
		
		List<Item> inventoryItems = GameState.getInstance().getCharacter().getInventory();
		List<EquipmentItem> equippedItems = GameState.getInstance().getCharacter().getEquippedItems();
		
		int numberInList = 0;
		for (Item item : inventoryItems) {
			numberInList = numberInList + 1;
			String message = numberInList + ": " + item.getName();
			if (equippedItems.contains(item)) {
				message.concat(" (Equipped)");
			}
			messenger.notifyUser(message);
		}
		
		input = sc.next();
		if (input.equalsIgnoreCase("exit")) {
			messenger.notifyUser("You closed your bag.");
			SystemOutUserInterface.setUserOptions(new DefaultSystemOutUserOptions());	
		}
		if (input.equalsIgnoreCase("help")) {
			messenger.notifyUser("Type \"Equip [number]\" to equip.");
			messenger.notifyUser("Type \"Unequip [number]\" to unequip.");
			messenger.notifyUser("Type \"exit\" to exit the inventory screen.");
		}
	}

}
