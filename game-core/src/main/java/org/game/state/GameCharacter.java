package org.game.state;

import java.util.HashMap;
import java.util.Map;

public class GameCharacter {
	
	private String name;
	private int healthPoints;
	private Map<String, Integer> characterAttributes = new HashMap<>();

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getHealthPoints() {
		return healthPoints;
	}
	public void setHealthPoints(int healthPoints) {
		this.healthPoints = healthPoints;
	}
	
	public Map<String, Integer> getCharacterAttributes() {
		return characterAttributes;
	}
	public void setCharacterAttributes(Map<String, Integer> characterAttributes) {
		this.characterAttributes = characterAttributes;
	}

}
