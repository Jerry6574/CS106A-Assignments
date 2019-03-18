/*
 * File: HangmanCanvas.java
 * ------------------------
 * This file keeps track of the Hangman display.
 */

import acm.graphics.*;

public class HangmanCanvas extends GCanvas {

/** Resets the display so that only the scaffold appears */
	public void reset() {
		removeAll();
		
		double ropeX = canvasWidth / 2;
		double ropeY0 = canvasHeight / 6;
		double ropeY1 = ropeY0 + ROPE_LENGTH;
		GLine rope = new GLine(ropeX, ropeY0, ropeX, ropeY1);
		
		double beamX0 = ropeX - BEAM_LENGTH;
		double beamX1 = ropeX;
		double beamY = ropeY0;
		GLine beam = new GLine(beamX0, beamY, beamX1, beamY);
		
		double scaffoldX = beamX0;
		double scaffoldY0 = ropeY0;
		double scaffoldY1 = scaffoldY0 + SCAFFOLD_HEIGHT;
		
		GLine scaffold = new GLine(scaffoldX, scaffoldY0, scaffoldX, scaffoldY1);

		add(scaffold);
		add(beam);
		add(rope);
	}

/**
 * Updates the word on the screen to correspond to the current
 * state of the game.  The argument string shows what letters have
 * been guessed so far; unguessed letters are indicated by hyphens.
 */
	public void displayWord(String word) {
		// remove previous wordLabel if any
		if(wordLabel != null) {
			remove(wordLabel);
		}
		wordLabel = new GLabel(word, 100, 580);
		wordLabel.setFont("Times-36");
		add(wordLabel);
	}

/**
 * Updates the display to correspond to an incorrect guess by the
 * user.  Calling this method causes the next body part to appear
 * on the scaffold and adds the letter to the list of incorrect
 * guesses that appears at the bottom of the window.
 */
	public void noteIncorrectGuess(char letter) {
		incorrectGuesses += letter;
		
		if(incorrectGuessesLabel != null) {
			remove(incorrectGuessesLabel);
		}
		
		incorrectGuessesLabel = new GLabel(incorrectGuesses, 100, 620);
		incorrectGuessesLabel.setFont("Times-24");
		add(incorrectGuessesLabel);
		incorrectGuessCount++;
		System.out.println(incorrectGuessCount);
		// add body parts to hangman diagram
		switch (incorrectGuessCount) {
		case 1:
			addHead();
			break;
		case 2:
			addBody();
			break;
		case 3:
			addLeftArm();
			break;
		case 4:
			addRightArm();
			break;
		case 5:
			addLeftLeg();
			break;
		case 6:
			addRightLeg();
			break;
		case 7:
			addLeftFoot();
			break;
		case 8:
			addRightFoot();
			break;
		default:
			break;
		}
	}
	
	private void addHead() {
		double headX = canvasWidth / 2 - HEAD_RADIUS;
		double headY = canvasHeight / 6 + ROPE_LENGTH;
		GOval head = new GOval(headX, headY, 2 * HEAD_RADIUS, 2 * HEAD_RADIUS);
		add(head);
	}
	
	private void addBody() {
		double bodyX = canvasWidth / 2;
		double bodyY0 = canvasHeight / 6 + ROPE_LENGTH + 2 * HEAD_RADIUS;
		double bodyY1 = bodyY0 + BODY_LENGTH;
		GLine body = new GLine(bodyX, bodyY0, bodyX, bodyY1);
		add(body);
	}
	
	private void addLeftArm() {
		double leftUpperArmX0 = canvasWidth / 2;
		double leftUpperArmX1 = leftUpperArmX0 - UPPER_ARM_LENGTH;
		double leftUpperArmY = canvasHeight / 6 + ROPE_LENGTH + 2 * HEAD_RADIUS + ARM_OFFSET_FROM_HEAD;
		GLine leftUpperArm = new GLine(leftUpperArmX0, leftUpperArmY, leftUpperArmX1, leftUpperArmY);
		
		double leftLowerArmX = leftUpperArmX1;
		double leftLowerArmY0 = leftUpperArmY;
		double leftLowerArmY1 = leftLowerArmY0 + LOWER_ARM_LENGTH;
		GLine leftLowerArm = new GLine(leftLowerArmX, leftLowerArmY0, leftLowerArmX, leftLowerArmY1);
		
		add(leftUpperArm);
		add(leftLowerArm);
	}
	
	private void addRightArm() {
		double rightUpperArmX0 = canvasWidth / 2;
		double rightUpperArmX1 = rightUpperArmX0 + UPPER_ARM_LENGTH;
		double rightUpperArmY = canvasHeight / 6 + ROPE_LENGTH + 2 * HEAD_RADIUS + ARM_OFFSET_FROM_HEAD;
		GLine rightUpperArm = new GLine(rightUpperArmX0, rightUpperArmY, rightUpperArmX1, rightUpperArmY);
		
		double rightLowerArmX = rightUpperArmX1;
		double rightLowerArmY0 = rightUpperArmY;
		double rightLowerArmY1 = rightLowerArmY0 + LOWER_ARM_LENGTH;
		GLine rightLowerArm = new GLine(rightLowerArmX, rightLowerArmY0, rightLowerArmX, rightLowerArmY1);
		
		add(rightUpperArm);
		add(rightLowerArm);
	}
	
	private void addLeftLeg() {
		double leftHipX0 = canvasWidth / 2;
		double leftHipX1 = leftHipX0 - HIP_WIDTH;
		double leftHipY = canvasHeight / 6 + ROPE_LENGTH + 2 * HEAD_RADIUS + BODY_LENGTH;
		GLine leftHip = new GLine(leftHipX0, leftHipY, leftHipX1, leftHipY);
		
		double leftLegX = leftHipX1;
		double leftLegY0 = leftHipY;
		double leftLegY1 = leftLegY0 + LEG_LENGTH;
		GLine leftLeg = new GLine(leftLegX, leftLegY0, leftLegX, leftLegY1);
		
		add(leftHip);
		add(leftLeg);
	}
	
	private void addRightLeg() {
		double rightHipX0 = canvasWidth / 2;
		double rightHipX1 = rightHipX0 + HIP_WIDTH;
		double rightHipY = canvasHeight / 6 + ROPE_LENGTH + 2 * HEAD_RADIUS + BODY_LENGTH;
		GLine lrightHip = new GLine(rightHipX0, rightHipY, rightHipX1, rightHipY);
		
		double rightLegX = rightHipX1;
		double rightLegY0 = rightHipY;
		double rightLegY1 = rightLegY0 + LEG_LENGTH;
		GLine rightLeg = new GLine(rightLegX, rightLegY0, rightLegX, rightLegY1);
		
		add(lrightHip);
		add(rightLeg);
	}
	
	private void addLeftFoot() {
		double leftFootX0 = canvasWidth / 2 - HIP_WIDTH;
		double leftFootX1 = leftFootX0 - FOOT_LENGTH;
		double leftFootY = canvasHeight / 6 + ROPE_LENGTH + 2 * HEAD_RADIUS + BODY_LENGTH + LEG_LENGTH;
		
		GLine leftFoot = new GLine(leftFootX0, leftFootY, leftFootX1, leftFootY);
		add(leftFoot);
	}
	
	private void addRightFoot() {
		double rightFootX0 = canvasWidth / 2 + HIP_WIDTH;
		double rightFootX1 = rightFootX0 + FOOT_LENGTH;
		double rightFootY = canvasHeight / 6 + ROPE_LENGTH + 2 * HEAD_RADIUS + BODY_LENGTH + LEG_LENGTH;
		
		GLine rightFoot = new GLine(rightFootX0, rightFootY, rightFootX1, rightFootY);
		add(rightFoot);
	}
	

/* Constants for the simple version of the picture (in pixels) */
	private static final int SCAFFOLD_HEIGHT = 360;
	private static final int BEAM_LENGTH = 144;
	private static final int ROPE_LENGTH = 18;
	private static final int HEAD_RADIUS = 36;
	private static final int BODY_LENGTH = 144;
	private static final int ARM_OFFSET_FROM_HEAD = 28;
	private static final int UPPER_ARM_LENGTH = 72;
	private static final int LOWER_ARM_LENGTH = 44;
	private static final int HIP_WIDTH = 36;
	private static final int LEG_LENGTH = 108;
	private static final int FOOT_LENGTH = 28;
	
	// getWidth() and getHeight() return 0.0, cannot use
	// Use following canvas width and height
	// approximates getWidth() and getHeight() at max window size
	private double canvasWidth = 640;
	private double canvasHeight = 640;
	
	private GLabel wordLabel;
	private int incorrectGuessCount = 0;
	private String incorrectGuesses = "";
	private GLabel incorrectGuessesLabel;
}
