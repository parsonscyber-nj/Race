package com.dijoism.race;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import com.dijoism.race.vehicle.Boat;
import com.dijoism.race.vehicle.Car;
import com.dijoism.race.vehicle.Plane;
import com.dijoism.race.vehicle.Teleporter;
import com.dijoism.race.vehicle.Vehicle;

public class VehicleBuyDialog extends JDialog {
	
	static final long serialVersionUID = 12345;
	
	private JLabel boatLabel = new JLabel("Boat: $" + new Boat().getStringCost());
	private JButton boatButton = new JButton("Purchase");
	
	private JLabel carLabel = new JLabel("Car: $" + new Car().getStringCost());
	private JButton carButton = new JButton("Purchase");
	
	private JLabel planeLabel = new JLabel("Plane: $" + new Plane().getStringCost());
	private JButton planeButton = new JButton("Purchase");
	
	private JLabel teleporterLabel = new JLabel("Teleporter: $" + new Teleporter().getStringCost());
	private JButton teleporterButton = new JButton("Purchase");
	
	private JButton cancelButton = new JButton("Cancel");
	
	public VehicleBuyDialog() {
		createGui();
	}
	
	/**
	 * Create the GUI and show it.  For thread safety,
	 * this method should be invoked from the
	 * event-dispatching thread.
	 */
	public void createGui() {
		//Make sure we have nice window decorations.
		//JFrame.setDefaultLookAndFeelDecorated(true);
		
		Container pane = this.getContentPane();
		pane.setLayout(new GridBagLayout());
		
		GridBagConstraints c = new GridBagConstraints();
		
		c.fill = GridBagConstraints.HORIZONTAL;
		
		c.gridx = 0;
		c.gridy = 0;
		pane.add(this.boatLabel, c);
		++c.gridx;
		ActionListener boatButtonAl = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buyVehicle(new Boat());
			}
		};
		this.boatButton.addActionListener(boatButtonAl);
		pane.add(this.boatButton, c);
		
		c.gridx = 0;
		++c.gridy;
		pane.add(this.carLabel, c);
		++c.gridx;
		ActionListener carButtonAl = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buyVehicle(new Car());
			}
		};
		this.carButton.addActionListener(carButtonAl);
		pane.add(this.carButton, c);
		
		c.gridx = 0;
		++c.gridy;
		pane.add(this.planeLabel, c);
		++c.gridx;
		ActionListener planeButtonAl = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buyVehicle(new Plane());
			}
		};
		this.planeButton.addActionListener(planeButtonAl);
		pane.add(this.planeButton, c);
		
		c.gridx = 0;
		++c.gridy;
		pane.add(this.teleporterLabel, c);
		++c.gridx;
		ActionListener teleporterButtonAl = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buyVehicle(new Teleporter());
			}
		};
		this.teleporterButton.addActionListener(teleporterButtonAl);
		pane.add(this.teleporterButton, c);
		
		c.gridx = 1;
		++c.gridy;
		ActionListener cancelButtonAl = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VehicleBuyDialog.this.setVisible(false);
			}
		};
		this.cancelButton.addActionListener(cancelButtonAl);
		pane.add(this.cancelButton, c);
		
	
		
		//Display the window.
		this.pack();
		
		/*if (HotRocks.getTurn().getWhoseTurn() == HotRocksTurn.OPPONENT_TURN)
			HotRocks.getTurn().getYourTurnLabel().setIcon(blankImageIcon);*/
				
	}
	
	public void buyVehicle(Vehicle vehicleToBuy) {
		try {
			vehicleToBuy.buy(RaceMain.sharedInstance().getPlayers()[RaceMain.sharedInstance().getPlayersTurn()]);
			this.setVisible(false);
		} catch (InsufficientFundsException ife) {
			System.err.println("Insufficient funds to buy that toy!");
			if (ife.getMessage() != null && ife.getMessage().length() > 1) {
				JOptionPane.showMessageDialog(VehicleBuyDialog.this, ife.getMessage());
			}
			else {
				JOptionPane.showMessageDialog(VehicleBuyDialog.this, "You do not have the required money ($" + vehicleToBuy.getCost() + ") to buy a " + vehicleToBuy.getName() + "!");
			}
		}
	}

}
