/*
 * File: Hailstone.java
 * Name: 
 * Section Leader: 
 * --------------------
 * This file is the starter file for the Hailstone problem.
 */

import acm.program.*;

public class Hailstone extends ConsoleProgram {
	public void run() {
		int n = readInt("Enter a number: ");
		int steps = 0; 
		
		while(n != 1) {
			if(n % 2 == 0) {
				int previous_n = n;
				n /= 2;
				println(previous_n + " is even so I take half: " + n);
			} else {
				int previous_n = n;
				n = 3 * n + 1;
				println(previous_n + " is odd, so I make 3n + 1: " + n);
			}
			steps++;
		}
		println("The process took " + steps + " steps to reach 1");
		
	}
}

