package deviationTest;
import java.time.LocalDateTime;

/**
 * 
 * Determines if the current temperature value deviates unacceptably beyond historical averages
 * Returns an array where position 0 indicates if a notification is required, and position 1 how many absolute deviations it is away from the average (ratio)
 * @author Kieron Ho
 * 

 */
public class DeviationChecks {
	
	/**
	 * Checks the deviation of the latest data point temperature to a historic average
	 * @param animalData is the set of day data
	 * @param acceptableDeviation is the amount of deviation acceptable
	 * @param daysToAverage
	 * @param advancedNotification
	 * @return returnArray with index 0 indicating if notification is required and 1 is the ratio of standard deviations from acceptable deviations
	 */
	public static double[] checkDeviationLatest(AnimalDataSet animalData, double acceptableDeviation, int daysToAverage) {
	double[] returnArray = new double[2];	
	double currentDeviation = getDeviationFromMean(animalData, daysToAverage, null);
	returnArray[1] = currentDeviation/acceptableDeviation;
	
	if(currentDeviation >= acceptableDeviation) {
		returnArray[0] = 1;
	}
	else returnArray[0] = 0;
	return returnArray;
	}
	
	/**
	 * Checks the deviation of a selected data point temperature to a historic average
	 * @param animalData
	 * @param acceptableDeviation
	 * @param daysToAverage
	 * @param customTime
	 * @return
	 */
	public static double[] checkDeviationCustom(AnimalDataSet animalData, double acceptableDeviation, int daysToAverage, LocalDateTime customTime) {
		double[] returnArray = new double[2];
		double currentDeviation = getDeviationFromMean(animalData, daysToAverage, customTime);
		returnArray[1] = currentDeviation/acceptableDeviation;
		
		if(currentDeviation >= acceptableDeviation) {
			returnArray[0] = 1;
		} else returnArray[0] = 0;
		return returnArray;
	}
	
	/**
	 * Modifies the temperature value of a data set to the average in previous days
	 * @param animalData
	 * @param daysToAverage
	 */
	public static void cleanDatapoint(AnimalDataSet animalData, int daysToAverage) {
		HistoricTemperatures pastTemperatures = new HistoricTemperatures(animalData, daysToAverage);
		double newTemperature = pastTemperatures.getMean();
		animalData.getLatestUpdate().modifyTemperature(newTemperature);
		animalData.getLatestUpdate().setViability(false);
	}
	
	/**
	 * Finds the amount that a time deviates from the mean of past days
	 * @param animalData
	 * @param daysToAverage
	 * @param customTime
	 * @return
	 */
	public static double getDeviationFromMean(AnimalDataSet animalData, int daysToAverage, LocalDateTime customTime) {
		if(customTime == null) {
		HistoricTemperatures pastTemperatures = new HistoricTemperatures(animalData, daysToAverage);
		return Math.abs(animalData.getLatestUpdate().getTemperature() - pastTemperatures.getMean());
		}else {
			HistoricTemperatures pastTemperatures = new HistoricTemperatures(animalData, daysToAverage, customTime);
			return Math.abs(animalData.getTemperatureFromTime(customTime) - pastTemperatures.getMean());
		}
	}
}
