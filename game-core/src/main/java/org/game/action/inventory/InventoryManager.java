package org.game.action.inventory;

import org.game.items.ConsumableItem;
import org.game.items.EquipmentItem;
import org.game.items.Item;
import org.game.state.GameState;

public interface InventoryManager {
	
	public void applyEffect(GameState gameState, ConsumableItem item);
	
	public void equipItem(GameState gameState, EquipmentItem item);
	
	public void unequipItem(GameState gameState, EquipmentItem item);
	
	public void addToInventory(GameState gameState, Item item);

}
