/*
 * File: ProgramHierarchy.java
 * Name: 
 * Section Leader: 
 * ---------------------------
 * This file is the starter file for the ProgramHierarchy problem.
 */

import acm.graphics.*;
import acm.program.*;
import java.awt.*;

public class ProgramHierarchy extends GraphicsProgram {	
	private static final int RECT_WIDTH = 120;
	private static final int RECT_HEIGHT = 40;
	private static final int SUBCLASS_DISTANCE = 20; 
	
	public void run() {
		int canvasWidth = getWidth();
		
		double programRectX = (double)(canvasWidth - RECT_WIDTH) / 2;
		double programRectY = 150.0;
		
		double programLineX = programRectX + RECT_WIDTH / 2;
		double programLineY = programRectY + RECT_HEIGHT;
		
		double subclassRectY = programRectY + RECT_HEIGHT * 2;
		double graphicsProgramRectX = programRectX - RECT_WIDTH - SUBCLASS_DISTANCE;
		double consoleProgramRectX = programRectX; 
		double dialogProgramRectX = programRectX + RECT_WIDTH + SUBCLASS_DISTANCE;
		
		LabeledRect programRect = new LabeledRect(programRectX, programRectY, "Program");
		LabeledRect graphicsProgramRect = new LabeledRect(graphicsProgramRectX, subclassRectY, "Graphics Program");
		LabeledRect consoleProgramRect = new LabeledRect(consoleProgramRectX, subclassRectY, "Console Program");
		LabeledRect dialogProgramRect = new LabeledRect(dialogProgramRectX, subclassRectY, "Dialog Program");
		
		programRect.addLabeledRect();
		graphicsProgramRect.addLabeledRect();
		consoleProgramRect.addLabeledRect();
		dialogProgramRect.addLabeledRect();
		
		double subClassRectXs[] = {graphicsProgramRectX, consoleProgramRectX, dialogProgramRectX};
		double subClassRectYs[] = {subclassRectY, subclassRectY, subclassRectY};
		
		for(int i = 0; i < subClassRectXs.length; i++) {
			double subClassLineX = subClassRectXs[i] + RECT_WIDTH / 2;
			double subClassLineY = subClassRectYs[i];
			
			GLine line = new GLine(programLineX, programLineY, subClassLineX, subClassLineY);
			add(line);
		}
		
	}
	
	class LabeledRect{		
		private double rectX;
		private double rectY;
		private GRect rect;
		
		private double labelX;
		private double labelY;
		private String labelText;
		private GLabel label;
		
		public LabeledRect(double rectX, double rectY, String labelText) {
			this.rectX = rectX;
			this.rectY = rectY;
			this.rect = new GRect(RECT_WIDTH, RECT_HEIGHT);
			this.rect.setLocation(this.rectX, this.rectY);
			
			this.labelText = labelText;
			this.label = new GLabel(this.labelText);
			this.labelX = this.rectX + (RECT_WIDTH - this.label.getWidth()) / 2;
			this.labelY = this.rectY + (RECT_HEIGHT + this.label.getAscent()) / 2;
			this.label.setLocation(this.labelX, this.labelY);
		}
		
		public GRect getRect() {
			return this.rect;
		}
		
		public GLabel getLabel() {
			return this.label;
		}
		
		public void addLabeledRect() {
			add(getLabel());
			add(getRect());
		}
	}
}






