package org.game.user;

import java.util.Scanner;

import org.game.action.BasicInventoryManager;
import org.game.action.BasicMovement;
import org.game.action.InventoryManager;
import org.game.action.MessengerInventoryManagerWrapper;
import org.game.action.MessengerMovementWrapper;
import org.game.action.Movement;
import org.game.action.WildernessMovementWrapper;
import org.game.encounters.CommonRandomEncounterGenerator;
import org.game.messenging.UserMessenger;
import org.game.messenging.UserMessengerFactory;
import org.game.state.GameState;

public class UserInterface {
	
	public static void main(String[] arguments) throws InterruptedException {
		GameState gameState = new GameState();
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
				movement.moveNorth(gameState);
			}
			if ("S".equalsIgnoreCase(input)) {
				movement.moveSouth(gameState);
			}
			if ("E".equalsIgnoreCase(input)) {
				movement.moveEast(gameState);
			}
			if ("W".equalsIgnoreCase(input)) {
				movement.moveWest(gameState);
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
		CommonRandomEncounterGenerator encounterGen = new CommonRandomEncounterGenerator();
		
		WildernessMovementWrapper movementWrapper = new WildernessMovementWrapper(basicMovement);
		movementWrapper.setRandomEncounterGenerator(encounterGen);
		
		MessengerMovementWrapper movement = new MessengerMovementWrapper(movementWrapper);
		movement.setUserMessenger(messenger);
		return movement;
	}
	
}
