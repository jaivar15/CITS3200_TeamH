import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

class DeviationChecksTest {

	DaySets animalOneData = new DaySets();
	LocalDateTime timeNow = LocalDateTime.now().minusHours(20);
	LocalDateTime[] times = {timeNow, timeNow.minusDays(1), timeNow.minusDays(2), timeNow.minusDays(3), timeNow.minusDays(4)};
	double[] temperatures = {30, 20, 19, 35, 32};
	DataPoint pointOne = new DataPoint(times[0], temperatures[0]);
	DataPoint pointTwo = new DataPoint(times[1], temperatures[1]);
	DataPoint pointThree = new DataPoint(times[2], temperatures[2]);
	DataPoint pointFour = new DataPoint(times[3], temperatures[3]);
	DataPoint pointFive = new DataPoint(times[4], temperatures[4]);
	
	public void addData() {
	animalOneData.addDataPoint(pointOne);
	animalOneData.addDataPoint(pointTwo);
	animalOneData.addDataPoint(pointThree);
	animalOneData.addDataPoint(pointFour);
	animalOneData.addDataPoint(pointFive);
	}
	
	@Test
	void testCheckDeviation() {
		addData();
		System.out.println(Arrays.toString(DeviationChecks.checkDeviation(animalOneData, 7, 4, false)));
	}

	@Test
	void testCleanDatapoint() {
		
	}
	
	@Test
	void testGetDeviationFromMean() {
		
	}

}
