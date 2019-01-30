/*
 * File: Breakout.java
 * -------------------
 * Name:
 * Section Leader:
 * 
 * This file will eventually implement the game of Breakout.
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.applet.*;
import java.awt.*;
import java.awt.event.*;

import com.sun.org.apache.xpath.internal.operations.And;

public class Breakout extends GraphicsProgram {

/** Width and height of application window in pixels */
	public static final int APPLICATION_WIDTH = 400;
	public static final int APPLICATION_HEIGHT = 600;

/** Dimensions of game board (usually the same) */
	private static final int WIDTH = APPLICATION_WIDTH;
	private static final int HEIGHT = APPLICATION_HEIGHT - 60;

/** Dimensions of the paddle */
	private static final int PADDLE_WIDTH = 60;
	private static final int PADDLE_HEIGHT = 10;

/** Offset of the paddle up from the bottom */
	private static final int PADDLE_Y_OFFSET = 30;

/** Number of bricks per row */
	private static final int NBRICKS_PER_ROW = 10;

/** Number of rows of bricks */
	private static final int NBRICK_ROWS = 10;

/** Separation between bricks */
	private static final int BRICK_SEP = 4;

/** Width of a brick */
	private static final int BRICK_WIDTH =
	  (WIDTH - (NBRICKS_PER_ROW - 1) * BRICK_SEP) / NBRICKS_PER_ROW;

/** Height of a brick */
	private static final int BRICK_HEIGHT = 8;

/** Radius of the ball in pixels */
	private static final int BALL_RADIUS = 10;
	private static final int BALL_DIAMETER = 2 * BALL_RADIUS;

/** Offset of the top brick row from the top */
	private static final int BRICK_Y_OFFSET = 70;

/** Number of turns */
	private static final int NTURNS = 3;
	
	private static final int PADDLE_Y = HEIGHT - PADDLE_Y_OFFSET;

	private double paddleX = 10.0;
	private GRect paddle = new GRect(paddleX, PADDLE_Y, PADDLE_WIDTH, PADDLE_HEIGHT);
	
	private GOval ball = new GOval(BALL_DIAMETER, BALL_DIAMETER); 
	
	private double vx, vy;
	
	private RandomGenerator rgen = RandomGenerator.getInstance();
	
	private int score = 0; 
	private GLabel scoreLabel = new GLabel("Score: " + score);
	
	private int curTurnCount = NTURNS;
	private GLabel turnCountLabel = new GLabel("Turn Count: " + NTURNS);
	
	private int delay = 15;
	
	private int brickCount = NBRICK_ROWS * NBRICKS_PER_ROW;
	
	private Color brickColors[] = {Color.RED, Color.ORANGE, Color.YELLOW, 
			   					   Color.GREEN, Color.CYAN};
	private boolean hitRightWall;
	private boolean hitLeftWall;
	private boolean hitTopWall;
	private boolean hitBottomWall;
	
/* Method: run() */
/** Runs the Breakout program. */
	public void run() {
		setUpBrick();
		setUpPaddle();
		setUpBall();
		play();
	}
	
	private void setUpBrick() {
		for(int i = 0; i < NBRICK_ROWS; i++) {
			for(int j = 0; j < NBRICKS_PER_ROW; j++) {
				int brickX = (BRICK_SEP + BRICK_WIDTH) * j + BRICK_SEP / 2;
				int brickY = (BRICK_SEP + BRICK_HEIGHT) * i + BRICK_Y_OFFSET; 
				GRect brick = new GRect(brickX, brickY, BRICK_WIDTH, BRICK_HEIGHT);
				
				add(brick);
				brick.setFilled(true);
	
				int colorIndex = (int)Math.ceil(i/2);
				brick.setFillColor(brickColors[colorIndex]);	
				
			}
		}
	}
	
	private void setUpBall() {
		ball.setFilled(true);
		ball.setFillColor(Color.BLACK);
		ball.setLocation(WIDTH / 2 - BALL_RADIUS, HEIGHT / 2 - BALL_RADIUS);
		add(ball);
		// set vx as random double between 1.0 and 3.0.
		// 50% chance to be negative. 
		vx = rgen.nextDouble(1.0,  3.0);
		if(rgen.nextBoolean(0.5)) vx = -vx;
		
		vy = 3.0;
	}
	
	private void setUpPaddle() {
		addMouseListeners();
		paddle.setFilled(true);
		paddle.setFillColor(Color.BLACK);
		add(paddle);
	}
	
	private void play() {
		displayScore();
		displayTurnCounts();
		
		while(brickCount != 0) {
			GObject collider = getCollidingObject();
			// reverse vx when ball hits left or right walls
			updateWallStatus();
			
			if(hitRightWall || hitLeftWall) {
				vx = -vx;
			}
			
			// reverse vy when ball hits top wall, paddle or brick
			
			boolean isBrickOrPaddle = collider != null;
			boolean isBrick = isBrickOrPaddle && collider != paddle;
			if(hitTopWall || isBrickOrPaddle) {
				vy = -vy;
				
				// if not paddle, then brick
				// remove brick
				if(isBrick) {
					brickCount--;
					
//					Color.RED: 5 point
//					Color.ORANGE: 4 point
//					Color.YELLOW: 3 point
//					Color.GREEN: 2 point
//					Color.CYAN: 1 point
					for(int i = 0; i < 5; i++) {
						if(((GRect) collider).getFillColor() == brickColors[i]) {
							int brickScore = 5 - i;
							score += brickScore;
						}
					}
					remove(collider);
					displayScore();
				}
			} 
		
			if(hitBottomWall) {
				curTurnCount--;
				displayTurnCounts();
				setUpBall();
				if(curTurnCount == 0) {
					displayGameOver();
					remove(ball);
					break;
				}
			}
			// move ball out of reach of paddle, left or right wall, 
			// avoid ball gluing effect
			
			if(collider == paddle) {
				for(int i = 0; i < 10; i++) {
					ball.move(vx, vy);
					pause(delay);
				}
			} else if (hitRightWall || hitLeftWall || hitTopWall){
				while(hitRightWall || hitLeftWall || hitTopWall) {
					ball.move(vx, vy);
					pause(delay);
					updateWallStatus();
				}
			} else {
				ball.move(vx, vy);
				pause(delay);
			}
			
		}
		if(brickCount == 0) {
			displayGameWon();
		}
		
		
	}
	
	private GObject getCollidingObject() {
		double[][] ballCorners = new double[][]{{ball.getX(), ball.getY()}, 
								  				{ball.getX() + BALL_DIAMETER, ball.getY()}, 
								  				{ball.getX(), ball.getY() + BALL_DIAMETER}, 
								  				{ball.getX() + BALL_DIAMETER, ball.getY() + BALL_DIAMETER}};
								  	
		for(int i = 0; i < ballCorners.length; i++) {
			double curX = ballCorners[i][0];
			double curY = ballCorners[i][1];
			
			GObject collider = getElementAt(curX, curY);
			if(collider != null) {
				return collider;
			}
		}
		return null;
	}
	
	private void displayScore() {
		scoreLabel.setLabel("Score: " + score);
		scoreLabel.setFont("Helvetica-18");
		double scoreLabelX = BRICK_SEP / 2; 
		double scoreLabelY = scoreLabel.getAscent() + BALL_DIAMETER + HEIGHT;
		scoreLabel.setLocation(scoreLabelX, scoreLabelY);
		add(scoreLabel);
	}
	
	private void displayTurnCounts() {
		turnCountLabel.setLabel("Turn Count: " + curTurnCount);
		turnCountLabel.setFont("Helvetica-18");
		
		double turnCountLabelX = WIDTH - BRICK_SEP / 2.0 - turnCountLabel.getWidth();
		double turnCountLabelY = turnCountLabel.getAscent() + BALL_DIAMETER + HEIGHT;
		turnCountLabel.setLocation(turnCountLabelX, turnCountLabelY);
		add(turnCountLabel);
	}
	
	private void displayGameOver() {
		GLabel gameOver = new GLabel("Game Over!");
		gameOver.setFont("Helvetica-30");
		double gameOverX = (WIDTH - gameOver.getWidth()) / 2.0; 
		double gameOverY = (HEIGHT - gameOver.getAscent()) / 2.0;
		gameOver.setLocation(gameOverX, gameOverY);
		add(gameOver);
	}
	
	private void displayGameWon() {
		GLabel gameWon = new GLabel("You Won!");
		gameWon.setFont("Helvetica-30");
		double gameWonX = (WIDTH - gameWon.getWidth()) / 2.0; 
		double gameWonY = (HEIGHT - gameWon.getAscent()) / 2.0;
		gameWon.setLocation(gameWonX, gameWonY);
		add(gameWon);
	}
	
	public void mouseMoved(MouseEvent event) {
		double mouseX = event.getX();
		// keep entire paddle within application width
		if(mouseX <= WIDTH - PADDLE_WIDTH) {
			paddle.setLocation(mouseX, PADDLE_Y);
		}
	}
	
	private void updateWallStatus() {
		hitRightWall = ball.getX() + BALL_DIAMETER >= WIDTH;
		hitLeftWall = ball.getX() <= 0;
		hitTopWall = ball.getY() <= 10;
		hitBottomWall = ball.getY() + BALL_DIAMETER >= HEIGHT;
	}
	
}