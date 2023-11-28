package org.game.user;

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

public class UserInterface {
	
	public static void main(String[] arguments) throws InterruptedException {
		GameState gameState = GameState.getInstance();
		UserMessenger messenger = new UserMessengerFactory().generateSystemOutUserMessenger();
		
		InventoryManager inventoryManager = buildInventoryManager(messenger);
		Movement movement = buildMovement(messenger);
		
		boolean run = true;
		
		System.out.println("Type \"quit\" to quit");
		System.out.println("Type N to move North");
		System.out.println("Type S to move South");
		System.out.println("Type E to move East");
		System.out.println("Type W to move West");
		
		while (run) {
			String input = "";
			Scanner sc = new Scanner(System.in);
			input = sc.next();
			
			if ("quit".contentEquals(input)) {
				run = false;
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
			Thread.sleep(100);
		}
	}

	private static InventoryManager buildInventoryManager(UserMessenger messenger) {
		BasicInventoryManager basicInventoryManager = new BasicInventoryManager();
		MessengerInventoryManagerWrapper inventoryManager = new MessengerInventoryManagerWrapper(basicInventoryManager);
		inventoryManager.setUserMessenger(messenger);
		
		return basicInventoryManager;
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
