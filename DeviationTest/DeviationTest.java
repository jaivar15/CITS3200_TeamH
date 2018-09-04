/**
 * 
 * @author K Ho
 * 
 * Determines if the current temperature value deviates unacceptably beyond historical averages
 * Returns true if the current temperature is considered extraordinary
 */
public class DeviationTest {
	public boolean checkDeviation(CSVMonitor monitor , float acceptableDeviation, int daysToAverage) {
	
	double tempCurrent = monitor.getMostRecentData().getTemp();
	double tempAverage = 0;
	double tempSum = 0;
	
	for(int i = 0 ; i < daysToAverage ; i++) {
		Pair historicPair;
		historicPair = monitor.getPairFromTime(monitor.getMostRecentData().getDateTime().minusDays(i + 1));
		tempSum += historicPair.getTemp();
	}
	
	tempAverage = tempSum/daysToAverage;
	return Math.abs(tempAverage - tempCurrent) >= acceptableDeviation;
	}
}
