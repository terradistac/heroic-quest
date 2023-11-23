package org.game.items;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Map;

import org.easymock.EasyMock;
import org.game.state.StatAttribute;
import org.junit.jupiter.api.Test;

public class CommonItemGeneratorTest {
	
	@Test
	public void testGeneratePotion() {
		CommonItemGenerator generator = new CommonItemGenerator();
		ConsumableItem generatedPotion = generator.generatePotion();
		
		assertEquals("Minor Potion of Healing", generatedPotion.getName());
		assertEquals(2, generatedPotion.getHealingPotency());
	}
	
	@Test
	public void testGenerateEquipmentItem() {
		CommonItemGenerator generator = new CommonItemGenerator();
		EquipmentItem generatedSword = generator.generateEquipment();
		Map<StatAttribute, Integer> stats = generatedSword.getAttributeEffects();
		
		assertEquals("Sword +1", generatedSword.getName());
		assertTrue(stats.containsKey(StatAttribute.STRENGTH));
		assertEquals(1, stats.get(StatAttribute.STRENGTH));
	}
	
	@Test
	public void testGenerateJunkItem() {
		CommonItemGenerator generator = new CommonItemGenerator();
		JunkItem generatedJunk = generator.generateJunk();
		
		assertEquals("Old Boot", generatedJunk.getName());
	}
	
	@Test
	public void testRollForConsumableItem() {
		Item randomItem;
		CommonItemGenerator generator = EasyMock.partialMockBuilder(CommonItemGenerator.class).addMockedMethod("getRandomResult").mock();
		EasyMock.expect(generator.getRandomNumber()).andReturn(1);
		EasyMock.replay(generator);
		
		randomItem = generator.rollForRandomItem();
		assertEquals(ConsumableItem.class, randomItem.getClass());
		EasyMock.verify(generator);
		
		EasyMock.reset(generator);
		
		EasyMock.expect(generator.getRandomNumber()).andReturn(0);
		EasyMock.replay(generator);
		randomItem = generator.rollForRandomItem();
		assertEquals(ConsumableItem.class, randomItem.getClass());
		
		EasyMock.verify(generator);
		
		EasyMock.reset(generator);
		
		EasyMock.expect(generator.getRandomNumber()).andReturn(2);
		EasyMock.replay(generator);
		randomItem = generator.rollForRandomItem();
		assertEquals(ConsumableItem.class, randomItem.getClass());
		
		EasyMock.verify(generator);
	}
	
	@Test
	public void testRollForEquipmentItem() {
		Item randomItem;
		CommonItemGenerator generator = EasyMock.partialMockBuilder(CommonItemGenerator.class).addMockedMethod("getRandomResult").mock();
		EasyMock.expect(generator.getRandomNumber()).andReturn(3).times(3);
		EasyMock.replay(generator);
		
		randomItem = generator.rollForRandomItem();
		assertEquals(EquipmentItem.class, randomItem.getClass());
		EasyMock.verify(generator);
		
		EasyMock.reset(generator);
		
		EasyMock.expect(generator.getRandomNumber()).andReturn(4).times(3);
		EasyMock.replay(generator);
		randomItem = generator.rollForRandomItem();
		assertEquals(EquipmentItem.class, randomItem.getClass());
		
		EasyMock.verify(generator);
	}
	
	@Test
	public void testRollForJunkItem() {
		Item randomItem;
		CommonItemGenerator generator = EasyMock.partialMockBuilder(CommonItemGenerator.class).addMockedMethod("getRandomResult").mock();
		EasyMock.expect(generator.getRandomNumber()).andReturn(5).times(2);
		EasyMock.replay(generator);
		
		randomItem = generator.rollForRandomItem();
		assertEquals(JunkItem.class, randomItem.getClass());
		EasyMock.verify(generator);
		
		EasyMock.reset(generator);
		
		EasyMock.expect(generator.getRandomNumber()).andReturn(10).times(2);
		EasyMock.replay(generator);
		randomItem = generator.rollForRandomItem();
		assertEquals(JunkItem.class, randomItem.getClass());
		
		EasyMock.verify(generator);
	}

}
