package org.game.action.inventory;

import java.util.Map;
import java.util.Set;

import org.game.attributes.StatAttribute;
import org.game.items.ConsumableItem;
import org.game.items.EquipmentItem;
import org.game.items.Item;
import org.game.messenging.UserMessenger;
import org.game.state.GameCharacter;
import org.game.state.GameState;

public class BasicInventoryManager implements InventoryManager {
	
	@Override
	public void applyEffect(GameState gameState, ConsumableItem item) {
		int initialHealth = gameState.getCharacter().getHealthPoints();
		gameState.getCharacter().setHealthPoints(initialHealth + item.getHealingPotency());
		if (gameState.getItems().contains(item)) {
			gameState.getItems().remove(item);
		}
	}
	
	@Override
	public void equipItem(GameState gameState, EquipmentItem item) {
		GameCharacter character = gameState.getCharacter();
		if (!character.getEquippedItems().contains(item)) {
			gameState.getCharacter().getEquippedItems().add(item);
			applyAttributeEffects(gameState, item);
		}
	}

	@Override
	public void unequipItem(GameState gameState, EquipmentItem item) {
		GameCharacter character = gameState.getCharacter();
		if (character.getEquippedItems().contains(item)) {
			gameState.getCharacter().getEquippedItems().remove(item);
			removeAttributeEffects(gameState, item);
		}
	}
	
	@Override
	public void addToInventory(GameState gameState, Item item) {
		gameState.getItems().add(item);
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
