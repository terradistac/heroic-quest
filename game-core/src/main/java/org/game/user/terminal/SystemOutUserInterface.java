package org.game.user.terminal;

import java.util.Scanner;

import org.game.messenging.UserMessenger;
import org.game.messenging.UserMessengerFactory;
import org.game.rules.GameRules;
import org.game.state.GameState;
import org.game.user.UserOptions;

public class SystemOutUserInterface {

	private static UserOptions userOptions;
	private static Scanner sc;

	public static void main(String[] arguments) throws InterruptedException {
		GameState.getInstance().setRun(true);
		
		UserMessenger messenger = new UserMessengerFactory().generateSystemOutUserMessenger();
		
		messenger.notifyUser("Welcome to the Heroic Quest!");
		messenger.notifyUser("Type \"Help\" for available commands." );
		
		setUserOptions(new DefaultSystemOutUserOptions());

		while (GameState.getInstance().isRun()) {
			
			UserOptions eventTriggeredUserOptions = GameRules.determineAvailableUserOptions();
			if (eventTriggeredUserOptions != null) {
				setUserOptions(eventTriggeredUserOptions);
			}
			getUserOptions().provideAvailableUserInputOptions();
		}
	}

	public static UserOptions getUserOptions() {
		return userOptions;
	}

	public static void setUserOptions(UserOptions userOptions) {
		SystemOutUserInterface.userOptions = userOptions;
	}

	public static Scanner getScanner() {
		if (sc == null) {
			sc = new Scanner(System.in);
		}
		return sc;
	}

}
