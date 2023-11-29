package org.game.user.terminal;

import java.util.Scanner;

import org.game.action.inventory.BasicInventoryManager;
import org.game.action.inventory.InventoryManager;
import org.game.action.inventory.MessengerInventoryManagerWrapper;
import org.game.action.movement.BasicMovement;
import org.game.action.movement.MessengerMovementWrapper;
import org.game.action.movement.Movement;
import org.game.action.movement.WildernessMovementWrapper;
import org.game.event.CommonRandomEventGenerator;
import org.game.messenging.UserMessenger;
import org.game.messenging.UserMessengerFactory;
import org.game.state.GameState;
import org.game.user.UserOptions;

public class DefaultSystemOutUserOptions implements UserOptions {

	@Override
	public void provideAvailableUserInputOptions() {
		UserMessenger messenger = new UserMessengerFactory().generateSystemOutUserMessenger();
		Movement movement = buildMovement(messenger);

		Scanner sc = SystemOutUserInterface.getScanner();
		String input = "";
		input = sc.next();

		if ("quit".contentEquals(input)) {
			GameState.getInstance().setRun(false);
		}
		if ("N".equalsIgnoreCase(input)) {
			movement.moveNorth();
		}
		if ("S".equalsIgnoreCase(input)) {
			movement.moveSouth();
		}
		if ("E".equalsIgnoreCase(input)) {
			movement.moveEast();
		}
		if ("W".equalsIgnoreCase(input)) {
			movement.moveWest();
		}
		if ("Inventory".equalsIgnoreCase(input) || "i".equalsIgnoreCase(input)) {
			SystemOutUserInterface.setUserOptions(new InventorySystemOutUserOptions());
		}
		
		if ("help".equalsIgnoreCase(input)) {
			messenger.notifyUser("Type N/S/E/W to move North, South, East or West.");
			messenger.notifyUser("Type \"(I)nventory\" to access your inventory.");
			messenger.notifyUser("Type \"Quit\" to quit the game.");
		}

	}

	private static Movement buildMovement(UserMessenger messenger) {
		BasicMovement basicMovement = new BasicMovement();
		CommonRandomEventGenerator encounterGen = new CommonRandomEventGenerator();

		WildernessMovementWrapper movementWrapper = new WildernessMovementWrapper(basicMovement);
		movementWrapper.setRandomEncounterGenerator(encounterGen);

		MessengerMovementWrapper movement = new MessengerMovementWrapper(movementWrapper);
		movement.setUserMessenger(messenger);
		return movement;
	}

}
