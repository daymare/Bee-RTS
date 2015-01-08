package com.probad.bee.entity;

import com.probad.bee.world.EntityWorld;
import com.probad.bee.gui.*;

public class BasicHive extends HealthEntity {
	
	public static final int DefenseUpgradeCost = 100;
	public static final int ProductionUpgradeCost = 100;
	public static final int EconomicUpgradeCost = 100;
	
	public static final int WorkerBeeCost = 50;
	
	
	public BasicHive(double x, double y, double z, int team, EntityWorld world) {
		super(x, y, z, team, world);
		
		image = Texture.loadBitmap("BeeHive.png");
	}
	
	@Override
	public boolean isFixedPosition() {
		return true;
	}
	
	@Override
	public boolean update(int deltaMS) {
		System.out.println(health);
		return super.update(deltaMS);
	}
	
	@Override
	public double getCollisionRadius() {
		return 10.0f;
	}
	
	@Override
	public double getMaxHealth() {
		return 1500.0f;
	}
	
	@Override
	public void hurt(double damage) {
		super.hurt(damage);
	}
	
	public int getUpgradeCost(int selectedUpgrade) {
		switch(selectedUpgrade) {
		case 0: // Defense upgrade
			return DefenseUpgradeCost;
		case 1: // Production upgrade
			return ProductionUpgradeCost;
		case 2: // Economic upgrade
			return EconomicUpgradeCost;
		default:
			return -1;
		}
	}
	
	public int getUnitCost(int selectedUnit) {
		switch(selectedUnit) {
		case 0:
			return WorkerBeeCost;
		default:
			return -1;	
		}
		
	}
	
	public void buyUpgrade(int selectedUpgrade) {
		int upgradeCost = getUpgradeCost(selectedUpgrade);
		if(upgradeCost != -1 && upgradeCost<world.getResources()) {
			switch(selectedUpgrade) {
			case 0:
				// TODO spawn defense hive
				break;
			case 1:
				// TODO spawn production hive
				break;
			case 2:
				// TODO spawn economic hive
				break;
			}
			world.addResources(-upgradeCost);
			this.setRemoved();
		}
	}
	
	public void buyUnit(int selectedUnit) {
		int cost = getUnitCost(selectedUnit);
		if(cost != -1 && cost < world.getResources()) {
			switch(selectedUnit) {
			case 0:
				world.addEntity(new WorkerBee(x, y, z, team, world));
				break;
			}
			world.addResources(-cost);
		}
	}
	
}
