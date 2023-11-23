package org.game.items;

public class ConsumableItem extends Item {
	
	String name;
	int healingPotency;
	
	public ConsumableItem(String name, int healingPotency) {
		super(name);
		this.healingPotency = healingPotency;
	}
	
	public int getHealingPotency() {
		return this.healingPotency;
	}

}
