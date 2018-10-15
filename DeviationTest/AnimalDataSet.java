package deviationTest;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

/**
 *  AnimalDataSets is designed to hold DayData objects that are discrete day values
 * @author Kieron Ho
 * 
 *
 */
public class AnimalDataSet {
private HashMap<LocalDate, DayData> animalDataDaysHashMap;
private HashSet<LocalDateTime> timesRecorded;
	
/**
 * Constructs the dataset for an animal
 */
public AnimalDataSet() {
	timesRecorded = new HashSet<LocalDateTime>();
	animalDataDaysHashMap = new HashMap<LocalDate, DayData>();
}
	
/**
 * 
 * @return The number of unique days worth of data stored in this set
 */
public int daysOfDataCount() {
	return animalDataDaysHashMap.size();
}

/**
 * 
 * @return the number of datapoints entered for this animal
 */
public int dataPointsCount() {
	return timesRecorded.size();
}
	
/**
 * 
 * @return The HashMap holding the groups of day data
 */
public HashMap<LocalDate, DayData> getAnimalDataDaysHashMap() {
	return animalDataDaysHashMap;
}


/**
 * 
 * @param dayToGet is the date of the day to retrieve data for
 * @return the day data for the date specified
 */
public DayData getIndividualDayData(LocalDate dayToGet) {
	Iterator<DayData> animalDayDataIterator = animalDataDaysHashMap.values().iterator();
	DayData currentCheck;
	while(animalDayDataIterator.hasNext()) {
		currentCheck = animalDayDataIterator.next();
		if(currentCheck.getDayDateGrouping().isEqual(dayToGet)) {
			return currentCheck;
		}
	}
	return null;
}


/**
 * 
 * @param timeToFind is the time to search for a data point
 * @return Is the data point that fits the requested time
 */
public DataPoint getDataPointFromTime(LocalDateTime timeToFind) {
	DayData dayThatContains = getIndividualDayData(timeToFind.toLocalDate());
	return dayThatContains.getDataFromTime(timeToFind);
}

/**
 * 
 * @param timeToFind is the time to search for a temperature value
 * @return Is the temperature that most closely fits the requested time
 */
public double getTemperatureFromTime(LocalDateTime timeToFind) {
	return getDataPointFromTime(timeToFind).getTemperature();
}

/**
 * Creates a new data point if none previously exists
 * @param newTime
 * @param newTemperature
 */
public void addDataPoint(LocalDateTime newTime, double newTemperature) {
	boolean dayExists = animalDataDaysHashMap.containsKey(newTime.toLocalDate());
	//boolean timeExists = timesRecorded.contains(newTime);
	if(!timeAlreadyRecorded(newTime)) {
		DataPoint newDataPoint = new DataPoint(newTime, newTemperature);
		if(dayExists) {
			animalDataDaysHashMap.get(newTime.toLocalDate()).addDataPointToDay(newDataPoint);
			timesRecorded.add(newTime);
		} else {
			DayData newDay = new DayData(newTime.toLocalDate());
			newDay.addDataPointToDay(newDataPoint);
			animalDataDaysHashMap.put(newDay.getDayDateGrouping(), newDay);
			timesRecorded.add(newTime);
		}
		
	}
}

/**
 * Creates a new data point if none previously exists
 * @param newDataPoint
 */
public void addDataPoint(DataPoint newDataPoint) {
	LocalDateTime newTime = newDataPoint.getTime();
	boolean dayExists = animalDataDaysHashMap.containsKey(newTime.toLocalDate());
	//boolean timeExists = timesRecorded.contains(newTime);
	
	if(!timeAlreadyRecorded(newTime)) {
		if(dayExists) {
			animalDataDaysHashMap.get(newTime.toLocalDate()).addDataPointToDay(newDataPoint);
			timesRecorded.add(newTime);
		} else {
			DayData newDay = new DayData(newTime.toLocalDate());
			newDay.addDataPointToDay(newDataPoint);
			animalDataDaysHashMap.put(newDay.getDayDateGrouping(), newDay);
			timesRecorded.add(newTime);
		}
	}
}

/**
 * Tests if a datapoint being entered for this animal will conflict with an existing datapoint
 * @param timeQuery
 * @return
 */
public boolean timeAlreadyRecorded(LocalDateTime timeQuery) {
	return timesRecorded.contains(timeQuery);
}


/**
 * 
 * @return the latest chronological DataPoint
 */
public DataPoint getLatestUpdate() {
	if(animalDataDaysHashMap.isEmpty() || timesRecorded.isEmpty()) {
		return null;
	}
	
	DayData latestDay;
Iterator<DayData> dayDataIterator = animalDataDaysHashMap.values().iterator();
latestDay = dayDataIterator.next();

while(dayDataIterator.hasNext()) {
	DayData nextDay = dayDataIterator.next();
	if(nextDay.getDayDateGrouping().isAfter(latestDay.getDayDateGrouping())) {
		latestDay = nextDay;
	}
}
	DataPoint latestDataTime;
Iterator<DataPoint> dataPointIterator = latestDay.getFullDayDataHashMap().values().iterator();
latestDataTime = dataPointIterator.next();
while(dataPointIterator.hasNext()) {
	DataPoint nextDataPoint = dataPointIterator.next();
	if(nextDataPoint.getTime().isAfter(latestDataTime.getTime())) {
		latestDataTime = nextDataPoint;
	}
}

return latestDataTime;
}
}
