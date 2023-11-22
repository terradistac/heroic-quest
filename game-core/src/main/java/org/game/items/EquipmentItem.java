package org.game.items;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.game.state.GameCharacter;
import org.game.state.GameState;
import org.game.state.StatAttribute;

public class EquipmentItem extends Item {

	private Map<StatAttribute, Integer> attributeEffects = new HashMap<>();

	public EquipmentItem(String name, Map<StatAttribute, Integer> statAttributes) {
		super(name);
		this.setAttributeEffects(statAttributes);
	}

	@Override
	public void applyEffect(GameState gameState) {
		// TODO Auto-generated method stub

	}

	public void equipItem(GameState gameState) {
		GameCharacter character = gameState.getCharacter();
		if (!character.getEquippedItems().contains(this)) {
			gameState.getCharacter().getEquippedItems().add(this);
		}
	}

	public void unequipItem(GameState gameState) {
		GameCharacter character = gameState.getCharacter();
		if (character.getEquippedItems().contains(this)) {
			gameState.getCharacter().getEquippedItems().remove(this);
		}
	}

	protected void applyAttributeEffects(GameState gameState) {
		Set<StatAttribute> equipmentAttributes = this.attributeEffects.keySet();
		Map<StatAttribute, Integer> characterAttributes = gameState.getCharacter().getStatAttributes();

		for (StatAttribute attributeName : equipmentAttributes) {
			characterAttributes.put(attributeName,
					characterAttributes.get(attributeName) + this.attributeEffects.get(attributeName));
		}
	}

	public Map<StatAttribute, Integer> getAttributeEffects() {
		return attributeEffects;
	}

	public void setAttributeEffects(Map<StatAttribute, Integer> attributeEffects) {
		this.attributeEffects = attributeEffects;
	}

}
