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

import com.dijoism.race.toy.Toy;

public class ToySellDialog extends JDialog {
	
	static final long serialVersionUID = 12345;
	
	private JButton cancelButton = new JButton("Cancel");
	private JLabel toyJLabels[] = new JLabel[Toy.NUMBER_OF_POSSIBLE_TOYS];
	private JButton toyJButtons[] = new JButton[Toy.NUMBER_OF_POSSIBLE_TOYS];
	
	public ToySellDialog() {
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
		c.gridy = 0;
		int i = 0;
		for (Toy toy: Toy.getAvailableToys()) {
			c.gridx = 0;
			this.toyJLabels[i] = new JLabel(toy.getName() + ": $" + (toy.getCost() / 2) + " - Quantity: 10");
			this.toyJButtons[i] = new JButton("Sell");
			this.toyJButtons[i].addActionListener(new SellToyButtonActionListener(i, this));
			pane.add(this.toyJLabels[i], c);
			++c.gridx;
			pane.add(this.toyJButtons[i], c);
			++c.gridy;
			++i;
		}
		/*for (TeleporterPart teleporterPart: RaceMain.sharedInstance().getCurrentPlayer().getTeleportMachineParts()) {
			System.out.println(":here!");
			c.gridx = 0;
			JLabel teleporterJLabel = new JLabel(teleporterPart.getName() + ": $" + (teleporterPart.getCost() / 2));
			JButton teleporterJButton = new JButton("Sell");
			teleporterJButton.addActionListener(new SellTeleporterPartButtonActionListener(teleporterPart.ordinal(), this));
			pane.add(teleporterJLabel, c);
			++c.gridx;
			pane.add(teleporterJButton, c);
			++c.gridy;
		}*/
				
		c.gridx = 1;
		++c.gridy;
		ActionListener cancelButtonAl = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ToySellDialog.this.setVisible(false);
			}
		};
		this.cancelButton.addActionListener(cancelButtonAl);
		pane.add(this.cancelButton, c);
				
		//Display the window.
		this.pack();
	}
	
	public void updateAndSetVisible() {
		int i = 0;
		for (Toy toy: Toy.getAvailableToys()) {
			int quantity = ToyPanel.getToyQuantity(RaceMain.sharedInstance().getCurrentPlayer().getToys(), toy);
			this.toyJLabels[i].setText(toy.getName() + ": $" + (toy.getCost() / 2) + " - Quantity: " + quantity);
			this.toyJButtons[i].setText("Sell");
			this.toyJButtons[i].setEnabled((quantity > 0) ? true : false);
			++i;
		}
		this.setVisible(true);
	}
	
	
	public static class SellToyButtonActionListener implements ActionListener {
		
		private int toyNumber;
		private ToySellDialog toySellDialog;
	
		public SellToyButtonActionListener(int toyNumber, ToySellDialog toySellDialog) {
			this.toyNumber = toyNumber;
			this.toySellDialog = toySellDialog;
		}
		
		public void actionPerformed(ActionEvent e) {
			Toy toy = Toy.getAvailableToys().get(this.toyNumber);

			try {
				RaceMain.sharedInstance().getCurrentPlayer().removeToy(toy);
				RaceMain.sharedInstance().getCurrentPlayer().addMoney(toy.getCost() / 2);
				RaceMain.sharedInstance().getCurrentPlayer().updateToysLabel();
				this.toySellDialog.setVisible(false);
			} catch (InvalidToyException ite) {
				JOptionPane.showMessageDialog(this.toySellDialog, "You no longer have a " + Toy.getAvailableToys().get(this.toyNumber).getName() + " to sell!");
			}
		}
	}

}
