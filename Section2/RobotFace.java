

import acm.graphics.GOval;
import acm.graphics.GRect;
import acm.program.*;
import java.awt.*;

public class RobotFace extends GraphicsProgram{
	private static final int HEAD_WIDTH = 100;
	private static final int HEAD_HEIGHT = 160;
	private static final int EYE_RADIUS = 10;
	private static final int MOUTH_WIDTH = 60;
	private static final int MOUTH_HEIGHT = 20;
	
	public void run() {
		double canvasWidth = getWidth();
		double canvasHeight = getHeight();
		
		GRect head = new GRect(HEAD_WIDTH, HEAD_HEIGHT);
		head.setFilled(true);
		head.setFillColor(Color.GRAY);
		double headX = (canvasWidth - HEAD_WIDTH) / 2;
		double headY = (canvasHeight - HEAD_HEIGHT) / 2;
		head.setLocation(headX, headY);
		add(head);
		
		GOval eye1 = new GOval(2 * EYE_RADIUS, 2 * EYE_RADIUS);
		eye1.setFilled(true);
		eye1.setFillColor(Color.YELLOW);
		GOval eye2 = new GOval(2 * EYE_RADIUS, 2 * EYE_RADIUS);
		eye2.setFilled(true);
		eye2.setFillColor(Color.YELLOW);
		
		double eye1X = 0.25 * HEAD_WIDTH + headX - EYE_RADIUS;
		double eye2X = 0.75 * HEAD_WIDTH + headX - EYE_RADIUS;
		double eyeY = 0.25 * HEAD_HEIGHT + headY - EYE_RADIUS;
		
		eye1.setLocation(eye1X, eyeY);
		eye2.setLocation(eye2X, eyeY);
		
		add(eye1);
		add(eye2);
		
		GRect mouth = new GRect(MOUTH_WIDTH, MOUTH_HEIGHT);
		mouth.setFilled(true);
		mouth.setFillColor(Color.WHITE);
		
		double mouthX = headX + (HEAD_WIDTH - MOUTH_WIDTH) / 2;
		double mouthY = 0.75 * HEAD_HEIGHT + headY;
		
		mouth.setLocation(mouthX, mouthY);
		add(mouth);
	}
	
	
}
