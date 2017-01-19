package com.dijoism.race;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import com.dijoism.race.teleportmachinepart.TeleporterPart;

public class PartSellDialog extends JDialog {
	
	static final long serialVersionUID = 12345;
	
	private JButton cancelButton = new JButton("Cancel");
	private JLabel teleporterPartJLabels[] = new JLabel[TeleporterPart.values().length];
	private JButton teleporterPartJButtons[] = new JButton[TeleporterPart.values().length];
	
	public PartSellDialog() {
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
		for (int i = 0; i < TeleporterPart.values().length; ++i) {
			c.gridx = 0;
			this.teleporterPartJLabels[i] = new JLabel(TeleporterPart.values()[i].getName() + ": $" + (TeleporterPart.values()[i].getCost() / 2) + " - Quantity: 10");
			this.teleporterPartJButtons[i] = new JButton("Sell");
			this.teleporterPartJButtons[i].addActionListener(new SellTeleporterPartButtonActionListener(i, this));
			pane.add(this.teleporterPartJLabels[i], c);
			++c.gridx;
			pane.add(this.teleporterPartJButtons[i], c);
			++c.gridy;
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
				PartSellDialog.this.setVisible(false);
			}
		};
		this.cancelButton.addActionListener(cancelButtonAl);
		pane.add(this.cancelButton, c);
				
		//Display the window.
		this.pack();
	}
	
	public void updateAndSetVisible() {
		for (TeleporterPart teleporterPart: TeleporterPart.values()) {
			int quantity = getTeleporterPartQuantity(RaceMain.sharedInstance().getCurrentPlayer().getTeleporterParts(), teleporterPart);
			this.teleporterPartJLabels[teleporterPart.ordinal()].setText(teleporterPart.getName() + ": $" + (teleporterPart.getCost() / 2) + " - Quantity: " + quantity);
			this.teleporterPartJButtons[teleporterPart.ordinal()].setText("Sell");
			this.teleporterPartJButtons[teleporterPart.ordinal()].setEnabled((quantity > 0) ? true : false);
		}
		this.setVisible(true);
	}
	
	public static int getTeleporterPartQuantity(ArrayList<TeleporterPart> teleporterParts, TeleporterPart teleporterPartToQuantify) {
		int quantity = 0;
		for (TeleporterPart teleporterPart: teleporterParts) {
			if (teleporterPart == teleporterPartToQuantify) {
				++quantity;
			}
		}
		return quantity;
	}
	
	public static class SellTeleporterPartButtonActionListener implements ActionListener {
		
		private int partNumber;
		private PartSellDialog partSellDialog;
	
		public SellTeleporterPartButtonActionListener(int partNumber, PartSellDialog partSellDialog) {
			this.partNumber = partNumber;
			this.partSellDialog = partSellDialog;
		}
		
		public void actionPerformed(ActionEvent e) {
			TeleporterPart teleporterPart = TeleporterPart.values()[this.partNumber];

			try {
				RaceMain.sharedInstance().getCurrentPlayer().removeTeleporterPart(teleporterPart);
				RaceMain.sharedInstance().getCurrentPlayer().addMoney(teleporterPart.getCost() / 2);
				this.partSellDialog.setVisible(false);
			} catch (ItemNotFoundException pnfe) {
				JOptionPane.showMessageDialog(this.partSellDialog, "You no longer have a " + TeleporterPart.values()[this.partNumber] + " to sell!");
			}
		}
	}

}
