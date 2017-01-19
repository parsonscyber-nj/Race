package com.dijoism.race.teleportmachinepart;

import java.util.Random;


//Not an enum due to needing unique objects to display on labels
public enum TeleporterPart {
	
	Part1("Part1", 1000),
	Part2("Part2", 1000),
	Part3("Part3", 1000),
	Part4("Part4", 1000),
	Part5("Part5", 1000),
	Part6("Part6", 1000),
	Part7("Part7", 1000),
	Part8("Part8", 1000),
	Part9("Part9", 1000),
	Part10("Part10", 1000);
	
	private String name;
	private int cost;
	
	TeleporterPart(String name, int cost) {
		this.name = name;
		this.cost = cost;
		//this.imageIcon = new ImageIcon(imageFilename);
	}
	
	public String getName() {
		return this.name;
	}
	
	public int getCost() {
		return this.cost;
	}
	
	public static TeleporterPart getRandomPart() {
		return TeleporterPart.values()[new Random(System.currentTimeMillis()).nextInt(TeleporterPart.values().length)];
	}

}
