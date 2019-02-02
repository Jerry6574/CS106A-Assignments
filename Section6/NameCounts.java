import acm.program.ConsoleProgram;
import java.util.*;

public class NameCounts extends ConsoleProgram{
	public void run() {
		HashMap<String, Integer> nameCounts = new HashMap<>();
		
		while(true) {
			String name = readLine("Enter name: ");
			
			if(name.equals("")) {
				for(String key: nameCounts.keySet()) {
					int count = nameCounts.get(key);
					println("Entry [" + key + "] has count " + count);
				}
				break;
			}
			
			if(nameCounts.containsKey(name)) {
				int count = nameCounts.get(name);
				nameCounts.put(name, ++count);
			} else {
				nameCounts.put(name, 1);
			}
		}
	}

}
