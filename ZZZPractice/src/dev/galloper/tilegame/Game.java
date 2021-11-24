package dev.galloper.tilegame;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import dev.galloper.tilegame.display.Display;
import dev.galloper.tilegame.gfx.Assets;
import dev.galloper.tilegame.gfx.Bird;
import dev.galloper.tilegame.gfx.BirdManager;
import dev.galloper.tilegame.input.MouseManager;

public class Game implements Runnable {

	private Display display;
	private int width, height;
	private String title;

	private boolean running = false;
	private Thread thread;

	private BufferStrategy bs;
	private Graphics g;

	private MouseManager mouseManager;
	private BirdManager birdManager;

	public Game(String title, int width, int height) {
		this.title = title;
		this.width = width;
		this.height = height;

		mouseManager = new MouseManager();
		birdManager = new BirdManager(this);
	}

	private void init() {
		display = new Display(title, width, height);
		Assets.init();
		display.getFrame().addMouseListener(mouseManager);
		display.getFrame().addMouseMotionListener(mouseManager);
		display.getCanvas().addMouseListener(mouseManager);
		display.getCanvas().addMouseMotionListener(mouseManager);

		//birdManager.AddBird(new Bird(100, 100));
	}

	private void tick() {
		birdManager.tick();
		if (mouseManager.isLeftPressed()) {
			birdManager.addBird(new Bird(mouseManager.getMouseX(), mouseManager.getMouseY()));
			mouseManager.setLeftPressed(false);
		}
		
	}

	private void render(Graphics g) {
		bs = display.getCanvas().getBufferStrategy();
		if (bs == null) {
			display.getCanvas().createBufferStrategy(3);
			return;
		}

		g = bs.getDrawGraphics();

		g.clearRect(0, 0, 640, 480);

		// Draw here
		// g.drawImage(Assets.bird[0], 0, 0, null);

		birdManager.render(g);

		// End drawing
		bs.show();
		g.dispose();
	}

	public void run() {

		init();

		int fps = 30;
		double timePerTick = 1000000000 / fps;
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();
		long timer = 0;
		int ticks = 0;

		while (running) {
			now = System.nanoTime();
			delta += (now - lastTime) / timePerTick;
			timer += now - lastTime;
			lastTime = now;

			if (delta >= 1) {
				tick();
				render(g);
				ticks++;
				delta--;
			}

			if (timer >= 1000000000) {
				System.out.println();
				ticks = 0;
				timer = 0;
			}
		}

		stop();
	}

	public synchronized void start() {
		if (running)
			return;
		running = true;
		thread = new Thread(this);
		thread.start();
	}

	public synchronized void stop() {
		if (!running)
			return;
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
