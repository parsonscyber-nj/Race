package com.dijoism.race;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.dijoism.race.toy.Toy;

public class ToyPanel extends JPanel {
	
	static final long serialVersionUID = 12345;
	
	private JLabel toysDescriptionLabel = new JLabel("Toys: ");
	private JLabel toysLabels[] = new JLabel[7];
	private JButton toysButtons[] = new JButton[7];
	
	public void build() {
		//Container pane = this.getContentPane();
		this.setLayout(new GridBagLayout());

		GridBagConstraints c = new GridBagConstraints();
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		this.add(this.toysDescriptionLabel, c);
		
		int i = 0;
		for (Toy toy: Toy.getAvailableToys()) {
			c.gridx = 0;
			++c.gridy;
			this.toysLabels[i] = new JLabel(toy.getName() + ": 10");
			this.add(this.toysLabels[i], c);
			++c.gridx;
			this.toysButtons[i] = new JButton("Use");
			this.toysButtons[i].addActionListener(new ToyButtonActionListener(i, this));
			this.add(this.toysButtons[i], c);
			++i;
		}
	}
	
	public void updateToys() {
		int i = 0;
		for (Toy toy: Toy.getAvailableToys()) {
			int quantity = getToyQuantity(RaceMain.sharedInstance().getCurrentPlayer().getToys(), toy);
			this.toysLabels[i].setText(toy.getName() + ": " + quantity);
			this.toysButtons[i].setEnabled((quantity > 0) ? true : false);
			++i;
		}
	}
	
	//When I make toys enums, make this a generic function, collapse PartSellDialog.getTeleporterPartQuantity into it.
	protected static int getToyQuantity(ArrayList<Toy> toys, Toy toyToQuantify) {
		int quantity = 0;
		for (Toy toy: toys) {
			if (toy.getName().equals(toyToQuantify.getName())) {
				++quantity;
			}
		}
		return quantity;
	}
	
	public static class ToyButtonActionListener implements ActionListener {
		
		private int buttonLabelNumber;
		private ToyPanel toyPanel;
	
		public ToyButtonActionListener(int buttonLabelNumber, ToyPanel toyPanel) {
			this.buttonLabelNumber = buttonLabelNumber;
			this.toyPanel = toyPanel;
		}
		
		public void actionPerformed(ActionEvent e) {
			//Get the toy corresponding to buttonLabelNumber
			for (Toy toy: RaceMain.sharedInstance().getCurrentPlayer().getToys()) {
				if (this.toyPanel.toysLabels[this.buttonLabelNumber].getText().startsWith(toy.getName())) {
					//Use it if possible
					RaceMain.sharedInstance().getCurrentPlayer().useToy(toy);
					
					break;
				}
			}
			//Remove toy from Label
			this.toyPanel.updateToys();
		}
	}

}
