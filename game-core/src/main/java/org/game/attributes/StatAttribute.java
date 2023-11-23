package org.game.attributes;

public enum StatAttribute {
	
	STRENGTH("Strength"), DEXTERITY("Dexterity"), CONSTITUTION("Constitution"), WISDOM("Wisdom"), INTELLIGENCE("Intelligence");
	
	String name;
	
	private StatAttribute(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}

}
