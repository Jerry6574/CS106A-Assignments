import acm.graphics.GImage;
import acm.program.*;

public class ImageProcessing extends GraphicsProgram {
    
    public void run() {
    	GImage img = new GImage("manning.jpg");
    	GImage newImg = HFlip(img);
    	
    	add(newImg);
    }
    
    private GImage HFlip(GImage img) {
    	int[][] pixels = img.getPixelArray();
    	
    	// perform horizontal flip
    	for(int i = 0; i < pixels.length; i++) {
    		for(int j = 0; j < pixels[0].length / 2; j++) {
    			// swap start and end pixels horizontally
        		int startPx = pixels[i][j];
        		int endPx = pixels[i][pixels[0].length - 1 - j];
   
        		int tempPx = startPx;
        		pixels[i][j] = endPx;
        		pixels[i][pixels[0].length - 1 - j] = tempPx;
    		}	
    	}
    	GImage newImg = new GImage(pixels);
    	return newImg;
    }
    
}
