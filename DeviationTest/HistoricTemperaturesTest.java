import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

class HistoricTemperaturesTest {

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
	void testHistoricTemperatures() {
		addData();
		HistoricTemperatures history = new HistoricTemperatures(animalOneData, 4, timeNow);
		assertTrue(history != null);
		assertEquals(HistoricTemperatures.class, history.getClass());
		HistoricTemperatures historyOverload = new HistoricTemperatures(animalOneData, 10, timeNow);
		assertEquals(4, historyOverload.getHistoricTemperatures().length);
	}

	@Test
	void testGetHistoricTemperatures() {
		addData();
		assertEquals(5, animalOneData.daysOfData());
		HistoricTemperatures history = new HistoricTemperatures(animalOneData, 4, timeNow);
		double[] actualHistoricTemps = {20, 19, 35, 32};
		assertTrue(Arrays.equals(history.getHistoricTemperatures(), actualHistoricTemps));
	}

	@Test
	void testGetMean() {
		addData();
		HistoricTemperatures history = new HistoricTemperatures(animalOneData, 4, timeNow);
		double actualMean = (20 + 19 + 35 + 32)/(double)4;
		assertEquals(actualMean, history.getMean());
		HistoricTemperatures historyShorter = new HistoricTemperatures(animalOneData, 3, timeNow);
		double actualMeanShorter = (20 + 19 + 35)/(double)3;
		assertEquals(historyShorter.getMean(), actualMeanShorter);
	}

	@Test
	void testGetStandardDeviation() {
		addData();
		HistoricTemperatures history = new HistoricTemperatures(animalOneData, 4, timeNow);
		System.out.println("Standard Deviation: " + history.getStandardDeviation());
		assertTrue((7.0887234393789 - history.getStandardDeviation()) <= 0.000001);
	}

}
