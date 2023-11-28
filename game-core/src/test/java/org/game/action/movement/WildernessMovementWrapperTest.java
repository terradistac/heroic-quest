package org.game.action.movement;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.easymock.EasyMock;
import org.game.encounter.Encounter;
import org.game.event.RandomEventGenerator;
import org.game.state.GameState;
import org.junit.jupiter.api.Test;

public class WildernessMovementWrapperTest {
	
	Movement basicMovement = new BasicMovement();
	
	@Test
	public void testMoveResolveEncounter() {
		WildernessMovementWrapper movement = new WildernessMovementWrapper(basicMovement);
		RandomEventGenerator encounterGenerator = EasyMock.createMock(RandomEventGenerator.class);
		Encounter encounter = EasyMock.createMock(Encounter.class);
		
		movement.setRandomEncounterGenerator(encounterGenerator);
		
		EasyMock.expect(encounterGenerator.rollEncounter()).andReturn(encounter);
		encounter.resolveEncounter();
		EasyMock.expectLastCall();
		EasyMock.replay(encounterGenerator, encounter);
		
		movement.resolveMovementEncounters();
		
		EasyMock.verify(encounterGenerator, encounter);
		
		resetGameState();
	}
	
	@Test
	public void testMoveNorth() {
		WildernessMovementWrapper movement = new WildernessMovementWrapper(basicMovement);
		
		RandomEventGenerator encounterGenerator = EasyMock.createMock(RandomEventGenerator.class);
		Encounter encounter = EasyMock.createMock(Encounter.class);
		
		EasyMock.expect(encounterGenerator.rollEncounter()).andReturn(encounter);
		encounter.resolveEncounter();
		EasyMock.expectLastCall();
		EasyMock.replay(encounterGenerator, encounter);
		
		movement.setRandomEncounterGenerator(encounterGenerator);
		
		movement.moveNorth();
		
		assertEquals(0, GameState.getInstance().getxPosition());
		assertEquals(1, GameState.getInstance().getyPosition());
		
		resetGameState();
	}
	
	@Test
	public void testMoveEast() {
		WildernessMovementWrapper movement = new WildernessMovementWrapper(basicMovement);
		
		RandomEventGenerator encounterGenerator = EasyMock.createMock(RandomEventGenerator.class);
		Encounter encounter = EasyMock.createMock(Encounter.class);
		
		EasyMock.expect(encounterGenerator.rollEncounter()).andReturn(encounter);
		encounter.resolveEncounter();
		EasyMock.expectLastCall();
		EasyMock.replay(encounterGenerator, encounter);
		
		movement.setRandomEncounterGenerator(encounterGenerator);
		
		movement.moveEast();
		
		assertEquals(1, GameState.getInstance().getxPosition());
		assertEquals(0, GameState.getInstance().getyPosition());
		
		resetGameState();
	}
	
	@Test
	public void testMoveWest() {
		WildernessMovementWrapper movement = new WildernessMovementWrapper(basicMovement);
		
		RandomEventGenerator encounterGenerator = EasyMock.createMock(RandomEventGenerator.class);
		Encounter encounter = EasyMock.createMock(Encounter.class);
		
		EasyMock.expect(encounterGenerator.rollEncounter()).andReturn(encounter);
		encounter.resolveEncounter();
		EasyMock.expectLastCall();
		EasyMock.replay(encounterGenerator, encounter);
		
		movement.setRandomEncounterGenerator(encounterGenerator);
		
		movement.moveWest();
		
		assertEquals(-1, GameState.getInstance().getxPosition());
		assertEquals(0, GameState.getInstance().getyPosition());
		
		resetGameState();
	}
	
	@Test
	public void testMoveSouth() {
		WildernessMovementWrapper movement = new WildernessMovementWrapper(basicMovement);
		
		RandomEventGenerator encounterGenerator = EasyMock.createMock(RandomEventGenerator.class);
		Encounter encounter = EasyMock.createMock(Encounter.class);
		
		EasyMock.expect(encounterGenerator.rollEncounter()).andReturn(encounter);
		encounter.resolveEncounter();
		EasyMock.expectLastCall();
		EasyMock.replay(encounterGenerator, encounter);
		
		movement.setRandomEncounterGenerator(encounterGenerator);
		
		movement.moveSouth();
		
		assertEquals(0, GameState.getInstance().getxPosition());
		assertEquals(-1, GameState.getInstance().getyPosition());
		
		resetGameState();
		
	}
	
	public void resetGameState() {
		GameState.getInstance().setxPosition(0);
		GameState.getInstance().setyPosition(0);
	}

}
