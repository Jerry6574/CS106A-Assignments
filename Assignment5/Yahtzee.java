/*
 * File: Yahtzee.java
 * ------------------
 * This program will eventually play the Yahtzee game.
 */


import acm.io.*;
import acm.program.*;
import acm.util.*;
import java.util.*;


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
		scoreCard = new int[nPlayers][N_CATEGORIES];
		
		// boolean array initialized to false
		usedCategories = new boolean[nPlayers][N_SCORING_CATEGORIES];
		for(int i = 0; i < N_SCORING_CATEGORIES; i++) {
			for(int j = 1; j <= nPlayers; j++) {
				playRound(j);
			}
		}
		
		int upperBonusThreshold = 63;
		
		for(int i = 1; i <= nPlayers; i++) {
			for(int j = 0; j < SIXES; j++) {
				scoreCard[i-1][UPPER_SCORE-1] += scoreCard[i-1][j];
			}
			
			display.updateScorecard(UPPER_SCORE, i, scoreCard[i-1][UPPER_SCORE-1]);
			
			// add bonus if meet condition
			if(scoreCard[i-1][UPPER_SCORE-1] >= upperBonusThreshold) {
				scoreCard[i-1][UPPER_BONUS-1] = 35;
			} else {
				scoreCard[i-1][UPPER_BONUS-1] = 0;
			}
			
			display.updateScorecard(UPPER_BONUS, i, scoreCard[i-1][UPPER_BONUS-1]);
			
			for(int j = THREE_OF_A_KIND; j < CHANCE; j++) {
				scoreCard[i-1][LOWER_SCORE-1] += scoreCard[i-1][j];
			}
			
			display.updateScorecard(LOWER_SCORE, i, scoreCard[i-1][LOWER_SCORE-1]);
			
			scoreCard[i-1][TOTAL-1] = scoreCard[i-1][UPPER_SCORE-1] + scoreCard[i-1][UPPER_BONUS-1] + scoreCard[i-1][LOWER_SCORE-1];
			display.updateScorecard(TOTAL, i, scoreCard[i-1][TOTAL-1]);
			
			int currentMaxTotal =  0;
			int currentWinner = 1;
			
			// find the winner index
			for(int j = 1; j <= nPlayers; j++) {
				if(scoreCard[j-1][TOTAL-1] > currentMaxTotal) {
					currentMaxTotal = scoreCard[j-1][TOTAL-1];
					currentWinner = j;
				}
			}
			
			display.printMessage("Congradullcation, " + playerNames[currentWinner] + ", you're the winnier with a total score of " + currentMaxTotal + "!");
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
	
	private void playRound(int player) {
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

		boolean isValidCategory = checkCategory(dice, category); 
		
		int score;
		
		if(isValidCategory) {
			score = calculateScore(category, dice);
			
		} else {
			score = 0;
		}
		
		scoreCard[player-1][category-1] = score;
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
	
	private boolean checkCategory(int[] dice, int category) {
		switch (category) {
		
		// FOUR_OF_A_KIND and YAHTZEE can be categorized as THREE_OF_A_KIND
		case THREE_OF_A_KIND:
			return checkNOfAKind(dice, 3, false);
		
		// YAHTZEE can be categorized as FOUR_OF_A_KIND
		case FOUR_OF_A_KIND:
			return checkNOfAKind(dice, 4, false);
			
		case FULL_HOUSE:
			// check if dice have proper 3 of a kind and a pair
			// Yahtzee is not a FULL_HOUSE
			if(checkNOfAKind(dice, 3, true) && checkNOfAKind(dice, 2, true)) {
				return true;
			} else {
				return false;
			}
			
		case SMALL_STRAIGHT:
			Arrays.sort(dice);
			
			// in a sorted dice sequence, if there are 3 increment counts from the current die value, it's a small straight. 
			int currentDieValue = dice[0];
			int incrementCounts = 0;
			
			for(int i = 1; i < N_DICE; i++) {
				// advance currentDieValue if the next dice is a +1 increment or next die is at 1 index
				if(currentDieValue + 1 == dice[i]) {
					currentDieValue = dice[i];
					incrementCounts++;
				
				// handle dice == {1, 3, 4, 5, 6} case
				} else if(i < 2){
					currentDieValue = dice[i];
				}
			}
			
			if(incrementCounts >= 3) {
				return true;
			} else {
				return false;
			}
			

		case LARGE_STRAIGHT:
			Arrays.sort(dice);
			
			// check if every die is distinct
			for(int i = 0; i < N_DICE-1; i++) {
				if(dice[i] == dice[i+1]) {
					return false;
				}
			}
			
			// check if dice is a consecutive sequence (difference between max and min is 4)
			if(dice[N_DICE-1] - dice[0] == N_DICE - 1) {
				return true;
			} else {
				return false;
			}

		case YAHTZEE:
			int die0 = dice[0];
			
			// check if all die values equal to each other
			for(int i = 1; i < N_DICE; i++) {
				if(die0 != dice[i]) {
					return false;
				}
			}
			return true;
		
		// ONES to SIXES and CHANCE always return true
		default:
			return true;
		}
	}
	
	private boolean checkNOfAKind(int[] dice, int n, boolean proper) {
		// compare every dice with every other dice, O(n^2)
		for(int i = 0; i < N_DICE; i++) {
			int matchCounts = 0; 
			for(int j = 0; j < N_DICE; j++) {
				if(dice[i] == dice[j]) {
					matchCounts++;
				}
			}
			
			// e.g. a proper 3 of a kind means exactly 3 of kind
			if(proper) {
				if(matchCounts == n) {
					return true;
				}
			} else {
				if(matchCounts >= n) {
					return true;
				}
			}

		}
		return false;
	}

/* Private instance variables */
	private int nPlayers;
	private String[] playerNames;
	private YahtzeeDisplay display;
	private RandomGenerator rgen = new RandomGenerator();
	
	private int[] dice = new int[N_DICE];
	private boolean[][] usedCategories;
	private int[][] scoreCard;
}
