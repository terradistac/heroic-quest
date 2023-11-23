package org.game.items;

public abstract class Item {
	
	public Item (String name) {
		this.name = name;
	}
	
	String name;
	
	public String getName() {
		return this.name;
	}

}
