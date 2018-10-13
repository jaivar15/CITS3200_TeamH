package swing;
import java.io.File;
import java.util.ArrayList;
import swing.deserialization;



public class change {
	
	static ArrayList<Animal> animals = new ArrayList<Animal>();
	
	
	// read through every animal data file
	public static void readAnimals(String directory) {
		File folder = new File(directory);
		File[] listOfFiles = folder.listFiles();

		for (File file : listOfFiles) {
		    if (file.isFile()) {
		        String path = file.getAbsolutePath();
		        if( path.indexOf(".dat")>0 ){
                            readFile(path);
                        }
		    }
		}
	}

	private static void readFile(String filename) {
		Object[] o = null;
		deserialization deser = new deserialization(filename);
		deser.deserializeObject();
		o = deser.getData();
		if( o == null) {
			System.out.println("error");
                        System.exit(0);
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
		
		for (Animal animal : animals) {
			if (a.animalName.equals(animal.animalName)) {
				return;
			}
		}
		animals.add(a);
	}
	
}
