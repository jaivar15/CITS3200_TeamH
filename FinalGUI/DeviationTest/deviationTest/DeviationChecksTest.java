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
		//System.out.println("Checking latest deviation");
		addData();
		double pastSTD1 = DeviationChecks.findStandardDeviation(new HistoricTemperatures(animalOneData, 4).getHistoricTemperatures());
		double pastSTD2 = DeviationChecks.findStandardDeviation(new HistoricTemperatures(animalOneData, 3).getHistoricTemperatures());
		double[] deviationMultipliers1 = {1.3};
		double[] deviationMultipliers2 = {.5, 1, 2};
		
		double[] expCaseLatest34 = {1, 3.5/(double)3};
		//assertTrue(Arrays.equals(expCaseLatest34, DeviationChecks.checkDeviationLatest(animalOneData, deviationMultipliers1, 4)));
		System.out.println("STD1 = " + pastSTD1);
		System.out.println("Threshold1 = " + pastSTD1*deviationMultipliers1[0]);
		//System.out.println("Actual Deviation = " + DeviationChecks.getDeviationFromMean(animalOneData, 4, animalOneData.getLatestUpdate().getTime()));
		System.out.println("Alert 1 produced = " + Arrays.toString(DeviationChecks.checkDeviationLatest(animalOneData, deviationMultipliers1, 4)));
		double[] expCaseLatest53 = {0, (30 - (20+19+35)/(double)3)/7};
		//assertTrue(Arrays.equals(expCaseLatest53, DeviationChecks.checkDeviationLatest(animalOneData, deviationMultipliers2, 3)));
		System.out.println("STD2 = " + pastSTD2);
		System.out.println("Threshold2 1 = " + pastSTD2*deviationMultipliers2[0]);
		System.out.println("Threshold2 2 = " + pastSTD2*deviationMultipliers2[1]);
		System.out.println("Threshold2 3 = " + pastSTD2*deviationMultipliers2[2]);
		//System.out.println("Actual Deviation = " + DeviationChecks.getDeviationFromMean(animalOneData, 2, animalOneData.getLatestUpdate().getTime()));
		System.out.println("Alert 2 produced = " +Arrays.toString( DeviationChecks.checkDeviationLatest(animalOneData, deviationMultipliers2, 3)));
	}
	
	@Test
	void testCheckDeviationCustom() {
		addData();
		double[] expCaseCustom1 = {1, 2};
		//assertTrue(Arrays.equals(expCaseCustom1,  DeviationChecks.checkDeviationCustom(animalOneData, 7.25, 2, times[2])));
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
	
	@Test
	void testFindStandardDeviation() {
		double[] numbers = {2, 3, 2, 5, 4};
		double sum = 0;
		for(double number:numbers) {
			sum += number;
		}
		double mean = sum/(double)numbers.length;
		double varianceActual = 0;
		for(double number:numbers) {
			varianceActual += Math.pow(mean - number, 2);
		}
		double standardDevActual = Math.sqrt(varianceActual/(double)numbers.length);
		assertTrue(standardDevActual == (DeviationChecks.findStandardDeviation(numbers)));
	}

}
