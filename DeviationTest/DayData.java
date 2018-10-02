import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.ArrayList;

/**
 * 
 * DayData is designed to hold a set of DataPoints grouped based on which day they are designated for.
 * 
 * @author Kieron Ho
 *
 */
public class DayData {

	private LocalDate dayDateGrouping;
	private ArrayList<DataPoint> fullDayDataArray;
	
	public DayData(LocalDate dayGrouping) {
		this.fullDayDataArray = new ArrayList<DataPoint>();
		this.dayDateGrouping = dayGrouping;
	}
	
	public LocalDate getDayDateGrouping() {
		return dayDateGrouping;
	}
	
	public ArrayList<DataPoint> getFullDayDataArray(){
		return fullDayDataArray;
	}
	
	public void addDataPointToDay(DataPoint newData) {
		fullDayDataArray.add(newData);
	}
	
	public void removeFromDayData(LocalDateTime timeToRemove) {
		for(int i = 0 ; i < fullDayDataArray.size(); i++) {
			if(fullDayDataArray.get(i).getTime().compareTo(timeToRemove) == 0) {
				fullDayDataArray.remove(i);
			}
		}
	}
	
	/**
	 * 
	 * @param soughtTime
	 * @return the data point at a required time
	 */
	public DataPoint getDataFromTime(LocalDateTime soughtTime) {
		
		//iterate through list looking for a specific time (assuming chronological entry)
		for(int i = 0 ; i < fullDayDataArray.size(); i ++) {
			
			//if the current checked time is before what you are looking for, keep searching
		if(fullDayDataArray.get(i).getTime().isBefore(soughtTime)) {
			continue;
		}
		
		//if the current checked time is what you are looking for, return it
		else if(fullDayDataArray.get(i).getTime().isEqual(soughtTime)) {
			return fullDayDataArray.get(i);
		}
		
		//if the current checked and the previously checked times "flank" the time you are looking for, return an averaged temperature pair. The non-preferred route
		else if(fullDayDataArray.get(i - 1).getTime().isBefore(soughtTime) && fullDayDataArray.get(i).getTime().isAfter(soughtTime)) {
			DataPoint dataPoint1 = fullDayDataArray.get(i-1);
			DataPoint dataPoint2 = fullDayDataArray.get(i);
			
			return new DataPoint(soughtTime, temperatureInterpolator(dataPoint1.getTime(), dataPoint1.getTemperature(), dataPoint2.getTime(), dataPoint2.getTemperature(), soughtTime));
		}
	}
	
		//if the time did not work
	return null;
	}
	
	/**
	 * THis is for if a particular data set is missing for a particular time. An alternative to using a mean
	 * @param time1
	 * @param temp1
	 * @param time2
	 * @param temp2
	 * @param soughtTime
	 * @return
	 */
	private double temperatureInterpolator(LocalDateTime time1, double temp1, LocalDateTime time2, double temp2, LocalDateTime soughtTime) {
	double interpolatedTemperature = 0.0;
	long time1EpochSeconds = time1.toEpochSecond(OffsetDateTime.now().getOffset());
	long time2EpochSeconds = time2.toEpochSecond(OffsetDateTime.now().getOffset());
	long soughtTimeEpochSeconds = soughtTime.toEpochSecond(OffsetDateTime.now().getOffset());
	long timeDuration;
	
	if(time2.isAfter(time1)) {
		timeDuration = time2EpochSeconds - time1EpochSeconds;
		interpolatedTemperature = (double) (temp1 + (temp2 - temp1) * (soughtTimeEpochSeconds - time1EpochSeconds)/timeDuration);
	}
	else {
		timeDuration = time1EpochSeconds = time2EpochSeconds;
		interpolatedTemperature = (double) (temp2 + (temp1 - temp2) * (soughtTimeEpochSeconds - time2EpochSeconds)/timeDuration);
	}
	return interpolatedTemperature;
	}
	
}
