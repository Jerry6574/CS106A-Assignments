/*
 * File: Hangman.java
 * ------------------
 * This program will eventually play the Hangman game from
 * Assignment #4.
 */

import acm.program.*;
import acm.util.*;


public class Hangman extends ConsoleProgram {
	private String secretWord;
	private String partiallyGuessedWord;
	private int guessCount;
	private HangmanCanvas canvas;
	

	public void init() {
		secretWord = getSecretWord();
		guessCount = 8;
		partiallyGuessedWord = "";
		for(int i = 0; i < secretWord.length(); i++) {
			partiallyGuessedWord += "-";
		}
		
		canvas = new HangmanCanvas();
		add(canvas);

		canvas.reset();
		canvas.displayWord(partiallyGuessedWord);
	}
	
    public void run() {
    	// cheat mode
//    	println("Secret word is " + secretWord);
    	
    	println("Welcome to Hangman!");
    	displayStatus();
    	canvas.displayWord(partiallyGuessedWord);
    	
    	while(!partiallyGuessedWord.equals(secretWord) && guessCount != 0) {
    		enterLetter();
    		displayStatus();
    		canvas.displayWord(partiallyGuessedWord);
    	}
	}
    
    private String getSecretWord() {
    	RandomGenerator rgen = new RandomGenerator();
    	HangmanLexicon lexicon = new HangmanLexicon();
    	
    	int randomIndex = rgen.nextInt(0, lexicon.getWordCount()-1);
    	String secretWord = lexicon.getWord(randomIndex);
    	
    	return secretWord;
    }
    
    private void displayStatus() {
    	// check if player lost
    	if(guessCount == 0) {
    		println("You're completely hung. ");
    		println("The word was: " + secretWord);
    		println("You lose. ");
    		return;
    	}
    	
    	// check if player won
    	if(partiallyGuessedWord.equals(secretWord)) {
    		println("You guessed the word: " + secretWord);
    		println("You win. ");
    		return;
    	}
    	
    	println("The word now looks like this: " + partiallyGuessedWord);
    	
    	if(guessCount > 1) {
    		println("You have " + guessCount + " guesses left. ");
    	} else {
    		println("You have only one guess left. ");
    	}
    	
    }
    
    private void enterLetter() {
    	String letterStr = readLine("Your guess: ").toUpperCase();
    	char letterChar = letterStr.charAt(0);
    	
    	// check if input is legal single letter
    	if(!(letterStr.length() == 1 && Character.isLetter(letterChar))) {
    		println("Your guess is illegal. ");
    		return;
    	}
    	
    	if(partiallyGuessedWord.contains(letterStr)) {
    		return;
    	} else if(secretWord.contains(letterStr)) {
    		StringBuilder newPartiallyGuessedWord = new StringBuilder(partiallyGuessedWord);
    		for(int i = 0; i < secretWord.length(); i++) {
    			if(letterChar == secretWord.charAt(i)) {
    				newPartiallyGuessedWord.setCharAt(i, letterChar);
    			}
    			partiallyGuessedWord = newPartiallyGuessedWord.toString();
    		}
    	} else {
    		println("There are no " + letterStr + "'s in the word. ");
    		canvas.noteIncorrectGuess(letterChar);
    		guessCount--;
    	}
    	
    }
    
}
