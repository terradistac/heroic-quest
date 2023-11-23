package org.game.items;

import java.util.HashMap;
import java.util.Map;

import org.game.attributes.StatAttribute;

public class EquipmentItem extends Item {

	private Map<StatAttribute, Integer> attributeEffects = new HashMap<>();

	public EquipmentItem(String name, Map<StatAttribute, Integer> statAttributes) {
		super(name);
		this.setAttributeEffects(statAttributes);
	}

	public Map<StatAttribute, Integer> getAttributeEffects() {
		return attributeEffects;
	}

	public void setAttributeEffects(Map<StatAttribute, Integer> attributeEffects) {
		this.attributeEffects = attributeEffects;
	}

}
