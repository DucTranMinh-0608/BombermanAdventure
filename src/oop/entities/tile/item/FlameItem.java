package oop.entities.tile.item;

import oop.Game;
import oop.entities.Entity;
import oop.entities.character.Bomber;
import oop.graphics.Sprite;
import oop.sound.Sound;

public class FlameItem extends Item {

	public FlameItem(int x, int y, Sprite sprite) {
		super(x, y, sprite);
	}

	@Override
	public boolean collide(Entity e) {
		// TODO: xử lý Bomber ăn 
            if (e instanceof Bomber) {
                
                Sound.play("Item");
                Game.addBombRadius(1);
                remove();
        }
        return false;
	}
}
