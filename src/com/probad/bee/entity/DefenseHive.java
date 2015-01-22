/*
 * Defense Hive
 * 
 * An entity created by upgrading a Basic Hive
 * Has all the functionality of a basic hive, 
 * but also launches projectiles at enemies
 * and can be upgraded further into a <better defensive hive>
 * 
 * TODO fix description when we make the next tier of hives
 * 
 */

package com.probad.bee.entity;

import com.probad.bee.gui.Texture;
import com.probad.bee.world.EntityWorld;

public class DefenseHive extends BasicHive {
	
	
	public DefenseHive(double x, double y, double z, int team, EntityWorld world) {
		super(x, y, z, team, world);
		
		image = Texture.loadBitmap("DefenseHive.png");
		
	}
	
	@Override
	public boolean update(int deltaMS) {
		
		return health>0;
	}

}
