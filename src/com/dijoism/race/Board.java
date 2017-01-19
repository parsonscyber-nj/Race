package com.dijoism.race;

import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.dijoism.race.terrain.Terrain;

/**
 * 2008.08.09 - Dijoist - Created
 */

public class Board extends JPanel {
	
	static final long serialVersionUID = 12345;
	
	protected Terrain boardTerrain[][];
	JButton endButton = new JButton();
	//public JLabel playerLocation[][][];
	
	public Board createToyChoiceBoard() {
		Board boardToReturn = new Board();
		boardToReturn.boardTerrain = new Terrain[4][12];
		for (int row = 0; row < 4; ++row) {
			for (int column = 0; column < 12; ++column) {
				boardToReturn.boardTerrain[row][column] = this.boardTerrain[row][column].clone();
				//boardToReturn.boardTerrain[row][column].getLabel().setIcon(new ImageIcon(((ImageIcon)this.boardTerrain[row][column].getLabel().getIcon()).getImage()));	
			}
		}
		return boardToReturn;
		
	}
	
	public void initBoard() {
		int numDirt = 16;
		int numRoad = 16;
		int numWater = 16;
		Random random = new Random(System.currentTimeMillis());
		int counter = 0;
		this.boardTerrain = new Terrain[4][12];
		//this.playerLocation = new JLabel[4][4][12]; //[player#][y][x]
		for (int row = 0; row < 4; ++row) {
			for (int column = 0; column < 12; ++column) {
				
				boolean continueLooping = true;
				while (continueLooping) {
					int randomValue = random.nextInt(3); 
					++counter;
					if (counter > 1000) {
						throw new NullPointerException("[" + numDirt + "] - [" + numRoad + "] - [" + numWater + "] - (" + row + ") (" + column + ")");
					}
					switch (randomValue) {
					case 0:
						if (numDirt <= 0) {
							break;
						}
						this.boardTerrain[row][column] = Terrain.getDirt();
						--numDirt;
						continueLooping = false;
						break;
					case 1:
						if (numRoad <= 0) {
							break;
						}
						this.boardTerrain[row][column] = Terrain.getRoad();
						--numRoad;
						continueLooping = false;
						break;
					default:
					case 2:
						if (numWater <= 0) {
							break;
						}
						this.boardTerrain[row][column] = Terrain.getWater();
						--numWater;
						continueLooping = false;
						break;
					}
				}
			}
		}
		build();
	}

	public void build() {
		//Container pane = this.getContentPane();
		this.setLayout(new GridBagLayout());

		GridBagConstraints c = new GridBagConstraints();
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridy = 0;
		c.gridx = 0;
		c.gridheight = 4;
		JLabel startLabel = new JLabel();
		startLabel.setIcon(new ImageIcon(Terrain.class.getResource("/images/startSquare.gif")));
		this.add(startLabel, c);
		
		c.gridheight = 1;
		for (int y = 0; y < 4; ++y) {
			c.gridy = y;
			for (int x = 0; x < 12; ++x) {
				c.gridx = x + 1;
				XyActionListener newAl = new XyActionListener(x, y, this); 
				this.boardTerrain[y][x].getLabel().addActionListener(newAl);
				this.add(this.boardTerrain[y][x].getLabel(), c);
				/*for (int player = 0; player < 4; ++player) {
					this.playerLocation[player][y][x] = new JLabel(player + "");
					this.add(this.playerLocation[player][y][x], c);
				}*/
			}
		}
		
		c.gridy = 0;
		c.gridx = 13;
		c.gridheight = 4;
		this.endButton.setIcon(new ImageIcon(Terrain.class.getResource("/images/endSquare.gif")));
		this.endButton.addActionListener(new EndGameActionListener(12, this));
		this.endButton.setBorder(null);
		this.add(this.endButton, c);
		
		//this.pack();
		//this.setVisible(true);
	}
	
	/*public void buildForToySelection() {
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
				for (int player = 0; player < 4; ++player) {
					this.playerLocation[player][y][x] = new JLabel(player + "");
					this.add(this.playerLocation[player][y][x], c);
				}
			}
		}
		
		//this.pack();
		//this.setVisible(true);
	}*/
	
	/*public static class ToySelectionActionListener implements ActionListener {
	
		private int xToSet;
		private int yToSet;
		private Board board;
	
		public ToySelectionActionListener(int x, int y, Board b) {
			this.xToSet = x;
			this.yToSet = y;
			this.board = b;
		}
		
		public void actionPerformed(ActionEvent e) {
			//This notifies the main board that the selection of which space to place the toy has been made
			RaceMain.sharedInstance().getRaceFrame().setToySelection(this.xToSet, this.yToSet);
			setVisibleFalseForParentDialog();
		}
	}*/
	
	//TODO: Make this extend EndGameActionListener..I couldn't think of a quick easy way to do it
	public static class EndGameActionListener implements ActionListener {
	
		private int xToSet;
		//private int yToSet;
		private Board board;
	
		public EndGameActionListener(int x, Board b) {
			this.xToSet = x;
			//this.yToSet = y;
			this.board = b;
		}
		
		public void actionPerformed(ActionEvent e) {
			GridBagConstraints c = new GridBagConstraints();
			c.fill = GridBagConstraints.HORIZONTAL;
			c.gridx = this.xToSet;
			//c.gridy = this.yToSet;
			System.out.println("I was fired for [" + this.xToSet + "], [" + "NA" + "]!");
			try { 
				//TODO: Make this NOT so totally lame
				InvalidMoveException imeToThrow = null;
				for (int i = 0; i < 4; ++ i) {
					try {
						RaceMain.sharedInstance().getCurrentPlayer().moveTo(c.gridx, i);
						imeToThrow = null;
						break;
					} catch (InvalidMoveException ime) {
						imeToThrow = ime;
					}
				}
				if (imeToThrow != null) {
					throw imeToThrow;
				}
			} catch (InsufficientMovePointsException impe) {
				JOptionPane.showMessageDialog(this.board, impe.getMessage());
				return;
			} catch (InvalidMoveException ime) {
				JOptionPane.showMessageDialog(this.board, ime.getMessage());
				return;
			}
			//this.board.add(RaceMain.sharedInstance().getPlayers()[RaceMain.sharedInstance().getPlayersTurn()].getTokenLabel(), c);
			// Create an image, and wait for it to load
			//Image i = ImageIcon("myimage.gif").getImage();

			Image i = ((ImageIcon)this.board.endButton.getIcon()).getImage();
			// Create a BufferedImage of the same size as the Image
			BufferedImage bi = (BufferedImage)this.board.createImage(i.getWidth(this.board),i.getHeight(this.board));



			Graphics2D g = bi.createGraphics();    // Get a Graphics2D object
			g.drawImage(i, 0, 0, this.board);      // Draw the Image data into the BufferedImage
			Image i2 = RaceMain.sharedInstance().getCurrentPlayer().getTokenImageIcon().getImage();// new ImageIcon("token" + RaceMain.sharedInstance().getPlayersTurn()  + + ".gif").getImage();
			
			if (RaceMain.sharedInstance().getPlayersTurn() == 0) {
				g.drawImage(i2, 40, 40, this.board);      // Draw the Image data into the BufferedImage
			}
			else if (RaceMain.sharedInstance().getPlayersTurn() == 1) {
				g.drawImage(i2, 0, 40, this.board);      // Draw the Image data into the BufferedImage
			}
			else if (RaceMain.sharedInstance().getPlayersTurn() == 2) {
				g.drawImage(i2, 40, 0, this.board);      // Draw the Image data into the BufferedImage
			}
			else if (RaceMain.sharedInstance().getPlayersTurn() == 3) {
				g.drawImage(i2, 0, 0, this.board);      // Draw the Image data into the BufferedImage
			}
				
			//g.drawImage(i2, 40, 40, this.board);      // Draw the Image data into the BufferedImage
			this.board.endButton.setIcon(new ImageIcon(bi));
			//this.board.playerLocation[RaceMain.sharedInstance().getPlayersTurn()][yToSet][xToSet].setIcon(new ImageIcon("token" + RaceMain.sharedInstance().getPlayersTurn() + ".gif"));
			//this.board.boardTerrain[yToSet][xToSet].getLabel().setIcon(new ImageIcon("dirt.gif"));
			//this.board.repaint();
			RaceMain.sharedInstance().endGame(RaceMain.sharedInstance().getCurrentPlayer());
		}
	}
	
	public static class XyActionListener implements ActionListener {
	
		private int xToSet;
		private int yToSet;
		private Board board;
	
		public XyActionListener(int x, int y, Board b) {
			this.xToSet = x;
			this.yToSet = y;
			this.board = b;
		}
		
		public void actionPerformed(ActionEvent e) {
			GridBagConstraints c = new GridBagConstraints();
			c.fill = GridBagConstraints.HORIZONTAL;
			c.gridx = this.xToSet;
			c.gridy = this.yToSet;
			System.out.println("I was fired for [" + this.xToSet + "], [" + this.yToSet + "]!");
			try { 
				RaceMain.sharedInstance().getCurrentPlayer().moveTo(c.gridx, c.gridy);
			} catch (InsufficientMovePointsException impe) {
				JOptionPane.showMessageDialog(this.board, impe.getMessage());
				return;
			} catch (InvalidMoveException ime) {
				JOptionPane.showMessageDialog(this.board, ime.getMessage());
				return;
			}
			//this.board.add(RaceMain.sharedInstance().getPlayers()[RaceMain.sharedInstance().getPlayersTurn()].getTokenLabel(), c);
			// Create an image, and wait for it to load
			//Image i = ImageIcon("myimage.gif").getImage();
			this.board.drawPlayerAt(RaceMain.sharedInstance().getCurrentPlayer(), this.xToSet, this.yToSet);
		}
	}
	
	public void drawPlayerAt(Player player, int xToSet, int yToSet) {
			System.out.println("drawPlayerAt called for [" + player.getName() + "] - " + xToSet + "," + yToSet + "!");
	
			Image i = ((ImageIcon)this.boardTerrain[yToSet][xToSet].getLabel().getIcon()).getImage();
			// Create a BufferedImage of the same size as the Image
			BufferedImage bi = (BufferedImage)this.createImage(i.getWidth(this),i.getHeight(this));

			Graphics2D g = bi.createGraphics();    // Get a Graphics2D object
			g.drawImage(i, 0, 0, this);      // Draw the Image data into the BufferedImage
			Image i2 = player.getTokenImageIcon().getImage();// new ImageIcon("token" + RaceMain.sharedInstance().getPlayersTurn()  + + ".gif").getImage();
			int playerNumber = getPlayerNumber(player);
			switch(playerNumber) {
				default:
				case 0:	g.drawImage(i2, 40, 40, this); break;      // Draw the Image data into the BufferedImage
				case 1:	g.drawImage(i2, 0, 40, this); break;     // Draw the Image data into the BufferedImage
				case 2: g.drawImage(i2, 40, 0, this); break;      // Draw the Image data into the BufferedImage
				case 3: g.drawImage(i2, 0, 0, this); break;      // Draw the Image data into the BufferedImage
			}
				
			//g.drawImage(i2, 40, 40, this.board);      // Draw the Image data into the BufferedImage
			this.boardTerrain[yToSet][xToSet].getLabel().setIcon(new ImageIcon(bi));
			//this.board.playerLocation[RaceMain.sharedInstance().getPlayersTurn()][yToSet][xToSet].setIcon(new ImageIcon("token" + RaceMain.sharedInstance().getPlayersTurn() + ".gif"));
			//this.board.boardTerrain[yToSet][xToSet].getLabel().setIcon(new ImageIcon("dirt.gif"));
			//this.board.repaint();
	}
	
	public int getPlayerNumber(Player playerToFind) {
		int counter = 0;
		for (Player player: RaceMain.sharedInstance().getPlayers()) {
			if (player == playerToFind) {
				return counter;
			}
			++counter;
		}
		return -1;
	}
	
	public Terrain getTerrain(int x, int y) {
		return this.boardTerrain[y][x];
	}
	
	//TODO: This needs to deal with keeping track of any tokens currently on it
	public void setTerrain(int x, int y, Terrain terrain) {
		this.boardTerrain[y][x].changeTerrainTo(terrain);
		for (Player player: RaceMain.sharedInstance().getPlayers()) {
			if (player.isAt(x, y)) {
				drawPlayerAt(player, x, y);
			}
		}
	}

}
