import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

public class CSVMonitor {
	
	private int charCount, lineCount;
	private BufferedReader reader;
	private Timer timer = new Timer();
	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
	private String delimiter, sensorID;
	private List<Pair> Data;
	
	public CSVMonitor(String l){
		charCount = 0;
		lineCount = 0;
		delimiter = ",";
		sensorID = "";
		Data = new ArrayList<>();
		try {
			reader = new BufferedReader(new FileReader(l));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void ReadCSV() {
		String[] line = new String[2];
		try {
			while ((line[0] = reader.readLine()) != null) {
				if (line[0].length() == 0){
					continue;
				}
				charCount += line[0].length();
				line = line[0].split(delimiter);
				if(lineCount == 0){
					sensorID = line[1];
					lineCount++;
					continue;
				}
				try {
					Pair p = new Pair(LocalDateTime.parse(line[0], formatter), Double.parseDouble(line[1]));
					Data.add(p);
				} catch (DateTimeParseException e) {
					e.printStackTrace();
				}
				lineCount++;
			}
		} catch (IOException e){
			e.printStackTrace();
		}
	}
	
	public List<Pair> getMostRecentData(int listLength){
		return Data.subList(Data.size()-listLength,Data.size());
	}
	
	public List<Pair> getData(){
		return Data;
	}

	//////////////////////////////////////////////////////////////////
	
	public Pair getPairFromTime(LocalDateTime soughtTime) {
	
		//iterate through list looking for a specific time (assuming chronological entry)
		for(int i = 0 ; i < Data.size(); i ++) {
			
			//if the current checked time is before what you are looking for, keep searching
		if(Data.get(i).getDateTime().isBefore(soughtTime)) {
			continue;
		}
		
		//if the current checked time is what you are looking for, return it
		else if(Data.get(i).getDateTime().isEqual(soughtTime)) {
			return Data.get(i);
		}
		
		//if the current checked and the previously checked times "flank" the time you are looking for, return an averaged temperature pair
		else if(Data.get(i - 1).getDateTime().isBefore(soughtTime) && Data.get(i).getDateTime().isAfter(soughtTime)) {
			return new Pair(soughtTime, (Data.get(i-1).getTemp() + Data.get(i).getTemp())/2);
		}
	}
	
		//if the time did not work
	return null;
	}
	
	/////////////////////////////////////////////////////////////////////	
	
	
}

class Pair {
	private LocalDateTime dateTime;
	private double temp;
	private String dateFormat = "dd/MM/yyyy HH:mm";
	Pair(LocalDateTime dt, double temp){
		dateTime = dt;
		this.temp = temp;
	}
	
	public LocalDateTime getDateTime(){
		return dateTime;
	}
	
	public String getDateTimeString(){
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateFormat);
		return formatter.format(dateTime);
	}
	
	public double getTemp(){
		return temp;
	}
	
	@Override
	public String toString() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateFormat);
		String str = formatter.format(dateTime) + ", " + String.format("%.3f", temp);
		return str;
	}
}