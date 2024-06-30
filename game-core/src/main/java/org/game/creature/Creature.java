package org.game.creature;

import org.game.attributes.StatAttribute;

public interface Creature {
	
	public Integer getAttributeModifer(StatAttribute stat);
	
	public Integer getMeleeAttackModifier();
	
	public Integer getDefenseThreshold();

}
