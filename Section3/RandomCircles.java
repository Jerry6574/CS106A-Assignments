
import java.awt.Color;

import acm.graphics.GOval;
import acm.program.*;
import acm.util.RandomGenerator;

public class RandomCircles extends GraphicsProgram{
    
    public void run() {
    	RandomGenerator rgen = new RandomGenerator();
    	
    	double[] randomRadii = new double[10];
    	Color[] randomColors = new Color[10];
    	double[] randomXs = new double[10];
    	double[] randomYs = new double[10];
    	
    	// add circles. On some runs of the program, not all ten circles are visisble, 
    	// a larger circle may entirely obscure a smaller circle.
    	for(int i = 0; i < 10; i++) {
    		randomRadii[i] = rgen.nextDouble(5, 50);
    		randomColors[i] = rgen.nextColor();
    		
    		// make entire circle visible in canvas
    		randomXs[i] = rgen.nextDouble(randomRadii[i], getWidth() - randomRadii[i]);
    		randomYs[i] = rgen.nextDouble(randomRadii[i], getHeight() - randomRadii[i]);
    		
    		GOval circle = new GOval(randomXs[i], randomYs[i], randomRadii[i], randomRadii[i]);
    		circle.setFilled(true);
    		circle.setFillColor(randomColors[i]);
    		add(circle);
    	}
    }
    
}
