package org.game.action.movement;

import org.game.encounter.Encounter;
import org.game.event.RandomEventGenerator;

public class WildernessMovementWrapper implements Movement {
	
	private RandomEventGenerator randomEncounterGenerator;
	private Movement movement;
	
	public WildernessMovementWrapper (Movement movement) {
		this.movement = movement;
	}
	
	@Override
	public void moveNorth() {
		resolveMovementEncounters();
		this.movement.moveNorth();
	}
	
	@Override
	public void moveEast() {
		resolveMovementEncounters();
		this.movement.moveEast();
	}
	
	@Override
	public void moveWest() {
		resolveMovementEncounters();
		this.movement.moveWest();
	}
	
	@Override
	public void moveSouth() {
		resolveMovementEncounters();
		this.movement.moveSouth();
	}
	
	protected void resolveMovementEncounters () {
		Encounter encounter = randomEncounterGenerator.rollEncounter();
		encounter.resolveEncounter();
	}
	
	public RandomEventGenerator getRandomEncounterGenerator() {
		return randomEncounterGenerator;
	}

	public void setRandomEncounterGenerator(RandomEventGenerator randomEncounterGenerator) {
		this.randomEncounterGenerator = randomEncounterGenerator;
	}


}
