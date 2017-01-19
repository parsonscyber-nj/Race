package com.dijoism.race.vehicle;

import com.dijoism.race.terrain.Terrain;
import com.dijoism.race.weather.Weather;

public class Plane extends Vehicle {
	
	private int cost = 7000;
	private int movementAllowed = 6;
	
	public int getCost() {
		return this.cost;
	}
	
	public int getMovementAllowed() {
		return this.movementAllowed;
	}
	
	public int getMovementCost(Terrain terrain) {
		/*switch (terrain) {
		case DIRT: return 1;
		case ROAD: return 1;
		case WATER: return 1;
		//DEFAULT: return 4;
		}*/
		return 1;
	}
	
	public int getMovementPenalty(Weather weather) {
		switch (weather) {
			case HOTSTILL: return 0;
			case LIGHTNING: return -6;
			case RAIN: return 0;
			case WIND: return 0;
			case NORMAL: return 0;
			default: return 0;
		}
	}

}
