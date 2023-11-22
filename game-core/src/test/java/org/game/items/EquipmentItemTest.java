package org.game.items;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.game.state.GameCharacter;
import org.game.state.GameState;
import org.game.state.StatAttribute;
import org.junit.jupiter.api.Test;

public class EquipmentItemTest {
	
	@Test
	public void testEquipItem() {
		EquipmentItem equipment = new EquipmentItem("Sword", constructStatAttributes());
		GameState gameState = new GameState();
		GameCharacter character = new GameCharacter();
		gameState.setCharacter(character);
		
		assertEquals(0, gameState.getCharacter().getEquippedItems().size());
		equipment.equipItem(gameState);
		assertEquals(1, gameState.getCharacter().getEquippedItems().size());
		assertTrue(gameState.getCharacter().getEquippedItems().contains(equipment));
	}
	
	@Test
	public void equipItemDoesNotDuplicateAdd() {
		EquipmentItem equipment = new EquipmentItem("Sword", constructStatAttributes());
		GameState gameState = new GameState();
		GameCharacter character = new GameCharacter();
		gameState.setCharacter(character);
		
		equipment.equipItem(gameState);
		assertEquals(1, gameState.getCharacter().getEquippedItems().size());
		equipment.equipItem(gameState);
		assertEquals(1, gameState.getCharacter().getEquippedItems().size());
		assertTrue(gameState.getCharacter().getEquippedItems().contains(equipment));
	}
	
	@Test
	public void testUnequipItem() {
		EquipmentItem equipment = new EquipmentItem("Sword", constructStatAttributes());
		GameState gameState = new GameState();
		GameCharacter character = new GameCharacter();
		gameState.setCharacter(character);
		
		equipment.equipItem(gameState);
		assertEquals(1, gameState.getCharacter().getEquippedItems().size());
		equipment.unequipItem(gameState);
		assertEquals(0, gameState.getCharacter().getEquippedItems().size());
	}
	
	@Test
	public void unequipItemDoesNotRemoveOthers() {
		EquipmentItem equipment = new EquipmentItem("Sword", constructStatAttributes());
		EquipmentItem equipment2 = new EquipmentItem("Mace", constructStatAttributes());
		EquipmentItem equipment3 = new EquipmentItem("Wand", constructStatAttributes());
		GameState gameState = new GameState();
		GameCharacter character = new GameCharacter();
		gameState.setCharacter(character);
		
		equipment2.equipItem(gameState);
		equipment.equipItem(gameState);
		equipment3.equipItem(gameState);
		assertEquals(3, gameState.getCharacter().getEquippedItems().size());
		
		equipment.unequipItem(gameState);
		assertEquals(2, gameState.getCharacter().getEquippedItems().size());
		assertEquals("Mace", gameState.getCharacter().getEquippedItems().get(0).getName());
		assertEquals("Wand", gameState.getCharacter().getEquippedItems().get(1).getName());
	}
	
	@Test
	public void testApplyAttributeEffects() {
		EquipmentItem equipment = new EquipmentItem("Sword", constructStatAttributes());
		GameState gameState = new GameState();
		GameCharacter character = new GameCharacter();
		character.setStatAttributes(constructStatAttributes());
		gameState.setCharacter(character);
		
		assertEquals(1, gameState.getCharacter().getStatAttributes().get(StatAttribute.CONSTITUTION));
		assertEquals(2, gameState.getCharacter().getStatAttributes().get(StatAttribute.DEXTERITY));
		assertEquals(3, gameState.getCharacter().getStatAttributes().get(StatAttribute.INTELLIGENCE));
		assertEquals(4, gameState.getCharacter().getStatAttributes().get(StatAttribute.STRENGTH));
		assertEquals(5, gameState.getCharacter().getStatAttributes().get(StatAttribute.WISDOM));
		
		equipment.applyAttributeEffects(gameState);
		
		assertEquals(2, gameState.getCharacter().getStatAttributes().get(StatAttribute.CONSTITUTION));
		assertEquals(4, gameState.getCharacter().getStatAttributes().get(StatAttribute.DEXTERITY));
		assertEquals(6, gameState.getCharacter().getStatAttributes().get(StatAttribute.INTELLIGENCE));
		assertEquals(8, gameState.getCharacter().getStatAttributes().get(StatAttribute.STRENGTH));
		assertEquals(10, gameState.getCharacter().getStatAttributes().get(StatAttribute.WISDOM));
	}
	
	private Map<StatAttribute, Integer> constructStatAttributes() {
		Map<StatAttribute, Integer> attributeEffects = new HashMap<>();
		attributeEffects.put(StatAttribute.CONSTITUTION, 1);
		attributeEffects.put(StatAttribute.DEXTERITY, 2);
		attributeEffects.put(StatAttribute.INTELLIGENCE, 3);
		attributeEffects.put(StatAttribute.STRENGTH, 4);
		attributeEffects.put(StatAttribute.WISDOM, 5);
		return attributeEffects;
	}

}
