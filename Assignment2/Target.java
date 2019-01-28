/*
 * File: Target.java
 * Name: 
 * Section Leader: 
 * -----------------
 * This file is the starter file for the Target problem.
 */

import acm.graphics.*;
import acm.program.*;
import java.awt.*;

public class Target extends GraphicsProgram {	
	
	public void run() {
		double diameters[] = new double[] {72.0, 72.0*0.65, 72.0*0.3};
		Color colors[] = new Color[] {Color.RED, Color.WHITE, Color.RED};
		
		for(int i=0; i < diameters.length; i++) {
			double diameter = diameters[i];
			double x = (getWidth()-diameter)/2;
			double y = (getHeight()-diameter)/2;
			GOval circle = new GOval(x, y, diameter, diameter);

			circle.setFilled(true);
			circle.setFillColor(colors[i]);
			
			add(circle);
		}
	
	}
}
