package deviationTest;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

class HistoricTemperaturesTest {

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
	void testHistoricTemperatures() {
		addData();
		HistoricTemperatures historyCustom = new HistoricTemperatures(animalOneData, 2, times[1]);
		assertEquals(HistoricTemperatures.class, historyCustom.getClass());
		HistoricTemperatures history = new HistoricTemperatures(animalOneData, 4);
		assertTrue(history != null);
		assertEquals(HistoricTemperatures.class, history.getClass());
		HistoricTemperatures historyOverload = new HistoricTemperatures(animalOneData, 10);
		assertEquals(4, historyOverload.getHistoricTemperatures().length);
	}

	@Test
	void testGetHistoricTemperatures() {
		addData();
		assertEquals(5, animalOneData.daysOfDataCount());
		HistoricTemperatures history = new HistoricTemperatures(animalOneData, 4);
		double[] actualHistoricTemps = {20, 19, 35, 32};
		assertTrue(Arrays.equals(history.getHistoricTemperatures(), actualHistoricTemps));
		HistoricTemperatures historyCustom = new HistoricTemperatures(animalOneData, 3, times[1]);
		double[] actualHistoricTempsCustom = {19, 35, 32};
		assertTrue(Arrays.equals(historyCustom.getHistoricTemperatures(), actualHistoricTempsCustom));
	}

	@Test
	void testGetMean() {
		addData();
		HistoricTemperatures history = new HistoricTemperatures(animalOneData, 4);
		double actualMean = (20 + 19 + 35 + 32)/(double)4;
		assertEquals(actualMean, history.getMean());
		HistoricTemperatures historyShorter = new HistoricTemperatures(animalOneData, 3);
		double actualMeanShorter = (20 + 19 + 35)/(double)3;
		assertEquals(historyShorter.getMean(), actualMeanShorter);
		HistoricTemperatures historyCustom = new HistoricTemperatures(animalOneData, 3, times[1]);
		double actualMeanCustom = (19 + 35 + 32)/(double)3;
		assertEquals(actualMeanCustom, historyCustom.getMean());
	}

}
