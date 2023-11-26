package org.game.action.movement;

import org.game.messenging.UserMessenger;
import org.game.state.GameState;

public class MessengerMovementWrapper implements Movement {
	
	protected static final String MOVE = "You moved one space ";
	protected static final String NORTH = "North";
	protected static final String SOUTH = "South";
	protected static final String EAST = "East";
	protected static final String WEST = "West";
	protected static final String PERIOD = ".";
	
	public MessengerMovementWrapper(Movement movement) {
		this.movement = movement;
	}
	
	private Movement movement;
	private UserMessenger userMessenger;

	@Override
	public void moveNorth(GameState gameState) {
		movement.moveNorth(gameState);
		userMessenger.notifyUser(MOVE + NORTH + PERIOD);
	}

	@Override
	public void moveSouth(GameState gameState) {
		movement.moveSouth(gameState);
		userMessenger.notifyUser(MOVE + SOUTH + PERIOD);
	}

	@Override
	public void moveWest(GameState gameState) {
		movement.moveWest(gameState);
		userMessenger.notifyUser(MOVE + WEST + PERIOD);
	}

	@Override
	public void moveEast(GameState gameState) {
		movement.moveEast(gameState);
		userMessenger.notifyUser(MOVE + EAST + PERIOD);
	}

	public UserMessenger getUserMessenger() {
		return userMessenger;
	}

	public void setUserMessenger(UserMessenger userMessenger) {
		this.userMessenger = userMessenger;
	}

}
