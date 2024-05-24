package org.game.user.terminal;

import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.game.action.inventory.BasicInventoryManager;
import org.game.action.inventory.MessengerInventoryManagerWrapper;
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
		MessengerInventoryManagerWrapper inventoryManager = new MessengerInventoryManagerWrapper(
				new BasicInventoryManager());
		inventoryManager.setUserMessenger(messenger);
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
		if (inputMatchesEquip(input)) {
			int number = getItemNumber(input) - 1;
			Item item;
			if (isNumberInInventory(number, inventoryItems)) {
				item = inventoryItems.get(number);
				if (item.getClass().equals(EquipmentItem.class)) {
					inventoryManager.equipItem((EquipmentItem) item);
				} else {
					messenger.notifyUser("You cannot equip that item.");
				}
			}
		}
		if (inputMatchesUnequip(input)) {
			int number = getItemNumber(input) - 1;
			if (isNumberInInventory(number, inventoryItems)) {
				inventoryManager.unequipItem((EquipmentItem) inventoryItems.get(number));
			}
		}
	}

	protected boolean inputMatchesEquip(String input) {
		Pattern pattern = Pattern.compile("^equip\\s\\d", Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(input);
		return matcher.find();
	}

	protected boolean inputMatchesUnequip(String input) {
		Pattern pattern = Pattern.compile("^unequip\\s\\d", Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(input);
		return matcher.find();
	}

	protected int getItemNumber(String input) {
		Pattern pattern = Pattern.compile("\\d+");
		Matcher matcher = pattern.matcher(input);
		matcher.find();
		return Integer.valueOf(input.substring(matcher.start(), matcher.end()));
	}

	protected boolean isNumberInInventory(int number, List<Item> inventoryItems) {
		return inventoryItems.get(number) != null;
	}

}
