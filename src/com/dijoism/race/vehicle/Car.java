package com.dijoism.race.vehicle;

import com.dijoism.race.terrain.Terrain;
import com.dijoism.race.vehicle.Vehicle;
import com.dijoism.race.weather.Weather;

public class Car extends Vehicle {
	
	private int cost = 4000;
	private int movementAllowed = 6;
	
	public int getCost() {
		return this.cost;
	}
	
	public int getMovementAllowed() {
		return this.movementAllowed;
	}
	
	public int getMovementCost(Terrain terrain) {
		if (terrain.isDirt()) {
			return 3;
		}
		else if (terrain.isRoad()) {
			return 1;
		}
		else if (terrain.isWater()) {
			return 6;
		}
		return 3;
	}
	
	public int getMovementPenalty(Weather weather) {
		switch (weather) {
			case HOTSTILL: return 0;
			case LIGHTNING: return 0;
			case RAIN: return -1;
			case WIND: return -1;
			case NORMAL: return 0;
			default: return 0;
		}
	}

}
