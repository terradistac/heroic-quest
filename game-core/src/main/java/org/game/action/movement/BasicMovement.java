package org.game.action.movement;

import org.game.state.GameState;

public class BasicMovement implements Movement {

	@Override
	public void moveNorth() {
		GameState.getInstance().setyPosition(GameState.getInstance().getyPosition() + 1);
	}

	@Override
	public void moveSouth() {
		GameState.getInstance().setyPosition(GameState.getInstance().getxPosition() - 1);
	}

	@Override
	public void moveWest() {
		GameState.getInstance().setxPosition(GameState.getInstance().getxPosition() - 1);
	}

	@Override
	public void moveEast() {
		GameState.getInstance().setxPosition(GameState.getInstance().getxPosition() + 1);
	}

}
