package com.dijoism.race.vehicle;

import com.dijoism.race.terrain.Terrain;
import com.dijoism.race.weather.Weather;

public class Foot extends Vehicle {
	
	private int cost = 0;
	private int movementAllowed = 6;
	
	public int getCost() {
		return this.cost;
	}
	
	public int getMovementAllowed() {
		return this.movementAllowed;
	}
	
	public int getMovementCost(Terrain terrain) {
		/*switch (terrain) {
		case DIRT: return 3;
		case ROAD: return 3;
		case WATER: return 3;
		//DEFAULT: return 4;
		}*/
		return 3;
	}
	
	public int getMovementPenalty(Weather weather) {
		switch (weather) {
			case HOTSTILL: return -1;
			case LIGHTNING: return 0;
			case RAIN: return 0;
			case WIND: return 0;
			case NORMAL: return 0;
			default: return 0;
		}
	}

}
