package GUI.swing;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InvalidPropertiesFormatException;


public class change {
	
	private static HashMap<String, Animal> animals = new HashMap<>();
	
	
	// read through every animal data file
	public static void readAnimals(String directory) {
		File folder = new File(directory);
		File[] listOfFiles = folder.listFiles();
		if (listOfFiles == null || listOfFiles.length == 0) return;
		for (File file : listOfFiles) {
		    if (file.isFile()) {
		        String path = file.getAbsolutePath();
		        if(path.indexOf(".dat")>0 ){
					try {
						readFile(path);
					} catch (InvalidPropertiesFormatException e) {
						e.printStackTrace();
					}
				}
		    }
		}
	}

	private static void readFile(String filename) throws InvalidPropertiesFormatException {
		Object[] o = null;
		deserialization deser = new deserialization(filename);
		deser.deserializeObject();
		o = deser.getData();
		if( o == null) {
			throw new InvalidPropertiesFormatException("Couldn't deserialise data file");
			//System.exit(0);
		}
		Animal a = new Animal();
		try {
			a.animalName = (String)o[0];
			a.animalDescription = (String)o[1];
			a.deviation = (double)o[2];
			a.days = (int)o[3];
			a.duration = (double)o[4];
			a.filePath = (String)o[5];
			a.orangeDev = (double)o[6];
			a.redDev = (double)o[7];
		} catch (IndexOutOfBoundsException e) {
			// this should only happen on old data files that dont have all of the fields
		}
		
		animals.put(a.animalName, a);
	}
	
	public static Animal getAnimal(String animalName){
		return animals.get(animalName);
	}
	
	public static int numberOfAnimals(){
		return animals.size();
	}
	
	public static HashMap<String, Animal> getAnimals() {
		return animals;
	}
}
