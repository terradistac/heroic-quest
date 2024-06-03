package org.game.items;

public abstract class Item {
	
	public Item (String name) {
		this.name = name;
	}
	
	String name;
	
	public String getName() {
		return this.name;
	}

	@Override
	public boolean equals(Object o) {
		if ((o != null) && (o instanceof Item) && (((Item) o).getName().equals(this.getName()))) {
			return true;
		}
		return false;
	}

}
