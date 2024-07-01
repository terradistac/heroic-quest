package org.game.state;

import java.util.ArrayList;
import java.util.List;

import org.game.attributes.StatAttribute;
import org.game.items.Item;

public class GameState {
	
	private static GameState gameState;
	
	private GameState() {
		
	}
	
	public static GameState getInstance() {
		if (gameState == null) {
			gameState = new GameState();
		}
		return gameState;
	}
	
	private GameCharacter character = new GameCharacter();
	private List<Item> foundItems = new ArrayList<Item>();
	private Integer xPosition = 0;
	private Integer yPosition = 0;
	private boolean run;
	
	public GameCharacter getCharacter() {
		return character;
	}
	public void setCharacter(GameCharacter character) {
		this.character = character;
	}
	public List<Item> getFoundItems() {
		return foundItems;
	}
	public void setFoundItems(List<Item> items) {
		this.foundItems = items;
	}
	public Integer getxPosition() {
		return xPosition;
	}
	public void setxPosition(Integer xPosition) {
		this.xPosition = xPosition;
	}
	public Integer getyPosition() {
		return yPosition;
	}
	public void setyPosition(Integer yPosition) {
		this.yPosition = yPosition;
	}

	public boolean isRun() {
		return run;
	}

	public void setRun(boolean run) {
		this.run = run;
	}
	
	public void setupDefaultFighterClass() {
		character.getStatAttributes().put(StatAttribute.CONSTITUTION, 1);
		character.getStatAttributes().put(StatAttribute.DEXTERITY, 1);
		character.getStatAttributes().put(StatAttribute.STRENGTH, 3);
		character.setArmorClass(14);
	}

}
