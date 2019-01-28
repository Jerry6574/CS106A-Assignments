/*
 * File: PythagoreanTheorem.java
 * Name: 
 * Section Leader: 
 * -----------------------------
 * This file is the starter file for the PythagoreanTheorem problem.
 */

import acm.program.*;

public class PythagoreanTheorem extends ConsoleProgram {
	public void run() {
		int a = readInt("Enter values to compute Pythagorean Theorem. \na: ");
		int b = readInt("b: ");
		
		double c = Math.sqrt(Math.pow(a, 2) + Math.pow(b, 2));
		print("c = " + c);
	}
}
