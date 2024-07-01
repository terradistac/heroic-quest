package org.game.state;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Map;

import org.game.attributes.StatAttribute;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GameStateTest {
	
	@BeforeEach
	public void reset() {
		GameState gameState = GameState.getInstance();
		Map<StatAttribute, Integer> characterStats = gameState.getCharacter().getStatAttributes();
		
		gameState.setxPosition(0);
		gameState.setyPosition(0);
		characterStats.put(StatAttribute.INTELLIGENCE, 0);
		characterStats.put(StatAttribute.WISDOM, 0);
		characterStats.put(StatAttribute.CONSTITUTION, 0);
		characterStats.put(StatAttribute.DEXTERITY, 0);
		characterStats.put(StatAttribute.STRENGTH, 0);
		gameState.getCharacter().setArmorClass(10);
	}
	
	@Test
	public void testInitialDefaultSetup() {
		GameState gameState = GameState.getInstance();
		Map<StatAttribute, Integer> characterStats = gameState.getCharacter().getStatAttributes();
		
		assertEquals(0, gameState.getxPosition());
		assertEquals(0, gameState.getyPosition());
		assertEquals(0, characterStats.get(StatAttribute.CONSTITUTION));
		assertEquals(0, characterStats.get(StatAttribute.DEXTERITY));
		assertEquals(0, characterStats.get(StatAttribute.INTELLIGENCE));
		assertEquals(0, characterStats.get(StatAttribute.STRENGTH));
		assertEquals(0, characterStats.get(StatAttribute.WISDOM));
		assertEquals(10, gameState.getCharacter().getArmorClass());
	}
	
	@Test
	public void testSetupFighterCharacter() {
		GameState gameState = GameState.getInstance();
		gameState.setupDefaultFighterClass();
		
		Map<StatAttribute, Integer> characterStats = gameState.getCharacter().getStatAttributes();
		
		assertEquals(0, gameState.getxPosition());
		assertEquals(0, gameState.getyPosition());
		assertEquals(1, characterStats.get(StatAttribute.CONSTITUTION));
		assertEquals(1, characterStats.get(StatAttribute.DEXTERITY));
		assertEquals(0, characterStats.get(StatAttribute.INTELLIGENCE));
		assertEquals(3, characterStats.get(StatAttribute.STRENGTH));
		assertEquals(0, characterStats.get(StatAttribute.WISDOM));
		assertEquals(14, gameState.getCharacter().getArmorClass());
	}

}
