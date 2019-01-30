/* MyProgram.java
 * --------------
 * Modify this file any way you like (or create additional files in
 * the ACMStarterProject) in order to experiment with the capabilities
 * of the ACM libraries.
 * 
 * NOTE: While you are free to do whatever you like with this project,
 * it is still best to use the assignment-specific starter files
 * for actual assignments.
 */
import acm.program.*;

public class Fibonacci extends ConsoleProgram {
    private static final int MAX_TERM_VALUE = 10000;
    
    public void run() {
    	int term1 = 0;
    	int term2 = 1;
    	println("This program lists the Fibonacci sequence. ");
        while (term1 <= MAX_TERM_VALUE) {
        	println(term1);
        	int term3 = term1 + term2;
        	term1 = term2;
        	term2 = term3;
        }
    }
}
