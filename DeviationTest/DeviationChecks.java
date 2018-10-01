/**
 * 
 * Determines if the current temperature value deviates unacceptably beyond historical averages
 * Returns an array where position 0 indicates if a notification is required, and position 1 how many standard deviations it is away from the average (ratio)
 * @author Kieron Ho
 * 

 */
public class DeviationChecks {
	
	/**
	 * 
	 * @param daySet is the set of day data
	 * @param acceptableDeviation is the amount of deviation acceptable
	 * @param daysToAverage
	 * @param advancedNotification
	 * @return returnArray with index 0 indicating if notification is required and 1 is the ratio of standard deviations from acceptable deviations
	 */
	public static double[] checkDeviation(DaySets daySet , double acceptableDeviation, int daysToAverage, boolean advancedNotification) {
	double[] returnArray = new double[2];	
	HistoricTemperatures pastTemperatures = new HistoricTemperatures(daySet, daysToAverage, daySet.getLatestUpdate().getTime());
	pastTemperatures.getStandardDeviation();/////////////////stop coding here
	double currentDeviation = Math.abs(pastTemperatures.getMean() - daySet.getLatestUpdate().getTemperature());
	returnArray[1] = currentDeviation/acceptableDeviation;
	
	if(currentDeviation >= acceptableDeviation) {
		returnArray[0] = 1;
	}
	else returnArray[0] = 0;
	return returnArray;
	}
	
	/**
	 * Modifies the temperature value of a data set to the average in previous days
	 * @param daySet
	 * @param daysToAverage
	 */
	public static void cleanDatapoint(DaySets daySet, int daysToAverage) {
		HistoricTemperatures pastTemperatures = new HistoricTemperatures(daySet, daysToAverage, daySet.getLatestUpdate().getTime());
		double newTemperature = pastTemperatures.getMean();
		daySet.getLatestUpdate().modifyTemperature(newTemperature);
		daySet.getLatestUpdate().setViability(false);
	}
	
	public static double getDeviationFromMean(DaySets daySet, int daysToAverage) {
		HistoricTemperatures pastTemperatures = new HistoricTemperatures(daySet, daysToAverage, daySet.getLatestUpdate().getTime());
		return Math.abs(daySet.getLatestUpdate().getTemperature() - pastTemperatures.getMean());
	}
}
