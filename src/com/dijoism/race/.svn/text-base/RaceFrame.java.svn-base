package com.dijoism.race;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import com.dijoism.race.terrain.Terrain;
import com.dijoism.race.toy.DirtToWaterToy;
import com.dijoism.race.toy.Toy;
import com.dijoism.race.vehicle.Boat;
import com.dijoism.race.vehicle.Car;
import com.dijoism.race.vehicle.Foot;
import com.dijoism.race.vehicle.Plane;
import com.dijoism.race.vehicle.Teleporter;
import com.dijoism.race.vehicle.Vehicle;
import com.dijoism.race.weather.Weather;

/**
 * 2008.08.09 - Dijoist - Created
 */

public class RaceFrame extends JFrame {
	
	static final long serialVersionUID = 12345;
	
	//static Logger logger = Logger.getLogger(RaceFrame.class);

	private final static String versionString = "Race .3 08/14/08 - Fully Functional!";
	//private final static String versionString = "Race .2 08/10/08 - Created / Coded";
	//private final static String versionString = "Race .1 08/09/08 - Created / Coded";
	
	/*private JButton rollButton = new JButton("Roll");
	private JButton keepButton = new JButton("Keep");
	private JRadioButton dieRadio[] = new JRadioButton[6];
	private ImageIcon turnImageIcon = new ImageIcon("downarrow.gif");
	private ImageIcon blankImageIcon = new ImageIcon("blank.gif");
	
	private GameOverDialog gameOverDialog = null;
	
	private int setDiceRoundNumber = 0;*/
	
	private JLabel currentPlayerLabel = new JLabel();
	private JLabel vehicleLabel = new JLabel();
	private JLabel balanceLabel = new JLabel();
	private JLabel movementLabel = new JLabel();
	private ToyPanel toyPanel = new ToyPanel();
	private TeleporterPartsPanel teleporterPartsPanel = new TeleporterPartsPanel();
	
	private JLabel weatherDial = null;
	private JButton buyToyButton = new JButton("Buy Toy");
	private JButton sellToyButton = new JButton("Sell Toy");
	private JButton buyVehicleButton = new JButton("Buy Vehicle");
	private JButton sellVehicleButton = new JButton("Sell Vehicle");
	private JButton buyPartButton = new JButton("Buy Part");
	private JButton sellPartButton = new JButton("Sell Part");
	private JButton changeWeatherButton = new JButton("Change Weather");
	private JButton digButton = new JButton("Dig");
	private JButton endTurnButton = new JButton("End Turn");
	private JButton instructionsButton = new JButton("Show Instructions");
	
	private VehicleBuyDialog vehicleBuyDialog = new VehicleBuyDialog();
	private PartBuyDialog partBuyDialog = new PartBuyDialog();
	private PartSellDialog partSellDialog = new PartSellDialog();
	private ToySellDialog toySellDialog = new ToySellDialog();
	private ChangeWeatherDialog changeWeatherDialog = new ChangeWeatherDialog();
	private ToyDeploymentDialog toyDeploymentDialog = new ToyDeploymentDialog();
	
	private Weather weather = Weather.NORMAL;
	
	private int weatherTimeout;
	
	private Toy toyToDeploy = null;
	
	public RaceFrame() {
		super(versionString);
		/*for (int i = 0; i < 6; ++i) {
			dieRadio[i] = new JRadioButton("");
		}*/
	}
	
	/**
	 * Create the GUI and show it.  For thread safety,
	 * this method should be invoked from the
	 * event-dispatching thread.
	 */
	public void createAndShowGUI() {
		//Make sure we have nice window decorations.
		//JFrame.setDefaultLookAndFeelDecorated(true);
		
		//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		WindowAdapter wa = new WindowAdapter() {
			
			public void windowClosed(WindowEvent e)  {
				RaceMain.sharedInstance().exit();
			}
			
			public void windowClosing(WindowEvent e)  {
				RaceMain.sharedInstance().exit();
			}
			
		};
		this.addWindowListener(wa);
		
		//TODO: It doesn't look right without this.  Maybe set size better?
		/*HotRocks.getTurn().getYourTurnLabel().setIcon(turnImageIcon);
		
		
		for (int i = 0; i < 6; ++i) {
			ActionListener al = new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int selectedScore = HotRocks.getScoreboard().getSelectedAndEnabledScore();
					if (selectedScore > 0 && HotRocks.getScoreboard().allPointMakers())
						selectRoll();
					else deselectRoll();
					HotRocks.getScoreboard().updateSelectedScore(selectedScore);
				}
			};
			dieRadio[i].setActionCommand("" + i);
			dieRadio[i].addActionListener(al);
		}*/
		
		Container pane = this.getContentPane();
		pane.setLayout(new GridBagLayout());
		
		GridBagConstraints c = new GridBagConstraints();
		
		c.fill = GridBagConstraints.HORIZONTAL;
		
		c.gridx = 0;
		c.gridy = 0;
		pane.add(RaceMain.sharedInstance().getPlayers()[0].getLabel(), c);
		++c.gridx;
		pane.add(RaceMain.sharedInstance().getPlayers()[1].getLabel(), c);
		++c.gridx;
		pane.add(RaceMain.sharedInstance().getPlayers()[2].getLabel(), c);
		++c.gridx;
		pane.add(RaceMain.sharedInstance().getPlayers()[3].getLabel(), c);
		++c.gridx;
		pane.add(getWeatherDial(), c);
		++c.gridx;
		ActionListener instructionsButtonAL = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					showInstructions();
			}
		};
		this.instructionsButton.addActionListener(instructionsButtonAL);
		pane.add(this.instructionsButton, c);
		
		
		c.gridx = 0;
		++c.gridy;
		c.gridwidth = 7;
		pane.add(RaceMain.sharedInstance().getBoard(), c);
		
		c.gridx = 0;
		c.gridwidth = 1;
		++c.gridy;
		c.gridheight = 5;
		c.gridwidth = 2;
		
		this.toyPanel.build();
		System.out.println(c.gridx + "," + c.gridy);
		pane.add(this.toyPanel, c);
		this.teleporterPartsPanel.build();
		++c.gridx;
		++c.gridx;
		System.out.println(c.gridx + "," + c.gridy);
		pane.add(this.teleporterPartsPanel, c);
		
		c.gridheight = 1;
		c.gridwidth = 1;
		++c.gridx;
		//Since we made the previous one 2 columns
		++c.gridx;
		ActionListener buyToyButtonAL = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Toy.buyToy(RaceMain.sharedInstance().getPlayers()[RaceMain.sharedInstance().getPlayersTurn()]);
				} catch (InsufficientFundsException ife) {
					System.err.println("Insufficient funds to buy that toy!");
					JOptionPane.showMessageDialog(RaceFrame.this, "You do not have the required money ($" + (new DirtToWaterToy().getCost()) + ") to buy a toy!");
				}
			}
		};
		this.buyToyButton.addActionListener(buyToyButtonAL);
		pane.add(this.buyToyButton, c);
		++c.gridx;
		ActionListener sellToyButtonAL = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RaceFrame.this.toySellDialog.updateAndSetVisible();
			}
		};
		this.sellToyButton.addActionListener(sellToyButtonAL);
		pane.add(this.sellToyButton, c);
		++c.gridx;
		pane.add(this.currentPlayerLabel, c);
		
		
		++c.gridy;
		//0 and 1 are covered by the current player's toys, 2 and 3 by the current player's parts
		c.gridx = 4;
		ActionListener buyVehicleButtonAL = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RaceFrame.this.vehicleBuyDialog.setVisible(true);
			}
		};
		this.buyVehicleButton.addActionListener(buyVehicleButtonAL);
		pane.add(this.buyVehicleButton, c);
		++c.gridx;
		ActionListener sellVehicleButtonAL = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Vehicle vehicle = RaceMain.sharedInstance().getCurrentPlayer().getVehicle();
				//We won't let them sell a foot, since it's the default Vehicle and they'd get no money for it
				if (vehicle.getCost() > 0) {
					if (JOptionPane.showConfirmDialog(RaceFrame.this, "Are you sure you want to sell your " + vehicle.getName() + " for $" + (vehicle.getCost() / 2) + "?", "Confirm Vehicle Sale", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
						RaceMain.sharedInstance().getCurrentPlayer().setVehicle(new Foot());
						RaceMain.sharedInstance().getCurrentPlayer().addMoney(vehicle.getCost() / 2);
					}
				}
			}
		};
		this.sellVehicleButton.addActionListener(sellVehicleButtonAL);
		pane.add(this.sellVehicleButton, c);
		++c.gridx;
		pane.add(this.balanceLabel, c);
		
		
		++c.gridy;
		//0 and 1 are covered by the current player's toys, 2 and 3 by the current player's parts
		c.gridx = 4;
		ActionListener buyPartButtonAL = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RaceFrame.this.partBuyDialog.setVisible(true);
			}
		};
		this.buyPartButton.addActionListener(buyPartButtonAL);
		pane.add(this.buyPartButton, c);
		++c.gridx;
		ActionListener sellPartButtonAL = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RaceFrame.this.partSellDialog.updateAndSetVisible();
			}
		};
		this.sellPartButton.addActionListener(sellPartButtonAL);
		pane.add(this.sellPartButton, c);
		++c.gridx;
		pane.add(this.vehicleLabel, c);
		
	
		++c.gridy;
		//0 and 1 are covered by the current player's toys, 2 and 3 by the current player's parts
		c.gridx = 4;
		ActionListener changeWeatherButtonAL = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RaceFrame.this.changeWeatherDialog.setVisible(true);
			}
		};
		this.changeWeatherButton.addActionListener(changeWeatherButtonAL);
		pane.add(this.changeWeatherButton, c);
		++c.gridx;
		ActionListener digButtonAl = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					RaceMain.sharedInstance().getCurrentPlayer().dig();
				} catch (CannotDigException cde) {
					//System.err.println("Insufficient funds to buy that toy!");
					JOptionPane.showMessageDialog(RaceFrame.this, cde.getMessage());
				}
			}
		};
		this.digButton.addActionListener(digButtonAl);
		pane.add(this.digButton, c);
		++c.gridx;
		pane.add(this.movementLabel, c);
		
		++c.gridy;
		//0 and 1 are covered by the current player's toys, 2 and 3 by the current player's parts
		c.gridx = 4;
		ActionListener endTurnButtonAL = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RaceMain.sharedInstance().endCurrentPlayersTurn();
			}
		};
		this.endTurnButton.addActionListener(endTurnButtonAL);
		pane.add(this.endTurnButton, c);
		//++c.gridx;
		//++c.gridx;
		
				
	
		RaceMain.sharedInstance().getCurrentPlayer().newTurn();
		
		this.pack();
		
		/*if (HotRocks.getTurn().getWhoseTurn() == HotRocksTurn.OPPONENT_TURN)
			HotRocks.getTurn().getYourTurnLabel().setIcon(blankImageIcon);*/
			
		this.setVisible(true);
		System.out.println("I just set myself visible");
	
	}
	
	public void updateToysLabel() {
		this.toyPanel.updateToys();
		/*StringBuffer sb = new StringBuffer();
		for (Toy tempToy: RaceMain.sharedInstance().getCurrentPlayer().getToys()) {
			sb.append("     " + tempToy.getName() + "\n");
		}
		if (sb.length() < 1) {
			sb.append("(none)");
		}
		this.toysLabel.setText("Toys: " + sb.toString());*/
	}
	
	public void updateBalanceLabel() {
		this.balanceLabel.setText("Balance: $" + RaceMain.sharedInstance().getCurrentPlayer().getBalance());
	}
	
	public void updateVehicleLabel() {
		this.vehicleLabel.setText("Vehicle: " + RaceMain.sharedInstance().getCurrentPlayer().getVehicle().getName());
	}
	
	public void updateMovementLabel() {
		this.movementLabel.setText("Movement Points: " + RaceMain.sharedInstance().getCurrentPlayer().getMovement());
	}
	
	public void updateCurrentPlayerLabel() {
		this.currentPlayerLabel.setText("Current Player: " + RaceMain.sharedInstance().getCurrentPlayer().getName());
	}
	
	public JLabel getWeatherDial() {
		if (this.weatherDial == null) {
			this.weatherDial = new JLabel();
			this.weatherDial.setHorizontalAlignment(JLabel.CENTER);
			this.weatherDial.setVerticalAlignment(JLabel.BOTTOM);
			this.weatherDial.setVerticalTextPosition(JLabel.BOTTOM);
			this.weatherDial.setHorizontalTextPosition(JLabel.CENTER);
			
			/*this.weatherDial.setBorder(BorderFactory.createCompoundBorder(
					BorderFactory.createLoweredBevelBorder(),
					BorderFactory.createEmptyBorder(5,5,5,5)));*/
			this.weatherDial.setText("Current Weather: Normal");
			this.weatherDial.setIcon(new ImageIcon(Terrain.class.getResource("/images/normalWeather.gif")));
		}
		return this.weatherDial;
	}
	
	public void setWeather(Weather weather) {
		this.weather = weather;
		if (weather != Weather.NORMAL) {
			this.weatherTimeout = 4;
		}
		updateWeatherDial();
		updateMovementLabel();
	}
	
	public void changeTurns() {
		if (this.weatherTimeout > 0) {
			--this.weatherTimeout;
		}
		if (this.weatherTimeout == 0) {
			this.weather = Weather.NORMAL;
			updateWeatherDial();
		}
		//In case they canceled and we didn't reset it properly, do so now.
		this.toyToDeploy = null;
		//TODO: Do other turn changing stuff here.
	}
	
	public void updateWeatherDial() {
		
		this.weatherDial.setText("Current Weather: " + this.weather.getName());
		this.weatherDial.setIcon(this.weather.getImageIcon());
	}
	
	public Weather getWeather() {
		return this.weather;
	}
	
	public void useToy(Toy toy) {
		//Select a square to function on
		
		//This copies over the latest terrain/player token updates
		this.toyDeploymentDialog.updateBoard(RaceMain.sharedInstance().getBoard());
		//TODO: Do build at start.  Need to make labels for it, updateBoard won't work after the board has been built
		this.toyDeploymentDialog.build();
		//This makes it visible so the user can make a selection
		this.toyDeploymentDialog.setVisible(true);
		this.toyToDeploy = toy;
		//Do we draw a whole new board and let them select from that?
	}
	
	public void setToySelection(int xToUse, int yToUse) {
		//this.toySelectionDialog.setVisible(false);
		//Verify the toy selection was legit.  If not, call useToy(toy) again.
		Terrain terrainToDeployUpon = RaceMain.sharedInstance().getBoard().getTerrain(xToUse, yToUse);
		if (this.toyToDeploy.isValidStartTerrain(terrainToDeployUpon)) {
			//Remove the toy from the player's inventory
			try {
				RaceMain.sharedInstance().getCurrentPlayer().removeToy(this.toyToDeploy);
			} catch (InvalidToyException ite) {
				//TODO: logical choice here..though technically this should never happen
			}
			//If legit, change the terrain
			RaceMain.sharedInstance().getBoard().setTerrain(xToUse, yToUse, this.toyToDeploy.getEndTerrain());
			this.toyToDeploy = null;
			updateToysLabel();
		}
		else {
			JOptionPane.showMessageDialog(RaceFrame.this, "Invalid toy deployment location!  You must choose a Terrain of type [" + this.toyToDeploy.getStartTerrain() + "]!");
			useToy(this.toyToDeploy);
		}
	}
	
	public void updateTeleporterPartsLabel() {
		this.teleporterPartsPanel.updateTeleporterParts();
	}
	
	public void showInstructions() {
		Foot foot = new Foot();
		Boat boat = new Boat();
		Car car = new Car();
		Plane plane = new Plane();
		Teleporter teleporter = new Teleporter();
		Terrain dirt = Terrain.getDirt();
		Terrain road = Terrain.getRoad();
		Terrain water = Terrain.getWater();
		
		String instructions = "<HTML><pre>" +
			"The goal of " + RaceMain.GAME_NAME + " is to make it to the finish line." + "<BR>" +  
			"To do this, you can either take off on foot, or spend money to buy a vehicle.  Your mode of transporation determines how long it" + "<BR>" + 
			"takes you to cross the terrain.  You get 6 movement points a turn.  Here are how much each terrain slows down a vehicle: " + "<BR>" +  
			"		  Dirt		Road	Water			Cost" + "<BR>" +  
			"On Foot: 	    " + foot.getMovementCost(dirt) + "		  " + foot.getMovementCost(road) + "	  " + foot.getMovementCost(water) + "			$0" + "<BR>" + 
			"By Boat: 	    " + boat.getMovementCost(dirt) + "		  " + boat.getMovementCost(road) + " 	  " + boat.getMovementCost(water) + "			$2000" + "<BR>" + 
			"By Car: 	    " + car.getMovementCost(dirt) + "		  " + car.getMovementCost(road) + "	  " + car.getMovementCost(water) + "			$4000" + "<BR>" + 
			"By Plane:	    " + plane.getMovementCost(dirt) + "  	          " + plane.getMovementCost(road) + "       " + plane.getMovementCost(water) + "			$7000" + "<BR>" + 
			"By Teleporter:	    " + teleporter.getMovementCost(dirt) + "*            " + teleporter.getMovementCost(road) + "*      " + teleporter.getMovementCost(water) + "*                    All 10 teleporter parts" + "<BR>" + 
			"                * The teleporter can move to any square in the game for 1 movement point" + "<BR>" + 
			"You start the game with $5000.  Which means you can't buy a plane right away.  You can earn money by digging for teleport parts" + "<BR>" + 
			"(Instead of moving that turn) and then selling them (they sell for $500 each)" + "<BR>" + 
			"You can also buy teleport parts for $1000 each (which would allow you to complete your teleporter more quickly)" + "<BR>" + 
			"You can also use your money to buy toys (which allow you to change one terrain square into another terrain square - each toy is specific" + "<BR>" + 
			" - example a 'Dirt-to-Water' toy, and they're distributed at random during purchase." + "<BR>" + 
			"The other way to spend money is to pay $1000 to use the weather machine to change the weather.  The weather affects the different " + "<BR>" + 
			"vehicles in different ways - the affects to the vehicles are detailed on the weather changing purchasing screen." + "<BR>" + 
			"That should cover everything!</pre></HTML>";
		JOptionPane.showMessageDialog(RaceFrame.this, instructions, "Instructions", JOptionPane.INFORMATION_MESSAGE);
	}
	
	public static void main(String[] args) {
		//Schedule a job for the event-dispatching thread:
		//creating and showing this application's GUI.
		//HotRocksFrame hrm = new HotRocksFrame();
		 /* javax.swing.SwingUtilities.invokeLater(new Runnable() {
		 public void run() {
		 RaceFrame hrf = new RaceFrame();
		 hrf.createAndShowGUI();
		 }
		 });*/
	}
}
