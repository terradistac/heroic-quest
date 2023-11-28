package org.game.action.inventory;

import org.game.items.ConsumableItem;
import org.game.items.EquipmentItem;
import org.game.items.Item;
import org.game.state.GameState;

public interface InventoryManager {
	
	public void applyEffect(ConsumableItem item);
	
	public void equipItem(EquipmentItem item);
	
	public void unequipItem(EquipmentItem item);
	
	public void addToInventory(Item item);

}
