package src.com.probad.bee.gui;

import src.com.probad.bee.*;

public class Tilemapper extends Bitmap {
	
	public int width;
	public int height;
	public int tile_display_width;
	public int tile_display_height;
	
	public Map map;
	
	public final int TILE_WIDTH = 32;
	public final int TILE_HEIGHT = 32;
	public final int TILE_BUFFER_SIZE = 2;
	
	public Tilemapper(int width, int height, Map map) {
		super(width, height);
		this.map = map;
		this.tile_display_width = (int) Math.floor(width/TILE_WIDTH);
		this.tile_display_height = (int) Math.floor(height/TILE_HEIGHT);
	}
	
	public void render(int xOff, int yOff) {
		int display_offset_x = xOff%TILE_WIDTH;
		int display_offset_y = yOff%TILE_HEIGHT;
		int firstTile_x = (int) Math.floor(xOff/TILE_WIDTH);
		int firstTile_y = (int) Math.floor(yOff/TILE_HEIGHT);
		for(int y = 0; y < tile_display_height+TILE_BUFFER_SIZE; y++) {
			for(int x = 0; x < tile_display_width+TILE_BUFFER_SIZE; x++) {
				int tile = map.getTile(x+firstTile_x, y+firstTile_y);
				if(tile >= 0 && tile <= 22) {
					this.draw(Texture.tiles[tile], x*TILE_WIDTH-display_offset_x, y*TILE_HEIGHT-display_offset_y);
				}
			}
		}
	}
	
}
