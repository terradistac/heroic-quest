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
	public void applyEffect(ConsumableItem item) {
		int initialHealth = GameState.getInstance().getCharacter().getHealthPoints();
		GameState.getInstance().getCharacter().setHealthPoints(initialHealth + item.getHealingPotency());
		if (GameState.getInstance().getItems().contains(item)) {
			GameState.getInstance().getItems().remove(item);
		}
	}
	
	@Override
	public void equipItem(EquipmentItem item) {
		GameCharacter character = GameState.getInstance().getCharacter();
		if (!character.getEquippedItems().contains(item)) {
			GameState.getInstance().getCharacter().getEquippedItems().add(item);
			applyAttributeEffects(GameState.getInstance(), item);
		}
	}

	@Override
	public void unequipItem(EquipmentItem item) {
		GameCharacter character = GameState.getInstance().getCharacter();
		if (character.getEquippedItems().contains(item)) {
			GameState.getInstance().getCharacter().getEquippedItems().remove(item);
			removeAttributeEffects(GameState.getInstance(), item);
		}
	}
	
	@Override
	public void addToInventory(Item item) {
		GameState.getInstance().getItems().add(item);
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
