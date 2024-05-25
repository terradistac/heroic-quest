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

	private boolean openingMessageSent = false;
	private Scanner sc;
	private UserMessenger messenger;
	private MessengerInventoryManagerWrapper inventoryManager;
	private BasicInventoryManager basicInventoryManager;
	private List<Item> inventoryItems = GameState.getInstance().getCharacter().getInventory();
	private List<EquipmentItem> equippedItems = GameState.getInstance().getCharacter().getEquippedItems();

	@Override
	public void provideAvailableUserInputOptions() {
		String input = "";

		if (!openingMessageSent) {
			this.getUserMessenger().notifyUser("You have the following items in your inventory: \nType \"Help\" for available commands.");
	
			int numberInList = 0;
			for (Item item : inventoryItems) {
				numberInList = numberInList + 1;
				String message = numberInList + ": " + item.getName();
				if (equippedItems.contains(item)) {
					message.concat(" (Equipped)");
				}
				this.getUserMessenger().notifyUser(message);
			}
			openingMessageSent = true;
		}

		input = this.getScanner().nextLine();
		if (input.equalsIgnoreCase("exit")) {
			this.getUserMessenger().notifyUser("You closed your bag.");
			SystemOutUserInterface.setUserOptions(new DefaultSystemOutUserOptions());
		}
		if (input.equalsIgnoreCase("help")) {
			this.getUserMessenger().notifyUser("Type \"Equip [number]\" to equip.");
			this.getUserMessenger().notifyUser("Type \"Unequip [number]\" to unequip.");
			this.getUserMessenger().notifyUser("Type \"exit\" to exit the inventory screen.");
		}
		if (inputMatchesEquip(input)) {
			int number = getItemNumber(input) - 1;
			Item item;
			if (isNumberInInventory(number, inventoryItems)) {
				item = inventoryItems.get(number);
				if (item.getClass().equals(EquipmentItem.class)) {
					getInventoryManagerWrapper().equipItem((EquipmentItem) item);
				} else {
					this.getUserMessenger().notifyUser("You cannot equip that item.");
				}
			}
		}
		if (inputMatchesUnequip(input)) {
			int number = getItemNumber(input) - 1;
			if (isNumberInInventory(number, inventoryItems)) {
				getInventoryManagerWrapper().unequipItem((EquipmentItem) inventoryItems.get(number));
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

	protected void setScanner(Scanner scanner) {
		this.sc = scanner;
	}

	public Scanner getScanner() {
		if (this.sc == null) {
			sc = SystemOutUserInterface.getScanner();
		}
		return this.sc;
	}

	public MessengerInventoryManagerWrapper getInventoryManagerWrapper() {
		if (this.inventoryManager == null) {
			MessengerInventoryManagerWrapper inventoryManager = new MessengerInventoryManagerWrapper(
					this.getBasicInventoryManager());
			inventoryManager.setUserMessenger(getUserMessenger());
			this.inventoryManager = inventoryManager;
		}
		return this.inventoryManager;
	}

	protected void setInventoryManagerWrapper(MessengerInventoryManagerWrapper inventoryManager) {
		this.inventoryManager = inventoryManager;
	}

	public BasicInventoryManager getBasicInventoryManager() {
		if (this.basicInventoryManager == null) {
			this.basicInventoryManager = new BasicInventoryManager();
		}
		return this.basicInventoryManager;
	}

	protected void setBasicInventoryManager(BasicInventoryManager basicInventoryManager) {
		this.basicInventoryManager = basicInventoryManager;
	}

	public UserMessenger getUserMessenger() {
		if (this.messenger == null) {
			this.messenger = new UserMessengerFactory().generateSystemOutUserMessenger();
		}
		return this.messenger;
	}

	protected void setUserMessenger(UserMessenger userMessenger) {
		this.messenger = userMessenger;
	}

}
