package dev.galloper.tilegame.gfx;

import java.awt.image.BufferedImage;

public class Assets {
	
	private static final int width = 32, height = 32;
	
	public static BufferedImage[] bird;
	
	public static void init() {
		SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/textures/mario_sprite.png"));
	
		bird = new BufferedImage[2];
		bird[0] = sheet.crop(107, 69, 33, 24);
		bird[1] = sheet.crop(141, 69, 25, 24);
	}
}
