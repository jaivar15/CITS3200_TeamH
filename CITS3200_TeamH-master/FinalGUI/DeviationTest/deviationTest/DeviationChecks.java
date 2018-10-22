package deviationTest;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Iterator;

/**
 * 
 * Determines if the current temperature value deviates unacceptably beyond
 * historical averages Returns an array where position 0 indicates if a
 * notification is required, and position 1 notifies which thresholds have been breached
 * 
 * @author Kieron Ho
 * 
 * 
 */
public class DeviationChecks {

	/**
	 * Checks the deviation of the latest data point temperature to a historic
	 * average
	 * 
	 * @param animalData is the set of day data
	 * @param acceptableDeviationMultiplier is the amount of deviation acceptable
	 * @param daysToAverage
	 * @return returnArray with index 0 indicating if notification is required and 1
	 *         is the thresholds passed
	 */
	public static double[] checkDeviationLatest(AnimalDataSet animalData, double[] acceptableDeviationMultiplier,
			int daysToAverage) {
		boolean advancedNotifications = (acceptableDeviationMultiplier.length > 1);
		double[] returnArray = new double[2];
		double currentDeviation = getDeviationFromMean(animalData, daysToAverage, null);
		double standardDev = findStandardDeviation(animalData, daysToAverage, animalData.getLatestUpdate().getTime());

		if (!advancedNotifications) {
			if (currentDeviation >= acceptableDeviationMultiplier[0] * standardDev) {
				returnArray[0] = 1;
				returnArray[1] = 1;
			} else {
				returnArray[0] = 0;
				returnArray[1] = 0;
			}
		} else {
			int thresholdLevel = 0;
			returnArray[0] = 0;
			while (acceptableDeviationMultiplier[thresholdLevel] * standardDev <= currentDeviation) {
				thresholdLevel++;
				returnArray[0] = 1;
				if (thresholdLevel == 3)
					break;
			}
			returnArray[1] = thresholdLevel;
		}
		return returnArray;
	}

	/**
	 * Checks the deviation of a selected data point temperature to a historic
	 * average
	 * 
	 * @param animalData is the set of day data
	 * @param acceptableDeviationMultiplier is the amount of deviation acceptable
	 * @param daysToAverage
	 * @param customTime
	 * @return returnArray with index0 indicating if notification is required and 1
	 * 			is the thresholds passed
	 */
	public static double[] checkDeviationCustom(AnimalDataSet animalData, double[] acceptableDeviationMultiplier,
			int daysToAverage, LocalDateTime customTime) {
		boolean advancedNotifications = (acceptableDeviationMultiplier.length > 1);
		double[] returnArray = new double[2];
		double currentDeviation = getDeviationFromMean(animalData, daysToAverage, customTime);
		double standardDev = findStandardDeviation(animalData, daysToAverage, customTime);//////////////////////////////////////

		if (!advancedNotifications) {
			if (currentDeviation >= acceptableDeviationMultiplier[0] * standardDev) {
				returnArray[0] = 1;
				returnArray[1] = 1;
			} else {
				returnArray[0] = 0;
				returnArray[1] = 0;
			}
		} else {
			int thresholdLevel = 0;
			returnArray[0] = 0;
			while (acceptableDeviationMultiplier[thresholdLevel] * standardDev <= currentDeviation) {
				thresholdLevel++;
				returnArray[0] = 1;
				if (thresholdLevel == 3)
					break;
			}
			returnArray[1] = thresholdLevel;
		}
		return returnArray;
	}

	/**
	 * Finds a running average standard deviation for all times within the daysToAverage bracket
	 * @param animalData
	 * @param daysToAverageDoubled
	 * @param time
	 * @return
	 */
	public static double findStandardDeviation(AnimalDataSet animalData, int daysToAverageDoubled, LocalDateTime time) {
		int daysToAverage = daysToAverageDoubled/2;
		double standardDeviation = 0;
		double variance = 0;
		int dataPointCount = 0;
		DayData[] days = new DayData[daysToAverage];
		
		for(int i = 0 ; i < days.length ; i++) {
			if(animalData.getIndividualDayData(time.toLocalDate().minusDays(1 + i)) != null) {
				days[i] = animalData.getIndividualDayData(time.toLocalDate().minusDays(1 + i));
			}
		}
		
		LocalDateTime[] allStandardDevTimes;
		
		for(DayData dayData:days) {//find how many datapoints to include in the analysis
			if(dayData != null) {
			dataPointCount += dayData.getFullDayDataHashMap().size();
			}
		}
		
		allStandardDevTimes = new LocalDateTime[dataPointCount];
		
		int position = 0;
		for(DayData dayData:days) {//get all the times to be used for the standard deviation
			if(dayData != null) {
			Iterator<LocalDateTime> dayIterator = dayData.getFullDayDataHashMap().keySet().iterator();
			while(dayIterator.hasNext()) {
				allStandardDevTimes[position] = dayIterator.next();
				position++;
			}
			}
		}
		
		for(LocalDateTime pointTime : allStandardDevTimes) {//iterate through all times
			HistoricTemperatures historicTemperatures = new HistoricTemperatures(animalData, daysToAverage, pointTime);//for each time make historic temperature to get mean
			double[] temperatures = historicTemperatures.getHistoricTemperatures();//get an array of all the historic temperatures
			if(temperatures.length <2) continue;
			double mean = historicTemperatures.getMean();//get the mean for these temperatures

			double temporaryTotalVariance = 0;
		for (double temperature : temperatures) {//for each temperature
			temporaryTotalVariance += Math.pow((mean - temperature), 2);//add to variance the difference squared
		}
		variance += temporaryTotalVariance/temperatures.length;//add to the total variance the historic temperatures
		}
		
		
		
		standardDeviation = Math.sqrt(variance / (double) dataPointCount);//get the average variance for all temperatures and find the square root
		return standardDeviation;
	}

	/**
	 * Modifies the temperature value of a data set to the average in previous days
	 * 
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
	 * 
	 * @param animalData
	 * @param daysToAverage
	 * @param customTime
	 * @return
	 */
	public static double getDeviationFromMean(AnimalDataSet animalData, int daysToAverage, LocalDateTime customTime) {
		if (customTime == null) {
			HistoricTemperatures pastTemperatures = new HistoricTemperatures(animalData, daysToAverage);
			return Math.abs(animalData.getLatestUpdate().getTemperature() - pastTemperatures.getMean());
		} else {
			HistoricTemperatures pastTemperatures = new HistoricTemperatures(animalData, daysToAverage, customTime);
			return Math.abs(animalData.getTemperatureFromTime(customTime) - pastTemperatures.getMean());
		}
	}
}