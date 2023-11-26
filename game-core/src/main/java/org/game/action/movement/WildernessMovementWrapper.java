package org.game.action.movement;

import org.game.encounter.Encounter;
import org.game.event.RandomEventGenerator;
import org.game.state.GameState;

public class WildernessMovementWrapper implements Movement {
	
	private RandomEventGenerator randomEncounterGenerator;
	private Movement movement;
	
	public WildernessMovementWrapper (Movement movement) {
		this.movement = movement;
	}
	
	@Override
	public void moveNorth(GameState gameState) {
		resolveMovementEncounters(gameState);
		this.movement.moveNorth(gameState);
	}
	
	@Override
	public void moveEast(GameState gameState) {
		resolveMovementEncounters(gameState);
		this.movement.moveEast(gameState);
	}
	
	@Override
	public void moveWest(GameState gameState) {
		resolveMovementEncounters(gameState);
		this.movement.moveWest(gameState);
	}
	
	@Override
	public void moveSouth(GameState gameState) {
		resolveMovementEncounters(gameState);
		this.movement.moveSouth(gameState);
	}
	
	protected void resolveMovementEncounters (GameState gameState) {
		Encounter encounter = randomEncounterGenerator.rollEncounter();
		encounter.resolveEncounter(gameState);
	}
	
	public RandomEventGenerator getRandomEncounterGenerator() {
		return randomEncounterGenerator;
	}

	public void setRandomEncounterGenerator(RandomEventGenerator randomEncounterGenerator) {
		this.randomEncounterGenerator = randomEncounterGenerator;
	}


}
