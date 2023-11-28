package org.game.action.movement;

import org.easymock.EasyMock;
import org.game.messenging.UserMessenger;
import org.game.state.GameState;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

public class MessengerMovementWrapperTest {
	
	UserMessenger messenger = EasyMock.createMock(UserMessenger.class);
	Movement movement = EasyMock.createMock(Movement.class);
	MessengerMovementWrapper messengerMovement = new MessengerMovementWrapper(movement);
	GameState gameState = GameState.getInstance();
	
	@Test
	public void testMoveNorth() {
		messenger.notifyUser(MessengerMovementWrapper.MOVE + MessengerMovementWrapper.NORTH + MessengerMovementWrapper.PERIOD);
		EasyMock.expectLastCall();
		
		movement.moveNorth();
		EasyMock.expectLastCall();
		
		EasyMock.replay(messenger, movement);
		messengerMovement.setUserMessenger(messenger);
		
		messengerMovement.moveNorth();
		EasyMock.verify(messenger);
		EasyMock.reset(messenger, movement);
	}
	
	@Test
	public void testMoveSouth() {
		messenger.notifyUser(MessengerMovementWrapper.MOVE + MessengerMovementWrapper.SOUTH + MessengerMovementWrapper.PERIOD);
		EasyMock.expectLastCall();
		
		movement.moveSouth();
		EasyMock.expectLastCall();
		
		EasyMock.replay(messenger, movement);
		
		messengerMovement.setUserMessenger(messenger);
		
		messengerMovement.moveSouth();
		EasyMock.verify(messenger);
		EasyMock.reset(messenger, movement);
	}
	
	@Test
	public void testMoveEast() {
		messenger.notifyUser(MessengerMovementWrapper.MOVE + MessengerMovementWrapper.EAST + MessengerMovementWrapper.PERIOD);
		EasyMock.expectLastCall();
		
		movement.moveEast();
		EasyMock.expectLastCall();
		
		EasyMock.replay(messenger, movement);
		
		messengerMovement.setUserMessenger(messenger);
		
		messengerMovement.moveEast();
		EasyMock.verify(messenger);
		EasyMock.reset(messenger, movement);
	}
	
	@Test
	public void testMoveWest() {
		messenger.notifyUser(MessengerMovementWrapper.MOVE + MessengerMovementWrapper.WEST + MessengerMovementWrapper.PERIOD);
		EasyMock.expectLastCall();
		
		movement.moveWest();
		EasyMock.expectLastCall();
		
		EasyMock.replay(messenger, movement);
		
		messengerMovement.setUserMessenger(messenger);
		
		messengerMovement.moveWest();
		EasyMock.verify(messenger);
		EasyMock.reset(messenger, movement);
	}
	
	@AfterEach
	public void resetGameState() {
		GameState.getInstance().setxPosition(0);
		GameState.getInstance().setyPosition(0);
	}

}
