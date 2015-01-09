package com.probad.bee.gui;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import com.probad.bee.gui.*;

public class Texture {
	public static Bitmap[] tiles = loadTiles(22);
	
	// loads the image in res into the Bitmap
	public static Bitmap loadBitmap(String fileName) {
		try {
			BufferedImage img = ImageIO.read(Texture.class.getResource("/" + fileName));
			int w = img.getWidth();
			int h = img.getHeight();

			Bitmap result = new Bitmap(w, h);
			img.getRGB(0, 0, w, h, result.pixels, 0, w);

			return result;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public static Bitmap[] loadTiles(int range) {
		Bitmap[] tiles = new Bitmap[range+1];
		for(int n = 0; n <= range; n++) {
			tiles[n] = loadBitmap(n+".png");
		}
		return tiles;
	}
	

	// Get ARGB from int color
	public static int getCol(int c) {
		int r = (c >> 16) & 0xff;
		int g = (c >> 8) & 0xff;
		int b = (c) & 0xff;

		r = r * 0x55 / 0xff;
		g = g * 0x55 / 0xff;
		b = b * 0x55 / 0xff;

		return r << 16 | g << 8 | b;
	}
}
