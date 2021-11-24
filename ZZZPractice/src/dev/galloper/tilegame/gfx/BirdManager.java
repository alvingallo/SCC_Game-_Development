package dev.galloper.tilegame.gfx;

import java.awt.Graphics;
import java.util.ArrayList;

import dev.galloper.tilegame.Game;

public class BirdManager {
	private Game game;
	private ArrayList<Bird> birds;
	
	public BirdManager(Game game) {
		this.game = game;
		birds = new ArrayList<Bird>();
	}
	
	public void tick() {
		for(int i = 0; i < birds.size(); i++) {
			Bird b = birds.get(i);
			b.tick();
			if(b.getX() < 50)
				removeBird(b);
		}
	}
	
	public void render(Graphics g) {
		for(Bird b : birds) {
			b.render(g);
		}
	}
	
	public void addBird(Bird b) {
		birds.add(b);
	}
	
	public void removeBird(Bird b) {
		birds.remove(b);
	}
}
