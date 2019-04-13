/*
 * File: FacePamphletCanvas.java
 * -----------------------------
 * This class represents the canvas on which the profiles in the social
 * network are displayed.  NOTE: This class does NOT need to update the
 * display when the window is resized.
 */


import acm.graphics.*;
import java.awt.*;
import java.util.*;
import javax.swing.*;


public class FacePamphletCanvas extends GCanvas 
					implements FacePamphletConstants {
	
	private GLabel msgLabel;
	/** 
	 * Constructor
	 * This method takes care of any initialization needed for 
	 * the display
	 */
	public FacePamphletCanvas() {
		
	}

	
	/** 
	 * This method displays a message string near the bottom of the 
	 * canvas.  Every time this method is called, the previously 
	 * displayed message (if any) is replaced by the new message text 
	 * passed in.
	 */
	public void showMessage(String msg) {
		if(msgLabel != null) {
			remove(msgLabel);
		}
		
		msgLabel = new GLabel(msg);
		msgLabel.setFont(MESSAGE_FONT);
		
		add(msgLabel, (APPLICATION_WIDTH-msgLabel.getWidth()) / 2, APPLICATION_HEIGHT-BOTTOM_MESSAGE_MARGIN);
	}
	
	
	/** 
	 * This method displays the given profile on the canvas.  The 
	 * canvas is first cleared of all existing items (including 
	 * messages displayed near the bottom of the screen) and then the 
	 * given profile is displayed.  The profile display includes the 
	 * name of the user from the profile, the corresponding image 
	 * (or an indication that an image does not exist), the status of
	 * the user, and a list of the user's friends in the social network.
	 */
	public void displayProfile(FacePamphletProfile profile) {
		removeAll();
		String name = profile.getName();
		
		String status = profile.getStatus();
		Iterator<String> friends = profile.getFriends();
		
		// display name label
		GLabel nameLabel = new GLabel(name);
		nameLabel.setFont(PROFILE_NAME_FONT);
		double nameLabelY = TOP_MARGIN + nameLabel.getAscent();
		add(nameLabel, LEFT_MARGIN, nameLabelY);
		
		double imageY = nameLabelY + IMAGE_MARGIN;
		if(profile.getImageFileName() != null && profile.getImage() != null) {
			GImage image = profile.getImage();
			// display image
			image.scale(IMAGE_WIDTH / image.getWidth(), IMAGE_HEIGHT / image.getHeight());
			add(image, LEFT_MARGIN, imageY);
			
			// or display "no image rectangle" and "no image" label
		} else {
			GRect noImageRect = new GRect(IMAGE_WIDTH, IMAGE_HEIGHT);
			add(noImageRect, LEFT_MARGIN, imageY);
			
			GLabel noImage = new GLabel("No Image");
			double noImageX = LEFT_MARGIN + (IMAGE_WIDTH - noImage.getWidth()) / 2;
			double noImageY = imageY + (IMAGE_HEIGHT - noImage.getAscent()) / 2;
			add(noImage, noImageX, noImageY);
		}
		
		// display status label
		GLabel statusLabel;
		if(status.equals("")) {
			statusLabel = new GLabel("No current status");
		} else {
			statusLabel = new GLabel(name + " is " + status);
		}
		statusLabel.setFont(PROFILE_STATUS_FONT);
		add(statusLabel, LEFT_MARGIN, imageY + IMAGE_HEIGHT + STATUS_MARGIN + statusLabel.getAscent());	
		
		// display friends header
		GLabel friendsHeader = new GLabel("Friends: ");
		friendsHeader.setFont(PROFILE_FRIEND_LABEL_FONT);
		double friendsHeaderY = imageY;
		add(friendsHeader, APPLICATION_WIDTH / 2, friendsHeaderY);
		
		// display friend list
		int friendsVerticalOffset = 3;
		int i = 1;
		while(friends.hasNext()) {
			String friend = friends.next();
			GLabel friendLabel = new  GLabel(friend);
			friendLabel.setFont(PROFILE_FRIEND_FONT);
			
			add(friendLabel, APPLICATION_WIDTH / 2, friendsHeaderY + (friendsVerticalOffset + friendLabel.getHeight()) * i);
			i++;
		}
	}

	
}
