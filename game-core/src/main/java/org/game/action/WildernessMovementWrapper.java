package org.game.action;

import org.game.encounters.Encounter;
import org.game.encounters.RandomEncounterGenerator;
import org.game.state.GameState;

public class WildernessMovementWrapper implements Movement {
	
	private RandomEncounterGenerator randomEncounterGenerator;
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
	
	public RandomEncounterGenerator getRandomEncounterGenerator() {
		return randomEncounterGenerator;
	}

	public void setRandomEncounterGenerator(RandomEncounterGenerator randomEncounterGenerator) {
		this.randomEncounterGenerator = randomEncounterGenerator;
	}


}
