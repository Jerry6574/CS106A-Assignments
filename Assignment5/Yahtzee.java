/*
 * File: Yahtzee.java
 * ------------------
 * This program will eventually play the Yahtzee game.
 */

import acm.io.*;
import acm.program.*;
import acm.util.*;


public class Yahtzee extends GraphicsProgram implements YahtzeeConstants {
	
	public static void main(String[] args) {
		new Yahtzee().start(args);
		
	}
	
	public void run() {
//		IODialog dialog = getDialog();
//		nPlayers = dialog.readInt("Enter number of players");
//		playerNames = new String[nPlayers];
//		for (int i = 1; i <= nPlayers; i++) {
//			playerNames[i - 1] = dialog.readLine("Enter name for player " + i);
//		}
		
		// Temperary playerNames
		String[] playerNames = {"Jerry", "Qiwen"};
		
		display = new YahtzeeDisplay(getGCanvas(), playerNames);
		playGame();
	}

	private void playGame() {
		
		// generate random dice
		int[] dice = new int[N_DICE];
		for(int i = 0; i < N_DICE; i++) {
			dice[i] = rgen.nextInt(1, 6);
		}
		
		display.displayDice(dice);
	}
		
/* Private instance variables */
	private int nPlayers;
	private String[] playerNames;
	private YahtzeeDisplay display;
	private RandomGenerator rgen = new RandomGenerator();
	
}
