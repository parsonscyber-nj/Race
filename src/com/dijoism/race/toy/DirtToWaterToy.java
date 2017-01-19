package com.dijoism.race.toy;

import com.dijoism.race.terrain.Terrain;

public class DirtToWaterToy extends Toy {
	
	public DirtToWaterToy() {
		super("Dirt-to-Water Toy");
	}
	
	public Terrain getEndTerrain() {
		return Terrain.getWater();
	}
	
	public Terrain getStartTerrain() {
		return Terrain.getDirt();
	}
	
	public boolean isValidStartTerrain(Terrain terrain) {
		return terrain.isDirt();
	}

}
