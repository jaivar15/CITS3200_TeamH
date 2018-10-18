package deviationTest;

import java.time.LocalDateTime;

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
		HistoricTemperatures pastTemperatures = new HistoricTemperatures(animalData, daysToAverage);
		double standardDev = findStandardDeviation(pastTemperatures.getHistoricTemperatures());

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
		HistoricTemperatures pastTemperatures = new HistoricTemperatures(animalData, daysToAverage, customTime);
		double standardDev = findStandardDeviation(pastTemperatures.getHistoricTemperatures());

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

	public static double findStandardDeviation(double[] temperatures) {
		double standardDeviation = 0;
		double mean = 0;
		double variance = 0;
		for (double temperature : temperatures) {
			mean += temperature;
		}
		mean = mean / (double) temperatures.length;

		for (double temperature : temperatures) {
			variance += Math.pow((Math.abs(mean - temperature)), 2);
		}

		standardDeviation = Math.sqrt(variance / (double) temperatures.length);
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
