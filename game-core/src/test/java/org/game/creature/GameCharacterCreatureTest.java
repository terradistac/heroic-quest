package org.game.creature;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.game.attributes.StatAttribute;
import org.game.state.GameCharacter;
import org.junit.jupiter.api.Test;

public class GameCharacterCreatureTest {
	
	@Test
	public void testGetGameCharacterAttribute() {
		GameCharacter gameCharacter = new GameCharacter();
		gameCharacter.getStatAttributes().put(StatAttribute.CONSTITUTION, 5);
		
		GameCharacterCreature<GameCharacter> gameCharacterCreature = new GameCharacterCreature<>(gameCharacter);
		
		assertEquals(5, gameCharacterCreature.getAttributeModifer(StatAttribute.CONSTITUTION));
		assertEquals(0, gameCharacterCreature.getAttributeModifer(StatAttribute.DEXTERITY));
	}
	
	@Test
	public void testGetGameCharacterMeleeAttackModifier() {
		GameCharacter gameCharacter = new GameCharacter();
		gameCharacter.getStatAttributes().put(StatAttribute.STRENGTH, 5);
		
		GameCharacterCreature<GameCharacter> gameCharacterCreature = new GameCharacterCreature<>(gameCharacter);
		
		assertEquals(5, gameCharacterCreature.getMeleeAttackModifier());
	}
	
	@Test
	public void testGetDefenseThreshold() {
		GameCharacter gameCharacter = new GameCharacter();
		
		GameCharacterCreature<GameCharacter> gameCharacterCreature = new GameCharacterCreature<>(gameCharacter);
		
		assertEquals(10, gameCharacterCreature.getDefenseThreshold());
		gameCharacter.setArmorClass(11);
		assertEquals(11, gameCharacterCreature.getDefenseThreshold());
	}

}
