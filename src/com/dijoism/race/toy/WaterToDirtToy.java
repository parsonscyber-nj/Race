package com.dijoism.race.toy;

import com.dijoism.race.terrain.Terrain;

public class WaterToDirtToy extends Toy {
	
	public WaterToDirtToy() {
		super("Water-to-Dirt Toy");
	}
	
	public Terrain getEndTerrain() {
		return Terrain.getDirt();
	}
	
	public Terrain getStartTerrain() {
		return Terrain.getWater();
	}
	
	public boolean isValidStartTerrain(Terrain terrain) {
		return terrain.isWater();
	}

}
