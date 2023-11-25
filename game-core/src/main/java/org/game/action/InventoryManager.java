package org.game.action;

import java.util.Map;
import java.util.Set;

import org.game.attributes.StatAttribute;
import org.game.items.ConsumableItem;
import org.game.items.EquipmentItem;
import org.game.items.Item;
import org.game.messenging.UserMessenger;
import org.game.state.GameCharacter;
import org.game.state.GameState;

public class InventoryManager {
	
	protected static final String EQUIP_ITEM = "You equipped the ";
	protected static final String UNEQUIP_ITEM = "You unequipped the ";
	protected static final String USE_ITEM = "You used the ";
	protected static final String PERIOD = ".";
	
	private UserMessenger userMessenger;
	
	public void applyEffect(GameState gameState, ConsumableItem item) {
		int initialHealth = gameState.getCharacter().getHealthPoints();
		gameState.getCharacter().setHealthPoints(initialHealth + item.getHealingPotency());
		if (gameState.getItems().contains(item)) {
			gameState.getItems().remove(item);
		}
		userMessenger.notifyUser(USE_ITEM + item.getName() + PERIOD);
	}
	
	public void equipItem(GameState gameState, EquipmentItem item) {
		GameCharacter character = gameState.getCharacter();
		if (!character.getEquippedItems().contains(item)) {
			gameState.getCharacter().getEquippedItems().add(item);
			applyAttributeEffects(gameState, item);
			userMessenger.notifyUser(EQUIP_ITEM + item.getName() + PERIOD); 
		}
	}

	public void unequipItem(GameState gameState, EquipmentItem item) {
		GameCharacter character = gameState.getCharacter();
		if (character.getEquippedItems().contains(item)) {
			gameState.getCharacter().getEquippedItems().remove(item);
			removeAttributeEffects(gameState, item);
			userMessenger.notifyUser(UNEQUIP_ITEM + item.getName() + PERIOD);
		}
	}

	protected void applyAttributeEffects(GameState gameState, EquipmentItem item) {
		Set<StatAttribute> equipmentAttributes = item.getAttributeEffects().keySet();
		Map<StatAttribute, Integer> characterAttributes = gameState.getCharacter().getStatAttributes();

		for (StatAttribute attributeName : equipmentAttributes) {
			characterAttributes.put(attributeName,
					characterAttributes.get(attributeName) + item.getAttributeEffects().get(attributeName));
		}
	}
	
	protected void removeAttributeEffects(GameState gameState, EquipmentItem item) {
		Set<StatAttribute> equipmentAttributes = item.getAttributeEffects().keySet();
		Map<StatAttribute, Integer> characterAttributes = gameState.getCharacter().getStatAttributes();

		for (StatAttribute attributeName : equipmentAttributes) {
			characterAttributes.put(attributeName,
					characterAttributes.get(attributeName) - item.getAttributeEffects().get(attributeName));
		}
	}
	
	public void addToInventory(GameState gameState, Item item) {
		gameState.getItems().add(item);
	}
	
	public void setUserMessenger(UserMessenger userMessenger) {
		this.userMessenger = userMessenger;
	}
	
	public UserMessenger getUserMessenger() {
		return userMessenger;
	}

}
