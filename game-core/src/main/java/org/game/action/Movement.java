package org.game.action;

import org.game.state.GameState;

public interface Movement {

	public void moveNorth(GameState gameState);
	
	public void moveSouth(GameState gameState);
	
	public void moveWest(GameState gameState);
	
	public void moveEast(GameState gameState);
}
