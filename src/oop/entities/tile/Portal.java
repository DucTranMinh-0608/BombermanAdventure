package oop.entities.tile;

import oop.Board;
import oop.entities.Entity;
import oop.entities.character.Bomber;
import oop.graphics.Sprite;
import oop.sound.Sound;

public class Portal extends Tile {
        protected Board _board;
	public Portal(int x, int y,Board board, Sprite sprite) {
		super(x, y, sprite);
                _board = board;
	}
	
	@Override
	public boolean collide(Entity e) {//xu li khi 2 entity va cham
                                          //true cho di qua
                                          //false khong cho di qua
		// TODO: xử lý khi Bomber đi vào
                if(e instanceof Bomber ) {
			
			if(_board.detectNoEnemies() == false)
				return false;
			
			if(e.getXTile() == getX() && e.getYTile() == getY()) {
				if(_board.detectNoEnemies()){
					_board.nextLevel();
                                        Sound.play("CRYST_UP");
                                }
			}
			
			return true;
		}
		
		return true;
	}

}
