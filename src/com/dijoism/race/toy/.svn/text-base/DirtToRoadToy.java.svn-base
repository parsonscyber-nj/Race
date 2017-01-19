package com.dijoism.race.toy;

import com.dijoism.race.terrain.Terrain;

public class DirtToRoadToy extends Toy {
	
	public DirtToRoadToy() {
		super("Dirt-to-Road Toy");
	}	
	
	public Terrain getEndTerrain() {
		return Terrain.getRoad();
	}
	
	public Terrain getStartTerrain() {
		return Terrain.getDirt();
	}
	
	public boolean isValidStartTerrain(Terrain terrain) {
		return terrain.isDirt();
	}

}
