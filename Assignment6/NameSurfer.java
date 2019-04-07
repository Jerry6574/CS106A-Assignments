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
public class NameSurfer extends ConsoleProgram implements NameSurferConstants {
	private JTextField nameTextField;
	private JButton graph;
	private JButton clear;
/* Method: init() */
/**
 * This method has the responsibility for reading in the data base
 * and initializing the interactors at the bottom of the window.
 */
	public void init() {
		add(new JLabel("Name"), SOUTH);
		
		nameTextField = new JTextField(30);
		graph = new JButton("Graph");
		clear = new JButton("Clear");
		
		add(nameTextField, SOUTH);
		add(graph, SOUTH);
		add(clear, SOUTH);
		
		addActionListeners();
	}

/* Method: actionPerformed(e) */
/**
 * This class is responsible for detecting when the buttons are
 * clicked, so you will have to define a method to respond to
 * button actions.
 */
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == graph) {
			println("Graph: " + "\"" + nameTextField.getText() + "\"");
		} else if(e.getSource() == clear) {
			println("Clear");
		}
	}
}
