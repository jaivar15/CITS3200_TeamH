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
	
public DaySets() {
	dayGroups = new ArrayList<DayData>();
}
	
public int daysOfData() {
	return dayGroups.size();
}
	
public ArrayList<DayData> getDayGroups() {
	return dayGroups;
}

public DayData getDayData(LocalDate dayToGet) {
	for(int i = 0 ; i < dayGroups.size(); i++) {
		if(dayGroups.get(i).getDayGrouping() == dayToGet) {
			return dayGroups.get(i);
		}
	}
	return null;
}

public DataPoint getDataPointFromTime(LocalDateTime timeToFind) {
	DayData dayThatContains = null;
	for(int i = 0 ; i < dayGroups.size(); i ++) {
		if(dayGroups.get(i).getDayGrouping() == timeToFind.toLocalDate()) {
			dayThatContains = dayGroups.get(i);
			break;
		}
	}
	return dayThatContains.getDataFromTime(timeToFind);
}

public double getTemperatureFromTime(LocalDateTime timeToFind) {
	return getDataPointFromTime(timeToFind).getTemperature();
}

public void addDataPoint(LocalDateTime newTime, double newTemperature) {
	boolean dayExists = false;
	for(int i = 0 ; i < dayGroups.size(); i++) {
		if(dayGroups.get(i).getDayGrouping() == newTime.toLocalDate()) {
			if(dayGroups.get(i).timeAlreadyExists(newTime)) {
				dayGroups.get(i).getDataFromTime(newTime).modifyTemperature(newTemperature);
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
