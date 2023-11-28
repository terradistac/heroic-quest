package org.game.event;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.easymock.EasyMock;
import org.game.encounter.ItemEncounter;
import org.game.encounter.NonEventEncounter;
import org.junit.jupiter.api.Test;

public class CommonRandomEventGeneratorTest {
	
	@Test
	public void testRollEncounter_itemEncounter() {
		CommonRandomEventGenerator generator = EasyMock.partialMockBuilder(CommonRandomEventGenerator.class).addMockedMethod("getRandomNumber").createMock();
		EasyMock.expect(generator.getRandomNumber()).andReturn(2);
		EasyMock.replay(generator);
		
		assertEquals(ItemEncounter.class, generator.rollEncounter().getClass());
		EasyMock.verify(generator);
	}
	
	@Test
	public void testRollEncounter_null() {
		CommonRandomEventGenerator generator = EasyMock.partialMockBuilder(CommonRandomEventGenerator.class).addMockedMethod("getRandomNumber").createMock();
		EasyMock.expect(generator.getRandomNumber()).andReturn(3);
		EasyMock.replay(generator);
		
		assertEquals(NonEventEncounter.class, generator.rollEncounter().getClass());
		EasyMock.verify(generator);
	}
}
