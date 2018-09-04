package teamH;

/**
 * 
 * @author K Ho
 * 
 * Determines if the current temperature value deviates unacceptably beyond historical averages
 * Returns true if the current temperature is considered extraordinary
 */
public class DeviationTest {
	
	/*
	 * global variable
	 */
	// count how long is the data out of the deviation is in minutes 
	private int time_out_count;
	
	// in a period of time, count number of times, temperature is very un-consistent
	private int accumulated_count;
	private int period;
	
	// how many days is of used for standard deviation
	private int daysToAverage;
	
	public boolean checkDeviation(CSVMonitor monitor , float acceptableDeviation) {
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
	
	public boolean checkTime_out_count() {
		
		return false;
	}
	
	// getter and setter methods
	public int getTime_out_count() {
		return time_out_count;
	}

	public void setTime_out_count(int time_out_count) {
		this.time_out_count = time_out_count;
	}

	public int getAccumulated_count() {
		return accumulated_count;
	}

	public void setAccumulated_count(int accumulated_count) {
		this.accumulated_count = accumulated_count;
	}

	public int getDaysToAverage() {
		return daysToAverage;
	}

	public void setDaysToAverage(int daysToAverage) {
		this.daysToAverage = daysToAverage;
	}

	/**
	 * @return the period
	 */
	public int getPeriod() {
		return period;
	}

	/**
	 * @param period the period to set
	 */
	public void setPeriod(int period) {
		this.period = period;
	}
}