/*
 * File: StoneMasonKarel.java
 * --------------------------
 * The StoneMasonKarel subclass as it appears here does nothing.
 * When you finish writing it, it should solve the "repair the quad"
 * problem from Assignment 1.  In addition to editing the program,
 * you should be sure to edit this comment so that it no longer
 * indicates that the program does nothing.
 */

import stanford.karel.*;

public class StoneMasonKarel extends SuperKarel {
	public void run(){
		while(frontIsClear()){
			repairColumn();
		}
		repairColumn();
	}
	
	private void repairColumn(){
		turnLeft();
		while(frontIsClear()){
			repair();
			move();
		}
		repair();
		nextColumn();
	}
	
	private void repair(){
		if(noBeepersPresent()){
			putBeeper();
		}
	}
	
	private void nextColumn(){
		// easier to navigate to next column on the floor than on the roof since column height can vary
		moveToFloor();
		// move to east 4 unit for next column
		if(frontIsClear()){
			for(int i = 0; i < 4; i++){
				move();
			}
		}
	}
	
	private void moveToFloor(){
		turnAround();
		while(frontIsClear()){
			move();
		}
		turnLeft();
	}
}
