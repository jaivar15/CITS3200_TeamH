import java.time.LocalDateTime;

/**
 * HistoricTemperatures will gather temperatures going back a specified number of days for a certain time of the day. This excludes the latest day
 * 
 * @author Kieron Ho
 *
 */
public class HistoricTemperatures {
private double[] historicTemperatures;
private double mean;
private double standardDeviation;
	
	public HistoricTemperatures(DaySets daySet, int daysToGather, LocalDateTime timeOfDay) {
		for(int i = 0 ; i < daysToGather ; i++) {
			historicTemperatures[i] = daySet.getTemperatureFromTime(daySet.getLatestUpdate().getTime().minusDays(i + 1));
		}
		
		double totalTemp = 0;
		for(int i = 0 ; i < daysToGather ; i++) {
			totalTemp += historicTemperatures[i];
		}
		mean = totalTemp/daysToGather;
		
		double stdDevTotal = 0;
		for(int i = 0 ; i < daysToGather ; i++) {
			stdDevTotal += Math.pow(mean - historicTemperatures[i], 2);
		}
		standardDeviation = stdDevTotal/daysToGather;
	}
	
	public double[] getHistoricTemperatures() {
		return historicTemperatures;
	}
	
	public double getMean() {
		return mean;
	}
	
	public double getStandardDeviation() {
		return standardDeviation;
	}
	
	
}
