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

	private LocalDate dayGrouping;
	private ArrayList<DataPoint> dailyData;
	
	public DayData(LocalDate dayGrouping) {
		this.dailyData = new ArrayList<DataPoint>();
		this.dayGrouping = dayGrouping;
	}
	
	public LocalDate getDayGrouping() {
		return dayGrouping;
	}
	
	public ArrayList<DataPoint> getDailyData(){
		return dailyData;
	}
	
	public void addDailyData(DataPoint newData) {
		dailyData.add(newData);
	}
	
	public void removeDailyData(LocalDateTime timeToRemove) {
		for(int i = 0 ; i < dailyData.size(); i++) {
			if(dailyData.get(i).getTime() == timeToRemove) {
				dailyData.remove(i);
			}
		}
	}
	
	public DataPoint getDataFromTime(LocalDateTime soughtTime) {
		
		//iterate through list looking for a specific time (assuming chronological entry)
		for(int i = 0 ; i < dailyData.size(); i ++) {
			
			//if the current checked time is before what you are looking for, keep searching
		if(dailyData.get(i).getTime().isBefore(soughtTime)) {
			continue;
		}
		
		//if the current checked time is what you are looking for, return it
		else if(dailyData.get(i).getTime().isEqual(soughtTime)) {
			return dailyData.get(i);
		}
		
		//if the current checked and the previously checked times "flank" the time you are looking for, return an averaged temperature pair
		else if(dailyData.get(i - 1).getTime().isBefore(soughtTime) && dailyData.get(i).getTime().isAfter(soughtTime)) {
			DataPoint dataPoint1 = dailyData.get(i-1);
			DataPoint dataPoint2 = dailyData.get(i);
			
			return new DataPoint(soughtTime, temperatureInterpolator(dataPoint1.getTime(), dataPoint1.getTemperature(), dataPoint2.getTime(), dataPoint2.getTemperature(), soughtTime));
		}
	}
	
		//if the time did not work
	return null;
	}
	
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
	
	public boolean timeAlreadyExists(LocalDateTime queryTime) {
		boolean exists = false;
		for(int i = 0 ; i < dailyData.size(); i++) {
			if(dailyData.get(i).getTime() == queryTime) {
				exists = true;
			}
		}
		return exists;
	}
	
}
