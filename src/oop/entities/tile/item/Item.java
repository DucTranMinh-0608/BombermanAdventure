package oop.entities.tile.item;

import oop.entities.tile.Tile;
import oop.graphics.Sprite;

public abstract class Item extends Tile {
        protected int _duration = -1; //thoi gian cua item ,-1 la vo han
	protected boolean _active = false;
	protected int _level;
	public Item(int x, int y, Sprite sprite) {
		super(x, y, sprite);
	}
    
}
