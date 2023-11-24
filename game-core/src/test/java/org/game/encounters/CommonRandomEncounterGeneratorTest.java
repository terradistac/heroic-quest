package org.game.encounters;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.easymock.EasyMock;
import org.junit.jupiter.api.Test;

public class CommonRandomEncounterGeneratorTest {
	
	@Test
	public void testRollEncounter_itemEncounter() {
		CommonRandomEncounterGenerator generator = EasyMock.partialMockBuilder(CommonRandomEncounterGenerator.class).addMockedMethod("getRandomNumber").createMock();
		EasyMock.expect(generator.getRandomNumber()).andReturn(2);
		EasyMock.replay(generator);
		
		assertEquals(ItemEncounter.class, generator.rollEncounter().getClass());
		EasyMock.verify(generator);
	}
	
	@Test
	public void testRollEncounter_null() {
		CommonRandomEncounterGenerator generator = EasyMock.partialMockBuilder(CommonRandomEncounterGenerator.class).addMockedMethod("getRandomNumber").createMock();
		EasyMock.expect(generator.getRandomNumber()).andReturn(3);
		EasyMock.replay(generator);
		
		assertEquals(null, generator.rollEncounter());
		EasyMock.verify(generator);
	}
}
