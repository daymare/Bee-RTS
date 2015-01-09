package src.com.probad.bee.gui;

import src.com.probad.bee.*;

public class Screen extends Bitmap {
	
	public Tilemapper tilemap;
	
	public Screen(int width, int height) {
		super(width, height);
		this.tilemap = new Tilemapper(width, height, new Map(32,32));
	}
	
	public void render(Game game) {
		// Graphics loop
		//System.out.println("X: " + game.cam_x + " Y: " + game.cam_y);
		game.cam_x = Math.min(Math.max(game.cam_x, 0), tilemap.map.map_width*tilemap.TILE_WIDTH-tilemap.width*2);
		game.cam_y = Math.min(Math.max(game.cam_y, 0), tilemap.map.map_height*tilemap.TILE_HEIGHT-tilemap.height*2);
		tilemap.render((int)game.cam_x, (int)game.cam_y);
		draw(tilemap,0,0);
	}
}
