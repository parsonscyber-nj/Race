package com.dijoism.race.toy;

import com.dijoism.race.terrain.Terrain;

public class RoadToWaterToy extends Toy {
	
	public RoadToWaterToy() {
		super("Road-to-Water Toy");
	}
	
	public Terrain getEndTerrain() {
		return Terrain.getWater();
	}
	
	public Terrain getStartTerrain() {
		return Terrain.getRoad();
	}
	
	public boolean isValidStartTerrain(Terrain terrain) {
		return terrain.isRoad();
	}

}
