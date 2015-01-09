package src.com.probad.bee.entity;

import src.com.probad.bee.world.EntityWorld;
import src.com.probad.bee.gui.*;

public class Flower extends HealthEntity {
	
	protected int resources;
	protected Bitmap drainedImage; // TODO get drained flower image
	
	public Flower(double x, double y, double z, EntityWorld world, int resources) {
		super(x, y, z, -1, world);
		
		this.resources = resources;
		image = Texture.loadBitmap("Tree.png"); // TODO get flower image
		
		entityType = EntityType.ResourceEntity;
	}
	
	public void render(Screen s, double camX, double camY) {
		if(resources>0) {
			s.draw(image, (int)(x-camX), (int)(y-camY));
		}else {
			s.draw(drainedImage, (int)(x-camX), (int)(y-camY));
		}
	}
	
	public int drainResources(int drain) {
		if(resources<drain) {
			int temp = resources;
			resources = 0;
			return temp;
		}else {
			resources-=drain;
			return drain;
		}
	}
	
	
	public double getMaxHealth() {
		return 250.0f;
	}
	
}
