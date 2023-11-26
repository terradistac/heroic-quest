package org.game.action.inventory;

import java.util.HashMap;
import java.util.Map;

import org.easymock.EasyMock;
import org.game.action.inventory.InventoryManager;
import org.game.action.inventory.MessengerInventoryManagerWrapper;
import org.game.attributes.StatAttribute;
import org.game.items.ConsumableItem;
import org.game.items.EquipmentItem;
import org.game.messenging.UserMessenger;
import org.game.state.GameCharacter;
import org.game.state.GameState;
import org.junit.jupiter.api.Test;

public class MessengerInventoryManagerWrapperTest {
	
	InventoryManager inventoryManager = EasyMock.createMock(InventoryManager.class);
	MessengerInventoryManagerWrapper messengerInventoryManager = new MessengerInventoryManagerWrapper(inventoryManager);
	UserMessenger userMessenger = EasyMock.createMock(UserMessenger.class);
	
	@Test
	public void testApplyEffect_potionInInventory() {
		ConsumableItem potion = new ConsumableItem("Potion of Healing", 5);
		GameState gameState = new GameState();
		
		userMessenger.notifyUser(MessengerInventoryManagerWrapper.USE_ITEM + "Potion of Healing" + MessengerInventoryManagerWrapper.PERIOD);
		EasyMock.expectLastCall();
		messengerInventoryManager.setUserMessenger(userMessenger);
		EasyMock.replay(userMessenger);
		
		messengerInventoryManager.applyEffect(gameState, potion);
		EasyMock.verify(userMessenger);
		EasyMock.reset(userMessenger);
	}
	
	@Test
	public void testEquipItem() {
		EquipmentItem equipment = new EquipmentItem("Sword", constructStatAttributes());
		GameState gameState = new GameState();
		GameCharacter character = new GameCharacter();
		gameState.setCharacter(character);
		
		userMessenger.notifyUser(MessengerInventoryManagerWrapper.EQUIP_ITEM + "Sword" + MessengerInventoryManagerWrapper.PERIOD);
		EasyMock.expectLastCall();
		messengerInventoryManager.setUserMessenger(userMessenger);
		EasyMock.replay(userMessenger);

		messengerInventoryManager.equipItem(gameState, equipment);
		
		EasyMock.verify(userMessenger);
		EasyMock.reset(userMessenger);
	}
	
	@Test
	public void equipItemDoesNotDuplicateAdd() {
		EquipmentItem equipment = new EquipmentItem("Sword", constructStatAttributes());
		GameState gameState = new GameState();
		GameCharacter character = new GameCharacter();
		gameState.setCharacter(character);
		
		EasyMock.replay(userMessenger);
		
		messengerInventoryManager.setUserMessenger(userMessenger);
		
		gameState.getCharacter().getEquippedItems().add(equipment);
		messengerInventoryManager.equipItem(gameState, equipment);
		
		EasyMock.verify(userMessenger);
		EasyMock.reset(userMessenger);
	}
	
	@Test
	public void testUnequipItem() {
		EquipmentItem equipment = new EquipmentItem("Sword", constructStatAttributes());
		GameState gameState = new GameState();
		GameCharacter character = new GameCharacter();
		gameState.setCharacter(character);
		
		userMessenger.notifyUser(MessengerInventoryManagerWrapper.UNEQUIP_ITEM + "Sword" + MessengerInventoryManagerWrapper.PERIOD);
		EasyMock.expectLastCall();
		messengerInventoryManager.setUserMessenger(userMessenger);
		EasyMock.replay(userMessenger);
		
		gameState.getCharacter().getEquippedItems().add(equipment);
		
		messengerInventoryManager.unequipItem(gameState, equipment);
		
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
		
		userMessenger.notifyUser(MessengerInventoryManagerWrapper.UNEQUIP_ITEM + "Sword" + MessengerInventoryManagerWrapper.PERIOD);
		EasyMock.expectLastCall();
		EasyMock.replay(userMessenger);
		
		messengerInventoryManager.setUserMessenger(userMessenger);
		
		gameState.getCharacter().getEquippedItems().add(equipment2);
		gameState.getCharacter().getEquippedItems().add(equipment);
		gameState.getCharacter().getEquippedItems().add(equipment3);
		
		messengerInventoryManager.unequipItem(gameState, equipment);
		
		EasyMock.verify(userMessenger);
		EasyMock.reset(userMessenger);
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
