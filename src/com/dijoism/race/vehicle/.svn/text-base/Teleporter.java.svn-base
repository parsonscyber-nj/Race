package com.dijoism.race.vehicle;

import com.dijoism.race.InsufficientFundsException;
import com.dijoism.race.Player;
import com.dijoism.race.teleportmachinepart.TeleporterPart;
import com.dijoism.race.terrain.Terrain;
import com.dijoism.race.weather.Weather;

public class Teleporter extends Vehicle {
	
	private int cost = 10000;
	private int movementAllowed = 6;
	
	public int getCost() {
		return this.cost;
	}
	
	public int getMovementAllowed() {
		return this.movementAllowed;
	}
	
	public String getStringCost() {
		return "All Parts";
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
			case LIGHTNING: return 0;
			case RAIN: return 0;
			case WIND: return 0;
			case NORMAL: return 0;
			default: return 0;
		}
	}
	
	public void buy(Player player) throws InsufficientFundsException {
		for (TeleporterPart tmp: TeleporterPart.values()) {
			if (!player.getTeleporterParts().contains(tmp)) {
				throw new InsufficientFundsException("You do not have all the required parts for this purchase!");
			}
		}
		//Get rid of all the parts for them
		//TODO: make this a purchaseTeleportMachine() call on player, as we shouldn't be modifying their private variables
		for (TeleporterPart tmp: TeleporterPart.values()) {
			player.getTeleporterParts().remove(tmp);
		}
		player.setVehicle(this);
		player.updateTeleportMachinePartsLabel();
	}

}
