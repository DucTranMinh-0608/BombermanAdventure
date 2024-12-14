package oop.entities.tile.item;

import oop.Game;
import oop.entities.Entity;
import oop.entities.character.Bomber;
import oop.graphics.Sprite;
import oop.sound.Sound;

public class BombItem extends Item {

	public BombItem(int x, int y, Sprite sprite) {
		super(x, y, sprite);
	}

	@Override
	public boolean collide(Entity e) {
		// TODO: xử lý Bomber ăn Item
            if (e instanceof Bomber) {
                
                Sound.play("Item");
                Game.addBombRate(1);
                remove();
            }
        return false;
	}

}
