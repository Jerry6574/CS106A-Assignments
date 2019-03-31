/*
 * File: Yahtzee.java
 * ------------------
 * This program will eventually play the Yahtzee game.
 */

import java.util.Locale.Category;

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
		display.waitForPlayerToClickRoll(1);
		
		rollDice();
		reroll();
		reroll();
		
		int category = display.waitForPlayerToSelectCategory();
		boolean isValidCategory = YahtzeeMagicStub.checkCategory(dice, category); 
//		display.updateScorecard(category, player, score);
		println(category);
		println(isValidCategory);
	}
	
	private void reroll() {
		display.waitForPlayerToSelectDice();
		
		for(int i = 0; i < N_DICE; i++) {
			if(display.isDieSelected(i)) {
				dice[i] = rgen.nextInt(1, 6);
			}
		}
		
		display.displayDice(dice);
	}
	
	private void rollDice() {
		for(int i = 0; i < N_DICE; i++) {
			dice[i] = rgen.nextInt(1, 6);
		}
		
		display.displayDice(dice);
	}
	
	private void turn(int player) {
		
	}
/* Private instance variables */
	private int nPlayers;
	private String[] playerNames;
	private YahtzeeDisplay display;
	private RandomGenerator rgen = new RandomGenerator();
	int[] dice = new int[N_DICE];
}
