import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import acm.program.*;
import acm.util.ErrorException;

public class Histograms extends ConsoleProgram{
	public void run() {
    	String filename = readLine("MidTermScore File: ");
    	BufferedReader rd = openFile(filename);
    	while(rd == null) {
    		filename = readLine("File: ");
    		rd = openFile(filename);
    	}
    	
    	String[] midTermScoreLabels = {"00-09: ", "10-19: ", "20-29: ", "30-39: ", "40-49: ", 
    								   "50-59: ", "60-69: ", "70-79: ", "80-89: ", "90-99: ", "  100: "};
    	String[] scoreCounts = new String [11];
    	for(int i = 0; i < scoreCounts.length; i++) {
    		scoreCounts[i] = "";
    	}
    	
    	int[] midTermScoreBins = new int[11];
    	
		try {
			while(true){
				String line = rd.readLine();
				if(line == null) {
					break;
				}
				int score = Integer.parseInt(line);
				
				int binIndex = score / 10;
				midTermScoreBins[binIndex] += 1;
			}
			rd.close();
			
			for(int i = 0; i < midTermScoreBins.length; i++) {
				for(int j = 0; j < midTermScoreBins[i]; j++) {
					scoreCounts[i] += "*";
				}
				println(midTermScoreLabels[i] + scoreCounts[i]);
			}
		} 
		catch(IOException ex){
			throw new ErrorException(ex);
		}
	}
	
    private BufferedReader openFile(String filename){
    	BufferedReader rd = null;
    	
		try{
			rd = new BufferedReader(new FileReader(filename));
		} catch(IOException ex){
			println("Nice try punk. That file doesn't exist. ");
		}
    	return rd;
    }
}
