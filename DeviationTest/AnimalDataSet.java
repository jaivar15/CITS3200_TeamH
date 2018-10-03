import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

/**
 *  AnimalDataSets is designed to hold DayData objects that are discrete day values
 * @author Kieron Ho
 * 
 *
 */
public class AnimalDataSet {
private ArrayList<DayData> animalDataDaysArray;
private HashSet<LocalDateTime> timesRecorded;
	
/**
 * Constructs an empty group of animal days data
 */
public AnimalDataSet() {
	animalDataDaysArray = new ArrayList<DayData>();
	timesRecorded = new HashSet<LocalDateTime>();
}
	
/**
 * 
 * @return The number of days worth of data stored in this set
 */
public int daysOfDataCount() {
	return animalDataDaysArray.size();
}

public int dataPointsCount() {
	return timesRecorded.size();
}
	
/**
 * 
 * @return The array holding the groups of day data
 */
public ArrayList<DayData> getAnimalDataDaysArray() {
	return animalDataDaysArray;
}


/**
 * 
 * @param dayToGet is the date of the day to retrieve data for
 * @return the day data for the date specified
 */
public DayData getIndividualDayData(LocalDate dayToGet) {
	for(int i = 0 ; i < animalDataDaysArray.size(); i++) {
		if(animalDataDaysArray.get(i).getDayDateGrouping().isEqual(dayToGet)) {
			return animalDataDaysArray.get(i);
		}
	}
	return null;
}


/**
 * 
 * @param timeToFind is the time to search for a data point
 * @return Is the data point that most closely fits the requested time
 */
public DataPoint getDataPointFromTime(LocalDateTime timeToFind) {
	DayData dayThatContains = null;
	for(int i = 0 ; i < animalDataDaysArray.size(); i ++) {
		if(animalDataDaysArray.get(i).getDayDateGrouping().isEqual(timeToFind.toLocalDate())) {
			dayThatContains = animalDataDaysArray.get(i);
			break;
		}
	}
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
	boolean dayExists = false;
	for(int i = 0 ; i < animalDataDaysArray.size(); i++) {
		if(animalDataDaysArray.get(i).getDayDateGrouping().isEqual(newTime.toLocalDate())) {
			if(timeAlreadyRecorded(newTime)) {
				dayExists = true;
			}else {
			animalDataDaysArray.get(i).addDataPointToDay(new DataPoint(newTime, newTemperature));
			timesRecorded.add(newTime);
			dayExists = true;
			}
		}
	}
	if(!dayExists) {
		DayData newDay = new DayData(newTime.toLocalDate());
		newDay.addDataPointToDay(new DataPoint(newTime, newTemperature));
		animalDataDaysArray.add(newDay);
		timesRecorded.add(newTime);
	}
}

public void addDataPoint(DataPoint newDataPoint) {
	boolean dayExists = false;
for(int i = 0 ; i < animalDataDaysArray.size(); i++) {
	if(animalDataDaysArray.get(i).getDayDateGrouping().isEqual(newDataPoint.getTime().toLocalDate())) {
		if(timeAlreadyRecorded(newDataPoint.getTime())) {
			dayExists = true;
		}else {
			animalDataDaysArray.get(i).addDataPointToDay(newDataPoint);
			timesRecorded.add(newDataPoint.getTime());
			dayExists = true;
		}
	}
}
if(!dayExists) {
	DayData newDay = new DayData(newDataPoint.getTime().toLocalDate());
			newDay.addDataPointToDay(newDataPoint);
			animalDataDaysArray.add(newDay);
			timesRecorded.add(newDataPoint.getTime());
	}
}

public boolean timeAlreadyRecorded(LocalDateTime timeQuery) {
	return timesRecorded.contains(timeQuery);
}


/**
 * 
 * @return the latest chronological data set
 */
public DataPoint getLatestUpdate() {
	if(animalDataDaysArray.size() == 0) {
		return null;
	}
	DayData latestDay = animalDataDaysArray.get(0);
Iterator<DayData> dayDataIterator = animalDataDaysArray.iterator();
while(dayDataIterator.hasNext()) {
	DayData nextDay = dayDataIterator.next();
	if(nextDay.getDayDateGrouping().isAfter(latestDay.getDayDateGrouping())) {
		latestDay = nextDay;
	}
}
	DataPoint latestDataTime = latestDay.getFullDayDataArray().get(0);
Iterator<DataPoint> dataPointIterator = latestDay.getFullDayDataArray().iterator();
while(dataPointIterator.hasNext()) {
	DataPoint nextDataPoint = dataPointIterator.next();
	if(nextDataPoint.getTime().isAfter(latestDataTime.getTime())) {
		latestDataTime = nextDataPoint;
	}
}

return latestDataTime;
}
}
