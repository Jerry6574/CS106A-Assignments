/*
 * File: NameSurferGraph.java
 * ---------------------------
 * This class represents the canvas on which the graph of
 * names is drawn. This class is responsible for updating
 * (redrawing) the graphs whenever the list of entries changes or the window is resized.
 */

import acm.graphics.*;
import java.awt.event.*;
import java.util.*;
import java.awt.*;

public class NameSurferGraph extends GCanvas
	implements NameSurferConstants, ComponentListener {

	/**
	* Creates a new NameSurferGraph object that displays the data.
	*/
	public NameSurferGraph() {
		addComponentListener(this);
		//	 You fill in the rest //
	}
	
	/**
	* Clears the list of name surfer entries stored inside this class.
	*/
	public void clear() {
		//	 You fill this in //
	}
	
	/* Method: addEntry(entry) */
	/**
	* Adds a new NameSurferEntry to the list of entries on the display.
	* Note that this method does not actually draw the graph, but
	* simply stores the entry; the graph is drawn by calling update.
	*/
	public void addEntry(NameSurferEntry entry) {
		// You fill this in //
	}
	
	
	
	/**
	* Updates the display image by deleting all the graphical objects
	* from the canvas and then reassembling the display according to
	* the list of entries. Your application must call update after
	* calling either clear or addEntry; update is also called whenever
	* the size of the canvas changes.
	*/
	public void update() {
		removeAll();
		
		
		for(int i = 0; i < NDECADES; i++) {
			// add vertical lines
			double x0 = i * (double) getWidth() / 11;
			double y0 = 0;
			double x1 = x0;
			double y1 = getHeight();
			GLine verticalLine = new GLine(x0, y0, x1, y1);
			add(verticalLine);
			
			// add decade labels
			int offset = 3; 
			GLabel decade = new GLabel(Integer.toString(1900 + 10 * i));
			add(decade, x0 + offset, y1 - offset); 
		}
		
		// add horizontal lines
		add(new GLine(0, getHeight() / 30, getWidth(), getHeight() / 30));
		add(new GLine(0, getHeight() - getHeight() / 30, getWidth(), getHeight() - getHeight() / 30));
	}
	
	
	
	
	/* Implementation of the ComponentListener interface */
	public void componentHidden(ComponentEvent e) { }
	public void componentMoved(ComponentEvent e) { }
	public void componentResized(ComponentEvent e) { update(); }
	public void componentShown(ComponentEvent e) { }
}
