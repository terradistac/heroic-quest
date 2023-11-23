package org.game.items;

import java.util.HashMap;
import java.util.Random;

import org.game.state.StatAttribute;

public class CommonItemGenerator implements ItemGenerator {

	@Override
	public Item rollForRandomItem() {
		Item generatedItem;
		
		if (this.getRandomNumber() <= 2 ) {
			generatedItem = generatePotion();
		} else if (this.getRandomNumber() < 5 && this.getRandomNumber() > 2) {
			generatedItem = generateEquipment();
		} else {
			generatedItem = generateJunk();
		}
		
		return generatedItem;
	}
	
	protected ConsumableItem generatePotion() {
		ConsumableItem potion = new ConsumableItem("Minor Potion of Healing", 2);
		return potion;
	}
	
	protected EquipmentItem generateEquipment() {
		HashMap<StatAttribute, Integer> stats = new HashMap<>();
		stats.put(StatAttribute.STRENGTH, 1);
		EquipmentItem sword = new EquipmentItem("Sword +1", stats);
		return sword;
	}
	
	protected JunkItem generateJunk() {
		return new JunkItem("Old Boot");
	}

	protected int getRandomNumber() {
		Random random = new Random();
		int randomResult = random.nextInt(1, 10);
		return randomResult;
	}

}
