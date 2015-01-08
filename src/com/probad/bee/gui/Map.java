package com.probad.bee.gui;

public class Map {
	
	public int map_width;
	public int map_height;
	//public int
	public int[][] map;
	
	public Map(int width, int height) {
		this.map_width = width;
		this.map_height = height;
		this.map = new int[width][height];
		this.fillMap();
	}
	
	public Map(int Map[][]) {
		this.map_width = 10;
		this.map_height = 10;
	}
	
	public int getTile(int x, int y) {
		if(inMap(x,y) == true) {
			return map[x][y];
		}
		return -1;
	}
	
	public boolean setTile(int tile, int x, int y) {
		if(inMap(x,y) == true) {
			map[x][y] = tile;
			return true;
		}
		return false;
	}
	
	public boolean inMap(int x, int y) {
		if(x >= 0 && x < map_width && y >= 0 && y < map_height) {
			return true;
		} else {
			return false;
		}
	}
	
	public void fillMap() {
		for(int y = 0; y < map_height; y++) {
			for(int x = 0; x < map_width; x++) {
				setTile((int)(Math.random()*22),x,y);
			}
		}
	}
}
