/*
 * File: HangmanLexicon.java
 * -------------------------
 * This file contains a stub implementation of the HangmanLexicon
 * class that you will reimplement for Part III of the assignment.
 */

import java.io.*;
import java.util.ArrayList;


public class HangmanLexicon {
	private BufferedReader lexiconReader;
	private ArrayList<String> lexicon;
	
	public HangmanLexicon() {
		String filename = "HangmanLexicon.txt";
		try{
			lexiconReader = new BufferedReader(new FileReader(filename));
			lexicon = new ArrayList<>();
			while(true) {
				String word = lexiconReader.readLine();
				if(word == null){
					break;
				}
				lexicon.add(word);
			}

		} catch(IOException ex){
			System.out.println("That file doesn't exist. ");
		}
	}
	

/** Returns the number of words in the lexicon. */
	public int getWordCount() {
		return lexicon.size();
	}

/** Returns the word at the specified index. */
	public String getWord(int index) {
		return lexicon.get(index);
	}
}
