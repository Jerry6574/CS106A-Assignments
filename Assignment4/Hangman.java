/*
 * File: Hangman.java
 * ------------------
 * This program will eventually play the Hangman game from
 * Assignment #4.
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.awt.*;

import com.sun.org.apache.xpath.internal.operations.And;

public class Hangman extends ConsoleProgram {
	private String secretWord = "COMPUTER";
	private String guessedWord = "";
	
	private int guesses = 8;
	
    public void run() {
    	println("Welcome to Hangman!");
    	
    	for(int i = 0; i < secretWord.length(); i++) {
    		guessedWord += "-";
    	}
    	
    	displayStatus();
    	while(guesses != 0 && guessedWord.contains("-")) {
    		makeGuess();
    		
    	}
	}
    
    private void displayStatus() {
    	String wordMsg = "The word now looks like this: ";
    	String wordStatus = wordMsg + guessedWord;
    	
    	if(guesses > 1 && guessedWord.contains("-")) {
        	String guessesStatus = "You have " + guesses + " guesses left. ";
        	println(wordStatus);
        	println(guessesStatus);
        	print("You guess: ");
    	} else if(guesses == 1) {
    		String guessesStatus = "You have only one guess left. ";
        	println(guessesStatus);
        	print("You guess: ");
    	} else if(guesses == 0) {
    		println("You're completely hung. ");
    		println("The word was: " + secretWord);
    		println("You lose. ");
    	} else {
    		println("You guessed the word: " + secretWord);
    		println("You win.");
    	}
    	
    }
    
    private void makeGuess() {
    	String guessedString = readLine();
    	if(guessedString.length() != 1 || !Character.isLetter(guessedString.charAt(0))) {
    		println("Your guess is illegal, please guess a single letter. ");
    		return;
    	}
    	char guessedLetter = Character.toUpperCase(guessedString.charAt(0));
    	StringBuilder updatedWord = new StringBuilder(guessedWord);
    	String guessStatus = "There are no " + guessedLetter + "'s in the word.";
    	
    	boolean guessedCorrect = false;
    	
    	for(int i = 0; i < secretWord.length(); i++) {
    		
    		boolean correctAtI = guessedLetter == secretWord.charAt(i);
    		if(correctAtI) {
    			updatedWord.setCharAt(i, guessedLetter);
    			guessStatus = "That guess is correct.";
    			guessedCorrect = guessedCorrect || correctAtI;
    		}
    	}
    	guessedWord = updatedWord.toString();
    	if(!guessedCorrect) {
    		guesses--;
    	}
  
    	println(guessStatus);
    	displayStatus();
    }
}
