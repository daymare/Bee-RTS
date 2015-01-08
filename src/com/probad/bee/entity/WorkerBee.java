package com.probad.bee.entity;

import com.probad.bee.gui.*;
import com.probad.bee.world.EntityWorld;

public class WorkerBee extends Unit {
	int targget = 0;
	
	public WorkerBee(double x, double y, double z, int team, EntityWorld world) {
		super(x, y, z, team, world);
		
		health = getMaxHealth();
		entityType = EntityType.Unit;
		
		maxSpeed = 20;
		maxAcceleration = 0.05;
		meleeDamage = 100;
		
		image = Texture.loadBitmap("TinyBee.png");
		
	}
	
	@Override
	public double getMaxHealth() {
		return 30;
	}
	
	@Override
	public boolean update(int deltaMS) {
		
		// TEST COMMANDS
		
		if(isAtTarget()) {
			switch(targget) {
			case 0:
				setTarget(500, 300);
				break;
			case 1:
				setTarget(0, 300);
				break;
			case 2:
				setTarget(500, 0);
				break;
			case 3:
				setTarget(20, 20);
				break;
			case 4:
				setTarget(250, 150);
				break;
			case 900:
				targget = -1;
				break;
			}
			
			if(targget>4) {
				setTarget(250, 150);
			}
			targget++;
		}
		
		
		// END
		
		
		
		return super.update(deltaMS);
	}
	
	@Override
	public double getCollisionRadius() {
		return 10.0;
	}
	
	@Override
	public void render(Screen s, double camX, double camY) {
		s.draw(image, (int)(x-camX), (int)(y-camY));
	}
	
	@Override
	public void onCollide(Entity entity) {
		super.onCollide(entity);
		if(entity.team!=team && (allTarget || entity == target)) {
			setRemoved();
		}
	}
}
