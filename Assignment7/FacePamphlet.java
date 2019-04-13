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
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.*;

public class FacePamphlet extends Program 
					implements FacePamphletConstants {
	
	private FacePamphletDatabase database;
	private FacePamphletProfile currentProfile;
	private FacePamphletCanvas canvas;
	
	private JButton addButton;
	private JButton deleteButton;
	private JButton lookUpButton;
	
	private JTextField databaseFileTF;
	private JButton saveButton;
	private JButton loadButton;
	
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
		canvas = new FacePamphletCanvas();
		
		nameLabel = new JLabel("Name");
		nameTF = new JTextField(TEXT_FIELD_SIZE);
		addButton = new JButton("Add");
		deleteButton = new JButton("Delete");
		lookUpButton = new JButton("Lookup");
		
		databaseFileTF = new JTextField(TEXT_FIELD_SIZE);
		saveButton = new JButton("Save Database");
		loadButton = new JButton("Load Database");		
		
		add(canvas);
		add(nameLabel, NORTH);
		add(nameTF, NORTH);
		add(addButton, NORTH);
		add(deleteButton, NORTH);
		add(lookUpButton, NORTH);
		
		add(databaseFileTF, EAST);
		add(saveButton, EAST);
		add(loadButton, EAST);
		
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
    public void actionPerformed(ActionEvent event) {
    	String name = nameTF.getText();
    	
    	// add profile
		if(event.getSource() == addButton) {
			// name is blank, do nothing
			if(!name.equals("")) {
				// profile exists
				if(database.containsProfile(name)) {
					currentProfile = database.getProfile(name);
					canvas.displayProfile(currentProfile);
					canvas.showMessage("A profile with the name " + currentProfile.getName() + " already exists");
				
				// profile doesn't exist, add new profile
				} else {
					FacePamphletProfile profile = new FacePamphletProfile(name);
					database.addProfile(profile);
					currentProfile = profile;
					canvas.displayProfile(currentProfile);
					canvas.showMessage("Displaying " + name);
				}
			}
		
		// delete profile
		} else if(event.getSource() == deleteButton) {
			if(!name.equals("")) {
				canvas.removeAll();
				
				if(database.containsProfile(name)) {
					database.deleteProfile(name);
				} else {	
					canvas.showMessage("A profile with name " + name + " does not exist");
				}
			}

			currentProfile = null;
			println("Profile of " + name + " deleted");
		
		// lookup profile
		} else if(event.getSource() == lookUpButton) {
			if(!name.equals("")) {
				if(database.containsProfile(name)) {
					FacePamphletProfile profile = database.getProfile(name);
					currentProfile = profile;
					canvas.displayProfile(currentProfile);
					canvas.showMessage("Displaying " + name);

				} else {
					currentProfile = null;
					canvas.removeAll();
					canvas.showMessage("A profile with name " + name + " does not exist");
				}
			}
		
		// change status
		} else if(event.getSource() == changeStatusButton || (event.getSource() == changeStatusTF && event.getActionCommand().equals("Go"))) {
			String status = changeStatusTF.getText();
			if(!status.equals("")) {
				if(currentProfile != null) {
					currentProfile.setStatus(status);
					canvas.displayProfile(currentProfile);
					canvas.showMessage("Status updated to " + status);
					
				} else {
					canvas.showMessage("Please select a profile to change status");
				}
			}
		
		// change picture
		} else if(event.getSource() == changePictureButton || (event.getSource() == changePictureTF) && event.getActionCommand().equals("Go")) {
			String imageFileName = changePictureTF.getText();
			GImage image = null;
			if(!imageFileName.equals("")) {
				if(currentProfile != null) {
					try {
						image = new GImage(imageFileName);
						currentProfile.setImageFileName(imageFileName);
						canvas.displayProfile(currentProfile);
						canvas.showMessage("Picture updated");
						
					} catch (ErrorException ex) {
						canvas.showMessage("Unable to open image file: " + imageFileName);
					}

				} else {
					canvas.showMessage("Please select a profile to change picture");
				}
			}
		
		// add friend
		} else if(event.getSource() == addFriendButton || (event.getSource() == addFriendTF && event.getActionCommand().equals("Go"))) {
			String friend = addFriendTF.getText();
			
			if(!friend.equals("")) {
				if(currentProfile != null) {
					if(database.containsProfile(friend)) {
						if(!friend.equals(currentProfile.getName())) {
							boolean notYetFriend = currentProfile.addFriend(friend);
							
							if(notYetFriend) {
								// add friend reciprocally
								database.getProfile(friend).addFriend(currentProfile.getName());
								canvas.displayProfile(currentProfile);
								canvas.showMessage(friend + " added as a friend");
							} else {
								canvas.showMessage(currentProfile.getName() + " already has " + friend + " as a friend");
							}
							

						} else {
							canvas.showMessage("You cannot add yourself as a friend");
						}

					} else {
						canvas.showMessage(friend + " does not exist");
					}

				} else {
					canvas.showMessage("Please select a profile to add friend. ");
				}
				
			}
		} else if(event.getSource() == loadButton) {
			String databaseFile = databaseFileTF.getText();
			
			try {
				FileInputStream fileIn = new FileInputStream(databaseFile);
				ObjectInputStream in = new ObjectInputStream(fileIn);
				database = (FacePamphletDatabase) in.readObject();
				in.close();
				fileIn.close();
				
				canvas.showMessage("Database was loaded from " + databaseFile);
				
			} catch (IOException ex) {
				canvas.showMessage("Database file was not found");
				ex.printStackTrace();
			} catch (ClassNotFoundException ex) {
				canvas.showMessage("FacePamphletDatabase class was not found");
			}
			
		} else if(event.getSource() == saveButton) {
			String databaseFile = databaseFileTF.getText();
			
			try {
				FileOutputStream fileOut = new FileOutputStream(databaseFile);
				ObjectOutputStream out = new ObjectOutputStream(fileOut);
				out.writeObject(database);
				out.close();
				fileOut.close();
				
				canvas.showMessage("Database was saved at " + databaseFile);
				
			} catch (IOException ex) {
				canvas.showMessage("Database file is not valid");
				ex.printStackTrace();
			}
			
		}
	}

}
