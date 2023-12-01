package org.game.user.terminal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class InventorySystemOutUserOptionsTest {
	
	InventorySystemOutUserOptions inventoryOptions = new InventorySystemOutUserOptions();
	
	@Test
	public void testMatchNumberedItemListEntryForEquip() {
		assertTrue(inventoryOptions.inputMatchesItemInListForEquip("Equip 1"));
		assertTrue(inventoryOptions.inputMatchesItemInListForEquip("Equip 11"));
		
		assertTrue(inventoryOptions.inputMatchesItemInListForEquip("equiP 2"));
	}
	
	@Test
	public void testMatchNumberedItemListEntryForEquipDoesNotTriggerOnUnequip() {
		assertFalse(inventoryOptions.inputMatchesItemInListForEquip("Unequip 1"));
		assertFalse(inventoryOptions.inputMatchesItemInListForEquip("Unequip 11"));
		
		assertFalse(inventoryOptions.inputMatchesItemInListForEquip("unEquip 3"));
	}
	
	@Test
	public void testMatchNumberedItemListEntryForUnequip() {
		assertTrue(inventoryOptions.inputMatchesItemInListForUnequip("Unequip 1"));
		assertTrue(inventoryOptions.inputMatchesItemInListForUnequip("Unequip 11"));
		
		assertTrue(inventoryOptions.inputMatchesItemInListForUnequip("unequiP 2"));
	}
	
	@Test
	public void testMatchNumberedItemListEntryForUnequipDoesNotTriggerOnEquip() {
		assertFalse(inventoryOptions.inputMatchesItemInListForUnequip("Equip 1"));
		assertFalse(inventoryOptions.inputMatchesItemInListForUnequip("Equip 11"));
		
		assertFalse(inventoryOptions.inputMatchesItemInListForUnequip("EqUip 3"));
	}
	
	@Test
	public void testGetItemNumber() {
		assertEquals(1, inventoryOptions.getItemNumber("Equip 1"));
		assertEquals(11, inventoryOptions.getItemNumber("Unequip 11"));
	}

}
