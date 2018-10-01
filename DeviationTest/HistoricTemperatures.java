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
		int limitDays = 0;
		if(daySet.daysOfData() > daysToGather) {
			limitDays = daysToGather;
		}else limitDays = daySet.daysOfData() - 1;
		
		historicTemperatures = new double[limitDays];
		
		for(int i = 0 ; i < limitDays ; i++) {
			historicTemperatures[i] = daySet.getTemperatureFromTime(daySet.getLatestUpdate().getTime().minusDays(i + 1));
		}
		
		double totalTemp = 0;
		for(int i = 0 ; i < limitDays ; i++) {
			totalTemp += historicTemperatures[i];
		}
		mean = totalTemp/limitDays;
		
		double varianceTotal = 0;
		for(int i = 0 ; i < limitDays ; i++) {
			varianceTotal += Math.pow(mean - historicTemperatures[i], 2);
		}
		standardDeviation = Math.sqrt(varianceTotal/(double)limitDays);
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
