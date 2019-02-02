import javax.swing.*;
import java.awt.event.*;
import java.util.HashMap;

import acm.graphics.*;
import acm.program.*;
import javax.swing.SwingConstants;

public class UsingInteractors extends GraphicsProgram {
	private static final double BOX_WIDTH = 120;
	private static final double BOX_HEIGHT = 50;
	
	private GObject gobj; /* The object being dragged */
	private double lastX; /* The last mouse X position */
	private double lastY; /* The last mouse Y position */
	
	private JButton add;
	private JButton remove;
	private JButton clear;
	JTextField textField;
	private HashMap<String, GObject> boxes = new HashMap<>();
	
    public void init() {
    	JLabel name = new JLabel("Name");
    	name.setHorizontalAlignment(SwingConstants.LEFT);
    	textField = new JTextField(35);
    	
    	add = new JButton("Add");
    	remove = new JButton("Remove");
    	clear = new JButton("Clear");
    	
    	add(name, SOUTH);
    	add(textField, SOUTH);
    	add(add, SOUTH);
    	add(remove, SOUTH);
    	add(clear, SOUTH);
    }
	
    public void run() {
    	addActionListeners();
    	addMouseListeners();
    }
    
    public void actionPerformed(ActionEvent event) {
    	String name = textField.getText();
    	
    	if(event.getSource() == add) {
    		addBox(name);
    	} else if(event.getSource() == remove) {
    		removeBox(name);
    	} else {
    		clearBoxes();
    	}
    }
    
    private void addBox(String name) {
    	double boxX = (getWidth() - BOX_WIDTH) /2;
    	double boxY = (getHeight() - BOX_HEIGHT) / 2;
    	GRect box = new GRect(boxX, boxY, BOX_WIDTH, BOX_HEIGHT);
    	
    	GLabel label = new GLabel(name);
    	double labelX = (BOX_WIDTH - label.getWidth()) / 2 + boxX;
    	double labelY = (BOX_HEIGHT + label.getDescent()) / 2 + boxY;
    	label.setLocation(labelX, labelY);
    	
    	GCompound labeledBox = new GCompound();
    	labeledBox.add(box);
    	labeledBox.add(label);
    	
    	add(labeledBox);
    	boxes.put(name, labeledBox);
    }
    
    private void removeBox(String name) {
    	if(boxes.get(name) != null) {
    		remove(boxes.get(name));
    	}
    	
    }
    
    private void clearBoxes() {
    	if(boxes.size() > 0) {
        	for(String name: boxes.keySet()) {
        		remove(boxes.get(name));
        	}
    	}

    }
    
    /** Called on mouse press to record the coordinates of the click */
	public void mousePressed(MouseEvent e) {
		lastX = e.getX();
		lastY = e.getY();
		gobj = getElementAt(lastX, lastY);
	}
	
	/** Called on mouse drag to reposition the object */
	public void mouseDragged(MouseEvent e) {
		if (gobj != null) {
			gobj.move(e.getX() - lastX, e.getY() - lastY);
			lastX = e.getX();
			lastY = e.getY();
		}
	}
	
	/** Called on mouse click to move this object to the front */
	public void mouseClicked(MouseEvent e) {
		if (gobj != null) gobj.sendToFront();
	}
}
