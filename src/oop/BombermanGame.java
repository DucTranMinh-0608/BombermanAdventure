package oop;

import oop.gui.Frame;
import oop.gui.GameMenuGui;
import oop.sound.Sound;

public class BombermanGame {
	public static void main(String[] args) {
		// Bắt đầu phát nhạc nền
		Sound.play("soundtrack");

		// Hiển thị menu
		GameMenuGui menu = new GameMenuGui();
		menu.showMenu(() -> {
			// Khi nhấn "Play", chạy game chính
			new Frame();
		});
	}
}
