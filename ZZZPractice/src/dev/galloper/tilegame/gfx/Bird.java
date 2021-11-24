package dev.galloper.tilegame.gfx;

import java.awt.Graphics;

public class Bird {
	private Animation flyAnim;
	private int x, y;
	public Bird(int x, int y) {
		this.x = x;
		this.y = y;
		flyAnim = new Animation(300, Assets.bird);
	}
	
	public void tick() {
		x--;
		flyAnim.tick();
	}
	
	public void render(Graphics g) {
		g.drawImage(flyAnim.getCurrentFrame(), x, y, null);
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	
}
