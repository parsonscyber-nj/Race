package com.dijoism.race.weather;

import javax.swing.ImageIcon;

import com.dijoism.race.InsufficientFundsException;
import com.dijoism.race.Player;
import com.dijoism.race.RaceMain;
import com.dijoism.race.terrain.Terrain;

public enum Weather {
	
	NORMAL("Normal", 1000, "normalWeather.gif"),
	HOTSTILL("Hot/Still", 1000, "hotStillWeather.gif"),
	LIGHTNING("Lightning", 1000, "lightningWeather.gif"),
	RAIN("Rain", 1000, "rainWeather.gif"),
	WIND("Wind", 1000, "windWeather.gif");
	
	private String name;
	private int cost;
	private ImageIcon imageIcon;
	
	Weather(String name, int cost, String imageFilename) {
		this.name = name;
		this.cost = cost;
		this.imageIcon = new ImageIcon(Terrain.class.getResource("/images/" + imageFilename));
	}
	
	public int getCost() {
		return this.cost;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void changeWeatherTo(Player player) throws InsufficientFundsException {
		player.spendMoney(this.getCost());
		RaceMain.sharedInstance().getRaceFrame().setWeather(this);
	}
	
	public ImageIcon getImageIcon() {
		return this.imageIcon;
	}
	
}
