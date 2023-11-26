package org.game.state;

import java.util.ArrayList;
import java.util.List;

import org.game.items.Item;

public class GameState {
	
	private GameCharacter character;
	private List<Item> items = new ArrayList<Item>();
	private Integer xPosition = 0;
	private Integer yPosition = 0;
	
	public GameCharacter getCharacter() {
		return character;
	}
	public void setCharacter(GameCharacter character) {
		this.character = character;
	}
	public List<Item> getItems() {
		return items;
	}
	public void setItems(List<Item> items) {
		this.items = items;
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

}
