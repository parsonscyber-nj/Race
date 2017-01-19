package com.dijoism.race.toy;

import java.util.ArrayList;
import java.util.Random;

import com.dijoism.race.InsufficientFundsException;
import com.dijoism.race.Player;
import com.dijoism.race.terrain.Terrain;

//TODO: I think I can actually make this an enum (do it like I did parts)
public abstract class Toy {
	
	public static final int NUMBER_OF_POSSIBLE_TOYS = 6;
	
	private String name;
	
	public Toy(String name) {
		this.name = name;
	}
	
	public int getCost() {
		return 1000;
	}
	
	public String getName() {
		return this.name;
	}
	
	public abstract Terrain getEndTerrain();
	public abstract boolean isValidStartTerrain(Terrain terrain);
	public abstract Terrain getStartTerrain();
		
	
	public static Toy buyToy(Player player) throws InsufficientFundsException {
		
		Random random = new Random(System.currentTimeMillis());
		Toy newToy;
		switch (random.nextInt(Toy.NUMBER_OF_POSSIBLE_TOYS)) {
		case 0: newToy = new DirtToRoadToy(); break;
		case 1: newToy = new DirtToWaterToy(); break;
		case 2: newToy = new RoadToDirtToy(); break;
		case 3: newToy = new RoadToWaterToy(); break;
		case 4: newToy = new WaterToDirtToy(); break;
		default:
		case 5: newToy = new WaterToRoadToy();
		}
		
		player.spendMoney(newToy.getCost());
		player.addToy(newToy);
		return newToy;
		
	}
	
	public static ArrayList<Toy> getAvailableToys() {
		ArrayList<Toy> availableToys = new ArrayList<Toy>();
		availableToys.add(new DirtToRoadToy());
		availableToys.add(new DirtToWaterToy());
		availableToys.add(new RoadToDirtToy());
		availableToys.add(new RoadToWaterToy());
		availableToys.add(new WaterToDirtToy());
		availableToys.add(new WaterToRoadToy());
		return availableToys;
	}

}
