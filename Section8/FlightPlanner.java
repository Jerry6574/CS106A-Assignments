import java.io.*;
import java.util.*;

import acm.program.*;
import acm.util.*;


public class FlightPlanner extends ConsoleProgram {
    
	private String filename;
	private BufferedReader rd;
	private HashMap<String, ArrayList<String>> cityToDestinations;
	
	public void init() {
    	filename = "flights.txt";
    	rd = openFile(filename);
    	cityToDestinations = new HashMap<>();
    	addCityToDestinations();
	}
	
    public void run() {
    	printWelcomeMsg();
    	planTrip();
    }
    
    private BufferedReader openFile(String filename){
    	BufferedReader rd = null;
    	
		try{
			rd = new BufferedReader(new FileReader(filename));
		} catch(IOException ex){
			println("Please provide a valid filename. ");
		}
    	return rd;
    }
    
    private void addCityToDestinations() {
    	try {
	    	while(true) {
	    		String line = rd.readLine();
	    		if(line == null) {
	    			break;
	    		} else if(!line.equals("")) {
	        		String arrow = " -> ";
	        		int arrowPos = line.indexOf(arrow);
	        		String city = line.substring(0, arrowPos);
	        		String destination = line.substring(arrowPos + arrow.length());
	        		
	        		ArrayList<String> destinations;
	        		if(!cityToDestinations.containsKey(city)) {
	        			destinations = new ArrayList<>();
	        		} else {
	        			destinations = cityToDestinations.get(city);
	        		}
	        		
        			destinations.add(destination);
        			cityToDestinations.put(city, destinations);
	    		}
	    	}
	    	rd.close();
    	}
		catch(IOException ex){
			throw new ErrorException(ex);
		}
    }
    
    private void printWelcomeMsg() {
    	println("Welcome to Flight Planner! ");
    	println("Here's a list of all the cities in our database: ");
    	
    	for(String city: cityToDestinations.keySet()) {
    		println(" " + city);
    	}
    	println("Let's plan a round trip route! ");
    }
    
    private void printDestinations(String city) {
    	for(String destination: cityToDestinations.get(city)) {
    		println(" " + destination);
    	}

    }
    
    private void planTrip() {
    	while(true) {
        	String startCity = readLine("Enter the starting city: ");
        	if(cityToDestinations.containsKey(startCity)) {
            	println("From " + startCity + " you can fly directly to: ");
            	printDestinations(startCity);
            	println("Where do you want to go from " + startCity + " ?");
        	} else {
        		println("Please enter a valid city. ");
        	}

    	}

    	
//    	String nextCity = "";
//    	
//    	while(!nextCity.equals(startCity)) {
//    		
//    	}
    }
    
    
}
