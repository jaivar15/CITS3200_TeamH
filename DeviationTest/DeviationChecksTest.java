package deviationTest;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

class DeviationChecksTest {

	AnimalDataSet animalOneData = new AnimalDataSet();
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
	void testCheckDeviationLatest() {
		addData();
		double[] expCaseLatest34 = {1, 3.5/(double)3};
		assertTrue(Arrays.equals(expCaseLatest34, DeviationChecks.checkDeviationLatest(animalOneData, 3, 4)));
		double[] expCaseLatest53 = {0, (30 - (20+19+35)/(double)3)/7};
		assertTrue(Arrays.equals(expCaseLatest53, DeviationChecks.checkDeviationLatest(animalOneData, 7, 3)));
	}
	
	@Test
	void testCheckDeviationCustom() {
		addData();
		double[] expCaseCustom1 = {1, 2};
		assertTrue(Arrays.equals(expCaseCustom1,  DeviationChecks.checkDeviationCustom(animalOneData, 7.25, 2, times[2])));
	}

	@Test
	void testCleanDatapoint() {
		addData();
		DeviationChecks.cleanDatapoint(animalOneData, 4);
		assertEquals(false, animalOneData.getLatestUpdate().getViability());
		assertEquals(26.5, animalOneData.getLatestUpdate().getTemperature());
	}
	
	@Test
	void testGetDeviationFromMean() {
		addData();
		assertEquals(3.5, DeviationChecks.getDeviationFromMean(animalOneData, 4, null));
		assertEquals(30 - (double)74/(double)3, DeviationChecks.getDeviationFromMean(animalOneData, 3, null));
		assertEquals(3.5, DeviationChecks.getDeviationFromMean(animalOneData,  4, timeNow));
		assertEquals(Math.abs(20 - (double)86/(double)3), DeviationChecks.getDeviationFromMean(animalOneData,  3, times[1]));
		assertEquals(Math.abs(19 - (double)67/(double)2), DeviationChecks.getDeviationFromMean(animalOneData,  2, times[2]));
	}

}
