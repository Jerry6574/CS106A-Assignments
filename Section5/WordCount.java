import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import acm.program.*;
import acm.util.ErrorException;

public class WordCount extends ConsoleProgram {
    public void run() {
    	String filename = readLine("File: ");
    	BufferedReader rd = openFile(filename);
    	
    	int lineCount = 0;
    	int charCount = 0; 
    	int wordCount = 0;
    	
    	boolean curCharIsLetterOrDigit = false;
    	boolean prevCharIsLetterOrDigit = false;
		try {
			while(true){
				String line = rd.readLine();
				if(line == null){
					break;
				}
				lineCount++;
				for(int i = 0; i < line.length()-1; i++) {
					prevCharIsLetterOrDigit = Character.isLetterOrDigit(line.charAt(i));
					curCharIsLetterOrDigit = Character.isLetterOrDigit(line.charAt(i+1));
					
					// word counting mechanism
					if((prevCharIsLetterOrDigit != curCharIsLetterOrDigit && prevCharIsLetterOrDigit) ||
					   (i == line.length()-2 && curCharIsLetterOrDigit)) {
						println(line.charAt(i) + " + " + line.charAt(i+1));
						wordCount++;
					}
				}
				charCount += line.length();
			}
			rd.close();
			println("Lines = " + lineCount);
			println("Words = " + wordCount);
			println("Chars = " + charCount);
		} 
		catch(IOException ex){
			throw new ErrorException(ex);
		}
    }
    
    private BufferedReader openFile(String filename){
    	BufferedReader rd = null;
    	
    	while(rd == null){
    		try{
    			rd = new BufferedReader(new FileReader(filename));
    		} catch(IOException ex){
    			println("Nice try punk. That file doesn't exist. ");
    		}
    	}
    	return rd;
    }
}
