package org.game.action.inventory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.game.attributes.StatAttribute;
import org.game.items.ConsumableItem;
import org.game.items.EquipmentItem;
import org.game.items.Item;
import org.game.items.JunkItem;
import org.game.state.GameCharacter;
import org.game.state.GameState;
import org.junit.jupiter.api.Test;

public class BasicInventoryManagerTest {
	
	BasicInventoryManager inventoryManager = new BasicInventoryManager();
	
	@Test
	public void testAddToInventory() {
		GameState gameState = GameState.getInstance();
		TestItem item = new TestItem("Test");
		
		assertEquals(0, gameState.getCharacter().getInventory().size());
		
		inventoryManager.addToInventory(item);
		
		assertEquals(1, gameState.getCharacter().getInventory().size());
		assertEquals("Test", gameState.getCharacter().getInventory().get(0).getName());
		
		resetGameState();
	}
	
	@Test
	public void testApplyEffect_potionInInventory() {
		ConsumableItem potion = new ConsumableItem("Potion of Healing", 5);
		GameState gameState = GameState.getInstance();
		GameCharacter gameCharacter = new GameCharacter();
		gameCharacter.setHealthPoints(5);
		gameState.setCharacter(gameCharacter);
		gameState.getCharacter().getInventory().add(potion);
		
		assertEquals(1, gameState.getCharacter().getInventory().size());
		inventoryManager.applyEffect(potion);
		assertEquals(10, gameState.getCharacter().getHealthPoints());
		assertEquals(0, gameState.getCharacter().getInventory().size());
		
		resetGameState();
	}
	
	@Test
	public void testApplyEffect_potionInInventory_doesNotRemoveOtherItem() {
		ConsumableItem potion0 = new ConsumableItem("Potion of Invisibility", 0);
		ConsumableItem potion = new ConsumableItem("Potion of Healing", 5);
		JunkItem junkItem = new JunkItem("Old Boot");
		ConsumableItem potion2 = new ConsumableItem("Potion of Firebreathing", 0);
		
		GameState gameState = GameState.getInstance();
		GameCharacter gameCharacter = new GameCharacter();
		gameCharacter.setHealthPoints(5);
		gameState.setCharacter(gameCharacter);
		gameState.getCharacter().getInventory().add(potion0);
		gameState.getCharacter().getInventory().add(potion);
		gameState.getCharacter().getInventory().add(junkItem);
		gameState.getCharacter().getInventory().add(potion2);
		
		assertEquals(4, gameState.getCharacter().getInventory().size());
		inventoryManager.applyEffect(potion);
		assertEquals(3, gameState.getCharacter().getInventory().size());
		assertEquals("Potion of Invisibility", gameState.getCharacter().getInventory().get(0).getName());
		assertEquals("Old Boot", gameState.getCharacter().getInventory().get(1).getName());
		assertEquals("Potion of Firebreathing", gameState.getCharacter().getInventory().get(2).getName());
		
		resetGameState();
	}
	
	@Test
	public void testEquipItem() {
		EquipmentItem equipment = new EquipmentItem("Sword", constructStatAttributes());
		GameState gameState = GameState.getInstance();
		GameCharacter character = new GameCharacter();
		gameState.setCharacter(character);
		
		assertEquals(0, gameState.getCharacter().getEquippedItems().size());
		inventoryManager.equipItem(equipment);
		assertEquals(1, gameState.getCharacter().getEquippedItems().size());
		assertTrue(gameState.getCharacter().getEquippedItems().contains(equipment));
		
		resetGameState();
	}
	
	@Test
	public void equipItemDoesNotDuplicateAdd() {
		EquipmentItem equipment = new EquipmentItem("Sword", constructStatAttributes());
		GameState gameState = GameState.getInstance();
		GameCharacter character = new GameCharacter();
		gameState.setCharacter(character);
		
		inventoryManager.equipItem(equipment);
		assertEquals(1, gameState.getCharacter().getEquippedItems().size());
		inventoryManager.equipItem(equipment);
		assertEquals(1, gameState.getCharacter().getEquippedItems().size());
		assertTrue(gameState.getCharacter().getEquippedItems().contains(equipment));
		
		resetGameState();
	}
	
	@Test
	public void testUnequipItem() {
		EquipmentItem equipment = new EquipmentItem("Sword", constructStatAttributes());
		GameState gameState = GameState.getInstance();
		GameCharacter character = new GameCharacter();
		gameState.setCharacter(character);
		
		inventoryManager.equipItem(equipment);
		assertEquals(1, gameState.getCharacter().getEquippedItems().size());
		inventoryManager.unequipItem(equipment);
		assertEquals(0, gameState.getCharacter().getEquippedItems().size());
		
		resetGameState();
	}
	
	@Test
	public void unequipItemDoesNotRemoveOthers() {
		EquipmentItem equipment = new EquipmentItem("Sword", constructStatAttributes());
		EquipmentItem equipment2 = new EquipmentItem("Mace", constructStatAttributes());
		EquipmentItem equipment3 = new EquipmentItem("Wand", constructStatAttributes());
		GameState gameState = GameState.getInstance();
		GameCharacter character = new GameCharacter();
		gameState.setCharacter(character);
		
		inventoryManager.equipItem(equipment2);
		inventoryManager.equipItem(equipment);
		inventoryManager.equipItem(equipment3);
		assertEquals(3, gameState.getCharacter().getEquippedItems().size());
		
		inventoryManager.unequipItem(equipment);
		assertEquals(2, gameState.getCharacter().getEquippedItems().size());
		assertEquals("Mace", gameState.getCharacter().getEquippedItems().get(0).getName());
		assertEquals("Wand", gameState.getCharacter().getEquippedItems().get(1).getName());
		
		resetGameState();
	}
	
	@Test
	public void testApplyAttributeEffects() {
		EquipmentItem equipment = new EquipmentItem("Sword", constructStatAttributes());
		GameState gameState = GameState.getInstance();
		GameCharacter character = new GameCharacter();
		character.setStatAttributes(constructStatAttributes());
		gameState.setCharacter(character);
		
		assertEquals(1, gameState.getCharacter().getStatAttributes().get(StatAttribute.CONSTITUTION));
		assertEquals(2, gameState.getCharacter().getStatAttributes().get(StatAttribute.DEXTERITY));
		assertEquals(3, gameState.getCharacter().getStatAttributes().get(StatAttribute.INTELLIGENCE));
		assertEquals(4, gameState.getCharacter().getStatAttributes().get(StatAttribute.STRENGTH));
		assertEquals(5, gameState.getCharacter().getStatAttributes().get(StatAttribute.WISDOM));
		
		inventoryManager.applyAttributeEffects(gameState, equipment);
		
		assertEquals(2, gameState.getCharacter().getStatAttributes().get(StatAttribute.CONSTITUTION));
		assertEquals(4, gameState.getCharacter().getStatAttributes().get(StatAttribute.DEXTERITY));
		assertEquals(6, gameState.getCharacter().getStatAttributes().get(StatAttribute.INTELLIGENCE));
		assertEquals(8, gameState.getCharacter().getStatAttributes().get(StatAttribute.STRENGTH));
		assertEquals(10, gameState.getCharacter().getStatAttributes().get(StatAttribute.WISDOM));
		
		resetGameState();
	}
	
	@Test
	public void testRemoveAttributeEffects() {
		EquipmentItem equipment = new EquipmentItem("Sword", constructStatAttributes());
		GameState gameState = GameState.getInstance();
		GameCharacter character = new GameCharacter();
		character.setStatAttributes(constructStatAttributes());
		gameState.setCharacter(character);
		
		assertEquals(1, gameState.getCharacter().getStatAttributes().get(StatAttribute.CONSTITUTION));
		assertEquals(2, gameState.getCharacter().getStatAttributes().get(StatAttribute.DEXTERITY));
		assertEquals(3, gameState.getCharacter().getStatAttributes().get(StatAttribute.INTELLIGENCE));
		assertEquals(4, gameState.getCharacter().getStatAttributes().get(StatAttribute.STRENGTH));
		assertEquals(5, gameState.getCharacter().getStatAttributes().get(StatAttribute.WISDOM));
		
		inventoryManager.removeAttributeEffects(gameState, equipment);
		
		assertEquals(0, gameState.getCharacter().getStatAttributes().get(StatAttribute.CONSTITUTION));
		assertEquals(0, gameState.getCharacter().getStatAttributes().get(StatAttribute.DEXTERITY));
		assertEquals(0, gameState.getCharacter().getStatAttributes().get(StatAttribute.INTELLIGENCE));
		assertEquals(0, gameState.getCharacter().getStatAttributes().get(StatAttribute.STRENGTH));
		assertEquals(0, gameState.getCharacter().getStatAttributes().get(StatAttribute.WISDOM));
		
		resetGameState();
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
	
	private class TestItem extends Item {

		public TestItem(String name) {
			super(name);
		}
		
	}
	
	private void resetGameState() {
		GameState gameState = GameState.getInstance();
		gameState.setFoundItems(new ArrayList<Item>());
		gameState.setCharacter(new GameCharacter());
	}
	
}

