package org.game.rules;

import org.game.state.GameState;
import org.game.user.FoundItemUserOptions;
import org.game.user.DefaultUserOptions;
import org.game.user.UserOptions;

public class GameRules {
	
	public static UserOptions determineAvailableUserOptions() {
		if (areItemsInQueue()) {
			return new FoundItemUserOptions();
		}
		return new DefaultUserOptions();
	}
	protected static boolean areItemsInQueue() {
		if (!(GameState.getInstance().getFoundItems().size() == 0)) {
			return true;
		} else
			return false;
	}
}
