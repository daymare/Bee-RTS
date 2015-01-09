package src.com.probad.bee.entity;

import src.com.probad.bee.world.EntityWorld;
import src.com.probad.bee.gui.*;

public abstract class RangedUnit extends Unit {
	
	protected int range = 1;
	
	protected double rangedDamage;
	
	RangedUnit(double x, double y, double z, int team, EntityWorld world) {
		super(x, y, z, team, world);
	}
	
	@Override
	public boolean update(int deltaMS) {
		
		
		return super.update(deltaMS);
	}
	
	public abstract void render(Screen s, double camX, double camY);
	
	@Override
	public boolean collidesWith(Entity other) {
		return other!=this;
	}
	
	@Override
	public void onCollide(Entity entity) {
		if(entity.isFixedPosition()) {
			resolveCollisionWithFixedEntity(entity);
		}else if(entity.team == team) {
			// friendly. Push away.
			double radius = getCollisionRadius() + entity.getCollisionRadius();

            double dx = (entity.x - x);
            double dz = (entity.z - z) * 2;
            double angle = Math.atan2(dz, dx);
            double distScale = 1.0f - Math.sqrt(dx * dx + dz * dz) / radius;
            velocity.x += -Math.cos(angle) * distScale * 2;
            velocity.z += -Math.sin(angle) * distScale * 2;
            entity.velocity.x += Math.cos(angle) * distScale * 2;
            entity.velocity.z += Math.sin(angle) * distScale * 2;
			
		}else {
			((HealthEntity) entity).hurt(meleeDamage);
		}
	}
	
	public double getRangedDamage() {
		return rangedDamage;
	}
}
