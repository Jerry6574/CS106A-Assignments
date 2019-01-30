import acm.program.*;

public class AddCommas extends ConsoleProgram {
    
    public void run() {
        while (true) {
            String digits = readLine("Enter a number: ");
            if(digits.length() == 0) {
            	break;
            }
            println(addCommasToNumericString(digits));
        }
    }
    
    private String addCommasToNumericString(String digits) {
    	if(digits.length() < 4) {
    		return digits;
    	} else {
    		String tail = "";
    		String head = "";
    		for(int i = 3; i < digits.length(); i += 3) {
    			head = digits.substring(0, digits.length() - i);
    			// build up commas separated tail portion
    			tail = "," + digits.substring(digits.length() - i, digits.length() - i + 3) + tail;
    		}
    		return head + tail;
    	}
    }
    
}
