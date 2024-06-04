package org.game.state;

import org.game.attributes.StatAttribute;

public class CharacterLeveler {
	
	private GameCharacter gameCharacter;

	public void setCharacter(GameCharacter character) {
		this.gameCharacter = character;
	}

	public void setAttributeToValue(StatAttribute statAttribute, int value) {
		gameCharacter.getStatAttributes().put(statAttribute, value);
	}

	public void incrementAttributeByValue(StatAttribute statAttribute, int incrementValue) {
		Integer oldValue = gameCharacter.getStatAttributes().get(statAttribute);
		gameCharacter.getStatAttributes().put(statAttribute, oldValue + incrementValue);
	}

}
