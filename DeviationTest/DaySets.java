import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *  DaySets is designed to hold DayData objects that are discrete day values
 * @author Kieron Ho
 * 
 *
 */
public class DaySets {
private ArrayList<DayData> dayGroups;
	
/**
 * Constructs an empty group of day data
 */
public DaySets() {
	dayGroups = new ArrayList<DayData>();
}
	
/**
 * 
 * @return The number of days worth of data stored in this set
 */
public int daysOfData() {
	return dayGroups.size();
}
	
/**
 * 
 * @return The array holding the groups of day data
 */
public ArrayList<DayData> getDayGroups() {
	return dayGroups;
}


/**
 * 
 * @param dayToGet is the date of the day to retrieve data for
 * @return the day data for the date specified
 */
public DayData getDayData(LocalDate dayToGet) {
	for(int i = 0 ; i < dayGroups.size(); i++) {
		if(dayGroups.get(i).getDayGrouping().isEqual(dayToGet)) {
			return dayGroups.get(i);
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
	for(int i = 0 ; i < dayGroups.size(); i ++) {
		if(dayGroups.get(i).getDayGrouping().isEqual(timeToFind.toLocalDate())) {
			dayThatContains = dayGroups.get(i);
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
	for(int i = 0 ; i < dayGroups.size(); i++) {
		if(dayGroups.get(i).getDayGrouping().isEqual(newTime.toLocalDate())) {
			if(dayGroups.get(i).timeAlreadyExists(newTime)) {
				dayExists = true;
			}else {
			dayGroups.get(i).addDailyData(new DataPoint(newTime, newTemperature));
			dayExists = true;
			}
		}
	}
	if(!dayExists) {
		DayData newDay = new DayData(newTime.toLocalDate());
		newDay.addDailyData(new DataPoint(newTime, newTemperature));
		dayGroups.add(newDay);
	}
}

public void addDataPoint(DataPoint newDataPoint) {
	boolean dayExists = false;
for(int i = 0 ; i < dayGroups.size(); i++) {
	if(dayGroups.get(i).getDayGrouping().isEqual(newDataPoint.getTime().toLocalDate())) {
		if(dayGroups.get(i).timeAlreadyExists(newDataPoint.getTime())) {
			dayExists = true;
		}else {
			dayGroups.get(i).addDailyData(newDataPoint);
			dayExists = true;
		}
	}
}
if(!dayExists) {
	DayData newDay = new DayData(newDataPoint.getTime().toLocalDate());
			newDay.addDailyData(newDataPoint);
			dayGroups.add(newDay);
	}
}


/**
 * 
 * @return the latest chronological data set
 */
public DataPoint getLatestUpdate() {
	DayData latestDay = dayGroups.get(0);
Iterator<DayData> dayDataIterator = dayGroups.iterator();
while(dayDataIterator.hasNext()) {
	DayData nextDay = dayDataIterator.next();
	if(nextDay.getDayGrouping().isAfter(latestDay.getDayGrouping())) {
		latestDay = nextDay;
	}
}
	DataPoint latestDataTime = latestDay.getDailyData().get(0);
Iterator<DataPoint> dataPointIterator = latestDay.getDailyData().iterator();
while(dataPointIterator.hasNext()) {
	DataPoint nextDataPoint = dataPointIterator.next();
	if(nextDataPoint.getTime().isAfter(latestDataTime.getTime())) {
		latestDataTime = nextDataPoint;
	}
}

return latestDataTime;
}
}
