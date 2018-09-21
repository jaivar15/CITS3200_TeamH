/**
 * 
 * Determines if the current temperature value deviates unacceptably beyond historical averages
 * Returns an array where position 0 indicates if a notification is required, and position 1 how many standard deviations it is away from the average.
 * @author Kieron Ho
 * 

 */
public class DeviationTest {
	public double[] checkDeviation(DaySets daySet , double acceptableDeviation, int daysToAverage, boolean advancedNotification) {
	
	double[] returnArray = new double[2];	
	double tempAverage = 0;
	double tempSum = 0;
	double[] temperatures = new double[daysToAverage];
	double standardDeviation = 0;
	double squaredDifferences = 0;
	
	for(int i = 0 ; i < daysToAverage ; i++) {
		double historicTemperature;
		historicTemperature = daySet.getTemperatureFromTime(daySet.getLatestUpdate().getTime().minusDays(i));
		tempSum += historicTemperature;
		temperatures[i] = historicTemperature;
	}
	
	tempAverage = tempSum/daysToAverage;
	
	for(int i = 0 ; i < daysToAverage ; i++) {
		squaredDifferences += Math.pow((tempAverage - temperatures[i]),2);
	}
	
	standardDeviation = squaredDifferences/daysToAverage;
	returnArray[1] = standardDeviation/acceptableDeviation;
	
	if(standardDeviation >= standardDeviation) {
		returnArray[0] = 1;
	}
	else {
		returnArray[0] = 0;
	}
	
	return returnArray;
	}
}
