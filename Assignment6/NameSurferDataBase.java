import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import acm.util.ErrorException;
import acm.program.*;

/*
 * File: NameSurferDataBase.java
 * -----------------------------
 * This class keeps track of the complete database of names.
 * The constructor reads in the database from a file, and
 * the only public method makes it possible to look up a
 * name and get back the corresponding NameSurferEntry.
 * Names are matched independent of case, so that "Eric"
 * and "ERIC" are the same names.
 */

public class NameSurferDataBase implements NameSurferConstants {
	
	private HashMap<String, NameSurferEntry> namesDataBase;
	
/* Constructor: NameSurferDataBase(filename) */
/**
 * Creates a new NameSurferDataBase and initializes it using the
 * data in the specified file.  The constructor throws an error
 * exception if the requested file does not exist or if an error
 * occurs as the file is being read.
 */
	public NameSurferDataBase(String filename) {
		namesDataBase = new HashMap<>();
		try {
			BufferedReader namesData = new BufferedReader(new FileReader(filename));
			while(true) {
				String line = namesData.readLine();
				if(line == null) {
					break;
				}
				NameSurferEntry entry = new NameSurferEntry(line);
				namesDataBase.put(entry.getName(), entry);
			}
			namesData.close();
			
		} catch (IOException ex) {
			throw new ErrorException(ex);
		}

	}
	
/* Method: findEntry(name) */
/**
 * Returns the NameSurferEntry associated with this name, if one
 * exists.  If the name does not appear in the database, this
 * method returns null.
 */
	public NameSurferEntry findEntry(String name) {
		return namesDataBase.get(name);
	}
}

