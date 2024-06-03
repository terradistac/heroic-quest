package org.game.user.terminal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.easymock.EasyMock;
import org.game.attributes.StatAttribute;
import org.game.items.EquipmentItem;
import org.game.messenging.UserMessenger;
import org.game.state.GameState;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

import org.game.items.Item;
import org.game.items.JunkItem;

public class InventorySystemOutUserOptionsTest {
	
	InventorySystemOutUserOptions inventoryOptions = new InventorySystemOutUserOptions();
	
	@Test
	public void testSendContentsOfInventoryToMessenger() {
		UserMessenger userMessenger = EasyMock.createMock(UserMessenger.class);
		List<Item> items = new ArrayList<>();
		List<EquipmentItem> equippedItems = new ArrayList<>();

		JunkItem item1 = new JunkItem("rubber duckie");
		EquipmentItem equipment1 = new EquipmentItem("shower cap", null);
		EquipmentItem equipment2 = new EquipmentItem("back scrubber", null);

		items.addAll(Arrays.asList(item1, equipment1, equipment2));
		equippedItems.add(equipment2);

		inventoryOptions.setUserMessenger(userMessenger);
		inventoryOptions.setInventoryItems(items);
		inventoryOptions.setEquippedItems(equippedItems);

		userMessenger.notifyUser(InventorySystemOutUserOptions.OPENING_MESSAGE);
		EasyMock.expectLastCall().once();
		userMessenger.notifyUser("1: rubber duckie");
		EasyMock.expectLastCall().once();
		userMessenger.notifyUser("2: shower cap");
		EasyMock.expectLastCall().once();
		userMessenger.notifyUser("3: back scrubber (Equipped)");
		EasyMock.expectLastCall().once();

		EasyMock.replay(userMessenger);

		inventoryOptions.sendContentsOfInventoryToMessenger();

		EasyMock.verify(userMessenger);
	}

	@Test
	public void testMatchStringForEquip() {
		assertTrue(inventoryOptions.inputMatchesEquip("Equip 1"));
		assertTrue(inventoryOptions.inputMatchesEquip("Equip 11"));
		
		assertTrue(inventoryOptions.inputMatchesEquip("equiP 2"));
	}
	
	@Test
	public void testMatchStringForEquipDoesNotTriggerOnUnequip() {
		assertFalse(inventoryOptions.inputMatchesEquip("Unequip 1"));
		assertFalse(inventoryOptions.inputMatchesEquip("Unequip 11"));
		
		assertFalse(inventoryOptions.inputMatchesEquip("unEquip 3"));
	}
	
	@Test
	public void testMatchStringForUnequip() {
		assertTrue(inventoryOptions.inputMatchesUnequip("Unequip 1"));
		assertTrue(inventoryOptions.inputMatchesUnequip("Unequip 11"));
		
		assertTrue(inventoryOptions.inputMatchesUnequip("unequiP 2"));
	}
	
	@Test
	public void testMatchStringForUnequipDoesNotTriggerOnEquip() {
		assertFalse(inventoryOptions.inputMatchesUnequip("Equip 1"));
		assertFalse(inventoryOptions.inputMatchesUnequip("Equip 11"));
		
		assertFalse(inventoryOptions.inputMatchesUnequip("EqUip 3"));
	}
	
	@Test
	public void testGetItemNumber() {
		assertEquals(1, inventoryOptions.getItemNumber("Equip 1"));
		assertEquals(11, inventoryOptions.getItemNumber("Unequip 11"));
	}

	@Test
	public void testIfItemNumberExistsInInventory() {
		Map<StatAttribute, Integer> attribute = new HashMap<StatAttribute, Integer>();
		GameState.getInstance().getCharacter().getInventory().add(new EquipmentItem("sword", attribute));

		assertTrue(inventoryOptions.isNumberInInventory(0, GameState.getInstance().getCharacter().getInventory()));
		
		GameState.getInstance().getCharacter().getInventory().clear();;
	}

}
