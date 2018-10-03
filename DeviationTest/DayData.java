import java.time.LocalDate;
import java.time.LocalDateTime;
//import java.time.OffsetDateTime;
//import java.util.ArrayList;
import java.util.HashMap;

/**
 * 
 * DayData is designed to hold a set of DataPoints grouped based on which day they are designated for.
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
		//fullDayDataArray.add(newData);
		fullDayDataHashMap.put(newData.getTime(), newData);
	}
	
	public void removeFromDayData(LocalDateTime timeToRemove) {
		if(fullDayDataHashMap.containsKey(timeToRemove)){
			fullDayDataHashMap.remove(timeToRemove);
		}
	}
	
	/**
	 * 
	 * @param soughtTime
	 * @return the data point at a required time
	 */
	public DataPoint getDataFromTime(LocalDateTime soughtTime) {
		
		//Use hashmap to find an existing time faster
		if(fullDayDataHashMap.containsKey(soughtTime)) {
			return fullDayDataHashMap.get(soughtTime);
		}
		
		//if the time did not work
	return null;
	}
	
}
