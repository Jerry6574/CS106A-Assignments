import java.awt.event.MouseEvent;

import javax.sound.sampled.Line;

import acm.graphics.GLine;
import acm.program.*;

public class DrawLines extends GraphicsProgram{
	private double startX;
	private double startY;
	private GLine curLine;
	
	public void run() {
		addMouseListeners();
	}
	
	public void mousePressed(MouseEvent event) {
		startX = event.getX();
		startY = event.getY();
		
		curLine = new GLine(startX, startY, startX, startY);
		add(curLine);
	}
	
	public void mouseDragged(MouseEvent event) {
		double endX = event.getX();
		double endY = event.getY();
		
		curLine.setEndPoint(endX, endY);
	}
}
