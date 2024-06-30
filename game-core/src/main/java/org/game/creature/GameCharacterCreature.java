package org.game.creature;

import org.game.attributes.StatAttribute;
import org.game.state.GameCharacter;

public class GameCharacterCreature<T extends GameCharacter> implements Creature {
	
	private T gameCharacter;
	
	public GameCharacterCreature (T gameCharacter) {
		this.gameCharacter = gameCharacter;
	}

	@Override
	public Integer getAttributeModifer(StatAttribute stat) {
		return gameCharacter.getStatAttributes().get(stat);
	}

	@Override
	public Integer getMeleeAttackModifier() {
		return gameCharacter.getStatAttributes().get(StatAttribute.STRENGTH);
	}

	@Override
	public Integer getDefenseThreshold() {
		return gameCharacter.getArmorClass();
	}

}
