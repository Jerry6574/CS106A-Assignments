/*
 * File: Yahtzee.java
 * ------------------
 * This program will eventually play the Yahtzee game.
 */


import acm.io.*;
import acm.program.*;
import acm.util.*;
import sun.security.provider.JavaKeyStore.CaseExactJKS;


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
//		
//		display = new YahtzeeDisplay(getGCanvas(), playerNames);
//		playGame();
		
//		// Test Yahtzee
//		for(int i = 0; i < N_DICE-1; i++) {
//			dice[i] = 1;
//		}
//		dice[N_DICE-1] = 6;
//		int category = YAHTZEE;
		
//		// Test THREE_OF_A_KIND
//		dice[0] = 1;
//		dice[1] = 1;
//		dice[2] = 2;
//		dice[3] = 2;
//		dice[4] = 4;
//		int category = THREE_OF_A_KIND;
//		
		// Test FOUR_OF_A_KIND
		dice[0] = 1;
		dice[1] = 1;
		dice[2] = 2;
		dice[3] = 1;
		dice[4] = 1;
		int category = FOUR_OF_A_KIND;
		
		println(checkCategory(dice, category));
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

		boolean isValidCategory = YahtzeeMagicStub.checkCategory(dice, category); 
		
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
		case THREE_OF_A_KIND:

			
			
		case FOUR_OF_A_KIND:
			
			return false;
			
		case FULL_HOUSE:
			
			return false;
			
		case SMALL_STRAIGHT:
			
			return false;
			
		case LARGE_STRAIGHT:
			return false;
			
			
		case YAHTZEE:
			int die0 = dice[0];
			for(int i = 1; i < N_DICE; i++) {
				if(die0 != dice[i]) {
					return false;
				}
			}
			return true;
		
		default:
			return true;
		}
	}
	
	private boolean checkNofKind(int dice, int n) {
		for(int i = 0; i < N_DICE; i++) {
			int matchCounts = 0; 
			for(int j = 0; j < N_DICE; j++) {
				if(dice[i] == dice[j]) {
					matchCounts++;
				}
			}
			
			if(matchCounts >= 3) {
				return true;
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
