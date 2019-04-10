/* 
 * File: FacePamphlet.java
 * -----------------------
 * When it is finished, this program will implement a basic social network
 * management system.
 */

import acm.program.*;
import acm.graphics.*;
import acm.util.*;
import java.awt.event.*;
import javax.swing.*;

public class FacePamphlet extends ConsoleProgram 
					implements FacePamphletConstants {
	
	private FacePamphletDatabase database;
//	private FacePamphletCanvas canvas;
	
	private JButton addButton;
	private JButton deleteButton;
	private JButton lookUpButton;
	private JButton changeStatusButton;
	private JButton changePictureButton;
	private JButton addFriendButton;
	
	private JLabel nameLabel;
	
	private JTextField nameTF;
	private JTextField changeStatusTF;
	private JTextField changePictureTF;
	private JTextField addFriendTF;
	
	/**
	 * This method has the responsibility for initializing the 
	 * interactors in the application, and taking care of any other 
	 * initialization that needs to be performed.
	 */
	public void init() {
		
		database = new FacePamphletDatabase();
//		canvas = new FacePamphletCanvas();
		
		nameLabel = new JLabel("Name");
		nameTF = new JTextField(TEXT_FIELD_SIZE);
		addButton = new JButton("Add");
		deleteButton = new JButton("Delete");
		lookUpButton = new JButton("Lookup");
		
		add(nameLabel, NORTH);
		add(nameTF, NORTH);
		add(addButton, NORTH);
		add(deleteButton, NORTH);
		add(lookUpButton, NORTH);
		
		changeStatusTF = new JTextField(TEXT_FIELD_SIZE);
		changeStatusTF.addActionListener(this);
		changeStatusTF.setActionCommand("Go");
		changeStatusButton = new JButton("Change Status");
		
		changePictureTF = new JTextField(TEXT_FIELD_SIZE);
		changePictureTF.addActionListener(this);
		changePictureTF.setActionCommand("Go");
		changePictureButton =  new JButton("Change Picture");
		
		addFriendTF = new JTextField(TEXT_FIELD_SIZE);
		addFriendTF.addActionListener(this);
		addFriendTF.setActionCommand("Go");
		addFriendButton = new JButton("Add Friend");
		
		add(changeStatusTF, WEST); 
		add(changeStatusButton, WEST); 
		add(new JLabel(EMPTY_LABEL_TEXT), WEST); 
		
		add(changePictureTF, WEST); 
		add(changePictureButton, WEST); 
		add(new JLabel(EMPTY_LABEL_TEXT), WEST); 
		
		add(addFriendTF, WEST); 
		add(addFriendButton, WEST); 
		
		addActionListeners();
    }
    
  
    /**
     * This class is responsible for detecting when the buttons are
     * clicked or interactors are used, so you will have to add code
     * to respond to these actions.
     */
    public void actionPerformed(ActionEvent e) {
    	String name = nameTF.getText();
    	
		if(e.getSource() == addButton) {
			if(!name.equals("")) {
				if(database.containsProfile(name)) {
					println("Add: profile for " + name + " already exists: " + database.getProfile(name));
				} else {
					FacePamphletProfile profile = new FacePamphletProfile(name);
					database.addProfile(profile);
					println("Add: new profile: " + profile.toString());
				}
			}
			
		} else if(e.getSource() == deleteButton) {
			if(!name.equals("")) {
				if(database.containsProfile(name)) {
					database.deleteProfile(name);
					println("Delete: profile of " + name + " deleted");
				} else {
					println("Delete: profile with name " + name + " does not exist");
				}
			}
			
		} else if(e.getSource() == lookUpButton) {
			if(!name.equals("")) {
				if(database.containsProfile(name)) {
					FacePamphletProfile profile = database.getProfile(name);
					println("Lookup: " + profile.toString());
				} else {
					println("Lookup: profile with name "  + name + " does not exist");
				}
			}
		
		// handle enter press and button click
		} else if(e.getSource() == changeStatusButton || (e.getSource() == changeStatusTF && e.getActionCommand().equals("Go"))) {
			String status = changeStatusTF.getText();
			if(!status.equals("")) {
				println("Status changed to " + status);
			}
		} else if(e.getSource() == changePictureButton || (e.getSource() == changePictureTF) && e.getActionCommand().equals("Go")) {
			String pictureFile = changePictureTF.getText();
			if(!pictureFile.equals("")) {
				println("Picture changed to " + pictureFile);
			}
		} else if(e.getSource() ==  addFriendButton || (e.getSource() == addFriendTF && e.getActionCommand().equals("Go"))) {
			String friend = addFriendTF.getText();
			if(!friend.equals("")) {
				println("Added Friend: " + friend);
			}
		} 
	}

}
