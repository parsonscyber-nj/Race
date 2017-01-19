package com.dijoism.race.toy;

import com.dijoism.race.terrain.Terrain;

public class WaterToRoadToy extends Toy {
	
	public WaterToRoadToy() {
		super("Water-to-Road Toy");
	}
	
	public Terrain getEndTerrain() {
		return Terrain.getRoad();
	}
	
	public Terrain getStartTerrain() {
		return Terrain.getWater();
	}
	
	public boolean isValidStartTerrain(Terrain terrain) {
		return terrain.isWater();
	}

}
