/*
 * File: NameSurfer.java
 * ---------------------
 * When it is finished, this program will implements the viewer for
 * the baby-name database described in the assignment handout.
 */

import acm.program.*;
import java.awt.event.*;
import javax.swing.*;

// Temporarily extends to ConsoleProgram, switch to Program later
public class NameSurfer extends Program implements NameSurferConstants {
	private JTextField nameTextField;
	private JButton graphButton;
	private JButton clearButton;
	private NameSurferGraph graph;
	
/* Method: init() */
/**
 * This method has the responsibility for reading in the data base
 * and initializing the interactors at the bottom of the window.
 */
	public void init() {
		add(new JLabel("Name"), SOUTH);
		
		nameTextField = new JTextField(30);
		graphButton = new JButton("Graph");
		clearButton = new JButton("Clear");
		graph = new NameSurferGraph();
		
		graph.setSize(APPLICATION_WIDTH, APPLICATION_HEIGHT);
		
		add(nameTextField, SOUTH);
		add(graphButton, SOUTH);
		add(clearButton, SOUTH);
		add(graph);
		
		graph.update();
		
		addActionListeners();
	}

/* Method: actionPerformed(e) */
/**
 * This class is responsible for detecting when the buttons are
 * clicked, so you will have to define a method to respond to
 * button actions.
 */
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == graphButton) {
//			println("Graph: " + "\"" + nameTextField.getText() + "\"");
			
			
		} else if(e.getSource() == clearButton) {
//			println("Clear");
			
			
		}
	}
	
}
