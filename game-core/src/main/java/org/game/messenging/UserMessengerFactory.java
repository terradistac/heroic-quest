package org.game.messenging;

public class UserMessengerFactory {
	
	public UserMessenger generateSystemOutUserMessenger() {
		return new SystemOutUserMessenger();
	}

}
