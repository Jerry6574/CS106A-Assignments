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
    	planTrip2();
//    	planTrip();
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
    	if(cityToDestinations.containsKey(city)) {
        	println("From " + city + " you can fly directly to: ");
           	for(String destination: cityToDestinations.get(city)) {
        		println(" " + destination);
        	}
        	print("Where do you want to go from " + city + " ? ");
    	} else {
    		println("You can't get to that city by a direct flight");
    	}
    }
    
    private void planTrip() {
    	String startCity = readLine("Enter the starting city: ");
    	String nextCity = "";
    	ArrayList<String> route = new ArrayList<>();
 	
		printDestinations(startCity);
		nextCity = readLine();
		route.add(startCity);
    	
    	while(!startCity.equals(nextCity)) {
			printDestinations(nextCity);
			nextCity = readLine();
			route.add(nextCity);
    	}
		for(String city: route) {
			print(city + " -> ");
		}
		print(startCity);
    }
    
    private void planTrip2() {
    	String startCity = "";
    	String nextCity = "";
    	ArrayList<String> route = new ArrayList<>();
    	
    	while(true) {
    		if(startCity.equals("")) {
    			startCity = readLine("Enter the starting city: ");
    			
    			if(cityToDestinations.containsKey(startCity)) {
    				printDestinations(startCity);
    				nextCity = readLine();
    			} else {
    				print("You can't get to that city by a direct flight. ");
    			}
    			
    			route.add(nextCity);
    			
    		} else if(!startCity.equals("") && !startCity.equals(nextCity)) {
    			printDestinations(nextCity);
    			nextCity = readLine();
    			route.add(nextCity);
    			
    			if(cityToDestinations.containsKey(startCity)) {
    				printDestinations(nextCity);
    				nextCity = readLine();
    			} else {
    				print("You can't get to that city by a direct flight. ");
    			}
    		}
    	}
    }
}
