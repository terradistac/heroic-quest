package org.game.state;

import java.util.Map;
import java.util.Set;

import org.game.attributes.StatAttribute;
import org.game.items.ConsumableItem;
import org.game.items.EquipmentItem;
import org.game.items.Item;

public class InventoryManager {
	
	public void addToInventory(GameState gameState, Item item) {
		gameState.getItems().add(item);
	}
	
	public void applyEffect(GameState gameState, ConsumableItem item) {
		int initialHealth = gameState.getCharacter().getHealthPoints();
		gameState.getCharacter().setHealthPoints(initialHealth + item.getHealingPotency());
		if (gameState.getItems().contains(item)) {
			gameState.getItems().remove(item);
		}
	}
	
	public void equipItem(GameState gameState, EquipmentItem item) {
		GameCharacter character = gameState.getCharacter();
		if (!character.getEquippedItems().contains(item)) {
			gameState.getCharacter().getEquippedItems().add(item);
			applyAttributeEffects(gameState, item);
		}
	}

	public void unequipItem(GameState gameState, EquipmentItem item) {
		GameCharacter character = gameState.getCharacter();
		if (character.getEquippedItems().contains(item)) {
			gameState.getCharacter().getEquippedItems().remove(item);
			removeAttributeEffects(gameState, item);
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

}
