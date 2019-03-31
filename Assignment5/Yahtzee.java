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
		IODialog dialog = getDialog();
		nPlayers = dialog.readInt("Enter number of players");
		playerNames = new String[nPlayers];
		for (int i = 1; i <= nPlayers; i++) {
			playerNames[i - 1] = dialog.readLine("Enter name for player " + i);
		}
		
		display = new YahtzeeDisplay(getGCanvas(), playerNames);
		playGame();
	}

	private void playGame() {
		// boolean array initialized to false
		usedCategories = new boolean[nPlayers][N_SCORING_CATEGORIES];
		for(int i = 0; i < N_SCORING_CATEGORIES; i++) {
			for(int j = 1; j <= nPlayers; j++) {
				playTurn(j);
			}
		}
	}
	
	private void reroll() {
		display.printMessage("Select the dice you wish to re-roll and click \"Roll Again\". ");
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
	
	private void playTurn(int player) {
		display.printMessage(playerNames[player - 1] + "'s turn. Click \"Roll Dice\" to roll the dice. ");
		display.waitForPlayerToClickRoll(player);
		
		rollDice();
		reroll();
		reroll();
		
		int category;
		while(true) {
			display.printMessage("Select a category for this roll. ");
			category = display.waitForPlayerToSelectCategory();
			int categoryIndex;
			
			// offset category value to category index
			if(category <= 6) {
				categoryIndex = category - 1;
			} else {
				categoryIndex = category - 3;
			}
			
			// allow player to use category has not been used
			if(!usedCategories[player - 1][categoryIndex]) {
				usedCategories[player - 1][categoryIndex] = true;
				break;
			// category has been used
			} else {
				display.printMessage("Please select a category that has not yet been used. ");
			}
		}

		boolean isValidCategory = YahtzeeMagicStub.checkCategory(dice, category); 
		
		int score;
		
		if(isValidCategory) {
			score = calculateScore(category, dice);
		} else {
			score = 0;
		}
		
		display.updateScorecard(category, player, score);
	}
	
	private int calculateScore(int category, int[] dice) {
		int score = 0;
		
		if(category <= SIXES) {
			score = onesToSixesScore(category);
			return score;
		}
		
		switch (category) {		
		case FULL_HOUSE:
			score = 25;
			break;
			
		case SMALL_STRAIGHT:
			score = 30;
			break;
			
		case LARGE_STRAIGHT:
			score = 40;
			break;
			
		case YAHTZEE:
			score = 50;
			break;
		
		// score calculation for THREE_OF_A_KIND, FOUR_OF_A_KIND and CHANCE
		default:
			for(int die: dice) {
				score += die;
			}
			break;
		}
		
		return score;
	}
	
	private int onesToSixesScore(int n) {
		int score = 0; 
		for(int die: dice) {
			if(die == n) {
				score += n;
			}
		}
		
		return score;
	}
	

/* Private instance variables */
	private int nPlayers;
	private String[] playerNames;
	private YahtzeeDisplay display;
	private RandomGenerator rgen = new RandomGenerator();
	private int[] dice = new int[N_DICE];
	private boolean[][] usedCategories;
}
