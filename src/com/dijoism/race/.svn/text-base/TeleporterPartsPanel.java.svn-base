package com.dijoism.race;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.dijoism.race.teleportmachinepart.TeleporterPart;

public class TeleporterPartsPanel extends JPanel {
	
	static final long serialVersionUID = 12345;
	
	private JLabel toysDescriptionLabel = new JLabel("Teleporter Parts: ");
	private JLabel teleportPartLabels[] = new JLabel[TeleporterPart.values().length];
	
	public void build() {
		//Container pane = this.getContentPane();
		this.setLayout(new GridBagLayout());

		GridBagConstraints c = new GridBagConstraints();
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		this.add(this.toysDescriptionLabel, c);
		
		int i = 0;
		for (TeleporterPart teleporterPart: TeleporterPart.values()) {
			c.gridx = 0;
			++c.gridy;
			this.teleportPartLabels[i] = new JLabel(teleporterPart.getName() + ": 10");
			this.add(this.teleportPartLabels[i], c);
			++i;
		}
	}
	
	public void updateTeleporterParts() {
		int i = 0;
		for (TeleporterPart teleporterPart: TeleporterPart.values()) {
			int quantity = PartSellDialog.getTeleporterPartQuantity(RaceMain.sharedInstance().getCurrentPlayer().getTeleporterParts(), teleporterPart);
			this.teleportPartLabels[i].setText(teleporterPart.getName() + ": " + quantity);
			++i;
		}
	}
}
