package dev.galloper.tilegame;

public class Launcher {
	public static void main(String[] args) {
		Game game = new Game("My Game", 640, 480);
		game.start();
	}
}
