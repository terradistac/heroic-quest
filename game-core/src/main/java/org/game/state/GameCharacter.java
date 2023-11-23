package org.game.state;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.game.attributes.StatAttribute;
import org.game.items.EquipmentItem;

public class GameCharacter {
	
	private String name;
	private int healthPoints;
	private Map<StatAttribute, Integer> statAttributes = new HashMap<>();
	private List<EquipmentItem> equippedItems = new ArrayList<>();
	
	public GameCharacter() {
		statAttributes.put(StatAttribute.CONSTITUTION, 0);
		statAttributes.put(StatAttribute.DEXTERITY, 0);
		statAttributes.put(StatAttribute.INTELLIGENCE, 0);
		statAttributes.put(StatAttribute.STRENGTH, 0);
		statAttributes.put(StatAttribute.WISDOM, 0);
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getHealthPoints() {
		return healthPoints;
	}
	public void setHealthPoints(int healthPoints) {
		this.healthPoints = healthPoints;
	}
	
	public Map<StatAttribute, Integer> getStatAttributes() {
		return statAttributes;
	}
	public void setStatAttributes(Map<StatAttribute, Integer> statAttributes) {
		this.statAttributes = statAttributes;
	}
	public List<EquipmentItem> getEquippedItems() {
		return equippedItems;
	}
	public void setEquippedItems(List<EquipmentItem> equippedItems) {
		this.equippedItems = equippedItems;
	}

}
