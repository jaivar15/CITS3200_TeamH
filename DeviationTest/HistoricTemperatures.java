package deviationTest;
import java.time.LocalDateTime;
import java.util.Iterator;

/**
 * HistoricTemperatures will gather temperatures going back a specified number of days for a certain time of the day. This excludes the latest day
 * 
 * @author Kieron Ho
 *
 */
public class HistoricTemperatures {
private double[] historicTemperatures;
private double mean;
	
/**
 * Constructs historic temperature automatically based on latest added temperature data
 * @param animalData
 * @param daysToGather
 */
	public HistoricTemperatures(AnimalDataSet animalData, int daysToGather) {
		int limitDays = 0;
		if(animalData.daysOfDataCount() > daysToGather) {
			limitDays = daysToGather;
		}else limitDays = animalData.daysOfDataCount() - 1;
		
		historicTemperatures = new double[limitDays];
		int timesFound = 0;
		for(int i = 0 ; i < limitDays ; i++) {
			if(animalData.timeAlreadyRecorded(animalData.getLatestUpdate().getTime().minusDays(i + 1))) {
			historicTemperatures[i] = animalData.getTemperatureFromTime(animalData.getLatestUpdate().getTime().minusDays(i + 1));
			timesFound++;
			}
			else historicTemperatures[i] = Integer.MIN_VALUE;
		}
		
		double totalTemp = 0;
		for(int i = 0 ; i < limitDays ; i++) {
			if(historicTemperatures[i] != Integer.MIN_VALUE) {
			totalTemp += historicTemperatures[i];
			}
		}
		mean = totalTemp/timesFound;
	}
	
	/**
	 * Constructs historic temperature based on a custom time
	 * @param animalData
	 * @param daysToGather
	 * @param customTime
	 */
	public HistoricTemperatures(AnimalDataSet animalData, int daysToGather, LocalDateTime customTime) {
		int limitDays = 0;
		int numberOfPastDays = 0;
		Iterator<DayData> dayDataIterator = animalData.getAnimalDataDaysHashMap().values().iterator();
		while(dayDataIterator.hasNext()) {
			if(dayDataIterator.next().getDayDateGrouping().isBefore(customTime.toLocalDate())) {
				numberOfPastDays++;
			}
		}
		if(numberOfPastDays > daysToGather) {
			limitDays = daysToGather;
		}else limitDays = numberOfPastDays;
		
		historicTemperatures = new double[limitDays];
		int timesFound = 0;
		for(int i = 0 ; i < limitDays ; i++) {
			if(animalData.timeAlreadyRecorded(customTime.minusDays(i + 1))) {
			historicTemperatures[i] = animalData.getTemperatureFromTime(customTime.minusDays(i + 1));
			timesFound++;
			}
			else historicTemperatures[i] = Integer.MIN_VALUE;
		}
		
		double totalTemp = 0;
		for(int i = 0 ; i < limitDays ; i++) {
			if(historicTemperatures[i] != Integer.MIN_VALUE) {
			totalTemp += historicTemperatures[i];
			}
		}
		mean = totalTemp/timesFound;
	}
	
	public double[] getHistoricTemperatures() {
		return historicTemperatures;
	}
	
	public double getMean() {
		return mean;
	}
	
}
