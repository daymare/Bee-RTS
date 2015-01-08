package com.probad.bee.entity;

import org.lwjgl.util.vector.Vector3f;
import org.lwjgl.util.vector.Vector2f;

import com.probad.bee.world.EntityWorld;
import com.probad.bee.gui.*;

public abstract class Projectile extends Entity {
	protected int damage;
    protected Vector3f direction;
    protected double speed; // Why do we have this?
    protected double blastRadius;
    Vector2f bulletOffset;
	
    protected Projectile(double x, double y, double z, int team, Vector3f direction, double speed, EntityWorld world) {
        super(x, y, z, team, world);
        this.speed = speed;
        this.direction = direction;
    }
    
    
	@Override
	public boolean update(int deltaMS) {
		x += direction.x * speed * deltaMS * .001f;
        y += direction.y * speed * deltaMS * .001f;
        z += direction.z * speed * deltaMS * .001f;
        
        if(!isRemoved() && y>0) {
        	return true;
        }else {
        	onDeath();
        	return false;
        }
	}
	
	public abstract void render(Screen s);
	
	protected void onDeath() {
        double bulletY = Math.max(0, y);
    }
	
	@Override
    public void onCollide(Entity entity) {
        if (!isRemoved()) {
            // ZUBZUB collision code
        }
    }
	
	@Override
    public double getCollisionRadius() {
        return 8;
    }

    @Override
    public double getEntityHeight() {
        return 10.0f;
    }
	
}
