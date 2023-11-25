package org.game.action;

import org.easymock.EasyMock;
import org.game.messenging.UserMessenger;
import org.game.state.GameState;
import org.junit.jupiter.api.Test;

public class MessengerMovementWrapperTest {
	
	UserMessenger messenger = EasyMock.createMock(UserMessenger.class);
	Movement movement = EasyMock.createMock(Movement.class);
	MessengerMovementWrapper messengerMovement = new MessengerMovementWrapper(movement);
	GameState gameState = new GameState();
	
	@Test
	public void testMoveNorth() {
		messenger.notifyUser(MessengerMovementWrapper.MOVE + MessengerMovementWrapper.NORTH + MessengerMovementWrapper.PERIOD);
		EasyMock.expectLastCall();
		
		movement.moveNorth(gameState);
		EasyMock.expectLastCall();
		
		EasyMock.replay(messenger, movement);
		messengerMovement.setUserMessenger(messenger);
		
		messengerMovement.moveNorth(gameState);
		EasyMock.verify(messenger);
		EasyMock.reset(messenger, movement);
	}
	
	@Test
	public void testMoveSouth() {
		messenger.notifyUser(MessengerMovementWrapper.MOVE + MessengerMovementWrapper.SOUTH + MessengerMovementWrapper.PERIOD);
		EasyMock.expectLastCall();
		
		movement.moveSouth(gameState);
		EasyMock.expectLastCall();
		
		EasyMock.replay(messenger, movement);
		
		messengerMovement.setUserMessenger(messenger);
		
		messengerMovement.moveSouth(gameState);
		EasyMock.verify(messenger);
		EasyMock.reset(messenger, movement);
	}
	
	@Test
	public void testMoveEast() {
		messenger.notifyUser(MessengerMovementWrapper.MOVE + MessengerMovementWrapper.EAST + MessengerMovementWrapper.PERIOD);
		EasyMock.expectLastCall();
		
		movement.moveEast(gameState);
		EasyMock.expectLastCall();
		
		EasyMock.replay(messenger, movement);
		
		messengerMovement.setUserMessenger(messenger);
		
		messengerMovement.moveEast(gameState);
		EasyMock.verify(messenger);
		EasyMock.reset(messenger, movement);
	}
	
	@Test
	public void testMoveWest() {
		messenger.notifyUser(MessengerMovementWrapper.MOVE + MessengerMovementWrapper.WEST + MessengerMovementWrapper.PERIOD);
		EasyMock.expectLastCall();
		
		movement.moveWest(gameState);
		EasyMock.expectLastCall();
		
		EasyMock.replay(messenger, movement);
		
		messengerMovement.setUserMessenger(messenger);
		
		messengerMovement.moveWest(gameState);
		EasyMock.verify(messenger);
		EasyMock.reset(messenger, movement);
	}

}
