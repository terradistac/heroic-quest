package org.game.state;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.game.attributes.StatAttribute;
import org.junit.jupiter.api.Test;

public class CharacterLevelerTest {
	
	private CharacterLeveler leveler = new CharacterLeveler();
	
	@Test
	public void testSetAttributeToValue() {
		GameCharacter character = new GameCharacter();
		leveler.setCharacter(character);
		leveler.setAttributeToValue(StatAttribute.CONSTITUTION, 8);
		
		assertEquals(8, character.getStatAttributes().get(StatAttribute.CONSTITUTION));
	}
	
	@Test
	public void testIncrementAttributeByValue() {
		GameCharacter character = new GameCharacter();
		leveler.setCharacter(character);
		
		assertEquals(0, character.getStatAttributes().get(StatAttribute.CONSTITUTION));
		
		leveler.incrementAttributeByValue(StatAttribute.CONSTITUTION, 2);
		assertEquals(2, character.getStatAttributes().get(StatAttribute.CONSTITUTION));
	}

}
