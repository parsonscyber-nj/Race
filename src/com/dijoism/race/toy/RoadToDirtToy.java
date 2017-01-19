package com.dijoism.race.toy;

import com.dijoism.race.terrain.Terrain;

public class RoadToDirtToy extends Toy {
	
	public RoadToDirtToy() {
		super("Road-to-Dirt Toy");
	}
	
	public Terrain getEndTerrain() {
		return Terrain.getDirt();
	}
	
	public Terrain getStartTerrain() {
		return Terrain.getRoad();
	}
	
	public boolean isValidStartTerrain(Terrain terrain) {
		return terrain.isRoad();
	}

}
