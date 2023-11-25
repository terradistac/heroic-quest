package org.game.messenging;

public class SystemOutUserMessenger implements UserMessenger {

	@Override
	public void notifyUser(String message) {
		System.out.print(message);
	}

}
