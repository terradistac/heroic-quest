package org.game.rules;

import org.game.state.GameState;
import org.game.user.UserOptions;
import org.game.user.terminal.FoundItemSystemOutUserOptions;

public class GameRules {
	
	public static UserOptions determineAvailableUserOptions() {
		if (areItemsInQueue()) {
			return new FoundItemSystemOutUserOptions();
		}
		return null;
	}
	protected static boolean areItemsInQueue() {
		if (!(GameState.getInstance().getFoundItems().size() == 0)) {
			return true;
		} else
			return false;
	}
}
