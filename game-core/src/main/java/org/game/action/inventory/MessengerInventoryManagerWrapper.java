package org.game.action.inventory;

import org.game.items.ConsumableItem;
import org.game.items.EquipmentItem;
import org.game.items.Item;
import org.game.messenging.UserMessenger;
import org.game.state.GameState;

public class MessengerInventoryManagerWrapper implements InventoryManager {
	
	protected static final String EQUIP_ITEM = "You equipped the ";
	protected static final String UNEQUIP_ITEM = "You unequipped the ";
	protected static final String USE_ITEM = "You used the ";
	protected static final String PERIOD = ".";
	
	private InventoryManager inventoryManager;
	private UserMessenger userMessenger;
	
	public MessengerInventoryManagerWrapper (InventoryManager inventoryManager) {
		this.inventoryManager = inventoryManager;
	}

	@Override
	public void applyEffect(GameState gameState, ConsumableItem item) {
		userMessenger.notifyUser(USE_ITEM + item.getName() + PERIOD);
		this.inventoryManager.applyEffect(gameState, item);
	}

	@Override
	public void equipItem(GameState gameState, EquipmentItem item) {
		if (!gameState.getCharacter().getEquippedItems().contains(item)) {
			userMessenger.notifyUser(EQUIP_ITEM + item.getName() + PERIOD); 
		}
		this.inventoryManager.equipItem(gameState, item);
	}

	@Override
	public void unequipItem(GameState gameState, EquipmentItem item) {
		if (gameState.getCharacter().getEquippedItems().contains(item)) {
			userMessenger.notifyUser(UNEQUIP_ITEM + item.getName() + PERIOD);
		}
		this.inventoryManager.unequipItem(gameState, item);
	}
	
	@Override
	public void addToInventory(GameState gameState, Item item) {
		this.inventoryManager.addToInventory(gameState, item);
	}

	public UserMessenger getUserMessenger() {
		return userMessenger;
	}

	public void setUserMessenger(UserMessenger userMessenger) {
		this.userMessenger = userMessenger;
	}


}
