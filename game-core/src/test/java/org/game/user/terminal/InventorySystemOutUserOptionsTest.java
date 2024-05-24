package org.game.user.terminal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.game.attributes.StatAttribute;
import org.game.items.EquipmentItem;
import org.game.state.GameState;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;

public class InventorySystemOutUserOptionsTest {
	
	InventorySystemOutUserOptions inventoryOptions = new InventorySystemOutUserOptions();
	
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
