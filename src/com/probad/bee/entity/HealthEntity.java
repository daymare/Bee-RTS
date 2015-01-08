package com.probad.bee.entity;

import com.probad.bee.world.EntityWorld;
import com.probad.bee.gui.*;

public abstract class HealthEntity extends Entity {
	protected double health;
	
	public HealthEntity(double x, double y, double z, int team, EntityWorld world) {
        super(x, y, z, team, world);
        health = getMaxHealth();
    }
	
	@Override
	public boolean update(int deltaMS) {
		
		return health>0;
	}
	
	
	public void hurt(double damage) {
        health -= damage;
        if (health < 0) {
            health = 0;
        }
    }
	
	public void heal(double amount) {
		health += amount;
		if (health > getMaxHealth()) {
            health = getMaxHealth();
        }
	}
	
	public double getHealth() {
        return health;
    }
	
	public double getMaxHealth() {
        return 100.0f;
    }
	
}
