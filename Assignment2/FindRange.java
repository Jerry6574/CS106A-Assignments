/*
 * File: FindRange.java
 * Name: 
 * Section Leader: 
 * --------------------
 * This file is the starter file for the FindRange problem.
 */

import acm.program.*;

public class FindRange extends ConsoleProgram {
	public void run() {
		int sentinel = 0;
		println("This program finds the largest and smallest numbers. ");
		int input = readInt("? ");
		
		if(input == sentinel) {
			println("Please enter a non-zero integer");
			return;
		}
		int largest = input;
		int smallest = input;
		
		while(true) {
			if(input == sentinel) {
				println("smallest: " + smallest);
				println("largest: " + largest);
			} else {
				if(input > largest) {
					largest = input;
				} else if(input < smallest){
					smallest = input;
				}
			}
			input = readInt("? ");
		}
	}
}

