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

import com.dijoism.race.teleportmachinepart.TeleporterPart;

public class PartBuyDialog extends JDialog {
	
	static final long serialVersionUID = 12345;
	
	private JButton cancelButton = new JButton("Cancel");
	
	public PartBuyDialog() {
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
		for (TeleporterPart teleporterPart: TeleporterPart.values()) {
			c.gridx = 0;
			JLabel teleporterJLabel = new JLabel(teleporterPart.getName() + ": $" + teleporterPart.getCost());
			JButton teleporterJButton = new JButton("Purchase");
			teleporterJButton.addActionListener(new BuyTeleporterPartButtonActionListener(teleporterPart.ordinal(), this));
			pane.add(teleporterJLabel, c);
			++c.gridx;
			pane.add(teleporterJButton, c);
			++c.gridy;
		}
				
		c.gridx = 1;
		++c.gridy;
		ActionListener cancelButtonAl = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PartBuyDialog.this.setVisible(false);
			}
		};
		this.cancelButton.addActionListener(cancelButtonAl);
		pane.add(this.cancelButton, c);
				
		//Display the window.
		this.pack();
	}
	
	public static class BuyTeleporterPartButtonActionListener implements ActionListener {
		
		private int partNumber;
		private PartBuyDialog partBuyDialog;
	
		public BuyTeleporterPartButtonActionListener(int partNumber, PartBuyDialog partBuyDialog) {
			this.partNumber = partNumber;
			this.partBuyDialog = partBuyDialog;
		}
		
		public void actionPerformed(ActionEvent e) {
			//Get the toy corresponding to buttonLabelNumber
			TeleporterPart teleporterPart = TeleporterPart.values()[this.partNumber];
			try {
				RaceMain.sharedInstance().getCurrentPlayer().spendMoney(teleporterPart.getCost());
				RaceMain.sharedInstance().getCurrentPlayer().addTeleporterPart(teleporterPart);
				this.partBuyDialog.setVisible(false);
			} catch (InsufficientFundsException ife) {
				if (ife.getMessage() != null && ife.getMessage().length() > 1) {
					JOptionPane.showMessageDialog(this.partBuyDialog, ife.getMessage());
				}
				else {
					JOptionPane.showMessageDialog(this.partBuyDialog, "You do not have the required money ($" + teleporterPart.getCost() + ") to buy a " + teleporterPart.getName() + "!");
				}
			}
		}
	}

}
