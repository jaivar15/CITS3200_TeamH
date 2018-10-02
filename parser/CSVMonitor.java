package parser;

import DeviationTest.DataPoint;
import DeviationTest.DaySets;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Timer;
import java.util.concurrent.atomic.AtomicBoolean;

public class CSVMonitor extends Thread{
	
	private int charCount, lineCount;
	private BufferedReader reader;
	private Timer timer = new Timer();
	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
	private String delimiter, sensorID;
	private DaySets data;
	private AtomicBoolean stop = new AtomicBoolean(false);
	private Boolean initialRead = true;
	
	public CSVMonitor(String filePath, int startLine) {
		charCount = 0;
		lineCount = 0;
		delimiter = ",";
		sensorID = "";
		data = new DaySets();
		try {
			reader = new BufferedReader(new FileReader(filePath));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		for (; lineCount < startLine; lineCount++) {
			try {
				reader.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		ReadCSV();
		initialRead = false;
	}
	
	public CSVMonitor(String filePath){
		this(filePath, 0);
	}
	
	public DataPoint ReadCSV() {
		String[] line = new String[2];
		DataPoint p = null;
		try {
			while ((line[0] = reader.readLine()) != null) {
				if (line[0].length() == 0) {
					continue;
				}
				charCount += line[0].length();
				line = line[0].split(delimiter);
				if (lineCount == 0) {
					sensorID = line[1];
					lineCount++;
					continue;
				}
				try {
					p = new DataPoint(LocalDateTime.parse(line[0], formatter), Double.parseDouble(line[1]));
					if (initialRead && data.getLatestUpdate() != null){
						if(!data.getLatestUpdate().isEqualTo(p)){
							data.addDataPoint(p);
						}
					} else if (initialRead && data.getLatestUpdate() == null){
						data.addDataPoint(p);
					}
				} catch (DateTimeParseException e) {
					e.printStackTrace();
				}
				lineCount++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return p;
	}
	
	public DaySets getData() {
		return data;
	}
	
	public boolean isStopped() { return stop.get(); }
	
	public void stopThread() { stop.set(true); }
	
	
	@Override
	public void run() {
		while (!isStopped()){
			DataPoint fromFile = ReadCSV();
			DataPoint latest = data.getLatestUpdate();
				if (!latest.isEqualTo(fromFile)) {
					data.addDataPoint(fromFile);
				}
			try {
				Thread.sleep(60000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public String getSensorID(){
		return sensorID;
	}
	
	public void setSensorID(String s){
		sensorID = s;
	}
	
	//Legacy Code
	/*
	public List<Pair> getMostRecentData(int listLength) {
		return Data.subList(Data.size() - listLength, Data.size());
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

*/
}