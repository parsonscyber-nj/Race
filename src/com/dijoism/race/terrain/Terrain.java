package com.dijoism.race.terrain;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Terrain {
		
	public JButton getLabel() {
		return label;
	}

	private String name;
	private String description;
	private JButton label;
	private String imageName;
	
	/**
     * Creates a new <code>SelectionType</code> based on the given values.
     * 
     * @param name The name of the new instance.
     * @param description The description of the new instance.
     */
    Terrain(String name, String description, String imageName){
        this.name = name;
        this.description = description;
        this.label = new JButton();
        this.label.setText("");
        this.label.setIcon(new ImageIcon(Terrain.class.getResource("/images/" + imageName)));
        this.label.setBorder(null);
        this.imageName = imageName;
    }
    
    /**
     * This allows you to get a <code>MatchType</code> value
     * based off its corresponding <code>String</code>. 
     * 
     * @param lookupString The <code>String</code> for which a <code>MatchType</codE> is desired.
     * @return The <code>SelectionType</code> corresponding to the given <code>String</code>,
     * 			or null if no such <code>SelectionType</code> exists.
     */
    /*public static Terrain lookup(String lookupString) {
    	for (Terrain value: Terrain.values()) {
    		if (value.name.equals(lookupString)) {
    			return value;
    		}
    	}
    	return null;
    }*/
    
    public static Terrain getDirt() {
    	return new Terrain("Dirt", "It's dirt.", "dirt.gif");
    }
    
    public static Terrain getRoad() {
    	return new Terrain("Road", "It's road.", "road.gif");
    }
    
    public static Terrain getWater() {
    	return new Terrain("Water", "It's water.", "water.gif");
    }
    
    public void changeTerrainTo(Terrain terrain) {
    	this.name = terrain.name;
    	this.description = terrain.description;
    	this.label.setIcon(terrain.label.getIcon());
    	this.imageName = terrain.imageName;
    }
    
    public Terrain clone() {
    	Terrain newTerrain = null;
    	if (isDirt()) {
    		newTerrain = Terrain.getDirt();
    	}
    	else if (isWater()) {
    		newTerrain = Terrain.getWater();
    	}
    	else if (isRoad()) {
    		newTerrain = Terrain.getRoad();
    	}
    	//We do this so that the player tokens get copied too.
    	newTerrain.label.setIcon(new ImageIcon(((ImageIcon)this.label.getIcon()).getImage()));
    	return newTerrain;
    }
    
    public boolean isDirt() {
    	if (this.name.equals("Dirt")) {
    		return true;
    	}
    	return false;
    }
        
    public boolean isRoad() {
    	if (this.name.equals("Road")) {
    		return true;
    	}
    	return false;
    }

    public boolean isWater() {
    	if (this.name.equals("Water")) {
    		return true;
    	}
    	return false;
    }
    
    public void reset() {
    	System.out.println("I was reset");
    	this.label.setIcon(new ImageIcon(Terrain.class.getResource("/images/" + this.imageName)));
    }
    
    /*
    	*//** The enumerated values *//*
    	DIRT("Dirt", "It's dirt.", "dirt.gif"),
    	ROAD("Road", "It's road.", "road.gif"),
    	WATER("Water", "It's water.", "water.gif");
    }*/

}
