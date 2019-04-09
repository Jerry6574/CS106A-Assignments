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

import com.sun.beans.introspect.PropertyInfo.Name;

import java.awt.*;

public class NameSurferGraph extends GCanvas
	implements NameSurferConstants, ComponentListener {
	private ArrayList<NameSurferEntry> entryList = new ArrayList<>();
	private Color[] entryColors = {Color.BLACK, Color.RED, Color.BLUE, Color.MAGENTA};
	
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
		entryList.clear();
	}
	
	/* Method: addEntry(entry) */
	/**
	* Adds a new NameSurferEntry to the list of entries on the display.
	* Note that this method does not actually draw the graph, but
	* simply stores the entry; the graph is drawn by calling update.
	*/
	public void addEntry(NameSurferEntry entry) {
		// You fill this in //
		entryList.add(entry);
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
		
		// add vertical lines of the grid
		for(int i = 0; i < NDECADES; i++) {
			double x0 = i * (double) getWidth() / 11;
			double y0 = 0;
			double x1 = x0;
			double y1 = getHeight();
			GLine verticalLine = new GLine(x0, y0, x1, y1);
			add(verticalLine);
			
			// add decade labels
			int decadelabelOffset = 3; 
			GLabel decade = new GLabel(Integer.toString(1900 + 10 * i));
			add(decade, x0 + decadelabelOffset, y1 - decadelabelOffset); 
		}
		
		// add horizontal lines of the grid
		double hLineOffset = getHeight() / 30;
		add(new GLine(0, hLineOffset, getWidth(), hLineOffset));
		add(new GLine(0, getHeight() - hLineOffset, getWidth(), getHeight() - hLineOffset));
		
		// add each entry in entryList
		for(int i =  0; i < entryList.size(); i++) {
			NameSurferEntry entry = entryList.get(i);
			
			// cycle through entryColors
			Color currentColor = entryColors[i % 4];
			String name = entry.getName();
			
			// add nameRank label for each decade
			for(int j = 0; j < NDECADES; j++) {
				int decade = 1900 + 10 * j;
				int nextDecade = 1900 + 10 * (j + 1);
				double x0 = j * (double) getWidth() / 11;
				double x1 = (j + 1) * (double) getWidth() / 11;
				double y0;
				
				int rank = entry.getRank(decade);

				GLabel nameRank;
				// determine the nameRank label position
				if(rank > 0) {
					// y-position proportional to rank
					y0 = hLineOffset + (getHeight() - 2 * hLineOffset) * rank / MAX_RANK;
					nameRank = new GLabel(name + " " + rank, x0, y0);
				
				// put nameRank label at bottom of grid if rank is 0
				} else {
					y0 = getHeight() - hLineOffset;
					nameRank = new GLabel(name + " *", x0, y0);
				}
				nameRank.setColor(currentColor);
				add(nameRank);
				
				if(j < NDECADES - 1) {
					int nextRank = entry.getRank(nextDecade);
					GLine rankLine;
					double y1;
					
					// only draw rankLine if current rank or next rank is above 1000
					if(rank > 0 || nextRank > 0) {
						// determine the rankLine direction
						if(nextRank == 0) {
							y1 = getHeight() - hLineOffset;
						} else {
							y1 = hLineOffset + (getHeight() - 2 * hLineOffset) * nextRank / MAX_RANK;
						}
						rankLine = new GLine(x0, y0, x1, y1);
						rankLine.setColor(currentColor);
						add(rankLine);
					}
				}

			}
		}
		
	}
	
	/* Implementation of the ComponentListener interface */
	public void componentHidden(ComponentEvent e) { }
	public void componentMoved(ComponentEvent e) { }
	public void componentResized(ComponentEvent e) { update(); }
	public void componentShown(ComponentEvent e) { }

}
