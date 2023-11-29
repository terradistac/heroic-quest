package org.game.user;

import org.game.messenging.UserMessenger;
import org.game.messenging.UserMessengerFactory;
import org.game.rules.GameRules;
import org.game.state.GameState;

public class UserInterface {

	private static UserOptions userOptions;

	public static void main(String[] arguments) throws InterruptedException {
		GameState.getInstance().setRun(true);
		
		UserMessenger messenger = new UserMessengerFactory().generateSystemOutUserMessenger();
		
		messenger.notifyUser("Welcome to the Heroic Quest!");
		messenger.notifyUser("Type \"Help\" for available commands." );

		while (GameState.getInstance().isRun()) {
			
			setUserOptions(GameRules.determineAvailableUserOptions());
			getUserOptions().provideAvailableUserInputOptions();

			Thread.sleep(100);
		}
	}

	public static UserOptions getUserOptions() {
		return userOptions;
	}

	public static void setUserOptions(UserOptions userOptions) {
		UserInterface.userOptions = userOptions;
	}

}
