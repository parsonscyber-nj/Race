package com.dijoism.race;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;

import com.dijoism.race.terrain.Terrain;

public class ToyDeploymentDialog extends JDialog {
	
	static final long serialVersionUID = 12345;
	
	protected Terrain boardTerrain[][];
	
	private boolean alreadyBuilt = false;
	
	public ToyDeploymentDialog() {
		this.boardTerrain = new Terrain[4][12];
		//build();
	}
	
	public void updateBoard(Board board) {
		//If this is the first time
		if (this.boardTerrain[0][0] == null) {
			for (int row = 0; row < 4; ++row) {
				for (int column = 0; column < 12; ++column) {
					this.boardTerrain[row][column] = board.boardTerrain[row][column].clone();
					//boardToReturn.boardTerrain[row][column].getLabel().setIcon(new ImageIcon(((ImageIcon)this.boardTerrain[row][column].getLabel().getIcon()).getImage()));	
				}
			}
		}
		//Otherwise
		else {
			for (int row = 0; row < 4; ++row) {
				for (int column = 0; column < 12; ++column) {
					this.boardTerrain[row][column].changeTerrainTo(board.boardTerrain[row][column]);
					//boardToReturn.boardTerrain[row][column].getLabel().setIcon(new ImageIcon(((ImageIcon)this.boardTerrain[row][column].getLabel().getIcon()).getImage()));	
				}
			}
		}
		
	}
	
	public void build() {
		if (!this.alreadyBuilt) {
		//Container pane = this.getContentPane();
		this.setLayout(new GridBagLayout());

		GridBagConstraints c = new GridBagConstraints();
		
		c.fill = GridBagConstraints.HORIZONTAL;
		for (int y = 0; y < 4; ++y) {
			c.gridy = y;
			for (int x = 0; x < 12; ++x) {
				c.gridx = x;
				ToySelectionActionListener newAl = new ToySelectionActionListener(x, y, this); 
				this.boardTerrain[y][x].getLabel().addActionListener(newAl);
				this.add(this.boardTerrain[y][x].getLabel(), c);
				/*for (int player = 0; player < 4; ++player) {
					this.playerLocation[player][y][x] = new JLabel(player + "");
					this.add(this.playerLocation[player][y][x], c);
				}*/
			}
		}
		
		this.pack();
		//this.setVisible(true);
		this.alreadyBuilt = true;
		}
	}
	
	public static class ToySelectionActionListener implements ActionListener {
		
		private int xToSet;
		private int yToSet;
		private ToyDeploymentDialog toyDeploymentDialog;
	
		public ToySelectionActionListener(int x, int y, ToyDeploymentDialog toyDeploymentDialog) {
			this.xToSet = x;
			this.yToSet = y;
			this.toyDeploymentDialog = toyDeploymentDialog;
		}
		
		public void actionPerformed(ActionEvent e) {
			//This notifies the main board that the selection of which space to place the toy has been made
			RaceMain.sharedInstance().getRaceFrame().setToySelection(this.xToSet, this.yToSet);
			this.toyDeploymentDialog.setVisible(false);
		}
	}

}
