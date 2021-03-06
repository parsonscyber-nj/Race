package com.dijoism.race;

import java.awt.Color;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import com.dijoism.race.teleportmachinepart.TeleporterPart;
import com.dijoism.race.terrain.Terrain;
import com.dijoism.race.toy.Toy;
import com.dijoism.race.vehicle.Foot;
import com.dijoism.race.vehicle.Teleporter;
import com.dijoism.race.vehicle.Vehicle;
//Flag: YouShouldPlayTheGame

public class Player {
	
	public Vehicle vehicle;
	public ArrayList<Toy> toys;
	public ArrayList<TeleporterPart> teleportMachineParts;
	public int money;
	public String name;
	private JLabel playerLabel = new JLabel();
	private ImageIcon tokenImageIcon;
	private Color color;
	private int currentMovement;
	private boolean hasMovedThisTurn = false;
	private boolean hasDugThisTurn = false;
	
	private int currentXLocation = -1;
	private int currentYLocation = -1;
	
	public static int PLAYER_COUNT = 4;
	
		
	public Player(String name, int playerNumber) {
		this.name = name;
		this.money = 5000;
		this.vehicle = new Foot();
		this.toys = new ArrayList<Toy>();
		this.teleportMachineParts = new ArrayList<TeleporterPart>();
		switch (playerNumber) {
		case 1: this.color = Color.blue; break;
		case 2: this.color = Color.red; break;
		case 3: this.color = Color.green; break; 
		case 4: this.color = Color.orange; break; 
		case 5: this.color = Color.pink; break;
		default: this.color = Color.yellow;
		}
		this.playerLabel.setHorizontalAlignment(JLabel.CENTER);
		this.playerLabel.setVerticalAlignment(JLabel.BOTTOM);
		this.playerLabel.setVerticalTextPosition(JLabel.BOTTOM);
		this.playerLabel.setHorizontalTextPosition(JLabel.CENTER);
		this.playerLabel.setForeground(this.color);
		this.playerLabel.setText(this.name);
		this.playerLabel.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createLoweredBevelBorder(),
				BorderFactory.createEmptyBorder(5, 5, 5, 5)));
		this.playerLabel.setIcon(getImageIcon(name));
		this.playerLabel.setOpaque(true);
		
		/*updateBalanceLabel();
		//this.toysLabel.setText("Toys: (none)");
		updateVehicleLabel();
		updateMovementLabel();*/
		System.out.println("Adding " + this.name + ", [" + playerNumber + "], [" + "token" + playerNumber + ".gif" + "]"); 
		this.tokenImageIcon = new ImageIcon(Terrain.class.getResource("/images/token" + playerNumber + ".gif"));
		
	}
	
	public JLabel getLabel() {
		return this.playerLabel;
		
	}
	
	public ImageIcon getImageIcon(String name) {
		String gifName = name.replaceAll(" ","") + ".gif";
		//if ((new File(gifName)).exists()) {
			return new ImageIcon(Terrain.class.getResource("/images/" + gifName));
		//}
		//else {
		//	return new ImageIcon(Terrain.class.getResource("/images/player.gif"));
		//}
	}
	
	public int getBalance() {
		return this.money;
	}
	
	public ArrayList<Toy> getToys() {
		return this.toys;
	}
	
	public Vehicle getVehicle() {
		return this.vehicle;
	}
	
	public int getAvailableMoney() {
		return this.money;
	}
	
	public void updateMovementLabel() {
		RaceMain.sharedInstance().getRaceFrame().updateMovementLabel();
		//this.movementLabel.setText("Movement Left: " + this.currentMovement);
	}
	
	public void updateCurrentPlayerLabel() {
		RaceMain.sharedInstance().getRaceFrame().updateCurrentPlayerLabel();
		//this.movementLabel.setText("Movement Left: " + this.currentMovement);
	}
	
	public void updateBalanceLabel() {
		RaceMain.sharedInstance().getRaceFrame().updateBalanceLabel();
		//this.balanceLabel.setText("Balance: $" + this.money);
	}
	
	public void updateVehicleLabel() {
		RaceMain.sharedInstance().getRaceFrame().updateVehicleLabel();
		//this.vehicleLabel.setText("Vehicle: " + this.vehicle.getName());
	}
	
	public void updateToysLabel() {
		RaceMain.sharedInstance().getRaceFrame().updateToysLabel();
		//this.vehicleLabel.setText("Vehicle: " + this.vehicle.getName());
	}
	
	public void updateTeleportMachinePartsLabel() {
		RaceMain.sharedInstance().getRaceFrame().updateTeleporterPartsLabel();
		//this.movementLabel.setText("Movement Left: " + this.currentMovement);
	}
		
	public void spendMoney(int moneyToSpend) throws InsufficientFundsException {
		if (this.money >= moneyToSpend) {
			this.money -= moneyToSpend;
			updateBalanceLabel();
		}
		else {
			throw new InsufficientFundsException();
		}
	}
	
	public void addToy(Toy toy) {
		this.toys.add(toy);
		updateToysLabel();
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
		updateVehicleLabel();
		updateMovementLabel();
	}
	
	public void newTurn() {
		this.currentMovement = 6;
		updateMovementLabel();
		updateVehicleLabel();
		updateToysLabel();
		updateBalanceLabel();
		updateCurrentPlayerLabel();
		updateTeleportMachinePartsLabel();
		this.hasMovedThisTurn = false;
		this.hasDugThisTurn = false;
		//Highlight their label so it's obvious whose turn it is
		this.playerLabel.setBackground(Color.YELLOW);
	}
	
	public void endTurn() {
		//Unhighlight their label
		this.playerLabel.setBackground(null);
	}
	
	public int getMovement() {
		return this.currentMovement + this.vehicle.getMovementPenalty(RaceMain.sharedInstance().getRaceFrame().getWeather());
	}
	
	public ImageIcon getTokenImageIcon() {
		return this.tokenImageIcon;
	}
	
	public void dig() throws CannotDigException {
		if (this.hasMovedThisTurn) {
			throw new CannotDigException("You've already moved this turn, you don't have enough time left to dig!");
		}
		if (this.hasDugThisTurn) {
			throw new CannotDigException("You've already dug this turn, you don't have enough time left to dig again!");
		}
		addTeleporterPart(TeleporterPart.getRandomPart());
		this.hasDugThisTurn = true;
	}
	
	public void addTeleporterPart(TeleporterPart teleporterPart) {
		this.teleportMachineParts.add(teleporterPart);
		//To keep them in order - useful for displaying them to the user
		Collections.sort(this.teleportMachineParts);
		updateTeleportMachinePartsLabel();
	}
	
	public void removeTeleporterPart(TeleporterPart teleporterPart) throws ItemNotFoundException {
		if (!this.teleportMachineParts.remove(teleporterPart)) {
			throw new ItemNotFoundException("You do not have a " + teleporterPart.getName() + " to remove!");
		}
		updateTeleportMachinePartsLabel();
	}
	
	public void addMoney(int moneyToAdd) {
		this.money += moneyToAdd;
		updateBalanceLabel();
	}
	
	public void moveTo(int x, int y) throws InsufficientMovePointsException, InvalidMoveException {
		if (this.hasDugThisTurn) {
			throw new InvalidMoveException("You've spent your time digging this turn, you don't get to move too!");
		}
		//Teleporter can go anywhere!
		if (this.vehicle instanceof Teleporter) {
			moveToAndCheckMovePoints(x, y);
			return;
		}
		//If you're on the start square
		else if (this.currentXLocation == -1 && this.currentYLocation == -1) {
			if (x == 0) {
				moveToAndCheckMovePoints(x, y);
				return;
			}
			else {
				throw new InvalidMoveException("That is not a valid move from your current location!");// + "[" + x + "],[" + y + "]"
			}
		}
		if (x == this.currentXLocation && (y - currentYLocation == -1 || y - currentYLocation == 1)) {
			moveToAndCheckMovePoints(x, y);
			return;
		}
		else if (y == this.currentYLocation && (x - currentXLocation == -1 || x - currentXLocation == 1)) {
			moveToAndCheckMovePoints(x, y);
			return;
		}
		throw new InvalidMoveException("That is not a valid move from your current location!");
	}
	
	private void moveToAndCheckMovePoints(int x, int y) throws InsufficientMovePointsException {
		if (this.currentXLocation != -1 && this.currentYLocation != -1) {
			Terrain currentTerrain = RaceMain.sharedInstance().getBoard().getTerrain(this.currentXLocation, this.currentYLocation);
			int movementCost = this.vehicle.getMovementCost(currentTerrain);
			if (movementCost > getMovement()) {
				throw new InsufficientMovePointsException("That move requires [" + movementCost + "] movement points and you only have [" + getMovement() + "] points left!");
			}
			this.currentMovement -= movementCost;
		}
		int oldXLocation = this.currentXLocation;
		int oldYLocation = this.currentYLocation;
		this.currentXLocation = x;
		this.currentYLocation = y;
		this.hasMovedThisTurn = true;
		if (oldXLocation != -1 && oldYLocation != -1) {
			//The reset gets our current token off of the terrain it's on
			RaceMain.sharedInstance().getBoard().getTerrain(oldXLocation, oldYLocation).reset();
			//This is a cheap way of putting back on any other tokens on the square
			RaceMain.sharedInstance().getBoard().setTerrain(oldXLocation, oldYLocation, RaceMain.sharedInstance().getBoard().getTerrain(oldXLocation, oldYLocation));
		}
		updateMovementLabel();
	}
	
	public String getName() {
		return this.name;
	}
	
	public void useToy(Toy toy) {
		//if (this.toys.contains(toy)) {
			RaceMain.sharedInstance().getRaceFrame().useToy(toy);
			//this.toys.remove(toy);
		//}
		//else {
		//	throw new InvalidToyException("I do not contain the toy I was just given - it's a [" + toy.getName() + "]!");
		//}
	}
	
	public void removeToy(Toy toy) throws InvalidToyException {
		if (this.toys.contains(toy)) {
			//RaceMain.sharedInstance().getRaceFrame().useToy(toy);
			this.toys.remove(toy);
			return;
		}
		else {
			for (Toy ownedToy: this.toys) {
				if (ownedToy.getName().equals(toy.getName())) {
					this.toys.remove(ownedToy);
					return;
				}
			}
		}
		throw new InvalidToyException("I do not contain the toy I was just given - it's a [" + toy.getName() + "]!");
	}

	public ArrayList<TeleporterPart> getTeleporterParts() {
		return this.teleportMachineParts;
	}
	
	public boolean isAt(int x, int y) {
		return (x == this.currentXLocation && y == this.currentYLocation);
	}
}
