package com.dijoism.race;

import java.util.Random;

import javax.swing.JOptionPane;
/**
 * 
 * 2008.08.09 - Dijoist - Created
 *
 */

public class RaceMain {
	
	private Board board;
	private Player players[];
	private int playersTurn;
	
	public static final String GAME_NAME = "Race";
	
	private static RaceMain self = new RaceMain();
	
	private RaceFrame frame;
	
	private RaceMain() {}
	
	public static RaceMain sharedInstance() {
		return self;
	}
	
	public void init() {
		//Setup the board
		this.board = new Board();
		this.board.initBoard();
		//Setup the players (defaulting to 4)
		//TODO: Make it so the user can pick the number of players
		int playerCount = 4;
		this.players = new Player[playerCount];
		for (int i = 0; i < playerCount; ++i) {
			//TODO: Make it so the user can pick the player names
			this.players[i] = new Player("Player" + (i + 1), i + 1);
			System.out.println("Adding player [" + (i + 1) + "]");
		}
		//Pick a player to go first
		Random random = new Random(System.currentTimeMillis());
		this.playersTurn = random.nextInt(4);
		
		//Create the GUI
		this.frame = new RaceFrame();
		
	}
	
	public void exit() {
		System.exit(0);
	}
	
	public Player[] getPlayers() {
		return this.players;
	}
	
	public Board getBoard() {
		return this.board;
	}
	
	public Player getCurrentPlayer() {
		return this.players[this.playersTurn];
	}
	
	public void start() {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				self.frame.createAndShowGUI();
			}
		});
	}
	
	public static void main(String[] args) {
		self.init();
		self.start();
	}
	
	public int getPlayersTurn() {
		return this.playersTurn;
	}
	
	public void endCurrentPlayersTurn() {
		getCurrentPlayer().endTurn();
		++this.playersTurn;
		if (this.playersTurn > 3) {
			this.playersTurn = 0;
		}
		this.frame.changeTurns();
		getCurrentPlayer().newTurn();
	}
	
	public RaceFrame getRaceFrame() {
		return this.frame;
	}

	public void endGame(Player currentPlayer) {
		JOptionPane.showMessageDialog(this.frame, currentPlayer.getName() + " wins the game!");
		//TODO: Either exit the game or allow for "Play Again?" her
	}
	
	
}
