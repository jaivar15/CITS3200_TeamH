package deviationTest;
import java.time.LocalDate;
import java.time.LocalDateTime;
//import java.time.OffsetDateTime;
//import java.util.ArrayList;
import java.util.HashMap;

/**
 * 
 * DayData is designed to hold a set of DataPoints grouped based on which day they were recorded for.
 * 
 * @author Kieron Ho
 *
 */
public class DayData {

	private LocalDate dayDateGrouping;
	private HashMap<LocalDateTime, DataPoint> fullDayDataHashMap;
	
	public DayData(LocalDate dayGrouping) {
		this.dayDateGrouping = dayGrouping;
		fullDayDataHashMap = new HashMap<LocalDateTime, DataPoint>();
	}
	
	public LocalDate getDayDateGrouping() {
		return dayDateGrouping;
	}
	
	public HashMap<LocalDateTime, DataPoint> getFullDayDataHashMap(){
		return fullDayDataHashMap;
	}
	
	public void addDataPointToDay(DataPoint newData) {
		fullDayDataHashMap.put(newData.getTime(), newData);
	}
	
	public void removeFromDayData(LocalDateTime timeToRemove) {
		if(fullDayDataHashMap.containsKey(timeToRemove)){
			fullDayDataHashMap.remove(timeToRemove);
		}
	}
	
	/**
	 * Searches for a data point matching a specified time. will small deviations left and right if not initially found
	 * @param soughtTime
	 * @return the data point at a required time
	 */
	public DataPoint getDataFromTime(LocalDateTime soughtTime) {
		
		if(fullDayDataHashMap.containsKey(soughtTime)) {
			return fullDayDataHashMap.get(soughtTime);
		} else {//Search small increments left and right in case of timing errors
			for(int i = 0 ; i < 3 ; i ++) {
			if(fullDayDataHashMap.containsKey(soughtTime.plusMinutes(i))){
				return fullDayDataHashMap.get(soughtTime);
			} else if(fullDayDataHashMap.containsKey(soughtTime.minusMinutes(i))) {
				return fullDayDataHashMap.get(soughtTime);
			}
			}
		}
		

	return null;
	}
	
}
