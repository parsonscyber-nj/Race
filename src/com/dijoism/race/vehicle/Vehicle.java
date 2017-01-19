package com.dijoism.race.vehicle;

import com.dijoism.race.InsufficientFundsException;
import com.dijoism.race.Player;
import com.dijoism.race.terrain.Terrain;
import com.dijoism.race.weather.Weather;

public abstract class Vehicle {
	
	public abstract int getCost();
	
	public String getStringCost() {
		return getCost() + "";
	}
	
	public abstract int getMovementAllowed();
	
	public abstract int getMovementCost(Terrain terrain);
	
	public void buy(Player player) throws InsufficientFundsException {
		player.spendMoney(this.getCost());
		player.setVehicle(this);
	}
	
	public String getName() {
		return this.getClass().getSimpleName();
	}
	
	public abstract int getMovementPenalty(Weather weather);

}
