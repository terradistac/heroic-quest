package org.game.action;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.easymock.EasyMock;
import org.game.encounters.Encounter;
import org.game.encounters.RandomEncounterGenerator;
import org.game.state.GameState;
import org.junit.jupiter.api.Test;

public class WildernessMovementWrapperTest {
	
	Movement basicMovement = new BasicMovement();
	
	@Test
	public void testMoveResolveEncounter() {
		WildernessMovementWrapper movement = new WildernessMovementWrapper(basicMovement);
		GameState gameState = new GameState();
		RandomEncounterGenerator encounterGenerator = EasyMock.createMock(RandomEncounterGenerator.class);
		Encounter encounter = EasyMock.createMock(Encounter.class);
		
		movement.setRandomEncounterGenerator(encounterGenerator);
		
		EasyMock.expect(encounterGenerator.rollEncounter()).andReturn(encounter);
		encounter.resolveEncounter(gameState);
		EasyMock.expectLastCall();
		EasyMock.replay(encounterGenerator, encounter);
		
		movement.resolveMovementEncounters(gameState);
		
		EasyMock.verify(encounterGenerator, encounter);
	}
	
	@Test
	public void testMoveNorth() {
		GameState gameState = new GameState();
		WildernessMovementWrapper movement = new WildernessMovementWrapper(basicMovement);
		
		RandomEncounterGenerator encounterGenerator = EasyMock.createMock(RandomEncounterGenerator.class);
		Encounter encounter = EasyMock.createMock(Encounter.class);
		
		EasyMock.expect(encounterGenerator.rollEncounter()).andReturn(encounter);
		encounter.resolveEncounter(gameState);
		EasyMock.expectLastCall();
		EasyMock.replay(encounterGenerator, encounter);
		
		movement.setRandomEncounterGenerator(encounterGenerator);
		
		gameState.setxPosition(0);
		gameState.setyPosition(0);
		
		movement.moveNorth(gameState);
		
		assertEquals(0, gameState.getxPosition());
		assertEquals(1, gameState.getyPosition());
		
	}
	
	@Test
	public void testMoveEast() {
		GameState gameState = new GameState();
		WildernessMovementWrapper movement = new WildernessMovementWrapper(basicMovement);
		
		RandomEncounterGenerator encounterGenerator = EasyMock.createMock(RandomEncounterGenerator.class);
		Encounter encounter = EasyMock.createMock(Encounter.class);
		
		EasyMock.expect(encounterGenerator.rollEncounter()).andReturn(encounter);
		encounter.resolveEncounter(gameState);
		EasyMock.expectLastCall();
		EasyMock.replay(encounterGenerator, encounter);
		
		movement.setRandomEncounterGenerator(encounterGenerator);
		
		gameState.setxPosition(0);
		gameState.setyPosition(0);
		
		movement.moveEast(gameState);
		
		assertEquals(1, gameState.getxPosition());
		assertEquals(0, gameState.getyPosition());
		
	}
	
	@Test
	public void testMoveWest() {
		GameState gameState = new GameState();
		WildernessMovementWrapper movement = new WildernessMovementWrapper(basicMovement);
		
		RandomEncounterGenerator encounterGenerator = EasyMock.createMock(RandomEncounterGenerator.class);
		Encounter encounter = EasyMock.createMock(Encounter.class);
		
		EasyMock.expect(encounterGenerator.rollEncounter()).andReturn(encounter);
		encounter.resolveEncounter(gameState);
		EasyMock.expectLastCall();
		EasyMock.replay(encounterGenerator, encounter);
		
		movement.setRandomEncounterGenerator(encounterGenerator);
		
		gameState.setxPosition(0);
		gameState.setyPosition(0);
		
		movement.moveWest(gameState);
		
		assertEquals(-1, gameState.getxPosition());
		assertEquals(0, gameState.getyPosition());
		
	}
	
	@Test
	public void testMoveSouth() {
		GameState gameState = new GameState();
		WildernessMovementWrapper movement = new WildernessMovementWrapper(basicMovement);
		
		RandomEncounterGenerator encounterGenerator = EasyMock.createMock(RandomEncounterGenerator.class);
		Encounter encounter = EasyMock.createMock(Encounter.class);
		
		EasyMock.expect(encounterGenerator.rollEncounter()).andReturn(encounter);
		encounter.resolveEncounter(gameState);
		EasyMock.expectLastCall();
		EasyMock.replay(encounterGenerator, encounter);
		
		movement.setRandomEncounterGenerator(encounterGenerator);
		
		gameState.setxPosition(0);
		gameState.setyPosition(0);
		
		movement.moveSouth(gameState);
		
		assertEquals(0, gameState.getxPosition());
		assertEquals(-1, gameState.getyPosition());
		
	}

}
