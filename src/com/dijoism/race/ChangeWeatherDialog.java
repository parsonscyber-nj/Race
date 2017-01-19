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

import com.dijoism.race.weather.Weather;

public class ChangeWeatherDialog extends JDialog {
	
	static final long serialVersionUID = 12345;
	
	private JLabel lightningLabel = new JLabel("Lightning (-6 plane): $" + Weather.LIGHTNING.getCost());
	private JButton lightningButton = new JButton("Purchase");
	
	private JLabel windLabel = new JLabel("Wind (-1 car, +1 boat): $" + Weather.WIND.getCost());
	private JButton windButton = new JButton("Purchase");
	
	private JLabel rainLabel = new JLabel("Rain (-1 car/boat): $" + Weather.RAIN.getCost());
	private JButton rainButton = new JButton("Purchase");
	
	private JLabel hotStillLabel = new JLabel("Hot/Still (-1 boat/foot): $" + Weather.HOTSTILL.getCost());
	private JButton hotStillButton = new JButton("Purchase");
	
	private JButton cancelButton = new JButton("Cancel");
	
	public ChangeWeatherDialog() {
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
		pane.add(this.hotStillLabel, c);
		++c.gridx;
		ActionListener hotStillButtonAl = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changeWeatherTo(Weather.HOTSTILL);
			}
		};
		this.hotStillButton.addActionListener(hotStillButtonAl);
		pane.add(this.hotStillButton, c);
		
		c.gridx = 0;
		++c.gridy;
		pane.add(this.lightningLabel, c);
		++c.gridx;
		ActionListener lightningButtonAl = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changeWeatherTo(Weather.LIGHTNING);
			}
		};
		this.lightningButton.addActionListener(lightningButtonAl);
		pane.add(this.lightningButton, c);
		
		c.gridx = 0;
		++c.gridy;
		pane.add(this.rainLabel, c);
		++c.gridx;
		ActionListener rainButtonAl = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changeWeatherTo(Weather.RAIN);
			}
		};
		this.rainButton.addActionListener(rainButtonAl);
		pane.add(this.rainButton, c);
		
		c.gridx = 0;
		++c.gridy;
		pane.add(this.windLabel, c);
		++c.gridx;
		ActionListener windButtonAl = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changeWeatherTo(Weather.WIND);
			}
		};
		this.windButton.addActionListener(windButtonAl);
		pane.add(this.windButton, c);
		
		c.gridx = 1;
		++c.gridy;
		ActionListener cancelButtonAl = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ChangeWeatherDialog.this.setVisible(false);
			}
		};
		this.cancelButton.addActionListener(cancelButtonAl);
		pane.add(this.cancelButton, c);
		
	
		
		//Display the window.
		this.pack();
		
		/*if (HotRocks.getTurn().getWhoseTurn() == HotRocksTurn.OPPONENT_TURN)
			HotRocks.getTurn().getYourTurnLabel().setIcon(blankImageIcon);*/
				
	}
	
	public void changeWeatherTo(Weather weatherToChangeTo) {
		try {
			weatherToChangeTo.changeWeatherTo(RaceMain.sharedInstance().getPlayers()[RaceMain.sharedInstance().getPlayersTurn()]);
			this.setVisible(false);
		} catch (InsufficientFundsException ife) {
			System.err.println("Insufficient funds to change the weather!");
			JOptionPane.showMessageDialog(ChangeWeatherDialog.this, "You do not have the required money ($" + weatherToChangeTo.getCost() + ") to change the weather to " + weatherToChangeTo.getName() + "!");
		}
	}

}
