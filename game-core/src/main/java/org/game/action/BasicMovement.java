package org.game.action;

import org.game.state.GameState;

public class BasicMovement implements Movement {

	@Override
	public void moveNorth(GameState gameState) {
		gameState.setyPosition(gameState.getyPosition() + 1);
	}

	@Override
	public void moveSouth(GameState gameState) {
		gameState.setyPosition(gameState.getxPosition() - 1);
	}

	@Override
	public void moveWest(GameState gameState) {
		gameState.setxPosition(gameState.getxPosition() - 1);
	}

	@Override
	public void moveEast(GameState gameState) {
		gameState.setxPosition(gameState.getxPosition() + 1);
	}

}
