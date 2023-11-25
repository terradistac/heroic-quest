package org.game.action;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.easymock.EasyMock;
import org.game.action.InventoryManager;
import org.game.attributes.StatAttribute;
import org.game.items.ConsumableItem;
import org.game.items.EquipmentItem;
import org.game.items.Item;
import org.game.items.JunkItem;
import org.game.messenging.UserMessenger;
import org.game.state.GameCharacter;
import org.game.state.GameState;
import org.junit.jupiter.api.Test;

public class InventoryManagerTest {
	
	InventoryManager inventoryManager = new InventoryManager();
	UserMessenger userMessenger = EasyMock.createMock(UserMessenger.class);
	
	@Test
	public void testAddToInventory() {
		GameState gameState = new GameState();
		TestItem item = new TestItem("Test");
		
		assertEquals(0, gameState.getItems().size());
		
		inventoryManager.addToInventory(gameState, item);
		
		assertEquals(1, gameState.getItems().size());
		assertEquals("Test", gameState.getItems().get(0).getName());
	}
	
	@Test
	public void testApplyEffect_potionInInventory() {
		ConsumableItem potion = new ConsumableItem("Potion of Healing", 5);
		GameState gameState = new GameState();
		GameCharacter gameCharacter = new GameCharacter();
		gameCharacter.setHealthPoints(5);
		gameState.setCharacter(gameCharacter);
		gameState.getItems().add(potion);
		
		userMessenger.notifyUser(InventoryManager.USE_ITEM + "Potion of Healing" + InventoryManager.PERIOD);
		EasyMock.expectLastCall();
		inventoryManager.setUserMessenger(userMessenger);
		EasyMock.replay(userMessenger);
		
		assertEquals(1, gameState.getItems().size());
		inventoryManager.applyEffect(gameState, potion);
		assertEquals(10, gameState.getCharacter().getHealthPoints());
		assertEquals(0, gameState.getItems().size());
		EasyMock.verify(userMessenger);
		EasyMock.reset(userMessenger);
	}
	
	@Test
	public void testApplyEffect_potionInInventory_doesNotRemoveOtherItem() {
		ConsumableItem potion0 = new ConsumableItem("Potion of Invisibility", 0);
		ConsumableItem potion = new ConsumableItem("Potion of Healing", 5);
		JunkItem junkItem = new JunkItem("Old Boot");
		ConsumableItem potion2 = new ConsumableItem("Potion of Firebreathing", 0);
		
		GameState gameState = new GameState();
		GameCharacter gameCharacter = new GameCharacter();
		gameCharacter.setHealthPoints(5);
		gameState.setCharacter(gameCharacter);
		gameState.getItems().add(potion0);
		gameState.getItems().add(potion);
		gameState.getItems().add(junkItem);
		gameState.getItems().add(potion2);
		
		userMessenger.notifyUser(InventoryManager.USE_ITEM + "Potion of Healing" + InventoryManager.PERIOD);
		EasyMock.expectLastCall().once();
		EasyMock.replay(userMessenger);
		inventoryManager.setUserMessenger(userMessenger);
		
		assertEquals(4, gameState.getItems().size());
		inventoryManager.applyEffect(gameState, potion);
		assertEquals(3, gameState.getItems().size());
		assertEquals("Potion of Invisibility", gameState.getItems().get(0).getName());
		assertEquals("Old Boot", gameState.getItems().get(1).getName());
		assertEquals("Potion of Firebreathing", gameState.getItems().get(2).getName());
		
		EasyMock.verify(userMessenger);
	}
	
	@Test
	public void testEquipItem() {
		EquipmentItem equipment = new EquipmentItem("Sword", constructStatAttributes());
		GameState gameState = new GameState();
		GameCharacter character = new GameCharacter();
		gameState.setCharacter(character);
		
		userMessenger.notifyUser(InventoryManager.EQUIP_ITEM + "Sword" + InventoryManager.PERIOD);
		EasyMock.expectLastCall();
		inventoryManager.setUserMessenger(userMessenger);
		EasyMock.replay(userMessenger);
		
		assertEquals(0, gameState.getCharacter().getEquippedItems().size());
		inventoryManager.equipItem(gameState, equipment);
		assertEquals(1, gameState.getCharacter().getEquippedItems().size());
		assertTrue(gameState.getCharacter().getEquippedItems().contains(equipment));
		
		EasyMock.verify(userMessenger);
		EasyMock.reset(userMessenger);
	}
	
	@Test
	public void equipItemDoesNotDuplicateAdd() {
		EquipmentItem equipment = new EquipmentItem("Sword", constructStatAttributes());
		GameState gameState = new GameState();
		GameCharacter character = new GameCharacter();
		gameState.setCharacter(character);
		
		userMessenger.notifyUser(InventoryManager.EQUIP_ITEM + "Sword" + InventoryManager.PERIOD);
		EasyMock.expectLastCall().once();
		EasyMock.replay(userMessenger);
		
		inventoryManager.setUserMessenger(userMessenger);
		
		inventoryManager.equipItem(gameState, equipment);
		assertEquals(1, gameState.getCharacter().getEquippedItems().size());
		inventoryManager.equipItem(gameState, equipment);
		assertEquals(1, gameState.getCharacter().getEquippedItems().size());
		assertTrue(gameState.getCharacter().getEquippedItems().contains(equipment));
		
		EasyMock.verify(userMessenger);
	}
	
	@Test
	public void testUnequipItem() {
		EquipmentItem equipment = new EquipmentItem("Sword", constructStatAttributes());
		GameState gameState = new GameState();
		GameCharacter character = new GameCharacter();
		gameState.setCharacter(character);
		
		userMessenger.notifyUser(InventoryManager.EQUIP_ITEM + "Sword" + InventoryManager.PERIOD);
		EasyMock.expectLastCall();
		userMessenger.notifyUser(InventoryManager.UNEQUIP_ITEM + "Sword" + InventoryManager.PERIOD);
		EasyMock.expectLastCall();
		inventoryManager.setUserMessenger(userMessenger);
		EasyMock.replay(userMessenger);
		
		inventoryManager.equipItem(gameState, equipment);
		assertEquals(1, gameState.getCharacter().getEquippedItems().size());
		inventoryManager.unequipItem(gameState, equipment);
		assertEquals(0, gameState.getCharacter().getEquippedItems().size());
		
		EasyMock.verify(userMessenger);
		EasyMock.reset(userMessenger);
	}
	
	@Test
	public void unequipItemDoesNotRemoveOthers() {
		EquipmentItem equipment = new EquipmentItem("Sword", constructStatAttributes());
		EquipmentItem equipment2 = new EquipmentItem("Mace", constructStatAttributes());
		EquipmentItem equipment3 = new EquipmentItem("Wand", constructStatAttributes());
		GameState gameState = new GameState();
		GameCharacter character = new GameCharacter();
		gameState.setCharacter(character);
		
		userMessenger.notifyUser(InventoryManager.EQUIP_ITEM + "Sword" + InventoryManager.PERIOD);
		EasyMock.expectLastCall();
		userMessenger.notifyUser(InventoryManager.EQUIP_ITEM + "Mace" + InventoryManager.PERIOD);
		EasyMock.expectLastCall();
		userMessenger.notifyUser(InventoryManager.EQUIP_ITEM + "Wand" + InventoryManager.PERIOD);
		EasyMock.expectLastCall();
		userMessenger.notifyUser(InventoryManager.UNEQUIP_ITEM + "Sword" + InventoryManager.PERIOD);
		EasyMock.expectLastCall();
		EasyMock.replay(userMessenger);
		
		inventoryManager.setUserMessenger(userMessenger);
		
		inventoryManager.equipItem(gameState, equipment2);
		inventoryManager.equipItem(gameState, equipment);
		inventoryManager.equipItem(gameState, equipment3);
		assertEquals(3, gameState.getCharacter().getEquippedItems().size());
		
		inventoryManager.unequipItem(gameState, equipment);
		assertEquals(2, gameState.getCharacter().getEquippedItems().size());
		assertEquals("Mace", gameState.getCharacter().getEquippedItems().get(0).getName());
		assertEquals("Wand", gameState.getCharacter().getEquippedItems().get(1).getName());
		
		EasyMock.verify(userMessenger);
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
		
		inventoryManager.applyAttributeEffects(gameState, equipment);
		
		assertEquals(2, gameState.getCharacter().getStatAttributes().get(StatAttribute.CONSTITUTION));
		assertEquals(4, gameState.getCharacter().getStatAttributes().get(StatAttribute.DEXTERITY));
		assertEquals(6, gameState.getCharacter().getStatAttributes().get(StatAttribute.INTELLIGENCE));
		assertEquals(8, gameState.getCharacter().getStatAttributes().get(StatAttribute.STRENGTH));
		assertEquals(10, gameState.getCharacter().getStatAttributes().get(StatAttribute.WISDOM));
	}
	
	@Test
	public void testRemoveAttributeEffects() {
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
		
		inventoryManager.removeAttributeEffects(gameState, equipment);
		
		assertEquals(0, gameState.getCharacter().getStatAttributes().get(StatAttribute.CONSTITUTION));
		assertEquals(0, gameState.getCharacter().getStatAttributes().get(StatAttribute.DEXTERITY));
		assertEquals(0, gameState.getCharacter().getStatAttributes().get(StatAttribute.INTELLIGENCE));
		assertEquals(0, gameState.getCharacter().getStatAttributes().get(StatAttribute.STRENGTH));
		assertEquals(0, gameState.getCharacter().getStatAttributes().get(StatAttribute.WISDOM));
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
	
}

