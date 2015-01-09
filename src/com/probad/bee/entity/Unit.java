package src.com.probad.bee.entity;

import src.com.probad.bee.world.EntityWorld;
import src.com.probad.bee.gui.*;

import java.math.*;
import java.util.Random;

import org.lwjgl.util.vector.*;

public abstract class Unit extends HealthEntity {

	protected double targetX, targetY;

	protected Entity target;
	protected boolean noTarget;
	protected boolean entTarget;
	protected boolean atTarget = false;

	protected boolean allTarget = true;

	protected double speed;
	protected double maxSpeed;

	protected double maxAcceleration;

	protected double meleeDamage = 10;

	protected Random rand = new Random();
	
	protected double collisionForceMultiplier = 20;


	public Unit(double x, double y, double z, int team, EntityWorld world) {
		super(x, y, z, team, world);

		health = getMaxHealth();
		entityType = EntityType.Unit;

	}

	@Override
	public boolean update(int deltaMS) {
		// code to move to target
		accelerateToTarget();
		applyFriction(0.05);

		x += Math.min(velocity.x, maxSpeed);
		y += Math.min(velocity.y, maxSpeed);
		z += Math.min(velocity.z, maxSpeed);
		
		if(entTarget) {
			if(target.isRemoved()) {
				noTarget = true;
			}
		}
		if(getDistanceToTarget()<2) {
			atTarget = true;
		}

		return super.update(deltaMS);
	}

	public abstract void render(Screen s, double camX, double camY);

	// accelerate to target
	//
	// accelerates the entity in the direction of the target
	public void accelerateToTarget() {
		if(!atTarget) {
			double ax, ay;
			if(entTarget) {
				if(!target.isRemoved()) {
					double[] dest = world.getPathing(this, target);
					targetX = dest[0];
					targetY = dest[1];
				}
			}else {
				double dx = targetX-x;
				double dy = targetY-y;

				double max = Math.abs(dx)+Math.abs(dy);

				ax = (dx/max)*maxAcceleration;
				ay = (dy/max)*maxAcceleration;

				push(new Vector3f((float)ax, (float)ay, 0f));
			}
		}
	}

	@Override
	public void onCollide(Entity entity) {
		if(entity.isFixedPosition()) {
			resolveCollisionWithFixedEntity(entity);
		}
		if(entity.team == team) {
			
			// TESTING
			collisionForceMultiplier = 0.001;
			
			// friendly. Push away.
			double radius = getCollisionRadius() + entity.getCollisionRadius();
			double dx = (entity.x - x);
			double dy = (entity.y - y) * 2;
			double angle = Math.atan2(dy, dx);
			double distScale = 1.0 - Math.sqrt(dx * dx + dy * dy) / radius;
			velocity.x += -Math.cos(angle) * distScale * 2 * collisionForceMultiplier;
			velocity.y += -Math.sin(angle) * distScale * 2 * collisionForceMultiplier;
			entity.velocity.x += Math.cos(angle) * distScale * 2 * collisionForceMultiplier;
			entity.velocity.y += Math.sin(angle) * distScale * 2 * collisionForceMultiplier;
		}else {
			if(allTarget || entity == target) {
				((HealthEntity) entity).hurt(meleeDamage);
			}
		}
	}

	public double getMeleeDamage() {
		return meleeDamage;
	}

	// set target
	//
	// sets the target of this entity
	// can be either another entity or a set of coordinates
	public void setTarget(Entity ent) {
		target = ent;
		entTarget = true;
		atTarget = false;
	}
	public void setTarget(double x, double y) {
		targetX = x;
		targetY = y;
		entTarget = false;
		atTarget = false;
	}

	public void clearTarget() {
		noTarget = true;
		atTarget = false;
		entTarget = false;
	}

	public boolean isAtTarget() {
		return atTarget;
	}

	public double getDistanceToTarget() {
		return Math.sqrt((x-targetX)*(x-targetX)+(y-targetY)*(y-targetY));
	}

}
