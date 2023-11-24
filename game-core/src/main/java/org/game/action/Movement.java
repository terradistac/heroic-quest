package org.game.action;

import org.game.encounters.Encounter;
import org.game.encounters.RandomEncounterGenerator;
import org.game.state.GameState;

public class Movement {
	
	private RandomEncounterGenerator randomEncounterGenerator;
	
	public void moveNorth(GameState gameState) {
		resolveMovementEncounters(gameState);
		gameState.setyPosition(gameState.getyPosition() + 1);
	}
	
	public void moveEast(GameState gameState) {
		resolveMovementEncounters(gameState);
		gameState.setxPosition(gameState.getxPosition() + 1);
	}
	
	public void moveWest(GameState gameState) {
		resolveMovementEncounters(gameState);
		gameState.setxPosition(gameState.getxPosition() - 1);
	}
	
	protected void resolveMovementEncounters (GameState gameState) {
		Encounter encounter = randomEncounterGenerator.rollEncounter();
		encounter.resolveEncounter(gameState);
	}
	
	public void moveSouth(GameState gameState) {
		resolveMovementEncounters(gameState);
		gameState.setyPosition(gameState.getyPosition() - 1);
	}
	
	public RandomEncounterGenerator getRandomEncounterGenerator() {
		return randomEncounterGenerator;
	}

	public void setRandomEncounterGenerator(RandomEncounterGenerator randomEncounterGenerator) {
		this.randomEncounterGenerator = randomEncounterGenerator;
	}


}
