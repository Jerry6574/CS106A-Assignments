/*
 * File: CheckerboardKarel.java
 * ----------------------------
 * When you finish writing it, the CheckerboardKarel class should draw
 * a checkerboard using beepers, as described in Assignment 1.  You
 * should make sure that your program works for all of the sample
 * worlds supplied in the starter folder.
 */

import stanford.karel.*;

public class CheckerboardKarel extends SuperKarel {

	public void run(){
		int colNum = getColNum();
		
		putOneRowCheck(colNum);
		
		// if leftIsClear() is true when facing East, Karel is not on ceiling 
		while(leftIsClear()){
			uTurnToWest();
			putOneRowCheck(colNum);
			
			// if rightIsClear() is true when facing West, Karel is not on ceiling 
			if(rightIsClear()){
				uTurnToEast();
				putOneRowCheck(colNum);
			} 
			else {
				turnAround();
			}
		}
	}
	
	private void putOneRowCheck(int colNum){
		int i = 0; 
		int checkerParity = 0; 
		
		if(facingWest() && colNum % 2 == 1 ){
			checkerParity = 1;
		}
		
		while(frontIsClear()){

			if(i % 2 == checkerParity){
				putBeeper();
			}
			move();
			i++;
		}
		
		if(i % 2 == checkerParity){
			putBeeper();
		}
	}
	
	private void uTurnToWest(){
		turnLeft();
		move();
		turnLeft();
	}
	
	private void uTurnToEast(){
		turnRight();
		move();
		turnRight();
	}
	
	private int getColNum(){
		int colNum = 1;
		
		while(frontIsClear()){
			move();
			colNum++;
		}
		turnAround();
		while(frontIsClear()){
			move();
		}
		turnAround();
		return colNum; 
	}
}
